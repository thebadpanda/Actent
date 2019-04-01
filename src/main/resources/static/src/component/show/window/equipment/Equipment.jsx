import React from "react";
import { MDBJumbotron, MDBContainer, MDBRow, MDBCol } from "mdbreact";
import Satisfie from './Satisfie.jsx'
import AssigneUser from './AssigneUser.jsx'

class Equipment extends React.Component {


    render() {
        return (
            <MDBContainer className="mt-5 text-center">
                <MDBRow>
                    <MDBCol>
                        <MDBJumbotron style={{ padding: "2rem 2rem", height: "300px" }}>

                            <div className="row">
                                <div className="col-md-12">
                                    <h2 className="h1 display-3">{this.props.equipment.title}</h2>
                                </div>
                            </div>

                            <hr className="my-2" />
                            <div className="row">

                                <div className="d-flex align-items-center col-md-2">
                                    {/* <MDBBtn color="primary">Primary</MDBBtn> */}
                                    <Satisfie
                                        currentEquipment={this.props.equipment}
                                        satisfied={this.props.equipment.satisfied}// To Do: Delete 
                                        handleUpdateEquipment={this.props.handleUpdateEquipment}
                                    />
                                </div>

                                <div className="col-md-10">

                                    <div className="row">
                                        <div className="d-flex align-items-center col-8">
                                            <p style={{ marginTop: "1rem", marginBottom: "1rem" }}> {this.props.equipment.description} </p>
                                        </div>

                                        <div className="col-md-4">
                                            <AssigneUser
                                                currentEquipment={this.props.equipment}
                                                handleUpdateEquipment={this.props.handleUpdateEquipment}
                                            />
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </MDBJumbotron>
                    </MDBCol>
                </MDBRow>
            </MDBContainer>
        );
    }
}

export default Equipment;