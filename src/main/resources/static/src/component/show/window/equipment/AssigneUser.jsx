import React from 'react';
import Button from '@material-ui/core/Button';
import Dialog from '@material-ui/core/Dialog';
import DialogActions from '@material-ui/core/DialogActions';
import DialogTitle from '@material-ui/core/DialogTitle';

let getUserId = () => 22;

class AlertDialog extends React.Component {
    state = {
        open: false,
    };

    handleClickOpen = () => {
        this.setState({ open: true });
    };

    handleClose = () => {
        this.setState({ open: false });
    };

    handleAssignedUserId = () => {
        let equipment = {
            assignedEventId: this.props.currentEquipment.assignedEventId,
            assignedUserId: getUserId(),
            description: this.props.currentEquipment.description,
            satisfied: this.props.currentEquipment.satisfied,
            title: this.props.currentEquipment.title
        }
        this.props.handleUpdateEquipment(this.props.currentEquipment.id, equipment);
        this.handleClose();

    };

    handleAgreeDiscardAssigne = () => {
        let equipment = {
            assignedEventId: this.props.currentEquipment.assignedEventId,
            assignedUserId: null,
            description: this.props.currentEquipment.description,
            satisfied: this.props.currentEquipment.satisfied,
            title: this.props.currentEquipment.title
        }
        
        this.props.handleUpdateEquipment(this.props.currentEquipment.id, equipment);
        this.handleClose();
    };

    render() {
        let assigneButton;

        if (this.props.currentEquipment.assignedUserFirstName === null || this.props.currentEquipment.assignedUserLastName === null) {

            assigneButton = (
                <div>
                    <Button variant="outlined" color="primary" onClick={this.handleClickOpen}>
                        Assigne
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
                {assigneButton}
                <p>{this.props.currentEquipment.assignedUserFirstName}</p>
                <p>{this.props.currentEquipment.assignedUserLastName}</p>
            </div>
        );
    }
}

export default AlertDialog;