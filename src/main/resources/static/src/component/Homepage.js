import React from 'react';
import {NavLink} from "react-router-dom";
import Button from '@material-ui/core/Button';

export default class HomePage extends React.Component {

    constructor(props) {
        super(props);
    }


    sendReview = () => {
        this.props.history.push('/reviews');
    }

    sendLogin = () => {
        this.props.history.push('/auth/sign-in')
    }

    sendRegistration = () => {
        this.props.history.push('/auth/sign-up')
    }

    render() {
        return (
                <div>
                    <div>
                        <Button className="FormField__Button" variant="contained" color="primary"
                                onClick={this.sendLogin}>
                            Login
                        </Button>
                    </div>
                    <div>
                        <Button className="FormField__Button" variant="contained" color="primary"
                                onClick={this.sendRegistration}>
                            Registration
                        </Button>
                    </div>
                    <div>
                        <Button className="FormField__Button" variant="contained" color="primary"
                                onClick={this.sendReview}>
                            Review
                        </Button>
                    </div>
                </div>
        );
    }
}