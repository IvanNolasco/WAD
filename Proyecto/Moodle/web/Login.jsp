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
        <header class="encabezado">
            <div class="container">
                <nav class="navbar navbar-dark bg-primary">
                    <a class="navbar-brand" href="#">
                        <h1 class="display-6">Moodle</h1>
                    </a>
                </nav>
            </div>
         </header>
        
        <div class="container" id="contenido" theme="simple" cssClass="border border-primary">
            <h1 class="h1 text-center mb-3" >Login</h1>
            <s:form action="Login" theme="simple">
               <div class="form-group">
                    <s:label for="userName" theme="simple" cssClass="form-label" value="User:"/>
                    <s:textfield name="userName" id="userName" theme="simple" cssClass="form-control" required="true"/>            
                </div>
               
                <div class="form-group">
                    <s:label for="password" theme="simple" cssClass="form-label" value="Password:"/>
                     <s:password name="password" theme="simple" cssClass="form-control" required="true"/>               
                </div>      
                <s:submit  theme="simple" id="password" value="Access" cssClass="btn btn-block btn-primary mb-2"  />
                <s:actionmessage  />
            </s:form> 
        </div>
    </body>
</html>
