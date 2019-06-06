import Titulo from './Components.js';
import { Header, FormGroup} from './Components.js';

function App() {
    return (
        <div className="App">
            <Header user={user} />
            <Titulo title="View Question" />
        </div>
    );
}

class preguntaFill extends React.Component{
    render(){
        const {
            children
        } = this.props;
        return(
            <div>
                <div id="row">
                    <InitialFeedback />
                    <Tries />
                </div> 
                <p class="h2 text-center border border-dark rounded bg-light">Responde la siguiente pregunta</p>              
                <div id="row">
                   <FormAnswer /> 
                </div>
            </div>
        );
    }
}

class InitialFeedback extends React.Component{
    render(){
        const {
            children
        } = this.props;
        return(
            <div className="col">
                <p className="h4">{children}:</p>
            </div>
        )
    }
}

class Tries extends React.Component {
    render() {
        const {
            children
        } = this.props;
        return (
            <div className="col" id="tr">
                <p className='h4 text-right'>{children}:</p>
            </div>
        )
    }
}

class FormAnswer extends React.Component {
    state = {answer : ""}

    handleChange = (e) => {
        const target = e.target;
        const name = target.name;
        this.setState({ [name]: target.value });
    }
    render(){
        return(
            <form onSubmit={this.handleSubmit} method="post">
                <FormGroup
                    onChange={this.handleChange}
                    ph="Write your answer here"
                    text="Your Answer"
                    title="ans"
                    type="textarea"
                    value={this.state.question}
                />
                <input type="submit" value="Next" className="btn btn-block btn-primary mb-2" />
            </form>
        )
    }
}

class Media extends React.Component {
    render(){
        const {
            mediaType
        } = this.props;
        return (
            <div class="col">
                {() =>{
                if (mediaType.startsWith("image")) {
                    return null;
            }
            }}   
            </div>
        )
    }
}

const domContainer = document.querySelector('#root');
ReactDOM.render(<App />, domContainer);