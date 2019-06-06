<%@taglib uri="/struts-tags" prefix="s" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <link rel="stylesheet" href="css/bootstrap.min.css" type="text/css">
        <title>View Question</title>
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
            <h1 class="text-center">View Question</h1>
            <div class="row">
                <div class="col">
                   <p class="h4"><s:property value="initial" />:</p> 
                </div>
                 <div class="col" id="tr">                   
                    <p class='h4 text-right'>Max Options: <s:property value="maxQuant" /></p>
                </div>
            </div>           
            <p class="h2 text-center border border-dark rounded bg-light"><s:property value="question" /></p>
            <div class="row">
                <div class="col">
                    <form  method="post" enctype="multipart/form-data">
                        <s:iterator value="optionList">
                            <div class="form-check ml-4">
                                <s:checkbox name="questionList" theme="simple" onchange="maxCheck()" name="option" cssClass="form-check-input" value="false" fieldValue="%{points}"/>
                                <label class="form-check-label" for="option">
                                    <s:property value="text"/>
                                </label>
                            </div>
                            
                        </s:iterator>
                        <div id="feed">
                        </div>
                        <input type="button" id="send" value="Send answer" onclick="eval()"  class="btn btn-block btn-primary mb-2"/>
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
            <s:set var="varCorrect" value="correct"/>
            <jsp:useBean id="varCorrect" type="java.lang.String" />
            <s:set var="varIncorrect" value="incorrect"/>
            <jsp:useBean id="varIncorrect" type="java.lang.String" />
            <s:set var="max" value="maxQuant"/>
            <jsp:useBean id="max" type="java.lang.String" />
            <script>
                function eval(){
                    var maxPoints = 0;
                    var max = <%= max %>;
                    var options = document.getElementsByName("option");
                    var p = [];
                    var points = 0;
                    var correct = document.getElementById("feed");
                    for (var i = 0; i < options.length; i++) {
                        if (options[i].checked) {
                            points = points + parseInt(options[i].value);
                        }
                        p[i] = parseInt(options[i].value);
                    }
                    p.sort().reverse();
                    for (var i = 0; i < max; i++) {
                        maxPoints = maxPoints + p[i];
                    }
                    console.log(points,",",maxPoints);
                    if (points === maxPoints) {
                        correct.innerHTML = "<div class='alert alert-success'><p><%= varCorrect %>, you've got "+points+" points of "+maxPoints+"</p></div>";
                        document.getElementById("send").disabled = true;
                    }else{
                        correct.innerHTML = "<div class='alert alert-danger'><p><%= varIncorrect %>, you've got "+points+" points of "+maxPoints+"</p></div>";
                        document.getElementById("send").disabled = true;
                    }
                }
                function maxCheck(){
                    var options = document.getElementsByName("option");
                    var max = <%= max %>;
                    var checked = 0;
                    for (var i = 0; i < options.length; i++) {
                        if (options[i].checked) {
                            checked = checked + 1;
                        }
                    }
                    console.log("checked",checked)
                    if (checked == max) {
                        console.log("checked2",checked)
                        for (var i = 0; i < options.length; i++){
                            if (!options[i].checked){
                                console.log("3:",options[i].checked);
                                options[i].disabled = true;
                            }
                        }
                    }
                    else {
                        for (var i = 0; i < options.length; i++){
                            options[i].disabled = false;
                        }
                    }
                }
            </script>
    </body>
</html>