import React from 'react';
import ReactDOM from 'react-dom';
import Hallo from './component/Hallo';
import LoginForm from './component/LoginForm';

export default class App extends React.Component {
    render() {
        return (
            <div>
                <div>
                    <Hallo />
                    <LoginForm />
                </div>
            </div>
        );
    }
}
