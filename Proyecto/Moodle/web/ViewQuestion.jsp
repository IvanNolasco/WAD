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
            <s:set var="questionJSON" value="questionJSON"/>
            <jsp:useBean id="questionJSON" type="java.lang.String" />
            <!-- Cargar React. -->
            <script src="js/react.min.js" crossorigin></script>
            <script src="js/react-dom.min.js" crossorigin></script>
            <!-- Cargamos nuestro componente de React. -->
            <script type="module" src="js/viewQuestion.js"></script>
            <script type="module" src="js/Components.js"></script>
            <script>
                var user = "<%=(String)session.getAttribute("userName")%>";
                var questionJSON = '<%= questionJSON %>';
                var question = JSON.parse(questionJSON);
                var tries = parseInt(question.tries);
                function eval(){
                    var triesDiv = document.getElementById("tr");
                    var correctAns = question.options;
                    var correct = document.getElementById("feed");
                    var answer = document.getElementById("ans").value;
                    var sensitive = question.casechk === "true" ? true : false;
                    var exact = question.exactchk === "true" ? true : false;
                    var isCorrect = false;
                    console.log(correctAns);
                    for (var i = 0; i < correctAns.length; i++) {
                        if (sensitive) {
                            answer = answer.toLowerCase();
                            correctAns[i].text = correctAns[i].text.toLowerCase();
                        }
                        isCorrect = exact ? correctAns[i].text === answer : answer.includes(correctAns[i].text);
                        if (isCorrect) break;
                    }
                    console.log("Ans:",typeof correctAns);
                    if (isCorrect) {
                        correct.innerHTML = "<div class='alert alert-success'><p>"+question.correct+"</p></div>";
                        document.getElementById("send").disabled = true;
                    }else if(tries > 1){
                        triesDiv.innerHTML = "<p class='h4 text-right'>Tries:"+--tries+"</p>";
                        correct.innerHTML = "<div class='alert alert-warning'><p>"+question.triesFB+"</p></div>";
                        
                    }else{
                        triesDiv.innerHTML = "<p class='h4 text-right'>Tries:"+--tries+"</p>"
                        correct.innerHTML = "<div class='alert alert-danger'><p>"+question.incorrect+"</p></div>";
                        document.getElementById("send").disabled = true;
                    }
                }
            </script>
    </body>
</html>
