<%@taglib uri="/struts-tags" prefix="s" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
        integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <title>Create Question</title>
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
            <h1 class="text-center">Create a New Question</h1>
            <s:form action="CreateQuestion"  method="post" enctype="multipart/form-data">
                <div class="form-group">
                    <s:label for="id" theme="simple" cssClass="form-label" value="ID:"/>
                    <s:textfield name="id" id="id" theme="simple" cssClass="form-control" required="true" />
                </div>
                <div class="form-group">
                    <s:label for="name" theme="simple" cssClass="form-label" value="Name:"/>
                    <s:textfield name="name" id="name" theme="simple" cssClass="form-control" required="true" />
                </div>
                <div class="form-group">
                    <s:label for="question" theme="simple" cssClass="form-label" value="Question:"/>
                    <s:textfield name="question" id="question" theme="simple" cssClass="form-control" required="true" />
                </div>
                <div class="form-group">
                    <s:label for="answer" theme="simple" cssClass="form-label" value="Answer:"/>
                    <s:textfield name="answer" id="answer" theme="simple" cssClass="form-control" required="true" />
                </div>
                <div class="form-group">
                     <s:label for="media" theme="simple" cssClass="form-label" value="Media File:"/>
                     <s:file name="media" id="media" theme="simple" cssClass="form-control-file" required="true" accept="image/jpeg,image/jpg,image/png,audio/mp3,audio/mpeg,video/mpeg,video/mp4"/>
                </div>
                <s:submit value="Next" theme="simple" cssClass="btn btn-block btn-dark mb-2"/>
            </s:form>
        </div>
        
    </body>
</html>
