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
        reviews: ['first review', 'second review', 'third review', ' review', 'fourth review', 'fifth review', 'sixth review', 'seventh review', 'eight review', 'ten review', 'eleven review', 'twelve review', 'thirteen review', 'fourteen review', ' review'],

        startDate: undefined,
        duration: undefined,
        category: undefined,
        creator: undefined,

        participants: undefined,
        spectators: undefined
    }

    componentDidMount() {
        this.getEvent();
    }

    getEvent = () => {

        Axios.get(`http://localhost:8080/api/v1/events/1`).then(eve => {

            this.setState({

                title: eve.data['title'],
                description: eve.data['description'],

                image: eve.data['image'],
                chat: eve.data['chat'],

                equipments: eve.data['equipments'],
                reviews: eve.data['feedback'],

                startDate: eve.data['startDate'],
                duration: eve.data['duration'],
                category: eve.data['category'],
                creator: eve.data['Creator.firstName']

            });

        }).catch(function(error) {
            console.log(error);
        });
    }

    getParticipants = () => {

        Axios.get(`http://localhost:8080/api/v1/eventUsers/1`).then(part => {

            this.setState({

                participants: part.data['']

            })
        })
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

                    startDate={this.state.startDate}
                    duration={this.state.duration}
                    category={this.state.category}
                    creator={this.state.creator}

                    participants={this.state.participants}
                    spectators={this.state.spectators}
                />
            </div>
        );
    }
}

export default App;