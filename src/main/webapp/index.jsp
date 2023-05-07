<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<script src="https://libs.baidu.com/jquery/2.1.4/jquery.min.js"></script>
<!DOCTYPE html>
<html>
<head>
    <title>Cierra</title>
    <link rel="stylesheet" href="css/index.css">
    <script src="./js/index.js"></script>
</head>
<body>
<div class="MyTitle">
    <h1>Cierra —— 一个基于JSP的问卷发布及填写网站</h1>
</div>
<div class="MyNav">

    <div class="MyNav-Item" onclick="JumpTo('SignIn')">登录</div>
    <div class="MyNav-Item" onclick="JumpTo('SignUp')">注册</div>

</div>

<div class="MyCont">

    <div id="MyAside" class="MyAside">

        <div class="ControlButtonDiv">
            <img id="RealAsideIcon" class="AsideControlButton" alt="你被骗了" src="./resources/nibeipianle.png"/>
            <img id="FakeAsideIcon" class="AsideControlButton" alt="这是一个按钮" src="./resources/thisisabutton.png"/>
        </div>
        <br>
        <br>
        <br>
        <h1 class="MyAside-Item" onclick="JumpTo('HomePage')">主页</h1>

    </div>

    <iframe
            name="ifd" id="ContentFrame" frameborder="0"
            onload="this.height=ifd.document.body.scrollHeight;AsideBarFit();"
    ></iframe>

    <script>

        JumpTo("SignIn");

    </script>

</div>
</body>
</html>