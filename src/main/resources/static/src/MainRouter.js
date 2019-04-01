import React from 'react';
import Home from './component/Homepage';
import SignUpInForm from './component/SignUpInForm';
import {Route, Switch, Redirect} from "react-router-dom";

export default class MainRouter extends React.Component{

    render() {
        return(
            <main>
                <Switch>
                    <Route path='/home' component={Home} />
                    <Route path='/auth' component={SignUpInForm} />
                    <Redirect path='*' to='/home' />
                </Switch>
            </main>
        );
    }

}