package com.smart.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.smart.dao.UserRepo;
import com.smart.entities.User;
import com.smart.helper.message;

@Controller
public class HomeController 
{
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	@Autowired
	private UserRepo userRepo;
	@GetMapping("/")
	public String home(Model model)
	{
		model.addAttribute("title", "Home-Smart Contact Manager");
		return "Home";
		
	}
	@GetMapping("/about")
	public String about(Model model)
	{
		model.addAttribute("title", "about-Smart Contact Manager");
		return "about";
		
	}
	@GetMapping("/signup")
	public String signup(Model model)
	{
		model.addAttribute("title", "Register-Smart Contact Manager");
		model.addAttribute("user", new User());
		return "signup";
		
	}
	@PostMapping("/do_register")
	public String registerUser( @Valid @ModelAttribute("user") User user,  BindingResult result1,   @RequestParam(value = "agreement",defaultValue = "false") boolean agreement,Model m,  HttpSession session)
	{
		try 
		{
			//if agreement is false
			if(!agreement)
			{
				System.out.println("please accept the terms and conditions");
				throw new Exception("please accept the terms and conditions");
			}
			//server side form validation
			if(result1.hasErrors())
			{
				System.out.println(result1);
				m.addAttribute("user", user);
				return "signup";
			}
			//set the values //enables=user is active or not
			user.setEnabled(true);
			user.setRole("Normal_user");
			user.setImageurl("default_png");
			//encode the password we write while registration
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			
			System.out.println("Agreement :"+agreement);
			System.out.println(user);
			//saving user info into database
			User result=this.userRepo.save(user);
			
			// to add new  user
			m.addAttribute("user", new User());
			session.setAttribute("message",new message("User Successfully registered !!", "alert-success"));
			return "signup";
		} catch (Exception e) 
		{
			e.printStackTrace();
			//to add the user
			m.addAttribute("user", user);
			session.setAttribute("message",new message("Something went wrong !! "+ e.getMessage(), "alert-danger"));
			return "signup";
		}
		
	}
	
	//custom controller
	@RequestMapping("/signin")
	public String customlogin(Model m)
	{
		m.addAttribute("title", "Login Page");
		return "login";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
