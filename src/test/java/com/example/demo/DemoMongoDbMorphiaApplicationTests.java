package com.example.demo;


import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.common.Page;
import com.example.demo.dao.DepartmentDao;
import com.example.demo.dao.EmployeeDao;
import com.example.demo.entity.Department;
import com.example.demo.entity.Employee;

@SpringBootTest
class DemoMongoDbMorphiaApplicationTests {

	@Autowired
	public EmployeeDao employeeDao;
	
	@Autowired
	public DepartmentDao departmentDao;
	
	@Test
	void testEmployee_save() {
		Employee employee = new Employee("张三", 26, 50000.0);
		employeeDao.save(employee);
		
		Department department = new Department("研发部", "软件研发");
		departmentDao.save(department);
	}
	
	@Test
	void testEmployee_saveList() {
		List<Employee> employees = new ArrayList<Employee>();
		Employee employee = new Employee("张三到", 28, 50000.0);
		employees.add(employee);
		Employee employee1 = new Employee("山顶", 29, 50000.0);
		employees.add(employee1);
		Employee employee2 = new Employee("风飞沙", 36, 50000.0);
		employees.add(employee2);
		Employee employee3 = new Employee("不然", 43, 50000.0);
		employee3.getDirectReports().add(employee);
		employee3.getDirectReports().add(employee1);
		employee3.getDirectReports().add(employee2);
		employees.add(employee3);
		employeeDao.save(employees);
	}
	
	@Test
	void testEmployee_queryByExample() {
		List<Employee> employees = new ArrayList<Employee>();
		employees = employeeDao.findAll();
		System.out.println(employees);
		System.out.println(employees.size());
		
		Employee employee = new Employee("张三到", 28, 50000.0);
		employees = employeeDao.queryByExample(employee);
		System.out.println(employees);
		System.out.println(employees.size());
		
		employee = new Employee();
		employee.setSalary(50000.0);
		employees = employeeDao.queryByExample(employee);
		System.out.println(employees);
		System.out.println(employees.size());
		
		employee = new Employee();
		employees = employeeDao.queryByExample(employee);
		System.out.println(employees);
		System.out.println(employees.size());
		
		employee = new Employee();
		employee.setName("李四");
		employees = employeeDao.queryByExample(employee);
		System.out.println(employees);
		System.out.println(employees.size());
		
		employee = new Employee();
		employee.setId(new ObjectId("5f10327c831bc9285b07fd8a"));
//		employee.setName("李四");
		employees = employeeDao.queryByExample(employee);
		System.out.println(employees);
		System.out.println(employees.size());
		
		employee = new Employee();
		employee.setName("李四");
		Page<Employee> pages = employeeDao.queryByExample(employee,2,2);
		System.out.println(pages);
	}
	
	@Test
	void testEmployee_delete() {
		Employee employee = new Employee();
		employee.setId(new ObjectId("5f11212637bc244f217252ad"));
		long l = employeeDao.delete(employee);
		System.out.println(l);
	}
	
	@Test
	void testEmployee_update() {
		long l = 0;
		Employee employee = new Employee();
//		employee.setId(new ObjectId("5f10327c831bc9285b07fd8a"));
//		employee.setName("李四");
		employee.setSalary(40000.0);
//		employee.setName("张三");
		Employee employee1 = new Employee();
		employee1.setAge(33);
		employee1.setSalary(500000.0);
		employee1.setName("李四导出");
		l = employeeDao.update(employee,employee1);
		System.out.println(l);
	}
	
	@Test
	void contextLoads() {
		
	}

}
