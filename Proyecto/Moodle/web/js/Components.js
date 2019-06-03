var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

function _defineProperty(obj, key, value) { if (key in obj) { Object.defineProperty(obj, key, { value: value, enumerable: true, configurable: true, writable: true }); } else { obj[key] = value; } return obj; }

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

function _possibleConstructorReturn(self, call) { if (!self) { throw new ReferenceError("this hasn't been initialised - super() hasn't been called"); } return call && (typeof call === "object" || typeof call === "function") ? call : self; }

function _inherits(subClass, superClass) { if (typeof superClass !== "function" && superClass !== null) { throw new TypeError("Super expression must either be null or a function, not " + typeof superClass); } subClass.prototype = Object.create(superClass && superClass.prototype, { constructor: { value: subClass, enumerable: false, writable: true, configurable: true } }); if (superClass) Object.setPrototypeOf ? Object.setPrototypeOf(subClass, superClass) : subClass.__proto__ = superClass; }

var Row = function (_React$Component) {
  _inherits(Row, _React$Component);

  function Row() {
    _classCallCheck(this, Row);

    return _possibleConstructorReturn(this, (Row.__proto__ || Object.getPrototypeOf(Row)).apply(this, arguments));
  }

  _createClass(Row, [{
    key: "render",
    value: function render() {
      var question = this.props.question;

      return React.createElement(
        "tr",
        null,
        React.createElement(
          "td",
          null,
          question.name
        ),
        React.createElement(
          "td",
          null,
          question.qtype
        ),
        React.createElement(
          "td",
          null,
          React.createElement(
            "div",
            { className: "btn-group btn-block", role: "group", "aria-label": "Basic example" },
            React.createElement(
              "button",
              { className: "btn btn-link", onClick: function onClick() {
                  return location.href = 'ViewQuestion.action?id=' + question.id;
                } },
              "View Question"
            ),
            React.createElement(
              "button",
              { className: "btn btn-link", onClick: function onClick() {
                  return location.href = 'ModifyQuestion.action?id=' + question.id;
                } },
              "Modify Question"
            ),
            React.createElement(
              "button",
              { className: "btn btn-link", onClick: function onClick() {
                  return confirmar(question.id);
                } },
              "Delete Question"
            )
          )
        )
      );
    }
  }]);

  return Row;
}(React.Component);

;

var TableObj = function (_React$Component2) {
  _inherits(TableObj, _React$Component2);

  function TableObj() {
    _classCallCheck(this, TableObj);

    return _possibleConstructorReturn(this, (TableObj.__proto__ || Object.getPrototypeOf(TableObj)).apply(this, arguments));
  }

  _createClass(TableObj, [{
    key: "render",
    value: function render() {
      return React.createElement(
        "table",
        { className: "table table-striped table-borderless container" },
        React.createElement(
          "thead",
          null,
          React.createElement(
            "tr",
            null,
            React.createElement(
              "th",
              null,
              "Questions"
            ),
            React.createElement(
              "th",
              null,
              "Type"
            ),
            React.createElement(
              "th",
              { className: "text-center" },
              "Actions"
            )
          )
        ),
        React.createElement(
          "tbody",
          null,
          questions.map(function (question) {
            return React.createElement(Row, { key: question.id, question: question });
          })
        )
      );
    }
  }]);

  return TableObj;
}(React.Component);

;

var Titulo = function (_React$Component3) {
  _inherits(Titulo, _React$Component3);

  function Titulo() {
    _classCallCheck(this, Titulo);

    return _possibleConstructorReturn(this, (Titulo.__proto__ || Object.getPrototypeOf(Titulo)).apply(this, arguments));
  }

  _createClass(Titulo, [{
    key: "render",
    value: function render() {
      return React.createElement(
        "h1",
        { className: "h1 text-center mb-3" },
        this.props.title
      );
    }
  }]);

  return Titulo;
}(React.Component);

;
Titulo.defaultProps = {
  title: "Titulo por defecto"
};

var Welcome = function (_React$Component4) {
  _inherits(Welcome, _React$Component4);

  function Welcome() {
    _classCallCheck(this, Welcome);

    return _possibleConstructorReturn(this, (Welcome.__proto__ || Object.getPrototypeOf(Welcome)).apply(this, arguments));
  }

  _createClass(Welcome, [{
    key: "render",
    value: function render() {
      return React.createElement(
        "h1",
        { className: "h1 text-center mb-3", style: { margin: "100px" } },
        "Welcome Teacher:",
        this.props.user
      );
    }
  }]);

  return Welcome;
}(React.Component);

;

var FormGroup = function (_React$Component5) {
  _inherits(FormGroup, _React$Component5);

  function FormGroup() {
    var _ref;

    var _temp, _this5, _ret;

    _classCallCheck(this, FormGroup);

    for (var _len = arguments.length, args = Array(_len), _key = 0; _key < _len; _key++) {
      args[_key] = arguments[_key];
    }

    return _ret = (_temp = (_this5 = _possibleConstructorReturn(this, (_ref = FormGroup.__proto__ || Object.getPrototypeOf(FormGroup)).call.apply(_ref, [this].concat(args))), _this5), _this5.defaultProps = {
      title: "Titulo por defecto"
    }, _this5.handleChange = function (e) {
      _this5.props.onChange(e);
    }, _temp), _possibleConstructorReturn(_this5, _ret);
  }

  _createClass(FormGroup, [{
    key: "render",
    value: function render() {
      var _this6 = this;

      return React.createElement(
        "div",
        { className: "form-group" },
        React.createElement(
          "label",
          { htmlFor: this.props.title, className: "form-label" },
          " ",
          this.props.text,
          ":"
        ),
        function () {
          switch (_this6.props.type) {
            case "textarea":
              return React.createElement("textarea", {
                name: _this6.props.title,
                className: "form-control",
                value: _this6.props.value,
                onChange: _this6.handleChange,
                required: true, placeholder: _this6.props.ph,
                rows: "3" });
            case "file":
              return React.createElement("input", {
                type: "file",
                name: _this6.props.title,
                onChange: _this6.FileHandler,
                required: true,
                accept: "image/*,audio/*,video/*"
              });
            default:
              return React.createElement("input", Object.assign({
                type: _this6.props.type,
                name: _this6.props.title,
                className: "form-control",
                value: _this6.props.value,
                onChange: _this6.handleChange
              }, _this6.props.type === "number" ? { min: "1" } : {}, {
                required: true,
                placeholder: _this6.props.ph }));
          }
        }()
      );
    }
  }]);

  return FormGroup;
}(React.Component);

;

var FormQuestion = function (_React$Component6) {
  _inherits(FormQuestion, _React$Component6);

  function FormQuestion(props) {
    _classCallCheck(this, FormQuestion);

    var _this7 = _possibleConstructorReturn(this, (FormQuestion.__proto__ || Object.getPrototypeOf(FormQuestion)).call(this, props));

    _this7.state = { view: false };

    _this7.changeView = function (data) {
      fetch('CreateQuestion.action', { method: 'POST', body: data }).then(function (response) {
        if (response.ok) _this7.setState({ view: true });
      });
    };

    return _this7;
  }

  _createClass(FormQuestion, [{
    key: "render",
    value: function render() {
      return this.state.view ? React.createElement(Feedback, null) : React.createElement(Question, { vista: this.changeView });
    }
  }]);

  return FormQuestion;
}(React.Component);

var Question = function (_React$Component7) {
  _inherits(Question, _React$Component7);

  function Question() {
    var _ref2;

    var _temp2, _this8, _ret2;

    _classCallCheck(this, Question);

    for (var _len2 = arguments.length, args = Array(_len2), _key2 = 0; _key2 < _len2; _key2++) {
      args[_key2] = arguments[_key2];
    }

    return _ret2 = (_temp2 = (_this8 = _possibleConstructorReturn(this, (_ref2 = Question.__proto__ || Object.getPrototypeOf(Question)).call.apply(_ref2, [this].concat(args))), _this8), _this8.state = {
      id: "",
      name: "",
      question: "",
      answer: "",
      qtype: "",
      media: null
    }, _this8.handleChange = function (e) {
      var target = e.target;
      var value = target.type === 'file' ? target.files[0] : target.value;
      var name = target.name;
      _this8.setState(_defineProperty({}, name, value));
    }, _this8.handleSubmit = function (event) {
      event.preventDefault();
      var data = new FormData(event.target);
      _this8.props.vista(data);
    }, _temp2), _possibleConstructorReturn(_this8, _ret2);
  }

  _createClass(Question, [{
    key: "render",
    value: function render() {
      var _React$createElement;

      return React.createElement(
        "form",
        (_React$createElement = { onSubmit: this.handleSubmit, method: "POST", id: "formQ", className: "container text-left" }, _defineProperty(_React$createElement, "id", "formQ"), _defineProperty(_React$createElement, "enctype", "multipart/form-data"), _React$createElement),
        React.createElement(FormGroup, { title: "id", text: "ID", ph: "Question ID", type: "number", value: this.state.id, onChange: this.handleChange }),
        React.createElement(FormGroup, { title: "name", text: "Name", ph: "Question Name", type: "text", value: this.state.name, onChange: this.handleChange }),
        React.createElement(FormGroup, { title: "question", text: "Question", ph: "Question Text", type: "textarea", value: this.state.question, onChange: this.handleChange }),
        React.createElement(FormGroup, { title: "answer", text: "Answer", ph: "Question Answer", type: "text", value: this.state.answer, onChange: this.handleChange }),
        React.createElement(FormGroup, { title: "media", text: "Media File", ph: "Choose a media file", type: "file", onChange: this.handleChange }),
        React.createElement("input", { type: "hidden", name: "qtype", value: "fill", id: "qtype" }),
        React.createElement("input", { type: "submit", value: "Next", className: "btn btn-block btn-primary mb-2" })
      );
    }
  }]);

  return Question;
}(React.Component);

;

var Feedback = function (_React$Component8) {
  _inherits(Feedback, _React$Component8);

  function Feedback() {
    var _ref3;

    var _temp3, _this9, _ret3;

    _classCallCheck(this, Feedback);

    for (var _len3 = arguments.length, args = Array(_len3), _key3 = 0; _key3 < _len3; _key3++) {
      args[_key3] = arguments[_key3];
    }

    return _ret3 = (_temp3 = (_this9 = _possibleConstructorReturn(this, (_ref3 = Feedback.__proto__ || Object.getPrototypeOf(Feedback)).call.apply(_ref3, [this].concat(args))), _this9), _this9.state = {
      id: "",
      tries: "",
      initial: "",
      evaluate: "",
      correct: "",
      incorrect: "",
      triesFB: ""
    }, _this9.handleChange = function (e) {
      var target = e.target;
      var name = target.name;
      _this9.setState(_defineProperty({}, name, target.value));
    }, _temp3), _possibleConstructorReturn(_this9, _ret3);
  }

  _createClass(Feedback, [{
    key: "render",
    value: function render() {
      var _React$createElement2;

      return React.createElement(
        "form",
        (_React$createElement2 = { action: "FeedbackQuestion", method: "POST", id: "formQ", className: "container text-left" }, _defineProperty(_React$createElement2, "id", "formQ"), _defineProperty(_React$createElement2, "enctype", "multipart/form-data"), _React$createElement2),
        React.createElement(FormGroup, { title: "id", text: "ID", ph: "Question ID", type: "number", value: this.state.id, onChange: this.handleChange }),
        React.createElement(FormGroup, { title: "tries", text: "Tries", ph: "Question Tries", type: "number", value: this.state.tries, onChange: this.handleChange }),
        React.createElement(FormGroup, { title: "initial", text: "Initial Feedback", ph: "Initial Feedback", type: "text", value: this.state.initial, onChange: this.handleChange }),
        React.createElement(FormGroup, { title: "evaluate", text: "Evaluate Feedback", ph: "Evaluate Feedback", type: "text", value: this.state.evaluate, onChange: this.handleChange }),
        React.createElement(FormGroup, { title: "correct", text: "Correct Feedback", ph: "Correct Feedback", type: "text", value: this.state.correct, onChange: this.handleChange }),
        React.createElement(FormGroup, { title: "incorrect", text: "Incorrect Feedback", ph: "Incorrect Feedback", type: "text", value: this.state.incorrect, onChange: this.handleChange }),
        React.createElement(FormGroup, { title: "triesFB", text: "Tries Feedback", ph: "Tries Feedback", type: "text", value: this.state.triesFB, onChange: this.handleChange }),
        React.createElement("input", { type: "submit", value: "Next", className: "btn btn-block btn-primary mb-2" })
      );
    }
  }]);

  return Feedback;
}(React.Component);

;

var Login = function (_React$Component9) {
  _inherits(Login, _React$Component9);

  function Login() {
    var _ref4;

    var _temp4, _this10, _ret4;

    _classCallCheck(this, Login);

    for (var _len4 = arguments.length, args = Array(_len4), _key4 = 0; _key4 < _len4; _key4++) {
      args[_key4] = arguments[_key4];
    }

    return _ret4 = (_temp4 = (_this10 = _possibleConstructorReturn(this, (_ref4 = Login.__proto__ || Object.getPrototypeOf(Login)).call.apply(_ref4, [this].concat(args))), _this10), _this10.state = {
      userName: "",
      password: ""
    }, _this10.handleChange = function (e) {
      var target = e.target;
      var name = target.name;
      console.log(target.name);
      _this10.setState(_defineProperty({}, name, e.target.value));
    }, _temp4), _possibleConstructorReturn(_this10, _ret4);
  }

  _createClass(Login, [{
    key: "render",
    value: function render() {
      return React.createElement(
        "form",
        { action: "/Moodle/Login", method: "POST", className: "container text-left" },
        React.createElement(FormGroup, { title: "userName", text: "User", ph: "Username", type: "text", value: this.state.userName, onChange: this.handleChange }),
        React.createElement(FormGroup, { title: "password", text: "Password", ph: "Password", type: "password", value: this.state.password, onChange: this.handleChange }),
        React.createElement("input", { type: "submit", value: "Access", className: "btn btn-block btn-primary mb-2" })
      );
    }
  }]);

  return Login;
}(React.Component);

;

var Header = function (_React$Component10) {
  _inherits(Header, _React$Component10);

  function Header() {
    _classCallCheck(this, Header);

    return _possibleConstructorReturn(this, (Header.__proto__ || Object.getPrototypeOf(Header)).apply(this, arguments));
  }

  _createClass(Header, [{
    key: "render",
    value: function render() {
      return React.createElement(
        "header",
        null,
        this.props.user !== null ? React.createElement(HeaderLogged, null) : React.createElement(HeaderNotLogged, null)
      );
    }
  }]);

  return Header;
}(React.Component);

;
Header.defaultProps = {
  user: null
};

var HeaderNotLogged = function (_React$Component11) {
  _inherits(HeaderNotLogged, _React$Component11);

  function HeaderNotLogged() {
    _classCallCheck(this, HeaderNotLogged);

    return _possibleConstructorReturn(this, (HeaderNotLogged.__proto__ || Object.getPrototypeOf(HeaderNotLogged)).apply(this, arguments));
  }

  _createClass(HeaderNotLogged, [{
    key: "render",
    value: function render() {
      return React.createElement(
        "div",
        { className: "container-fluid" },
        React.createElement(
          "nav",
          { className: "navbar navbar-dark bg-primary" },
          React.createElement(
            "a",
            { className: "navbar-brand", href: "#" },
            React.createElement(
              "h1",
              { className: "display-6" },
              "Moodle"
            )
          )
        )
      );
    }
  }]);

  return HeaderNotLogged;
}(React.Component);

;

var HeaderLogged = function (_React$Component12) {
  _inherits(HeaderLogged, _React$Component12);

  function HeaderLogged() {
    _classCallCheck(this, HeaderLogged);

    return _possibleConstructorReturn(this, (HeaderLogged.__proto__ || Object.getPrototypeOf(HeaderLogged)).apply(this, arguments));
  }

  _createClass(HeaderLogged, [{
    key: "render",
    value: function render() {
      return React.createElement(
        "div",
        { className: "container-fluid" },
        React.createElement(
          "nav",
          { className: "navbar navbar-expand-lg navbar-dark bg-primary" },
          React.createElement(
            "button",
            { className: "navbar-toggler navbar-toggler-right", "data-toggle": "collapse", "data-target": "#navbarSupportedContent",
              "aria-controls": "navbarSupportedContent", "aria-expanded": "true", "aria-label": "Toggle navigation" },
            React.createElement("span", { className: "navbar-toggler-icon" })
          ),
          React.createElement(
            "a",
            { className: "navbar-brand", href: "#" },
            React.createElement(
              "h1",
              { className: "display-6" },
              "Moodle"
            )
          ),
          React.createElement(
            "div",
            { className: "collapse navbar-collapse", id: "navbarSupportedContent" },
            React.createElement(
              "ul",
              { className: "navbar-nav mr-auto" },
              React.createElement(
                "li",
                { className: "nav-item" },
                React.createElement(
                  "a",
                  { className: "nav-link", href: "QuestionCreation" },
                  "Questions"
                )
              ),
              React.createElement(
                "li",
                { className: "nav-item" },
                React.createElement(
                  "a",
                  { className: "nav-link", href: "ExamCreation" },
                  "Exams"
                )
              )
            )
          ),
          React.createElement(
            "span",
            { className: "navbar-text" },
            React.createElement(
              "button",
              { type: "button", className: "btn btn-link text-light", onClick: function onClick() {
                  return location.href = 'Login.jsp';
                } },
              "Sing out"
            )
          )
        )
      );
    }
  }]);

  return HeaderLogged;
}(React.Component);

;

function confirmar(id) {
  if (confirm("Do you really want to delete this question?")) {
    location.href = "DeleteQuestion.action?id=" + id;
  }
}
function changeF() {

  var formE = document.querySelector("formQ");
  var formData = new FormData(formE);
  // Display the values
  console.log(formData);
  var _iteratorNormalCompletion = true;
  var _didIteratorError = false;
  var _iteratorError = undefined;

  try {
    for (var _iterator = formData.values()[Symbol.iterator](), _step; !(_iteratorNormalCompletion = (_step = _iterator.next()).done); _iteratorNormalCompletion = true) {
      var value = _step.value;

      console.log(value);
    }
  } catch (err) {
    _didIteratorError = true;
    _iteratorError = err;
  } finally {
    try {
      if (!_iteratorNormalCompletion && _iterator.return) {
        _iterator.return();
      }
    } finally {
      if (_didIteratorError) {
        throw _iteratorError;
      }
    }
  }
}

export default Titulo;
export { Header, Login, Welcome, TableObj, FormQuestion };