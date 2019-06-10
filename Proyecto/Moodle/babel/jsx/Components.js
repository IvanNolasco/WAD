/* global fetch */
// Componente que representa una fila de la tabla TableObj, Listando ya sean las preguntas o los examenes,
//junto con los botones que representan las acciones ver, modificar y eliminar
class Row extends React.Component{
  render() {
    const { element, children } = this.props;
    return (
        <tr>
            {element.map((col,index)=>{
                return <td key={index}>{col}</td>
            })}
            <td>
                {children}
            </td>
        </tr>	
    );
  }
};
// Componente que representa el grupo de botones de acciones de cada pregunta/examen
class Actions extends React.Component{
    render(){
        const { 
            param,
            type
        } = this.props;
        const typeAction = type !== "Exam" ? "Question" : type;
        return(
            <div className="btn-group btn-block" role="group" aria-label="Basic example">
                <button className="btn btn-link" onClick={() => location.href = 'View' + type + '?id=' + param}>View {typeAction}</button>
                <button className="btn btn-link" onClick={() => location.href = 'Modify' + type +'?id=' + param}>Modify {typeAction}</button>
                <button className="btn btn-link" onClick={() => confirmar(param, typeAction)}>Delete {typeAction}</button>
            </div>
        )
    }
}

class TableObj extends React.Component{   
    render() {
        const {
            actions,
            header,
            list
        } = this.props;
        return (
            <table className="table table-striped table-borderless container">
                <thead>
                    <tr>
                        {header.map((col,index)=>{
                            return col !== "Actions" ? <th key={index}>{col}</th> : <th className="text-center" key={index}>{col}</th> 
                        })}
                    </tr>
                </thead>
                <tbody>
                    {
                    list.map(elem => {
                        const type = Object.keys(elem).indexOf("id");
                        const listElement = type < 0 
                            ? Object.values(elem).reverse() 
                            : Object.values(elem).reverse().slice(1);
                        var qtype = qtype = elem["qtype"] === "fill" ? "Question" : "QuestionP"; 
                        const a = actions
                            ? <Actions {...(type < 0 ? { type: "Exam", param: elem.name } : { type: qtype, param: elem.id })} />
                            : null;
                        return (
                            <Row element={listElement} key={elem.id}>
                               {a}
                            </Row>
                        );
                    })
                    }
                </tbody>
            </table>
        );
    }
};

class Titulo extends React.Component{
    render() {
        return (
            <h1 className="h1 text-center mb-3">{this.props.title}</h1>
        );
    }
};
Titulo.defaultProps = {
      title: "Titulo por defecto"
};

class Welcome extends React.Component{
    render() {
        return (
            <h1 className="h1 text-center mb-3" style={{margin: "100px"}} >Welcome Teacher:{this.props.user}</h1>
        );
      }
}; 


class FormGroup extends React.Component{
    handleChange = (e) => {
        this.props.onChange(e);
    }
    
    render(){
        const {
            ph,
            readonly,
            req,
            text,
            title,
            type,
            value
        } = this.props;
        return(
            <div className="form-group">
                <label htmlFor={title} className="form-label" > {text}:</label >
                {(() =>{
                    switch (type) {
                        case "textarea":
                            return (
                                <textarea 
                                    className="form-control"
                                    id = {title} 
                                    name={title} 
                                    onChange={this.handleChange}  
                                    placeholder={ph} 
                                    required = {req}
                                    rows="3" 
                                    value={value} 
                                /> 
                            );
                        case "file":
                            return (
                                <input
                                    accept="image/*,audio/*,video/*"
                                    name={title} 
                                    required = {req}
                                    type="file"
                                />
                            );
                        default:
                            return (
                                <input
                                    className="form-control" 
                                    name={title}
                                    {...(type === "number" ? {min: "1"}:{})}
                                    onChange={this.handleChange}
                                    placeholder={ph}
                                    readOnly = {readonly}
                                    required
                                    type={type} 
                                    value={value}
                                />         
                            );
                    } 
                })()}
            </div> 
        );          
    }
};
FormGroup.defaultProps = {
    readonly: false,
    req: true
};

class FormQuestion extends React.Component{
       state = {
           view : false,
           id : 0
       }
   
    constructor(props){
         super(props);
    }
    
    changeView = (data) => {
        fetch('CreateQuestion', {method: 'POST',body: data})
            .then((response)=>{
                if(response.ok)
                    this.setState({view: true, id: data.get("id")});
            });        
    }
    render(){
        
        return (
            this.state.view ? <Feedback id={this.state.id} /> : <Question vista={this.changeView}/>
        );
    }
}


class QuestionP extends React.Component{
    state = {
        id: "",
        name: "",
        qtype: "",
        question: "",
        max: ""
    }
    
    handleChange = (e) => {
        const target = e.target;
        const value = target.type === 'file' ? target.files[0] : target.value;
        const name = target.name;
        this.setState({[name]: value});
    }
    handleSubmit = (event) => {
        event.preventDefault();
        const data = new FormData(event.target);
        this.props.vista(data);
    }
     render(){
        return(
            <form onSubmit={this.handleSubmit} method="POST" id="formQP" className="container text-left" id="formQP" enctype="multipart/form-data">
                <FormGroup 
                    onChange={this.handleChange}
                    ph="Question ID" 
                    text="ID" 
                    title="id" 
                    type ="number" 
                    value={this.state.id} 
                />
                <FormGroup 
                    onChange={this.handleChange}
                    ph="Question Name" 
                    text="Name" 
                    title="name" 
                    type="text" 
                    value={this.state.name} 
                />
                <FormGroup 
                    onChange={this.handleChange}
                    ph="Question Text" 
                    text="Question" 
                    title="question" 
                    type="textarea" 
                    value={this.state.question} 
                />
                <FormGroup 
                    onChange={this.handleChange}
                    ph="Choose a media file" 
                    text="Media File" 
                    title="media" 
                    type="file" 
                />
                <FormGroup 
                    onChange={this.handleChange}
                    ph="Amount" 
                    text="Maximum number of options" 
                    title="max"  
                    type ="number" 
                />
                <input type="hidden" name="qtype" value="partial" id="qtype" />
                
                <input type="submit" value="Next" className="btn btn-block btn-primary mb-2"/>
            </form>
        );
    }
};


class Question extends React.Component{
    state = {
        answer: "",
        id: "",
        media: null,
        name: "",
        qtype: "",
        question: "",
        optionList: ["",""],
        exactchk : false,
        cosechk : false
    }
    handleChange = (e) => {
        const target = e.target;
        var value;
        switch (target.type)
        {
            case 'file':
                value=target.files[0];
                break;
            case 'checkbox':
                value=target.checked;
                break;
            default:
                value = target.value;
        }
        const name = target.name;
        this.setState({[name]: value});
    }
    handleOptions = (e) => {
        i = e.target.name;
        let copy = this.state.optionList;
        copy[i] = e.target.value;
        this.setState({optionList: copy})
    }

    add = () => {
        this.setState(state =>{
            {optionList: state.optionList.push("")}
        })
    }
    quit = () => {
        this.setState(state => {
            state.optionList.pop();
            { optionList: state.optionList}
        })
    }

    handleSubmit = (event) => {
        event.preventDefault();
        const data = new FormData(event.target);
        this.props.vista(data);
    }
     render(){
        return(
            <form onSubmit={this.handleSubmit} method="POST" id="formQ" className="container text-left" id="formQ" enctype="multipart/form-data">
                <FormGroup 
                    onChange={this.handleChange}
                    ph="Question ID" 
                    text="ID" 
                    title="id" 
                    type ="number" 
                    value={this.state.id} 
                />
                <FormGroup 
                    onChange={this.handleChange}
                    ph="Question Name" 
                    text="Name" 
                    title="name" 
                    type="text" 
                    value={this.state.name} 
                />
                <FormGroup 
                    onChange={this.handleChange}
                    ph="Question Text" 
                    text="Question" 
                    title="question" 
                    type="textarea" 
                    value={this.state.question} 
                />
                <FormGroup 
                    onChange={this.handleChange}
                    ph="Question Answer" 
                    text="Answer" 
                    title="answer" 
                    type="text" 
                    value={this.state.answer} 
                />
                <FormGroup 
                    onChange={this.handleChange}
                    ph="Choose a media file" 
                    text="Media File" 
                    title="media" 
                    type="file" 
                />
                <input type="hidden" name="qtype" value="fill" id="qtype" />
                <label class="form-label" >Options:</label>
                <div class="row" id="optionL">
                    {this.state.optionList.map((option,index) =>{
                        return <input key={index} type="text" value={option} className="form-control" name={index} placeholder="Option Answer" onChange={this.handleOptions} />   
                    })}
                </div>
                <input type="button" id="addBtn" className="btn btn-primary mt-2" value="Add option" onClick={this.add} />
                <input type="button" id="quitBtn" className="btn btn-primary mt-2" value="Quit option" onClick={this.quit} />
                        <p></p>
                <div className="form-check align-center">
                    <input className="form-check1-input" type="checkbox" name="cosechk" onChange={this.handleChange} />
                    <label className="form-check1-label" for="cosechk">Cose sensitive</label>
                </div>
                <div className="form-check align-center">
                    <input className="form-check2-input" type="checkbox" name="exactchk" onChange={this.handleChange}/>
                    <label className="form-check2-label" for="exactchk">Exact Result</label>
                </div>
                <input type="submit" value="Next" className="btn btn-block btn-primary mb-2"/>
            </form>
        );
    }
};

class Feedback extends React.Component{
    state = {
        id: this.props.id,
        tries: "",
        initial: "",
        evaluate: "",
        correct: "",
        incorrect: "",
        triesFB: "",
    }
    handleChange = (e) => {
        const target = e.target;
        const name = target.name;
        this.setState({[name]: target.value});
    }
    render(){
        return(
            <form action="FeedbackQuestion" method="POST" id="formQ" className="container text-left" id="formQ" enctype="multipart/form-data">
                <FormGroup 
                    onChange={this.handleChange}
                    ph="Question ID"
                    readonly={true} 
                    text="ID" 
                    title="id" 
                    type ="number" 
                    value={this.state.id}
                />
                <FormGroup 
                    onChange={this.handleChange}
                    ph="Question Tries" 
                    text="Tries" 
                    title="tries" 
                    type="number" 
                    value={this.state.tries} 
                />
                <FormGroup 
                    onChange={this.handleChange}
                    ph="Initial Feedback" 
                    text="Initial Feedback" 
                    title="initial" 
                    type="text" 
                    value={this.state.initial} 
                />
                <FormGroup 
                    onChange={this.handleChange}
                    ph="Evaluate Feedback" 
                    text="Evaluate Feedback" 
                    title="evaluate" 
                    type="text" 
                    value={this.state.evaluate} 
                />
                <FormGroup 
                    onChange={this.handleChange}
                    ph="Correct Feedback" 
                    text="Correct Feedback" 
                    title="correct" 
                    type="text" 
                    value={this.state.correct} 
                />
                <FormGroup 
                    onChange={this.handleChange}
                    ph="Incorrect Feedback" 
                    text="Incorrect Feedback" 
                    title="incorrect" 
                    type="text" 
                    value={this.state.incorrect} 
                />
                <FormGroup 
                    onChange={this.handleChange}
                    ph="Tries Feedback" 
                    text="Tries Feedback" 
                    title="triesFB" 
                    type="text" 
                    value={this.state.triesFB} 
                />
                <input type="submit" value="Next" className="btn btn-block btn-primary mb-2"/>
            </form>
        );
    }
};

class FeedbackP extends React.Component{
    state = {
        id: this.props.id,
        initial: "",
        evaluate: "",
        correct: "",
        incorrect: ""
    }
    handleChange = (e) => {
        const target = e.target;
        const name = target.name;
        this.setState({[name]: target.value});
    }
    render(){
        console.log(this.props.id);
        return(
            <form action="CreateFeedbackP" method="POST" id="formQP" className="container text-left" id="formQP" enctype="multipart/form-data">
                <FormGroup 
                    onChange={this.handleChange}
                    ph="Question ID"
                    readonly={true} 
                    text="ID" 
                    title="id" 
                    type ="number" 
                    value={this.state.id}
                />
                <FormGroup 
                    onChange={this.handleChange}
                    ph="Initial Feedback" 
                    text="Initial Feedback" 
                    title="initial" 
                    type="text" 
                    value={this.state.initial} 
                />
                <FormGroup 
                    onChange={this.handleChange}
                    ph="Evaluate Feedback" 
                    text="Evaluate Feedback" 
                    title="evaluate" 
                    type="text" 
                    value={this.state.evaluate} 
                />
                <FormGroup 
                    onChange={this.handleChange}
                    ph="Correct Feedback" 
                    text="Correct Feedback" 
                    title="correct" 
                    type="text" 
                    value={this.state.correct} 
                />
                <FormGroup 
                    onChange={this.handleChange}
                    ph="Incorrect Feedback" 
                    text="Incorrect Feedback" 
                    title="incorrect" 
                    type="text" 
                    value={this.state.incorrect} 
                />
                <input type="submit" value="Next" className="btn btn-block btn-primary mb-2"/>
            </form>
        );
    }
};

class Login extends React.Component{
    state = {
        userName: "",
        password: "",
    }
    handleChange = (e) => {
        const target = e.target;
        const name = target.name;
        console.log(target.name);
        this.setState({[name]: e.target.value});
    }
    render(){
        return(
            <form action = "/Moodle/Login" method="POST" className="container text-left">
                <FormGroup 
                    title="userName" 
                    text="User" 
                    ph="Username" 
                    type ="text" 
                    value={this.state.userName} 
                    onChange={this.handleChange} 
                />
                <FormGroup 
                    title="password" 
                    text="Password" 
                    ph="Password" 
                    type="password" 
                    value={this.state.password} 
                    onChange={this.handleChange}
                />
                <input type="submit" value="Access" className="btn btn-block btn-primary mb-2"/>
            </form>
        );
    }
};

class Header extends React.Component {
    render (){
        return (
            <header>
                {this.props.user !== null 
                    ? <HeaderLogged />
                    : <HeaderNotLogged />
                } 
            </header>
        );
    }
};
Header.defaultProps = {
      user: null
};

class HeaderNotLogged extends React.Component {
    render() {
        return (
        <div className="container-fluid">
            <nav className="navbar navbar-dark bg-primary">
            <a className="navbar-brand" href="#">
                <h1 className="display-6">Moodle</h1>
            </a>
            </nav>
        </div>
        );
    }
};

class HeaderLogged extends React.Component{   
    render(){
        return (
        <div className="container-fluid">
            <nav className="navbar navbar-expand-lg navbar-dark bg-primary">
                <a className="navbar-brand" href="#">
                    <h1 className="display-6">Moodle</h1>
                </a>
                <button className="navbar-toggler" data-toggle="collapse" data-target="#navbarSupportedContent"
                    aria-controls="navbarSupportedContent" aria-expanded="true" aria-label="Toggle navigation" >
                    <span className="navbar-toggler-icon"></span>
                </button>
                <div className="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul className="navbar-nav mr-auto">
                    <li className="nav-item">
                        <a className="nav-link" href="QuestionCreation">Questions</a>
                    </li>
                    <li className="nav-item">
                        <a className="nav-link" href="ExamCreation">Exams</a>
                    </li>
                    </ul>
                </div>
                <span className="navbar-text">
                    <button type="button" className="btn btn-link text-light" onClick={() => location.href='Login.jsp' }>Sing out</button>
                </span>
            </nav>
        </div>
        );
    }
};

function confirmar(id,type){
    if (confirm("Do you really want to delete this question?")) {
        location.href ="Delete" + type +"?id="+id;
    }
}
function changeF (){
    
    var formE =  document.querySelector("formQ");
    var formData = new FormData(formE);
    // Display the values
    console.log(formData)
    for (var value of formData.values()) {
           console.log(value); 
    }
}

export default Titulo;
export {
    FeedbackP,
    FormGroup,
    FormQuestion,
    Header,
    Login, 
    TableObj,
    Welcome,
};

