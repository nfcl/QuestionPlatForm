JumpTo = function (frameName){

    document.getElementById("ContentFrame").src = "./html/"+frameName+".html";

}

ResetForm = function(name){

    if(name === 'SignInForm'){

        document.getElementById('SignInAccount').value = window.localStorage.getItem("Cierra_Account");
        document.getElementById('SignInPassword').value = window.localStorage.getItem("Cierra_Password");

    }
    else{

        document.getElementsByClassName(name)[0].reset();

    }

}

TrySignIn = function (){

    let Account = document.getElementById('SignInAccount').value;
    let Password = document.getElementById('SignInPassword').value;

    if(Account == null || Account === ""){
        alert("账号为空");
    }
    else if(Password == null || Password === ""){
        alert("密码为空");
    }
    else{

        $.get(

            "./Servlet_SignIn?account="+Account+"&password="+Password,
            null,
            function(info){

                //这里应该传回一个字符串形式为 Id:***&Name:*** 即用户ID和名称
                //如果info为空则登录失败

                if(info === "-1"){

                    //登录失败

                }
                else{

                    //登录成功

                    let Id = info.match(/Id:(\S*)&Name:/);

                    let Name = info.match(/&Name:(\S*)/);

                    console.log(info);

                    window.localStorage.setItem("Cierra_Account",Account);
                    window.localStorage.setItem("Cierra_Password",Password);

                }

            }

        );

    }

}

TrySignUp = function (){

    let Account = document.getElementById("SignUpAccount").value;

    let Password = document.getElementById("SignUpPassword").value;

    let Name = document.getElementById("SignUpName").value;

    if(Account == null || Account === ""){
        alert("账号为空");
    }
    else if(Password == null || Password === ""){
        alert("密码为空");
    }
    else if(Name == null || Name === ""){
        alert("名称为空");
    }
    else{

        $.get(

            "./Servlet_SignUp?name="+Name+"&account="+Account+"&password="+Password,
            null,
            function (info){

                if(info.startsWith("TRUE")){

                    let id = info.match(/TRUE\nId=(\S*)/);

                    console.log(id);

                }
                else{

                    let reason = info.match(/FALSE\nReason=(\S*)/);

                    console.log(reason);

                }

            }

        );

    }

}

ClickA = function (name){

    document.getElementById(name).click();

}

ShowQuestionnaireList = function(PerPageListItemNum,CurrentPage){

    //计算开头的列表项下标
    let firstItemIndex = (CurrentPage - 1) * PerPageListItemNum;

    $.get("./Servlet_LookThroughQuestionnaires?FirstIndex="+firstItemIndex+"&PerPageListItemNum="+PerPageListItemNum,
        null,
        function(data){
            $("#HomePageQnListView").html(data);
            AsideBarFit();
            document.getElementById("HomePageListPageNum").textContent = CurrentPage;
        }
    );

}

QuestionNaireListPageUp = function(){

    //获得当前页数
    let CurrentPage = parseInt(document.getElementById("HomePageListPageNum").textContent);

    let PerPageListItemNum = 10;

    $.get(
        "./Servlet_GetQuestionNaireNum",
        null,
        function (num){

            let MaxPageNum =Math.ceil(parseInt(num) / PerPageListItemNum);

            if(CurrentPage > 1){

                CurrentPage -= 1;

            }

            ShowQuestionnaireList(PerPageListItemNum,CurrentPage);

        }
    )

}

QuestionNaireListPageDn = function(){

    //获得当前页数
    let CurrentPage = parseInt(document.getElementById("HomePageListPageNum").textContent);

    let PerPageListItemNum = 10;

    $.get(
        "./Servlet_GetQuestionNaireNum",
        null,
        function (num){

            let MaxPageNum =Math.ceil(parseInt(num) / PerPageListItemNum);

            if(CurrentPage < MaxPageNum){

                CurrentPage += 1;

            }

            ShowQuestionnaireList(PerPageListItemNum,CurrentPage);

        }
    )

}

QuestionNaireListPageHome = function(){

    ShowQuestionnaireList(10,1);

}

QuestionNaireListPageEnd = function(){

    //获得当前页数
    let CurrentPage = parseInt(document.getElementById("HomePageListPageNum").textContent);

    let PerPageListItemNum = 10;

    $.get(
        "./Servlet_GetQuestionNaireNum",
        null,
        function (num){

            CurrentPage = Math.ceil(parseInt(num) / PerPageListItemNum);

            ShowQuestionnaireList(PerPageListItemNum,CurrentPage);

        }
    )

}

AsideBarFit = function(){
    //拿到子元素的高度
    var frame=document.getElementById("ContentFrame").height;
    //将子元素的高度赋予父元素
    document.getElementById("MyAside").style.height = frame + "px";
}

ShowQuestionNaireInfo = function(QN_Id){

    $.get(
        "./Servlet_GetQuestionNaireInfo?QN_Id="+QN_Id,
        null,
        function (data){

            $("#QuestionNaireInfoPage-Cont").html(data);

        }
    );

}