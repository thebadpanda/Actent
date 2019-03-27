import React from 'react';
import FullPageIntroWithFixedTransparentNavbar from "./Header";
import FilterBody from "./FilterBody";
import CardExample from './Cart';
import { BrowserRouter } from 'react-router-dom';
import axios from 'axios';



const cartStyle ={
    paddingTop: '2rem'
};

class RenderEventFilterPage extends React.Component {

    state={
        categories:[],
        events:[],
        id: 0,
        title: undefined,
        description: undefined
    };

    componentDidMount(){
        this.getEvents();
        this.getCategories();
    };
    
    getCategories = () => {
        axios.get(`http://localhost:8080/api/v1/categories`)
            .then(res=>{
                console.log(res.data);
                const categories = res.data;
                this.setState({categories});
            }).catch(function(error) {
                console.log(error);
            });
    };

    getEventsByName = () => {
        axios.get(`http://localhost:8080/api/v1/events/all`)
            .then(res => {
                const events = res.data;
                this.setState({ events:events,
                    id: res.data['id'],
                    title: res.data['title'],
                    description: res.data['description']
                });
            }) .catch(function(error) {
            console.log(error);
        });
    };

    getEvents = () => {
        axios.get(`http://localhost:8080/api/v1/events/all`)
            .then(res => {
                const events = res.data;
                this.setState({ events:events,
                    id: res.data['id'],
                    title: res.data['title'],
                    description: res.data['description']
                });
            }) .catch(function(error) {
            console.log(error);
        });
    };


    render() {
        return (
            <div>
                <BrowserRouter>
                <FullPageIntroWithFixedTransparentNavbar/>
                </BrowserRouter>
                <div className="container">
                    <FilterBody
                        categories = {this.state.categories}
                    />
                    <div className="row">
                        <div className="row align-items-center" >
                            {
                                this.state.events.map(event=>{
                                        return(
                                            <div key={event.id} className="col-md-4 col-sm-12 align-self-center cart" style={cartStyle}>
                                                <CardExample
                                                    title={event.title}
                                                    description={event.description}
                                                />
                                            </div>
                                        )
                                    }
                                )
                            }
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default RenderEventFilterPage;
