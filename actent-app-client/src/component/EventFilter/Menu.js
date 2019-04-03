import { Component } from 'react';
import {
    MDBNavbar,
    MDBNavbarBrand,
    MDBNavbarNav,
    MDBNavbarToggler,
    MDBCollapse,
    MDBNavItem,
    MDBNavLink,
} from 'mdbreact';
import { BrowserRouter as Router } from 'react-router-dom';
import React from 'react';

export default class Menu extends Component {
    constructor(props) {
        super(props);
        this.state = {
            collapse: false,
            isWideEnough: false,
        };
        this.onClick = this.onClick.bind(this);
    }

    onClick() {
        this.setState({
            collapse: !this.state.collapse,
        });
    }

    render() {
        return (
            <Router>
                <MDBNavbar color='bg-primary' fixed='top' dark expand='md' scrolling transparent>
                    <MDBNavbarBrand href='/'>
                        <strong>Actent</strong>
                    </MDBNavbarBrand>
                    {!this.state.isWideEnough && <MDBNavbarToggler onClick={this.onClick} />}
                    <MDBCollapse isOpen={this.state.collapse} navbar>
                        <MDBNavbarNav left>
                            <MDBNavItem active>
                                <MDBNavLink to='#'>Home</MDBNavLink>
                            </MDBNavItem>
                            <MDBNavItem>
                                <MDBNavLink to='#'>Link</MDBNavLink>
                            </MDBNavItem>
                            <MDBNavItem>
                                <MDBNavLink to='#'>Profile</MDBNavLink>
                            </MDBNavItem>
                        </MDBNavbarNav>
                    </MDBCollapse>
                </MDBNavbar>
            </Router>
        );
    }
}
