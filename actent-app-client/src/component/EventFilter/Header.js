import React from 'react';
import {
    MDBNavbar,
    MDBNavbarBrand,
    MDBNavbarNav,
    MDBNavbarToggler,
    MDBCollapse,
    MDBNavItem,
    MDBNavLink,
    MDBView,
    MDBMask,
} from 'mdbreact';
import { BrowserRouter as Router } from 'react-router-dom';
import HeaderInput from './HeaderInput';
import Menu from './Menu';

export default class Header extends React.Component {
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
            <div>
                <header>
                    <Menu />
                    <MDBView src='https://mdbootstrap.com/img/Photos/Others/img%20(40).jpg'>
                        <MDBMask overlay='black-strong' className='flex-center flex-column text-white text-center'>
                            <HeaderInput setTitle={this.props.setTitle} />
                        </MDBMask>
                    </MDBView>
                </header>
            </div>
        );
    }
}
