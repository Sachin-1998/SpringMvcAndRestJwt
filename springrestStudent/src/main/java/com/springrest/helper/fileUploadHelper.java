package com.springrest.helper;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class fileUploadHelper 
{
	//public final String Upload_Dir="/home/sachin/eclipse-workspace/springrestStudent/src/main/resources/static/image";
	public final String Upload_Dir=new ClassPathResource("static/image/").getFile().getAbsolutePath();
	public fileUploadHelper () throws IOException
	{
		
	}
	public boolean uploadFile(MultipartFile multipartFile)
	{
		boolean f=false;
		try {
			
            Files.copy(multipartFile.getInputStream(), Paths.get(Upload_Dir+File.separator+multipartFile.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
            f=true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;
	}
 
}
