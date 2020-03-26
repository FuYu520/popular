/**
 * DOM 加载完后执行
 */
$(function () {
    //初始规则
    validateRule();
});
/**
 * 通过验证后运行的函数
 */
$.validator.setDefaults({
    //通过验证后运行的函数
    submitHandler: function() {
        submit();
    }
});

function login() {
    //a标签的提交，触发校验
    $("#formLogin").submit();
}

function submit() {
    //$("#formLogin").submit();
    //jQuery中的$("input[name='xxx']")的含义
    var username = $("#username").val();
    var password = $("input[name='password']").val();
    $.ajax({
        type: "post",
        url:  ctx + "login",
        data: {
            "username": username,
            "password": password
        },
        success: function (r) {
            if (r.code == 0){
                //登录成功跳转index
                location.href = ctx +'index';
            }
        }
    });
}

/**
 * 初始校验规则
 */
function validateRule() {
    var icon = "<i class='fa fa-times-circle'></i> ";

    $("#formLogin").validate({
        rules: {
            username: {
                required: true
            },
            password: {
                required: true
            }
        },
        messages: {
            username: {
                required: icon + "请输入您的用户名",
            },
            password: {
                required: icon + "请输入您的密码",
            }
        }
    });
}