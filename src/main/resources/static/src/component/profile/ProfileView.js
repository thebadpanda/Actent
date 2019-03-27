import React from 'react';
import RaisedButton from 'material-ui/RaisedButton';

const s3Root = 'https://s3.ap-south-1.amazonaws.com/';

function getImageUrl(imageName) {
    if (imageName) {
        return s3Root + 'actent-res/' + imageName;
    }
}
import style from './style.css';

export default class ProfileView extends React.Component {
    constructor(props) {
        super(props);
    }

    render() {
        const editBtn = this.props.isMyProfile ?
            (<RaisedButton
                label="Edit"
                disabled={this.props.profileData.id === ''}
                primary={true}
                keyboardFocused={true}
                onClick={this.props.onEditClick}
            />) :
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
                        <p className="styleInput"><span className="styleSpan">Birth Date:</span>{this.props.profileData.birthDate}</p>
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