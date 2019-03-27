import React from 'react';
import axios from 'axios';
import ProfileView from './ProfileView';
import ProfileEdit from './ProfileEdit';

export const apiUrl = 'http://localhost:8080/api/v1';

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
            isMyProfile: true, // +this.props.match.params.profileId === getUserId(),
            firstName: '',
            lastName: '',
            phone: '',
            login: '',
            address: '',
            birthDate: '',
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
            isEdit: true
        });
    };

    handleClose = () => {
        this.componentWillMount();
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
