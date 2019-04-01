import React from 'react';

class Info extends React.Component {

    render() {

        return (
            <div>
                <h1>{this.props.info}</h1>
                <h3>Start date: {this.props.startDate}</h3>
                <h3>Duration: {this.props.duration}</h3>
                <h3>Category: {this.props.category}</h3>
                <h3>Creator name: {this.props.creator}</h3>
                <h3>Participants: {this.props.participants}</h3>
                <h3>Spectators: {this.props.spectators}</h3>
            </div>
        );
    }
}

export default Info;
