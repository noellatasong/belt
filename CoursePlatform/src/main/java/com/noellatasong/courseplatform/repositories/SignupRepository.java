package com.noellatasong.courseplatform.repositories;



import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.noellatasong.courseplatform.models.Signup;

@Repository
public interface SignupRepository extends CrudRepository<Signup, Long>{
	ArrayList<Signup> findByCourseId(Long course_id);
	
}
