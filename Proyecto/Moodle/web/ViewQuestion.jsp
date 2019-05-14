<%@taglib uri="/struts-tags" prefix="s" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
        integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <title>View Question</title>
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
            <h1 class="text-center">View Question</h1>
            <div class="row">
                <div class="col">
                   <p class="h4"><s:property value="initial" />:</p> 
                </div>
                <div class="col" id="tr">                   
                    <p class='h4 text-right'>Tries: <s:property value="tries" /></p>
                </div>
            </div>           
            <p class="h2 text-center border border-dark rounded bg-light"><s:property value="question" /></p>
            <div class="row">
                <div class="col">
                    <form  method="post" enctype="multipart/form-data">
                        <div class="form-group">
                            <label for="ans" theme="simple" cssClass="form-label">Your answer:</label>
                            <textarea name='ans' id='ans' class='form-control'></textarea>
                        </div>
                        <div id="feed">
                        </div>
                        <input type="button" id="send" value="Send answer" onclick="eval()"  class="btn btn-block btn-dark mb-2"/>
                        <div class="alert alert-warning" role="alert">
                            <s:property value="evaluate" />
                        </div> 
                    </form>
                </div>
                <div class="col">

                    <s:set var="srcvar" value="source"/>
                    <s:set var="typevar" value="type"/>
                    <jsp:useBean id="srcvar" type="java.lang.String" />
                    <jsp:useBean id="typevar" type="java.lang.String" />
                    <% 
                        if (typevar.startsWith("image"))
                            out.print("<img src='"+srcvar+"' class='img-thumbnail mx-auto d-block' style='max-width:50%;width:auto;height:auto;'>");
                        if(typevar.startsWith("audio"))
                            out.print("<audio src='"+srcvar+"' controls />");
                        if(typevar.startsWith("video"))
                            out.print("<video src='"+srcvar+"' width='640' height='480' controls></video>");
                    %>
                </div>
            </div>           
        </div>
            <s:set var="varAns" value="answer"/>
            <jsp:useBean id="varAns" type="java.lang.String" />
            <s:set var="varCorrect" value="correct"/>
            <jsp:useBean id="varCorrect" type="java.lang.String" />
            <s:set var="varIncorrect" value="incorrect"/>
            <jsp:useBean id="varIncorrect" type="java.lang.String" />
            <s:set var="varTries" value="tries"/>
            <jsp:useBean id="varTries" type="java.lang.String" />
            <s:set var="varTriesFB" value="triesFB"/>
            <jsp:useBean id="varTriesFB" type="java.lang.String" />
            <script>
                window.tries = <%= varTries %>;
                function eval(){
                    var triesDiv = document.getElementById("tr")
                    var correctAns = "<%= varAns %>";
                    var correct = document.getElementById("feed");
                    var answer = document.getElementById("ans").value;
                    if (answer === correctAns) {
                        correct.innerHTML = "<div class='alert alert-success'><p><%= varCorrect %></p></div>";
                        document.getElementById("send").disabled = true;
                    }else if(tries > 1){
                        triesDiv.innerHTML = "<p class='h4 text-right'>Tries:"+--tries+"</p>"
                        correct.innerHTML = "<div class='alert alert-warning'><p><%= varTriesFB %></p></div>";
                        
                    }else{
                        correct.innerHTML = "<div class='alert alert-danger'><p><%= varIncorrect %></p></div>";
                        document.getElementById("send").disabled = true;
                    }
                }
            </script>
    </body>
</html>
