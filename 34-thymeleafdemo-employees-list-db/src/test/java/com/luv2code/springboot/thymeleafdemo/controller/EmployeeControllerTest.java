package com.luv2code.springboot.thymeleafdemo.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.ModelAndViewAssert;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import com.luv2code.springboot.thymeleafdemo.entity.Employee;
import com.luv2code.springboot.thymeleafdemo.service.EmployeeService;

@AutoConfigureMockMvc
@SpringBootTest
@RunWith(SpringRunner.class)
public class EmployeeControllerTest {

	private static MockHttpServletRequest request;

	@Autowired
	private WebApplicationContext wac;

	@Autowired
	private MockMvc mockMvc;

	@Mock
	private EmployeeService service;

	@Test
	public void findAllController() throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/employees/list")).andExpect(status().isOk())
				.andReturn();

		ModelAndView mav = result.getModelAndView();
		ModelAndViewAssert.assertViewName(mav, "employees/list-employees");
	}

	@Test
	public void saveController() throws Exception {
		MvcResult result = mockMvc.perform(post("/employees/save").contentType(MediaType.APPLICATION_JSON)
				.param("firstname", request.getParameterValues("firstname"))
				.param("lastname", request.getParameterValues("lastname"))
				.param("email", request.getParameterValues("email"))).andExpect(status().isOk()).andReturn();

		ModelAndView mav = result.getModelAndView();
		ModelAndViewAssert.assertViewName(mav, "employees/list-employees");
	}

	@Test
	public void createEmployee() {
		Employee employee = new Employee("Mc", "Millan", "mcmillan@gmail.com");

		when(service.findAll()).thenReturn(Arrays.asList(employee));
	}

}
