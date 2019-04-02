import React from "react";
import ReactDOM from "react-dom";
import Profile from "./component/profile/Profile";
import MuiThemeProvider from 'material-ui/styles/MuiThemeProvider';
import {BrowserRouter as Router} from 'react-router-dom';
import Layout from './Layout';
import './styles/index.css';

// TODO: remove
class App extends React.Component {
    render() {
        return (
            <div>
                <Profile/>
            </div>
        );
    }
}

//TODO: remove <App/>
ReactDOM.render(
    <Router>
        <MuiThemeProvider>
            <Layout/>
            <App/>
        </MuiThemeProvider>
    </Router>,

    window.document.getElementById('root'));
