package com.noellatasong.courseplatform.services;

import java.util.ArrayList;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.noellatasong.courseplatform.models.User;
import com.noellatasong.courseplatform.repositories.UserRepository;


@Service
public class UserService {
	
	private UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public User create(User user) {
		return userRepository.save(user);
	}
	
	public ArrayList<User>findAll() {
		return (ArrayList<User>) userRepository.findAll();
	}
	
	public User findById(Long id) {
		Optional<User> user = userRepository.findById(id);
		if(user.isPresent()) return user.get();
		return null;
	}
	public User registerUser(User user) {
        String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hashed);
        return userRepository.save(user);
    }
    
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    
    public boolean authenticateUser(String email, String password) {
        // first find the user by email
        User user = userRepository.findByEmail(email);
        // if we can't find it by email, return false
        if(user == null) {
            return false;
        } else {
            // if the passwords match, return true, else, return false
            if(BCrypt.checkpw(password, user.getPassword())) {
                return true;
            } else {
                return false;
            }
        }
    }
	
	public User update(User user) {
		return userRepository.save(user);
	}
	
	
	public void delete(User user) {
		userRepository.delete(user);
		
	}
	
	public void delete(Long id) {
		userRepository.deleteById(id);
		
	}

	
	
	

}

