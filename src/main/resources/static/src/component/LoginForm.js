import React from "react";
import TextField from '@material-ui/core/TextField';
import Button from '@material-ui/core/Button';
import axios from "axios";
import '../styles/login.css';

export default class Login extends React.Component{

    state = {
        email: undefined,
        password: undefined,
        user: undefined
    };

    handleEmail = (event) => {
        this.setState({email: event.target.value})
    };

    handlePassword = (event) => {
        this.setState({password: event.target.value})
    };

    sendData = (event) => {
        event.preventDefault();

        console.log(this.state.email + " " + this.state.password);

        axios.get('http://localhost/api/v1/users?email='+this.state.email)
            .then(res => {
                console.log(res);
                console.log(res.data);
                const user = res.data;
                this.setState( {user} );
            })
    };

    isValid = () => {
        if(this.state.email && this.state.email.length > 5){
            return true;
        }else{
            return false;
        }
    };

    render(){
        return(
            <div className="container">
                <div className="loginForm">
                    <div className="signIn">Sign in</div>
                    <div className="fields">
                        <TextField
                            id="outlined-email-input"
                            label="Email"
                            className="textField"
                            type="email"
                            name="email"
                            autoComplete="email"
                            margin="normal"
                            variant="outlined"
                            onChange={this.handleEmail}
                        />
                        <TextField
                            id="outlined-password-input"
                            label="Password"
                            className="textField"
                            type="password"
                            autoComplete="current-password"
                            margin="normal"
                            variant="outlined"
                            onChange={this.handlePassword}
                        />
                    </div>
                    <Button className="loginButton" variant="contained" color="primary" disabled={!this.isValid()} onClick={this.sendData}>
                        Sign in
                    </Button>
                </div>
            </div>
        );
    }
}