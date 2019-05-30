<%@taglib uri="/struts-tags" prefix="s" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <link rel="stylesheet" href="css/bootstrap.min.css" type="text/css">
        <title>Create Question</title>
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
            <h1 class="text-center">Create a New Fill in the blank Question</h1>
            <s:form action="CreateQuestion"  method="post" enctype="multipart/form-data">
                <div class="form-group">
                    <label for="id" class="form-label">ID:</label>
                    <input type="number" class="form-control" name="id" id="id" required placeholder="Question ID" min="1"/>
                </div>
                <div class="form-group">
                    <s:label for="name" theme="simple" cssClass="form-label" value="Name:"/>
                    <s:textfield name="name" id="name" theme="simple" cssClass="form-control" required="true" placeholder="Question Name"/>
                </div>
                <div class="form-group">
                    <s:label for="question" theme="simple" cssClass="form-label" value="Question:"/>
                    <s:textarea name="question" id="question" theme="simple" cssClass="form-control" required="true" placeholder="Question Text"/>
                </div>
                <div class="form-group">
                    <s:label for="answer" theme="simple" cssClass="form-label" value="Answer:"/>
                    <s:textfield name="answer" id="answer" theme="simple" cssClass="form-control" required="true" placeholder="Question Answer"/>
                </div>
                <div class="form-group">
                    <label class="for-label" >Media File:</label>
                    <div class="custom-file">
                        <label for="media" class="custom-file-label">Choose a file</label>
                        <input type="file" name="media" id="media" class="custom-file-input" required accept="image/*,audio/*,video/*"/>
                    </div>
                </div>
                     <s:hidden name="qtype" id="qtype" value="fill" />
                <s:submit value="Next" theme="simple" cssClass="btn btn-block btn-primary mb-2"/>
            </s:form>
        </div>
        
    </body>
</html>
