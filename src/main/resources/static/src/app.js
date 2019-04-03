import React from "react";
import ReactDOM from "react-dom";
import Profile from "./component/profile/Profile";
import MuiThemeProvider from '@material-ui/core/styles/MuiThemeProvider';
import {BrowserRouter as Router} from 'react-router-dom';
import Layout from './Layout';
import './styles/index.css';

ReactDOM.render(
    <Router>
        <MuiThemeProvider>
            <Layout/>
        </MuiThemeProvider>
    </Router>,
    window.document.getElementById('root'));
