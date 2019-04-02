import React from 'react';

class Image extends React.Component {

    render() {

        return (
            <div>
                <h1>{this.props.image}</h1>
            </div>
        );
    }
}

export default Image;
