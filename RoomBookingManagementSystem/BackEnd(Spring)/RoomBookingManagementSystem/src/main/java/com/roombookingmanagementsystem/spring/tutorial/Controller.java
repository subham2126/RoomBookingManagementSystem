package com.roombookingmanagementsystem.spring.tutorial;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/room")
@CrossOrigin(origins = "http://localhost:3000")
public class Controller {
	
	// Pair Containing two integer Variable 
	
	public class Pair
	{
		private int first ,second;
		public Pair(int first,int second)
		{
			this.setFirst(first);
			this.setSecond(second);
		}
		public int getFirst() {
			return first;
		}
		public void setFirst(int first) {
			this.first = first;
		}
		public int getSecond() {
			return second;
		}
		public void setSecond(int second) {
			this.second = second;
		}
		
		
	}
	public class RoomAndList
	{
		
		//Class Containing a String for roomId and List of Pair Containing Start and End Time of Vacant Slots
		public RoomAndList(String roomId, List<Pair> availablity) {
			super();
			this.roomId = roomId;
			this.availablity = availablity;
		}
		private String roomId;
		private List<Pair> availablity;
		public String getRoomId() {
			return roomId;
		}
		public void setRoomId(String roomId) {
			this.roomId = roomId;
		}
		public List<Pair> getAvailablity() {
			return availablity;
		}
		public void setAvailablity(List<Pair> availablity) {
			this.availablity = availablity;
		}
		
	}
	
	
	private BookingRepository bookingRepository;
	private EmployeeRepository employeeRepository;
	private EmployeeActivitiesRepository employeeActivitiesRepository;
	private RoomRepository roomRepository;
	 
	public Controller(BookingRepository bookingRepository,EmployeeRepository employeeRepository,EmployeeActivitiesRepository employeeActivitiesRepository,RoomRepository roomRepository)
	{
		this.bookingRepository= bookingRepository;
		this.employeeRepository = employeeRepository;
		this.employeeActivitiesRepository = employeeActivitiesRepository;
		this.roomRepository=roomRepository;
	}
	//gets all the bookings
	 
	@GetMapping("/allbooking")
	public List<Booking> getallBooking()
	{
		List<Booking> roomBookingList = this.bookingRepository.findAll();
		return roomBookingList;
		
	}
	//gets all the employee list
	@GetMapping("/allemployee")
	public List<Employee> getallEmployees()
	{
		List<Employee> employeeList= this.employeeRepository.findAll();
		return employeeList;
		
	}
	// gets all the rooms available
	 @CrossOrigin(origins = "http://localhost:3000")
     @GetMapping("/allrooms")
	public List<Room> getallRooms()
	{
		List<Room> roomList= this.roomRepository.findAll();
		return roomList;
		
	}
	// gets all Employees Activities 
	@GetMapping("/allemployeeactivities") //All Employees Activities
	public List<EmployeeActivities> getallEmployeeActivities()
	{
		List<EmployeeActivities> employeeActivitiesList= this.employeeActivitiesRepository.findAll();
		return employeeActivitiesList;
		
	}
	
	 // tells for a particular roomId and date , the list of slots when can assign which is greater than or equal to duration 
	@GetMapping("/{roomId}/{date}/{duration}")   
	List<Pair> getPossibleList(@PathVariable("duration") int duration, @PathVariable("date") String date,@PathVariable("roomId") String roomId)
	{
		//System.out.println(duration+" "+date +" "+roomId);
		List<Booking> roomBookingList = this.bookingRepository.findByDateOfBookingAndRoomId(date,roomId);
		boolean marked[]= new boolean[(24*60)+1];
		List<Pair> vacantTimes = new ArrayList<Pair>();
		

		
		for(int i=0;i<roomBookingList.size();i++)
		{
			int start = roomBookingList.get(i).from;
			int end = roomBookingList.get(i).to;
			for(int j=start;j<end;j++)
				marked[j]=true;
			
		}
	
		
		
		
		int Start=0,End;
		marked[1440]=true;
		
		for(int i=1;i<24*60;i++) 
		{
			if(marked[i]==false && marked[i-1]==true)
				Start=i+1;
			if(marked[i]==false && marked[i+1]==true)
			{
				End = i;
				
				int dif  =End - Start+1;
				if(dif>=duration)
				{
					vacantTimes.add(new Pair(Start,End));
				}

				
			}
				
				
		}
		
		
	return vacantTimes;
		
	}
	// for a particular date , gets all the rooms with vacant Slots , slots which will be greater than or equal to given duration
	@CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(method= RequestMethod.POST,value="/listofslots")
	List<Pair> getPossibleListWithAllEmployee(@RequestBody RoomBookingByDuration roomBookingByDuration)
	{
		List<String> idOfEmployees = roomBookingByDuration.getEmpId();
		int duration =roomBookingByDuration.getDuration();
		String date=roomBookingByDuration.getDateOfBooking();
		String roomId=roomBookingByDuration.getRoomId();
		List<Pair> vacantTimes = getPossibleList(duration,date,roomId);
		//return vacantTimes;
		
		boolean marked[] = new boolean[1441];
		for(int i=0;i<vacantTimes.size();i++)
		{
			int start = vacantTimes.get(i).first;
			int end  = vacantTimes.get(i).second;     // marked[j]=true represent it is available for booking
			for(int j=start;j<=end;j++)
				marked[j]=true;
			
		}
		for(int i=0;i<idOfEmployees.size();i++)
		{
		String eId = idOfEmployees.get(i);
		List<EmployeeActivities> employeeActivitesList = this.employeeActivitiesRepository.findByDateAndEId(date,eId);
		          for(int j=0;j<employeeActivitesList.size();j++)
		          {
		        	  int start = employeeActivitesList.get(j).start;
		        	  int end = employeeActivitesList.get(j).end;
		        	  for(int k = start;k<=end;k++)
		        		  marked[k]=false;
			
		          }
		}
		List<Pair> vacantTimesFinal =new ArrayList<Pair>();
		
		int Start=0,End;
		marked[1440]=false;
		
		for(int i=1;i<24*60;i++) 
		{
			if(marked[i]==true && marked[i-1]==false)
				Start=i;
			if(marked[i]==true && marked[i+1]==false)
			{
				End = i;
				
				int dif  =End - Start+1;
				if(dif>=duration)
				{
					vacantTimesFinal.add(new Pair(Start,End));
				}

				
			}
				
				
		}
		
		
		
		return vacantTimesFinal;
		
	}
	
	//validate if the roomBooking Object is valid or not 
	@CrossOrigin(origins = "http://localhost:3000")
	boolean isValidNewBooking(Booking roomBooking)
	{
		
		//if(this.roomRepository.findOne(roomBooking.getRoomId()) ==null)
			//return false;
		int duration = roomBooking.getTo() - roomBooking.getFrom() +1;
		
		List<Pair> allSlotsAvailable = getPossibleList(duration,roomBooking.getDateOfBooking(), roomBooking.getRoomId());
		boolean isPossible =false;
		for(int i=0;i<allSlotsAvailable.size();i++)
		{
			if(allSlotsAvailable.get(i).first<=roomBooking.from && allSlotsAvailable.get(i).second >=roomBooking.to)
				isPossible =true;
		}
		if(isPossible==false)
			return false; 
		
		List<Employee> listOfEmployeeId = this.employeeRepository.findAll();
		HashMap<String,Integer> map = new HashMap<String,Integer>();
		for(int i=0;i<listOfEmployeeId.size();i++)
		{
			map.put(listOfEmployeeId.get(i).getEmpId(),1);
		}
		for(int i=0;i<roomBooking.getEmpId().size();i++)
		{
			if(map.containsKey(roomBooking.getEmpId().get(i))==false)
					return false;
		}
		
		
		return true;
		
	}
	//stores the details of the new Booking
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(method= RequestMethod.POST,value="/newbooking")
	 String storeThedetailsOfNewBooking(@RequestBody Booking roomBooking)
    {
		
		if(isValidNewBooking(roomBooking)==false)
		return "404 Bad Request";
		Room room = this.roomRepository.findByRoomId(roomBooking.roomId);
        roomBooking.setConferenceRoomEnalbed(room.isConferenceCallingAvailable());
        roomBooking.setProjectorEnabled(room.isProjectorAvailable());
		
		this.bookingRepository.insert(roomBooking);
				
		for(int i=0;i<roomBooking.empId.size();i++)
		{
			System.out.println(i);
			this.employeeActivitiesRepository.insert(new EmployeeActivities(roomBooking.dateOfBooking, roomBooking.from, roomBooking.to, roomBooking.empId.get(i)));
			
		}
		
		return "New Booking Done ";
		
	}
	// Use Case  : Returns all the roomId and Slots of the Room which having conference Enabled and have slots of atleast duration  
	@SuppressWarnings("null")
	List<RoomAndList> checkForAllRooms(RoomBookingByDuration roomBookingByDuration)
    {
    	
    	List<Room> room = this.roomRepository.findAll();
    	List<RoomAndList> roomAndList = null;
    	for(int i=0;i<room.size();i++)
    	{
    		roomBookingByDuration.roomId = room.get(i).getRoomId();
    		if(room.get(i).isProjectorAvailable()==roomBookingByDuration.isProjectorEnabled());
    		{
    			
    		List<Pair> vacantTimes = getPossibleListWithAllEmployee(roomBookingByDuration);
    		roomAndList.add(new RoomAndList(roomBookingByDuration.roomId ,vacantTimes));
    		}
    		
    		
    		
    	}
    	return roomAndList;
    	
    	
    }
	
	
	// Add new Employee
	@RequestMapping(method= RequestMethod.POST,value="/addnewemployee")
	String addNewEmployee(@RequestBody Employee employee)
	{
		this.employeeRepository.insert(new Employee(employee.getEmpName(), employee.getEmpDesignation()));
		return "SUCCESS";
		
	}
	// Add new Room
	 @CrossOrigin(origins = "http://localhost:3000")
		@RequestMapping(method= RequestMethod.POST,value="/addnewroom")
		String addNewEmployee(@RequestBody Room room)
		{
			this.roomRepository.insert(new Room(room.getRoomNo(), room.isConferenceCallingAvailable(), room.isProjectorAvailable(), room.getCapacity()));
			return "SUCCESS";
			
		}
		
		
	// Use Case three 
	 
	 @CrossOrigin(origins = "http://localhost:3000")
	 @RequestMapping(method= RequestMethod.POST,value="/getbookersdetails")
	
	List<StringPair> getBookingIdOfParticularSlot(@RequestBody Booking getHistory)
	
	{
		 String date = getHistory.dateOfBooking;
		 int start = getHistory.from;
		 int end = getHistory.to;
		 boolean conferenceCallingRequired = getHistory.conferenceRoomEnabled;
		 boolean projectorRequired = getHistory.projectorEnabled;
		 System.out.println(date);
		
		List<Booking> bookingList = this.bookingRepository.findByDateOfBooking(date); 
		List<StringPair> bookingIdList = new ArrayList<StringPair>();
	
		for(int i=0;i<bookingList.size();i++)
		{
			
			if(bookingList.get(i).getFrom()  > end )
				continue;
			else if(bookingList.get(i).getTo()< start)
				continue;
			else if(bookingList.get(i).isConferenceRoomEnabled() ==conferenceCallingRequired && bookingList.get(i).isProjectorEnabled()==projectorRequired)
				bookingIdList.add(new StringPair(bookingList.get(i).getOrganiserId(),bookingList.get(i).getRoomId(),bookingList.get(i).getFrom(),bookingList.get(i).getTo()));
		}
		
		return bookingIdList;
		
	}
	// This Function takes startDate , durationOfSlot, NoOfDays and return a contigous slot i.e time will be same for all days
	public List<RoomAndList> bookingSlotsForDailyMeeting(RoomBookingWithDays roomBookingWithDays) throws ParseException
	{
		String dateNow =  roomBookingWithDays.getDateOfBooking();
		int numberOfDays = roomBookingWithDays.getNumberOfDays();
		List<Room> room = this.roomRepository.findAll();
		int duration = roomBookingWithDays.to - roomBookingWithDays.to+1;
		HashMap<String, int[]> hMap = new HashMap<String, int[]>();
		int  emptySlots[]= new int[1441];
		
		for(int i=0;i<room.size();i++)
		{
			hMap.put(room.get(i).getRoomId(), emptySlots);
		}

		for(int i=0;i<numberOfDays;i++)
		{
			for(int j=0;j<room.size();j++)
			{
				String roomId = room.get(j).getRoomId();
				List<Pair> listSlots  =  getPossibleList(duration, dateNow, roomId);
				for(int k = 0;k<listSlots.size();k++)
				{
					int start = listSlots.get(k).first;
					int end = listSlots.get(k).second;
					for(int x = start;x<=end;x++)
						hMap.get(roomId)[x] += 1;
				}
				
				
				
				
			}
			
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
			Calendar c = Calendar.getInstance();
			c.setTime(simpleDateFormat.parse(dateNow));
			c.add(Calendar.DATE, 1);  
			dateNow = simpleDateFormat.format(c.getTime());  
		}
		List<RoomAndList> vacantList = new ArrayList<RoomAndList>();
		for (HashMap.Entry<String, int[]> entry : hMap.entrySet())
		{
		     String roomId = entry.getKey();
		     int emptySlot[] = entry.getValue();
		     
		     List<Pair> tempList=new ArrayList<Pair>();
		     int Start=0,End;
			
				
			for(int i=1;i<24*60;i++) 
		   {
			if(emptySlot[i]==numberOfDays && emptySlot[i-1]!=numberOfDays)
					Start=i+1;
					if(emptySlot[i]==numberOfDays && emptySlot[i+1]!=numberOfDays)
					{
						End = i;
						
						int dif  =End - Start+1;
						if(dif>=duration)
						{
							tempList.add(new Pair(Start,End));
						}

						
					}
						
						
				}
			vacantList.add(new RoomAndList(roomId, tempList));
		     
		     
		     
		}
		return vacantList;
		
	
		
	}
	
		
	
	
	
	
	
	
	

}
