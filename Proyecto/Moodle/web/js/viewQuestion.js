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
        React.createElement(Titulo, { title: 'View Question' }),
        React.createElement(PreguntaFill, { info: question })
    );
}
//Componente para mostrar y responder la pregunta de tipo fill in the blank

var PreguntaFill = function (_React$Component) {
    _inherits(PreguntaFill, _React$Component);

    function PreguntaFill() {
        _classCallCheck(this, PreguntaFill);

        return _possibleConstructorReturn(this, (PreguntaFill.__proto__ || Object.getPrototypeOf(PreguntaFill)).apply(this, arguments));
    }

    _createClass(PreguntaFill, [{
        key: 'render',
        value: function render() {
            var info = this.props.info;

            return React.createElement(
                'div',
                { className: 'container' },
                React.createElement(
                    'div',
                    { className: 'row' },
                    React.createElement(
                        InitialFeedback,
                        null,
                        info.initial
                    ),
                    React.createElement(
                        Tries,
                        null,
                        info.qtype === "fill" ? "Tries:" + info.tries : "Max Options:" + info.max
                    )
                ),
                React.createElement(
                    'p',
                    { className: 'h2 text-center border border-dark rounded bg-light' },
                    info.question
                ),
                React.createElement(
                    'div',
                    { className: 'row' },
                    React.createElement(FormAnswer, Object.assign({ feedback: info.evaluate, type: info.qtype }, info.qtype === "partial" ? { options: info.options } : {})),
                    React.createElement(Media, { source: info.source, mediaType: info.type })
                )
            );
        }
    }]);

    return PreguntaFill;
}(React.Component);
//Componente que muestra el feedback inicial, como por ejemplo "Responda la siguiente pregunta"


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
//Componente donde se imprimen los intentos para repsonder una pregunta fill in the blank


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
                    children
                )
            );
        }
    }]);

    return Tries;
}(React.Component);
//Componente que contiene el formulario para responder la pregunta


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
        }, _this4.handleSubmit = function (e) {
            event.preventDefault();
            eval();
        }, _temp), _possibleConstructorReturn(_this4, _ret);
    }

    _createClass(FormAnswer, [{
        key: 'render',
        value: function render() {
            var _props = this.props,
                feedback = _props.feedback,
                options = _props.options,
                type = _props.type;

            return React.createElement(
                'div',
                { className: 'col' },
                React.createElement(
                    'form',
                    { onSubmit: this.handleSubmit, method: 'post' },
                    type === "fill" ? React.createElement(FormGroup, {
                        onChange: this.handleChange,
                        ph: 'Write your answer here',
                        req: false,
                        text: 'Your Answer',
                        title: 'ans',
                        type: 'textarea',
                        value: this.state.question
                    }) : options.map(function (option, index) {
                        return React.createElement(Options, { key: index, text: option.text, points: option.points });
                    }),
                    React.createElement('div', { id: 'feed' }),
                    React.createElement('input', { type: 'submit', value: 'Next', id: 'send', className: 'btn btn-block btn-primary mb-2' })
                ),
                React.createElement(
                    'div',
                    { className: 'alert alert-warning', role: 'alert' },
                    feedback
                )
            );
        }
    }]);

    return FormAnswer;
}(React.Component);

var Options = function (_React$Component5) {
    _inherits(Options, _React$Component5);

    function Options() {
        _classCallCheck(this, Options);

        return _possibleConstructorReturn(this, (Options.__proto__ || Object.getPrototypeOf(Options)).apply(this, arguments));
    }

    _createClass(Options, [{
        key: 'render',
        value: function render() {
            var _props2 = this.props,
                points = _props2.points,
                text = _props2.text;

            return React.createElement(
                'div',
                { className: 'form-check ml-4' },
                React.createElement('input', { type: 'checkbox', name: 'option', value: points, className: 'form-check-input', onChange: function onChange() {
                        return maxCheck();
                    } }),
                React.createElement(
                    'label',
                    { htmlFor: 'option', className: 'form-check-label' },
                    text
                )
            );
        }
    }]);

    return Options;
}(React.Component);
//Componente que contiene el archivo multimedia para complementar la pregunta, ya sean imagenes, videos o audios


var Media = function (_React$Component6) {
    _inherits(Media, _React$Component6);

    function Media() {
        _classCallCheck(this, Media);

        return _possibleConstructorReturn(this, (Media.__proto__ || Object.getPrototypeOf(Media)).apply(this, arguments));
    }

    _createClass(Media, [{
        key: 'render',
        value: function render() {
            var _props3 = this.props,
                mediaType = _props3.mediaType,
                source = _props3.source;

            return React.createElement(
                'div',
                { className: 'col' },
                function () {
                    if (mediaType.startsWith("image")) {
                        return React.createElement('img', { src: source, className: 'img-thumbnail mx-auto d-block' });
                    } else if (mediaType.startsWith("audio")) {
                        return React.createElement('audio', { src: source, controls: true });
                    } else {
                        return React.createElement('video', { src: source, width: '640', height: '480', controls: true });
                    }
                }()
            );
        }
    }]);

    return Media;
}(React.Component);

var domContainer = document.querySelector('#root');
ReactDOM.render(React.createElement(App, null), domContainer);