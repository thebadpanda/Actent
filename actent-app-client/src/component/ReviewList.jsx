import React, { Component } from 'react';
import Review from './Review';
import Grid from '@material-ui/core/Grid';
import { Divider } from '@material-ui/core';

export default class ReviewList extends Component {
    state = {
        reviews: [],
    };

    constructor(props) {
        super(props);
        this.state.reviews = props.reviews;
    }

    render() {
        return (
            <Grid container direction='column'>
                {this.state.reviews.map(currentReview => (
                    <Grid item>
                        <Review review={currentReview} />
                        <Divider />
                    </Grid>
                ))}
            </Grid>
        );
    }
}
