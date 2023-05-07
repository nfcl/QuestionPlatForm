ResetForm = function(name){

    document.getElementsByClassName(name)[0].reset();

}

ResetIFrame = function (name){

    document.getElementById(name).contentWindow.document.body.innerHTML="";

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
    var frame=document.getElementsByClassName("MyFrame")[0].offsetHeight;
    //将子元素的高度赋予父元素
    document.getElementsByClassName("MyAside")[0].style.height = frame + "px";
}