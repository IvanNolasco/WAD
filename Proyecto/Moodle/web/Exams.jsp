<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
        integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <title>Exams</title>
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
        <h1 class='text-center'>Exams Creation</h1>
        <s:form action="CreateExam.action" theme="simple" cssClass="text-center">
            <s:submit theme="simple" cssClass="btn btn-dark" value="Create Exam"/>
        </s:form>
        <table class="table table-striped table-borderless"> 
            <tr>
                <th>Exams</th>
                <th class="text-center">Actions</th>
            </tr>
            <s:iterator value="exams" var="c">
            <tr>
                <td>
                    <s:property/>
                    <s:set var="namevar" value="c"/>
                    <jsp:useBean id="namevar" type="java.lang.String" />
                </td>
                <td>
                    <div class="btn-group btn-block" role="group">
                        
                        <%
                            
                            out.print("<button type=\"button\" class=\"btn btn-link\" onclick=\"location.href='ViewExam.action?id="+namevar+"'\">View Exam</button>");
                            
                            out.print("<button type=\"button\" class=\"btn btn-link\" onclick=\"location.href='ModifyExam.action?id="+namevar+"'\">Modify Exam</button>");
                        
                            out.print("<button type=\"button\" class=\"btn btn-link\" onclick=\"confirmar('"+namevar+"')\">Delete Exam</button>");
                        %>
                        
                        
                    </div>                 
                </td>
            </tr>
            </s:iterator>        
        </table>
        
    </div>
        
        <script type="text/javascript">
            function confirmar(name){
                if (confirm("Do you really want to delete this question?")) {
                    location.href ="DeleteExam.action?name="+name;
                } else {
                    
                }
            }
        </script>
        
    </body>
</html>