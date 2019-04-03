import React from "react";
import {
  MDBNavbar,
  MDBNavbarBrand,
  MDBNavbarNav,
  MDBNavbarToggler,
  MDBCollapse,
  MDBNavItem,
  MDBNavLink,
  MDBView,
  MDBMask
} from "mdbreact";
import { BrowserRouter as Router } from "react-router-dom";
import HeaderInput from "./HeaderInput";

export default class Header extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      collapse: false,
      isWideEnough: false
    };
    this.onClick = this.onClick.bind(this);
  }

  onClick() {
    this.setState({
      collapse: !this.state.collapse
    });
  }

  render() {
    return (
      <div>
        <header>
          <Router>
            <MDBNavbar
              color="bg-primary"
              fixed="top"
              dark
              expand="md"
              scrolling
              transparent
            >
              <MDBNavbarBrand href="/">
                <strong>Navbar</strong>
              </MDBNavbarBrand>
              {!this.state.isWideEnough && (
                <MDBNavbarToggler onClick={this.onClick} />
              )}
              <MDBCollapse isOpen={this.state.collapse} navbar>
                <MDBNavbarNav left>
                  <MDBNavItem active>
                    <MDBNavLink to="#">Home</MDBNavLink>
                  </MDBNavItem>
                  <MDBNavItem>
                    <MDBNavLink to="#">Link</MDBNavLink>
                  </MDBNavItem>
                  <MDBNavItem>
                    <MDBNavLink to="#">Profile</MDBNavLink>
                  </MDBNavItem>
                </MDBNavbarNav>
              </MDBCollapse>
            </MDBNavbar>
          </Router>
          <MDBView src="https://mdbootstrap.com/img/Photos/Others/img%20(40).jpg">
            <MDBMask
              overlay="black-strong"
              className="flex-center flex-column text-white text-center"
            >
              <HeaderInput setTitle={this.props.setTitle} />
            </MDBMask>
          </MDBView>
        </header>
      </div>
    );
  }
}
