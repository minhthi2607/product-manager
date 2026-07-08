# Product Manager Application

Ứng dụng quản lý sản phẩm được xây dựng bằng Spring Boot và Thymeleaf.

## 🚀 Tính năng chính

- **Quản lý sản phẩm**: Xem danh sách, thêm mới, chỉnh sửa và xóa sản phẩm.
- **Quản lý danh mục**: Liên kết sản phẩm với các danh mục tương ứng.
- **Tìm kiếm & Lọc**: Tìm kiếm sản phẩm theo tên, giá và danh mục.
- **Phân trang**: Hiển thị danh sách sản phẩm với tính năng phân trang.
- **Xóa hàng loạt**: Cho phép chọn và xóa nhiều sản phẩm cùng một lúc.
- **Khởi tạo dữ liệu**: Tự động tạo dữ liệu mẫu khi khởi động ứng dụng lần đầu.

## 🛠 Công nghệ sử dụng

- **Backend**: Java 17+, Spring Boot 3.x
- **Dữ liệu**: Spring Data JPA, Hibernate
- **Cơ sở dữ liệu**: MySQL
- **Frontend**: Thymeleaf, HTML/CSS (Layout cơ bản)
- **Công cụ xây dựng**: Gradle

## 📋 Yêu cầu hệ thống

- Java JDK 17 hoặc mới hơn.
- MySQL Server.
- Gradle (đi kèm trong dự án qua Gradle Wrapper).

## ⚙️ Cấu hình cơ sở dữ liệu

Trước khi chạy ứng dụng, hãy đảm bảo cấu hình đúng thông tin kết nối MySQL trong file `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/product_manager_db?createDatabaseIfNotExist=true
spring.datasource.username=root
spring.datasource.password=your_password_here
```

*Lưu ý: Thay đổi `your_password_here` thành mật khẩu MySQL của bạn.*

## 🏃 Cách chạy ứng dụng

1. Mở terminal tại thư mục gốc của dự án.
2. Chạy lệnh sau để build và chạy ứng dụng:

```bash
./gradlew bootRun
```

3. Sau khi ứng dụng khởi động thành công, truy cập vào trình duyệt:
   - URL: `http://localhost:8080` hoặc `http://localhost:8080/product`

## 📂 Cấu trúc dự án chính

- `src/main/java/com/code/productmanager/controller`: Xử lý các request từ người dùng.
- `src/main/java/com/code/productmanager/model`: Các thực thể (Entity) Product và Category.
- `src/main/java/com/code/productmanager/repository`: Giao tiếp với cơ sở dữ liệu.
- `src/main/java/com/code/productmanager/service`: Logic xử lý nghiệp vụ.
- `src/main/resources/templates`: Giao diện người dùng (Thymeleaf).
- `src/main/resources/application.properties`: Cấu hình hệ thống.
