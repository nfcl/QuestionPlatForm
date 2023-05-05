ResetForm = function(name){

    document.getElementsByClassName(name)[0].reset();

}

ResetIFrame = function (name){

    document.getElementById(name).contentWindow.document.body.innerHTML="";

}