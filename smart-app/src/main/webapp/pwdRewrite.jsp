<%@ page import="com.smart.model.Apply" %>
<%@ page import="com.smart.common.Page" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Insert title here</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bridging.css"/>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js" ></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/plugins/My97DatePicker/WdatePicker.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/function.js" ></script>
    <script>
	$(function(){
		var w=$(".pad20")[0].clientWidth;
		$(".body_main").width=w;
	})
    </script>
   
</head>
<body class="pad20">
		<div class="body_main">
		<!--filter start-->
		<div class="fieldsContainer">
			<ul class="fields search pdt0 pdb0">
				<li class="pr9">
					<ul>
						<li class="text w60 fb c1">新密码：</li>
						<li class="">
							<input type="text" class="w300 h27 inputStyle"/>
						</li>
					</ul>
				</li>
				<li class="ml15 pr9">
					<ul>
						<li class="text w60 fb c1">确认密码：</li>
						<li class="">
							<input type="text" class="w300 h27 inputStyle"/>
						</li>
					</ul>
				</li>
			</ul>
		</div>
		<div class="bt_icon bt_icon_b3 fr r240 pr bd0" style="right:140px"><div class="text c1 pdl0">确定</div></div>
	</div>
	</body>
</html>
