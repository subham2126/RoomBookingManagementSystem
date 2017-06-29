package com.roombookingmanagementsystem.spring.tutorial;

import java.util.List;

import org.springframework.data.annotation.Id;

public class RoomBookingByDuration {
	
	
	


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public String getDateOfBooking() {
		return dateOfBooking;
	}

	public void setDateOfBooking(String dateOfBooking) {
		this.dateOfBooking = dateOfBooking;
	}

	public boolean isProjectorEnabled() {
		return projectorEnabled;
	}

	public void setProjectorEnabled(boolean projectorEnabled) {
		this.projectorEnabled = projectorEnabled;
	}

	
	

	@Id
	public String id;
	public int duration;
	public String roomId;
	public boolean projectorEnabled;
	public List<String> empId;
	
	public List<String> getEmpId() {
		return empId;
	}

	public void setEmpId(List<String> empId) {
		this.empId = empId;
	}




	public String dateOfBooking;
	
	
	
	public RoomBookingByDuration()
	{
		
	}
	
	public RoomBookingByDuration(String roomId, int duration, String dateOfBooking, List<String> empName) {
		super();
		this.projectorEnabled = true;
	    this.roomId = roomId;
		this.duration = duration;
		this.dateOfBooking = dateOfBooking;
		
	}
}
