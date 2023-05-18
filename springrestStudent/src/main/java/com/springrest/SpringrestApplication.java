package com.springrest;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.springrest.Dao.StudentDao;
import com.springrest.entity.Student;



@SpringBootApplication
public class SpringrestApplication {

	public static void main(String[] args) 
	{
		/* ApplicationContext ctx=*/  SpringApplication.run(SpringrestApplication.class, args);
		// StudentDao studentDao=ctx.getBean(StudentDao.class);
		 //custom finder methods
		 //select name from Student where name="somu"
//		 List<Student> student=((StudentDao) studentDao).findByName("somu");
//		 student.forEach(e->System.out.println(e));
//		 
		 //select * from Student where name="gomu" and marks=57;
//		 List<Student> student1=((StudentDao) studentDao).findByNameAndMarks("gomu", 57);
//		 student1.forEach(e->System.out.println(e));
//		 
         //jpql
//		 List<Student> student2=studentDao.getAllStudent();
//		 student2.forEach(e->{System.out.println(e);});
	     
		 
//		 List<Student> student3=studentDao.getStudentByName("Ajay",82);
//		 student3.forEach(e->{System.out.println(e);});
		 
		 
		 //native query method
		 //studentDao.getAllStudents().forEach(e->System.out.println(e));
		
		 
     
		 
		
	}

}
