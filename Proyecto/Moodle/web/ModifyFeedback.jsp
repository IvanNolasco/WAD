<%@taglib uri="/struts-tags" prefix="s" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
        integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <title>Modify Question</title>
    </head>
    <body>
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
            <h1 class="text-center">Modify Feedback</h1>
            <s:form action="ModifyFeedback"  method="post" enctype="multipart/form-data">
                
                <s:set var="idvar" value="id"/>
                <jsp:useBean id="idvar" type="java.lang.String" />
                <s:set var="triesvar" value="tries"/>
                <jsp:useBean id="triesvar" type="java.lang.String" />
                <s:set var="initialvar" value="initial"/>
                <jsp:useBean id="initialvar" type="java.lang.String" />
                <s:set var="evaluatevar" value="evaluate"/>
                <jsp:useBean id="evaluatevar" type="java.lang.String" />
                <s:set var="correctvar" value="correct"/>
                <jsp:useBean id="correctvar" type="java.lang.String" />
                <s:set var="incorrectvar" value="incorrect"/>
                <jsp:useBean id="incorrectvar" type="java.lang.String" />
                <s:set var="triesFBvar" value="triesFB"/>
                <jsp:useBean id="triesFBvar" type="java.lang.String" />
                    
                <div class="form-group">
                    <s:label for="id" theme="simple" cssClass="form-label" value="ID:"/>
                    <%
                        String id = idvar;
                        out.print("<input type=\"text\" name=\"id\" id=\"id\" class=\"form-control\" required=\"true\" value=\""+id+"\" readonly/>");
                    %>
                </div>
                <div class="form-group">
                <s:label for="tries" theme="simple" cssClass="form-label" value="Tries:"/>
                <%
                    String tries = triesvar;
                    out.print("<input type='text' name='tries' id='tries' class='form-control' required='true' value='"+tries+"'/>");
                %>
                </div>
                <div class="form-group">
                <s:label for="initial" theme="simple" cssClass="form-label" value="Initial Feedback:"/>
                <%
                    String initial = initialvar;
                    out.print("<input type='text' name='initial' id='initial' class='form-control' required='true' value='"+initial+"'/>");
                %>
                </div>
                <div class="form-group">
                <s:label for="evaluate" theme="simple" cssClass="form-label" value="Evaluate Feedback:"/>
                <%
                    String evaluate = evaluatevar;
                    out.print("<input type='text' name='evaluate' id='evaluate' class='form-control' required='true' value='"+evaluate+"'/>");
                %>
                </div>
                <div class="form-group">
                <s:label for="correct" theme="simple" cssClass="form-label" value="Correct Feedback:"/>
                <%
                    String correct = correctvar;
                    out.print("<input type='text' name='correct' id='correct' class='form-control' required='true' value='"+correct+"'/>");
                %>
                </div>
                <div class="form-group">
                <s:label for="incorrect" theme="simple" cssClass="form-label" value="Incorrect Feedback:"/>
                <%
                    String incorrect = incorrectvar;
                    out.print("<input type='text' name='incorrect' id='incorrect' class='form-control' required='true' value='"+incorrect+"'/>");
                %>
                </div>
                <div class="form-group">
                <s:label for="tries" theme="simple" cssClass="form-label" value="Tries Feedback:"/>
                <%
                    String triesFB = triesFBvar;
                    out.print("<input type='text' name='triesFB' id='triesFB' class='form-control' required='true' value='"+triesFB+"'/>");
                %>
                </div>
                <s:submit value="Next" theme="simple" cssClass="btn btn-block btn-dark mb-2"/>
            </s:form>
        </div>
        
    </body>
</html>
