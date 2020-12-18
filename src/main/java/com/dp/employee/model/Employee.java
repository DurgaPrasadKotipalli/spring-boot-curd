package com.dp.employee.model;

import java.util.Date;

import org.springframework.hateoas.RepresentationModel;

public class Employee  extends RepresentationModel<Employee>{
	
	private int id;
	private String name;
	private int age;
	private String mobile;
    private Date dob;
    
    public Employee(int id, String name, int age, String mobile, Date dob) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.mobile = mobile;
		this.dob = dob;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	
	
	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", age=" + age + ", mobile=" + mobile + ", dob=" + dob
				+ ", getId()=" + getId() + ", getName()=" + getName() + ", getAge()=" + getAge() + ", getMobile()="
				+ getMobile() + ", getDob()=" + getDob() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
    
    
    
    
    

}
