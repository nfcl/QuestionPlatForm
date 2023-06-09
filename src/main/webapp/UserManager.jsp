<%--
  Created by IntelliJ IDEA.
  User: 煖風遲來
  Date: 2023/6/9
  Time: 15:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.LinkedList" %>
<%@ page import="Sessions.User" %>
<html>
<head>
    <title>Cierra</title>
    <link rel="stylesheet" href="./css/index.css">
    <link rel="stylesheet" href="./css/UserManager.css">
    <script src="https://libs.baidu.com/jquery/2.1.4/jquery.min.js"></script>
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
        <ul id="UserList-ul">
            <%

                LinkedList<User> userList = (LinkedList<User>) request.getAttribute("UserList");

                for (User user : userList) {

                    out.println("<li class='UserList-li'>");
                    out.println("   " + user.Name + "<input type='button' value='删除' onclick='DeleteUser(" + user.Id + ")'>");
                    out.println("</li>");

                }

            %>
        </ul>
    </div>
</div>
<script>

    DeleteUser = function (userId) {

        $.post("./Servlet_DropUser?UserId=" + userId,
            function () {

                window.location.href = "./Servlet_GetUserList";

            }
        );

    }


</script>
</body>
</html>
