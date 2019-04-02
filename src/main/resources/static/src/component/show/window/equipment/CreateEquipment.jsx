import React from 'react'
import Button from '@material-ui/core/Button';
import TextField from '@material-ui/core/TextField';
import Dialog from '@material-ui/core/Dialog';
import DialogActions from '@material-ui/core/DialogActions';
import DialogContent from '@material-ui/core/DialogContent';
import DialogContentText from '@material-ui/core/DialogContentText';
import DialogTitle from '@material-ui/core/DialogTitle';

const TITLE_AT_LEAST_SIX_AND_NO_LONGER_THAN_HUNDRED_SYMBOLS = "Title should be between 6 and 100 symbols";
const DESCRIPTION_AT_LEAST_SIX_AND_NO_LONGER_THAN_5HUNDRED_SYMBOLS = "Descriptions should be between 6 and 500 symbols";

export default class CreateEquipment extends React.Component {

    state = {
        open: false,
        equipment: {
            assignedEventId: 1,
            assignedUserId: 1,
            description: undefined,
            satisfied: false,
            title: undefined
        },
        errorText: '',
    };

    handleClickOpen = () => {
        this.setState({open: true});
    };

    handleClose = () => {

        this.setState({open: false});

    };

    handleChange = name => ({target: {value}}) => {

        this.setState({
            equipment: {
                ...this.state.equipment,
                [name]: value
            }
        })
    }

    handleCreate = () => {

        const {equipment} = this.state;
        console.log(equipment);
        this.props.handleCreateEquipment(equipment);
        this.handleClose();
    }

    isValidTitle = () => {

        if (this.state.equipment.title && this.state.equipment.title.length <= 100 && this.state.equipment.title.length >= 6) {
            return true;
        }
        return false;
    }

    isValidDescription = () => {

        if (this.state.equipment.description && this.state.equipment.description.length >= 500){
            return false
        }

        return true;
    }

    isValid = () => {

        if (this.isValidTitle() && this.isValidDescription()){
            return true;
        }
        return false;
    }

    render() {
        return (
                <div>
                    <Button variant="contained" color="primary" onClick={this.handleClickOpen}>
                        Add new equipment
                    </Button>
                    <Dialog
                            open={this.state.open}
                            onClose={this.handleClose}
                            aria-labelledby="form-dialog-title"
                    >
                        <DialogTitle id="form-dialog-title">Create Equipment</DialogTitle>
                        <DialogContent>
                            <DialogContentText>
                                To create a new equipment, please enter title and description here.
                            </DialogContentText>
                            <TextField
                                    name="title"
                                    required={true}
                                    error={this.state.errorText.length !== 0}
                                    helperText={this.state.errorText}
                                    autoFocus
                                    margin="dense"
                                    id="title"
                                    label="Title"
                                    type="text"
                                    fullWidth
                                    onChange={this.handleChange('title')}
                            />
                            <TextField
                                    name="description"
                                    multiline={true}
                                    error={this.state.errorText.length !== 0}
                                    helperText={this.state.errorText}
                                    margin="dense"
                                    id="Description"
                                    label="Description"
                                    type="text"
                                    fullWidth
                                    onChange={this.handleChange('description')}
                            />
                        </DialogContent>
                        <DialogActions>
                            <Button onClick={this.handleClose} color="primary">
                                Cancel
                            </Button>
                            <Button onClick={this.handleCreate} disabled={!this.isValid()} color="primary">
                                Create
                            </Button>
                        </DialogActions>
                    </Dialog>
                </div>
        );
    }
}