<%@page import="java.util.List"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <link rel="stylesheet" href="css/bootstrap.min.css" type="text/css">
        <title>Modify Question</title>
    </head>
    <body>
        <header class="encabezado">
            <div class="container">
                <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
                    <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo02" aria-controls="navbarTogglerDemo02" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <a class="navbar-brand" href="WelcomeTeacher.jsp">
                        <h1 class="display-6">Moodle</h1>
                    </a>
                    <div class="collapse navbar-collapse" id="navbarTogglerDemo02">
                        <ul class="navbar-nav mr-auto mt-2 mt-md-0">
                          <li class="nav-item active"> 
                            <a class="nav-link" href="QuestionCreation.action">Questions</a>
                          </li>
                          <li class="nav-item active">
                            <a class="nav-link" href="ExamCreation.action">Exams</a>
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
            <h1 class="text-center">Modify Question</h1>
            <s:form action="ModifyQuestion2"  method="post" enctype="multipart/form-data">
                <s:set var="sourcevar" value="mediaFileName"/>
                <jsp:useBean id="sourcevar" type="java.lang.String" />
                <s:set var="contentvar" value="mediaContentType"/>
                <jsp:useBean id="contentvar" type="java.lang.String" />
                    
                <div class="form-group">
                    <s:label for="id" theme="simple" cssClass="form-label" value="ID:"/>
                    <s:textfield name="id" id="id" theme="simple" cssClass="form-control" required="true" value="%{id}" readonly="true"/>
                </div>
                <div class="form-group">
                    <s:label for="name" theme="simple" cssClass="form-label" value="Name:"/>
                    <s:textfield name="name" id="name" theme="simple" cssClass="form-control" required="true" value="%{name}"/>
                </div>
                <div class="form-group">
                    <s:label for="question" theme="simple" cssClass="form-label" value="Question:"/>
                    <s:textfield name="question" id="question" theme="simple" cssClass="form-control" required="true" value="%{question}"/>
                </div>
                <%
                        out.print("<input type=\"text\" name= \"mediaFileName\" id=\"mediaFileName\" class=\"form-control\" required=\"true\" value=\""+sourcevar+"\" style='display:none' />");
                        out.print("<input type=\"text\" name= \"mediaContentType\" id=\"mediaContentType\" class=\"form-control\" required=\"true\" value=\""+contentvar+"\" style='display:none' />");  
                %>
                <div class="form-group">
                    <label class="for-label" >Media File:</label>
                    <div class="custom-file">
                        <label for="media" class="custom-file-label">Choose a file</label>
                        <input type="file" name="media" id="media" class="custom-file-input" accept="image/*,audio/*,video/*"/>
                    </div>
                    <div class="alert alert-warning" role="alert">
                        If you do not choose a new file, it will stay the original.
                    </div>
                </div>
                     
               <s:hidden name="qtype" id="qtype" value="fill" />
               
               
               <label class="form-label" >Options:</label>
                <div class="row" id="optionL">
                    <s:set var="optionsvar" value="optionList"/>
                    <jsp:useBean id="optionsvar" type="java.util.ArrayList" />
                     <%  
                         List<String> op = optionsvar;
                         int j=0; %>
                         
                    <s:iterator value="optionList" var="text">
                        <div class="col-10">
                            <s:set var="textvar" value="text"/>
                            <jsp:useBean id="textvar" type="java.lang.String" />
                            <%
                                    int n=j;
                                    String text=textvar;
                                    out.println("<input type='text' class='form-control' name='optionList["+n+"]' placeholder='Option Answer' value='"+text+"'/> ");
                                    j++;
                            %>
                        </div>
                        </s:iterator>
                 </div>
                <input type="button" id="addBtn" class="btn btn-primary mt-2" value="Add option" onclick="addOption()" />
                <input type="button" id="quitBtn" class="btn btn-primary mt-2" value="Quit option" onclick="quitOption()" />
                <p></p>
                <div class="form-check align-center">
                     <s:set var="casevar" value="casechk"/>
                     <jsp:useBean id="casevar" type="java.lang.String" />
                     <s:set var="exactvar" value="casechk"/>
                     <jsp:useBean id="exactvar" type="java.lang.String" />
                     <div class="form-check align-center">
                    <%
                        if (casevar=="false"){
                            out.println("<input class='form-check1-input' type='checkbox' id='casechk' value='false' name='casechk' />");
                            out.println("<label class='form-check1-label' for='casechk'>Case Sensitive</label>");
                        }else{
                            out.println("<input class='form-check1-input' type='checkbox' id='casechk' name='casechk' checked/>");
                            out.println("<label class='form-check1-label' for='casechk'>Case Sensitive</label>");
                        }
                   
                    %>   
                    </div>
                    <div class="form-check align-center">
                    <%
                        if (exactvar=="false"){
                            out.println("<input class='form-check2-input' type='checkbox' id='exactchk' value='false' name='exactchk' />");
                            out.println("<label class='form-check2-label' for='exactchk'>Exact Result</label>");
                        }else{
                            out.println("<input class='form-check2-input' type='checkbox' id='exactchk' name='exactchk' checked/>");
                            out.println("<label class='form-check2-label' for='exactchk'>Exact Result</label>");
                        }
                    %>
                </div>
               
                <s:submit value="Next" theme="simple" cssClass="btn btn-block btn-primary mb-2"/>
            </s:form>
        </div>
        
                 <script src="js/jquery-3.4.1.min.js"></script>
        <script>
            var i = document.getElementById("optionL").childElementCount;
            console.log(i)
            // Add the following code if you want the name of the file appear on select
            $(".custom-file-input").on("change", function () {
                var fileName = $(this).val().split("\\").pop();
                $(this).siblings(".custom-file-label").addClass("selected").html(fileName);
            });
            function addOption(){
                var optList = document.getElementById("optionL");
                var col = document.createElement("div");
                col.setAttribute("class","col-10 mt-2");

                var input = document.createElement("input");
                input.setAttribute("type","text");
                input.setAttribute("class","form-control");
                input.setAttribute("name","optionList["+i+"].text");
                input.setAttribute("placeholder","Option Answer");

                col.appendChild(input);
                optList.appendChild(col);
                
                i ++;               
            }
            function quitOption(){
                var optList = document.getElementById("optionL");
                if (optList.childElementCount > 1) {
                    optList.removeChild(optList.lastChild);
                    i--;
                }
            }
        </script>
    </body>
</html>

