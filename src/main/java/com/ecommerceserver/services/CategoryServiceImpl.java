package com.ecommerceserver.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import com.ecommerceserver.model.Category;
import com.ecommerceserver.respository.CategoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

  @Autowired
  CategoryRepository categoryRepository;

	@Override
	public List<Category> addList(List<Category> lCategories) {
		return categoryRepository.saveAll(lCategories);
	}

	@Override
	public List<Category> getList() {
		return categoryRepository.findAll();
	}

	@Override
	public Category addOne(Category category) {
		return categoryRepository.save(category);
	}

	@Override
	public Optional<Category> getById(String categoryId) {
		return categoryRepository.findById(categoryId);
	}

	@Override
	public List<Category> addChildren(String categoryId, Category category) {
		Optional<Category> found = categoryRepository.findById(categoryId);
		if(found.isEmpty()) {
			return new ArrayList<>();
		}
		Category foundCategory = found.get();

		// Create new list category save the old and add new one
		List<Category> newChildren = new ArrayList<Category>();
		if(foundCategory.getChildren() != null) {
			Iterator<Category> iterator = foundCategory.getChildren().iterator();
			while (iterator.hasNext()) {
				newChildren.add((Category) iterator.next());
			}
		}
		newChildren.add(category);
		foundCategory.setChildren(newChildren);
		categoryRepository.save(foundCategory);
		return categoryRepository.findAll();
	}

}