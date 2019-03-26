import React from "react";
import { MDBCol } from "mdbreact";

const style ={
    height: 'calc(1.5em + 1.75rem + 2px)'
};

const SearchPage = () => {
    return (
        <MDBCol md="6">
            <input style={style} className="form-control form-control-lg" type="text" placeholder="Find an event" aria-label="Search" />
        </MDBCol>
    );
}

export default SearchPage;