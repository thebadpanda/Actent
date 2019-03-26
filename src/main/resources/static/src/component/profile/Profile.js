import React from 'react';
import axios from 'axios';
import ProfileView from './ProfileView';

const apiUrl = 'http://localhost:8080/api/v1';

export const getUserId = () => {
    let cookies = document.cookie.split(';').map(cookie => cookie.replace(' ', ''));
    const name = 'user_id=';

    for (let i=0; i < cookies.length; i++) {
        if(cookies[i].indexOf(name) !== -1) {
            return +cookies[i].substring(name.length, cookies[i].length);
        }
    }
    return null;
};

export default class Profile extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            userId: 2,
            isEdit: false,
            isMyProfile: true,
                // +this.props.match.params.profileId === getUserId(),
            firstName: '',
            lastName: '',
            phone: '',
            login: '',
            address: '',
            birthDate: new Date().toUTCString(),
            bio: '',
            interests: '',
            avatar: ''

        };
    }

    // // TODO: unsafe !!
    // componentWillReceiveProps(nextProps, nextContext) {
    //     //TODO: check if my profile
    //     this.getProfile();
    // }

    // TODO: unsafe !!
    componentDidMount() {
        this.getProfile(event);
    }


    handleFirstName = (event) => this.setState({firstName: event.target.value});

    handleLastName = (event) => this.setState({lastName: event.target.value});

    handlePhone = (event) => this.setState({phone: event.target.value});

    handleLogin = (event) => this.setState({login: event.target.value});

    handleAddress = (event) => this.setState({address: event.target.value});

    handleBirthDate = (event) => this.setState({birthDate: event.target.value});

    handleBio = (event) => this.setState({bio: event.target.value});

    handleInterests = (event) => this.setState({interests: event.target.value});


    saveUserSettings = () => {
        //TODO send to backend
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


    getProfile = () => {
        // event.preventDefault();

        const profileUrl = apiUrl + "/users/" + this.state.userId;

        axios.get(profileUrl).then(response => {

            console.log(response.data);
            console.log(response.data['firstName']);

            this.setState({
                userId: response.data['id'],
                firstName: response.data['firstName'],
                lastName: response.data['lastName'],
                login: response.data['login'],
                address: response.data['location'],
                birthDate: response.data['birthDate'],
                bio: response.data['bio'],
                interests: response.data['interests'],
                avatar: response.data['avatar']

            });
        }).catch(function(error) {
            console.log(error);
        });
    };

    handleEditClick = () => {
        this.setState({
            isEdit: false
        });
    };

    render() {

        const profileData = {
            firstName: this.state.firstName,
            lastName: this.state.lastName,
            phone: this.state.phone,
            login: this.state.login,
            address: this.state.address,
            birthDate: this.state.birthDate,
            bio: this.state.bio,
            interests: this.state.interests,
            avatar: this.state.avatar
        };

        const view =
            (<ProfileView
                profileData={profileData}
                isMyProfile={this.state.isMyProfile}
                onEditClick={this.handleEditClick}
            />);

        return (
            <div>
                {view}
            </div>
        )
    };

}
