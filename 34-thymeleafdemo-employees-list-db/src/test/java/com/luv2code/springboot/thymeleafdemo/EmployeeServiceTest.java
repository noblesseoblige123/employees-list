package com.luv2code.springboot.thymeleafdemo;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import com.luv2code.springboot.thymeleafdemo.entity.Employee;
import com.luv2code.springboot.thymeleafdemo.service.EmployeeService;

@TestPropertySource("/application.properties")
@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeServiceTest {

	@Autowired
	private EmployeeService service;

	@Test
	@Order(1)
	public void createEmployee() {
		Employee employee = new Employee();
		employee.setLastName("Mc");
		employee.setFirstName("Millan");
		employee.setEmail("mcmillan@gmail.com");

		service.save(employee);
	}
	
	@Test
	@Order(2)
	@Sql(value = "/employee.sql")
	public void findAll() {
		List<Employee> employees = service.findAll();
		employees.forEach(System.out::println);
		Assert.notEmpty(employees, "Success");
	}

}
