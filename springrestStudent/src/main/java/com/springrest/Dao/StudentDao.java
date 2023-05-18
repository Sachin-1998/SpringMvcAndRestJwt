package com.springrest.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.springrest.entity.Course;
import com.springrest.entity.Student;

public interface StudentDao extends JpaRepository<Student, Integer>{
	
//custom finder methods
public List<Student> findByName(String name);
public List<Student> findByNameAndMarks(String name,int marks);

//jpql
@Query("select name from Student name")
public List<Student> getAllStudent();
//jpql for getting courses by id
@Query("select c from Course c where c.id =:id")
public List<Course> getCourseById(@Param("id") int id);

@Query("select c from Course c where c.price =:price")
public List<Course> getCourseByPrice(@Param("price") int price);

@Query("SELECT s from Student s WHERE s.name =:n and s.marks =:m" )
public List<Student> getStudentByName(@Param("n") String name, @Param("m") int marks);

//native query
@Query(value = "select * from Student", nativeQuery = true)
public List<Student> getAllStudents();




	

}
