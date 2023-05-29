QuestionNaireListPageUp = function(){

    //获得当前页数
    let CurrentPage = parseInt(document.getElementById("HomePageListPageNum").textContent);

    let PerPageListItemNum = 10;

    $.get(
        "../Servlet_GetQuestionNaireNum",
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
        "../Servlet_GetQuestionNaireNum",
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
        "../Servlet_GetQuestionNaireNum",
        null,
        function (num){

            CurrentPage = Math.ceil(parseInt(num) / PerPageListItemNum);

            ShowQuestionnaireList(PerPageListItemNum,CurrentPage);

        }
    )

}

ShowQuestionnaireList = function(PerPageListItemNum,CurrentPage){

    //计算开头的列表项下标
    let firstItemIndex = (CurrentPage - 1) * PerPageListItemNum;

    $.get("../Servlet_LookThroughQuestionnaires?FirstIndex="+firstItemIndex+"&PerPageListItemNum="+PerPageListItemNum,
        function(data){
            $("#HomePageQnListView").html(data);
            document.getElementById("HomePageListPageNum").textContent = CurrentPage;
        }
    );

}

ShowQuestionNaireInfo = function(QN_Id){

    $.get(
        "../Servlet_GetQuestionNaireInfo?QN_Id="+QN_Id,
        function (data){

            AsideBarFit();

            $("#QuestionNaireInfoPage-Cont").html(data);

        }
    );

}

JumpTo = function (frameName){

    window.parent.document.getElementById("ContentFrame").src = "./html/"+frameName+".html";

}

AsideBarFit = function(){
    //拿到子元素的高度
    var frame=window.parent.document.getElementById("ContentFrame").height;
    //将子元素的高度赋予父元素
    window.parent.document.getElementById("MyAside").style.height = frame + "px";
}