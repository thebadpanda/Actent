import { Component } from 'react';
import { NavLink } from 'react-router-dom';
import {
    MDBNavbar,
    MDBNavbarBrand,
    MDBNavbarNav,
    MDBNavItem,
    MDBNavLink,
    MDBNavbarToggler,
    MDBCollapse,
    MDBDropdown,
    MDBDropdownToggle,
    MDBDropdownMenu,
    MDBDropdownItem,
    MDBIcon,
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
            <MDBNavbar color='blue' dark expand='md'>
                <NavLink to='/'>
                    <MDBNavbarBrand>
                        <strong className='white-text'>Actent</strong>
                    </MDBNavbarBrand>
                </NavLink>
                <MDBNavbarToggler onClick={this.toggleCollapse} />
                <MDBCollapse id='navbarCollapse3' isOpen={this.state.isOpen} navbar>
                    <MDBNavbarNav left>
                        <MDBNavItem>
                            <MDBNavLink to='#!'>Features</MDBNavLink>
                        </MDBNavItem>
                        <MDBNavItem>
                            <MDBNavLink to='#!'>Pricing</MDBNavLink>
                        </MDBNavItem>
                    </MDBNavbarNav>
                    <MDBNavbarNav right>
                        <MDBNavItem>
                            <MDBDropdown>
                                <MDBDropdownToggle nav caret>
                                    <MDBIcon icon='user' />
                                </MDBDropdownToggle>
                                <MDBDropdownMenu className='dropdown-default' right>
                                    <MDBDropdownItem href='/auth/sign-in'>Sign In</MDBDropdownItem>
                                    <MDBDropdownItem href='/auth/sign-up'>Sign Up</MDBDropdownItem>
                                    <MDBDropdownItem href='/profile'>Profile</MDBDropdownItem>
                                </MDBDropdownMenu>
                            </MDBDropdown>
                        </MDBNavItem>
                    </MDBNavbarNav>
                </MDBCollapse>
            </MDBNavbar>
        );
    }
}
