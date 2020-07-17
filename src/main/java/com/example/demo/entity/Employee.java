/*
 * Company: 
 * Copyright (c) 2012-2032 
 * All Rights Reserved.
 */
package com.example.demo.entity;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Field;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.Index;
import dev.morphia.annotations.IndexOptions;
import dev.morphia.annotations.Indexes;
import dev.morphia.annotations.Property;
import dev.morphia.annotations.Reference;

@Entity("employees")
@Indexes(@Index(options = @IndexOptions(name = "salary"), fields = @Field("salary")))
public class Employee {

	@Id
	private ObjectId id;
	
	private String name;//姓名
	
	private Integer age;//年龄
	
	@Reference
	private Employee manager;//经理
	
	@Reference
	private List<Employee> directReports = new ArrayList<>();//直接下属
	
	@Property("wage")
	private Double salary;//工资
	
	public Employee() {
		super();
	}
	
	public Employee(String name, Integer age, Double salary) {
		super();
		this.name = name;
		this.age = age;
		this.salary = salary;
	}

	public Employee(String name, Integer age, Employee manager, List<Employee> directReports, Double salary) {
		super();
		this.name = name;
		this.age = age;
		this.manager = manager;
		this.directReports = directReports;
		this.salary = salary;
	}

	public Employee(ObjectId id, String name, Integer age, Employee manager, List<Employee> directReports,
			Double salary) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.manager = manager;
		this.directReports = directReports;
		this.salary = salary;
	}
	
	public ObjectId getId() {
		return id;
	}
	public void setId(ObjectId id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Employee getManager() {
		return manager;
	}
	public void setManager(Employee manager) {
		this.manager = manager;
	}
	public List<Employee> getDirectReports() {
		return directReports;
	}
	public void setDirectReports(List<Employee> directReports) {
		this.directReports = directReports;
	}
	public Double getSalary() {
		return salary;
	}
	public void setSalary(Double salary) {
		this.salary = salary;
	}
	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", age=" + age + ", manager=" + manager + ", directReports="
				+ directReports + ", salary=" + salary + "]";
	}
}
