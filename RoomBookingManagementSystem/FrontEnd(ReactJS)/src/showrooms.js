import React, {Component} from 'react';
import ReactDOM from 'react-dom';
import Redirect from 'react-router-dom';
import AddRooms from './addrooms.js';
import {
  BrowserRouter as Router,
  Route,
  Link
} from 'react-router-dom';
import $ from "jquery";


export default class ShowRooms extends Component {
  render() {
    return (
        <ShowRoom/>
    );
  }
}


var ShowRoom = React.createClass({

    getInitialState : function()
    {
       return{
             searchResults:[]
          }

    },
    componentDidMount(){
    this.search('http://localhost:8080/room/allrooms');
    },


    showResults : function(response)
    {
        
        console.log(response);
        this.setState({
        searchResults : response

        })

    },
    search :function(URL)
    {
      
		console.log(URL); 

        $.ajax({
          type:"GET",
          dataType:'json',
          url:URL,
          success:function(response){
            this.showResults(response);
          }.bind(this)

        });

    },
    addroom : function()
   {
    
    




   },
	   render: function(){
        return (
   <div>
    <div className="mdl-layout--fixed-header">
       <header className="mdl-layout__header">
          <div className="mdl-layout__header-row">
			<span className="mdl-layout-title">RoomList</span>
            <div className="mdl-layout-spacer"></div>
         </div>
         <main className="mdl-layout__content">
        	
     	 </main>
      </header>
    </div>

   <Results searchResults={this.state.searchResults}/>


  </div>
        );
    }
    
});
var Results = React.createClass({
    render: function(result){
        var resultItems =  this.props.searchResults.map(function(result){
        console.log(result);
        if(result.conferenceCallingAvailable === true)
         result.conferenceCallingAvailable = "available";
       else
        result.conferenceCallingAvailable= "not available";
 
 		if(result.projectorAvailable ===true)
         result.projectorAvailable = "available";
       else
        result.projectorAvailable= "not available";

        return <ResultItem key = {result.roomId} result = {result} />

        });
        return(
            <ul>
                {resultItems}
            </ul>           
        );
    }
});
var ResultItem = React.createClass({
    render: function(){
        return <div>   
<div>
	<div className="demo-card-wide mdl-card mdl-shadow--2dp">
  <div className="mdl-card__title">
    <h2 className="mdl-card__title-text">Room No : {this.props.result.roomNo}</h2>
  </div>
  <div className="mdl-card__supporting-text">

    <div>Room Id : {this.props.result.roomId }</div>
    <div>Conference Calling : {this.props.result.conferenceCallingAvailable}</div>
    <div>projectorAvailable : {this.props.result.projectorAvailable}</div>

 
  </div>
  <div className="mdl-card__actions mdl-card--border">
    <a className="mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect">
           capacity : {this.props.result.capacity }

      
    </a>
  </div>
</div>

</div>


  	


        </div>
    }
});