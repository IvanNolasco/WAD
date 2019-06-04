import Titulo from './Components.js';
import { Header, TableObj } from './Components.js';

function App() {
    var encabezado = ["Exams", "Actions"];
    return React.createElement(
        'div',
        { className: 'App' },
        React.createElement(Header, { user: user }),
        React.createElement(Titulo, { title: 'Exams Creation' }),
        React.createElement(
            'div',
            { className: 'container text-center' },
            React.createElement(
                'button',
                { type: 'button', className: 'btn btn-primary', onClick: function onClick() {
                        return location.href = 'CreateExam.action';
                    } },
                'Create Exam'
            )
        ),
        React.createElement(TableObj, { header: encabezado, list: exams })
    );
}

var domContainer = document.querySelector('#root');
ReactDOM.render(React.createElement(App, null), domContainer);