package com.springrest.controller;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.springrest.helper.fileUploadHelper;

@RestController
public class FileUploadController 
{
	@Autowired
	private fileUploadHelper fileUploadHelper;
	@PostMapping("/uploadFile")
	public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) throws IOException
	{
		/*System.out.println(file.getName());
		System.out.println(file.getOriginalFilename());
		System.out.println(file.getContentType());
		System.out.println(file.getSize());
		System.out.println(file.isEmpty());
		System.out.println(file.getResource());
		System.out.println(file.getInputStream());
		System.out.println(file.getBytes());*/
	    try 
		{
		//validation
			if(file.isEmpty())
			{
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("File may not be empty");
			}
		//file  upload code
		  boolean	f=fileUploadHelper.uploadFile(file);
		if(f)
		{
			//return ResponseEntity.ok("File Uploaded successfully");
            return ResponseEntity.ok(ServletUriComponentsBuilder.fromCurrentContextPath().path("/image/").path(file.getOriginalFilename()).toUriString());
        }
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong ?? Please try again!!");
	}

}
