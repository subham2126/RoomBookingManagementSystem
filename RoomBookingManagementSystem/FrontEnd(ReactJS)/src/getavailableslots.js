import React, {Component} from 'react';

import $ from "jquery";

export default class getAvailabeSlots extends Component {
  constructor(props){
  super(props);
  

}




  render() {
    return (
      <GetSlots/>
    );
  }


}





var GetSlots = React.createClass({

    getInitialState: function () {

    return {
      timeSlots:[]
    }

  },


    componentDidMount() {
         if(window.componentHandler)
window.componentHandler.upgradeAllRegistered();

     },

     doit :function(result)
     {
      this.setState({timeSlots: result});
      console.log(this.state.timeSlots)
     },

     




  submitQuery()
  {
    console.log("reached");
    
    var EmployeeIds = document.getElementById('employeesid').value;
    console.log(EmployeeIds);
    var temp="";
    var list=[];
    for(var i=0;i<EmployeeIds.length;i++){
      if(EmployeeIds[i]===' ')
      {
       list.push(temp);
       temp = "";
      }
      else
      temp+=EmployeeIds[i];

    }
    list.push(temp);
    console.log(list);
    var booking={    
    "duration": parseInt(document.getElementById('duration').value ,10),
    "projectorEnabled" : document.getElementById('projectorNeeded').checked,
    "conferenceRoomEnabled" : document.getElementById('conferenceRoomNeeded').checked,

    "dateOfBooking": document.getElementById('dateofbooking').value,
    "roomId": document.getElementById('roomid').value,
    "empId" : (list),
  

  };

  

    
 
    $.ajax({
            type : "POST",
            contentType : 'application/json; charset=utf-8',
            dataType : 'json',
            url : "http://localhost:8080/room/listofslots",
            data : JSON.stringify(booking),
            success : function(result) {
              console.log(result);
           this.doit(result);

              


             
          
              

            }.bind(this)
        });
                    
            
  

  },



  render: function() {
    return (
<div>
<section>
      <div className="mdl-layout--fixed-header ">
       <header className="mdl-layout__header ">
          <div className="mdl-layout__header-row">
			<span className="mdl-layout-title "> Enter the Details</span>
            <div className="mdl-layout-spacer"></div>
         </div>
         <main className="mdl-layout__content">
        	
     	 </main>
      </header>
    </div>
  <div className="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
    <input className="mdl-textfield__input" type="text" id="duration"/>
    <label className="mdl-textfield__label">Duration in Minutes</label>
  </div>
  
    <div className="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
    <input className="mdl-textfield__input" type="text" id="roomid"/>
    <label className="mdl-textfield__label" >RoomID</label>
    </div>
      <div className="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
    <input className="mdl-textfield__input" type="text" id="dateofbooking"/>
    <label className="mdl-textfield__label" >Date of Booking (DD-MM-YYYY)</label>

  </div>
      <div className="mdl-textfield mdl-js-textfield">
    <input className="mdl-textfield__input" type="text" id="employeesid"/>
    <label className="mdl-textfield__label">Enter Employee ID Seperated by Space</label>
  </div>
  <div > Projector Needed
  <label className="mdl-radio mdl-js-radio mdl-js-ripple-effect" >
  <input type="radio" id="projectorNeeded" className="mdl-radio__button" name="isProjectorNeeded" value="0" />
  <span className="mdl-radio__label">Yes</span>
</label>
<label className="mdl-radio mdl-js-radio mdl-js-ripple-effect" >
  <input type="radio" id="projectorNotNeeded" className="mdl-radio__button" name="isProjectorNeeded" value="1"/>
  <span className="mdl-radio__label">No</span>
</label>
</div>
  <div > Conference Room Needed
  <label className="mdl-radio mdl-js-radio mdl-js-ripple-effect" >
  <input type="radio" id="conferenceRoomNeeded" className="mdl-radio__button" name="isConferenceRoomNeeded" value="0" />
  <span className="mdl-radio__label">Yes</span>
</label>
<label className="mdl-radio mdl-js-radio mdl-js-ripple-effect" >
  <input type="radio" id="conferenceRoomNotNeeded" className="mdl-radio__button" name="isConferenceRoomNeeded" value="1"/>
  <span className="mdl-radio__label">No</span>
</label>
</div>

  <button className="mdl-button mdl-js-button mdl-button--raised mdl-button--colored" onClick ={this.submitQuery}>
  Submit
</button>




</section>
      <div className="mdl-layout--fixed-header ">
       <header className="mdl-layout__header ">
          <div className="mdl-layout__header-row">
			<span className="mdl-layout-title "> Available Slots</span>
            <div className="mdl-layout-spacer"></div>
         </div>
         <main className="mdl-layout__content">
        	
     	 </main>
      </header>
    </div>
 <PrintSlots slots = {this.state.timeSlots}/>
</div>

    );
  }

});

var PrintSlots= React.createClass({
	render : function(){
      var convertTime = function(t)
  {
     var hh = parseInt(t/60);
     var mm = t-hh*60;
     if(hh<=9)
     hh='0'+hh;
     if(mm<=9)
     mm='0'+mm;
     return hh+':'+mm;
  }

	var slots = this.props.slots.map(function(s,index){
    s.first =convertTime(s.first);
    s.second =convertTime(s.second);
	return (<SlotItem key={index} slot={s}/>);

	});

	return (<div>
              {slots}
	        </div>
	);

	
	
	}
});


var SlotItem = React.createClass({
  render: function(){
    return (
      <div>
        
        <div className="mdl-card mdl-shadow--4dp">

  <div className="mdl-card__supporting-text">
        Start : {this.props.slot.first}
  </div>
  <div className="mdl-card__supporting-text">
        End :   {this.props.slot.second}
  </div>
</div>


       
      </div>
    );
  }
});

