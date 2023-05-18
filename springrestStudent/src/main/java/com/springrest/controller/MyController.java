package com.springrest.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springrest.Dao.StudentDaoImpl;
import com.springrest.entity.Course;
import com.springrest.entity.Student;

@RestController

public class MyController 
{
	
	@Autowired
	private StudentDaoImpl studentDaoImpl;
	
	//get course by id
	@GetMapping("/courses/{id}")
	public List<Course> getCourseById(@PathVariable int id)
	{
		return studentDaoImpl.getCourseById(id);
	}
	
	//get course by price
	@GetMapping("/course/{price}")
	public List<Course> getCourseByPrice( @PathVariable int price)
	{
		return studentDaoImpl.getCourseByPrice(price);
	}
	
	@GetMapping("/students")
	public ResponseEntity<List<Student>> geStudents()
	{
		List<Student> list=studentDaoImpl.getStudent();
		if(list.size()<=0)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(list);
	}
	
	@PostMapping("/students")
	public ResponseEntity<Student> addStudent(@RequestBody Student student)
	{
		Student s=null;
		try {
			 s=this.studentDaoImpl.addStudent(student);
			 return ResponseEntity.status(HttpStatus.CREATED).build();
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		return ResponseEntity.of(Optional.of(s));
	}
	
	@GetMapping("/students/{sid}")
	public ResponseEntity<Student> getStudent(@PathVariable int sid)
	{
		Student student=studentDaoImpl.getStudentById(sid);
		if(student==null)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.of(Optional.of(student));
	}
	@PutMapping("/students")
	public ResponseEntity<Student> upStudent( @RequestBody  Student student)
	{
		try {
		    this.studentDaoImpl.updateStudent(student);
			return ResponseEntity.ok().body(student);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	@DeleteMapping("/students/{studentid}")
	public ResponseEntity<HttpStatus> deleteStudent(@PathVariable int studentid)
	{
		try {
			this.studentDaoImpl.deleteStudent(studentid);
			return  ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (Exception e) {
			return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
    
}
