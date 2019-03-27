import React from 'react';
import Checkbox from '@material-ui/core/Checkbox';
import FormGroup from '@material-ui/core/FormGroup';
import FormControlLabel from '@material-ui/core/FormControlLabel';

export default class CategoryList extends React.Component{

    getInitialState = () => {
        return {checked: true}
      };

        handleCheck = () => {
        this.setState({checked: !this.state.checked});
      };

    render() {
        return(
                    <div className="row">
                    {
                               this.props.categories.map(category=>{
                                        return(
                                            <div key={category.id} className="col-md-4 col-sm-12 align-self-center cart" >
                                            <FormGroup row key={category.id}>
                                            <FormControlLabel
                                                control={
                                                    <Checkbox
                                                    value="checkedA"
                                                    color="primary"
                                                    />
                                                }
                                                label={category.name}
                                                />
                                            </FormGroup>
                                           </div>
                                        )
                                    }
                                )
                            }
                       
                    </div>
            
        )
    }
}
