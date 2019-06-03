import Titulo from './Components.js';
import {Header, FormQuestion} from './Components.js';

function App() {
  return (
    <div className="App">
      <Header user={user} />
      <Titulo title="Create a New Fill in the blank Question"/>
      <FormQuestion />
    </div>
  );      
}

const domContainer = document.querySelector('#root');
ReactDOM.render(<App/>, domContainer);