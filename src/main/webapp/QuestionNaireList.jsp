<%--
  Created by IntelliJ IDEA.
  User: 煖風遲來
  Date: 2023/6/7
  Time: 19:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="Sessions.QuestionNaireInfo" %>
<%@ page import="java.util.ArrayList" %>
<html>
<head>
    <title>Cierra</title>
    <link rel="stylesheet" href="./css/index.css">
    <link rel="stylesheet" href="./css/QuestionNaireList.css">
    <link rel="stylesheet" href="./css/PageJumpButton.css">
    <script src="./js/QuestionNaireList.js"></script>
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
        <div align="center" id="MyHomePage">
            <ul id="QuestionNaireListView">
                <%
                    if (request.getAttribute("QuestionNaireInfos") != null) {
                        ArrayList<QuestionNaireInfo> list = (ArrayList<QuestionNaireInfo>) request.getAttribute("QuestionNaireInfos");
                        QuestionNaireInfo item;
                        for (QuestionNaireInfo questionNaireInfo : list) {
                            out.println(
                                    "<li class='QuestionNaireListView-li' onclick='this.querySelector(\"a\").click();'>" +
                                            "   <a hidden='hidden' href='Servlet_GetQuestionNaireInfo?QN_Id=" + questionNaireInfo.getId() + "'></a>" +
                                            "   <h1 class='QuestionNaireListView-li-Title'>" + questionNaireInfo.getTitle() + "</h1>" +
                                            "   <div class='QuestionNaireListView-li-Sessions.User'>" + questionNaireInfo.getCreator() + "</div>" +
                                            "   <div class='QuestionNaireListView-li-StartTime'>" + questionNaireInfo.getStartTime() + "</div>" +
                                            "</li>"
                            );
                        }
                    }
                %>
            </ul>
            <div>
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

                            out.println("<a class='PageJumpButton' href='Servlet_GetQuestionNaireList?forward=QuestionNaireList.jsp&CurrentPageNum=" + i + "'>");
                            out.println(i);
                            out.println("</a>");

                        }

                    } else {

                        for (int i = 1; i <= StartShowNum; ++i) {

                            out.println("<a class='PageJumpButton' href='Servlet_GetQuestionNaireList?forward=QuestionNaireList.jsp&CurrentPageNum=" + i + "'>");
                            out.println(i);
                            out.println("</a>");

                        }

                        out.println("···");

                        for (int i = LeftShowNum; i > 0; --i) {

                            out.println("<a class='PageJumpButton' href='Servlet_GetQuestionNaireList?forward=QuestionNaireList.jsp&CurrentPageNum=" + (CurrentPageNum - i) + "'>");
                            out.println(CurrentPageNum - i);
                            out.println("</a>");

                        }

                    }

                    out.println("<a class='PageJumpButton CurrentPage' href='Servlet_GetQuestionNaireList?forward=QuestionNaireList.jsp&CurrentPageNum=" + CurrentPageNum + "'>");
                    out.println(CurrentPageNum);
                    out.println("</a>");

                    if (RightShowNum + EndShowNum >= MaxPageNum - CurrentPageNum) {

                        for (int i = CurrentPageNum + 1; i <= MaxPageNum; ++i) {

                            out.println("<a class='PageJumpButton' href='Servlet_GetQuestionNaireList?forward=QuestionNaireList.jsp&CurrentPageNum=" + i + "'>");
                            out.println(i);
                            out.println("</a>");

                        }

                    } else {

                        for (int i = 1; i <= RightShowNum; ++i) {

                            out.println("<a class='PageJumpButton' href='Servlet_GetQuestionNaireList?forward=QuestionNaireList.jsp&CurrentPageNum=" + (CurrentPageNum + i) + "'>");
                            out.println(CurrentPageNum + i);
                            out.println("</a>");

                        }

                        out.println("···");

                        for (int i = EndShowNum - 1; i >= 0; --i) {

                            out.println("<a class='PageJumpButton' href='Servlet_GetQuestionNaireList?forward=QuestionNaireList.jsp&CurrentPageNum=" + (MaxPageNum - i) + "'>");
                            out.println(MaxPageNum - i);
                            out.println("</a>");

                        }

                    }

                %>
                <%--                <form action="Servlet_GetQuestionNaireList?forward=QuestionNaireList.jsp" method="post">--%>
                <%--                    <input type="hidden" name="CurrentPageNum" value="--%>
                <%--                        <%--%>
                <%--                            if (request.getAttribute("CurrentPageNum") == null) {--%>
                <%--                                out.print(1);--%>
                <%--                            } else {--%>
                <%--                                out.print((int) request.getAttribute("CurrentPageNum"));--%>
                <%--                            }--%>
                <%--                        %>">--%>
                <%--                    <input type="submit" class="HomePageJumpButton" onclick="QuestionNaireListPageHome()" value="|<<">--%>
                <%--                    <input type="submit" class="HomePageJumpButton" onclick="QuestionNaireListPageUp()" value="<">--%>
                <%--                    <label id="ListPageNum">--%>
                <%--                        <%--%>
                <%--                            if (request.getAttribute("CurrentPageNum") == null) {--%>
                <%--                                out.print(1);--%>
                <%--                            } else {--%>
                <%--                                out.print((int) request.getAttribute("CurrentPageNum") + 1);--%>
                <%--                            }--%>
                <%--                        %>--%>
                <%--                    </label>--%>
                <%--                    <input type="submit" class="HomePageJumpButton" onclick="QuestionNaireListPageDn()" value=">">--%>
                <%--                    <input type="submit" class="HomePageJumpButton" onclick="QuestionNaireListPageEnd()" value=">>|">--%>
                <%--                </form>--%>
            </div>
        </div>
    </div>
</div>
</body>
</html>