package com.roombookingmanagementsystem.spring.tutorial;



public class Employee {

	private String empId;
	private String empName;
	private String empDesignation;
	public  static int empCount=1;
	
	
	
	public String getEmpId() {
		return empId;
	}
	
	


	public void setEmpId(String empId) {
		this.empId = empId;
	}


	public String getEmpName() {
		return empName;
	}


	public void setEmpName(String empName) {
		this.empName = empName;
	}


	public String getEmpDesignation() {
		return empDesignation;
	}


	public void setEmpDesignation(String empDesignation) {
		this.empDesignation = empDesignation;
	}
    public Employee()
    {
    	
    }
   
	public Employee(String empName,String empDesignation)
	{
		String empId = "emp";
		int digit = ((int) Math.log10(empCount))+ 1;
		for(int i=digit;i<=5;i++)
			empId = empId +'0';
			
			
		empId+=empCount;
		empCount++;
		this.setEmpId(empId);

		
		
		
		this.empName = empName;
		this.empDesignation = empDesignation;
		
	}
	
	

}
