import React from "react";
import { MDBCol } from "mdbreact";

const style = {
  margin: "auto"
};

export default class CityInput extends React.Component {
  state = {
    findCity: ""
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
    }
  };

  render() {
    return (
      <MDBCol md="6" style={style}>
        <input
          className="form-control form-control-lg"
          name="findCity"
          type="text"
          placeholder="Select country"
          aria-label="Search"
          onChange={this.handleChange}
          onKeyPress={this.enterPressed}
        />
      </MDBCol>
    );
  }
}
