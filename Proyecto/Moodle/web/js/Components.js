var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

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

var FormGroup = function (_React$Component5) {
  _inherits(FormGroup, _React$Component5);

  function FormGroup() {
    _classCallCheck(this, FormGroup);

    return _possibleConstructorReturn(this, (FormGroup.__proto__ || Object.getPrototypeOf(FormGroup)).apply(this, arguments));
  }

  _createClass(FormGroup, [{
    key: "render",
    value: function render() {
      return React.createElement(
        "div",
        { className: "form-group" },
        React.createElement(
          "label",
          { htmlFor: this.props.title, className: "form-label" },
          this.props.text,
          ":"
        ),
        React.createElement("input", { type: this.props.type, name: this.props.title, className: "form-control", required: true, placeholder: this.props.ph })
      );
    }
  }]);

  return FormGroup;
}(React.Component);

var Form = function (_React$Component6) {
  _inherits(Form, _React$Component6);

  function Form() {
    _classCallCheck(this, Form);

    return _possibleConstructorReturn(this, (Form.__proto__ || Object.getPrototypeOf(Form)).apply(this, arguments));
  }

  _createClass(Form, [{
    key: "render",
    value: function render() {
      return React.createElement(
        "div",
        { className: "container text-left" },
        React.createElement(
          "form",
          { action: "Login", method: "POST" },
          React.createElement(FormGroup, { title: "userName", text: "User", ph: "Username", type: "text" }),
          React.createElement(FormGroup, { title: "password", text: "Password", ph: "Password", type: "password" }),
          React.createElement("input", { type: "submit", value: "Access", className: "btn btn-block btn-primary mb-2" })
        )
      );
    }
  }]);

  return Form;
}(React.Component);

var Header = function (_React$Component7) {
  _inherits(Header, _React$Component7);

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

Header.defaultProps = {
  user: null
};

var HeaderNotLogged = function (_React$Component8) {
  _inherits(HeaderNotLogged, _React$Component8);

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

var HeaderLogged = function (_React$Component9) {
  _inherits(HeaderLogged, _React$Component9);

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

export default Form;
export { Header, Titulo, Welcome, TableObj };