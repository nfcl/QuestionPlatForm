TrySignIn = function () {

    let Account = document.getElementById('SignInAccount').value;
    let Password = document.getElementById('SignInPassword').value;

    $.get(
        "../Servlet_SignIn?account=" + Account + "&password=" + Password,
        null,
        function (info) {

            if (info.startsWith("TRUE")) {

                let name = info.match(/TRUE\nName=(\S*)\n/)[1];

                let isAdmin = info.match(/\nIsAdmin=(\S*)/)[1];

                window.parent.LogOn(name, isAdmin);

                alert("欢迎登陆 " + name + " ");

            } else {

                alert(info.match(/FALSE\nReason=(\S*)/)[1]);

            }

        }
    );

}