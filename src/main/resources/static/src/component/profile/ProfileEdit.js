import React from 'react';
import axios from 'axios';
import DatePicker from 'material-ui/DatePicker';
import {apiUrl} from './Profile.js';
import {getImageUrl} from './ProfileView';
import {imageValidator} from './FileUploadValidator';
import {Card} from 'material-ui/Card';
import Button from '@material-ui/core/Button';
import TextField from '@material-ui/core/TextField';
import FileUpload from './FileUpload'
import Typography from "@material-ui/core/Typography";
import styles from './style.css'


export default class ProfileEdit extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            userId: this.props.profileData.userId,
            firstName: this.props.profileData.firstName,
            lastName: this.props.profileData.lastName,
            phone: this.props.profileData.phone,
            login: this.props.profileData.login,
            address: this.props.profileData.address,
            birthday: new Date(this.props.profileData.birthday),
            bio: this.props.profileData.bio,
            interests: this.props.profileData.interests,
            // imageUrl: getImageUrl(this.props.profileData.avatar.filePath),
            imageUrl: this.props.profileData.avatar.filePath,
            imageName: this.props.profileData.avatar.filePath,
            imageData: {}
        };
    }

    handleFirstName = (event) => this.setState({firstName: event.target.value});

    handleLastName = (event) => this.setState({lastName: event.target.value});

    handlePhone = (event) => this.setState({phone: event.target.value});

    handleLogin = (event) => this.setState({login: event.target.value});

    handleAddress = (event) => this.setState({address: event.target.value});

    handleBirthday = (event, date) => this.setState({birthday: date});

    handleBio = (event) => this.setState({bio: event.target.value});

    handleInterests = (event) => this.setState({interests: event.target.value});

    getBirthday = () => {
        return this.state.birthday.getFullYear() + '-' +
            this.state.birthday.getMonth() + '-' +
            this.state.birthday.getDate();
    };
    saveUserSettings = () => {
        console.log("state: " + this.state.userId + "props: " + this.props.userId);

        const url = apiUrl + /users/ + this.state.userId;

        axios.put(url, {
            firstName: this.state.firstName,
            lastName: this.state.lastName,
            login: this.state.login,
            address: this.state.address,
            birthday: this.getBirthday(),
            bio: this.state.bio,
            interests: this.state.interests,
        }).then(() => {
            this.props.onCloseClick();
        });

    };

    saveUserPhoto = () => {
        const uploadUrl = apiUrl + '/storage/uploadFile/';
        const userUrl = apiUrl + /users/ + this.state.userId;
        const requestTimeout = 30000;

        axios.post(uploadUrl, this.state.imageData, {
            timeout: requestTimeout,
            headers: {
                'Content-Type': 'multipart/form-data'
            }})
            .then(response => {
                this.setState({
                    imageName: response.data['image_key']
                });
            }).then(() => {
            axios.post(userUrl, this.state.imageName)
                .then(response => {
                    this.props.onCloseClick();
                });
            // this.hideLinearProgress();
        }).catch(error => {
            console.log(error);
        });
    };

    fetchData = (imageData, imageUrl) => {
        this.setState({
            imageData: imageData,
            imageUrl: imageUrl
        }, () => { this.saveUserPhoto(); });
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

    render() {
        return (
            <div className="styleMainDiv">
                <Card className="styleCard">
                    <Typography component="h5" variant="h5" className="styleHeader" align="center">
                        Edit profile
                    </Typography>

                    <div className="styleContainerEdit">
                        <img src={this.state.imageUrl}
                             alt=""
                             className="imageStyle"
                        />
                    </div>
                    <FileUpload
                        fetchData={this.fetchData}
                        handleSavePhoto={this.handleSavePhoto}
                    />
                    <div className="styleName">
                        <TextField
                            id="tv_first_name"
                            onChange={this.handleFirstName}
                            label="First Name:"
                            value={this.state.firstName}
                            fullWidth={true}
                        />
                        <TextField
                            id="tv_last_name"
                            onChange={this.handleLastName}
                            label="Last Name"
                            value={this.state.lastName}
                            fullWidth={true}
                        />
                        <TextField
                            id="tv_login"
                            label='Login'
                            onChange={this.handleLogin}
                            fullWidth={true}
                            rowsMax={3}
                            value={this.state.login}
                        />
                        <TextField
                            id="tv_address"
                            label='Address'
                            onChange={this.handleAddress}
                            fullWidth={true}
                            multiline
                            rowsMax={3}
                            value={this.state.address.name}
                        />
                        <DatePicker
                            id="date_picker_birthday"
                            label="Birthday"
                            mode="landscape"
                            openToYearSelection={true}
                            fullWidth={true}
                            onChange={this.handleBirthday}
                            value={this.state.birthday}

                        />
                        <TextField
                            id="tv_bio"
                            label='Bio'
                            onChange={this.handleBio}
                            fullWidth={true}
                            multiline
                            rowsMax={3}
                            value={this.state.bio}
                        />
                        {/*<ChangePassword />*/}
                    </div>
                    <div className="styleButtons">
                        <Button
                            label="Cancel"
                            variant="contained"
                            color="secondary"
                            onClick={this.props.onCloseClick}
                        >Cancel </Button>
                        <Button
                            label="Save"
                            color="primary"
                            variant="contained"
                            onClick={this.saveUserSettings}
                        >Save </Button>
                    </div>
                    {/*<div className="linearProgressWrapperStyle">*/}
                    {/*<LinearProgress mode='indeterminate' className= "linearProgressStyle"/>*/}
                    {/*</div>*/}
                </Card>
            </div>
        )
    }


}
