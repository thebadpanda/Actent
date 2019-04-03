import React from 'react';
import {MDBJumbotron, MDBContainer, MDBBtn} from 'mdbreact';

const Event = props => {
    return (
        <MDBJumbotron>
            <div className='row'>
                <div className='col-md-2'>
                    <img
                        src='https://mdbootstrap.com/img/Photos/Slides/img%20(54).jpg'
                        className='rounded float-left img-thumbnail'
                        alt='some image'
                    />
                </div>
                <div className='col-md-10'>
                    <h2 className='h2 display-5'>{props.title}</h2>
                    <p className='lead'>{props.description}</p>
                    <div className='row'>
                        <div className='col-md-8'>
                            <MDBBtn color='default' disabled={false} href='#'>
                                Go to event
                            </MDBBtn>
                        </div>
                    </div>
                </div>
            </div>
        </MDBJumbotron>
    );
};

export default Event;
