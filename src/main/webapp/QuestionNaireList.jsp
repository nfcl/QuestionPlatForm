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

                        for (int i = 0; i < list.size(); ++i) {

                            out.println(
                                    "<li class='QuestionNaireListView-li'>" +
                                            "   <h1 class='QuestionNaireListView-li-Title'>" + list.get(i).getTitle() + "</h1>" +
                                            "   <div class='QuestionNaireListView-li-Sessions.User'>" + list.get(i).getCreator() + "</div>" +
                                            "   <div class='QuestionNaireListView-li-StartTime'>" + list.get(i).getStartTime() + "</div>" +
                                            "</li>"
                            );

                        }

                    }

                %>

            </ul>

            <div>

                <form action="Servlet_LookThroughQuestionnaires" method="post">

                    <input type="hidden" name="CurrentPageNum" value="
                        <%

                            if (request.getAttribute("CurrentPageNum") == null) {
                                out.print(1);
                            } else {
                                out.print((int) request.getAttribute("CurrentPageNum"));
                            }

                        %>">

                    <input type="submit" class="HomePageJumpButton" onclick="QuestionNaireListPageHome()" value="|<<">

                    <input type="submit" class="HomePageJumpButton" onclick="QuestionNaireListPageUp()" value="<">

                    <label id="ListPageNum">

                        <%

                            if (request.getAttribute("CurrentPageNum") == null) {
                                out.print(1);
                            } else {
                                out.print((int) request.getAttribute("CurrentPageNum") + 1);
                            }

                        %>

                    </label>

                    <input type="submit" class="HomePageJumpButton" onclick="QuestionNaireListPageDn()" value=">">

                    <input type="submit" class="HomePageJumpButton" onclick="QuestionNaireListPageEnd()" value=">>|">

                </form>


            </div>

        </div>

    </div>

</div>

</body>
</html>