Init = function () {

    let url = location.search; //获取url中"?"符后的字串
    if (url.length > 0) { //判断是否携带参数
        let params = {};
        if (url.indexOf('?') != -1) {
            this.isShwoInput = true;
            let str = url.substr(url.indexOf('?') + 1);
            let strs = str.split('&');
            for (let i = 0; i < strs.length; i++) {
                params[strs[i].split('=')[0]] = decodeURI(strs[i].split('=')[1]);
            }
        }

        console.log(params);

        $.get(
            "../Servlet_GetQuestionNaireInfo?QN_Id="+params["QN_Id"],
            null,
            function (data)
            {
                $("#QuestionNaireInfoPage-Cont").html(data);

                AsideBarFit();
            }
        );

    }

}

AsideBarFit = function(){
    window.parent.document.getElementById("ContentFrame").onload(undefined);
    //拿到子元素的高度
    var frame=window.parent.document.getElementById("ContentFrame").height;
    //将子元素的高度赋予父元素
    window.parent.document.getElementById("MyAside").style.height = frame + "px";
}