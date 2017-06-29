package com.roombookingmanagementsystem.spring.tutorial;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface EmployeeActivitiesRepository extends MongoRepository<EmployeeActivities, String> {
	
	
	public List<EmployeeActivities> findByDateAndEId(String date,String eId);

}
