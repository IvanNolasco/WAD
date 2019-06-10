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
        <div id="root"></div>       
        <!-- Cargar React. -->
        <script src="js/react.min.js" crossorigin></script>
        <script src="js/react-dom.min.js" crossorigin></script>
        <script type="module" src="js/createQuestion.js"></script>
        <script type="module" src="js/Components.js"></script>
        <script>
            var user = "<%=(String)session.getAttribute("userName")%>";
        </script>
        
         <script src="js/jquery-3.4.1.min.js"></script>
        <script>
            var i = 1;
            $("#addBtn").click(function (){
                var optList = document.getElementById("optionL");
            });
            $("#quitBtn").click(function (){
                var optList = document.getElementById("optionL");
            });
            // Add the following code if you want the name of the file appear on select
            $(".custom-file-input").on("change", function () {
                var fileName = $(this).val().split("\\").pop();
                $(this).siblings(".custom-file-label").addClass("selected").html(fileName);
            });
            function addOption(){
                var optList = document.getElementById("optionL");
                var col = document.createElement("div");

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
