import React from 'react'
import Button from '@material-ui/core/Button';
import TextField from '@material-ui/core/TextField';
import Dialog from '@material-ui/core/Dialog';
import DialogActions from '@material-ui/core/DialogActions';
import DialogContent from '@material-ui/core/DialogContent';
import DialogContentText from '@material-ui/core/DialogContentText';
import DialogTitle from '@material-ui/core/DialogTitle';

export default class CreateEquipment extends React.Component {

    state = {
        open: false,
        equipment: {
            assignedEventId: 1,
            assignedUserId: 1,
            description: undefined,
            satisfied: false,
            title: undefined
        }
    };

    handleClickOpen = () => {
        this.setState({ open: true });
    };

    handleClose = () => {

        this.setState({ open: false });
        // console.log(document.getElementById("title").value);  
    };

    handleChange = name => ({target: {value} }) => {
        
        this.setState({
            equipment: {
                ...this.state.equipment,
                [name] : value
            }
        })
    }

    handleCreate = () => {
        // TODO: validation
        
        const {equipment} = this.state;
        console.log(equipment);
        this.props.handleCreateEquipment(equipment);
        this.handleClose(); 
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
                        <Button onClick={this.handleCreate} color="primary">
                            Create
                        </Button>
                    </DialogActions>
                </Dialog>
            </div>
        );
    }
}