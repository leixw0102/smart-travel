<%@ page import="java.util.Map" %>
<%@ page import="java.util.Set" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="com.google.common.collect.Maps" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bridging.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js" ></script>
<script>
function save(){
	var newRow = $('<li class="bg2">'+
    '<ul>'+
    '<li ><input type="checkbox"/></li>'+
    '<li class="ml20 secondName category-name"></li>'+
    '<li class="del">删除</li>'+
    '<li class="edi">编辑</li>'+
'</ul>'+
'</li>');
	var second_item = $(window.top.frames["mainFrame"].that).closest(".bg1").find(".second-item");
	newRow.appendTo(second_item);
	newRow.find(".category-name").text($("#categoryName").val());
}
   
</script>
</head>
<body class="pad20">
		<div class="body_main">
		<form id="companyInfo">
		<!--filter start-->
		<div class="fieldsContainer">
			<ul class="fields search pdt0 pdb0">
				<li class="pr9">
					<ul>
						<li class="text w60 fb c1">分类名称：</li>
						<li class="value">
							<input id="categoryName" name="categoryName" type="text" class="w300 h27 inputStyle"/>
						</li>
					</ul>
				</li>
				
			</ul>
		</div>
		<%--<input type="hidden" name="type" value="0"/> javascript:$('#companyInfo').submit()--%>

		<div class="bt_icon bt_icon_b3 bd0" style="margin-left:130px;display:inline-block" onclick="save()"><div class="text c1 pdl0">确定</div></div>
        </form>
	</div>
	</body>
</html>
     <!-- -->