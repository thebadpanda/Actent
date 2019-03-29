import React from 'react';
import Button from '@material-ui/core/Button';
import styles from './style.css'

export const s3Root = 'https://s3.ap-south-1.amazonaws.com/';

export function getImageUrl(imageName) {
    if (imageName) {
        return s3Root + 'actent-res/' + imageName;
    }
}

export default class ProfileView extends React.Component {
    constructor(props) {
        super(props);
    }

    render() {
        const editBtn = this.props.isMyProfile ?
            (<Button
                label="Edit"
                color="primary"
                variant="contained"
                disabled={this.props.profileData.id === ''}
                onClick={this.props.onEditClick}
            >Edit
            </Button>) :
            null;

        return (
            <div className = "styleMain">
                <div className= "styleLowerMain1">

                    <div className="styleContainer">
                        <img src={this.props.profileData.avatar.filePath }
                        //&& getImageUrl(this.props.profileData.avatar)}
                             alt=""
                             className="imageStyle"
                        />
                    </div>

                    <div>
                        <p className="styleInput"><span className="styleSpan">First Name:</span>{this.props.profileData.firstName}</p>
                        <p className="styleInput"><span className="styleSpan">Last Name:</span>{this.props.profileData.lastName}</p>
                        <p className="styleInput"><span className="styleSpan">Login:</span>{this.props.profileData.login}</p>
                        <p className="styleInput"><span className="styleSpan">Address:</span>{this.props.profileData.address.name},
                            {this.props.profileData.address.regionCountryName}</p>
                        <p className="styleInput"><span className="styleSpan">Birth Date:</span>{this.props.profileData.birthday}</p>
                        <p className="styleInput"><span className="styleSpan">Bio:</span>{this.props.profileData.bio}</p>
                        <p className="styleInput"><span className="styleSpan">Interests:</span>{this.props.profileData.interests}</p>
                    </div>
                </div>

                <div className="styleLowerMain2">
                    {editBtn}
                </div>
            </div>
        )
    };
}