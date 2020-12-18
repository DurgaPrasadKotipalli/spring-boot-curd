package com.dp.employee.controller;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.dp.employee.model.Employee;
import com.dp.employee.service.EmployeeService;

@RestController
@EnableWebMvc
@RequestMapping("/rest/auth")
public class EmployeeController {

	@Autowired
	private EmployeeService empployeeService;

	@GetMapping(value = "/getEmployees", produces = MediaType.APPLICATION_XML)
	public List<Employee> getAllEmployees() {
		return empployeeService.getAllEmployees();
	}

	@GetMapping(value = "/getEmployees/{id}")
	public Employee getEmployeeWithId(@PathVariable int id) {
		Link selfLink = new Link("http://localhost:8080/getAllEmployees", "self");
		Employee emp = empployeeService.getEmployeeWithId(id);
		emp.add(selfLink);
		return emp;
	}

	@PostMapping(value = "/saveEmployee", produces = MediaType.TEXT_PLAIN)
	public String saveEmployee(@RequestBody Employee employee) {
		return empployeeService.saveEmployee(employee);
	}

	@PutMapping(value = "/updateEmployee", produces = MediaType.TEXT_PLAIN, consumes = MediaType.APPLICATION_JSON)
	public String updateEmployee(@RequestBody Employee emp) {
		return empployeeService.updateEmployee(emp);
	}

	@DeleteMapping(value = "/deleteEmployee/{id}", produces = MediaType.TEXT_PLAIN)
	public String deleteEmployee(@PathVariable int id) {
		return empployeeService.deleteEmployee(id);
	}

}
