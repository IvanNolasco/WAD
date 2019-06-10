<%@taglib uri="/struts-tags" prefix="s" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
     <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="css/bootstrap.min.css" type="text/css">
    <title>Main Page</title>
</head>
<body>
    <div id="root"></div>        
    <!-- Cargar React. -->
    <script src="js/react.min.js" crossorigin></script>
    <script src="js/react-dom.min.js" crossorigin></script>
    <script src="js/bootstrap.bundle.min.js"></script>
    <script src="js/jquery-3.4.1.min.js"></script>
    <script type="module" src="js/welcome.js"></script>
    <script type="module" src="js/Components.js"></script>
    <script>
        var user = "<%=(String)session.getAttribute("userName")%>";
    </script>
</body>
</html>