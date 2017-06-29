package com.roombookingmanagementsystem.spring.tutorial;



import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface BookingRepository extends MongoRepository<Booking, String> {
	
	
	public List<Booking> findByDateOfBooking(String date);
	public List<Booking> findByDateOfBookingAndRoomId(String date,String roomId); 
	
	
	

}



