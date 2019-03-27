import React from 'react';
import axios from 'axios';
import DatePicker from 'material-ui/DatePicker';
import apiUrl from './Profile.js';




export default class ProfileEdit extends React.Component{

    constructor(props){
        super(props);
        this.state = {
            userId: this.props.profileData.userId,
            firstName: this.props.profileData.firstName,
            lastName: this.props.profileData.lastName,
            phone: this.props.profileData.phone,
            login: this.props.profileData.login,
            address: this.props.profileData.address,
            birthDate: new Date(this.props.profileData.birthDate),
            bio: this.props.profileData.bio,
            interests: this.props.profileData.interests,
            avatar: this.props.profileData.avatar
        };
    }

    handleFirstName = (event) => this.setState({firstName: event.target.value});

    handleLastName = (event) => this.setState({lastName: event.target.value});

    handlePhone = (event) => this.setState({phone: event.target.value});

    handleLogin = (event) => this.setState({login: event.target.value});

    handleAddress = (event) => this.setState({address: event.target.value});

    handleBirthDate = (event, date) => this.setState({birthDate: date});

    handleBio = (event) => this.setState({bio: event.target.value});

    handleInterests = (event) => this.setState({interests: event.target.value});

    getBirthDate = () => {
        return this.state.birthDate.getFullYear() + '-' +
        this.state.birthDate.getMonth()+ '-' +
            this.state.birthDate.getDate();
    };
    saveUserSettings = () => {
        const url = apiUrl + /users/ + this.props.userId;

        axios.put( url, {
            firstName : this.state.firstName,
            lastName: this.state.lastName,
            login: this.state.login,
            address: this.state.address,
            birthDate: this.getBirthDate(),
            bio: this.state.bio,
            interests: this.state.interests,
        }).then(() => {
            this.props.onCloseClick();
        });

    };

    saveUserPhoto = () => {

    };

    isValidName = () => {
        if (this.state.name && this.state.name.length > 2) {
            return true;
        } else {
            return false;
        }
    };

    isValidEmail = () => {
        return !!this.state.email;
    };
}