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

                //这里应该传回一个字符串形式为 Id:***&Name:*** 即用户ID和名称
                //如果info为空则登录失败

                if(info === "-1"){

                    //登录失败

                }
                else{

                    //登录成功

                    let Id = info.match(/Id:(\S*)&Name:/);

                    let Name = info.match(/&Name:(\S*)/);

                    window.localStorage.setItem("Cierra_Account",Account);
                    window.localStorage.setItem("Cierra_Password",Password);

                }

                console.log(info);

            }

        );

    }

}