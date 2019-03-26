import React from 'react';
import { MDBInput } from 'mdbreact';
import axios from 'axios';

class CategoryList extends React.Component{
    state={
        categories:[]
    };

    getCategories = () =>{
        axios.get(`http://localhost:8080/api/v1/categories`)
            .then(res=>{

                const categories = res.data;
                this.setState({categories:categories});
            })
    };
    render() {
        return(
            <div className="row">
                {this.state.categories.map(category=>{
                    return(<div className="col-md-6">
                        <MDBInput key={category.id} label="Filled-in unchecked" type="checkbox" id={category.id}/>
                    </div>)
                })

                }
            </div>
        )
    }
}
export default CategoryList;