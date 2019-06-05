import Titulo from './Components.js';
import {Header, TableObj} from './Components.js';

function App() {
    const encabezado = ["Questions","Type", "Actions"];
    return (
        <div className="App">
            <Header user={user} />
            <Titulo title="Questions Creation"/>
            <div className="container text-center">
                <div className="form-group text-left">
                    <label for="selType" className="form-label" >Question type:</label>
                    <select className="custom-select" id="selType">
                        <option value="1">Fill in the Blank</option>
                        <option value="2">Partial Credit</option>
                    </select>
                </div>
                <button type="button" className="btn btn-primary" onClick={() => selectType() } >Create Question</button>
            </div>
            <TableObj header={encabezado} list={questions} actions={true} />
        </div>
    );      
}
function selectType(){
    var type = document.getElementById("selType").value;
    if (type === '1') {
        location.href ="CreateQuestion.jsp";
    }
    else{
        location.href ="CreateQuestionP.jsp";
    }
}

const domContainer = document.querySelector('#root');
ReactDOM.render(<App/>, domContainer);

