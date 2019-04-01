import React from 'react';
import Show from '../component/show/Show.jsx';
import Axios from 'axios';

class App extends React.Component {

    state = {
        title: 'Super party event',
        description: 'Description of this super puper event !',
        image: 'Image name',
        info: 'All info about event',
        chat: 'Chat of this event',
        equipments: ['first equipment', 'second equipment', 'third equipment'],
        reviews: ['first review', 'second review', 'third review', 'fff review', 'fourth review', 'fifth review', 'sixth review', 'seventh review', 'eight review', 'ten review', 'eleven review', 'twelve review', 'thirteen review', 'fourteen review', '15 review', '16 review', '17 review', '18 review', '19 review', '20 review', '21 review', '22 review', '23 review', '24 review', '25 review', '26 review', '27 review', '28 review', '29 review'],

        creationDate: undefined,
        startDate: undefined,
        duration: undefined,
        capacity: undefined,
        category: undefined,

        participants: undefined,
        spectators: undefined
    }

    componentDidMount() {
        this.getEvent();
        this.getParticipants();
    }

    getEvent = () => {

        Axios.get(`http://localhost:8080/api/v1/events/1`).then(eve => {

            this.setState({

                title: eve.data['title'],
                description: eve.data['description'],

                image: eve.data['image'],
                chat: eve.data['chat'],

                equipments: eve.data['equipments'],
                //reviews: eve.data.feedback,

                creationDate: eve.data.creationDate,
                startDate: eve.data['startDate'],
                duration: eve.data['duration'],
                capacity: eve.data['capacity'],
                category: eve.data.Category.name,
                creatorFirstName: eve.data.Creator.firstName,
                creatorLastName: eve.data.Creator.lastName,

            });

        }).catch(function(error) {
            console.log(error);
        });
    }

    getParticipants = () => {

        Axios.get(`http://localhost:8080/api/v1/eventsUsers/events/1`).then(part => {

            console.log(part.data.length);

            let participantsCount = 0;
            let spectatorsCount = 0;

            part.data.forEach(e => {
                e.eventUserType === 'PARTICIPANT' ? participantsCount++ : spectatorsCount++;
            });

            this.setState({
                ...this.state,
                participants: participantsCount,
                spectators: spectatorsCount
            })
        });
    }

    render() {

        return(

            <div>
                <Show
                    title={this.state.title}
                    description={this.state.description}
                    image={this.state.image}
                    info={this.state.info}
                    chat={this.state.chat}
                    equipments={this.state.equipments}
                    reviews={this.state.reviews}

                    creationDate={this.state.creationDate}
                    startDate={this.state.startDate}
                    duration={this.state.duration}
                    capacity={this.state.capacity}
                    category={this.state.category}
                    creatorFirstName={this.state.creatorFirstName}
                    creatorLastName={this.state.creatorLastName}

                    participants={this.state.participants}
                    spectators={this.state.spectators}
                />
            </div>
        );
    }
}

export default App;