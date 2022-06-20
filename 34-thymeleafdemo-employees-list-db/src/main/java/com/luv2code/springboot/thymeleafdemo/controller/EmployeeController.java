package com.luv2code.springboot.thymeleafdemo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.luv2code.springboot.thymeleafdemo.entity.Employee;
import com.luv2code.springboot.thymeleafdemo.service.EmployeeService;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

	private EmployeeService employeeService;

	public EmployeeController(EmployeeService theEmployeeService) {
		employeeService = theEmployeeService;
	}

	// add mapping for "/list"

	@GetMapping("/list")
	public String listEmployees(Model theModel) {

		// get employees from db
		List<Employee> theEmployees = employeeService.findAll();

		// add to the spring model
		theModel.addAttribute("employees", theEmployees);

		return "employees/list-employees";
	}

	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model model) {
		// create model attribute to bind form data
		Employee employee = new Employee();

		model.addAttribute("employee", employee);

		return "employees/employee-form";
	}

	@GetMapping("/showFormForUpdate")
	public String showFormForAdd(@RequestParam("employeeId") int id, Model model) {
		// create model attribute to bind form data
		Employee employee = employeeService.findById(id);

		model.addAttribute("employee", employee);

		return "employees/employee-form";
	}

	@PostMapping("/save")
	public String saveEmployee(@ModelAttribute("employee") Employee employee) {
		// create model attribute to bind form data

		employeeService.save(employee);

		return "redirect:/employees/list";
	}

	@GetMapping("/delete")
	public String delete(@RequestParam("employeeId") int id) {
		// create model attribute to bind form data
		employeeService.deleteById(id);

		return "redirect:/employees/list";
	}
}
