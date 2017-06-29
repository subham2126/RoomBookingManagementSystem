import React, { Component } from 'react';
import ReactDOM from 'react-dom';
import $ from "jquery";
export default class AddRoom extends Component {
  componentDidMount() {
         if(window.componentHandler)
window.componentHandler.upgradeAllRegistered();

     };
  render() {
    return (
       <AddRooms />
    );
  }
}

var AddRooms = React.createClass({

    



  getInitialState: function () {

    return {
      isConferenceCallingNeeded: false,
      isProjectorNeeded: false
    }

  },
  showrooms: function () {
    console.log("go");
  },



  submitData: function () {


    var room = {
      "roomNo": this.state.roomNo,

      "conferenceCallingAvailable": this.state.isConferenceRoomNeeded,
      "projectorAvailable": this.state.isProjectorNeeded,
      "capacity": this.state.capacity
    };

    console.log(room);


    $.ajax({
      type: "POST",
      contentType: 'application/json; charset=utf-8',
      dataType: 'json',
      url: "http://localhost:8080/room/addnewroom",
      data: JSON.stringify(room),
      success: function (result) {
      }
    });


  },
  handleChange(event) {
    console.log(event.target.id);
    if (event.target.id === "roomNo")
      this.setState({ roomNo: event.target.value });
    else if (event.target.id === "capacity")
      this.setState({ capacity: event.target.value });
    else {
      if (event.target.id === "isProjectorNeeded") {
        this.setState({ isProjectorNeeded: !this.state.isProjectorNeeded });


      }
      else
        this.setState({ isConferenceRoomNeeded: !this.state.isConferenceRoomNeeded });




    }





  },

  render: function () {
    return (
      <div>
        <div className="centeritems mdl-grid">
          <div className="mdl-layout-spacer"></div>
          <div className="mdl-cell mdl-cell--4-col">
            <div className="demo-card-wide mdl-card mdl-shadow--2dp ">

              <section>

                <div className="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                  <input className="mdl-textfield__input" type="text" id="roomNo" value={this.state.roomNo} onChange={this.handleChange} />
                  <label className="mdl-textfield__label">Room Number</label>
                </div>
                <div className="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                  <input className="mdl-textfield__input" type="text" pattern="[1-9]*([0-9])?" id="capacity" value={this.state.capacity} onChange={this.handleChange} />
                  <label className="mdl-textfield__label">Capacity</label>
                  <span className="mdl-textfield__error">Input is not a number!</span>

                </div>
                <label className="mdl-switch mdl-js-switch mdl-js-ripple-effect" > &nbsp;&nbsp;&nbsp; Projector Available
  <input type="checkbox" id="isProjectorNeeded" className="mdl-switch__input" value={this.state.isProjectorNeeded} onChange={this.handleChange} />
                  <span className="mdl-switch__label"></span>
                </label>
                <label className="mdl-switch mdl-js-switch mdl-js-ripple-effect" >  &nbsp;&nbsp;&nbsp;  Conference Room Available
  <input type="checkbox" id="isConferenceRoomNeeded" className="mdl-switch__input" value={this.state.isConferenceRoomNeeded} onChange={this.handleChange} />
                  <span className="mdl-switch__label"></span>
                </label>



                <button className="mdl-button mdl-js-button mdl-button--raised mdl-button--colored" onClick={this.submitData} >
                  ADD ROOM
</button>

                <button className="mdl-button mdl-js-button mdl-button--raised mdl-button--colored" onClick={this.showrooms} >
                  Show Rooms
</button>


              </section>
            </div>
          </div>
          <div className="mdl-layout-spacer"></div>
        </div>
      </div>
    );
  }

});