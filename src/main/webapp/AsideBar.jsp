<%--
  Created by IntelliJ IDEA.
  Sessions.User: 煖風遲來
  Date: 2023/6/7
  Time: 13:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" %>
<%@ page import="Sessions.User" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="./css/AsideBar.css">
</head>
<body>
<div class='MyAside-Group'>
    <div class='MyAside-GroupTitle'>主页</div>
    <a class='MyAside-Item' href="Servlet_GetQuestionNaireList?forward=QuestionNaireList.jsp">查看问卷</a>
    <a class='MyAside-Item' href="CreateQuestionNaire.jsp">创建问卷</a>
</div>
<%

    User user= (User) session.getAttribute("User");

    if(user!=null && Objects.equals(user.IsAdmin, "True")){
        out.print(
            "<div class='MyAside-Group'>\n" +
            "    <div class='MyAside-GroupTitle'>管理员</div>\n" +
            "    <a class='MyAside-Item' href='Servlet_GetUserList'>用户</a>\n" +
            "    <a class='MyAside-Item' href='Servlet_GetQuestionNaireList?forward=QuestionNaireManager.jsp'>问卷</a>\n" +
            "    <a class='MyAside-Item'>答卷</a>\n" +
            "</div>"
        );
    }

%>
</body>
</html>
