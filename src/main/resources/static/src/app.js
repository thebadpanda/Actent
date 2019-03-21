import React from "react";
import ReactDOM from "react-dom";
import Hallo from "./component/Hallo";

class App extends React.Component {
    render() {
        return (
           <div>
               <Hallo />
           </div>
        );
    }
}

ReactDOM.render(<App/>, window.document.getElementById('root'));
