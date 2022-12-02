package net.javaguide.springboot.service;

import java.util.List;

import org.springframework.stereotype.Service;

import net.javaguide.springboot.model.Employee;

@Service
public interface EmployeeService {
	
	Employee saveEmployee(Employee employee); 
    List<Employee>getAllEmployee();	
    Employee getEmployeeById(long id);
    Employee updateEmployee(Employee employee, long id);
    void deleteEmployee(long id);

}
