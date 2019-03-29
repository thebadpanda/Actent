import 'date-fns';
import React from 'react';
import PropTypes from 'prop-types';
import Grid from '@material-ui/core/Grid';
import { withStyles } from '@material-ui/core/styles';
import DateFnsUtils from '@date-io/date-fns';
import { MuiPickersUtilsProvider, DatePicker } from 'material-ui-pickers';

DateFnsUtils.prototype.getStartOfMonth = DateFnsUtils.prototype.startOfMonth;

const styles = {
    grid: {
        width: '60%',
    },
};
const style ={
    margin: 'auto'
};
class MaterialUIPickers extends React.Component {
    state = {
        selectedDateFrom: new Date(),
        selectedDateTo: undefined,
        buttonCollor: 
    };

    handleDateFromChange = date => {
        this.setState({ selectedDateFrom: date });
        console.log(selectedDateFrom);
    };
    handleDateToChange = date => {
        this.setState({ selectedDateTo: date });
        console.log(selectedDateTo);
    };

    render() {
        const { classes } = this.props;


        return (
            <MuiPickersUtilsProvider utils={DateFnsUtils}>
                <Grid container style={style} className={classes.grid} justify="space-around">
               
                    <DatePicker
                        margin="normal"
                        label="Date from"
                        value={this.state.selectedDateFrom}
                        onChange={this.handleDateFromChange}
                    />
                    
                    <DatePicker
                        margin="normal"
                        label="Date to"
                        value={this.state.selectedDateTo}
                        onChange={this.handleDateToChange}
                    />
                    </Grid>
            </MuiPickersUtilsProvider>
        );
    }
}

MaterialUIPickers.propTypes = {
    classes: PropTypes.object.isRequired,
};

export default withStyles(styles)(MaterialUIPickers);