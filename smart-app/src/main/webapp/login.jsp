<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/bridging.css"/>
<link rel="stylesheet" href="js/plugins/pagination/default-style/css/pagination.css" />
<link rel="stylesheet" href="js/plugins/pagination/default-style/css/pagination.extend.css" />	
<script type="text/javascript" src="js/jquery-1.9.1.min.js" ></script>
<script type="text/javascript" src="js/plugins/pagination/default-style/js/jquery.pagination.js" ></script>
<script type="text/javascript" src="js/plugins/pagination/default-style/js/jquery.pagination.extend.js"></script>
<script type="text/javascript" src="js/plugins/My97DatePicker/WdatePicker.js"></script> 
<script type="text/javascript" src="js/function.js" ></script>
<script>
    function login(){
        $.ajax({

            type: "Post",

            url: "<%=request.getContextPath()%>/1.0/user/login",

            data: $('#userLogin').serialize(),

            success: function(result){
                if (result.code==0){
                    location.href="<%=request.getContextPath()%>"+result.info;
                }else{
                    alert(result.message)
                }

            }

        });
    }
</script>
</head>
<body>
	<div class="top">
        <form id="userLogin">
		<div class="login-logo fl"></div>
	
		<div class="bt_icon bt_icon_b2 fr  pr t8" style="margin-top:13px;" onClick="login()"><div class="text clfff pdl0">登录</div></div>
		<div class="pwd-login-wrapper fr"><input name="pwd" type="password" class="pwd-login"/></div>
		<div class="user-login-wrapper fr"><input name="userName" type="text" class="user-login"/></div>
		
            </form>
	</div>
	<div class="login-bg"></div>
	<div class="login-bts">
		<div class="jd-tj"></div>
		<div class="jdd-tj"></div>
		<div class="cy-tj"></div>
		<div class="sh-tj"></div>
	</div>
	<div class="login-footer">
		<div class="login-footer-wrapper">
			<div class="bt-border-s1 fl">关于</div>
			<div class="bt-border-s1 fl">服务</div>
			<div class="bt-border-s1 fl">客服</div>
			<div class="fl">隐私</div>
			<div class="fr">英文</div>
			<div class="bt-border-s1 fr">中文</div>
		</div>
		<div class="cp">© 1998 - 2015 Tencent Inc,All Rights Reserved</div>
	</div>		
</body>
</html>
