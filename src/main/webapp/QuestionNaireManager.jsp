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
    <link rel="stylesheet" href="./css/PageJumpButton.css">
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
            <%
                //列表最大页数
                int MaxPageNum = (int) request.getAttribute("MaxPageNum");
                //当前位于页数
                int CurrentPageNum = (int) request.getAttribute("CurrentPageNum");

                int StartShowNum = 3;
                int EndShowNum = 3;
                int LeftShowNum = 2;
                int RightShowNum = 2;

                //        C         M
                //          5 4 3 2 1
                //1 2 3 4 5 6 7 8 9 10

                if (StartShowNum + LeftShowNum + 1 >= CurrentPageNum) {

                    for (int i = 1; i < CurrentPageNum; ++i) {

                        out.println("<a class='PageJumpButton' href='Servlet_GetQuestionNaireList?forward=QuestionNaireManager.jsp&CurrentPageNum=" + i + "'>");
                        out.println(i);
                        out.println("</a>");

                    }

                } else {

                    for (int i = 1; i <= StartShowNum; ++i) {

                        out.println("<a class='PageJumpButton' href='Servlet_GetQuestionNaireList?forward=QuestionNaireManager.jsp&CurrentPageNum=" + i + "'>");
                        out.println(i);
                        out.println("</a>");

                    }

                    out.println("···");

                    for (int i = LeftShowNum; i > 0; --i) {

                        out.println("<a class='PageJumpButton' href='Servlet_GetQuestionNaireList?forward=QuestionNaireManager.jsp&CurrentPageNum=" + (CurrentPageNum - i) + "'>");
                        out.println(CurrentPageNum - i);
                        out.println("</a>");

                    }

                }

                out.println("<a class='PageJumpButton CurrentPage' href='Servlet_GetQuestionNaireList?forward=QuestionNaireManager.jsp&CurrentPageNum=" + CurrentPageNum + "'>");
                out.println(CurrentPageNum);
                out.println("</a>");

                if (RightShowNum + EndShowNum >= MaxPageNum - CurrentPageNum) {

                    for (int i = CurrentPageNum + 1; i <= MaxPageNum; ++i) {

                        out.println("<a class='PageJumpButton' href='Servlet_GetQuestionNaireList?forward=QuestionNaireManager.jsp&CurrentPageNum=" + i + "'>");
                        out.println(i);
                        out.println("</a>");

                    }

                } else {

                    for (int i = 1; i <= RightShowNum; ++i) {

                        out.println("<a class='PageJumpButton' href='Servlet_GetQuestionNaireList?forward=QuestionNaireManager.jsp&CurrentPageNum=" + (CurrentPageNum + i) + "'>");
                        out.println(CurrentPageNum + i);
                        out.println("</a>");

                    }

                    out.println("···");

                    for (int i = EndShowNum - 1; i >= 0; --i) {

                        out.println("<a class='PageJumpButton' href='Servlet_GetQuestionNaireList?forward=QuestionNaireManager.jsp&CurrentPageNum=" + (MaxPageNum - i) + "'>");
                        out.println(MaxPageNum - i);
                        out.println("</a>");

                    }

                }

            %>
        </div>
    </div>
</div>
</body>
</html>