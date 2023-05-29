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

ClickA = function (name){

    document.getElementById(name).click();

}

AsideBarFit = function(){
    //拿到子元素的高度
    var frame=document.getElementById("ContentFrame").height;
    //将子元素的高度赋予父元素
    document.getElementById("MyAside").style.height = frame + "px";
}