import React from 'react';
import {BrowserRouter as Router, Route, Link, NavLink, Switch, Redirect} from "react-router-dom";
import '../styles/loginRegistrationForm.css';
import SignIn from './pages/SignIn';
import SignUp from './pages/SignUp';

export default class SignInUp extends React.Component {
    render() {
        return (
                <div className="App">
                    <div className="App__Form">

                        <div className="FormTitle">
                            <NavLink to="/auth/sign-in" activeClassName="FormTitle__Link--Active"
                                     className="FormTitle__Link">Sign In</NavLink>
                            or <NavLink to="/auth/sign-up" activeClassName="FormTitle__Link--Active"
                                        className="FormTitle__Link">Sign Up</NavLink>
                        </div>

                        <Route path={`${this.props.match.path}/sign-up`} component={SignUp} />
                        <Route path={`${this.props.match.path}/sign-in`} component={SignIn} />
                    </div>
                </div>
        );
    }
}