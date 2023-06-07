<%--
  Created by IntelliJ IDEA.
  Sessions.User: 煖風遲來
  Date: 2023/6/7
  Time: 8:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Cierra</title>
    <link rel="stylesheet" href="./css/index.css">
    <link rel="stylesheet" href="./css/SignIn.css">
</head>
<body>
<div id="MyTitle">
    <h1>Cierra</h1>
</div>
<div id="MyNav">

    <jsp:include page="NavigationBar.jsp"/>

</div>
<div class="MyCont">

    <div id="MyAside" class="MyAside">

        <jsp:include page="./AsideBar.jsp"/>

    </div>

    <div id="ContentFrame">
        <div align="center" id="MySignIn">
            <form action="Servlet_SignIn" method="post">
                <label class="MySignIn-Label">
                    账号<input name="Account" type="text" class="MyTextArea" required/>
                </label>
                <label class="MySignIn-Label">
                    密码<input name="Password" type="password" class="MyTextArea" autocomplete required/>
                </label>
                <label class="MySignIn-Label">
                    <%

                        if(request.getAttribute("SignInFalseReason")!=null){

                            out.print(request.getAttribute("SignInFalseReason"));

                            request.removeAttribute("SignInFalseReason");

                        }

                    %>
                </label>
                <input class="MySignIn-Submit" value="登录" type="submit" />
            </form>
        </div>
    </div>

</div>
</body>
</html>