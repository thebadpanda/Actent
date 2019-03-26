import React, { Component } from "react";
import { MDBBtn, MDBCollapse } from "mdbreact";
import DatePickerPage from './DatePick';
import ButtonPage from './FilterRadioButton';
import CityInput from './CityInput';
import CategoryList from './CategoryList';

class FilterButtoms extends Component {
    state = {
        collapseID: ""
    }

    toggleCollapse = collapseID => () => {
        this.setState(prevState => ({
            collapseID: prevState.collapseID !== collapseID ? collapseID : ""
        }));
    }

    render() {
        return (
            <>
                <MDBBtn color="info" onClick={this.toggleCollapse("category")} style={{ marginBottom: "1rem" }}>
                    Add category filter
                </MDBBtn>
                <MDBBtn color="info" onClick={this.toggleCollapse("date")} style={{ marginBottom: "1rem" }}>
                    Add data filter
                </MDBBtn>
                <MDBBtn color="info" onClick={this.toggleCollapse("city")} style={{ marginBottom: "1rem" }}>
                    Select City
                </MDBBtn>
                <MDBCollapse id="category" isOpen={this.state.collapseID}>
                    <div className="container">
                        <div>sdfas</div>
                        <CategoryList/>
                    </div>
                </MDBCollapse>
                <MDBCollapse id="date" isOpen={this.state.collapseID}>
                    <div className="container">
                        <DatePickerPage/>
                    </div>
                </MDBCollapse>
                <MDBCollapse id="city" isOpen={this.state.collapseID}>
                    <div className="container">
                        <CityInput/>
                    </div>
                </MDBCollapse>
            </>
        );
    }
}

export default FilterButtoms;