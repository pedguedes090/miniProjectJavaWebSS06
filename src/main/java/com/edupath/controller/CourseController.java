package com.edupath.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CourseController {
	@GetMapping({"/", "/home"})
	public String home() {
		return "redirect:/course/list";
	}

	@GetMapping("/course/list")
	public String showCourseList() {
		return "course-list";
	}

	@GetMapping("/course/detail")
	public String showCourseDetail() {
		return "course-detail";
	}

	@GetMapping("/course/form")
	public String showCourseForm() {
		return "course-form";
	}
}
