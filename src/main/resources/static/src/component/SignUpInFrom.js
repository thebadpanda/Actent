import React from 'react';
import {BrowserRouter as Router, Route, Link, NavLink, Switch, Redirect } from "react-router-dom";
import '../styles/loginRegistrationForm.css';
import SignIn from './pages/SignIn';
import SignUp from './pages/SignUp';
import LoginForm from './LoginForm';

export default class SignInUp extends Component {
    render() {
        return (
            <Router>
                <div className="App">
                    <div className="App__Aside"></div>
                    <div className="App__Form">

                        <div className="FormTitle">
                            <NavLink to="/sign-in" activeClassName="FormTitle__Link--Active"
                                     className="FormTitle__Link">Sign In</NavLink>
                            or <NavLink to="/sign-up" activeClassName="FormTitle__Link--Active"
                                        className="FormTitle__Link">Sign Up</NavLink>
                        </div>
                        <Switch>
                            <Route exact path="/" component={LoginForm}/>
                            <Route path="/sign-up" component={SignUp}/>
                            <Route path="/sign-in" component={SignIn}/>
                            <Redirect path="*" to="/sign-in" />
                        </Switch>
                    </div>
                </div>
            </Router>
        );
    }
}