package net.javaguide.springboot.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.javaguide.springboot.model.Employee;
import net.javaguide.springboot.service.EmployeeService;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
	
	private EmployeeService employeeService;

	public EmployeeController(EmployeeService employeeService) {
		super();
		this.employeeService = employeeService;
	}

	
	//build create employee REST API 
	@PostMapping
	public ResponseEntity<Employee>saveEmployee(@RequestBody Employee employee){
		return new ResponseEntity<Employee>(employeeService.saveEmployee(employee),HttpStatus.OK);
	}
	
	@GetMapping
	public List<Employee>getAllEmployees(){
		return employeeService.getAllEmployee();
	}
	//build get employee by Id Rest Api
	
	@GetMapping("{id}")
	public ResponseEntity<Employee>findById(@PathVariable("id")long employeeId){
		return new ResponseEntity<Employee>(employeeService.getEmployeeById(employeeId),HttpStatus.OK);
	}
	
     //build update employee REST API
	@PutMapping("{id}")
	public ResponseEntity<Employee>updateEmployee(@PathVariable("id")long id, @RequestBody Employee employee){
		return new ResponseEntity<Employee>(employeeService.updateEmployee(employee, id), HttpStatus.OK);
	}
	//build  delete employee REST API
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable("id")long id){
		employeeService.deleteEmployee(id);
		
		return new ResponseEntity<String>("Employee delete successfully", HttpStatus.OK);
	}
}
