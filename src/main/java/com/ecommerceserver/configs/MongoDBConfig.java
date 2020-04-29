package com.ecommerceserver.configs;

import java.util.Date;
import java.util.List;

import com.ecommerceserver.model.Category;
import com.ecommerceserver.model.Product;
import com.ecommerceserver.model.ReviewStar;
import com.ecommerceserver.model.Role;
import com.ecommerceserver.model.Seller;
import com.ecommerceserver.model.User;
import com.ecommerceserver.respository.CategoryRepository;
import com.ecommerceserver.respository.ProductRepository;
import com.ecommerceserver.respository.RoleRepository;
import com.ecommerceserver.respository.SellerRepository;
import com.ecommerceserver.respository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableMongoRepositories(basePackageClasses = RoleRepository.class)
@Configuration
public class MongoDBConfig {

        // @Bean
        // CommandLineRunner initRole(RoleRepository roleRepository) {
        // return (args) -> {
        // roleRepository.saveAll(List.of(new Role("1", "ROLE_CUSTOMER"), new Role("2",
        // "ROLE_SELLER"),
        // new Role("3", "ROLE_ADMIN")));
        // };
        // }

        // @Bean
        // CommandLineRunner initCategory(CategoryRepository categoryRepository) {
        // return (args) -> {
        // categoryRepository.deleteAll();
        // categoryRepository.saveAll(List.of(new Category("Thiết bị thông minh"), new
        // Category("Sách"),
        // new Category("Điện thoại"), new Category("Máy tính"),
        // new Category("Thời trang"), new Category("Phụ kiện thời trang"),
        // new Category("Điện gia dụng"), new Category("Sức khỏe, làm đẹp")));
        // };
        // }

        // @Bean
        // CommandLineRunner initSeller(UserRepository userRepository, PasswordEncoder
        // encoder) {
        // return (args) -> {
        // Role role = new Role("2", "ROLE_SELLER");
        // Seller seller1 = new Seller();
        // seller1.setEmail("asdf@asdf.asdf");
        // seller1.setFullname("N H D");
        // seller1.setId("5ea559a87d0d4f053d1248cc");
        // seller1.setPassword(encoder.encode("password"));
        // seller1.setPhoneNumber("phoneNumber");
        // seller1.setRole(role);
        // seller1.setUsername("asdf");

        // Seller vietSeller = new Seller();
        // vietSeller.setEmail("zxc@zxc.zxc");
        // vietSeller.setFullname("Open Store");
        // vietSeller.setId("5ea559a87d0d4f053d1248c0");
        // vietSeller.setPassword(encoder.encode("zxczxc"));
        // vietSeller.setPhoneNumber("0987654321");
        // vietSeller.setRole(role);
        // vietSeller.setUsername("openstore");

        // userRepository.save(seller1);
        // userRepository.save(vietSeller);
        // };
        // }

        // @Bean
        // CommandLineRunner initProducts(ProductRepository productRepository,
        // CategoryRepository categoryRepository,
        // SellerRepository sellerRepository) {
        // return (args) -> {

        // productRepository.deleteAll();
        // Category smartDigital = categoryRepository.findByName("Thiết bị thông minh");
        // Category book = categoryRepository.findByName("Sách");
        // Category phone = categoryRepository.findByName("Điện thoại");
        // Category pc = categoryRepository.findByName("Máy tính");
        // Category fashion = categoryRepository.findByName("Thời trang");
        // Category fashionAccessories = categoryRepository.findByName("Phụ kiện thời
        // trang");
        // Category electricAppliances = categoryRepository.findByName("Điện gia dụng");
        // Category healthBeauty = categoryRepository.findByName("Sức khỏe, làm đẹp");

        // Seller seller1 = sellerRepository.findByEmail("asdf@asdf.asdf").get();
        // Seller vietSeller = sellerRepository.findByEmail("zxc@zxc.zxc").get();

        // productRepository.saveAll(List.of(new Product(
        // "Đồng Hồ Thông Minh Apple Watch Series 5 GPS Only Aluminum Case With Sport
        // Band",
        // (long) 11990000,
        // "Apple Watch Series 5 mang lại cho bạn nhiều sự lựa chọn, không chỉ lựa chọn
        // kích thước, dây đeo, mà nay mang đến vô số màu sắc và chất liệu mặt đồng hồ.
        // Phù hợp với mọi nhu cầu của bạn, phù hợp với tất cả mọi người. ECG trên Apple
        // Watch Series 5 giúp bạn luôn theo dõi nhịp tim, theo dõi tình trạng sức
        // khỏe.",
        // 100, "/img/product/407c703670c083b0505b614ec81c94ee.jpg", smartDigital,
        // seller1,
        // new ReviewStar(), new Date(), new Date()),

        // new Product("Vòng đeo tay thông minh Mi Band 4", (long) 850000,
        // "Màn hình màu lớn hơn, thao tác cảm ứng tự nhiên hơn. Mi Band 4 được trang bị
        // kính cường lực 2.5D giúp bạn dễ dàng thao tác với màn hình cảm ứng từ phía
        // viền hai bên. Thêm vào đó, màn hình màu AMOLED lớn hơn 39.9% so với thế hệ
        // trước giúp Xiaomi Mi Band 4 hiển thị được nhiều thông tin hơn, như phần trăm
        // pin, thông tin luyện tập, hay thông báo từ ứng dụng, cuộc gọi...",
        // 200, "/img/product/mi-band-4-6-600x600.jpg", smartDigital,
        // seller1, new ReviewStar(), new Date(), new Date()),

        // new Product("Ổ cắm hẹn giờ AL06", (long) 265000,
        // "Ổ cắm hẹn giờ tự động AL-06 là thiết bị điện tử giúp kiểm soát việc bật tắt
        // dòng điện chạy qua thiết bị thứ 2 theo lịch trình, giúp bảo vệ thiết bị điện
        // chạy trên nó một cách an toàn và hiệu quả. Al-06 được ứng dụng và sử dụng vào
        // thực tế ở hầu hết các mô hình nhà thông minh hiện đại, điểm đặc biệt của sản
        // phẩm đó là tính thực tiễn cao giúp người tiêu dùng có thể mua và sử dụng
        // thiết bị ở bất cứ vị trí nào mình mong muốn. Tính năng chính của sản phẩm
        // cũng chính là tên gọi của nó, điểm hay và đặc biệt nhất của thiết bị này đó
        // là khả năng hoạt động bền bỉ và có độ bền cao cùng với tính năng bật tắt hẹn
        // giờ lên lịch trình theo tuần. Nếu chẳng may nhà bạn có bị mất điện khi có
        // điện trở lại tất cả các thông số đã cài đặt trước trên ổ cắm vẫn được lưu và
        // tiếp tục hoạt động bình thường.",
        // 34, "/img/product/twe052-600x600.jpg", smartDigital, seller1,
        // new ReviewStar(), new Date(), new Date()),

        // new Product("Loa thông minh Google Nest Mini (Thế hệ 2)", (long) 1100000,
        // "Khả năng nhận diện giọng nói Tiếng Việt của Google Nest Mini cũng tốt hơn
        // rất nhiều so với Google Home Mini nhờ được trang bị hệ thống 3 micro (Google
        // Home Mini chỉ có 2 micro). Bên cạnh đó, thiết bị còn được tích hợp sẵn một
        // chip AI chuyên dụng để tương tác với người dùng tốt hơn. Về mặt thiết kế,
        // thay đổi đáng chú ý nhất của Google Nest Mini là cổng cấp nguồn đã được thiết
        // kế dạng hình tròn, trong khi đó phiên bản cũ có cổng cấp nguồn dạng dẹp. Cũng
        // giống như phiên bản cũ, loa Google Nest Mini sẽ giúp bạn thỏa sức giải trí,
        // nghe nhạc, hỏi về các thông tin ở mọi lĩnh vực hoặc ra lệnh để điều khiển các
        // thiết bị trong ngôi nhà thông minh. Loa thông minh Google Nest Mini có 4 màu
        // sắc để bạn lựa chọn. Sản phẩm được thiết kế rất đẹp và sang trọng, mặt trên
        // được tích hợp 4 đèn Led hiển thị trạng thái hoạt động và âm lượng.",
        // 34,
        // "/img/product/loa-thong-minh-google-nest-mini-v2-abaro1-350x350.jpg",
        // smartDigital, seller1, new ReviewStar(), new Date(),
        // new Date()),

        // new Product("Vòng đeo tay thông minh Mi Band 3", (long) 550000,
        // "Màn hình màu lớn hơn, thao tác cảm ứng tự nhiên hơn. Mi Band 3 được trang bị
        // kính cường lực 2.5D giúp bạn dễ dàng thao tác với màn hình cảm ứng từ phía
        // viền hai bên. Thêm vào đó, màn hình màu AMOLED lớn hơn 39.9% so với thế hệ
        // trước giúp Xiaomi Mi Band 3 hiển thị được nhiều thông tin hơn, như phần trăm
        // pin, thông tin luyện tập, hay thông báo từ ứng dụng, cuộc gọi...",
        // 20, "/img/product/mi-band-3-nt-600x600.jpg", smartDigital,
        // seller1, new ReviewStar(), new Date(), new Date()),

        // new Product("Bố Già (Mario Puzo)", (long) 66000,
        // "Thế giới ngầm được phản ánh trong tiểu thuyết Bố Già là sự gặp gỡ giữa một
        // bên là ý chí cương cường và nền tảng gia tộc chặt chẽ theo truyền thống mafia
        // xứ Sicily với một bên là xã hội Mỹ nhập nhằng đen trắng, mảnh đất màu mỡ cho
        // những cơ hội làm ăn bất chính hứa hẹn những món lợi kếch xù. Trong thế giới
        // ấy, hình tượng Bố Già được tác giả dày công khắc họa đã trở thành bức chân
        // dung bất hủ trong lòng người đọc. Từ một kẻ nhập cư tay trắng đến ông trùm
        // tột đỉnh quyền uy, Don Vito Corleone là con rắn hổ mang thâm trầm, nguy hiểm
        // khiến kẻ thù phải kiềng nể, e dè, nhưng cũng được bạn bè, thân quyến xem như
        // một đấng toàn năng đầy nghĩa khí. Nhân vật trung tâm ấy đồng thời cũng là
        // hiện thân của một pho triết lí rất “đời” được nhào nặn từ vốn sống của hàng
        // chục năm lăn lộn giữa chốn giang hồ bao phen vào sinh ra tử, vì thế mà có ý
        // kiến cho rằng “Bố Già là sự tổng hòa của mọi hiểu biết. Bố Già là đáp án cho
        // mọi câu hỏi”.",
        // 100, "/img/product/7eca66c10e5f99f14325733bf53aa425.jpg", book,
        // seller1, new ReviewStar(), new Date(), new Date()),

        // new Product("Đất Máu Sicily (Mario Puzo)", (long) 72000,
        // "Sicily ngập tràn ánh mặt trời Địa Trung Hải, thoang thoảng hương cam chanh
        // là xứ sở của núi non đẹp như tranh rải rác những phế tích lâu đời. Ở đó tầng
        // lớp dân nghèo suốt nhiều thế kỉ phải chịu sự đè nén của nhiều thế lực tham
        // lam và hà khắc, từ ngoại xâm đến phát-xít, từ các phe phái chính trị đến giới
        // mafia và quý tộc địa phương. Ở đó những huyền thoại anh hùng xa xưa được
        // truyền tụng như chỉ là mơ ước. Cho đến một ngày, xứ sở tươi đẹp và bạo tàn ấy
        // đã sản sinh ra một nhân vật được xưng tụng là anh hùng của dân nghèo, là
        // Robin Hood của nước Ý…",
        // 100, "/img/product/dat mau - bia 1.u335.d20160701.t101955.jpg",
        // book, seller1, new ReviewStar(), new Date(), new Date()),

        // new Product("Không Gia Đình (Tái Bản)", (long) 65000,
        // "Không Gia Đình là tiểu thuyết nổi tiếng nhất trong sự nghiệp văn chương của
        // Hector Malot. Hơn một trăm năm nay, tác phẩm giành giải thưởng của Viện Hàn
        // Lâm Văn học Pháp này đã trở thành người bạn thân thiết của thiếu nhi và tất
        // cả những người yêu mến trẻ khắp thế giới.\nKhông Gia Đình kể về chuyện đời
        // Rémi, một cậu bé không cha mẹ, họ hàng thân thích. Sau khi phải rời khỏi vòng
        // tay của người má nuôi, em đã đi theo đoàn xiếc thú của cụ già Vitalis tốt
        // bụng. Kể từ đó, em lưu lạc khắp nơi, ban đầu dưới sự che chở của cụ Vitalis,
        // sau đó thì tự lập và còn lo cả công việc biểu diễn và sinh sống cho cả một
        // gánh hát rong. Đã có lúc em và cả đoàn lang thang cả mấy ngày đói khát rồi
        // còn suýt chết rét. Có bận em bị lụt ngầm chôn trong giếng mỏ hàng tuần. Rồi
        // có lần em còn mắc oan bị giải ra tòa và phải ở tù. Nhưng cũng có khi em được
        // nuôi nấng đàng hoàng, no ấm. Song dù trong hoàn cảnh nào, Rémi vẫn giữ được
        // sự gan dạ, ngay thẳng, lòng tự trọng, tính thương người, ham lao động chứ
        // không hạ mình hay gian dối. Cuối cùng, sau bao gian nan khổ cực, em đã đoàn
        // tụ được với gia đình của mình.\nTác phẩm đã ca ngợi sự lao động bền bỉ, tinh
        // thần tự lập, chịu đựng gian khó, khích lệ tình bạn chân chính. Ca ngợi lòng
        // nhân ái, tình yêu cuộc sống, ý chí vươn lên không ngừng…Không Gia Đình vì thế
        // đã vượt qua biên giới nước Pháp và tồn tại lâu dài với thời gian.",
        // 100,
        // "/img/product/khong-gia-dinh---pc-bia-mem.u5552.d20171101.t141222.773750.jpg",
        // book, seller1, new ReviewStar(), new Date(), new Date()),

        // new Product("Những Người Khốn Khổ (Trọn Bộ 5 Tập)", (long) 240000,
        // "Khi pháp luật và phong hóa còn đày đọa con người, còn dựng nên những địa
        // ngục giữa xã hội văn minh và đem một thứ định mệnh nhân tạo chồng thêm lên
        // thiên mệnh; khi ba vấn đề lớn của thời đại là sự sa đọa của đàn ông vì bán
        // sức lao động, sự trụy lạc của đàn bà vì đói khát, sự cằn cỗi của trẻ nhỏ vì
        // tối tăm, chưa được giải quyết; khi ở một số nơi đời sống còn ngạt thở; nói
        // khác đi, và trên quan điểm rộng hơn, khi trên mặt đất, dốt nát và đói khổ còn
        // tồn tại, thì những cuốn sách như loại này còn có thể có ích.",
        // 100, "/img/product/433cf186367f485d62bd4d80379fc9fd.jpg", book,
        // seller1, new ReviewStar(), new Date(), new Date()),

        // new Product("Hai Số Phận (Bìa Cứng)", (long) 440000,
        // "“Hai số phận” không chỉ đơn thuần là một cuốn tiểu thuyết, đây có thể xem là
        // \"thánh kinh\" cho những người đọc và suy ngẫm, những ai không dễ dãi, không
        // chấp nhận lối mòn.\n“Hai số phận” làm rung động mọi trái tim quả cảm, nó có
        // thể làm thay đổi cả cuộc đời bạn. Đọc cuốn sách này, bạn sẽ bị chi phối bởi
        // cá tính của hai nhân vật chính, hoặc bạn là Kane, hoặc sẽ là Abel, không thể
        // nào nhầm lẫn. Và điều đó sẽ khiến bạn thấy được chính mình.",
        // 100, "/img/product/202015881b04b7c8a4b47d15cf9c049f.jpg", book,
        // seller1, new ReviewStar(), new Date(), new Date()),

        // new Product("Tuổi Thơ Dữ Dội (Bản Mới 2013)", (long) 85000,
        // "Nhắc đến Phùng Quán, người ta sẽ nhớ ngay tới một cây bút lạ kỳ trong nền
        // văn học Việt Nam với một tác phẩm thiếu nhi vô cùng chân thực và xúc động về
        // một thế hệ trẻ anh hùng: Tuổi thơ dữ dội - cuốn truyện xoay quanh cuộc sống
        // chiến đấu và hy sinh của những thiếu niên 13, 14 tuổi trong hàng ngũ Đội
        // thiếu niên trinh sát của trung đoàn Trần Cao Vân. Những Lượm, Mừng, Quỳnh sơn
        // ca, Hòa đen, Bồng da rắn, Vịnh sưa, Tư dát... mỗi người một hoàn cảnh song
        // đều chung quyết tâm, nhiệt huyết và lòng yêu nước, đã tham gia chiến đấu hết
        // mình và hy sinh khi tuổi đời còn rất trẻ.",
        // 100, "/img/product/tuoi-tho-du-doi-a.jpg", book, seller1,
        // new ReviewStar(), new Date(), new Date()),

        // new Product("Hộp Sách: Tây Du Ký (Tái Bản 2018)", (long) 260000,
        // "Tây du ký là đỉnh cao sáng tạo trong lịch sử phát triển về tiểu thuyết cổ
        // điển của Trung Quốc). Chủ nghĩa lãng mạn tích cực là đặc sắc nghệ thuật chủ
        // yếu của nó. Nó đã kế thừa và phát huy truyền thống tốt đẹp của thần thoại cổ
        // đại Trung Quốc, đã thể hiện sức sáng tạo vĩ đại và sức tưởng tượng phong phú
        // của dân tộc Trung Hoa. \"Chủ nghĩa lãng mạn là cơ sở của thần thoại\" (lời
        // của M.Goocki) Tây du ký chính là một bộ tiểu thuyết thần thoại trường thiên
        // vĩ đại, lãng mạn tích cực. Thông qua hình thức ảo tưởng thần kỳ và nội dung
        // bắt nguồn ở hiện thực mà cao hơn hiện thực. Tây du ký đã phản ánh đầy đủ,
        // khúc chiết, phức tạp lý tưởng cao quý và đời sống hiện thực của nhân dân.",
        // 100, "/img/product/5432cf841314cf28c9d5cde258955cb5.jpg", book,
        // seller1, new ReviewStar(), new Date(), new Date()),

        // new Product("chienbinhcauvong", (long) 65000,
        // "Một tác phẩm có tầm ảnh hưởng sâu rộng nhất Indonesia. “Thầy Harfan và cô
        // Mus nghèo khổ đã mang đến cho tôi tuổi thơ đẹp nhất, tình bạn đẹp nhất, và
        // tâm hồn phong phú, một thứ gì đó vô giá, thậm chí còn có giá trị hơn những
        // khao khát mơ ước. Có thể tôi lầm, nhưng theo ý tôi, đây thật sự là hơi thở
        // của giáo dục và linh hồn của một chốn được gọi là trường học.” - (Trích tác
        // phẩm)",
        // 100,
        // "/img/product/chien_binh_cau_vong_sap_xuat_ban-240412_2.jpg",
        // book, seller1, new ReviewStar(), new Date(), new Date()),

        // new Product("Điện thoại Xiaomi Redmi Note 8 (4GB/64GB)", (long) 4390000,
        // "Một tác phẩm có tầm ảnh hưởng sâu rộng nhất Indonesia. “Thầy Harfan và cô
        // Mus nghèo khổ đã mang đến cho tôi tuổi thơ đẹp nhất, tình bạn đẹp nhất, và
        // tâm hồn phong phú, một thứ gì đó vô giá, thậm chí còn có giá trị hơn những
        // khao khát mơ ước. Có thể tôi lầm, nhưng theo ý tôi, đây thật sự là hơi thở
        // của giáo dục và linh hồn của một chốn được gọi là trường học.” - (Trích tác
        // phẩm)",
        // 100, "/img/product/202004160915267461.jpg", phone, seller1,
        // new ReviewStar(), new Date(), new Date()),

        // new Product("Apple iPhone SE - 64GB - 2020 Chính hãng VN/A", (long) 10990000,
        // "Apple hiện là thương hiệu dẫn đầu công nghệ trên thị trường hiện nay. Bên
        // cạnh những sản phẩm như Macbook hay máy tính bảng iPad, iPhone cũng giành vị
        // trí số một về thị phần smartphone tại nhiều khu vực trên thế giới. Những
        // chiếc iPhone của Apple luôn gây ấn tượng bởi thiết kế thời thượng cùng rất
        // nhiều hiệu năng đỉnh cao. Giữa tháng 4/2020, Apple tiếp tục tung ra thị
        // trường một dòng sản phẩm iPhone SE 2020 mới. Đây cũng là sản phẩm đánh dấu sự
        // trở lại của Apple trên thị trường điện thoại thông minh năm nay trước khi
        // loạt iPhone 12 ra mắt vào mùa thu. Chiếc iPhone SE 2020 64GB chính hãng có
        // thiết kế nhỏ gọn, mang những đặc điểm của những chiếc iPhone đời đầu. Vậy
        // thì, Apple đã bổ sung những tính năng đặc biệt gì để iPhone SE 2020 64GB có
        // thể cạnh tranh trên thị trường?",
        // 19, "/img/product/201910021048421691.jpg", phone, seller1,
        // new ReviewStar(), new Date(), new Date()),

        // new Product("Samsung Galaxy Note 10 Plus - Chính hãng", (long) 2290000,
        // "Samsung Galaxy Note 10 Plus sẽ sở hữu thiết kế tương tự như người tiền nhiệm
        // – Samsung Note 9 với viền mỏng hơn và khối lượng nhẹ hơn. Vẫn với màn hình
        // cong tràn hai bên quen thuộc trên các thế hệ Galaxy Note. Bên cạnh đó thiết
        // kế mặt trước được trang bị màn hình 6.75 inch, độ phân giải 1440 x 3040 picel
        // và được hỗ trợ HDR10 +. Đặc biệt hơn cả là thiết kế đục lỗ, nhưng vị trí sẽ
        // được đặt chính giữa thay vì bên phải như Samsung S10. Ngoài ra, trên Galaxy
        // Note 10 Plus cũng chỉ có 1 camera self để thiết kế tinh tế, hiện đại hơn.",
        // 19, "/img/product/samsung-galaxy-note-10-silver-400x460.png",
        // phone, seller1, new ReviewStar(), new Date(), new Date()),

        // new Product("Nokia 7.2 - Chính hãng", (long) 3790000,
        // "Nokia 7.2 còn là một sự cải tiến đáng kể so với các sản phẩm tiền nhiệm với
        // thiết kế cụm 3 camera sau đặt trong một vòng tròn lớn trên mặt lưng được làm
        // bằng kính. Hình ảnh cho thấy bộ 3 camera của Nokia 7.2 khá giống với thiết kế
        // của điện thoại Nokia Lumia trước đây với 3 camera kết hợp 1 đèn flash. Máy
        // cũng có cảm biến vân tay được đặt ngay bên dưới cụm camera. Theo thông tin
        // được Nokia tiết lộ, Nokia 7.2 chính hãng sẽ có camera chính 48MP sử dụng ống
        // kính Zeiss kèm với 1 camera góc rộng, 1 camera xóa phông. Camera góc rộng với
        // góc chụp 120 độ cho những bức ảnh phong cảnh khá rộng.",
        // 19, "/img/product/nokia-72-black-400x460.png", phone, seller1,
        // new ReviewStar(), new Date(), new Date()),

        // new Product("Vsmart Bee - Chính Hãng", (long) 990000,
        // "Vsmart là thương hiệu “điện thoại quốc dân” đã gây tiếng vang lớn ngay từ
        // những ngày đầu ra mắt thị trường. Trong nhiều năm qua, Vsmart dần xây dựng
        // được niềm tin và uy tín với người dùng thị trường trong nước. Hoàng Hà Mobile
        // tự hào là một trong những nhà phân phối sản phẩm điện thoại Vsmart chính hãng
        // với nhiều ưu đãi hấp dẫn và mức giá tốt nhất thị trường. Cuối năm 2019,
        // Vsmart đã tung ra chiếc điện thoại đầu tiên trong dòng sản phẩm thế hệ thứ ba
        // của công ty. Vsmart Bee 3 là “người kế nhiệm” của chiếc điện thoại Vsmart Bee
        // đã ra mắt trước đó. Đây có thể cũng là sản phẩm đánh dấu bước tiến tiếp theo
        // của công ty trong tương lai. Chiếc điện thoại Vsmart Bee 3 đã có những nâng
        // cấp trong trong cấu hình, thời lượng pin,… so với người tiền nhiệm của mình.
        // Vậy thì, với mức giá chưa tới 2 triệu đồng, Vsmart Bee 3 có thể cung cấp
        // những gì cho người dùng?",
        // 19, "/img/product/vsmart-bee-blue-400x460.png", phone, seller1,
        // new ReviewStar(), new Date(), new Date()),

        // new Product("Realme 5 Pro - R4GB/128GB - Chính hãng", (long) 4490000,
        // "Về thiêt kế, Realme 5 Pro không có nhiều điểm khác biệt so với Realme 5. Vẫn
        // là những nâng cấp hiện đại hơn so với phiên bản tiền nhiệm. Mặt lưng có độ
        // mỏng vừa phải mang đến cảm giác êm ái khi cầm trên tay. Đặc biệt Realme đã
        // tạo cho sản phẩm hiệu ứng Gradient với 2 màu xanh và tím. Đây là 2 tone màu
        // khá độc đáo, đẹp mắt giúp người dùng có thêm nhiều sự lựa chọn. Thiết kế có
        // nhiều sự đột phá khả năng chống trầy xước càng làm tăng thêm tính thẩm mỹ cho
        // sản phẩm. Màn hình công nghệ IPS LCD, kích thước 6.3 inch mang đến trải
        // nghiệm tốt hơn cho người dùng. Với tỉ lệ 19:5:9, Realme 5 Pro có khung hình
        // tràn viền giúp xem phim, lướt web thoải mái hơn. Chiếc điện thoại này sở hữu
        // độ phân giải Full HD+ (1080 x 2340 pixel). Nhờ đó, bạn sẽ nhìn thấy những
        // hình ảnh rõ nét, màu sắc sinh động, tương phản tốt. Máy còn có mặt kính cảm
        // ứng Gorilla Glass 3 với khả năng chống bám bụi, chống trày xước. Đồng thời nó
        // cũng giúp nhìn rõ hơn dưới ánh nắng mặt trời",
        // 55, "/img/product/realme-5-pro-8gb-purple-400x460.png", phone,
        // seller1, new ReviewStar(), new Date(), new Date()),

        // new Product("Vsmart Joy 3 - 2GB/32GB - Chính Hãng", (long) 2290000,
        // "Vsmart Joy 3 vẫn sở hữu màn hình giọt nước quen thuộc. Thế nhưng với thiết
        // kế tràn viền, các thao tác của bạn không còn bị giới hạn bởi các cạnh nữa.
        // Viền màn hình được chế tác cong 2.5D tạo cảm giác liền mạch, dễ thao tác. Bên
        // cạnh đó, mặt lưng của máy cũng có các góc bo tròn mềm mại, mang tính thẩm mỹ
        // cao. Hiệu ứng chuyển màu độc đáo là một điểm hấp dẫn của sản phẩm này. Với
        // kích thước siêu khủng lên tới 6.5 inch, màn hình Vsmart Joy 3 mang đến cho
        // bạn những hình ảnh vô cùng sống động, chân thực. Mặc dù có màn hình lớn nhưng
        // kích thước của sản phẩm này lại khá nhỏ gọn. Tấm nền IPS LCD cùng độ phân
        // giải HD + sẽ giúp tăng độ sáng và độ tương phản khi sử dụng. Bạn chắc chắn sẽ
        // có những giây phút giải trí tuyệt vời khi trải nghiệm Vsmart Joy 3.",
        // 55, "/img/product/vsmart-joy-3-tim-400x460-400x460.png", phone,
        // seller1, new ReviewStar(), new Date(), new Date()),

        // new Product("Samsung Galaxy A71 - Chính hãng", (long) 8250000,
        // "Galaxy A71 là mẫu điện thoại Samsung sở hữu thiết kế dẫn đầu về xu hướng với
        // camera macro đột phá cùng mặt lưng cắt kim cương. Không những vậy, mẫu
        // smartphone Galaxy A 2020 thế hệ mới này còn được trang bị màn hình tràn viền
        // vô cực đem lại thiết kế vô cùng thời thượng và cao cấp. Giờ đây, các SamFan
        // đã được trải nghiệm màn hình lớn Infinity-O tràn viền vô cực tương tự như của
        // Note 10 nhưng với giá mềm hơn. Cụ thể, phần camera selfie của Galaxy A71 được
        // đặt trong một Notch hình tròn khá nhỏ ở chính giữa màn hình cung cấp trải
        // nghiệm tốt nhất. Kèm với đó, máy được trang bị một màn hình Super AMOLED kích
        // thước lên đến 6,7inch cung cấp độ phân giải Full HD+ có độ tương phản tốt,
        // màu sắc rực rỡ, nịnh mắt.",
        // 55,
        // "/img/product/samsung-galaxy-a71-blue-400x460-400x460-1-400x460.png",
        // phone, seller1, new ReviewStar(), new Date(), new Date()),

        // new Product("Điện thoại iPhone 8 Plus 64GB", (long) 14490000,
        // "Thừa hưởng những thiết kế đã đạt đến độ chuẩn mực, thế hệ iPhone 8 Plus thay
        // đổi phong cách bóng bẩy hơn và bổ sung hàng loạt tính năng cao cấp cho trải
        // nghiệm sử dụng vô cùng tuyệt vời.",
        // 55, "/img/product/iphone-8-plus-1-400x460.png", phone, seller1,
        // new ReviewStar(), new Date(), new Date()),

        // new Product("Điện thoại OPPO A9 (2020)", (long) 5990000,
        // "Kế thừa phiên bản OPPO A7 đã từng gây hot trước đó, OPPO A9 (2020) có nhiều
        // sự cải tiến hơn về màn hình, camera và hiệu năng trải nghiệm. Cấu hình mạnh
        // mẽ với chip mới Snapdragon 665, RAM 8 GB. Cung cấp sức mạnh cho chiếc
        // smartphone là con chip tầm trung mới Snapdragon 665. So với phiên bản
        // Snapdragon 660 trước đó, phiên bản chip này mang đến hiệu năng mạnh mẽ và tối
        // ưu tiết kiệm điện hơn.",
        // 55, "/img/product/oppo-a9-400x460-1-400x460.png", phone,
        // seller1, new ReviewStar(), new Date(), new Date()),

        // new Product("Điện thoại iPhone 11 Pro Max 256GB", (long) 37990000,
        // "Camera là một trong những điểm nâng cấp mạnh mẽ nhất năm nay Apple mang đến
        // cho chiếc iPhone 11 Pro Max.Camera là một trong những điểm nâng cấp mạnh mẽ
        // nhất năm nay Apple mang đến cho chiếc iPhone 11 Pro Max.Camera là một trong
        // những điểm nâng cấp mạnh mẽ nhất năm nay Apple mang đến cho chiếc iPhone 11
        // Pro Max.Camera là một trong những điểm nâng cấp mạnh mẽ nhất năm nay Apple
        // mang đến cho chiếc iPhone 11 Pro Max.",
        // 55, "/img/product/iphone-11-pro-max-256gb-black-400x460.png",
        // phone, seller1, new ReviewStar(), new Date(), new Date()),

        // new Product("Điện thoại Samsung Galaxy S20 Ultra", (long) 27490000,
        // "Samsung Galaxy S20 Ultra siêu phẩm công nghệ hàng đầu của Samsung mới ra mắt
        // với nhiều đột phá công nghệ, màn hình tràn viền không khuyết điểm, hiệu năng
        // đỉnh cao, camera độ phân giải siêu khủng 108 MP cùng khả năng zoom 100X thách
        // thức mọi giới hạn smartphone.",
        // 55,
        // "/img/product/samsung-galaxy-s20-ultra-400x460-1-400x460.png",
        // phone, seller1, new ReviewStar(), new Date(), new Date()),

        // new Product("Điện thoại huawei-p40-pro", (long) 23990000,
        // "siêu phẩm công nghệ hàng đầu mới ra mắt với nhiều đột phá công nghệ, màn
        // hình tràn viền không khuyết điểm, hiệu năng đỉnh cao, camera độ phân giải
        // siêu khủng 108 MP cùng khả năng zoom 100X thách thức mọi giới hạn
        // smartphone.",
        // 55, "/img/product/product-435x408-2.png", phone, seller1,
        // new ReviewStar(), new Date(), new Date()),

        // new Product("Điện thoại OPPO Find X2", (long) 23990000,
        // "Tiếp nối thành công vang dội của thế hệ Find X, OPPO chính thức ra mắt Find
        // X2 với những đường nét thanh lịch từ thiết kế phần cứng cho đến trải nghiệm
        // phần mềm, hứa hẹn một vẻ đẹp hoàn hảo, một sức mạnh xứng tầm. Trải nghiệm thị
        // giác tuyệt vời Find X2 sở hữu màn hình AMOLED Ultra Vision cao cấp với kích
        // thước lên đến 6.7 inch cùng độ phân giải 2K+ cực nét được bảo vệ bằng kính
        // cường lực Corning Gorilla Glass 6 cao cấp.",
        // 55, "/img/product/oppo-find-x2-xanh-400x460-1-400x460.png",
        // phone, seller1, new ReviewStar(), new Date(), new Date()),

        // new Product("Điện thoại Samsung Galaxy S10+", (long) 19990000,
        // "Samsung Galaxy S10+ 128GB là một trong những chiếc smartphone được trông chờ
        // nhiều nhất trong năm 2019 và không phụ sự kỳ vọng của mọi người thì chiếc
        // Galaxy S thứ 10 của Samsung thực sự gây ấn tượng mạnh cho người dùng.",
        // 55, "/img/product/samsung-galaxy-s10-plus-white-400x460.png",
        // phone, seller1, new ReviewStar(), new Date(), new Date()),

        // new Product("Điện thoại Samsung Galaxy Z Flip", (long) 36000000,
        // "Cuối cùng sau bao nhiêu thời gian chờ đợi, chiếc điện thoại Samsung Galaxy Z
        // Flip đã được Samsung ra mắt tại sự kiện Unpacked 2020. Siêu phẩm với thiết kế
        // màn hình gập vỏ sò độc đáo, hiệu năng tuyệt đỉnh cùng nhiều công nghệ thời
        // thượng, dẫn dầu 2020.",
        // 55, "/img/product/samsung-galaxy-z-flip-chitiet-2-788x544.png",
        // phone, seller1, new ReviewStar(), new Date(), new Date())

        // ));
        // };
        // }

}
