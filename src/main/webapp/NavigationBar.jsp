<%--
  Created by IntelliJ IDEA.
  Sessions.User: 煖風遲來
  Date: 2023/6/7
  Time: 15:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="Sessions.User" contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="./css/NavigationBar.css">
</head>
<body>
<%

    User user= (User)session.getAttribute("User");

    if(user != null){

        out.print(
            "<a href='Servlet_SignOff' class='MyNav-Item'>退出</a>" +
            "<div class='MyNav-Item'>" + user.Name + "</div>"
        );

    }
    else{

        out.print(
            "<a href='./SignIn.jsp' class='MyNav-Item'>登录</a>" +
            "<a href='./SignUp.jsp' class='MyNav-Item'>注册</a>"
        );

    }

%>
</body>
</html>
