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

            "../Servlet_SignUp?name="+Name+"&account="+Account+"&password="+Password,
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