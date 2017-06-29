import React, {Component} from 'react';
import ReactDOM from 'react-dom';
import $ from "jquery";



     
function xyz()
{
  console.log("hello");
}



export default class NewBooking extends Component {

  componentDidMount() {
         if(window.componentHandler)
window.componentHandler.upgradeAllRegistered();


     }


  

  


  submitBooking() {




    
    var EmployeeIds = document.getElementById('employeesid').value;
    console.log(EmployeeIds);
    var temp="";
    var list=[];
    for(var i=0;i<EmployeeIds.length;i++)
    {
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
    var str = document.getElementById('from').value.toString();
    
    var from = ((str.charCodeAt(0)-48)*10 + (str.charCodeAt(1)-48))*60 + ((str.charCodeAt(3)-48)*10 + (str.charCodeAt(4)-48));
    str = document.getElementById('to').value
    var to = ((str.charCodeAt(0)-48)*10 + (str.charCodeAt(1)-48))*60 + ((str.charCodeAt(3)-48)*10 + (str.charCodeAt(4)-48));
    


    var booking={    
    "to": to,
    
    "from":from,
    "dateOfBooking": document.getElementById('dateofbooking').value,
    "roomId": document.getElementById('roomid').value,
    "empId" : (list),
    "organiserId" : document.getElementById('organiserId').value

  };  
  

    
    console.log(booking);
    var x;
     
   $.ajax({
            type : "POST",
            contentType : 'application/json; charset=utf-8',
            dataType : 'json',
            url : "http://localhost:8080/room/newbooking",
            data : JSON.stringify(booking),
            success : function(result) {
              xyz();
            
          
              

            }
            
    });

        
  };




  render() {
    return (

<section>
      <div className="mdl-layout--fixed-header ">
       <header className="mdl-layout__header ">
          <div className="mdl-layout__header-row">
			<span className="mdl-layout-title "> New Booking Details</span>
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
    <label className="mdl-textfield__label" >To</label>

  </div>
    <div className="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
    <input className="mdl-textfield__input" type="text" id="roomid"/>
    <label className="mdl-textfield__label" >RoomID</label>
    </div>
      <div className="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
    <input className="mdl-textfield__input" type="text" id="dateofbooking"/>
    <label className="mdl-textfield__label" >Date of Booking (DD-MM-YYYY)</label>

  </div>
        <div className="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
    <input className="mdl-textfield__input" type="text" id="organiserId"/>
    <label className="mdl-textfield__label" > Organiser Id</label>

  </div>
      <div className="mdl-textfield mdl-js-textfield">
    <input className="mdl-textfield__input" type="text" id="employeesid"/>
    <label className="mdl-textfield__label" >Enter Employee ID Seperated by Space</label>
  </div>
  <button className="mdl-button mdl-js-button mdl-button--raised mdl-button--colored "  onClick ={this.submitBooking}>
  Submit
</button>





</section>







    );
  }
}

