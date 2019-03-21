import React from 'react'
import Button from '@material-ui/core/Button';

const style = {
    display: "flex",
    backgroundColor: "gray",
    flexDirection: "column",
    justifyContent: "center"
};

export default class LoginForm extends React.Component{

    state = {
        email: undefined,
        password: undefined
    };

    handleEmail = (event) => {

        this.setState({email: event.target.value});

    };

    handlePassword = (event) => {

        this.setState({password: event.target.value});

    };

    sendData = () => {

        alert(this.state.email + " " + this.state.password)
    };

    isValid = () => {

        if (this.state.email && this.state.email.length > 5){
            return true;
        }else{
            return false;
        }
    };

    render(){
        return(
                <div>
                    <div style={style}>
                        <input type="text" placeholder="email" onChange={this.handleEmail}/>
                        <input type="password" placeholder="password" onChange={this.handlePassword}/>
                        <Button onClick={this.sendData} disabled={!this.isValid()}>Login3</Button>
                    </div>
                </div>
        );
    }
}