package com.ecommerceserver.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerceserver.model.ERole;
import com.ecommerceserver.model.Role;
import com.ecommerceserver.model.User;
import com.ecommerceserver.payload.request.LoginRequest;
import com.ecommerceserver.payload.request.SignupRequest;
import com.ecommerceserver.payload.response.JwtResponse;
import com.ecommerceserver.payload.response.MessageResponse;
import com.ecommerceserver.respository.RoleRepository;
import com.ecommerceserver.respository.UserRepository;
import com.ecommerceserver.security.jwt.JwtUtils;
import com.ecommerceserver.security.services.UserDetailsImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class UserController {
  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  UserRepository userRepository;

  @Autowired
  RoleRepository roleRepository;

  @Autowired
  PasswordEncoder encoder;

  @Autowired
  JwtUtils jwtUtils;

  @PostMapping("/login")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

    try {
      // FIX: notify if email already existed
      Authentication authentication = authenticationManager
          .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

      SecurityContextHolder.getContext().setAuthentication(authentication);
      String jwt = jwtUtils.generateJwtToken(authentication);

      UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
      List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
          .collect(Collectors.toList());

      return ResponseEntity.ok(new JwtResponse(userDetails.getId(), userDetails.getUsername(),
          userDetails.getFullname(), userDetails.getEmail(), jwt, roles.get(0)));
    } catch (BadCredentialsException e) {
      return new ResponseEntity<>(new MessageResponse(0, "Tài khoản hoặc mật khẩu không đúng."),
          HttpStatus.UNAUTHORIZED);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
  }

  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
      return ResponseEntity.badRequest().body(new MessageResponse(0, "Email này đã được đăng ký."));
    }

    // Create new user's account
    User user = new User(signUpRequest.getEmail(), encoder.encode(signUpRequest.getPassword()),
        signUpRequest.getFullname(), signUpRequest.getPhoneNumber());

    String strRoles = signUpRequest.getRole();
    Role roles;

    if (strRoles == null) {
      Role customerRole = roleRepository.findByName(ERole.ROLE_CUSTOMER)
          .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
      roles = customerRole;
    } else {
      switch (strRoles) {
        /**
         * Disable for the risky =]]
         */
        // case "ROLE_ADMIN":
        // Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
        // .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        // roles = adminRole;
        // break;
        case "ROLE_SELLER":
          // TODO: Need to add approve pending list by admin in real world
          Role sellerRole = roleRepository.findByName(ERole.ROLE_SELLER)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles = sellerRole;
          break;
        default:
          Role customerRole = roleRepository.findByName(ERole.ROLE_CUSTOMER)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles = customerRole;
      }
    }

    user.setRole(roles);
    userRepository.save(user);

    Authentication authentication = authenticationManager
        .authenticate(new UsernamePasswordAuthenticationToken(signUpRequest.getEmail(), signUpRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtUtils.generateJwtToken(authentication);

    return ResponseEntity.ok(new JwtResponse(user.getId(), user.getUsername(), user.getFullname(), user.getEmail(), jwt,
        user.getRole().getName()));
  }
}