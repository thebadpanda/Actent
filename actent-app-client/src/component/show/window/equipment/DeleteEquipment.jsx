import React from 'react';
import Button from '@material-ui/core/Button';
import Dialog from '@material-ui/core/Dialog';
import DialogActions from '@material-ui/core/DialogActions';
import DialogTitle from '@material-ui/core/DialogTitle';

let getUserId = () => 2;

class DeleteEquipment extends React.Component {
    state = {
        open: false,
    };

    handleClickOpen = () => {
        this.setState({ open: true });
    };

    handleClose = () => {
        this.setState({ open: false });
    };

    handleDelete = () => {

        console.log("in handle delete")
        this.handleClose();
    };

    render() {
        let assigneButton;

        if (true) {

            assigneButton = (
                    <div>
                        <Button variant="outlined" color="primary" onClick={this.handleClickOpen}>
                            Delete
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
                                <Button onClick={this.handleDelete} color="primary" autoFocus>
                                    Delete
                                </Button>
                            </DialogActions>
                        </Dialog>
                    </div>
            );
        }
        return (
                <div>
                    {assigneButton}
                </div>
        );
    }
}

export default DeleteEquipment;