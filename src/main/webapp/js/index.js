JumpTo = function (frameName) {

    document.getElementById("ContentFrame").src = "./html/" + frameName + ".html";

}

ResetForm = function (name) {

    if (name === 'SignInForm') {

        document.getElementById('SignInAccount').value = window.localStorage.getItem("Cierra_Account");
        document.getElementById('SignInPassword').value = window.localStorage.getItem("Cierra_Password");

    } else {

        document.getElementsByClassName(name)[0].reset();

    }

}

ClickA = function (name) {

    document.getElementById(name).click();

}

LogOn = function (name, isAdmin) {

    ReBuildNavBar("LogOn");

    sessionStorage.setItem("UserName", name);
    sessionStorage.setItem("UserIsAdmin", isAdmin);

    $("#LogOn-UserName").html(sessionStorage.getItem("UserName"));

    ReBuildAsideBar();

}

LogOff = function () {

    ReBuildNavBar("LogOff");

    sessionStorage.removeItem("UserIsAdmin");
    sessionStorage.removeItem("UserName");

    $.get("./Servlet_SignOff");

    ReBuildAsideBar();

}

IframeFit = function () {

    let frame = document.getElementById("ContentFrame");

    frame.style.height =
        window.innerHeight
        - document.getElementById('MyNav').clientHeight
        - document.getElementById('MyTitle').clientHeight
        + 'px';

    document.getElementById('MyAside').style.height = frame.style.height;

}

ReBuildNavBar = function (state) {

    let html = "";

    if (state === "LogOff") {

        html +=
            "<div class='MyNav-Item' onclick='JumpTo(\"SignIn\")'>登录</div>" +
            "<div class='MyNav-Item' onclick='JumpTo(\"SignUp\")'>注册</div>";

    }

    if (state === "LogOn") {

        html +=
            "<div class='MyNav-Item' onclick='LogOff()'>退出</div>" +
            "<div id='LogOn-UserName' class='MyNav-Item'>" + sessionStorage.getItem("UserName") + "</div>";

    }

    $("#MyNav").html(html);

}

ReBuildAsideBar = function () {

    let html = "";

    html +=
        "<div id='MyAside-Group-HomePage' class='MyAside-Group'>" +
        "    <div class='MyAside-GroupTitle'>主页</div>" +
        "    <div class='MyAside-Item' onclick='JumpTo(\"QuestionNaireList\")'>查看问卷</div>" +
        "    <div class='MyAside-Item' onclick='JumpTo(\"CreateNewQuestionNaire\")'>创建问卷</div>" +
        "</div>";

    if (sessionStorage.getItem("UserIsAdmin") === "True") {

        html +=
            "<div id='MyAside-Group-Manager' class='MyAside-Group'>" +
            "    <div class='MyAside-GroupTitle'>管理员</div>" +
            "    <div class='MyAside-Item' onclick='JumpTo(\"UserManager\")'>用户</div>" +
            "    <div class='MyAside-Item'>问卷</div>" +
            "    <div class='MyAside-Item'>答卷</div>" +
            "</div>";

    }

    $("#MyAside").html(html);

}