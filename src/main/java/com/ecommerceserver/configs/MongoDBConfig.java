package com.ecommerceserver.configs;

import java.util.Date;
import java.util.List;

import com.ecommerceserver.model.Category;
import com.ecommerceserver.model.Product;
import com.ecommerceserver.model.ReviewStar;
import com.ecommerceserver.model.Role;
import com.ecommerceserver.model.Seller;
import com.ecommerceserver.respository.CategoryRepository;
import com.ecommerceserver.respository.ProductRepository;
import com.ecommerceserver.respository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories(basePackageClasses = RoleRepository.class)
@Configuration
public class MongoDBConfig {

  // @Bean
  // CommandLineRunner initRole(RoleRepository roleRepository) {
  //   return (args) -> {
  //     roleRepository.saveAll(
  //       List.of(
  //         new Role("1", "ROLE_CUSTOMER"),
  //         new Role("2", "ROLE_SELLER"),
  //         new Role("3", "ROLE_ADMIN")
  //     ));
  //   };
  // }

  // @Bean
  // CommandLineRunner initCategory(CategoryRepository categoryRepository) {
  //   return (args) -> {
  //     categoryRepository.deleteAll();
  //     categoryRepository.saveAll(
  //       List.of(
  //         new Category("Thiết bị thông minh", "/category/smartdigitals"),
  //         new Category("Sách", "/category/books"),
  //         new Category("Điện thoại", "/category/smartphone"),
  //         new Category("Máy tính", "/category/laptop"),
  //         new Category("Thời trang", "/category/fashion"),
  //         new Category("Phụ kiện thời trang", "/category/fashion-accessories"),
  //         new Category("Điện gia dụng", "/category/homeelectric"),
  //         new Category("Sức khỏe, làm đẹp", "/category/health-beauty")
  //     ));
  //   };
  // }

  // @Bean
  // CommandLineRunner initProducts(ProductRepository productRepository, CategoryRepository categoryRepository) {
  //   return (args) -> {
  //     productRepository.deleteAll();
  //     Category smartDigital = categoryRepository.findByName("Thiết bị thông minh");
  //     Category book = categoryRepository.findByName("Sách");
  //     Seller seller1 = new Seller();
  //     productRepository.saveAll(
  //       List.of(
  //         new Product("Đồng Hồ Thông Minh Apple Watch Series 5 GPS Only Aluminum Case With Sport Band", (long)11990000, "Apple Watch Series 5 mang lại cho bạn nhiều sự lựa chọn, không chỉ lựa chọn kích thước, dây đeo, mà nay mang đến vô số màu sắc và chất liệu mặt đồng hồ. Phù hợp với mọi nhu cầu của bạn, phù hợp với tất cả mọi người. ECG trên Apple Watch Series 5 giúp bạn luôn theo dõi nhịp tim, theo dõi tình trạng sức khỏe.", 100, "/img/product/407c703670c083b0505b614ec81c94ee.jpg", smartDigital, seller1, new ReviewStar(), new Date(), new Date()),
          
  //         new Product("Bố Già (Mario Puzo)", (long)66000, "Thế giới ngầm được phản ánh trong tiểu thuyết Bố Già là sự gặp gỡ giữa một bên là ý chí cương cường và nền tảng gia tộc chặt chẽ theo truyền thống mafia xứ Sicily với một bên là xã hội Mỹ nhập nhằng đen trắng, mảnh đất màu mỡ cho những cơ hội làm ăn bất chính hứa hẹn những món lợi kếch xù. Trong thế giới ấy, hình tượng Bố Già được tác giả dày công khắc họa đã trở thành bức chân dung bất hủ trong lòng người đọc. Từ một kẻ nhập cư tay trắng đến ông trùm tột đỉnh quyền uy, Don Vito Corleone là con rắn hổ mang thâm trầm, nguy hiểm khiến kẻ thù phải kiềng nể, e dè, nhưng cũng được bạn bè, thân quyến xem như một đấng toàn năng đầy nghĩa khí. Nhân vật trung tâm ấy đồng thời cũng là hiện thân của một pho triết lí rất “đời” được nhào nặn từ vốn sống của hàng chục năm lăn lộn giữa chốn giang hồ bao phen vào sinh ra tử, vì thế mà có ý kiến cho rằng “Bố Già là sự tổng hòa của mọi hiểu biết. Bố Già là đáp án cho mọi câu hỏi”.", 100, "/img/product/7eca66c10e5f99f14325733bf53aa425.jpg", book, seller1, new ReviewStar(), new Date(), new Date()),
          
  //         new Product("Đất Máu Sicily (Mario Puzo)", (long)72000, "Sicily ngập tràn ánh mặt trời Địa Trung Hải, thoang thoảng hương cam chanh là xứ sở của núi non đẹp như tranh rải rác những phế tích lâu đời. Ở đó tầng lớp dân nghèo suốt nhiều thế kỉ phải chịu sự đè nén của nhiều thế lực tham lam và hà khắc, từ ngoại xâm đến phát-xít, từ các phe phái chính trị đến giới mafia và quý tộc địa phương. Ở đó những huyền thoại anh hùng xa xưa được truyền tụng như chỉ là mơ ước. Cho đến một ngày, xứ sở tươi đẹp và bạo tàn ấy đã sản sinh ra một nhân vật được xưng tụng là anh hùng của dân nghèo, là Robin Hood của nước Ý…", 100, "/img/product/dat mau - bia 1.u335.d20160701.t101955.jpg", book, seller1, new ReviewStar(), new Date(), new Date()),
          
  //         new Product("Không Gia Đình (Tái Bản)", (long)65000, "Không Gia Đình là tiểu thuyết nổi tiếng nhất trong sự nghiệp văn chương của Hector Malot. Hơn một trăm năm nay, tác phẩm giành giải thưởng của Viện Hàn Lâm Văn học Pháp này đã trở thành người bạn thân thiết của thiếu nhi và tất cả những người yêu mến trẻ khắp thế giới.\nKhông Gia Đình kể về chuyện đời Rémi, một cậu bé không cha mẹ, họ hàng thân thích. Sau khi phải rời khỏi vòng tay của người má nuôi, em đã đi theo đoàn xiếc thú của cụ già Vitalis tốt bụng. Kể từ đó, em lưu lạc khắp nơi, ban đầu dưới sự che chở của cụ Vitalis, sau đó thì tự lập và còn lo cả công việc biểu diễn và sinh sống cho cả một gánh hát rong. Đã có lúc em và cả đoàn lang thang cả mấy ngày đói khát rồi còn suýt chết rét. Có bận em bị lụt ngầm chôn trong giếng mỏ hàng tuần. Rồi có lần em còn mắc oan  bị giải ra tòa và phải ở tù. Nhưng cũng có khi em được nuôi nấng đàng hoàng, no ấm. Song dù trong hoàn cảnh nào, Rémi vẫn giữ được sự gan dạ, ngay thẳng, lòng tự trọng, tính thương người, ham lao động chứ không hạ mình hay gian dối. Cuối cùng, sau bao gian nan khổ cực, em đã đoàn tụ được với gia đình của mình.\nTác phẩm đã ca ngợi sự lao động bền bỉ, tinh thần tự lập, chịu đựng gian khó, khích lệ tình bạn chân chính. Ca ngợi lòng nhân ái, tình yêu cuộc sống, ý chí vươn lên không ngừng…Không Gia Đình vì thế đã vượt qua biên giới nước Pháp và tồn tại lâu dài với thời gian.", 100, "/img/product/khong-gia-dinh---pc-bia-mem.u5552.d20171101.t141222.773750.jpg", book, seller1, new ReviewStar(), new Date(), new Date()),
          
  //         new Product("Những Người Khốn Khổ (Trọn Bộ 5 Tập)", (long)240000, "Khi pháp luật và phong hóa còn đày đọa con người, còn dựng nên những địa ngục giữa xã hội văn minh và đem một thứ định mệnh nhân tạo chồng thêm lên thiên mệnh; khi ba vấn đề lớn của thời đại là sự sa đọa của đàn ông vì bán sức lao động, sự trụy lạc của đàn bà vì đói khát, sự cằn cỗi của trẻ nhỏ vì tối tăm, chưa được giải quyết; khi ở một số nơi đời sống còn ngạt thở; nói khác đi, và trên quan điểm rộng hơn, khi trên mặt đất, dốt nát và đói khổ còn tồn tại, thì những cuốn sách như loại này còn có thể có ích.", 100, "/img/product/433cf186367f485d62bd4d80379fc9fd.jpg", book, seller1, new ReviewStar(), new Date(), new Date()),
          
  //         new Product("Hai Số Phận (Bìa Cứng)", (long)440000, "“Hai số phận” không chỉ đơn thuần là một cuốn tiểu thuyết, đây có thể xem là \"thánh kinh\" cho những người đọc và suy ngẫm, những ai không dễ dãi, không chấp nhận lối mòn.\n“Hai số phận” làm rung động mọi trái tim quả cảm, nó có thể làm thay đổi cả cuộc đời bạn. Đọc cuốn sách này, bạn sẽ bị chi phối bởi cá tính của hai nhân vật chính, hoặc bạn là Kane, hoặc sẽ là Abel, không thể nào nhầm lẫn. Và điều đó sẽ khiến bạn thấy được chính mình.", 100, "/img/product/407c703670c083b0505b614ec81c94ee.jpg", book, seller1, new ReviewStar(), new Date(), new Date()),
          
  //         new Product("Tuổi Thơ Dữ Dội (Bản Mới 2013)", (long)85000, "Nhắc đến Phùng Quán, người ta sẽ nhớ ngay tới một cây bút lạ kỳ trong nền văn học Việt Nam với một tác phẩm thiếu nhi vô cùng chân thực và xúc động về một thế hệ trẻ anh hùng: Tuổi thơ dữ dội - cuốn truyện xoay quanh cuộc sống chiến đấu và hy sinh của những thiếu niên 13, 14 tuổi trong hàng ngũ Đội thiếu niên trinh sát của trung đoàn Trần Cao Vân. Những Lượm, Mừng, Quỳnh sơn ca, Hòa đen, Bồng da rắn, Vịnh sưa, Tư dát... mỗi người một hoàn cảnh song đều chung quyết tâm, nhiệt huyết và lòng yêu nước, đã tham gia chiến đấu hết mình và hy sinh khi tuổi đời còn rất trẻ.", 100, "/img/product/tuoi-tho-du-doi-a.jpg", book, seller1, new ReviewStar(), new Date(), new Date()),
          
  //         new Product("Hộp Sách: Tây Du Ký (Tái Bản 2018)", (long)260000, "Tây du ký là đỉnh cao sáng tạo trong lịch sử phát triển về tiểu thuyết cổ điển của Trung Quốc). Chủ nghĩa lãng mạn tích cực là đặc sắc nghệ thuật chủ yếu của nó. Nó đã kế thừa và phát huy truyền thống tốt đẹp của thần thoại cổ đại Trung Quốc, đã thể hiện sức sáng tạo vĩ đại và sức tưởng tượng phong phú của dân tộc Trung Hoa. \"Chủ nghĩa lãng mạn là cơ sở của thần thoại\" (lời của M.Goocki) Tây du ký chính là một bộ tiểu thuyết thần thoại trường thiên vĩ đại, lãng mạn tích cực. Thông qua hình thức ảo tưởng thần kỳ và nội dung bắt nguồn ở hiện thực mà cao hơn hiện thực. Tây du ký đã phản ánh đầy đủ, khúc chiết, phức tạp lý tưởng cao quý và đời sống hiện thực của nhân dân.", 100, "/img/product/5432cf841314cf28c9d5cde258955cb5.jpg", book, seller1, new ReviewStar(), new Date(), new Date()),
          
  //         new Product("chienbinhcauvong.u5430.d20170927.t153952.139563.jpg", (long)65000, "Một tác phẩm có tầm ảnh hưởng sâu rộng nhất Indonesia. “Thầy Harfan và cô Mus nghèo khổ đã mang đến cho tôi tuổi thơ đẹp nhất, tình bạn đẹp nhất, và tâm hồn phong phú, một thứ gì đó vô giá, thậm chí còn có giá trị hơn những khao khát mơ ước. Có thể tôi lầm, nhưng theo ý tôi, đây thật sự là hơi thở của giáo dục và linh hồn của một chốn được gọi là trường học.” - (Trích tác phẩm)", 100, "/img/product/407c703670c083b0505b614ec81c94ee.jpg", book, seller1, new ReviewStar(), new Date(), new Date())
  //     ));
  //   };
  // }

}