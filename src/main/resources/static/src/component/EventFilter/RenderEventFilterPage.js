import React, { Component } from 'react';
import FullPageIntroWithFixedTransparentNavbar from "./Header";
import FilterBody from "./FilterBody";
import CardExample from './Cart';
import { BrowserRouter } from 'react-router-dom';
import axios from 'axios';
import "babel-polyfill";

const cartStyle ={
    paddingTop: '2rem'
};

class RenderEventFilterPage extends Component {

    state={
        events:[],
        categories:[],
        id: 0,
        title: undefined,
        description: undefined
    };

    getEvents = () => {
        axios.get(`http://localhost:8080/api/v1/events/all`)
            .then(res => {
                console.log("hi");
                console.log(res.data);
                const events = res.data;
                this.setState({ events:events,
                    id: res.data['id'],
                    title: res.data['title'],
                    description: res.data['description']
                });
            })
    };


    render() {
        return (
            <div>
                <BrowserRouter>
                <FullPageIntroWithFixedTransparentNavbar/>
                </BrowserRouter>
                <div className="container">
                    <div>aaaa</div>
                    <FilterBody/>
                    <div className="row">
                        <div className="row align-items-center">
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
