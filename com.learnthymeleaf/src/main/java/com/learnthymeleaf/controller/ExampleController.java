package com.learnthymeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ExampleController 
{
	@RequestMapping("/example")
	public String addController()
	{
		return "example";
	}

}
