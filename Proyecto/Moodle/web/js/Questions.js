import Titulo from './Components.js';
import { Header, TableObj } from './Components.js';

function App() {
    var encabezado = ["Questions", "Type", "Actions"];
    return React.createElement(
        'div',
        { className: 'App' },
        React.createElement(Header, { user: user }),
        React.createElement(Titulo, { title: 'Questions Creation' }),
        React.createElement(
            'div',
            { className: 'container text-center' },
            React.createElement(
                'div',
                { className: 'form-group text-left' },
                React.createElement(
                    'label',
                    { 'for': 'selType', className: 'form-label' },
                    'Question type:'
                ),
                React.createElement(
                    'select',
                    { className: 'custom-select', id: 'selType' },
                    React.createElement(
                        'option',
                        { value: '1' },
                        'Fill in the Blank'
                    ),
                    React.createElement(
                        'option',
                        { value: '2' },
                        'Partial Credit'
                    )
                )
            ),
            React.createElement(
                'button',
                { type: 'button', className: 'btn btn-primary', onClick: function onClick() {
                        return selectType();
                    } },
                'Create Question'
            )
        ),
        React.createElement(TableObj, { header: encabezado, list: questions, actions: true })
    );
}
function selectType() {
    var type = document.getElementById("selType").value;
    if (type === '1') {
        location.href = "CreateQuestion.jsp";
    } else {
        location.href = "CreateQuestionP.jsp";
    }
}

var domContainer = document.querySelector('#root');
ReactDOM.render(React.createElement(App, null), domContainer);