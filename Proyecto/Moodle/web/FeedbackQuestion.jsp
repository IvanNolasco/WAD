<%@taglib  uri="/struts-tags" prefix="s" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
        integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <title>Feedback Question</title>
    </head>
    <body>
        <header class="encabezado">
            <div class="container">
                <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
                    <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo02" aria-controls="navbarTogglerDemo02" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <a class="navbar-brand" href="#">
                        <h1 class="display-6">Moodle</h1>
                    </a>
                    <div class="collapse navbar-collapse" id="navbarTogglerDemo02">
                        <ul class="navbar-nav mr-auto mt-2 mt-md-0">
                          <li class="nav-item active">
                            <a class="nav-link" href="QuestionCreation.action">Questions<span class="sr-only">(current)</span></a>
                          </li>
                        </ul>
                      </div>
                    <span class="navbar-text">
                       <button type="button" class="btn btn-link text-light" onclick="location.href='Login.jsp'">Sing out</button>
                   </span>
               </nav>
            </div>
        </header>
        <div class="container">
            <h1 class="text-center">Feedback</h1>
            <s:form action="FeedbackQuestion">
                
                <s:set var="idvar" value="id"/>
                <jsp:useBean id="idvar" type="java.lang.String" />
                
                <div class="form-group">
                    <s:label for="id" theme="simple" cssClass="form-label" value="ID:"/>
                   <%
                        String id = idvar;
                        out.print("<input type='text' name='id' id='id' class='form-control' required='true' value='"+id+"' readonly/>");
                    %>
                    </div> 
                <div class="form-group">
                    <s:label for="tries" theme="simple" cssClass="form-label" value="Tries:"/>
                    <s:textfield name="tries" id="tries" theme="simple" cssClass="form-control" required="true" />
                </div>
                <div class="form-group">
                    <s:label for="initial" theme="simple" cssClass="form-label" value="Initial Feedback:"/>
                    <s:textfield name="initial" id="initial" theme="simple" cssClass="form-control" required="true" />
                </div>
                <div class="form-group">
                    <s:label for="evaluate" theme="simple" cssClass="form-label" value="Evaluate Feedback:"/>
                    <s:textfield name="evaluate" id="evaluate" theme="simple" cssClass="form-control" required="true" />
                </div>
                <div class="form-group">
                    <s:label for="correct" theme="simple" cssClass="form-label" value="Correct Feedback:"/>
                    <s:textfield name="correct" id="correct" theme="simple" cssClass="form-control" required="true" />
                </div>
                <div class="form-group">
                    <s:label for="incorrect" theme="simple" cssClass="form-label" value="Incorrect Feedback:"/>
                    <s:textfield name="incorrect" id="incorrect" theme="simple" cssClass="form-control" required="true" />
                </div>
                <div class="form-group">
                    <s:label for="triesFB" theme="simple" cssClass="form-label" value="Tries Feedback:"/>
                    <s:textfield name="triesFB" id="triesFB" theme="simple" cssClass="form-control" required="true" />
                </div>
                <s:submit value="Save Question" theme="simple" cssClass="btn btn-block btn-dark mb-2"/>
            </s:form>
        </div>
    </body>
</html>
