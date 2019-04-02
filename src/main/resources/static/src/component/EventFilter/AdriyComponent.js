import React from "react";
import { MDBJumbotron, MDBBtn, MDBContainer, MDBRow, MDBCol } from "mdbreact";

const JumbotronPage = () => {
  return (
    <MDBContainer className="mt-5 text-center">
      <MDBRow>
        <MDBCol>
            <div className="col-md-6">
            <MDBJumbotron>
              <div className="row">
                  <div className="col-md-12"> <h2 className="h1 display-3">Hello, world!</h2></div>
                  
              </div>
              <hr className="my-2" />
              <div className="row">
           
                  <div className="col-md-2">
                    <MDBBtn color="primary">Primary</MDBBtn>
                  </div>
                  <div className="col-md-10">
                        <div className="row">
                      <div className="col-8">
                          <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. 
                              In mattis lobortis consequat. Vivamus ut ipsum ac est commodo fermentum. Duis feugiat diam imperdiet,
                               sollicitudin metus sed, pellentesque augue. Nullam ex purus, aliquet nec lectus vel, molestie venenatis odio. 
                               In hac habitasse platea dictumst. Proin laoreet ante eros, a rhoncus leo luctus at. Quisque porta dignissim nisl,
                                vitae ullamcorper odio ornare in. 
                              Proin porta tempus metus, mattis efficitur felis tincidunt sed. </p>
                      </div>
                      <div className="col-md-4">
                      <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. 
                              In mattis lobortis consequat. Vivamus ut ipsum ac est commodo fermentum. Duis feugiat diam imperdiet,
                            t sed. </p>
                      </div>
                      </div>
                  </div>
              </div>
          </MDBJumbotron>
            </div>
       
          <div className="col-md-6">
          <MDBJumbotron>
              <div className="row">
                  <div className="col-md-12"> <h2 className="h1 display-3">Hello, world!</h2></div>
                  
              </div>
              <hr className="my-2" />
              <div className="row">
           
                  <div className="col-md-3">
                    <MDBBtn color="success" disabled={false}>p</MDBBtn>
                  </div>
                  <div className="col-md-9">
                        <div className="row">
                      <div className="col-8">
                          <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. 
                              In mattis lobortis consequat. Vivamus ut ipsum ac est commodo fermentum. Duis feugiat diam imperdiet,
                               sollicitudin metus sed, pellentesque augue. Nullam ex purus, aliquet nec lectus vel, molestie venenatis odio. 
                               In hac habitasse platea dictumst. Proin laoreet ante eros, a rhoncus leo luctus at. Quisque porta dignissim nisl,
                                vitae ullamcorper odio ornare in. 
                              Proin porta tempus metus, mattis efficitur felis tincidunt sed. </p>
                      </div>
                      <div className="col-md-4">
                      <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. 
                              In mattis lobortis consequat. Vivamus ut ipsum ac est commodo fermentum. Duis feugiat diam imperdiet,
                            t sed. </p>
                      </div>
                      </div>
                  </div>
              </div>
          
           
          </MDBJumbotron>
            </div>
       
        </MDBCol>
      </MDBRow>
    </MDBContainer>
  )
}

export default JumbotronPage;