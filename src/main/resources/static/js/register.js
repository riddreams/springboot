$(function(){
    $("#userName").blur(function(){
        var userName = $(this).val();
        if(userName===""){
            $("#info").html("用户名不能为空");
        }else{
            // $.ajax方法实现
            $.ajax({
                url:"/checkUser",
                type:"post",
                data:"userName="+userName,
                dataType:"text",
                success:function(message){
                    $("#info").html(message);
                }
            });
        }
    });
});