package com.luv2code.springboot.thymeleafdemo.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import com.luv2code.springboot.thymeleafdemo.entity.Employee;

@TestPropertySource("/application.properties")
@SpringBootTest
@RunWith(SpringRunner.class)
public class EmployeeDaoTest {

	@Autowired
	private EmployeeRepository repository;

	@Test
	public void createEmployee() {
		Employee employee = new Employee("Mc", "Millan", "mcmillan@gmail.com");

		Employee savedObject = repository.save(employee);

		Assert.isTrue((savedObject.getId() != 0), "Saved Successfully");
	}

}
