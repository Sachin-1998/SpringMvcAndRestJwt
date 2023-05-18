package com.springrest.Dao;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.springrest.entity.Course;
import com.springrest.entity.Student;

@Service
public class StudentDaoImpl 
{
	@Autowired
	private StudentDao studentDao;
	
	public List<Course> getCourseById(@Param("id") int id)
	{
		return studentDao.getCourseById(id);
	}
	
	public List<Course> getCourseByPrice( int price)
	{
		return studentDao.getCourseByPrice(price);
	}
	public List<Student> getStudent()
	{ 
		return studentDao.findAll();
		
	}
	
	
	public Student getStudentById( int sid)
	{
		Student student=null;
		try {
			return studentDao.findById(sid).get();
			
		} catch (Exception e) 
		{
			e.printStackTrace();
			
		}
		return student;
		
	}
	
	public Student addStudent(Student student)
	{
		return studentDao.save(student);
		
	}
	
	public Student updateStudent(Student student)
	{
		return studentDao.save(student);
	}
	
	public void deleteStudent(int studentid)
	{
		@SuppressWarnings("deprecation")
		Student s=studentDao.getOne( studentid);
		studentDao.delete(s);
		
	}

	
	

}
