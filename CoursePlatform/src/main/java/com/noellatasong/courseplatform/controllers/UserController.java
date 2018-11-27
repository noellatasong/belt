package com.noellatasong.courseplatform.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.noellatasong.courseplatform.models.User;
import com.noellatasong.courseplatform.services.CourseService;
import com.noellatasong.courseplatform.services.SignupService;
import com.noellatasong.courseplatform.services.UserService;

@Controller
public class UserController {
    private final UserService userService;
    private  CourseService courseService;
    private  SignupService signupService;
    
    
    public UserController(UserService userService, CourseService courseService, SignupService signupService) {
        this.userService = userService;
        this.courseService = courseService;
        this.signupService = signupService;
    }
    
    @GetMapping("/")
    public String registerForm(@ModelAttribute("user") User user) {
        return "index.jsp";
    }    
    
    @PostMapping("/registration")
	public String register(@Valid @ModelAttribute("user") User user, BindingResult result, Model model, HttpSession session) {
		if(result.hasErrors()) {
			return "index.jsp";
		}else {
			if(!user.getPassword().equals(user.getConfirm())) {
				model.addAttribute("userError", "Password's must match!");
				return "index.jsp";
			} else {
				String pw = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
				user.setPassword(pw);
				User exists= userService.findByEmail(user.getEmail());
				
				if(exists != null) {
					model.addAttribute("userError", "A user with this email already exists");
					return "index.jsp";
				} else {
					userService.create(user);
					session.setAttribute("user", user.getId());
					return "redirect:/dashboard";
				}
			}
		}
	}
    
    
    
    @PostMapping("/login")
	public String login( @RequestParam("email") String email, @RequestParam("password") String password, Model model, HttpSession session) {
		if(email.length() < 1) {
			model.addAttribute("loginError", "Invalid Credentials");
			model.addAttribute("user", new User());
			return "index.jsp";
		}
		if(password.length() < 8) {
			model.addAttribute("loginError", "Invalid Credentials");
			model.addAttribute("user", new User());
			System.out.println("hello");
			return "index.jsp";
		}
		
		User u = userService.findByEmail(email);
		
		if(u == null) {
			model.addAttribute("loginError", "Invalid Credentials");
			model.addAttribute("user", new User());
			return "index.jspr";
		} else {
			boolean matches = BCrypt.checkpw(password, u.getPassword());
			
			if(matches) {
				session.setAttribute("user", u.getId() );
				return "redirect:/dashboard";
				} else {
					model.addAttribute("loginError", "Invalid Credentials");
					model.addAttribute("user", new User());
					return "index.jsp";
				}
			}
		}
	
    @GetMapping("/dashboard")
	public String dashboard(Model model, HttpSession session) {
		Long id = (Long) session.getAttribute("user");
		if(id == null) {
			return "redirect:/";
		} else {
			User u = userService.findById(id); 
			model.addAttribute("user", u);
			model.addAttribute("courses", courseService.findAll());
			model.addAttribute("signups", signupService.findAll());
			System.out.println("hello");
			return "dashboard.jsp";
		}
	}
	
}