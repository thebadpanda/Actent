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
                        <MDBNavItem>
                            <MDBDropdown>
                                <MDBDropdownToggle nav caret>
                                    <div className='d-none d-md-inline'>Dropdown</div>
                                </MDBDropdownToggle>
                                <MDBDropdownMenu className='dropdown-default' right>
                                    <MDBDropdownItem href='#!'>Action</MDBDropdownItem>
                                    <MDBDropdownItem href='#!'>Another Action</MDBDropdownItem>
                                    <MDBDropdownItem href='#!'>Something else here</MDBDropdownItem>
                                    <MDBDropdownItem href='#!'>Something else here</MDBDropdownItem>
                                </MDBDropdownMenu>
                            </MDBDropdown>
                        </MDBNavItem>
                    </MDBNavbarNav>
                    <MDBNavbarNav right>
                        <MDBNavItem>
                            <MDBNavLink className='waves-effect waves-light' to='#!'>
                                <MDBIcon fab icon='twitter' />
                            </MDBNavLink>
                        </MDBNavItem>
                        <MDBNavItem>
                            <MDBNavLink className='waves-effect waves-light' to='#!'>
                                <MDBIcon fab icon='google-plus-g' />
                            </MDBNavLink>
                        </MDBNavItem>
                        <MDBNavItem>
                            <MDBDropdown>
                                <MDBDropdownToggle nav caret>
                                    <MDBIcon icon='user' />
                                </MDBDropdownToggle>
                                <MDBDropdownMenu className='dropdown-default' right>
                                    <MDBDropdownItem href='#!'>Action</MDBDropdownItem>
                                    <MDBDropdownItem href='#!'>Another Action</MDBDropdownItem>
                                    <MDBDropdownItem href='#!'>Something else here</MDBDropdownItem>
                                    <MDBDropdownItem href='#!'>Something else here</MDBDropdownItem>
                                </MDBDropdownMenu>
                            </MDBDropdown>
                        </MDBNavItem>
                    </MDBNavbarNav>
                </MDBCollapse>
            </MDBNavbar>
        );
    }
}
