import Form from './Components.js';
import { Header, Titulo, Welcome } from './Components.js';
function App() {
  return React.createElement(
    'div',
    { className: 'App' },
    React.createElement(Header, { user: user }),
    React.createElement(Welcome, { user: user })
  );
}

var domContainer = document.querySelector('#root');
ReactDOM.render(React.createElement(App, null), domContainer);