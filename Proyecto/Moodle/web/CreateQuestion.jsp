<%@taglib uri="/struts-tags" prefix="s" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Question</title>
    </head>
    <body>
        <h1>Create a New Question</h1>
        <s:form action="CreateAction">
            <s:textfield name="ID" key="ID:" required="true" />
            <s:textfield name="question" key="Question:" required="true" />
            <s:textfield name="answer" key="Answer:" required="true" />
            <s:submit value="Next"/>
        </s:form>
    </body>
</html>
