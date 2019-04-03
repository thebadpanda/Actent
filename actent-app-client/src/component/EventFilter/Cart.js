import React from 'react';
import { MDBBtn, MDBCard, MDBCardBody, MDBCardImage, MDBCardTitle, MDBCardText, MDBCol } from 'mdbreact';
import { Route, Link, BrowserRouter } from 'react-router-dom';
import Show from '../show/Show.jsx';

const CardExample = props => {
    return (
        <MDBCol style={{ maxWidth: '22rem', margin: 'auto' }}>
            <MDBCard>
                <MDBCardImage
                    className='img-fluid'
                    src='https://mdbootstrap.com/img/Mockups/Lightbox/Thumbnail/img%20(67).jpg'
                    waves
                />
                <MDBCardBody>
                    <MDBCardTitle>{props.title}</MDBCardTitle>
                    <MDBCardText>Description: {props.description}</MDBCardText>
                    <MDBCardText>City: {props.city}</MDBCardText>
                    <MDBCardText>Category: {props.category}</MDBCardText>

                    <MDBBtn href='#'>Go to Event</MDBBtn>
                </MDBCardBody>
            </MDBCard>
        </MDBCol>
    );
};

export default CardExample;
