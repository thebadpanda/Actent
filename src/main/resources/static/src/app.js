import React from "react";
import ReactDOM from "react-dom";
import './styles/index.css';
import '@fortawesome/fontawesome-free/css/all.min.css';
import 'bootstrap-css-only/css/bootstrap.min.css';
import 'mdbreact/dist/css/mdb.css';
import Layout from './Layout';
import {BrowserRouter as Router} from 'react-router-dom';

ReactDOM.render(
    <Router>
        <Layout/>
    </Router>,
    document.getElementById('root'));
