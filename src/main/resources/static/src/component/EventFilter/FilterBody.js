import React from 'react';
import { MDBCard, MDBCardBody, MDBRow, MDBCol} from 'mdbreact';
import FilterButtoms from './FilterButtoms';


const FilterBody = () => {
    return (
        <MDBRow>
            <MDBCol style={{ maxWidth: "100%" }}>
                <MDBCard reverse>
                    <MDBCardBody cascade className="text-center">

                        <FilterButtoms/>

                    </MDBCardBody>
                </MDBCard>
            </MDBCol>
        </MDBRow>
    )
}

export default FilterBody;