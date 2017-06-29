import React, { Component } from 'react';
import {
  BrowserRouter as Router,
  Route,
  Link
} from 'react-router-dom'
import Redirect from 'react-router';
import Bookings from './booking';
import NewBooking from './newbooking'
import ShowRooms from './showrooms';
import AddRoom from './addrooms';
import getAvailableSlots from './getavailableslots';
import GetBookersList from './getbookerslist';


import logo from './logo.svg';
import './App.css';


class App extends Component {
  render() {
    return (
        <ParamsExample/>
    );
  }
}

const ParamsExample = () => (
  <Router>
    <div>
      
      <ul>
        <li><Link to="/newbooking">New Booking</Link></li>
        
        <li><Link to="/showrooms">Show Rooms</Link></li>
        <li><Link to="/addnewroom">Add New Room</Link></li>
        <li><Link to="/getavailableslots">Check Available Slots</Link></li>
        
        <li><Link to="/getbookerlist"> Check Bookers Details</Link></li> 
      
      </ul>
      
      <Route path="/newbooking" component={NewBooking}/>
      <Route path="/roomdetails" component={RoomDetails}/>
       <Route path="/showrooms" component={ShowRooms}/>
      <Route path="/addnewroom" component={AddRoom}/>
      <Route path ="/getavailableslots" component ={getAvailableSlots} />
      <Route path ="/getbookerlist" component = {GetBookersList} />
               
  


   
    </div>
  </Router>
)




const RoomDetails = React.createClass({
  render() {
    return <h3> Room Details</h3>
  }
})






export default App;
