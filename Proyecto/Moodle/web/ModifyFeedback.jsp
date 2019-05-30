<%@taglib uri="/struts-tags" prefix="s" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <link rel="stylesheet" href="css/bootstrap.min.css" type="text/css">
        <title>Modify Question</title>
    </head>
    <body>
        <header class="encabezado">
            <div class="container">
                <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
                    <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo02" aria-controls="navbarTogglerDemo02" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <a class="navbar-brand" href="WelcomeTeacher.jsp">
                        <h1 class="display-6">Moodle</h1>
                    </a>
                    <div class="collapse navbar-collapse" id="navbarTogglerDemo02">
                        <ul class="navbar-nav mr-auto mt-2 mt-md-0">
                          <li class="nav-item active"> 
                            <a class="nav-link" href="QuestionCreation.action">Questions</a>
                          </li>
                          <li class="nav-item active">
                            <a class="nav-link" href="ExamCreation.action">Exams</a>
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
            <h1 class="text-center">Modify Feedback</h1>
            <s:form action="ModifyFeedback"  method="post" enctype="multipart/form-data">        
                <div class="form-group">
                    <s:label for="id" theme="simple" cssClass="form-label" value="ID:"/>
                    <s:textfield name="id" id="id" theme="simple" cssClass="form-control" required="true" value="%{id}" readonly="true"/>
                </div>
                <div class="form-group">
                    <s:label for="tries" theme="simple" cssClass="form-label" value="Tries:"/>
                    <s:textfield type="number" name="tries" id="tries" theme="simple" cssClass="form-control" required="true" min="1" value="%{tries}"/>
                </div>
                <div class="form-group">
                <s:label for="initial" theme="simple" cssClass="form-label" value="Initial Feedback:"/>
                <s:textfield name="initial" id="initial" theme="simple" cssClass="form-control" required="true" value="%{initial}"/>
                </div>
                <div class="form-group">
                <s:label for="evaluate" theme="simple" cssClass="form-label" value="Evaluate Feedback:"/>
                <s:textfield name="evaluate" id="evaluate" theme="simple" cssClass="form-control" required="true" value="%{evaluate}"/>
                </div>
                <div class="form-group">
                <s:label for="correct" theme="simple" cssClass="form-label" value="Correct Feedback:"/>
                <s:textfield name="correct" id="correct" theme="simple" cssClass="form-control" required="true" value="%{correct}"/>
                </div>
                <div class="form-group">
                <s:label for="incorrect" theme="simple" cssClass="form-label" value="Incorrect Feedback:"/>
                <s:textfield name="incorrect" id="incorrect" theme="simple" cssClass="form-control" required="true" value="%{incorrect}"/>
                </div>
                <div class="form-group">
                <s:label for="triesFB" theme="simple" cssClass="form-label" value="Tries Feedback:"/>
                <s:textfield name="triesFB" id="triesFB" theme="simple" cssClass="form-control" required="true" value="%{triesFB}"/>
                </div>
                <s:submit value="Next" theme="simple" cssClass="btn btn-block btn-primary mb-2"/>
            </s:form>
        </div>
        
    </body>
</html>
