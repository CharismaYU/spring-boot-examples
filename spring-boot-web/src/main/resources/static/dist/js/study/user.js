$(function () {
    //登录学习平台
    $('#saveButton').click(function () {
        //ajax提交数据
        var params = $("#studyUserForm").serialize();
        $.ajax({
            type: "POST",
            url: "/study/login",
            data: params,
            success: function (r) {
                console.log(r);
                if (r.msg == 'success') {
                    alert('登录成功');
                    $("#saveButton").attr("disabled", true);
                } else {
                    alert('登录失败');
                }
            }
        });
    });
})
