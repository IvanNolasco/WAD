<<%@taglib uri="/struts-tags" prefix="s" %>
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
    <s:set var="userNameVar" value="userName"/>
    <jsp:useBean id="userNameVar" type="java.lang.String" />
    
    <header class="encabezado">
        <div class="container">
            <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
                <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo02" aria-controls="navbarTogglerDemo02" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <a class="navbar-brand" href="#">
                    <h1 class="display-6">Moodle</h1>
                </a>
                <div class="collapse navbar-collapse" id="navbarTogglerDemo02">
                    <ul class="navbar-nav mr-auto mt-2 mt-md-0">
                      <li class="nav-item active">
                          
                        <%
                            String userName = userNameVar;
                            session.setAttribute("userName", userName);
                        %>
                        <a class="nav-link" href="QuestionCreation.action">Questions<span class="sr-only">(current)</span></a>
                        
                      </li>
                    </ul>
                  </div>
                <span class="navbar-text">
                   <button type="button" class="btn btn-link text-light" onclick="location.href='Login.jsp'">Sing out</button>
               </span>
           </nav>
        </div>
     </header>
    <div class="container">
        <h1 class="h1 text-center mb-3" style="margin: 100px;">Welcome Teacher: <s:property value="userName" /> </h1>
    </div>
    
</body>
</html>