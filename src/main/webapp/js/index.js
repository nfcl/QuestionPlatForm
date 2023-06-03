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

LogOn = function (name){

    $("#MyNav-LogOn").removeClass('MyNav-Hide');
    $("#MyNav-LogOff").addClass('MyNav-Hide');

    $("#LogOn-UserName").html(name);

}

LogOff = function (){

    $("#MyNav-LogOn").addClass('MyNav-Hide');
    $("#MyNav-LogOff").removeClass('MyNav-Hide');

    sessionStorage.removeItem("User_Id");

}