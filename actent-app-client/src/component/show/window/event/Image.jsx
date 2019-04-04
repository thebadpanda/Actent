import React from 'react';
import { el } from 'date-fns/esm/locale';

class Image extends React.Component {

    render() {

        if  (this.props.image) {
            return (
                <div>
                    <h1>{this.props.image}</h1>
                </div>
            );
        } else {
            return (
                <div>
                    <img src='bigstock-events-7444309.jpg' />
                </div>
            )
        }
    }
}

export default Image;
