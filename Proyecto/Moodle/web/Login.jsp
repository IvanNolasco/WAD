<%@taglib  uri="/struts-tags" prefix="s" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="css/bootstrap.min.css" type="text/css">
        <title>Login</title>
    </head>
    <body>
        <div id="root"></div>
       
        <!-- Cargar React. -->
        <!-- Nota: cuando se despliegue, reemplazar "development.js" con "production.min.js". -->
        <script src="js/react.min.js" crossorigin></script>
        <script src="js/react-dom.min.js" crossorigin></script>
        <!-- Cargamos nuestro componente de React. -->
        <script type="module" src="js/app.js"></script>
        <script type="module" src="js/Login.js"></script>
    </body>
</html>
