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

}