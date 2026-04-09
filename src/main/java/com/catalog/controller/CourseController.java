package com.catalog.controller;

import com.catalog.model.Course;
import com.catalog.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    // Yêu cầu: Sử dụng @RequestParam, có giá trị mặc định, Shortcut @GetMapping
    @GetMapping("/list")
    public String showCourseList(
            @RequestParam(value = "level", required = false, defaultValue = "") String level,
            @RequestParam(value = "maxFee", required = false) Double maxFee,
            Model model) {

        // Gọi service lấy danh sách đã lọc
        List<Course> courses = courseService.getFilteredCourses(level, maxFee);

        // Truyền dữ liệu xuống View
        model.addAttribute("courses", courses);
        // Truyền lại params để giữ trạng thái cho Form (ví dụ khi user bấm tìm kiếm xong form vẫn giữ lựa chọn cũ)
        model.addAttribute("selectedLevel", level);
        model.addAttribute("selectedFee", maxFee);

        return "course-list";
    }

    @GetMapping("/detail/{id}")
    public String showCourseDetail(@PathVariable("id") String id, Model model) {
        Course course = courseService.getCourseById(id);
        if (course != null) {
            model.addAttribute("course", course);
            return "course-detail";
        }
        return "redirect:/course/list";
    }

    @GetMapping("/register")
    public String showRegisterForm() {
        return "course-form";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") String id, Model model) {
        Course course = courseService.getCourseById(id);
        if (course != null) {
            model.addAttribute("course", course);
            return "course-form";
        }
        return "redirect:/course/list";
    }

    @PostMapping("/update")
    public String updateCourse(@ModelAttribute("course") Course course) {
        courseService.updateCourse(course);
        return "redirect:/course/list";
    }
}