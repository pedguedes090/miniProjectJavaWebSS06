SRS CHI TIẾT MINI PROJECT: HỆ THỐNG QUẢN LÝ KHÓA HỌC "EDU-PATH"
1. Mục tiêu dự án
   Xây dựng phân hệ quản lý thông tin các khóa học tiếng Anh, cho phép nhân viên học vụ thực hiện tra cứu, xem chi tiết và cập nhật trạng thái lớp học.
2. Đặc tả chức năng chi tiết (Core Features)
   Chức năng 1: Thiết lập Hệ thống Layout (Thymeleaf Layout Dialect)
   File gốc: layout/main-layout.html.
   Cấu trúc:
   Header: Chứa Logo trung tâm và Slogan.
   Navbar: Các nút: Danh sách khóa học, Đăng ký tư vấn.
   Content: Vùng layout:fragment="content" để nhúng các trang con.
   Yêu cầu: Toàn bộ các trang (Danh sách, Chi tiết, Form) đều phải kế thừa layout này.
   Chức năng 2: Danh sách & Lọc khóa học (Course Catalog)
   Giao diện: Hiển thị danh sách khóa học dưới dạng lưới (Grid) hoặc Bảng.
   Input (Search): Một thanh tìm kiếm gồm:
   Dropdown chọn Level (Beginner, Intermediate, Advanced).
   Input nhập Học phí tối đa.
   Xử lý Controller: Nhận tham số qua @RequestParam. Nếu không nhập thì lấy giá trị mặc định (default value).
   Output: Danh sách các khóa học thỏa mãn điều kiện.
   Bẫy dữ liệu: Nếu không có khóa học nào, hiển thị: "Hiện chưa có lớp học phù hợp với trình độ này".
   Chức năng 3: Xem chi tiết lộ trình (Course Detail)
   Input: Nhận mã khóa học (Course Code) từ URL động qua @PathVariable.
   Ví dụ: /course/detail/IELTS-6.5.
   Output: Hiển thị: Tên khóa, Lộ trình học (Description), Giảng viên, Thời lượng (Duration).
   Logic hiển thị: Nếu khóa học đã đủ học viên, hiển thị nhãn "HẾT CHỖ" (Dùng th:if với thuộc tính isFull).

   --- Hướng dẫn chức năng: Xem chi tiết lộ trình (thực tế trong mã nguồn) ---
   Endpoint (Controller):
   - GET /course/detail/{id}
     - Controller method: `CourseController.showCourseDetail(@PathVariable("id") String id, Model model)`
     - Hành vi: gọi `CourseService.getCourseById(id)`, nếu tồn tại thì trả về view `course-detail` với model attribute `course`, nếu không tồn tại -> redirect về `/course/list`.

   Template (Thymeleaf):
   - File: `src/main/webapp/layout/course-detail.html`
   - Hiển thị các trường: `course.name`, `course.description`, `course.instructor`, `course.duration`, `course.tuitionFee` (định dạng số bằng `#numbers.formatDecimal`), `course.startDate`, `course.studentCount`.
   - Hiển thị nhãn "HẾT CHỖ" khi `course.full` là true: sử dụng `th:if`.
   - Có link quay lại danh sách: `/course/list`.

   Dữ liệu mẫu (Repository):
   - `CourseRepository` giữ một `List<Course>` tĩnh (mock data). Phương thức `getCourseById` tìm course theo `courseCode`.

   Luồng (Request -> Response):
   1) Người dùng bấm link chi tiết (ví dụ từ `course-list.html`) -> trình duyệt gửi GET /course/detail/{id}.
   2) `CourseController` nhận `id`, gọi `CourseService.getCourseById(id)`.
   3) `CourseService` gọi `CourseRepository.getCourseById(id)` -> trả Course (hoặc null).
   4) Nếu có Course -> Controller thêm `model.addAttribute("course", course)` và trả `course-detail`.
   5) Thymeleaf render `course-detail.html`, hiển thị các thông tin chi tiết; nếu `course.full` true thì hiển thị "HẾT CHỖ".

   Ghi chú kỹ thuật:
   - Không xóa bất kỳ file hoặc dữ liệu mẫu nào.
   - Template `course-detail.html` đã được cập nhật để hiển thị đầy đủ trường theo yêu cầu.

   Chức năng 4: Cập nhật thông tin khóa học (Admin Action)
   Giao diện: Một Form chỉnh sửa (Binding dữ liệu qua th:object).
   Input: Cho phép sửa Học phí và Ngày khai giảng.
   Xử lý Controller: Sử dụng @PostMapping để nhận dữ liệu.
   Logic điều hướng: Sau khi lưu thành công, phải sử dụng redirect:/course/list (PRG Pattern) để tránh việc người dùng F5 gây gửi lại form.
   Chức năng 5: Xóa/Lưu trữ khóa học (Delete Logic)
   Hành động: Nút "Hủy khóa học" ở mỗi dòng trong bảng admin.
   Xử lý: Gửi yêu cầu qua @PostMapping đến /course/delete/{id}.
   Bẫy nghiệp vụ: Chỉ cho phép xóa nếu khóa học có studentCount = 0. Nếu đã có học viên, trả về thông báo lỗi: "Không thể hủy khóa học đã có học viên đăng ký".
3. Đặc tả Luồng dữ liệu (Có thể dùng PlantUML để nhanh)
   Sinh viên vẽ các luồng nghiệp vụ tương ứng để giải thích trên lớp (từ 1 - 5 cái)
   Ví dụ về PlantUML: Tra cứu theo trình độ (Level Filter)
   @startuml
   participant "Học viên (Browser)" as User
   participant "CourseController" as Ctrl
   participant "CourseService" as Svc
   participant "CourseList (Thymeleaf)" as View

User -> View : Chọn Level 'Intermediate'
View -> Ctrl : GET /courses/list?level=Intermediate
Ctrl -> Svc : filterByLevel("Intermediate")
Svc --> Ctrl : List<Course>
Ctrl -> View : model.addAttribute("courses", list)
View --> User : Hiển thị các khóa Intermediate trong Layout
@enduml

4. Yêu cầu kỹ thuật bắt buộc
   Thymeleaf: Sử dụng #numbers.formatDecimal để hiển thị học phí (VD: 5.000.000 VNĐ).
   Controller: Phải sử dụng đúng các Shortcut: @GetMapping, @PostMapping.
   Mock Data: Sinh viên tự khởi tạo một List<Course> tĩnh trong Repository (ít nhất 5 khóa học) để demo.
5. Mở rộng
   Sinh viên có thể mở rộng tìm hiểu thêm về thông báo lỗi cho mỗi hành động redirect, viết css để dễ nhìn, responsive, phân quyền dựa trên một biến trong bộ nhớ, hoặc thông qua query param.
