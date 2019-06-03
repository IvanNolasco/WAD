import Titulo from './Components.js';
import {Header, Welcome} from './Components.js';
function App() {
  return (
    <div className="App">
      <Header user={user} />
      <Welcome user={user}/>
    </div>
  );      
}

const domContainer = document.querySelector('#root');
ReactDOM.render(<App/>, domContainer);


