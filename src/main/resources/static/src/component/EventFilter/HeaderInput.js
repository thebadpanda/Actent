import React, {Component} from "react";
import { MDBCol } from "mdbreact";
import axios from 'axios';

const style ={
    height: 'calc(1.5em + 1.75rem + 2px)'
};
export default class SearchField extends Component{

    state = {
        findEvent: '',
      }

    handleChange = event => {
        this.setState({ findEvent: event.target.value });
    }

    handleSubmit = event => {
        event.preventDefault();

        const serchEvent = {
            findEvent: this.state.findEvent
          };

          axios.get(`http://localhost:8080/api/v1/events/title/Super%20party`)
          .then(res => {
            console.log(res);
            console.log(res.data);
          }).catch(error => {
            console.log(error.response)
        });
      } 

    render(){
        return (
            <MDBCol md="6">
            <form onSubmit={this.handleSubmit}>
                <input style={style} name ="findEvent" className="form-control form-control-lg" type="text" placeholder="Find an event" aria-label="Search" />
            </form>
            </MDBCol>
        );
    }
}
