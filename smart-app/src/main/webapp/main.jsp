<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>无标题文档</title>
<link href="css/bridging.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="js/plugins/popWin/css/win.css"/>
<script src="js/jquery-1.9.1.min.js" language="javascript" type="text/javascript"></script>
<script src="js/function.js" language="javascript" type="text/javascript"></script>
<script src="js/plugins/popWin/js/jquery.window.min.js" type="text/javascript" charset="utf-8"></script>	
<script src="js/plugins/jquery-ui-1.10.4.custom.min.js" type="text/javascript" charset="utf-8"></script>
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
			obj.removeClass("down");
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
		<div class="user-info">

		</div>
	</div>
</div>
<!--content-->
<div class="content">
	<div class="menu">
		<div class="items">
			<ul class="menu-items">
				<li class="no-level flgl"><a href="typeManager.jsp" target="mainFrame"></a></li>
				<li class="no-level companyAcount"><a href="sellerManager.jsp" target="mainFrame"></a></li>
				<li class="no-level yy-userAcount"><a href="javascript:void(0)" target="mainFrame"></a></li>
				<li class="no-level finaAcount"><a href="javascript:void(0)" target="mainFrame"></a></li>
			</ul>
		</div>
	</div>
	<div class="page">
		<iframe src="typeManager.jsp" frameborder="0" allowTransparency='true' height="100%" id="mainFrame" name="mainFrame" width="100%"></iframe>
	</div>
</div>
</body>
</html>
