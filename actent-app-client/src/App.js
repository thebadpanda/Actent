import React from 'react';
import ReviewForm from './component/review/ReviewForm';
import Profile from './component/profile/Profile';
import FormContainer from './component/createevent/FormContainer';
import RenderEventFilterPage from './component/EventFilter/RenderEventFilterPage';
import UserEventsPage from './component/userEventsPage/UserEventsPage';
import ShowEvent from './container/ShowEvent';
import SignInUp from './component/SignUpInForm';
import { configureAxios, getCurrentUser } from './util/apiUtils';
import Show from './component/show/Show';
import Confirm from './component/confirm/Confirm';

import { Route, Switch, Redirect } from 'react-router-dom';

export default class App extends React.Component {
    constructor(props) {
        super(props);
        configureAxios();
        this.state = {
            currentUser: null,
            currentUserId: null,
            isAuthenticated: false,
        };
    }

    setCurrentUser = async _ => {
        try {
            const currentUser = (await getCurrentUser()).data;

            this.setState({
                currentUser: currentUser,
                currentUserId: currentUser.id,
                isAuthenticated: true,
            });
        } catch (e) {
            console.error(e);
        }
    };

    componentDidMount() {
        this.setCurrentUser();
    }

    render() {
        return (
            <div>
                <Switch>
                    <Route path='/auth' component={SignInUp} />
                    <Route path='/show/:id' component={ShowEvent} />
                    <Route path='/show' render={() => <ShowEvent />} />
                    <Route path='/profile' render={() => <Profile />} />
                    <Route path='/userEvents' render={() => <UserEventsPage />} />
                    <Route path='/createEvent' render={() => <FormContainer />} />
                    <Route path='/confirm' component={Confirm} />
                    <Route exact path='/' component={RenderEventFilterPage} />
                </Switch>
            </div>
        );
    }
}
