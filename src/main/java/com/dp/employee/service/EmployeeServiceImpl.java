package com.dp.employee.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.dp.employee.exception.EmployeeNotFoundException;
import com.dp.employee.model.Employee;

@Component
public class EmployeeServiceImpl implements EmployeeService {
	
	
	public List<Employee> employees = new ArrayList<>();
	
	
	public EmployeeServiceImpl() {
		Employee emp1 = new Employee(501, "Shyam", 23, "9885473503", new Date("23-Jan-1990"));
		Employee emp2 = new Employee(502, "Smith", 21, "9885473404", new Date("23-Jan-1989"));
		Employee emp3 = new Employee(503, "John", 26, "9885473609", new Date("23-Jan-1992"));
		Employee emp4 = new Employee(504, "Bharat", 28, "9885473455", new Date("23-Jan-1997"));
		Employee emp5 = new Employee(505, "Steve", 20, "9885473377", new Date("23-Jan-1995"));
		
		employees.add(emp1);
		employees.add(emp2);
		employees.add(emp3);
		employees.add(emp4);
		employees.add(emp5);
		
	}

	@Override
	@Cacheable(cacheNames = "employees")
	public List<Employee> getAllEmployees() {
		System.out.println("Getting employee list from database ");
		return employees;
	}

	@Override
	@Cacheable(cacheNames = "employees", key = "#id")
	public Employee getEmployeeWithId(int id) {
		System.out.println("Getting employee from database ");
		List<Employee> emps =  employees.stream().filter(x -> x.getId() == id).collect(Collectors.toList());
		 if( emps.isEmpty() ) {
		    	throw new EmployeeNotFoundException("Employee with id "+id+" not Found");
		    }
		return emps.get(0);
	}

	@Override
	@Cacheable(cacheNames = "employees", key ="#emp.id")
	public String saveEmployee(Employee emp) {
		List<Employee> emps =  employees.stream().filter(x -> x.getId() == emp.getId()).collect(Collectors.toList());
		    if( !emps.isEmpty() ) {
		    	return "Employee with id :"+emp.getId()+" is already available in database";
		    }
		employees.add(emp);
		return "SUCCESS";
	}

	@Override
	@CachePut(cacheNames = "employees", key ="#emp.id")
	public String updateEmployee(Employee emp) {
		
		System.out.println("updating employee in database ");		
		List<Employee> emps =  employees.stream().filter(x -> x.getId() == emp.getId()).collect(Collectors.toList());
		
		 if( emps.isEmpty() ) {
		    	throw new EmployeeNotFoundException("Employee not Found");
		    }
		
		emps.get(0).setAge(emp.getAge());
		emps.get(0).setDob(emp.getDob());
		emps.get(0).setMobile(emp.getMobile());
		emps.get(0).setName(emp.getName());
		return "SUCCESS";
	}

	@Override
	@CacheEvict(cacheNames = "employees", key ="#id")
	public String deleteEmployee(int id) {
		System.out.println("deleting employee in database ");
		List<Employee> emps =  employees.stream().filter(x -> x.getId() == id).collect(Collectors.toList());
		 if( emps.isEmpty() ) {
		    	return "Employee with id :"+id+" is not available in database";
		    }
		 
		Iterator itr = employees.iterator();
		
		while(itr.hasNext()) {
			Employee emp = (Employee) itr.next();
			
			if(emp.getId() == id) {
				itr.remove();
			}
		}
		
		return "SUCCESS";
	}
	
	@CacheEvict(cacheNames = "employees", allEntries = true)
	public String clearCache() {
		return "SUCCESS";
	}

}
