<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<script src="https://libs.baidu.com/jquery/2.1.4/jquery.min.js"></script>
<!DOCTYPE html>
<html>
<head>
    <title>Cierra</title>
    <link rel="stylesheet" href="./css/Main.css">
    <script src="./js/index.js"></script>
</head>
<body>
<div class="MyTitle">
    <h1>Cierra —— 一个基于JSP的问卷发布及填写网站</h1>
</div>
<div class="MyNav">

    <div class="MyNav-Item" onclick="ClickA('SignInA');ResetForm('SignInForm');ResetIFrame('SignInFrame')">登录</div>
    <div class="MyNav-Item" onclick="ClickA('SignUpA');ResetForm('SignUpForm');ResetIFrame('SignUpFrame')">注册</div>
    <a href="#MySignIn" id="SignInA"></a>
    <a href="#MySignUp" id="SignUpA"></a>

</div>

<div class="MyCont">

    <div class="MyAside">

        <div>
            <img id="RealAsideIcon" class="AsideControlButton" alt="你被骗了" src="./resources/nibeipianle.png"/>
            <img id="FakeAsideIcon" class="AsideControlButton" alt="这是一个按钮" src="./resources/thisisabutton.png"/>
        </div>
        <br>
        <br>
        <br>
        <h1 class="MyAside-Item" onclick="ClickA('homePageA');QuestionNaireListPageUp();AsideBarFit();">主页</h1>
        <a id="homePageA" href="#MyHomePage"></a>


    </div>

    <div class="MyFrame">

        <div align="center" id="MySignIn" class="MyContFrame">
            <form class="SignInForm" action="Servlet_SignIn" target="SignInframe">
                <label class="MySignIn-Label">账号
                    <input name="account" type="text" class="MyTextArea"/>
                </label>
                <br>
                <br>
                <label class="MySignIn-Label">密码
                    <input name="password" type="password" class="MyTextArea"/>
                </label>
                <br>
                <br>
                <br>
                <input class="MySignIn-Submit" type="submit" value="登录"/>
                <br>
                <br>
                <iframe id="SignInFrame" class="Myiframe" name="SignInframe"></iframe>
            </form>
        </div>

        <div align="center" id="MySignUp" class="MyContFrame">

            <form class="SignUpForm" action="Servlet_SignUp" target="SignUpframe">
                <label class="MySignIn-Label">名称
                    <input name="name" type="text" class="MyTextArea"/>
                </label>
                <br>
                <br>
                <label class="MySignIn-Label">账号
                    <input name="account" type="text" class="MyTextArea"/>
                </label>
                <br>
                <br>
                <label class="MySignIn-Label">密码
                    <input name="password" type="password" class="MyTextArea"/>
                </label>
                <br>
                <br>
                <br>
                <input class="MySignIn-Submit" type="submit" value="注册"/>
                <br>
                <br>
                <iframe class="Myiframe" id="SignUpFrame" name="SignUpframe"></iframe>
            </form>

        </div>

        <div align="center" id="MyHomePage" class="MyContFrame">

            <ul id="HomePageQnListView">

                <li class="HomePageQnLi">
                    <h1 class="HomePageQnName">问卷标题</h1>
                    <div class="HomePageQnUser">问卷发起人</div>
                    <div class="HomePageQnStartTime">问卷开始时间</div>
                </li>
                <li class="HomePageQnLi">
                    <h1 class="HomePageQnName">问卷标题</h1>
                    <div class="HomePageQnUser">问卷发起人</div>
                    <div class="HomePageQnStartTime">问卷开始时间</div>
                </li>
                <li class="HomePageQnLi">
                    <h1 class="HomePageQnName">问卷标题</h1>
                    <div class="HomePageQnUser">问卷发起人</div>
                    <div class="HomePageQnStartTime">问卷开始时间</div>
                </li>
                <li class="HomePageQnLi">
                    <h1 class="HomePageQnName">问卷标题</h1>
                    <div class="HomePageQnUser">问卷发起人</div>
                    <div class="HomePageQnStartTime">问卷开始时间</div>
                </li>

            </ul>

            <div>

                <span class="HomePageJumpButton" onclick="QuestionNaireListPageHome()">|<<</span>

                <span class="HomePageJumpButton" onclick="QuestionNaireListPageUp()"><</span>

                <span id="HomePageListPageNum">1</span>

                <span class="HomePageJumpButton" onclick="QuestionNaireListPageDn()">></span>

                <span class="HomePageJumpButton" onclick="QuestionNaireListPageEnd()">>>|</span>

            </div>

        </div>

    </div>

    <script>

        AsideBarFit();

    </script>

</div>
</body>
</html>