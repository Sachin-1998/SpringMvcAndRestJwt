package com.springrest.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="Course_Datails")
public class Course 
{
	
	@Id
	@Column(name="Course_Id")
	@NotNull
	private int id;
	@Column(name="Course_title")
	private String title;
	@Column(name="Course_price")
	private int price;
	@Column(name="Course_Content")
    private String description;
	
	@OneToOne(mappedBy = "course")
	@JsonBackReference
	private Student student;
	public Course() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Course(int id, String title, int price, String description) {
		super();
		this.id = id;
		this.title = title;
		this.price = price;
		this.description = description;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	
	
    
}
