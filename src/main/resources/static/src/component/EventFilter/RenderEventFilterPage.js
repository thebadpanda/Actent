import React from 'react';
import FullPageIntroWithFixedTransparentNavbar from "./Header";
import FilterBody from "./FilterBody";
import CardExample from './Cart';
import { BrowserRouter } from 'react-router-dom';
import CategoryList from './CategoryList';
import axios from 'axios';
import JumbotronPage from './AdriyComponent';


const cartStyle ={
    paddingTop: '2rem'
};

class RenderEventFilterPage extends React.Component {

    state={
        categories1:[],
        events:[],
        categories:[],
        id: 0,
        title: undefined,
        description: undefined
    };

    componentDidMount(){
        this.getEvents();
        this. getCategories();
    };
    
    getCategories = () => {
      
        console.log("zdarova");
        
        axios.get(`http://localhost:8080/api/v1/categories`)
            .then(res=>{
                console.log("zdarova1");
                const categories = res.data;
                this.setState({categories1:categories});
                console.log(categories);
            })
            // this.state.categories1.map(category)
    };

    getEvents() {
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
            }) .catch(function(error) {
            console.log(error);
        });
    };


    render() {
        console.log("Hell22");
        return (
            <div>
                <BrowserRouter>
                <FullPageIntroWithFixedTransparentNavbar/>
                </BrowserRouter>
                <div className="container">
                <JumbotronPage/>
                    <FilterBody/>
                    <div className="row">
                        {console.log("aaaaas")}
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
