import React from 'react'

const apiUrl = '/api/v1/';

export default class Profile extends React.Component{

    state={
        isEdit: false,
        isMyProfile: +this.props.match.params.profileId === getUserId(),
        firstName : undefined,
        lastName : undefined,
        phone : undefined,
        login : undefined,
        address : undefined,
        birthDate : new Date(),
        bio : undefined,
        interests : undefined

    };

    // TODO: unsafe !!
    componentWillReceiveProps(nextProps, nextContext) {
        //TODO: check if
        this.getProfile(+nextProps.props.match.params.profileId);
    }

    // TODO: unsafe !!
    componentWillMount() {
        this.getProfile(this.props.match.params.profileId)
    }


    handleFirstName = (event) => this.setState({firstName : event.target.value});

    handleLastName = (event) => this.setState({lastName : event.target.value});

    handlePhone = (event) => this.setState({phone : event.target.value});

    handleLogin = (event) => this.setState({login : event.target.value});

    handleAddress = (event) => this.setState({address : event.target.value});

    handleBirthDate = (event) => this.setState({birthDate : event.target.value});

    handleBio = (event) => this.setState({bio : event.target.value});

    handleInterests = (event) => this.setState({interests : event.target.value});


    saveUserSettings = () =>{
        //TODO send to backend
    };

    isValidName = () => {
        if (this.state.name && this.state.name.length > 2){
            return true;
        }else{
            return false;
        }
    };

    isValidEmail = () => {
        return !!this.state.email;
    };


    getProfile = () => {
        getProfilesE
    }



}
