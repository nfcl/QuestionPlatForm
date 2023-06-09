<%@ page import="Sessions.QuestionNaire" %>
<%@ page import="java.util.ListIterator" %><%--
  Created by IntelliJ IDEA.
  User: 煖風遲來
  Date: 2023/6/7
  Time: 23:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cierra</title>
    <link rel="stylesheet" href="./css/index.css">
    <link rel="stylesheet" href="./css/QuestionNaireInfo.css">
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

        <%

            QuestionNaire questionNaire = (QuestionNaire) request.getAttribute("QuestionNaire");

            request.removeAttribute("QuestionNaire");

        %>

        <div align="center" id="QuestionNaireInfoPage">

            <div id="QuestionNaireInfoPage-Cont">

                <h1 id="QuestionNaireInfoPage-Title">
                    <%=questionNaire.getTitle()%>
                </h1>
                <div style="display: flex;justify-content: space-between">
                    <span class="QuestionNaireInfoPage-UserName" style="opacity: 0">
                        <%=questionNaire.getCreatorName()%>
                    </span>
                    <span class="QuestionNaireInfoPage-Time">
                        <%=questionNaire.getStartTime()%>——<%=questionNaire.getEndTime()%>
                    </span>
                    <span class="QuestionNaireInfoPage-UserName">
                        <%=questionNaire.getCreatorName()%>
                    </span>
                </div>
                <hr class="DivideLine">
                <form action="./Servlet_PostAnswerNaire" method="post">

                    <input name="QuestionNaireId" type="hidden" value="<%=questionNaire.getId()%>">

                    <ul id="QuestionList">

                        <%

                            var questionEnter = questionNaire.getQuestions_Enter();

                            for (QuestionNaire.Enter item : questionEnter) {

                                out.println("<li class='QuestionNaireInfoPage-Question' >");
                                out.println("   <div class='QuestionNaireInfoPage-QuestionTitle'>" + item.getTitle() + "</div>");
                                out.println("   <div class='QuestionNaireInfoPage-QuestionAnswerRegion'>");
                                out.println("       <div class='QuestionNaireInfoPage-QuestionAnswer-Enter'>");
                                out.println("           <input name='Answer-" + item.getId() + "' class='QuestionNaireInfoPage-QuestionAnswer-Enter-textField' type='text' />");
                                out.println("       </div>");
                                out.println("   </div>");
                                out.println("</li>");

                            }

                            var questionSingle = questionNaire.getQuestions_Single();

                            for (QuestionNaire.Single item : questionSingle) {

                                out.println("<li class='QuestionNaireInfoPage-Question' >");
                                out.println("   <div class='QuestionNaireInfoPage-QuestionTitle'>" + item.getTitle() + "</div>");
                                out.println("   <div class='QuestionNaireInfoPage-QuestionAnswerRegion'>");

                                var Options = item.GetOptions();

                                for (QuestionNaire.Option option : Options) {

                                    out.println("       <div class='QuestionNaireInfoPage-QuestionAnswer-Single' align='left'>");
                                    out.println("           <label class='QuestionNaireInfoPage-QuestionAnswer-Single-Label'>");
                                    out.println("               <input name='Answer-" + item.getId() + "' class='QuestionNaireInfoPage-QuestionAnswer-Single-Radio' type='radio' value='" + option.getId() + "' />");
                                    out.println(option.getContent());
                                    out.println("           </label>");
                                    out.println("       </div>");

                                }

                                out.println("   </div>");
                                out.println("</li>");

                            }

                            var questionMultiple = questionNaire.getQuestions_Multiple();

                            for (QuestionNaire.Multiple item : questionMultiple) {

                                out.println("<li class='QuestionNaireInfoPage-Question' >");
                                out.println("   <div class='QuestionNaireInfoPage-QuestionTitle'>" + item.getTitle() + "</div>");
                                out.println("   <div class='QuestionNaireInfoPage-QuestionAnswerRegion'>");

                                var Options = item.GetOptions();

                                for (QuestionNaire.Option option : Options) {

                                    out.println("       <div class='QuestionNaireInfoPage-QuestionAnswer-Multiple' align='left'>");
                                    out.println("           <label class='QuestionNaireInfoPage-QuestionAnswer-Multiple-Label'>");
                                    out.println("               <input name='Answer-" + item.getId() + "' class='QuestionNaireInfoPage-QuestionAnswer-Multiple-CheckBox' type='checkBox' value='" + option.getId() + "' />");
                                    out.println(option.getContent());
                                    out.println("           </label>");
                                    out.println("       </div>");

                                }

                                out.println("   </div>");
                                out.println("</li>");

                            }

                        %>

                    </ul>

                    <input type="submit" value="提交">

                </form>

            </div>

        </div>
    </div>

</div>

</body>
</html>
