<%@taglib uri="/struts-tags" prefix="s" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
        integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <title>Main Page</title>
    </head>
<body>
    <header class="encabezado">
        <div class="container">
            <nav class="navbar navbar-dark bg-dark">
                <a class="navbar-brand" href="#">
                    <h1 class="display-6">Moodle</h1>
                </a>
                 <span class="navbar-text">
                    <button type="button" class="btn btn-link text-light" onclick="location.href='Login.jsp'">Sing out</button>
                </span>
            </nav>
        </div>
     </header>
    <div class="container" id="contenido" theme="simple" cssClass="border border-primary">
        <h1 class="h1 text-center mb-3" style="margin: 100px;">Welcome Student: <s:property value="userName" /> </h1>
    </div>
    
</body>
</html>