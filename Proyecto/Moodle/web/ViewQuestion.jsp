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
        <div id="root"></div>
        <div class="container">
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
                            <label for="ans" class="form-label">Your answer:</label>
                            <textarea name='ans' id='ans' class='form-control'></textarea>
                        </div>
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
            <!-- Cargar React. -->
            <script src="js/react.min.js" crossorigin></script>
            <script src="js/react-dom.min.js" crossorigin></script>
            <!-- Cargamos nuestro componente de React. -->
            <script type="module" src="js/viewQuestion.js"></script>
            <script type="module" src="js/Components.js"></script>
            <script>
                var user = "<%=(String)session.getAttribute("userName")%>";
                var tries = <%= varTries %>;
                function eval(){
                    var triesDiv = document.getElementById("tr")
                    var correctAns = "<%= varAns %>";
                    var correct = document.getElementById("feed");
                    var answer = document.getElementById("ans").value;
                    if (answer === correctAns) {
                        correct.innerHTML = "<div class='alert alert-success'><p><%= varCorrect %></p></div>";
                        document.getElementById("send").disabled = true;
                    }else if(tries > 0){
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
