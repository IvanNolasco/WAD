import Titulo from './Components.js';
import { Header, FormGroup} from './Components.js';

function App() {
    return (
        <div className="App">
            <Header user={user} />
            <Titulo title="View Question" />
            <PreguntaFill info={question} />
        </div>
    );
}
//Componente para mostrar y responder la pregunta de tipo fill in the blank
class PreguntaFill extends React.Component{
    render(){
        const {
            info
        } = this.props;
        return(
            <div className="container">
                <div className="row">
                    <InitialFeedback >{info.initial}</InitialFeedback>
                    <Tries >{info.qtype === "fill" ? "Tries:" + info.tries : "Max Options:" + info.max}</Tries>
                </div> 
                <p className="h2 text-center border border-dark rounded bg-light">{info.question}</p>              
                <div className="row">
                    <FormAnswer feedback={info.evaluate} type={info.qtype} {...(info.qtype === "partial" ? {options: info.options}:{})}/>                   
                    <Media source={info.source} mediaType={info.type} />
                </div>          
            </div>
        );
    }
}
//Componente que muestra el feedback inicial, como por ejemplo "Responda la siguiente pregunta"
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
//Componente donde se imprimen los intentos para repsonder una pregunta fill in the blank
class Tries extends React.Component {
    render() {
        const {
            children
        } = this.props;
        return (
            <div className="col" id="tr">
                <p className='h4 text-right'>{children}</p>
            </div>
        )
    }
}
//Componente que contiene el formulario para responder la pregunta
class FormAnswer extends React.Component {
    state = {answer : ""}

    handleChange = (e) => {
        const target = e.target;
        const name = target.name;
        this.setState({ [name]: target.value });
    }
    handleSubmit = (e) => {
        event.preventDefault();
        eval()
    }

    render(){
        const {
            feedback,
            options,
            type
        } = this.props;
        return(
            <div className="col" >
                <form onSubmit={this.handleSubmit} method="post">
                    {
                        type === "fill" 
                        ? <FormGroup
                            onChange={this.handleChange}
                            ph="Write your answer here"
                            req = {false}
                            text="Your Answer"
                            title="ans"
                            type="textarea"
                            value={this.state.question}
                        />
                        : options.map((option,index)=>{
                            return <Options key={index} text={option.text} points={option.points} />
                        }) 
                    }
                    <div id="feed"></div>
                    <input type="submit" value="Next" id="send" className="btn btn-block btn-primary mb-2" />
                </form>
                <div className="alert alert-warning" role="alert">
                    {feedback}
                </div>
            </div>
        )
    }
}

class Options extends React.Component{
    render(){
        const {
            points,
            text
        } = this.props;
        return(
            <div className="form-check ml-4">
                <input type="checkbox" name="option"  value={points} className="form-check-input" onChange={() => maxCheck()} />
                <label htmlFor="option" className="form-check-label" >{text}</label>
            </div>
        )
    }
}
//Componente que contiene el archivo multimedia para complementar la pregunta, ya sean imagenes, videos o audios
class Media extends React.Component {
    render(){
        const {
            mediaType,
            source
        } = this.props;
        return (
            <div className="col">
                {(() =>{
                if (mediaType.startsWith("image")){
                    return <img src={source} className='img-thumbnail mx-auto d-block' />
                }
                else if (mediaType.startsWith("audio")){
                    return <audio src={source} controls />
                }
                else{
                    return <video src={source} width='640' height='480' controls />
                }
            })()}   
            </div>
        )
    }
}

const domContainer = document.querySelector('#root');
ReactDOM.render(<App />, domContainer);