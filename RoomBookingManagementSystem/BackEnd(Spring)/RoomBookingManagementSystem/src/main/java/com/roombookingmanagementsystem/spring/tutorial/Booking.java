package com.roombookingmanagementsystem.spring.tutorial;

import java.util.List;

import org.springframework.data.annotation.Id;

public class Booking {
	
	
	@Id
	public String id;
	public int from;
	public int to;
	public String dateOfBooking;
	public String roomId;
	public String organiserId;
	public boolean projectorEnabled;
	public boolean conferenceRoomEnabled;
		public List<String> empId;
	public boolean isProjectorEnabled() {
		return projectorEnabled;
	}
	public void setProjectorEnabled(boolean projectorEnabled) {
		this.projectorEnabled = projectorEnabled;
	}
	public boolean isConferenceRoomEnabled() {
		return conferenceRoomEnabled;
	}
	public void setConferenceRoomEnalbed(boolean conferenceRoomEnalbed) {
		this.conferenceRoomEnabled = conferenceRoomEnalbed;
	}
		public void setOrganiserId(String organiserId) {
		this.organiserId = organiserId;
	}
	public Booking()
	{
		
	}
	public Booking(boolean projectorEnabled,boolean conferenceEnabled,int from, int to, String dateOfBooking, String roomId, String organiserId,
		List<String> empId ) {
		super();
		
		this.from = from;
		this.to = to;
		this.dateOfBooking = dateOfBooking;
		this.roomId = roomId;
		this.organiserId = organiserId;
		this.empId = empId;
		this.conferenceRoomEnabled =conferenceEnabled;
		this.conferenceRoomEnabled = conferenceEnabled;
		this.setOrganiserId(organiserId);
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getFrom() {
		return from;
	}
	public void setFrom(int from) {
		this.from = from;
	}
	public int getTo() {
		return to;
	}
	public void setTo(int to) {
		this.to = to;
	}
	public String getDateOfBooking() {
		return dateOfBooking;
	}
	public void setDateOfBooking(String dateOfBooking) {
		this.dateOfBooking = dateOfBooking;
	}
	public String getRoomId() {
		return roomId;
	}
	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}
	public String getOrganiserId() {
		return organiserId;
	}
	public void setOrganiser(String organiserId) {
		this.organiserId = organiserId;
	}
	public List<String> getEmpId() {
		return empId;
	}
	public void setEmpId(List<String> empId) {
		this.empId = empId;
	}
	

}
