<%@taglib uri="/struts-tags" prefix="s" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Welcome Page</title>
</head>
<body>
    <h1>Welcome Teacher: <s:property value="userName" /></h1> 
    <button type="submit" formaction="QuestionCreation">Question Creation</button>>
    <s:a href="Login.jsp">Sing out</s:a>
</body>
</html>