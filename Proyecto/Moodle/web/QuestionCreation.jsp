<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Questions Creation</title>
    </head>
    <body>
        <h1>Questions Creation</h1>
        
        <s:form action="CreateQuestion">
            <s:submit value="Create Question"/>
        </s:form>
    
        <table class="egt"> 
            <tr>
                <td>Questions</td>
                <td>Actions</td>
            </tr>
            <s:iterator value="questions" >
            <tr>
                <td>
                    <s:property />
                </td>
                <td>
                    <a href="ViewQuestion.jsp">View Question</a> |
                    <a href="ModifyQuestion.jsp">Modify Question</a> |
                    <a href="DeleteQuestion.jsp">Delete Question</a>
                </td>
            </tr>
            </s:iterator>
                
        </table>
    
        <s:a href="Login.jsp">Sing out</s:a>
    </body>
</html>
