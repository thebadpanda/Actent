import React from 'react';
import Button from '@material-ui/core/Button';
import Dialog from '@material-ui/core/Dialog';
import DialogActions from '@material-ui/core/DialogActions';
import DialogTitle from '@material-ui/core/DialogTitle';

let getUserId = () => 2;

class AssigneSpectator extends React.Component {
    state = {
        open: false,
    };

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
        let currentUser = "dont assigne";

        if ((currentUser == "dont assigne") ? true : false ) {

            assigneButton = (
                    <div>
                        <Button variant="outlined" color="primary" onClick={this.handleClickOpen}>
                            Assigne as spectator
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

export default AssigneSpectator;