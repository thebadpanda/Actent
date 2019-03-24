import React from 'react';
import { Link } from 'react-router-dom';
import TextField from '@material-ui/core/TextField';
import Button from '@material-ui/core/Button';
import axios from 'axios';

export default class SignIn extends React.Component{

    constructor(){
        super();
        state = {
          email: undefined,
          password: undefined
        };
    };

    handleEmail = (event) => {
        this.setState({email: event.target.value})
    };

    handlePassword = (event) => {
        this.setState({ password: event.target.value})
    };

    sendData = (event) => {

        event.preventDefault();

        axios.get(`http://localhost/api/v1/users?email=${this.state.email}`)
            .then(res => {
                console.log(res.data);
            })
    };
    
    isValid = () => {
        if(this.state.email && this.state.email > 5){
            return true;
        }else {
            return false;
        }
    };

    render() {

        return(

            <div className="FormCenter">
                <form className="FormFields">
                    <div className="FormField">
                        <TextField
                            id="outlined-email-input"
                            label="Email"
                            className="FormField__Input"
                            type="email"
                            name="email"
                            autoComplete="email"
                            margin="normal"
                            variant="outlined"
                            onChange={this.handleEmail}
                        />
                    </div>
                    <div className="FormField">
                        <TextField
                            id="outlined-password-input"
                            label="Password"
                            className="FormField__Input"
                            type="password"
                            autoComplete="current-password"
                            margin="normal"
                            variant="outlined"
                            onChange={this.handlePassword}
                        />
                    </div>

                    <div className="FromField">
                        <Button className="FormField__Button" variant="contained" color="primary" disabled={!this.isValid()} onClick={this.sendData}>
                            Sign in
                        </Button>
                        <Link to="/sign-up" className="FormField__Link">Create an account</Link>
                    </div>

                </form>
            </div>

        );

    }

}