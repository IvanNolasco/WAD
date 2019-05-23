<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
        integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <title>Create Exam</title>
    </head>
    <body>
        <header class="encabezado">
        <div class="container">
            <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
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
        <h1 class='text-center'>Questions Creation</h1>
        <s:form action="CreateExam2.action" theme="simple">
            <div class="form-group row">
                <s:label for="nameE" theme="simple" cssClass="col col-form-label" value="Name:"/>
                <div class="col-11">
                    <s:textfield name="nameE" id="nameE" theme="simple" cssClass="form-control" required="true" placeholder="Nombre del examen"/>
                </div>                
            </div>
            <table class="table table-striped table-borderless text-center"> 
                <tr>
                    <th>Question Name</th>
                    <th>Actions</th>
                </tr>
                <s:iterator value="questions">
                <tr>
                    <td>
                        <s:checkbox name="questionList" value="false" fieldValue="%{id}"/>
                        <s:property value="name"/>
                        
                        <s:set var="idvar" value="id"/>
                        <jsp:useBean id="idvar" type="java.lang.String" />
                    </td>
                    <td>
                        <div class="btn-group btn-block" role="group" aria-label="Basic example">
                            <%="<button type=\"button\" class=\"btn btn-link\" onclick=\"location.href='ViewQuestion.action?id="+idvar+"'\">View Question</button>"%>  
                        </div>                 
                    </td>
                </tr>
                </s:iterator>        
            </table>
            <s:submit theme="simple" cssClass="btn btn-dark btn-block" value="Create Exam"/>
        </s:form>
    </div>
    </body>
</html>
