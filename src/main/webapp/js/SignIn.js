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

            "../Servlet_SignIn?account="+Account+"&password="+Password,
            null,
            function(info){

                //这里应该传回一个字符串为用户名称
                //如果info为空则登录失败

                if(info==null || info === ""){

                    //登录失败

                }
                else{

                    //登录成功

                    window.parent.localStorage.setItem("Cierra_Account",Account);
                    window.parent.localStorage.setItem("Cierra_Password",Password);

                    window.parent.LogOn(info);

                }

            }

        );

    }

}