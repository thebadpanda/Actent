import React from "react";
import ReactDOM from "react-dom";
import './styles/index.css';
import Layout from './Layout';
import {BrowserRouter as Router} from 'react-router-dom';

ReactDOM.render(
    <Router>
        <Layout/>
    </Router>,
    document.getElementById('root'));
