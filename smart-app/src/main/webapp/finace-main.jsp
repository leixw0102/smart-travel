<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link href="css/bridging.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="js/plugins/popWin/css/win.css"/>
<script src="js/jquery-1.9.1.min.js" type="text/javascript"></script>
<script src="js/function.js" type="text/javascript"></script>
<script src="js/plugins/popWin/js/jquery.window.min.js" type="text/javascript"></script>	
<script src="js/plugins/jquery-ui-1.10.4.custom.min.js" type="text/javascript"></script>
<script language="javascript">

$(function(){
	contentWH();
	$(window).resize(contentWH);
})
function contentWH(){
	var winH = document.documentElement.clientHeight;
	$(".content").height(winH - $(".head").outerHeight(true));

	//40是iframe的边距。
	//$(".page-pos").width(document.body.clientWidth - $(".menu").outerWidth(true)-40);
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
	<div class="page" style="width:100%">
		<iframe src="/FinaceAction?type=finace_main" frameborder="0" allowTransparency='true' height="100%" id="mainFrame" name="mainFrame" width="100%"></iframe>
	</div>
</div>
</body>
</html>
