package com.roombookingmanagementsystem.spring.tutorial;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
@Component
public class DbSeeder implements CommandLineRunner {
	private BookingRepository bookingRepository;
	private EmployeeRepository employeeRepository;
	private EmployeeActivitiesRepository employeeActivitiesRepository; 
	private RoomRepository roomRepository;
	public DbSeeder(BookingRepository bookingRepository, EmployeeRepository employeeRepository ,EmployeeActivitiesRepository employeeActivitiesRepository,RoomRepository roomRepository)
	{
		this.bookingRepository=bookingRepository;
		this.employeeRepository= employeeRepository;
		this.employeeActivitiesRepository = employeeActivitiesRepository;
		this.roomRepository = roomRepository;
		
	}
	

	@Override
	public void run(String... arg0) throws Exception {
		// TODO Auto-generated method stub
		
		List<Room> roomList = new ArrayList<Room>();
		Room room;
		room = new Room(101, true, true, 8);
		roomList.add(room);
		room = new Room(102, true, true, 10);
		roomList.add(room);
		room = new Room(103, true, false, 11);
		roomList.add(room);
		room = new Room(104, true, true, 15);
		roomList.add(room);
		this.roomRepository.deleteAll();
		this.roomRepository.save(roomList);
		
		
		List<Booking> roomBookingList=new ArrayList<Booking>();
		
		
		
		Booking roomBooking;
		roomBooking = new Booking(true,true,0, 59, "02-06-2017", "room001", "emp000001", Arrays.asList("emp000001","emp000002","emp000003"));
		roomBookingList.add(roomBooking);
		roomBooking = new Booking(true,true,60, 119, "02-06-2017", "room001", "emp000002", Arrays.asList("emp000001","emp000002","emp000003"));
		roomBookingList.add(roomBooking);
		roomBooking = new Booking(true,false,360, 419, "02-06-2017", "room001", "emp00003", Arrays.asList("emp000001","emp000002","emp000003"));
		roomBookingList.add(roomBooking);
		roomBooking = new Booking(true,false,720, 899, "02-06-2017", "room001", "emp00003", Arrays.asList("emp000001","emp000002","emp000003"));
		roomBookingList.add(roomBooking);
		roomBooking = new Booking(false,false,960,1019, "02-06-2017", "room001", "emp00001", Arrays.asList("emp000001","emp000002","emp000003"));
		roomBookingList.add(roomBooking);
		roomBooking = new Booking(true,true,0, 119, "03-06-2017", "room001", "emp00002", Arrays.asList("emp000001","emp000002","emp000003"));
		roomBookingList.add(roomBooking);
		roomBooking = new Booking(false,true,120, 239, "03-06-2017", "room001", "emp00001", Arrays.asList("emp000001","emp000002","emp000003"));
		roomBookingList.add(roomBooking);
		this.bookingRepository.deleteAll();
		this.bookingRepository.save(roomBookingList);
		
		List<Employee> employeeList = new ArrayList<Employee>();
		Employee employee;
		employee = new Employee("Neil Gohain","Product Manager");
		employeeList.add(employee);
		employee = new Employee("Satish Gupta","JavaScript Developer");
		employeeList.add(employee);
		
		employee = new Employee("Pranav Sharma","Software Engineer");
		employeeList.add(employee);
		employee = new Employee("Subham Gupta","Software Engineer");
		employeeList.add(employee);
		employee = new Employee("Ashwani Pandey","Senior Software Engineer");
		employeeList.add(employee);
		this.employeeRepository.deleteAll();
		this.employeeRepository.save(employeeList);
		
		List<EmployeeActivities> employeeActivitiesList = new ArrayList<EmployeeActivities>();
		EmployeeActivities employeeActivities;
		String empId;
		empId = this.employeeRepository.findByEmpName("Neil Gohain").getEmpId();
		employeeActivities = new EmployeeActivities("02-06-2017",0,119,empId);
		employeeActivitiesList.add(employeeActivities);
		empId = this.employeeRepository.findByEmpName("Neil Gohain").getEmpId();
		employeeActivities = new EmployeeActivities("02-06-2017",240,299,empId);
		employeeActivitiesList.add(employeeActivities);
		empId = this.employeeRepository.findByEmpName("Satish Gupta").getEmpId();
		employeeActivities = new EmployeeActivities("02-06-2017",180,239,empId);
		employeeActivitiesList.add(employeeActivities);
		this.employeeActivitiesRepository.deleteAll();
		this.employeeActivitiesRepository.save(employeeActivitiesList);

		
		
		
		
		

		
		
		
		
		
	}
	

	

}
