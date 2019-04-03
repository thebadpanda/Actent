import React from 'react';
import { MDBCol } from 'mdbreact';

const style = {
    margin: 'auto',
};

export default class CityInput extends React.Component {
    state = {
        findCity: this.props.cityName,
    };

    handleChange = event => {
        this.setState({ findCity: event.target.value });
    };

    enterPressed = event => {
        let code = event.keyCode || event.which;
        console.log(code);
        if (code === 13) {
            this.props.setCity(this.state.findCity);
            console.log(this.state.findCity);
            console.log(event.value);
            console.log(event.value);
        }
    };

    render() {
        return (
            <MDBCol md='6' style={style}>
                {console.log(this.props.cityName)}
                <input
                    className='form-control form-control-lg'
                    type='text'
                    value={this.state.findCity}
                    placeholder='Select city'
                    aria-label='Search'
                    onChange={this.handleChange}
                    onKeyPress={this.enterPressed}
                />
            </MDBCol>
        );
    }
}
