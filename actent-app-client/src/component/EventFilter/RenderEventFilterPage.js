import React from 'react';
import Header from './Header';
import FilterBody from './FilterBody';
import CardExample from './Cart';
import { BrowserRouter } from 'react-router-dom';
import axios from 'axios';
import FooterPage from './Footer';

const cartStyle = {
    paddingTop: '2rem',
};

export default class RenderEventFilterPage extends React.Component {
    state = {
        categories: [],
        events: [],
        id: 0,
        title: '',
        description: '',
        category: '',
        city: '',
        filteredEvents: [],
        categoryId: [],
        cityName: '',
        dateFrom: undefined,
        dateTo: undefined,
    };

    componentDidMount() {
        this.getEvents();
        this.getCategories();
    }

    setTitle = title => {
        console.log(title);
        this.setState({ title: title }, () => this.eventsFilter());
        console.log('in title');
        console.log(this.state.title);
    };

    cleanFilter = () => {
        this.setState({ filteredEvents: [] }, () => this.getEvents());
        console.log('in clean');
    };

    setDateRange = (dateFrom, dateTo) => {
        this.setState({ dateFrom: dateFrom, dateTo: dateTo }, () => this.eventsFilter());
    };

    setCity = cityName => {
        this.setState({ cityName: cityName }, () => this.eventsFilter());
    };

    setCategoryId = categoryId => {
        this.setState({ categoryId: categoryId }, () => this.eventsFilter());
    };

    getCategories = () => {
        axios
            .get(`http://localhost:8080/api/v1/categories`)
            .then(res => {
                console.log(res.data);
                const categories = res.data;
                this.setState({ categories });
            })
            .catch(function(error) {
                console.log(error);
            });
    };

    eventsFilter = () => {
        const data = {
            categoryId: this.state.categoryId,
            cityName: this.state.cityName,
            dateFrom: this.state.dateFrom,
            dateTo: this.state.dateTo,
            title: this.state.title,
        };
        console.log('filter');
        console.log(data);
        axios
            .post(`http://localhost:8080/api/v1/events/filter`, data)
            .then(res => {
                const events = res.data;
                console.log(res.data);
                console.log(this.state.title);
                console.log('aaaaaaaaaaaaaaaa');
                this.setState({
                    events: [],
                    events: events,
                    id: res.data['id'],
                    title: res.data['title'],
                    category: res.data['Category.name'],
                    description: res.data['description'],
                });
            })
            .catch(function(error) {
                console.log(error);
            });
        console.log(data);
    };

    getEvents = () => {
        axios
            .get(`http://localhost:8080/api/v1/events/all`)
            .then(res => {
                const events = res.data;
                console.log(res.data);
                this.setState({
                    events: events,
                    id: res.data['id'],
                    title: res.data['title'],
                    category: res.data['Category.name'],
                    description: res.data['description'],
                });
            })
            .catch(function(error) {
                console.log(error);
            });
    };

    switchToFilter = () => {
        let events;
        if (this.state.filteredEvents === undefined || this.state.filteredEvents.length == 0) {
            events = this.state.events;
        } else {
            events = this.state.categoryId;
        }
        return events;
    };

    render() {
        let events = this.switchToFilter();
        return (
            <div>
                <BrowserRouter>
                    <Header setTitle={this.setTitle} />
                </BrowserRouter>
                <div className='container'>
                    <FilterBody
                        filteredEvents={this.state.filteredEvents}
                        cleanFilter={this.cleanFilter}
                        setDateRange={this.setDateRange}
                        categories={this.state.categories}
                        setCity={this.setCity}
                        setCategoryId={this.setCategoryId}
                    />
                    <div className='row'>
                        {events.map(event => {
                            return (
                                <div
                                    key={event.id}
                                    className='col-md-4 col-sm-12 align-self-center cart'
                                    style={cartStyle}>
                                    <CardExample
                                        title={event.title}
                                        eventId={event.id}
                                        description={event.description}
                                        city={event.Location.Country.Region.City.name}
                                        category={event.Category.name}
                                    />
                                </div>
                            );
                        })}
                    </div>
                </div>
                <FooterPage />
            </div>
        );
    }
}
