import React from 'react'
import Paper from '@material-ui/core/Paper';
import Typography from '@material-ui/core/Typography';

export default class EquipmentView extends React.Component {

    render() {
        return (
                <div>
                    <Paper>
                        <Typography>
                            This is a my sheet of paper.
                        </Typography>
                        <Typography>
                            Paper can be used to build surface or other elements for your application.
                        </Typography>
                    </Paper>
                </div>
        );
    }
}
