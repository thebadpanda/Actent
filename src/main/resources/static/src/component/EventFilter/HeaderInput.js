import React, {Component} from "react";
import { MDBCol } from "mdbreact";
import axios from 'axios';

const style ={
    height: 'calc(1.5em + 1.75rem + 2px)'
};
export default class HeaderInput extends Component{

    state = {
        findEvent: '',
      }

    handleChange = event => {
      this.setState({findEvent: event.target.value});

    }


    getData = () => {



          axios.get(`http://localhost:8080/api/v1/events/title/${this.state.findEvent}`)
          .then(res => {
            console.log(res);
            console.log(res.data);
          }).catch(error => {
            console.log(this.state.findEvent);
            console.log(error.response);
        });
      }
    enterPressed = (event) => {
      let code = event.keyCode || event.which;
      console.log(code);
      if(code === 13) { //13 is the enter keycode
        this.props.setFilter(this.state.findEvent);
        this.getData()//Do stuff in here
      } 
    }

      

    render(){
        return (
            <MDBCol md="6">

                <input style={style}
                 name ="findEvent" 
                 onChange={this.handleChange}
                 onKeyPress={this.enterPressed}
                   className="form-control form-control-lg" type="text" placeholder="Find an event" aria-label="Search" />

            </MDBCol>
        );
    }
}
