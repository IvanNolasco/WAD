import Titulo from './Components.js';
import { Header, FeedBack } from './Components.js';

function App() {
  return React.createElement(
    'div',
    { className: 'App' },
    React.createElement(Header, { user: user }),
    React.createElement(Titulo, { title: 'Create a New Fill in the blank Question' }),
    React.createElement(FeedBack, { id: id })
  );
}

var domContainer = document.querySelector('#root');
ReactDOM.render(React.createElement(App, null), domContainer);