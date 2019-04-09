<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inicio de Sesion</title>
    </head>
    <body>
        <html:form method="POST" action="ActionLogin">
            Usuario:<html:text property="nombre" /><br />
            Password:<html:password property="password" /><br />
            <html:submit />
        </html:form>
        
    </body>
</html>