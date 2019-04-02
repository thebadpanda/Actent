import React from 'react';
import { MDBBtn, MDBCard, MDBCardBody, MDBCardImage, MDBCardTitle, MDBCardText, MDBCol } from 'mdbreact';


const CardExample = (props) => {
    return (
        <MDBCol style={{ maxWidth: "22rem", margin: "auto" }}>
            <MDBCard>
                <MDBCardImage className="img-fluid" src="https://mdbootstrap.com/img/Mockups/Lightbox/Thumbnail/img%20(67).jpg"
                              waves />
                <MDBCardBody>
                    <MDBCardTitle>{props.title}</MDBCardTitle>
                    <MDBCardText>{props.description}</MDBCardText>
                    <MDBBtn href="#">Click</MDBBtn>
                </MDBCardBody>
            </MDBCard>
        </MDBCol>
    )
}

export default CardExample;