import React from 'react';
import Event from './Event';

export default class PastEventList extends React.Component {
  render() {
    return (
      <div>
        {this.props.events.map(event => (
          <div key={event.eventId.toString()}>
            <Event title={event.eventTitle} description={event.eventDescription} />
          </div>
        ))}
      </div>
    );
  }
}
