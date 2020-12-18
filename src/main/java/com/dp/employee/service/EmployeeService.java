package com.dp.employee.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.dp.employee.model.Employee;

@Component
public interface EmployeeService {
	
	public List<Employee> getAllEmployees();
	
	public Employee getEmployeeWithId(int id);
	
	public String saveEmployee(Employee emp);
	
	public String updateEmployee(Employee emp);
	
	public String deleteEmployee(int id);

}
