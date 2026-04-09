package com.catalog.repository;

import com.catalog.model.Course;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class CourseRepository {
    private List<Course> courseList = new ArrayList<>();

    public CourseRepository() {
        courseList.add(new Course("ENG-BEG-01", "Tiếng Anh Giao Tiếp Cơ Bản", "Beginner", 3500000.0, "Nền tảng phát âm và giao tiếp hàng ngày.", "Mr. John", 8, false, 15, "2026-05-15"));
        courseList.add(new Course("IELTS-INT-65", "IELTS 6.5 Nền Tảng", "Intermediate", 6000000.0, "Xây dựng 4 kỹ năng IELTS cơ bản.", "Ms. Sarah", 12, true, 25, "2026-05-20"));
        courseList.add(new Course("IELTS-ADV-75", "IELTS 7.5 Intensive", "Advanced", 8500000.0, "Luyện đề chuyên sâu và chiến thuật phòng thi.", "Mr. David", 10, false, 10, "2026-06-01"));
        courseList.add(new Course("TOEIC-BEG-500", "TOEIC 500+", "Beginner", 2500000.0, "Lấy lại căn bản ngữ pháp và từ vựng.", "Ms. Lan", 6, false, 20, "2026-06-10"));
        courseList.add(new Course("BUS-INT-01", "Tiếng Anh Thương Mại", "Intermediate", 5500000.0, "Viết email, thuyết trình và đàm phán.", "Mr. Michael", 10, false, 12, "2026-06-15"));
        courseList.add(new Course("JAVA-BEG-01", "Java Web Cơ Bản", "Beginner", 4200000.0, "Làm quen với Java Web, Servlet và MVC.", "Ms. Hạnh", 8, false, 0, "2026-07-01"));
        courseList.add(new Course("SPRING-INT-01", "Spring MVC Thực Hành", "Intermediate", 6800000.0, "Xây dựng ứng dụng web với Spring MVC và Thymeleaf.", "Mr. Tuấn", 10, false, 0, "2026-07-15"));
    }

    public List<Course> filterCourses(String level, Double maxFee) {
        return courseList.stream()
                // Lọc theo Level (Nếu tham số rỗng -> lấy tất cả)
                .filter(course -> level == null || level.isEmpty() || course.getLevel().equalsIgnoreCase(level))
                // Lọc theo Học phí (Nếu maxFee null -> không giới hạn học phí)
                .filter(course -> maxFee == null || course.getTuitionFee() <= maxFee)
                .collect(Collectors.toList());
    }

    public Course getCourseById(String id) {
        return courseList.stream()
                .filter(course -> course.getCourseCode().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void updateCourse(Course updatedCourse) {
        for (int i = 0; i < courseList.size(); i++) {
            if (courseList.get(i).getCourseCode().equals(updatedCourse.getCourseCode())) {
                Course existingCourse = courseList.get(i);
                existingCourse.setTuitionFee(updatedCourse.getTuitionFee());
                existingCourse.setStartDate(updatedCourse.getStartDate());
                break;
            }
        }
    }

    public void deleteCourse(String id) {
        courseList.removeIf(course -> course.getCourseCode().equals(id));
    }
}