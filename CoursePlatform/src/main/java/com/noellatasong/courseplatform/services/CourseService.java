package com.noellatasong.courseplatform.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.noellatasong.courseplatform.models.Course;
import com.noellatasong.courseplatform.repositories.CourseRepository;


@Service
public class CourseService {
	
	private CourseRepository courseRepository;

	public CourseService(CourseRepository courseRepository) {
		this.courseRepository = courseRepository;
	}
	
	public Course create(Course course) {
		return courseRepository.save(course);
	}
	
	public ArrayList<Course>findAll() {
		return (ArrayList<Course>) courseRepository.findAll();
	}
	
	public Course findById(Long id) {
		Optional<Course> course = courseRepository.findById(id);
		if(course.isPresent()) return course.get();
		return null;
	}
	
	
	public Course update(Course course) {
		return courseRepository.save(course);
	}
	
	public void delete(Course course) {
		courseRepository.delete(course);
		
	}
	
	public void deleteById(Long id) {
		courseRepository.deleteById(id);
		
	}

	
	
	

}
