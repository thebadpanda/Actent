import React from "react";
import ReactDOM from "react-dom";
import Hallo from "./component/Hallo";
import LoginForm from "./component/LoginForm";
import Profile from "./component/profile/Profile";
import MuiThemeProvider from 'material-ui/styles/MuiThemeProvider';
import {BrowserRouter as Router} from 'react-router-dom';
import {getMuiTheme} from "material-ui/styles";
import Link from "@material-ui/core/Link";

const muiTheme = getMuiTheme();
class App extends React.Component {
    render() {
        return (
                <div>
                    <Hallo/>
                    <LoginForm/>
                    <Profile/>
                    <Link to="/users/1" className="FormField__Link">Profile</Link>
                </div>
        );
    }
}

ReactDOM.render(
    <Router>
        <MuiThemeProvider>
            <App/>
        </MuiThemeProvider>
    </Router>,

    window.document.getElementById('root'));
