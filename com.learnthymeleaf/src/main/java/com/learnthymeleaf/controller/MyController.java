package com.learnthymeleaf.controller;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.learnthymeleaf.entity.Employee;

import serverSideformValidation.entity.LoginData;

@Controller

public class MyController 
{
	
	
	@RequestMapping(value="/about" ,method=RequestMethod.GET)
	public String about(Model m)
	{
		
		System.out.println("Inside about page");
		m.addAttribute("name", "Sachin");
		m.addAttribute("Date", new Date());
		return "about";
	}
    @GetMapping("/example-loop")
	public String iterateHandler(Model m)
	{
    	System.out.println("this is inside iterateHandler method");
    	//create a list
    	List<String> names=List.of("Tushar","Rahul","Ankit","Kartik");
    	m.addAttribute("names", names);
		return "iterate";
	}
    @GetMapping({"/employee","/empdetail"})
    public String getEmployeeDetails(Model m)
    {
    	Employee employee=new Employee();
    	employee.setEid(1);
    	employee.setName("Ganesh");
    	employee.setMobileNumber("9545439928");
    	m.addAttribute("myemp", employee);
    	
    	return "employee";
    }
    @GetMapping("/return")
    public String getEmpDetails()
    {
		return "Return";
    	
    }
    @GetMapping("/condition")
    public String conditionHandler(Model m)
    {
    	System.out.println("Condtion handler is executed");
    	m.addAttribute("isActive", true);
    	m.addAttribute("gender", "M");
    	//create list to use switch case
    	List<Integer> list=List.of(12);
    	m.addAttribute("mylist", list);
    	return "condition";
    }
    
    @GetMapping("/service")
    public String footerHandler(Model m)
    {
    	m.addAttribute("title", "These are the details of employee");
    	m.addAttribute("subtitle", LocalDateTime.now().toString());
    	return "service";
    }
    
    //for server side validation form  
    
    @GetMapping("/form")
	public String getForm(Model m)
	{
    	System.out.println("_____________1__________________");
    	m.addAttribute("logindata", new LoginData());
		return "form";
		
	}
    @PostMapping("/process")
    public String processForm(@Valid @ModelAttribute("logindata") LoginData loginData,BindingResult result)
    {
    	if(result.hasErrors())
    	{
    		System.out.println(result);
    		return "form";
    	}
    	System.out.println(loginData);
    	return "success";
    }
    
   
    
    
    
    
    
    
}
