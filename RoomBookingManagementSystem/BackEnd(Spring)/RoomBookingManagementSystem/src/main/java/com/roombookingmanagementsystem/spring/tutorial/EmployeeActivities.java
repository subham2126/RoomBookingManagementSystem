package com.roombookingmanagementsystem.spring.tutorial;

public class EmployeeActivities {
	
	
	public String date ;
	public int start;
	public int end;
	public String eId;
	public EmployeeActivities(String date,int start, int end,String eId)
	{
	
		this.date= date;
		this.start = start;
		this.end=  end;
		this.eId = eId;
	}

}
