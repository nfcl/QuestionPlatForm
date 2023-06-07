<%--
  Created by IntelliJ IDEA.
  User: 煖風遲來
  Date: 2023/6/7
  Time: 17:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cierra</title>
    <link rel="stylesheet" href="./css/index.css">
    <link rel="stylesheet" href="./css/SignUp.css">
</head>
<body>

<div id="MyTitle">
    <h1>Cierra</h1>
</div>

<div id="MyNav">

    <jsp:include page="./NavigationBar.jsp"/>

</div>

<div class="MyCont">

    <div id="MyAside" class="MyAside">

        <jsp:include page="./AsideBar.jsp"/>

    </div>

    <div id="ContentFrame">

        <div align="center" id="MySignUp">

            <form action="Servlet_SignUp" method="post">
                <label class="MySignIn-Label">
                    名称<input name="Name" type="text" class="MyTextArea" required/>
                </label>
                <label class="MySignIn-Label">
                    账号<input name="Account" type="text" class="MyTextArea" required/>
                </label>
                <label class="MySignIn-Label">
                    密码<input name="Password" type="password" class="MyTextArea" autocomplete required/>
                </label>
                <label class="MySignIn-Label">
                    <%
                        if(request.getAttribute("SignUpFalseReason")!=null){

                            out.print(request.getAttribute("SignUpFalseReason"));

                            request.removeAttribute("SignUpFalseReason");

                        }
                    %>
                </label>
                <input class="MySignIn-Submit" type="submit" value="注册"/>
            </form>

        </div>

    </div>

</div>

</body>
</html>
