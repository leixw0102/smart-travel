<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bridging.css"/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/js/plugins/pagination/default-style/css/pagination.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/js/plugins/pagination/default-style/css/pagination.extend.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/plugins/pagination/default-style/js/jquery.pagination.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/plugins/pagination/default-style/js/jquery.pagination.extend.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/function.js" ></script>
<script>
$(function(){
	var w=$(".pad20")[0].clientWidth;
	$(".body_main").width=w;
	$(".level li ul").find("li.secondName :text").bind("focus",function(){
		this.blur();
	})
	
	$(".level li.toggle_bt").click(function(){
		$(this).parents("li:eq(0)").find("ul.second-item").slideToggle();
	
	})
	
	
	$(".level li ul li.edi").click(function(){
		$(this).parents("ul:eq(0)").find("li.secondName :text").removeClass("input-style1").addClass("input-style2").unbind("focus").removeAttr("readonly");
	})
	
	$(".level li ul li.del").click(function(){
		$(this).parents("li:eq(0)").remove();
	})
	
	$(".level li ul li.ok").click(function(){
		$(this).parents("ul:eq(0)").find("li.secondName :text").removeClass("input-style2").addClass("input-style1").bind("focus",function(){
			this.blur();
		}).attr("readonly","true");
	})
	
	$(".level li ul li.cancel").click(function(){
		var that = $(this);
		$(this).parents("ul:eq(0)").find("li.secondName :text").removeClass("input-style2").addClass("input-style1").bind("focus",function(){
			this.blur();
		}).attr("readonly","true").val($(this).parents("ul:eq(0)").find("li.secondName :text").attr("ov"));
	})
})
</script>
</head>
<body class="pad20">
		<div class="body_main">
			<ul class="level">
				<li class="list-item-c1 h40">
					<ul>
						<li class="title">分类管理</li>
						<li class="del">删除</li>
						<li class="edi">编辑</li>
						<li class="addnew"><div class="bt_icon bt_icon_b1 addCat_bt"><div class="addCat_bt"></div><div class="text">新增分类</div></div></li>
					</ul>
				</li>
				<li class="bg1">
					<ul class="h40">
						<li class="toggle_bt">酒店</li>
						<li class="del">删除</li>
						<li class="edi">编辑</li>
						<li class="addnew"><div class="bt_icon bt_icon_b1 addCat_bt"><div class="addCat_bt"></div><div class="text">新增分类</div></div></li>
					</ul>
                    <ul class="second-item">
                    	<li class="bg2">
                            <ul>
                                <li ><input type="checkbox"/></li>
                                <li class="ml20 secondName"><input  type="text" class="input-style1 w80 c1" ov="精品酒店" value="精品酒店" readonly="true"/></li>
                                <li class="ok ml10">确定</li>
                                <li class="cancel ml10">取消</li>
                                <li class="del">删除</li>
                                <li class="edi">编辑</li>
                            </ul>
                   	 	</li>
                        <li class="bg2">
                            <ul>
                                <li ><input type="checkbox"/></li>
                                <li class="ml20 secondName"><input  type="text" class="input-style1 w80" ov="公寓" value="公寓" readonly="true"/></li>
                                <li class="ok ml10">确定</li>
                                <li class="cancel ml10">取消</li>
                                <li class="del">删除</li>
                                <li class="edi">编辑</li>
                            </ul>
                        </li>
                    
                    </ul>
				</li>
				
				
				
				<li class="bg1">
					<ul class="h40">
						<li class="toggle_bt">餐饮</li>
						<li class="del">删除</li>
						<li class="edi">编辑</li>
						<li class="addnew"><div class="bt_icon bt_icon_b1 addCat_bt"><div class="addCat_bt"></div><div class="text">新增分类</div></div></li>
					</ul>
                    <ul class="second-item">
                        <li class="bg2">
                            <ul>
                                <li ><input type="checkbox"/></li>
                                <li class="ml20 secondName"><input  type="text" class="input-style1 w80 c1" ov="人文景点" value="人文景点" readonly="true"/></li>
                                <li class="ok ml10">确定</li>
                                <li class="cancel ml10">取消</li>
                                <li class="del">删除</li>
                                <li class="edi">编辑</li>
                            </ul>
                        </li>
                        <li class="bg2">
                            <ul>
                                <li ><input type="checkbox"/></li>
                                <li class="ml20 secondName"><input  type="text" class="input-style1 w80" ov="自然景点" value="自然景点" readonly="true"/></li>
                                <li class="ok ml10">确定</li>
                                <li class="cancel ml10">取消</li>
                                <li class="del">删除</li>
                                <li class="edi">编辑</li>
                            </ul>
                        </li>
                    </ul>
				</li>
				
				
				<li class="bg1">
					<ul class="h40">
						<li class="toggle_bt">生活</li>
						<li class="del">删除</li>
						<li class="edi">编辑</li>
						<li class="addnew"><div class="bt_icon bt_icon_b1 addCat_bt"><div class="addCat_bt"></div><div class="text">新增分类</div></div></li>
					</ul>
                    <ul class="second-item">
                    
                    	<li class="bg2">
                            <ul>
                                <li ><input type="checkbox"/></li>
                                <li class="ml20 secondName"><input  type="text" class="input-style1 w80 c1" ov="人文景点" value="人文景点" readonly="true"/></li>
                                <li class="ok ml10">确定</li>
                                <li class="cancel ml10">取消</li>
                                <li class="del">删除</li>
                                <li class="edi">编辑</li>
                            </ul>
                        </li>
                        <li class="bg2">
                            <ul>
                                <li ><input type="checkbox"/></li>
                                <li class="ml20 secondName"><input  type="text" class="input-style1 w80" ov="自然景点" value="自然景点" readonly="true"/></li>
                                <li class="ok ml10">确定</li>
                                <li class="cancel ml10">取消</li>
                                <li class="del">删除</li>
                                <li class="edi">编辑</li>
                            </ul>
                        </li>
                    
                    
                    </ul>
				</li>
				
				
				
				<li class="bg1">
					<ul class="h40">
						<li class="toggle_bt">服务类型设置</li>
						<li class="del">删除</li>
						<li class="edi">编辑</li>
						<li class="addnew"><div class="bt_icon bt_icon_b1 addCat_bt"><div class="addCat_bt"></div><div class="text">新增分类</div></div></li>
					</ul>
                    <ul class="second-item">
                    	<li class="bg2">
                            <ul>
                                <li ><input type="checkbox"/></li>
                                <li class="ml20 secondName"><input  type="text" class="input-style1 w80 c1" ov="VIP" value="VIP" readonly="true"/></li>
                                <li class="ok ml10">确定</li>
                                <li class="cancel ml10">取消</li>
                                <li class="ml20 secondName"><input  type="text" class="input-style1 w80 c1" ov="其它" value="其它" readonly="true"/></li>
                                <li class="ok ml10">确定</li>
                                <li class="cancel ml10">取消</li>
                                <li class="del">删除</li>
                                <li class="edi">编辑</li>
                            </ul>
                        </li>
                        <li class="bg2">
                            <ul>
                                <li ><input type="checkbox"/></li>
                                <li class="ml20 secondName"><input  type="text" class="input-style1 w80" ov="VIP" value="VIP" readonly="true"/></li>
                                <li class="ok ml10">确定</li>
                                <li class="cancel ml10">取消</li>
                                <li class="del">删除</li>
                                <li class="edi">编辑</li>
                            </ul>
                        </li>
                    
                    </ul>
				</li>
				
				
				
				
			</ul>
		</div>
	</body>
</html>