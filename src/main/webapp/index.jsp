<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Cierra</title>
    <link rel="stylesheet" href="./css/Main.css">
</head>
<body>
<div class="MyTitle">
    <h1>Cierra —— 一个基于JSP的问卷发布及填写网站</h1>
</div>
<div class="MyNav">

    <a href="#MySignIn" class="MyNav-Item">登录</a>
    <a href="#MySignUp" class="MyNav-Item">注册</a>

</div>

<div class="MyCont">

    <div class="MyAside">

        <img

                class="AsideControlButton"
                alt="这是一个按钮(×这是一个图标"
                src="./resources/thisisabutton.png"

        />
        <h1 class="MyAside-Item">ceshi</h1>

    </div>

    <div class="MyFrame">
        <div align="center" id="MySignIn" class="MyContFrame">
            <form action="Servlet_SignIn">
                <label class="MySignIn-Label">
                    账号
                    <input name="account" type="text" class="MyTextArea"/>
                </label>
                <br>
                <br>
                <label class="MySignIn-Label">
                    密码
                    <input name="password" type="password" class="MyTextArea"/>
                </label>
                <br>
                <br>
                <br>
                <input class="MySignIn-Submit" type="submit" value="登录"/>
            </form>
        </div>
        <div id="MySignUp" class="MyContFrame">注册</div>
    </div>

</div>
</body>
</html>