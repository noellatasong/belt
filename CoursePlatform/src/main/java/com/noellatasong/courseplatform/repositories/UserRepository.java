package com.noellatasong.courseplatform.repositories;

import java.util.List;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.noellatasong.courseplatform.models.User;

@Repository
public interface UserRepository  extends CrudRepository<User, Long> {
	User findByEmail(String email);
	List<User> findAll();
	User findByEmailAndPassword(String email, String password);
	
}
