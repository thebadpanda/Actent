import React from 'react';
import ReviewForm from './component/review/ReviewForm';
import Profile from './component/profile/Profile';
import FormContainer from './component/createevent/FormContainer';
import RenderEventFilterPage from './component/EventFilter/RenderEventFilterPage';
import ShowEvent from './container/ShowEvent';
import SignInUp from './component/SignUpInForm';
import { configureAxios } from './util/apiUtils';
import Show from './component/show/Show';

import { Route, Switch, Redirect } from 'react-router-dom';

export default class App extends React.Component {
    render() {
        configureAxios();

        return (
            <div>
                <Switch>
                    <Route path='/auth' component={SignInUp} />
                    <Route path='/show/:id' component={ShowEvent} />
                    <Route path='/show' render={() => <ShowEvent />} />
                    <Route path='/' component={RenderEventFilterPage} />
                </Switch>
            </div>
        );
    }
}
