import Titulo from './Components.js';
import {Header, Login} from './Components.js';

function App() {
  return (
    <div className="App">
      <Header />
      <Titulo title="Login"/>
      <Login />
    </div>
  );      
}

const domContainer = document.querySelector('#root');
ReactDOM.render(<App/>, domContainer);