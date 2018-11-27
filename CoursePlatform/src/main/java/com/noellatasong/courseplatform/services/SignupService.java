package com.noellatasong.courseplatform.services;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.noellatasong.courseplatform.models.Signup;
import com.noellatasong.courseplatform.repositories.SignupRepository;

@Service
public class SignupService {
	private final SignupRepository signupRepository;
	
	public SignupService(SignupRepository signupRepository) {
		this.signupRepository=signupRepository;
	}
	
	public Signup create(Signup signup) {
		return signupRepository.save(signup);
	}
	
	public ArrayList<Signup> findAll(){
		return (ArrayList<Signup>) signupRepository.findAll();
	}
	
	public Signup findById(Long id) {
		return signupRepository.findById(id).get();
	}
	
	public ArrayList<Signup> findCourse(Long courseId){
		return  (ArrayList<Signup>) signupRepository.findByCourseId(courseId);
	}
	
	public Signup update(Signup signup) {
		return signupRepository.save(signup);
	}
	
	public void destroy(Long id) {
		signupRepository.deleteById(id);
	}

}