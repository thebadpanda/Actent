import React from 'react';
import axios from 'axios';
import DatePicker from 'material-ui/DatePicker';
import {apiUrl} from './Profile.js';
import {getImageUrl} from './ProfileView';
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
            email: this.props.profileData.email,
            phone: this.props.profileData.phone,
            login: this.props.profileData.login,
            address: this.props.profileData.address,
            birthday: new Date(this.props.profileData.birthday),
            bio: this.props.profileData.bio,
            interests: this.props.profileData.interests,
            imageName: this.props.profileData.avatar.filePath,
            imageData: {},
            imageId: undefined,
            filePath: undefined
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

    handleEmail = (event) => this.setState({email: event.target.value});

    getBirthday = () => {
        return this.state.birthday.getFullYear() + '-' +
            this.handleDigitsInMonth(this.state.birthday.getMonth() + 1) + '-' +
            this.handleDigitsInDate(this.state.birthday.getDate());
    };

    handleDigitsInDate = (i) => {
        return (i < 10) ? (`0 + ${i}`) : i;
    };

    handleDigitsInMonth = (i) => {
        return (i < 10) ? (`0 + ${i + 1}`) : i;
    };

    saveUserSettings = () => {
        const url = apiUrl + /users/ + this.state.userId;

        const data = {
            firstName: this.state.firstName,
            lastName: this.state.lastName,
            login: this.state.login,
            address: this.state.address,
            birthDate: this.getBirthday(),
            bio: this.state.bio,
            interests: this.state.interests,
            email: this.state.email,
            phone: this.state.phone,
            avatarId: this.state.imageId
        };

        axios.put(url, data).then(() => {
            this.props.onCloseClick();
        });

    };

    saveUserPhoto = () => {
        const uploadUrl = apiUrl + '/storage/uploadFile/';
        const addImageUrl = apiUrl + '/images/';
        const userUrl = apiUrl + /users/ + this.state.userId;
        const requestTimeout = 30000;

        const imageData = {
            filePath: this.state.imageName
        };

        axios.post(uploadUrl, this.state.imageData, {
            timeout: requestTimeout,
            headers: {
                'Content-Type': 'multipart/form-data'
            }
        }).then(response => {
            this.setState({
                imageName: response.data,
                imageData: {
                    filePath: response.data
                }
            });
        }).then(() => {
            axios.post(addImageUrl, this.state.imageData, {
                headers: {
                    'Content-Type': 'application/json'
                }
            }).then(response => {
                this.setState({
                    imageId: response.data.id,
                })
            });
        }).catch(error => {
            console.log(error);
        });
    };

    fetchData = (imageData, imageName) => {
        this.setState({
            imageData: imageData,
            imageName: imageName
        }, () => {
            this.saveUserPhoto();
        });
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

        const img = this.state.imageName !== null ?
            (<img src={getImageUrl(this.state.imageName)}
                  alt=""
                  className="imageStyle"
            />)
            :
            (<img src={"https://s3.ap-south-1.amazonaws.com/actent-res/1554136129708-default-user.png"}
                  alt=""
                  className="imageStyle"
                />
            );

        return (
            <div className="styleMainDiv">
                <Card className="styleCard">
                    <Typography component="h5" variant="h5" className="styleHeader" align="center">
                        Edit profile
                    </Typography>

                    <div className="styleContainerEdit">
                        {img}
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
                        <TextField
                            id="tv_phone"
                            label='Phone'
                            onChange={this.handlePhone}
                            fullWidth={true}
                            value={this.state.phone}
                        />
                        <TextField
                            id="tv_email"
                            label='Email'
                            onChange={this.han}
                            fullWidth={true}
                            value={this.state.email}
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
