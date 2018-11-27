package com.noellatasong.courseplatform.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.noellatasong.courseplatform.models.Course;
import com.noellatasong.courseplatform.models.Signup;
import com.noellatasong.courseplatform.models.User;
import com.noellatasong.courseplatform.services.CourseService;
import com.noellatasong.courseplatform.services.SignupService;
import com.noellatasong.courseplatform.services.UserService;


@Controller
@RequestMapping("/course")
public class CourseController {
	private UserService userService;
	private CourseService courseService;
	private SignupService signupService;
	
	public CourseController(UserService userService, CourseService courseService, SignupService signupService) {
		this.userService = userService;
		this.courseService = courseService;
		this.signupService = signupService;
	}
	
	@GetMapping("/add")
	public String newCourse(@ModelAttribute ("course")Course course) {
		return "new.jsp";
	}
	
	@PostMapping("/create")
	public String createCourse(@Valid @ModelAttribute("course") Course course, BindingResult res, HttpSession session) {
		if(res.hasErrors()) {
			return "new.jsp";
		}
		User user = userService.findById((Long)session.getAttribute("user"));
		course.setUser(user);
		course.setUsers(null);
		courseService.create(course);		
		return "redirect:/dashboard";
		
	}
	
	@GetMapping("/{course_id}")
    public String displayShow(@PathVariable("course_id") Long course_id, Model model, HttpSession session) {
    	Course singleCourse = courseService.findById(course_id);
    	model.addAttribute("singleCourse", singleCourse );
    	model.addAttribute("user_id",(Long)session.getAttribute("user"));
    	model.addAttribute("signups", signupService.findCourse( course_id ) );
    	System.out.println("hello");
    	return "show.jsp";
    }
	
	
	@PostMapping("/addcourse/{user_id}/{course_id}")
	public String addCourse(@PathVariable("user_id") Long user_id, @PathVariable("course_id") Long course_id) {
		User user = userService.findById(user_id);
		Course course = courseService.findById(course_id);
		
		Signup newSignup = new Signup();
    	newSignup.setUser( user );
    	newSignup.setCourse( course );
    	newSignup.setSigned(true);
    	signupService.create( newSignup );
    	System.out.println(course);
		return "redirect:/dashboard";
	}
	
	
	
	@GetMapping("/edit/{id}")
	public String showedit(@PathVariable("id") Long id, @ModelAttribute ("courseedit")Course course, Model model) {
		Course courses = courseService.findById(id);
        model.addAttribute("course", courses);
		return "edit.jsp";
	}
	
	@PostMapping("/update/{id}")
	public String editCourse(@Valid @PathVariable("id") Long id, @ModelAttribute("courseedit") Course course, BindingResult res) {
		if(res.hasErrors()) {
			return "edit.jsp";
		}
		courseService.update(course);		
		return "redirect:/dashboard";
		
	}
	
	@GetMapping("/remove/{id}")
	public String delete(@PathVariable("id") Long id) {
		courseService.deleteById(id);
		return "redirect:/dashboard";
	}
	
	
}

