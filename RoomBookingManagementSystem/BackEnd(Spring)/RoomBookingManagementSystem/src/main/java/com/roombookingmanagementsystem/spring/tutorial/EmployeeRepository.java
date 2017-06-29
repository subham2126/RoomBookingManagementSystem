package com.roombookingmanagementsystem.spring.tutorial;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface EmployeeRepository extends MongoRepository<Employee, String> {

	
	public Employee findByEmpName(String empName);
}
