package net.javaguide.springboot.service.impl;

import java.util.Map;
import java.util.Optional;
import java.lang.reflect.Field;
import org.springframework.util.ReflectionUtils;


import java.util.List;
import org.springframework.stereotype.Service;
import net.javaguide.springboot.exception.ResourceNotFoundException;
import net.javaguide.springboot.model.Employee;
import net.javaguide.springboot.repository.EmployeeRepository;
import net.javaguide.springboot.service.EmployeeService;
@Service
public class EmployeeserviceImpl implements EmployeeService {

	
	private EmployeeRepository employeeRepository;
	
	public EmployeeserviceImpl(EmployeeRepository employeeRepository) {
		super();
		this.employeeRepository = employeeRepository;
	}

	public Employee saveEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	@Override
	public List<Employee> getAllEmployee() {
		return employeeRepository.findAll();
	}

	@Override
	public Employee getEmployeeById(long id) {
		return employeeRepository.findById(id).orElseThrow(()->new  ResourceNotFoundException("Employee", "id", id));
	}

	@Override
	public Employee updateEmployee(Employee employee, long id) {
		//we need to check whether employee is exit or not
		
		Employee exitingEmployee = employeeRepository.findById(id).orElseThrow(()-> 
		new ResourceNotFoundException("Employee", "id", id));
		
		exitingEmployee.setFirstName(employee.getFirstName());
		exitingEmployee.setLastName(employee.getLastName());
		exitingEmployee.setEmail(employee.getEmail());
		
		employeeRepository.save(exitingEmployee);
		return exitingEmployee;
	
	}
	
	@Override
	public Employee updateProductByFields(Long id, Map<String, Object> fields) {
        Optional<Employee> exitingEMP = employeeRepository.findById(id);

        if (exitingEMP.isPresent()) {
            fields.forEach((key, value) -> {
                Field field = ReflectionUtils.findField(Employee.class, key);
                field.setAccessible(true);
                ReflectionUtils.setField(field, exitingEMP.get(), value);
            });
            return employeeRepository.save(exitingEMP.get());
        }
        return null;
    }

	@Override
	public void deleteEmployee(long id) {
		//check whether employee exit in DB or not
		
		employeeRepository.findById(id).orElseThrow(()->
		new ResourceNotFoundException("Employee", "id", id));
		
		employeeRepository.deleteById(id);
		
	}
}
	

