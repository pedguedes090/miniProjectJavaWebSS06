package com.catalog.service;

import com.catalog.model.Course;
import com.catalog.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public List<Course> getFilteredCourses(String level, Double maxFee) {
        return courseRepository.filterCourses(level, maxFee);
    }

    public Course getCourseById(String id) {
        return courseRepository.getCourseById(id);
    }

    public void updateCourse(Course course) {
        courseRepository.updateCourse(course);
    }

    public void deleteCourse(String id) {
        courseRepository.deleteCourse(id);
    }
}