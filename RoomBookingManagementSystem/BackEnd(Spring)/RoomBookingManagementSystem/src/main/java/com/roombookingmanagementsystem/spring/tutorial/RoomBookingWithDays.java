package com.roombookingmanagementsystem.spring.tutorial;



import org.springframework.data.annotation.Id;

public class RoomBookingWithDays {
	
	
	@Id
	public String id;
	public int from;
	public int to;
	public String dateOfBooking;
	public int numberOfDays;
	public String roomId;
	public String organiserId;
	public boolean projectorEnabed;
	public boolean conferenceRoomEnabed;
	
	public RoomBookingWithDays()
	{
		
	}
	public RoomBookingWithDays(boolean projectorEnabled,boolean conferenceEnabled,int from, int to, String dateOfBooking, String roomId, String organiserId,
		int numberOfDays) {
		super();
		
		this.from = from;
		this.to = to;
		this.dateOfBooking = dateOfBooking;
		this.roomId = roomId;
		this.organiserId = organiserId;
		
		this.conferenceRoomEnabed =conferenceEnabled;
		this.conferenceRoomEnabed = conferenceEnabled;
		this.numberOfDays = numberOfDays;
	}
	
	public int getNumberOfDays() {
		return numberOfDays;
	}
	public void setNumberOfDays(int numberOfDays) {
		this.numberOfDays = numberOfDays;
	}
	public boolean isProjectorEnabed() {
		return projectorEnabed;
	}
	public void setProjectorEnabed(boolean projectorEnabed) {
		this.projectorEnabed = projectorEnabed;
	}
	public boolean isConferenceRoomEnabed() {
		return conferenceRoomEnabed;
	}
	public void setConferenceRoomEnabed(boolean conferenceRoomEnabed) {
		this.conferenceRoomEnabed = conferenceRoomEnabed;
	}
	public void setOrganiserId(String organiserId) {
		this.organiserId = organiserId;
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
	
	

}
