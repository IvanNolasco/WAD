var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

function _defineProperty(obj, key, value) { if (key in obj) { Object.defineProperty(obj, key, { value: value, enumerable: true, configurable: true, writable: true }); } else { obj[key] = value; } return obj; }

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

function _possibleConstructorReturn(self, call) { if (!self) { throw new ReferenceError("this hasn't been initialised - super() hasn't been called"); } return call && (typeof call === "object" || typeof call === "function") ? call : self; }

function _inherits(subClass, superClass) { if (typeof superClass !== "function" && superClass !== null) { throw new TypeError("Super expression must either be null or a function, not " + typeof superClass); } subClass.prototype = Object.create(superClass && superClass.prototype, { constructor: { value: subClass, enumerable: false, writable: true, configurable: true } }); if (superClass) Object.setPrototypeOf ? Object.setPrototypeOf(subClass, superClass) : subClass.__proto__ = superClass; }

/* global fetch */
// Componente que representa una fila de la tabla TableObj, Listando ya sean las preguntas o los examenes,
//junto con los botones que representan las acciones ver, modificar y eliminar
var Row = function (_React$Component) {
    _inherits(Row, _React$Component);

    function Row() {
        _classCallCheck(this, Row);

        return _possibleConstructorReturn(this, (Row.__proto__ || Object.getPrototypeOf(Row)).apply(this, arguments));
    }

    _createClass(Row, [{
        key: "render",
        value: function render() {
            var _props = this.props,
                element = _props.element,
                children = _props.children;

            return React.createElement(
                "tr",
                null,
                element.map(function (col, index) {
                    return React.createElement(
                        "td",
                        { key: index },
                        col
                    );
                }),
                React.createElement(
                    "td",
                    null,
                    children
                )
            );
        }
    }]);

    return Row;
}(React.Component);

;
// Componente que representa el grupo de botones de acciones de cada pregunta/examen

var Actions = function (_React$Component2) {
    _inherits(Actions, _React$Component2);

    function Actions() {
        _classCallCheck(this, Actions);

        return _possibleConstructorReturn(this, (Actions.__proto__ || Object.getPrototypeOf(Actions)).apply(this, arguments));
    }

    _createClass(Actions, [{
        key: "render",
        value: function render() {
            var _props2 = this.props,
                param = _props2.param,
                type = _props2.type;

            var typeAction = type !== "Exam" ? "Question" : type;
            return React.createElement(
                "div",
                { className: "btn-group btn-block", role: "group", "aria-label": "Basic example" },
                React.createElement(
                    "button",
                    { className: "btn btn-link", onClick: function onClick() {
                            return location.href = 'View' + type + '?id=' + param;
                        } },
                    "View ",
                    typeAction
                ),
                React.createElement(
                    "button",
                    { className: "btn btn-link", onClick: function onClick() {
                            return location.href = 'Modify' + type + '?id=' + param;
                        } },
                    "Modify ",
                    typeAction
                ),
                React.createElement(
                    "button",
                    { className: "btn btn-link", onClick: function onClick() {
                            return confirmar(param, typeAction);
                        } },
                    "Delete ",
                    typeAction
                )
            );
        }
    }]);

    return Actions;
}(React.Component);

var TableObj = function (_React$Component3) {
    _inherits(TableObj, _React$Component3);

    function TableObj() {
        _classCallCheck(this, TableObj);

        return _possibleConstructorReturn(this, (TableObj.__proto__ || Object.getPrototypeOf(TableObj)).apply(this, arguments));
    }

    _createClass(TableObj, [{
        key: "render",
        value: function render() {
            var _props3 = this.props,
                actions = _props3.actions,
                header = _props3.header,
                list = _props3.list;

            return React.createElement(
                "table",
                { className: "table table-striped table-borderless container" },
                React.createElement(
                    "thead",
                    null,
                    React.createElement(
                        "tr",
                        null,
                        header.map(function (col, index) {
                            return col !== "Actions" ? React.createElement(
                                "th",
                                { key: index },
                                col
                            ) : React.createElement(
                                "th",
                                { className: "text-center", key: index },
                                col
                            );
                        })
                    )
                ),
                React.createElement(
                    "tbody",
                    null,
                    list.map(function (elem) {
                        var type = Object.keys(elem).indexOf("id");
                        var listElement = type < 0 ? Object.values(elem).reverse() : Object.values(elem).reverse().slice(1);
                        var qtype = qtype = elem["qtype"] === "fill" ? "Question" : "QuestionP";
                        var a = actions ? React.createElement(Actions, type < 0 ? { type: "Exam", param: elem.name } : { type: qtype, param: elem.id }) : null;
                        return React.createElement(
                            Row,
                            { element: listElement, key: elem.id },
                            a
                        );
                    })
                )
            );
        }
    }]);

    return TableObj;
}(React.Component);

;

var Titulo = function (_React$Component4) {
    _inherits(Titulo, _React$Component4);

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

var Welcome = function (_React$Component5) {
    _inherits(Welcome, _React$Component5);

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

var FormGroup = function (_React$Component6) {
    _inherits(FormGroup, _React$Component6);

    function FormGroup() {
        var _ref;

        var _temp, _this6, _ret;

        _classCallCheck(this, FormGroup);

        for (var _len = arguments.length, args = Array(_len), _key = 0; _key < _len; _key++) {
            args[_key] = arguments[_key];
        }

        return _ret = (_temp = (_this6 = _possibleConstructorReturn(this, (_ref = FormGroup.__proto__ || Object.getPrototypeOf(FormGroup)).call.apply(_ref, [this].concat(args))), _this6), _this6.handleChange = function (e) {
            _this6.props.onChange(e);
        }, _temp), _possibleConstructorReturn(_this6, _ret);
    }

    _createClass(FormGroup, [{
        key: "render",
        value: function render() {
            var _this7 = this;

            var _props4 = this.props,
                ph = _props4.ph,
                readonly = _props4.readonly,
                req = _props4.req,
                text = _props4.text,
                title = _props4.title,
                type = _props4.type,
                value = _props4.value;

            return React.createElement(
                "div",
                { className: "form-group" },
                React.createElement(
                    "label",
                    { htmlFor: title, className: "form-label" },
                    " ",
                    text,
                    ":"
                ),
                function () {
                    switch (type) {
                        case "textarea":
                            return React.createElement("textarea", {
                                className: "form-control",
                                name: title,
                                onChange: _this7.handleChange,
                                required: true, placeholder: ph,
                                rows: "3",
                                value: value
                            });
                        case "file":
                            return React.createElement("input", {
                                accept: "image/*,audio/*,video/*",
                                name: title,
                                required: req,
                                type: "file"
                            });
                        default:
                            return React.createElement("input", Object.assign({
                                className: "form-control",
                                name: title
                            }, type === "number" ? { min: "1" } : {}, {
                                onChange: _this7.handleChange,
                                placeholder: ph,
                                readOnly: readonly,
                                required: true,
                                type: type,
                                value: value
                            }));
                    }
                }()
            );
        }
    }]);

    return FormGroup;
}(React.Component);

;
FormGroup.defaultProps = {
    readonly: false,
    req: true
};

var FormQuestion = function (_React$Component7) {
    _inherits(FormQuestion, _React$Component7);

    function FormQuestion(props) {
        _classCallCheck(this, FormQuestion);

        var _this8 = _possibleConstructorReturn(this, (FormQuestion.__proto__ || Object.getPrototypeOf(FormQuestion)).call(this, props));

        _this8.state = {
            view: false,
            id: 0
        };

        _this8.changeView = function (data) {
            fetch('CreateQuestion', { method: 'POST', body: data }).then(function (response) {
                if (response.ok) _this8.setState({ view: true, id: data.get("id") });
            });
        };

        return _this8;
    }

    _createClass(FormQuestion, [{
        key: "render",
        value: function render() {

            return this.state.view ? React.createElement(Feedback, { id: this.state.id }) : React.createElement(Question, { vista: this.changeView });
        }
    }]);

    return FormQuestion;
}(React.Component);

var QuestionP = function (_React$Component8) {
    _inherits(QuestionP, _React$Component8);

    function QuestionP() {
        var _ref2;

        var _temp2, _this9, _ret2;

        _classCallCheck(this, QuestionP);

        for (var _len2 = arguments.length, args = Array(_len2), _key2 = 0; _key2 < _len2; _key2++) {
            args[_key2] = arguments[_key2];
        }

        return _ret2 = (_temp2 = (_this9 = _possibleConstructorReturn(this, (_ref2 = QuestionP.__proto__ || Object.getPrototypeOf(QuestionP)).call.apply(_ref2, [this].concat(args))), _this9), _this9.state = {
            id: "",
            name: "",
            qtype: "",
            question: "",
            max: ""
        }, _this9.handleChange = function (e) {
            var target = e.target;
            var value = target.type === 'file' ? target.files[0] : target.value;
            var name = target.name;
            _this9.setState(_defineProperty({}, name, value));
        }, _this9.handleSubmit = function (event) {
            event.preventDefault();
            var data = new FormData(event.target);
            _this9.props.vista(data);
        }, _temp2), _possibleConstructorReturn(_this9, _ret2);
    }

    _createClass(QuestionP, [{
        key: "render",
        value: function render() {
            var _React$createElement;

            return React.createElement(
                "form",
                (_React$createElement = { onSubmit: this.handleSubmit, method: "POST", id: "formQP", className: "container text-left" }, _defineProperty(_React$createElement, "id", "formQP"), _defineProperty(_React$createElement, "enctype", "multipart/form-data"), _React$createElement),
                React.createElement(FormGroup, {
                    onChange: this.handleChange,
                    ph: "Question ID",
                    text: "ID",
                    title: "id",
                    type: "number",
                    value: this.state.id
                }),
                React.createElement(FormGroup, {
                    onChange: this.handleChange,
                    ph: "Question Name",
                    text: "Name",
                    title: "name",
                    type: "text",
                    value: this.state.name
                }),
                React.createElement(FormGroup, {
                    onChange: this.handleChange,
                    ph: "Question Text",
                    text: "Question",
                    title: "question",
                    type: "textarea",
                    value: this.state.question
                }),
                React.createElement(FormGroup, {
                    onChange: this.handleChange,
                    ph: "Choose a media file",
                    text: "Media File",
                    title: "media",
                    type: "file"
                }),
                React.createElement(FormGroup, {
                    onChange: this.handleChange,
                    ph: "Amount",
                    text: "Maximum number of options",
                    title: "max",
                    type: "number"
                }),
                React.createElement("input", { type: "hidden", name: "qtype", value: "partial", id: "qtype" }),
                React.createElement("input", { type: "submit", value: "Next", className: "btn btn-block btn-primary mb-2" })
            );
        }
    }]);

    return QuestionP;
}(React.Component);

;

var Question = function (_React$Component9) {
    _inherits(Question, _React$Component9);

    function Question() {
        var _ref3;

        var _temp3, _this10, _ret3;

        _classCallCheck(this, Question);

        for (var _len3 = arguments.length, args = Array(_len3), _key3 = 0; _key3 < _len3; _key3++) {
            args[_key3] = arguments[_key3];
        }

        return _ret3 = (_temp3 = (_this10 = _possibleConstructorReturn(this, (_ref3 = Question.__proto__ || Object.getPrototypeOf(Question)).call.apply(_ref3, [this].concat(args))), _this10), _this10.state = {
            answer: "",
            id: "",
            media: null,
            name: "",
            qtype: "",
            question: ""
        }, _this10.handleChange = function (e) {
            var target = e.target;
            var value = target.type === 'file' ? target.files[0] : target.value;
            var name = target.name;
            _this10.setState(_defineProperty({}, name, value));
        }, _this10.handleSubmit = function (event) {
            event.preventDefault();
            var data = new FormData(event.target);
            _this10.props.vista(data);
        }, _temp3), _possibleConstructorReturn(_this10, _ret3);
    }

    _createClass(Question, [{
        key: "render",
        value: function render() {
            var _React$createElement2;

            return React.createElement(
                "form",
                (_React$createElement2 = { onSubmit: this.handleSubmit, method: "POST", id: "formQ", className: "container text-left" }, _defineProperty(_React$createElement2, "id", "formQ"), _defineProperty(_React$createElement2, "enctype", "multipart/form-data"), _React$createElement2),
                React.createElement(FormGroup, {
                    onChange: this.handleChange,
                    ph: "Question ID",
                    text: "ID",
                    title: "id",
                    type: "number",
                    value: this.state.id
                }),
                React.createElement(FormGroup, {
                    onChange: this.handleChange,
                    ph: "Question Name",
                    text: "Name",
                    title: "name",
                    type: "text",
                    value: this.state.name
                }),
                React.createElement(FormGroup, {
                    onChange: this.handleChange,
                    ph: "Question Text",
                    text: "Question",
                    title: "question",
                    type: "textarea",
                    value: this.state.question
                }),
                React.createElement(FormGroup, {
                    onChange: this.handleChange,
                    ph: "Question Answer",
                    text: "Answer",
                    title: "answer",
                    type: "text",
                    value: this.state.answer
                }),
                React.createElement(FormGroup, {
                    onChange: this.handleChange,
                    ph: "Choose a media file",
                    text: "Media File",
                    title: "media",
                    type: "file"
                }),
                React.createElement("input", { type: "hidden", name: "qtype", value: "fill", id: "qtype" }),
                React.createElement("input", { type: "submit", value: "Next", className: "btn btn-block btn-primary mb-2" })
            );
        }
    }]);

    return Question;
}(React.Component);

;

var Feedback = function (_React$Component10) {
    _inherits(Feedback, _React$Component10);

    function Feedback() {
        var _ref4;

        var _temp4, _this11, _ret4;

        _classCallCheck(this, Feedback);

        for (var _len4 = arguments.length, args = Array(_len4), _key4 = 0; _key4 < _len4; _key4++) {
            args[_key4] = arguments[_key4];
        }

        return _ret4 = (_temp4 = (_this11 = _possibleConstructorReturn(this, (_ref4 = Feedback.__proto__ || Object.getPrototypeOf(Feedback)).call.apply(_ref4, [this].concat(args))), _this11), _this11.state = {
            id: _this11.props.id,
            tries: "",
            initial: "",
            evaluate: "",
            correct: "",
            incorrect: "",
            triesFB: ""
        }, _this11.handleChange = function (e) {
            var target = e.target;
            var name = target.name;
            _this11.setState(_defineProperty({}, name, target.value));
        }, _temp4), _possibleConstructorReturn(_this11, _ret4);
    }

    _createClass(Feedback, [{
        key: "render",
        value: function render() {
            var _React$createElement3;

            console.log(this.props.id);
            return React.createElement(
                "form",
                (_React$createElement3 = { action: "FeedbackQuestion", method: "POST", id: "formQ", className: "container text-left" }, _defineProperty(_React$createElement3, "id", "formQ"), _defineProperty(_React$createElement3, "enctype", "multipart/form-data"), _React$createElement3),
                React.createElement(FormGroup, {
                    onChange: this.handleChange,
                    ph: "Question ID",
                    readonly: true,
                    text: "ID",
                    title: "id",
                    type: "number",
                    value: this.state.id
                }),
                React.createElement(FormGroup, {
                    onChange: this.handleChange,
                    ph: "Question Tries",
                    text: "Tries",
                    title: "tries",
                    type: "number",
                    value: this.state.tries
                }),
                React.createElement(FormGroup, {
                    onChange: this.handleChange,
                    ph: "Initial Feedback",
                    text: "Initial Feedback",
                    title: "initial",
                    type: "text",
                    value: this.state.initial
                }),
                React.createElement(FormGroup, {
                    onChange: this.handleChange,
                    ph: "Evaluate Feedback",
                    text: "Evaluate Feedback",
                    title: "evaluate",
                    type: "text",
                    value: this.state.evaluate
                }),
                React.createElement(FormGroup, {
                    onChange: this.handleChange,
                    ph: "Correct Feedback",
                    text: "Correct Feedback",
                    title: "correct",
                    type: "text",
                    value: this.state.correct
                }),
                React.createElement(FormGroup, {
                    onChange: this.handleChange,
                    ph: "Incorrect Feedback",
                    text: "Incorrect Feedback",
                    title: "incorrect",
                    type: "text",
                    value: this.state.incorrect
                }),
                React.createElement(FormGroup, {
                    onChange: this.handleChange,
                    ph: "Tries Feedback",
                    text: "Tries Feedback",
                    title: "triesFB",
                    type: "text",
                    value: this.state.triesFB
                }),
                React.createElement("input", { type: "submit", value: "Next", className: "btn btn-block btn-primary mb-2" })
            );
        }
    }]);

    return Feedback;
}(React.Component);

;

var FeedbackP = function (_React$Component11) {
    _inherits(FeedbackP, _React$Component11);

    function FeedbackP() {
        var _ref5;

        var _temp5, _this12, _ret5;

        _classCallCheck(this, FeedbackP);

        for (var _len5 = arguments.length, args = Array(_len5), _key5 = 0; _key5 < _len5; _key5++) {
            args[_key5] = arguments[_key5];
        }

        return _ret5 = (_temp5 = (_this12 = _possibleConstructorReturn(this, (_ref5 = FeedbackP.__proto__ || Object.getPrototypeOf(FeedbackP)).call.apply(_ref5, [this].concat(args))), _this12), _this12.state = {
            id: _this12.props.id,
            initial: "",
            evaluate: "",
            correct: "",
            incorrect: ""
        }, _this12.handleChange = function (e) {
            var target = e.target;
            var name = target.name;
            _this12.setState(_defineProperty({}, name, target.value));
        }, _temp5), _possibleConstructorReturn(_this12, _ret5);
    }

    _createClass(FeedbackP, [{
        key: "render",
        value: function render() {
            var _React$createElement4;

            console.log(this.props.id);
            return React.createElement(
                "form",
                (_React$createElement4 = { action: "CreateFeedbackP", method: "POST", id: "formQP", className: "container text-left" }, _defineProperty(_React$createElement4, "id", "formQP"), _defineProperty(_React$createElement4, "enctype", "multipart/form-data"), _React$createElement4),
                React.createElement(FormGroup, {
                    onChange: this.handleChange,
                    ph: "Question ID",
                    readonly: false,
                    text: "ID",
                    title: "id",
                    type: "number",
                    value: this.state.id
                }),
                React.createElement(FormGroup, {
                    onChange: this.handleChange,
                    ph: "Initial Feedback",
                    text: "Initial Feedback",
                    title: "initial",
                    type: "text",
                    value: this.state.initial
                }),
                React.createElement(FormGroup, {
                    onChange: this.handleChange,
                    ph: "Evaluate Feedback",
                    text: "Evaluate Feedback",
                    title: "evaluate",
                    type: "text",
                    value: this.state.evaluate
                }),
                React.createElement(FormGroup, {
                    onChange: this.handleChange,
                    ph: "Correct Feedback",
                    text: "Correct Feedback",
                    title: "correct",
                    type: "text",
                    value: this.state.correct
                }),
                React.createElement(FormGroup, {
                    onChange: this.handleChange,
                    ph: "Incorrect Feedback",
                    text: "Incorrect Feedback",
                    title: "incorrect",
                    type: "text",
                    value: this.state.incorrect
                }),
                React.createElement("input", { type: "submit", value: "Next", className: "btn btn-block btn-primary mb-2" })
            );
        }
    }]);

    return FeedbackP;
}(React.Component);

;

var Login = function (_React$Component12) {
    _inherits(Login, _React$Component12);

    function Login() {
        var _ref6;

        var _temp6, _this13, _ret6;

        _classCallCheck(this, Login);

        for (var _len6 = arguments.length, args = Array(_len6), _key6 = 0; _key6 < _len6; _key6++) {
            args[_key6] = arguments[_key6];
        }

        return _ret6 = (_temp6 = (_this13 = _possibleConstructorReturn(this, (_ref6 = Login.__proto__ || Object.getPrototypeOf(Login)).call.apply(_ref6, [this].concat(args))), _this13), _this13.state = {
            userName: "",
            password: ""
        }, _this13.handleChange = function (e) {
            var target = e.target;
            var name = target.name;
            console.log(target.name);
            _this13.setState(_defineProperty({}, name, e.target.value));
        }, _temp6), _possibleConstructorReturn(_this13, _ret6);
    }

    _createClass(Login, [{
        key: "render",
        value: function render() {
            return React.createElement(
                "form",
                { action: "/Moodle/Login", method: "POST", className: "container text-left" },
                React.createElement(FormGroup, {
                    title: "userName",
                    text: "User",
                    ph: "Username",
                    type: "text",
                    value: this.state.userName,
                    onChange: this.handleChange
                }),
                React.createElement(FormGroup, {
                    title: "password",
                    text: "Password",
                    ph: "Password",
                    type: "password",
                    value: this.state.password,
                    onChange: this.handleChange
                }),
                React.createElement("input", { type: "submit", value: "Access", className: "btn btn-block btn-primary mb-2" })
            );
        }
    }]);

    return Login;
}(React.Component);

;

var Header = function (_React$Component13) {
    _inherits(Header, _React$Component13);

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

var HeaderNotLogged = function (_React$Component14) {
    _inherits(HeaderNotLogged, _React$Component14);

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

var HeaderLogged = function (_React$Component15) {
    _inherits(HeaderLogged, _React$Component15);

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

function confirmar(id, type) {
    if (confirm("Do you really want to delete this question?")) {
        location.href = "Delete" + type + "?id=" + id;
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
export { FeedbackP, FormGroup, FormQuestion, Header, Login, TableObj, Welcome };