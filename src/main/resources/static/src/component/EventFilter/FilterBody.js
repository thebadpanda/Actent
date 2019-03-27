import React from 'react';
import { MDBCard, MDBCardBody, MDBRow, MDBCol} from 'mdbreact';
import FilterButtoms from './FilterButtoms';


const FilterBody = (props) => {
    return (
        <MDBRow>
            <MDBCol style={{ maxWidth: "100%" }}>
                <MDBCard reverse>
                    <MDBCardBody cascade className="text-center">

                        <FilterButtoms
                        categories = {props.categories}
                        />

                    </MDBCardBody>
                </MDBCard>
            </MDBCol>
        </MDBRow>
    )
}

export default FilterBody;