import Titulo from './Components.js';
import { Header, Login } from './Components.js';

function App() {
  return React.createElement(
    'div',
    { className: 'App' },
    React.createElement(Header, null),
    React.createElement(Titulo, { title: 'Login' }),
    React.createElement(Login, null)
  );
}

var domContainer = document.querySelector('#root');
ReactDOM.render(React.createElement(App, null), domContainer);