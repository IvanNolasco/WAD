var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

function _possibleConstructorReturn(self, call) { if (!self) { throw new ReferenceError("this hasn't been initialised - super() hasn't been called"); } return call && (typeof call === "object" || typeof call === "function") ? call : self; }

function _inherits(subClass, superClass) { if (typeof superClass !== "function" && superClass !== null) { throw new TypeError("Super expression must either be null or a function, not " + typeof superClass); } subClass.prototype = Object.create(superClass && superClass.prototype, { constructor: { value: subClass, enumerable: false, writable: true, configurable: true } }); if (superClass) Object.setPrototypeOf ? Object.setPrototypeOf(subClass, superClass) : subClass.__proto__ = superClass; }

import Form from './Login.js';

var Header = function (_React$Component) {
  _inherits(Header, _React$Component);

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
        React.createElement(
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
        )
      );
    }
  }]);

  return Header;
}(React.Component);

var Titulo = function (_React$Component2) {
  _inherits(Titulo, _React$Component2);

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

function App() {
  return React.createElement(
    "div",
    { className: "App" },
    React.createElement(Header, null),
    React.createElement(Titulo, { title: "Login" }),
    React.createElement(Form, null)
  );
}

var domContainer = document.querySelector('#root');
ReactDOM.render(React.createElement(App, null), domContainer);