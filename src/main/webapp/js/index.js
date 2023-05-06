ResetForm = function(name){

    document.getElementsByClassName(name)[0].reset();

}

ResetIFrame = function (name){

    document.getElementById(name).contentWindow.document.body.innerHTML="";

}

ClickA = function (name){

    document.getElementById(name).click();

}

ShowQuestionnaireList = function(){

    //每页列表最多可填充的列表项个数
    let PerPageListItemNum = 10;
    //获得当前页数
    let CurrentPage = document.getElementById("HomePageListPageNum").textContent;
    //计算开头的列表项下标
    let firstItemIndex = (CurrentPage - 1) * PerPageListItemNum + 1;
    //计算结尾的列表项下标
    let lastItemIndex  = (CurrentPage - 0) * PerPageListItemNum;

    console.log(CurrentPage);
    console.log(firstItemIndex);
    console.log(lastItemIndex);

    $.get("./Servlet_LookThroughQuestionnaires?FirstIndex="+firstItemIndex+"&LastIndex="+lastItemIndex,
        null,
        function(data){
            console.log(data);
            $("#HomePageQnListView").html(data);
            AsideBarFit();
        }
    );
}

AsideBarFit = function(){
    //拿到子元素的高度
    var frame=document.getElementsByClassName("MyFrame")[0].offsetHeight;
    //将子元素的高度赋予父元素
    document.getElementsByClassName("MyAside")[0].style.height = frame + "px";
}