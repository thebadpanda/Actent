import React from "react";
import { MDBCol } from "mdbreact";

const style ={
    margin: 'auto'
};
const CityInput = () => {
    return (
        <MDBCol md="6" style={style}>
            <input  className="form-control form-control-lg" type="text" placeholder="Select country" aria-label="Search" />
        </MDBCol>
    );
}

export default CityInput;