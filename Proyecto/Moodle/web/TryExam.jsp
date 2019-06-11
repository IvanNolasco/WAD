<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/bootstrap.min.css" type="text/css">
    </head>
    <body>
        <div id="root"></div>
        <s:set var="questions" value="questions"/>
        <jsp:useBean id="questions" type="java.lang.String" />
        <!-- Cargar React. -->
        <script src="js/react.min.js" crossorigin></script>
        <script src="js/react-dom.min.js" crossorigin></script>
        <!-- Cargamos nuestro componente de React. -->
        <script type="module" src="js/TryExam.js"></script>
        <script type="module" src="js/Components.js"></script>
        <script type="text/javascript">
            var user = "<%=(String)session.getAttribute("userName")%>";
            var questionsString = '<%=questions%>';
            var questions = JSON.parse(questionsString);
            console.log(questions);
        </script>

    </body>
</html>
