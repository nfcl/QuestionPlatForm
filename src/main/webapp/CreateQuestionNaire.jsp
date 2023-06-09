<%--
  Created by IntelliJ IDEA.
  User: 煖風遲來
  Date: 2023/6/9
  Time: 12:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cierra</title>
    <link rel="stylesheet" href="./css/index.css">
    <link rel="stylesheet" href="./css/CreateNewQuestionNaire.css">
    <script src="./js/CreateNewQuestionNaire.js"></script>
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
        <div align="center">
            <form action="./Servlet_CreateQuestionNaire" method="post">
                <label>
                    <input name="QuestionNaire-Title" class="CreateNaire-Title-Input" type="text" placeholder="问卷标题" required>
                </label>
                <hr class="DivideLine">
                <input name="QuestionNum" id="QuestionNum" type="hidden" value="0">
                <ol id="Question_List">
                    <!--
                    <li>
                        <div class="QuestionNaire-Question">
                            <input class="QuestionNaire-Question-Kind" type="hidden" value="Enter">
                            <label>
                                <input name="QuestionNaire-Question-Title" class="QuestionNaire-Question-Title-Input" type="text" placeholder="问题内容">
                            </label>
                        </div>
                    </li>
                    <li>
                        <div class="QuestionNaire-Question">
                            <input class="QuestionNaire-Question-Kind" type="hidden" value="Single">
                            <label>
                                <input name="QuestionNaire-Question-Title" class="QuestionNaire-Question-Title-Input" type="text" placeholder="问题内容">
                            </label>
                            <ul class="QuestionNaire-Question-Radio-Options">
                            </ul>
                            <input class="QuestionNaire-Question-Radio-OptionInsert-Button" type="button" onclick="console.log(1);InsertOption_Radio(1);" value="添加新单选项">
                        </div>
                    </li>
                    <li>
                        <div class="QuestionNaire-Question">
                            <input class="QuestionNaire-Question-Kind" type="hidden" value="Multiple">
                            <label>
                                <input name="QuestionNaire-Question-Title" class="QuestionNaire-Question-Title-Input" type="text" placeholder="问题内容">
                            </label>
                            <ul class="QuestionNaire-Question-Check-Options">
                            </ul>
                            <input class="QuestionNaire-Question-Multiple-OptionInsert-Button" type="button" onclick="console.log(2);InsertOption_Multiple(2);" value="添加新多选项">
                        </div>
                    </li>
                    -->
                </ol>
                <input class="QuestionNaire-Add-Button" type="button" onclick="InsertQuestion_Enter()" value="插入一个输入问题">
                <input class="QuestionNaire-Add-Button" type="button" onclick="InsertQuestion_Single()" value="插入一个单选问题">
                <input class="QuestionNaire-Add-Button" type="button" onclick="InsertQuestion_Multiple()" value="插入一个多选问题">
                <input class="QuestionNaire-Add-Submit" type="submit" value="创建新问卷">
            </form>
        </div>
    </div>
</div>
</body>
</html>
