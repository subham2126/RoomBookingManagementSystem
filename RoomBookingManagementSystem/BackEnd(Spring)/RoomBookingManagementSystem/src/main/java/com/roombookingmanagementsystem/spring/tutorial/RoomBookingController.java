package com.roombookingmanagementsystem.spring.tutorial;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RoomBookingController {
 
	@RequestMapping(value = "/showrooms")
	public String index() {
		return "index.html";
	}
	@RequestMapping(value="/newroomadd")
	public String addNewRoom()
	{
	      return "addNewRoom.html";
	}

}