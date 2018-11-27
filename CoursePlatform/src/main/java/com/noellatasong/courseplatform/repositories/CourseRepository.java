package com.noellatasong.courseplatform.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.noellatasong.courseplatform.models.Course;

@Repository
public interface CourseRepository  extends CrudRepository<Course, Long>{

}
