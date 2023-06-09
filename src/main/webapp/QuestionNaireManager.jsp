<%--
  Created by IntelliJ IDEA.
  User: 煖風遲來
  Date: 2023/6/9
  Time: 18:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="Sessions.QuestionNaireInfo" %>
<%@ page import="java.util.ArrayList" %>
<html>
<head>
    <title>Cierra</title>
    <link rel="stylesheet" href="./css/index.css">
    <link rel="stylesheet" href="./css/QuestionNaireManager.css">
    <script src="./js/QuestionNaireList.js"></script>
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
        <jsp:include page="AsideBar.jsp"/>
    </div>
    <div id="ContentFrame">
        <ul id="QuestionNaireListView">
            <%
                ArrayList<QuestionNaireInfo> questionNaireInfos = (ArrayList<QuestionNaireInfo>) request.getAttribute("QuestionNaireInfos");
                request.removeAttribute("QuestionNaireInfos");
                for (QuestionNaireInfo naire : questionNaireInfos) {
                    out.println("<li class='QuestionNaireListView-li'>");
                    out.println("   <div class='QuestionNaire-QuestionInfo'>");
                    out.println("       <h1 class='QuestionNaireListView-li-Title'>" + naire.getTitle() + "</h1>");
                    out.println("       <div class='QuestionNaireListView-li-User'>" + naire.getCreator() + "</div>");
                    out.println("       <div class='QuestionNaireListView-li-StartTime'>" + naire.getStartTime() + "</div>");
                    out.println("   </div>");
                    out.println("   <a class='QuestionNaire-QuestionDelete' href='Servlet_DeleteQuestionNaire?QuestionNaireId=" + naire.getId() + "'>删除</a>");
                    out.println("</li>");
                }
            %>
            <%--<li class="QuestionNaireListView-li">--%>
            <%--    <div class="QuestionNaire-QuestionInfo">--%>
            <%--        <h1 class="QuestionNaireListView-li-Title">问卷标题</h1>--%>
            <%--        <div class="QuestionNaireListView-li-User">问卷发起人</div>--%>
            <%--        <div class="QuestionNaireListView-li-StartTime">问卷开始时间</div>--%>
            <%--    </div>--%>
            <%--    <div class="QuestionNaire-QuestionDelete">删除</div>--%>
            <%--</li>--%>
        </ul>
        <div align="center">
            <form action="Servlet_GetQuestionNaireList?forward=QuestionNaireManager.jsp" method="post">
                <input type="hidden" name="CurrentPageNum" value="
                        <%
                            if (request.getAttribute("CurrentPageNum") == null) {
                                out.print(1);
                            } else {
                                out.print((int) request.getAttribute("CurrentPageNum"));
                            }
                        %>">
                <input type="submit" class="JumpButton" onclick="QuestionNaireListPageHome()" value="|<<">
                <input type="submit" class="JumpButton" onclick="QuestionNaireListPageUp()" value="<">
                <label id="ListPageNum">
                    <%
                        if (request.getAttribute("CurrentPageNum") == null) {
                            out.print(1);
                        } else {
                            out.print((int) request.getAttribute("CurrentPageNum") + 1);
                        }
                    %>
                </label>
                <input type="submit" class="JumpButton" onclick="QuestionNaireListPageDn()" value=">">
                <input type="submit" class="JumpButton" onclick="QuestionNaireListPageEnd()" value=">>|">
            </form>
        </div>
    </div>
</div>
</body>
</html>