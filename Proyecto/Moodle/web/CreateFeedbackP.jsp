<%@taglib uri="/struts-tags" prefix="s" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <link rel="stylesheet" href="css/bootstrap.min.css" type="text/css">
        <title>Create Feedback</title>
    </head>
    <body>
        <div id="root"></div> 
        <s:set var="idvar" value="id"/>
        <jsp:useBean id="idvar" type="java.lang.String" />
        <!-- Cargar React. -->
        <script src="js/react.min.js" crossorigin></script>
        <script src="js/react-dom.min.js" crossorigin></script>
        <script type="module" src="js/createQuestionP.js"></script>
        <script type="module" src="js/Components.js"></script>
        <script>
            var user = "<%=(String)session.getAttribute("userName")%>";
            var id = "<%= idvar %>";
        </script>
    </body>
</html>
