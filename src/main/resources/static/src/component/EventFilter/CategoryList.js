import React from 'react';
import { MDBInput } from 'mdbreact';
import axios from 'axios';

export default class CategoryList extends React.Component{

    state = {
        categories1:[]
    };

   

    getCategories = () => {
      
        console.log("zdarova");
        
        axios.get(`http://localhost:8080/api/v1/categories`)
            .then(res=>{
                console.log("zdarova1");
                const categories = res.data;
                this.setState({categories1:categories});
                console.log(categories1);
            })
    };

    render() {
        return(
            <div className="row">
            {console.log("in list")}
                {this.state.categories1.map(category=>{
                    return(<div className="col-md-6">
                        <MDBInput key={category.id} label="Filled-in unchecked" type="checkbox" id={category.id}/>
                    </div>)
                })

                }
            </div>
        )
    }
}
