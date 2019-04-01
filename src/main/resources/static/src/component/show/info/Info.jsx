import React from 'react';

class Info extends React.Component {

    constructor(props) {
        super(props);
        console.log(props);
    }

    render() {

        return (

            <div>
                <h1>{this.props.info}</h1>
                <h4>Creation date: {this.props.creationDate}</h4>
                <h4>Start date: {this.props.startDate}</h4>
                <h4>Duration: {this.props.duration}</h4>
                <h4>Capacity: {this.props.capacity}</h4>
                <h4>Category: {this.props.category}</h4>
                <h4>Creator:</h4>
                <h4> First name: {this.props.creatorFirstName}</h4>
                <h4> Last name: {this.props.creatorLastName}</h4>
                <h4>Participants: {this.props.participants}</h4>
                <h4>Spectators: {this.props.spectators}</h4>
            </div>
        );
    }
}

export default Info;
