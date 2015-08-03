<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
    <%
        Object user = session.getAttribute("userSessionId");
        String userName="游客";
        if(null == user ){
            response.sendRedirect("login.jsp");

        } else{
        userName=user.toString().split("-")[2];
        }
    %>
<link href="<%=request.getContextPath()%>/css/bridging.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/plugins/popWin/css/win.css"/>
<script src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/js/function.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/js/plugins/popWin/js/jquery.window.min.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/js/plugins/jquery-ui-1.10.4.custom.min.js" type="text/javascript"></script>
<script language="javascript">

$(function(){
	//菜单事件
	showMenu();
	contentWH();
	$(window).resize(contentWH);
})

function showMenu(){
	
	//一级菜单点击事件
	$(".menu-items > li").bind("click",function(){
		var obj = $(this);
		if(obj.hasClass("down")){
			//obj.removeClass("down");
		}else{
			obj.addClass("down");
			$(this).siblings("li").removeClass("down");
		}
	})
}
function contentWH(){
	var winH = document.documentElement.clientHeight;
	$(".content").height(winH - $(".head").outerHeight(true));

	//40是iframe的边距。
	//$(".page-pos").width(document.body.clientWidth - $(".menu").outerWidth(true)-40);
	$(".page").width(document.body.clientWidth - $(".menu").outerWidth(true));
}
function logout(){
    $.ajax({

        type: "Post",

        url: "<%=request.getContextPath()%>/1.0/user/logout",


        success: function(result){
            if (result.code==0){
                location.href="<%=request.getContextPath()%>/"+result.info;
            }else{
                alert(result.message)
            }

        }

    });
}
</script>
<style>
	body{overflow:hidden}
</style>
</head>
<body>
<!--head-->
<div class="head">
	<div class="up">
		<div class="logo"></div>
		<div class="user-info" style="display:inline-block;color:#fff;float:right;margin-top:25px;margin-right:60px">
			您好，<%=userName%>
		</div>
		<div class="bt_icon bt_icon_b2 fr  pr t8" style="margin-top:13px;margin-right:10px" onClick="logout()"><div class="text clfff pdl0">退出</div></div>
	</div>
</div>
<!--content-->
<div class="content">
	<div class="menu">
		<div class="items">
			<ul class="menu-items">
				<li class="no-level finaAcount"><a href="<%=request.getContextPath()%>/finace.jsp" target="mainFrame"></a></li>
                <li class="no-level jsOrder"><a href="<%=request.getContextPath()%>/js-order.jsp" target="mainFrame"></a></li>
			</ul>
		</div>
	</div>
	<div class="page">
		<iframe src="<%=request.getContextPath()%>/finace.jsp" frameborder="0" allowTransparency='true' height="100%" id="mainFrame" name="mainFrame" width="100%"></iframe>
	</div>
</div>
</body>
</html>
