/*
 * Company: 
 * Copyright (c) 2012-2032 
 * All Rights Reserved.
 */
package com.example.demo.entity;

import org.bson.types.ObjectId;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;

@Entity("department")
public class Department {
	
	@Id
	private ObjectId id;
	
	private String departmentName;//名称
	
	private String describe;//描述

	public Department() {
		super();
	}

	public Department(String departmentName, String describe) {
		super();
		this.departmentName = departmentName;
		this.describe = describe;
	}

	public Department(ObjectId id, String departmentName, String describe) {
		super();
		this.id = id;
		this.departmentName = departmentName;
		this.describe = describe;
	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	@Override
	public String toString() {
		return "Department [id=" + id + ", departmentName=" + departmentName + ", describe=" + describe + "]";
	}
}
