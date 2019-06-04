import Titulo from './Components.js';
import { Header, TableObj } from './Components.js';

function App() {
    const encabezado = ["Exams","Actions"];
    return (
        <div className="App">
            <Header user={user} />
            <Titulo title="Exams Creation" />
            <div className="container text-center">
                <button type="button" className="btn btn-primary" onClick={() => location.href ='CreateExam.action'} >Create Exam</button>
            </div>
            <TableObj header={encabezado} list={exams} />
        </div>
        
    );
}

const domContainer = document.querySelector('#root');
ReactDOM.render(<App />, domContainer);