import React from 'react';
import Grid from '@material-ui/core/Grid';
import { Typography, Avatar } from '@material-ui/core';
import Ratings from 'react-ratings-declarative';

const Review = props => {
    return (
        <React.Fragment>
            {props.review ? (
                <Grid container direction='row' spacing={24} style={{ paddingLeft: 24, paddingRight: 24 }}>
                    <Grid item sm={0}>
                        <Avatar
                            src={props.review.author.avatarUrl}
                            style={{ margin: 10, width: 70, height: 70, borderRadius: 3 }}
                        />
                    </Grid>
                    <Grid item container direction='column' sm style={{ paddingTop: 15 }}>
                        <Grid item sm>
                            <Typography variant='h5'>{props.review.author.name}</Typography>
                        </Grid>
                        <Grid item sm>
                            <Typography variant='subtitle1'>{`${props.review.author.location.city}, ${
                                props.review.author.location.region
                            }, ${props.review.author.location.country}`}</Typography>
                        </Grid>
                        <Grid item sm style={{ paddingBottom: 12, paddingTop: 12 }}>
                            <Ratings rating={props.review.score}>
                                <Ratings.Widget widgetDimension={24} />
                                <Ratings.Widget widgetDimension={24} />
                                <Ratings.Widget widgetDimension={24} />
                                <Ratings.Widget widgetDimension={24} />
                                <Ratings.Widget widgetDimension={24} />
                            </Ratings>
                        </Grid>
                        <Grid item sm>
                            <Typography variant='body1'>{props.review.text}</Typography>
                        </Grid>
                    </Grid>
                </Grid>
            ) : (
                console.error('Bad review format.')
            )}
        </React.Fragment>
    );
};

export default Review;
