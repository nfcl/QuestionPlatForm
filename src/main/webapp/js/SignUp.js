TrySignUp = function () {

    let Account = document.getElementById("SignUpAccount").value;

    let Password = document.getElementById("SignUpPassword").value;

    let Name = document.getElementById("SignUpName").value;

    $.get(
        "../Servlet_SignUp?name=" + Name + "&account=" + Account + "&password=" + Password,
        null,
        function (info) {

            if (info.startsWith("TRUE")) {

                alert("注册成功");

            } else {

                let reason = info.match(/FALSE\nReason=(\S*)/)[1];

                alert(reason);

            }

        }
    );

}