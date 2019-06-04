import Titulo from './Components.js';
import { Header, FeedbackP } from './Components.js';

function App() {
  return React.createElement(
    'div',
    { className: 'App' },
    React.createElement(Header, { user: user }),
    React.createElement(Titulo, { title: 'Create a New Partial Credit Question' }),
    React.createElement(FeedbackP, null)
  );
}

var domContainer = document.querySelector('#root');
ReactDOM.render(React.createElement(App, null), domContainer);