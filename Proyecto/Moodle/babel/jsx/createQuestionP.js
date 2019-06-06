import Titulo from './Components.js';
import {Header, FeedbackP} from './Components.js';

function App() {
    return (
    <div className="App">
      <Header user={user} />
      <Titulo title="Create a New Partial Credit Question"/>
      
      <FeedbackP id={id} />
    </div>
  );      
}

const domContainer = document.querySelector('#root');
ReactDOM.render(<App/>, domContainer);


