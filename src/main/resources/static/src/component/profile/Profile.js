import React from 'react';
import axios from 'axios';
import ProfileView from './ProfileView';
import ProfileEdit from './ProfileEdit';

export const apiUrl = 'http://localhost:8080/api/v1';

export const getUserId = () => {
    let cookies = document.cookie.split(';').map(cookie => cookie.replace(' ', ''));
    const name = 'user_id=';

    for (let i = 0; i < cookies.length; i++) {
        if (cookies[i].indexOf(name) !== -1) {
            return +cookies[i].substring(name.length, cookies[i].length);
        }
    }
    return null;
};

export default class Profile extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            userId: 1,
            isEdit: false,
            isMyProfile: true, // isMyProfile: +this.props.match.params.userId === getCurrentUser().getId(),
            firstName: '',
            lastName: '',
            phone: '',
            email: '',
            login: '',
            address: '',
            birthday: '',
            bio: '',
            interests: '',
            avatar: ''

        };
    }

    // componentWillReceiveProps(nextProps, nextContext) {
    //     //TODO: check if my profile
    //     this.getProfile();
    // }

    componentDidMount() {
        this.getProfile();
    }

    getProfile = () => {
        const profileUrl = apiUrl + "/users/" + this.state.userId;

        axios.get(profileUrl).then(response => {

            console.log(response.data);

            this.setState({
                userId: response.data['id'],
                firstName: response.data['firstName'],
                lastName: response.data['lastName'],
                login: response.data['login'],
                address: response.data['location'],
                birthday: response.data['birthDate'],
                bio: response.data['bio'],
                interests: response.data['interests'],
                avatar: response.data['avatar'],
                email: response.data['email'],
                phone: response.data['phone']

            });
        }).catch(function (error) {
            console.log(error);
        });
    };

    handleEditClick = () => {
        this.setState({
            isEdit: true
        });
    };

    handleClose = () => {
        this.setState({
            isEdit: false
        });
    };

    render() {

        const profileData = {
            userId: this.state.userId,
            firstName: this.state.firstName,
            lastName: this.state.lastName,
            phone: this.state.phone,
            email: this.state.email,
            login: this.state.login,
            address: this.state.address,
            birthday: this.state.birthday,
            bio: this.state.bio,
            interests: this.state.interests,
            avatar: this.state.avatar
        };

        const view = this.state.isEdit ?
            (<ProfileEdit
                profileData={profileData}
                onCloseClick={this.handleClose}
            />) :
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
