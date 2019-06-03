class Row extends React.Component{
  render() {
    const { question } = this.props;
    return (
      <tr>
        <td>
          {question.name}
        </td>
        <td>
          {question.qtype}
        </td>
        <td>
          <div className="btn-group btn-block" role="group" aria-label="Basic example">
            <button className="btn btn-link" onClick={() => location.href='ViewQuestion.action?id='+question.id }>View Question</button>
            <button className="btn btn-link" onClick={() => location.href='ModifyQuestion.action?id='+question.id }>Modify Question</button>
            <button className="btn btn-link" onClick={() => confirmar(question.id) }>Delete Question</button>
          </div>
        </td>
      </tr>
    );
  }
};

class TableObj extends React.Component{
  render() {
    return (
      <table className="table table-striped table-borderless container">
        <thead>
          <tr>
            <th>Questions</th>
            <th>Type</th>
            <th className="text-center">Actions</th>
          </tr>
        </thead>
        <tbody>
          {
            questions.map(question => {
              return (
                <Row key={question.id} question ={question}/>
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
    defaultProps = {
        title: "Titulo por defecto"
    }; 
    handleChange = (e) => {
        this.props.onChange(e);
    }
    
    render(){
        return(
            <div className="form-group">
                <label htmlFor={this.props.title} className="form-label" > {this.props.text}:</label >
                {(() =>{
                    switch (this.props.type) {
                        case "textarea":
                            return (
                              <textarea 
                                name={this.props.title} 
                                className="form-control" 
                                value={this.props.value} 
                                onChange={this.handleChange} 
                                required placeholder={this.props.ph} 
                                rows="3" />
                            );
                        case "file":
                            return (
                              <input 
                                type="file" 
                                name={this.props.title} 
                                onChange={this.FileHandler} 
                                required 
                                accept="image/*,audio/*,video/*"
                                />
                            );
                        default:
                            return (
                              <input 
                                type={this.props.type} 
                                name={this.props.title} 
                                className="form-control" 
                                value={this.props.value} 
                                onChange={this.handleChange} 
                                {...(this.props.type === "number" ? {min: "1"}:{})} 
                                required 
                                placeholder={this.props.ph} />
                            );
                    } 
                })()
                }
            </div> 
        );          
    }
};

class FormQuestion extends React.Component{
   state = {view : false}
   
    constructor(props){
       super(props);
    }
    
    changeView = (data) => {
        fetch('CreateQuestion.action', {method: 'POST',body: data})
            .then((response)=>{
                if(response.ok)
                    this.setState({view: true});    
            });        
    }
    render(){
        return (
            this.state.view ? <Feedback /> : <Question vista={this.changeView}/>
        );
    }
}

class Question extends React.Component{
    state = {
        id: "",
        name: "",
        question: "",
        answer: "",
        qtype: "",
        media: null,
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
            <form onSubmit={this.handleSubmit} method="POST" id="formQ" className="container text-left" id="formQ" enctype="multipart/form-data">
                <FormGroup title="id" text="ID" ph="Question ID" type ="number" value={this.state.id} onChange={this.handleChange}/>
                <FormGroup title="name" text="Name" ph="Question Name" type="text" value={this.state.name} onChange={this.handleChange}/>
                <FormGroup title="question" text="Question" ph="Question Text" type="textarea" value={this.state.question} onChange={this.handleChange}/>
                <FormGroup title="answer" text="Answer" ph="Question Answer" type="text" value={this.state.answer} onChange={this.handleChange}/>
                <FormGroup title="media" text="Media File" ph="Choose a media file" type="file" onChange={this.handleChange}/>
                <input type="hidden" name="qtype" value="fill" id="qtype" />
                <input type="submit" value="Next" className="btn btn-block btn-primary mb-2"/>
            </form>
        );
    }
};

class Feedback extends React.Component{
    state = {
        id: "",
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
                <FormGroup title="id" text="ID" ph="Question ID" type ="number" value={this.state.id} onChange={this.handleChange}/>
                <FormGroup title="tries" text="Tries" ph="Question Tries" type="number" value={this.state.tries} onChange={this.handleChange}/>
                <FormGroup title="initial" text="Initial Feedback" ph="Initial Feedback" type="text" value={this.state.initial} onChange={this.handleChange}/>
                <FormGroup title="evaluate" text="Evaluate Feedback" ph="Evaluate Feedback" type="text" value={this.state.evaluate} onChange={this.handleChange}/>
                <FormGroup title="correct" text="Correct Feedback" ph="Correct Feedback" type="text" value={this.state.correct} onChange={this.handleChange}/>
                <FormGroup title="incorrect" text="Incorrect Feedback" ph="Incorrect Feedback" type="text" value={this.state.incorrect} onChange={this.handleChange}/>
                <FormGroup title="triesFB" text="Tries Feedback" ph="Tries Feedback" type="text" value={this.state.triesFB} onChange={this.handleChange}/>
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
                <FormGroup title="userName" text="User" ph="Username" type ="text" value={this.state.userName} onChange={this.handleChange} />
                <FormGroup title="password" text="Password" ph="Password" type="password" value={this.state.password} onChange={this.handleChange}/>
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
          <button className="navbar-toggler navbar-toggler-right" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="true" aria-label="Toggle navigation" >
            <span className="navbar-toggler-icon"></span>
          </button>
          <a className="navbar-brand" href="#">
            <h1 className="display-6">Moodle</h1>
          </a>
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

function confirmar(id){
    if (confirm("Do you really want to delete this question?")) {
        location.href ="DeleteQuestion.action?id="+id;
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
    Header,
    Login, 
    Welcome, 
    TableObj,
    FormQuestion
};

