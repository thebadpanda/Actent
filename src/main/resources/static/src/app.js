import React from "react";
import ReactDOM from "react-dom";
import Hallo from "./component/Hallo";
import LoginForm from "./component/LoginForm";
import EquipmentView from "./component/equipment/EquipmentView";

class App extends React.Component {
    render() {
        return (
           <div>
               <div>
                   <Hallo />
                   <LoginForm />
                   <EquipmentView/>
               </div>
           </div>
        );
    }
}

ReactDOM.render(<App/>, window.document.getElementById('root'));
