package com.springrest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class HomeController 
{
	@GetMapping("/return")
	public String getAllData()
	{
		return "showdata";
		
	}

}
