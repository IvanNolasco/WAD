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
                console.log(question);
                function eval(){
                    var maxPoints = 0;
                    var max = parseInt(question.max);
                    var options = document.getElementsByName("option");
                    var p = [];
                    var points = 0;
                    var correct = document.getElementById("feed");
                    for (var i = 0; i < options.length; i++) {
                        if (options[i].checked) {
                            console.log(options[i].value);
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
                        correct.innerHTML = "<div class='alert alert-success'><p>"+question.correct+", you've got "+points+" points of "+maxPoints+"</p></div>";
                        document.getElementById("send").disabled = true;
                    }else{
                        correct.innerHTML = "<div class='alert alert-danger'><p>"+question.incorrect+", you've got "+points+" points of "+maxPoints+"</p></div>";
                        document.getElementById("send").disabled = true;
                    }
                }
                function maxCheck(){
                    var options = document.getElementsByName("option");
                    var max = parseInt(question.max);
                    var checked = 0;
                    for (var i = 0; i < options.length; i++) {
                        if (options[i].checked) {
                            checked = checked + 1;
                        }
                    }
                    if (checked === max) {
                        for (var i = 0; i < options.length; i++){
                            if (!options[i].checked){
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