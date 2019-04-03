import React from 'react';
import Event from './Event';

export default class PastEventList extends React.Component {
  render() {
    return (
      <div>
        {this.props.events.map(event => (
          <Event key={event.eventId.toString()} title={event.eventTitle} description={event.eventDescription} />
        ))}
      </div>
    );
  }
}
