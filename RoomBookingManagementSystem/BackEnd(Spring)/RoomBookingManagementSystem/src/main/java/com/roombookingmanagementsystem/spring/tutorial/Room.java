package com.roombookingmanagementsystem.spring.tutorial;



public class Room {
	
	private int roomNo;
	private String roomId;
	private boolean conferenceCallingAvailable;
	private boolean projectorAvailable;
	public static int roomCount=1;

	private int capacity;
	
	public Room()
	{
		
	}
	
	
	
	public Room(int roomNo, boolean conferenceCallingAvailable , boolean projectorAvailable,int capacity)
	
    {
		this.roomNo=roomNo;
		this.conferenceCallingAvailable = conferenceCallingAvailable;
		this.projectorAvailable = projectorAvailable;
		
		
		String roomId="room";
		int digit = (int) (Math.log10(roomCount) + 1);
		for(int i=digit;i<3;i++)
			roomId = roomId+'0';
		roomId+=roomCount;
		this.roomId = roomId;
		roomCount++;
		
		
		this.setRoomId(roomId);
		this.setCapacity(capacity);
		
		
		
		
		
		
	}
	public int getRoomNo() {
		return roomNo;
	}
	
	public void setRoomNo(int roomNo) {
		this.roomNo = roomNo;
	}
	public boolean isConferenceCallingAvailable() {
		return conferenceCallingAvailable;
	}
	public void setConferenceCallingAvailable(boolean conferenceCallingAvailable) {
		this.conferenceCallingAvailable = conferenceCallingAvailable;
	}
	public boolean isProjectorAvailable() {
		return projectorAvailable;
	}
	public void setProjectorAvailable(boolean projectorAvailable) {
		this.projectorAvailable = projectorAvailable;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public String getRoomId() {
		return roomId;
	}
	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}
	
	

}
