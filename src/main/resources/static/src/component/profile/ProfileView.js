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
            <div style={style.styleMain}>
                <div style={style.styleLowerMain1}>

                    <div style={style.styleContainer}>
                        <img src={this.props.profileData.avatar && getImageUrl(this.props.profileData.avatar)}
                             alt=""
                             style={style.imageStyle}
                        />
                    </div>

                    <div>
                        <p style={style.styleInput}><span style={style.styleSpan}>First Name:</span>{this.props.profileData.firstName}</p>
                        <p style={style.styleInput}><span style={style.styleSpan}>Last Name:</span>{this.props.profileData.lastName}</p>
                        <p style={style.styleInput}><span style={style.styleSpan}>Login:</span>{this.props.profileData.login}</p>
                        <p style={style.styleInput}><span style={style.styleSpan}>Address:</span>{this.props.profileData.address.name},
                            {this.props.profileData.address.regionCountryName}</p>
                        <p style={style.styleInput}><span style={style.styleSpan}>Birth Date:</span>{this.props.profileData.birthDate}</p>
                        <p style={style.styleInput}><span style={style.styleSpan}>Bio:</span>{this.props.profileData.bio}</p>
                        <p style={style.styleInput}><span style={style.styleSpan}>Interests:</span>{this.props.profileData.interests}</p>
                    </div>
                </div>

                <div style={style.styleLowerMain2}>
                    {editBtn}
                </div>
            </div>
        )
    };
}