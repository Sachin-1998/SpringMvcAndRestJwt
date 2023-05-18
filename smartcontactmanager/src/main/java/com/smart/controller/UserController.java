package com.smart.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.smart.dao.UserRepo;
import com.smart.entities.Contact;
import com.smart.entities.User;
import com.smart.helper.message;

@Controller
@RequestMapping("/user")
public class UserController 
{
	//we use UserRepo ref obj to get the whole deatils of the user
	@Autowired
	private UserRepo userRepo;
	//method for adding data to response
	@ModelAttribute
	public void addCommonData(Principal principal,Model model)
	{
		       //fetch user name entered while login
				String name=principal.getName();
				System.out.println("USERNAME "+name);
				//fetch all details of user from db.
				User user=userRepo.getUserByUserName(name);
				model.addAttribute("user", user);
				System.out.println("Username "+user);
	}
	/* use of principal =it is used to get the information of user like username(email)   */
	@RequestMapping("/index")
	public String dashboard(  Model model,Principal principal)
	{
		model.addAttribute("title", "User Dashboard");
		return "normal/user_dashboard";
	}
	//open add contact form  handler
	@GetMapping("/add-contact")
	public String openaddContactForm(Model model)
	{
		model.addAttribute("title", "Add Contact");
		model.addAttribute("contact", new Contact());
		return "normal/add_Contact_form";
	}
	//process contact
	@PostMapping("/process-contact")
	public String processContact(@Valid @ModelAttribute Contact contact, @RequestParam("profileImage") MultipartFile file,  BindingResult result, Principal principal,Model m,HttpSession session)
	{
		try 
		{
			if(result.hasErrors())
			{
				System.out.println("Error: "+result);
				m.addAttribute("contact", contact);
				m.addAttribute("title", "Add Contact");
				return "normal/add_Contact_form";
			}
			
			String name=principal.getName();
			System.out.println("Username: "+name);
			User user=this.userRepo.getUserByUserName(name);
			
			//processing and uploading file
			
			if(file.isEmpty())
			{
				System.out.println("File is empty");
			}
			else
			{
				//upload the file to folder and update the name of conatct
				contact.setImage(file.getOriginalFilename());
				//set the dynamic path to store the file.
				File saveFile=new ClassPathResource("/static/image").getFile();
				//copy files on this path
				Path path=Paths.get(saveFile.getAbsolutePath()+File.separator+file.getOriginalFilename());
				Files.copy(file.getInputStream(), path,StandardCopyOption.REPLACE_EXISTING );
				System.out.println("file uploaded successfully");
				
			}
			contact.setUser(user);
			user.getContacts().add(contact);
			this.userRepo.save(user);
			System.out.println("user added to database");
			// message success
			session.setAttribute("message", new message("Contact added successfully !! Add More !!", "success"));
			
		} catch (Exception e) 
		{
			System.out.println(e.getMessage());
			e.printStackTrace();
			//message error
			session.setAttribute("message", new message("Something went wrong!! Please try again !!", "danger"));

		}
		return "normal/add_Contact_form";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
