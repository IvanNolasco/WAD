<%@taglib uri="/struts-tags" prefix="s" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <link rel="stylesheet" href="css/bootstrap.min.css" type="text/css">
        <title>Create Question</title>
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
                            <a class="nav-link" href="QuestionCreation">Questions</a>
                          </li>
                          <li class="nav-item active">
                            <a class="nav-link" href="ExamCreation">Exams</a>
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
            <h1 class="text-center">Create a New Partial Credit Question</h1>
            <s:form action="CreateQuestion"  method="post" enctype="multipart/form-data">
                <div class="form-group">
                    <s:label for="id" theme="simple" cssClass="form-label" value="ID:"/>
                    <s:textfield name="id" id="id" theme="simple" cssClass="form-control" required="true" placeholder="Question ID"/>
                </div>
                <div class="form-group">
                    <s:label for="name" theme="simple" cssClass="form-label" value="Name:"/>
                    <s:textfield name="name" id="name" theme="simple" cssClass="form-control" required="true" placeholder="Question Name"/>
                </div>
                <div class="form-group">
                    <s:label for="question" theme="simple" cssClass="form-label" value="Question:"/>
                    <s:textarea name="question" id="question" theme="simple" cssClass="form-control" required="true" placeholder="Question Text"/>
                </div>
                <div class="form-group">
                    <label class="form-label" >Maximum number of options:</label>
                    <input type="number" class="form-control" name="maxQuant" id="maxQuant" required="true" placeholder="Amount" min="1"/>
                </div>
                <div class="form-group">
                    <label class="for-label" >Media File:</label>
                    <div class="custom-file">
                        <label for="media" class="custom-file-label">Choose a file</label>
                        <input type="file" name="media" id="media" class="custom-file-input" required="true" accept="image/*,audio/*,video/*"/>
                    </div>
                </div>
             
                <div class="form-group">
                    <label class="form-label" >Options:</label>
                    <div class="row" id="optionL">
                        <div class="col-10">
                            <input type="text" class="form-control" name="optionList[0].text" placeholder="Option Answer"/> 
                        </div>
                        <div class="col-2">
                            <input type="number" class="form-control" name="optionList[0].points" placeholder="Points" min="1" max="5" />
                        </div>
                        <div class="col-10 mt-2">
                            <input type="text" class="form-control" name="optionList[1].text" placeholder="Option Answer"/> 
                        </div>
                        <div class="col-2 mt-2">
                            <input type="number" class="form-control" name="optionList[1].points" placeholder="Points" min="1" max="5" />
                        </div>
                    </div>
                    <input type="button" id="addBtn" class="btn btn-primary mt-2" value="Add option" onclick="addOption()" />
                    <input type="button" id="quitBtn" class="btn btn-primary mt-2" value="Quit option" onclick="quitOption()" />
                </div>               
                <s:hidden name="qtype" id="qtype" value="partial" />
                <s:submit value="Next" theme="simple" cssClass="btn btn-block btn-primary"/>
            </s:form>
        </div>
        <script src="js/jquery-3.4.1.min.js"></script>
        <script>
            var i = 2;
            var max;
            $("#addBtn").click(function (){
                var optList = document.getElementById("optionL");
                document.getElementById("maxQuant").setAttribute("max",optList.childElementCount / 2-1);
            });
            $("#quitBtn").click(function (){
                var optList = document.getElementById("optionL");
                var maxQuant = document.getElementById("maxQuant");
                maxQuant.setAttribute("max",optList.childElementCount / 2-1);
                if (maxQuant.value > maxQuant.getAttribute("max"))
                    maxQuant.value = maxQuant.getAttribute("max");
            });
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

                col = document.createElement("div");
                col.setAttribute("class","col-2 mt-2");
                input = document.createElement("input");
                input.setAttribute("type","number");
                input.setAttribute("class","form-control");
                input.setAttribute("name","optionList["+i+"].points");
                input.setAttribute("placeholder","Points");
                input.setAttribute("min","1");
                input.setAttribute("max","5");

                col.appendChild(input);
                optList.appendChild(col);
                i ++;               
            }
            function quitOption(){
                var optList = document.getElementById("optionL");
                if (optList.childElementCount/2 > 2) {
                    optList.removeChild(optList.lastChild);
                    optList.removeChild(optList.lastChild);
                    i--;
                }
            }
        </script>
    </body>
</html>
