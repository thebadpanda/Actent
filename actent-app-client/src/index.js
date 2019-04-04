import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
import * as serviceWorker from './serviceWorker';
import { BrowserRouter as Router } from 'react-router-dom';
import MuiThemeProvider from 'material-ui/styles/MuiThemeProvider';
// import createMuiTheme from 'material-ui/styles/createMuiTheme';

// const theme = createMuiTheme({
//     typography: {
//         useNextVariants: true,
//     },
// });

ReactDOM.render(
    <Router>
        <MuiThemeProvider>
            <App />
        </MuiThemeProvider>
    </Router>,
    document.getElementById('root'),
);

// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: https://bit.ly/CRA-PWA
serviceWorker.unregister();
