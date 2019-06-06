var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

function _defineProperty(obj, key, value) { if (key in obj) { Object.defineProperty(obj, key, { value: value, enumerable: true, configurable: true, writable: true }); } else { obj[key] = value; } return obj; }

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

function _possibleConstructorReturn(self, call) { if (!self) { throw new ReferenceError("this hasn't been initialised - super() hasn't been called"); } return call && (typeof call === "object" || typeof call === "function") ? call : self; }

function _inherits(subClass, superClass) { if (typeof superClass !== "function" && superClass !== null) { throw new TypeError("Super expression must either be null or a function, not " + typeof superClass); } subClass.prototype = Object.create(superClass && superClass.prototype, { constructor: { value: subClass, enumerable: false, writable: true, configurable: true } }); if (superClass) Object.setPrototypeOf ? Object.setPrototypeOf(subClass, superClass) : subClass.__proto__ = superClass; }

import Titulo from './Components.js';
import { Header, FormGroup } from './Components.js';

function App() {
    return React.createElement(
        'div',
        { className: 'App' },
        React.createElement(Header, { user: user }),
        React.createElement(Titulo, { title: 'View Question' })
    );
}

var preguntaFill = function (_React$Component) {
    _inherits(preguntaFill, _React$Component);

    function preguntaFill() {
        _classCallCheck(this, preguntaFill);

        return _possibleConstructorReturn(this, (preguntaFill.__proto__ || Object.getPrototypeOf(preguntaFill)).apply(this, arguments));
    }

    _createClass(preguntaFill, [{
        key: 'render',
        value: function render() {
            var children = this.props.children;

            return React.createElement(
                'div',
                null,
                React.createElement(
                    'div',
                    { id: 'row' },
                    React.createElement(InitialFeedback, null),
                    React.createElement(Tries, null)
                ),
                React.createElement(
                    'p',
                    { 'class': 'h2 text-center border border-dark rounded bg-light' },
                    'Responde la siguiente pregunta'
                ),
                React.createElement(
                    'div',
                    { id: 'row' },
                    React.createElement(FormAnswer, null)
                )
            );
        }
    }]);

    return preguntaFill;
}(React.Component);

var InitialFeedback = function (_React$Component2) {
    _inherits(InitialFeedback, _React$Component2);

    function InitialFeedback() {
        _classCallCheck(this, InitialFeedback);

        return _possibleConstructorReturn(this, (InitialFeedback.__proto__ || Object.getPrototypeOf(InitialFeedback)).apply(this, arguments));
    }

    _createClass(InitialFeedback, [{
        key: 'render',
        value: function render() {
            var children = this.props.children;

            return React.createElement(
                'div',
                { className: 'col' },
                React.createElement(
                    'p',
                    { className: 'h4' },
                    children,
                    ':'
                )
            );
        }
    }]);

    return InitialFeedback;
}(React.Component);

var Tries = function (_React$Component3) {
    _inherits(Tries, _React$Component3);

    function Tries() {
        _classCallCheck(this, Tries);

        return _possibleConstructorReturn(this, (Tries.__proto__ || Object.getPrototypeOf(Tries)).apply(this, arguments));
    }

    _createClass(Tries, [{
        key: 'render',
        value: function render() {
            var children = this.props.children;

            return React.createElement(
                'div',
                { className: 'col', id: 'tr' },
                React.createElement(
                    'p',
                    { className: 'h4 text-right' },
                    children,
                    ':'
                )
            );
        }
    }]);

    return Tries;
}(React.Component);

var FormAnswer = function (_React$Component4) {
    _inherits(FormAnswer, _React$Component4);

    function FormAnswer() {
        var _ref;

        var _temp, _this4, _ret;

        _classCallCheck(this, FormAnswer);

        for (var _len = arguments.length, args = Array(_len), _key = 0; _key < _len; _key++) {
            args[_key] = arguments[_key];
        }

        return _ret = (_temp = (_this4 = _possibleConstructorReturn(this, (_ref = FormAnswer.__proto__ || Object.getPrototypeOf(FormAnswer)).call.apply(_ref, [this].concat(args))), _this4), _this4.state = { answer: "" }, _this4.handleChange = function (e) {
            var target = e.target;
            var name = target.name;
            _this4.setState(_defineProperty({}, name, target.value));
        }, _temp), _possibleConstructorReturn(_this4, _ret);
    }

    _createClass(FormAnswer, [{
        key: 'render',
        value: function render() {
            return React.createElement(
                'form',
                { onSubmit: this.handleSubmit, method: 'post' },
                React.createElement(FormGroup, {
                    onChange: this.handleChange,
                    ph: 'Write your answer here',
                    text: 'Your Answer',
                    title: 'ans',
                    type: 'textarea',
                    value: this.state.question
                }),
                React.createElement('input', { type: 'submit', value: 'Next', className: 'btn btn-block btn-primary mb-2' })
            );
        }
    }]);

    return FormAnswer;
}(React.Component);

var Media = function (_React$Component5) {
    _inherits(Media, _React$Component5);

    function Media() {
        _classCallCheck(this, Media);

        return _possibleConstructorReturn(this, (Media.__proto__ || Object.getPrototypeOf(Media)).apply(this, arguments));
    }

    _createClass(Media, [{
        key: 'render',
        value: function render() {
            var mediaType = this.props.mediaType;

            return React.createElement(
                'div',
                { 'class': 'col' },
                function () {
                    if (mediaType.startsWith("image")) {
                        return null;
                    }
                }
            );
        }
    }]);

    return Media;
}(React.Component);

var domContainer = document.querySelector('#root');
ReactDOM.render(React.createElement(App, null), domContainer);