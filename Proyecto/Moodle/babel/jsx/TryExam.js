import Titulo from './Components.js';
import { Header, FormGroup } from './Components.js';

class App extends React.Component{
    render(){
        return (
            <div className="App">
                <Header user={user} />
                <Titulo title="Try Exam" />

            </div>
        );
    }
}

const domContainer = document.querySelector('#root');
ReactDOM.render(<App />, domContainer);