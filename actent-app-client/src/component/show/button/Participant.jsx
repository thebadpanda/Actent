import React from 'react';
import Button from '@material-ui/core/Button/index';
import Dialog from '@material-ui/core/Dialog/index';
import DialogActions from '@material-ui/core/DialogActions/index';
import DialogTitle from '@material-ui/core/DialogTitle/index';
import Axios from 'axios';

let getUserId = () => 2;

class Participant extends React.Component {

    state = {
        open: false,
        eventUser: undefined,
    };

    getParticipants = () => {

        let userId = {id: this.props.currentUserId}
        let eventId = {id: this.props.eventId}
        let eventUser = {userId, eventId, eventUserType: 'PARTICIPANT'}
        
        Axios.post(`http://localhost:8080/api/v1/eventsUsers`, eventUser).then().catch(function(error) {
            console.log(error);
        });
    };


    createEventUser = () => {
        let user = {id: this.props.currentUserId}
        let event = {id: this.props.eventId}

        this.setState({
            eventUser: {User: user, Event: event, EventUserType: 'PARTICIPANT'}
        });
    }


    handleClickOpen = () => {
        this.setState({ open: true });
    };

    handleClose = () => {
        this.setState({ open: false });
    };

    isPresent = () => {

    }

    handleAssignedUserId = () => {


        this.handleClose();

    };

    handleAgreeDiscardAssigne = () => {

        this.handleClose();
    };

    render() {
        let assigneButton;
    
        if (this.props.currentUserId) {

            assigneButton = (
                    <div>
                        <Button variant="outlined" color="primary" onClick={this.handleClickOpen}>
                            Assigne as participiant
                        </Button>

                        <Dialog
                                open={this.state.open}
                                onClose={this.handleClose}
                                aria-labelledby="alert-dialog-title"
                                aria-describedby="alert-dialog-description"
                        >
                            <DialogTitle id="alert-dialog-title">{"Are you sure?"}</DialogTitle>
                            <DialogActions>
                                <Button onClick={this.handleClose} color="primary">
                                    Cancel
                                </Button>
                                <Button onClick={this.handleAssignedUserId} color="primary" autoFocus>
                                    Agree
                                </Button>
                            </DialogActions>
                        </Dialog>
                    </div>
            );
        } else {
            assigneButton = (
                    <div>
                        <Button variant="outlined" color="primary" onClick={this.handleClickOpen}>
                            Discard assigne
                        </Button>

                        <Dialog
                                open={this.state.open}
                                onClose={this.handleClose}
                                aria-labelledby="alert-dialog-title"
                                aria-describedby="alert-dialog-description"
                        >
                            <DialogTitle id="alert-dialog-title">{"Are you sure?"}</DialogTitle>

                            <DialogActions>
                                <Button onClick={this.handleClose} color="primary">
                                    Cancel
                                </Button>
                                <Button onClick={this.handleAgreeDiscardAssigne} color="primary" autoFocus>
                                    Agree
                                </Button>
                            </DialogActions>
                        </Dialog>
                    </div>
            )
        }
        return (
                <div>
                    <div>
                        {assigneButton}
                    </div>
                </div>
        );
    }
}

export default Participant;
