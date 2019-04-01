import React from 'react';
import Event from './event/Event.jsx';
import EquipmentList from './equipment/EquipmentList.jsx';
import Review from './review/Review.jsx';
import CreateEquipment from './equipment/CreateEquipment.jsx';

import './Window.css';
import AppBar from '@material-ui/core/AppBar';
import Tabs from '@material-ui/core/Tabs';
import Tab from '@material-ui/core/Tab';
import PropTypes from 'prop-types';
import axios from 'axios';

function TabContainer(props) {
    return (
        <div className='tab-container'>
            {props.children}
        </div>
    );
  }

TabContainer.propTypes = {
    children: PropTypes.node.isRequired,
  };

class Window extends React.Component {

    state = {
        value: 0,
      };
    
      handleChange = (event, value) => {
        this.setState({ value });
      };
      
    handleCreateEquipment = (equipment) => {

        console.log("In handleCreateEquipment equipment = ");
        console.log(equipment);

        axios.post(`http://localhost:8080/api/v1/equipments/`, equipment)
            .then(res => {
                const createdEquipment = res.data;
                console.log(createdEquipment);
                this.getEquipments()
            })
            .catch(function (error) {
                console.log(error);
            });
    };

    render() {

        const { value } = this.state;

        return (

            <div>
                <AppBar position="static">
                    <Tabs value={value} onChange={this.handleChange} centered>
                        <Tab label="Event" />
                        <Tab label="Equipment" />
                        <Tab label="Review" />
                    </Tabs>
                </AppBar>
                {value === 0 && <TabContainer>
                                        <Event 
                                            description={this.props.description}
                                            image={this.props.image}
                                        />
                                    </TabContainer>}
                {value === 1 && <TabContainer>
                                    <CreateEquipment handleCreateEquipment = {this.handleCreateEquipment}/>
                                    <EquipmentList />
                                </TabContainer>}
                {value === 2 && <TabContainer>
                                    <Review reviews={this.props.reviews}/>
                                </TabContainer>}
            </div>
        );
    }
}

export default Window;
