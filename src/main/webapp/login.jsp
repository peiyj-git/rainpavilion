<%@page isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; UTF-8" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>登录页面</title>
		<link rel="stylesheet" href="layui/css/layui.css">
		 <link rel="stylesheet" href="css/main.css"  media="all">
		 <link rel="stylesheet" href="css/plugins/font-awesome-4.7.0/css/font-awesome.min.css"  media="all">
	</head>
	
	<body class="layui-layout-login">
	<div class="login-bg">
	    <div class="cover"></div>
	</div>
	<div class="login-content" >
	    <h1 class="login-box-title"><i class="fa fa-fw fa-user"></i>登录</h1>
	    <form class="layui-form" id="oneform" method="post">
			
	        <div class="layui-form-item">
	            <label class="layui-icon layui-icon-username" for="LAY-user-login-username"></label>
	            <input class="layui-input" type="text" name="username" id="LAY-user-login-username" placeholder="用户名">
	        </div>
			
	        <div class="layui-form-item">
	            <label class="layui-icon layui-icon-password" for="LAY-user-login-password"></label>
	            <input class="layui-input" type="password" name="password" id="LAY-user-login-password" placeholder="密码">
	        </div>
			
	        <div class="layui-form-item captcha-item">
	            <label class="layui-icon layui-icon-vercode"></label>
	            <input class="layui-input" type="text" name="code" id="LAY-user-login-vercode" autocomplete="off" placeholder="验证码">
				<a onclick="changImg()" href="javascript:void(0)">
				<img id="imgimg" src="${pageContext.request.contextPath}/code/validateCode.png"
					 class="layadmin-user-login-codeimg"/>
				</a>
	        </div>

	        <button type="submit" class="layui-btn layui-btn-fluid ajax-login" lay-submit lay-filter="login-submit"><i class="fa fa-sign-in fa-lg fa-fw"></i> 登录</button>
	    </form>
	</div>
	</body>

</html>

<script type="text/javascript" src="${pageContext.request.contextPath}/layui/layui.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.12.4.js"></script>
<script>

    layui.use(['form','laydate'], function () {
        var form = layui.form;
        var layer=layui.layer;

        //登录提交
        form.on('submit(login-submit)', function (data) {
            var datas=$("#oneform").serialize();
            // alert(datas);
            var adminPhone=$("#LAY-user-login-username").val();
            var adminPassword=$("#LAY-user-login-password").val();
            var code=$("#LAY-user-login-vercode").val();
            // alert(username)
            // alert(password)
            // alert(code)

            $.ajax({
                url:"${pageContext.request.contextPath }/admin/adminLogin"
                ,data:"adminPhone="+adminPhone+"&adminPassword="+adminPassword+"&code="+code
                ,dataType:"json"
                ,type:"post"
                ,success:function(data){
                    if(data.flag==1){
                        // layer.msg("登录成功");
                        window.location="${pageContext.request.contextPath}/main.jsp";
                    }else if(data.flag==3){
                        layer.msg("验证码错误");
                    }else if(data.flag==2){
                        layer.msg("账号或密码错误");
                    }
                }
            });

            return false;
        });
    });



    // 验证码开始
    function changImg(){
        $("#imgimg").prop("src","${pageContext.request.contextPath}/code/validateCode.png?"+Math.random());
    };
    // 验证码结束


</script>


