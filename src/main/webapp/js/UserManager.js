ShowUserList = function(){

    $.get(
        "../Servlet_GetUserList",
        function(data){

            $("#UserList-ul").html(data);

        }
    );

}

DropUser = function (UserId){

    $.get(
        "../Servlet_DropUser?UserId="+UserId,
        function (){

            ShowUserList();

        }
    );

}