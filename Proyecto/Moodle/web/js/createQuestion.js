import Titulo from './Components.js';
import { Header, FormQuestion } from './Components.js';

function App() {
  return React.createElement(
    'div',
    { className: 'App' },
    React.createElement(Header, { user: user }),
    React.createElement(Titulo, { title: 'Create a New Fill in the blank Question' }),
    React.createElement(FormQuestion, null)
  );
}

var domContainer = document.querySelector('#root');
ReactDOM.render(React.createElement(App, null), domContainer);