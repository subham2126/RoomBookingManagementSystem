import React, {Component} from 'react';

import $ from "jquery";

export default class GetBookersList extends Component {
  constructor(props){
  super(props);
  }




  render() {
    return (
      <GetList/>
    );
  }}





var GetList = React.createClass({

    getInitialState: function () {

    return {
      bookerList:[]
    }

  },


    componentDidMount() {
         if(window.componentHandler)
window.componentHandler.upgradeAllRegistered();

     },

     doit :function(result)
     {
      this.setState({bookerList: result});
      console.log(this.state.bookerList)
     },

     




  submitQuery()
  {
    console.log("reached");
    var str = document.getElementById('from').value.toString();
    
    var from = ((str.charCodeAt(0)-48)*10 + (str.charCodeAt(1)-48))*60 + ((str.charCodeAt(3)-48)*10 + (str.charCodeAt(4)-48));
    str = document.getElementById('to').value
    var to = ((str.charCodeAt(0)-48)*10 + (str.charCodeAt(1)-48))*60 + ((str.charCodeAt(3)-48)*10 + (str.charCodeAt(4)-48));
    

    

    var booking={    
    "from":from,
    "to":to,
    "projectorEnabled" : document.getElementById('projectorNeeded').checked,
    "conferenceRoomEnabled" : document.getElementById('conferenceRoomNeeded').checked,

    "dateOfBooking": document.getElementById('dateofbooking').value,

  

  };

  

    
 
    $.ajax({
            type : "POST",
            contentType : 'application/json; charset=utf-8',
            dataType : 'json',
            url : "http://localhost:8080/room/getbookersdetails",
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
    <input className="mdl-textfield__input" type="text" id="from"/>
    <label className="mdl-textfield__label">From</label>
  </div>
  <div className="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
    <input className="mdl-textfield__input" type="text" id="to"/>
    <label className="mdl-textfield__label">To</label>
  </div>    
  

      <div className="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
    <input className="mdl-textfield__input" type="text" id="dateofbooking"/>
    <label className="mdl-textfield__label" >Date of Booking (DD-MM-YYYY)</label>

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
 <PrintSlots slots = {this.state.bookerList}/>
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
    s.third = convertTime(s.third);
    s.fourth = convertTime(s.fourth);
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
        Organiser : {this.props.slot.first}
  </div>
  <div className="mdl-card__supporting-text">
        Room No :   {this.props.slot.second}
  </div>
    <div className="mdl-card__supporting-text">
        Start :   {this.props.slot.third}
  </div>
    <div className="mdl-card__supporting-text">
        End :   {this.props.slot.fourth}
  </div>
</div>


       
      </div>
    );
  }
});

