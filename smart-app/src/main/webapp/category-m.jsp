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
	
	$(".level").delegate(".del","click",function(){
		$(this).parents("li:eq(0)").remove();
	})
	
	$(".addnew").bind("click",function(){
		add_new_category(this);
	})
	$(".level").delegate(".edi","click",function(){
		edit_new_category(this);
	})
})
var new_category_win,
	edit_category_win,
	that,that_edit;
function add_new_category(obj){
	new_category_win = window.top.$.popWin({
		title:"新增分类",
		width:400,
		height:140,
		center:true,
		url:"<%=request.getContextPath()%>/FinaceAction?type=addNewCategory"
	})
	that = obj;
}

function edit_new_category(obj){
	edit_category_win = window.top.$.popWin({
		title:"编辑分类",
		width:400,
		height:140,
		center:true,
		url:"<%=request.getContextPath()%>/FinaceAction?type=editCategoryName"
	})
	that_edit = obj;
}
</script>
</head>
<body class="pad20">
		<div class="body_main">
			<ul class="level">
				<li class="list-item-c1 h40">
					<ul>
						<li class="title">分类管理</li>
						<%--<li class="del">删除</li>--%>
						<%--<li class="edi">编辑</li>--%>
						<%--<li class="addnew"><div class="bt_icon bt_icon_b1 addCat_bt"><div class="addCat_bt"></div><div class="text">新增分类</div></div></li>--%>
					</ul>
				</li>
				<li class="bg1">
					<ul class="h40">
						<li class="toggle_bt category-name">酒店</li>
						<li class="del">删除</li>
						<li class="edi">编辑</li>
						<li class="addnew"><div class="bt_icon bt_icon_b1 addCat_bt"><div class="addCat_bt"></div><div class="text">新增分类</div></div></li>
					</ul>
                    <ul class="second-item">
                    	<li class="bg2">
                            <ul>
                                <li ><input type="checkbox"/></li>
                                <li class="ml20 secondName category-name">精品酒店</li>
                                <li class="del">删除</li>
                                <li class="edi">编辑</li>
                            </ul>
                   	 	</li>
                        <li class="bg2">
                            <ul>
                                <li ><input type="checkbox"/></li>
                                <li class="ml20 secondName category-name">公寓</li>
                                <li class="del">删除</li>
                                <li class="edi">编辑</li>
                            </ul>
                        </li>
                    
                    </ul>
				</li>
				
				
				
				<li class="bg1">
					<ul class="h40">
						<li class="toggle_bt category-name">餐饮</li>
						<li class="del">删除</li>
						<li class="edi">编辑</li>
						<li class="addnew"><div class="bt_icon bt_icon_b1 addCat_bt"><div class="addCat_bt"></div><div class="text">新增分类</div></div></li>
					</ul>
                    <ul class="second-item">
                        <li class="bg2">
                            <ul>
                                <li ><input type="checkbox"/></li>
                                <li class="ml20 secondName category-name">人文景点</li>
                                <li class="del">删除</li>
                                <li class="edi">编辑</li>
                            </ul>
                        </li>
                        <li class="bg2">
                            <ul>
                                <li ><input type="checkbox"/></li>
                                <li class="ml20 secondName category-name">自然景点</li>
                                <li class="del">删除</li>
                                <li class="edi">编辑</li>
                            </ul>
                        </li>
                    </ul>
				</li>
				
				
				<li class="bg1">
					<ul class="h40">
						<li class="toggle_bt category-name">生活</li>
						<li class="del">删除</li>
						<li class="edi">编辑</li>
						<li class="addnew"><div class="bt_icon bt_icon_b1 addCat_bt"><div class="addCat_bt"></div><div class="text">新增分类</div></div></li>
					</ul>
                    <ul class="second-item">
                    
                    	<li class="bg2">
                            <ul>
                                <li ><input type="checkbox"/></li>
                                <li class="ml20 secondName category-name">人文景点</li>
                                <li class="del">删除</li>
                                <li class="edi">编辑</li>
                            </ul>
                        </li>
                        <li class="bg2">
                            <ul>
                                <li ><input type="checkbox"/></li>
                                <li class="ml20 secondName category-name">自然景点</li>
                                <li class="del">删除</li>
                                <li class="edi">编辑</li>
                            </ul>
                        </li>
                    
                    
                    </ul>
				</li>
				
				
				
				<li class="bg1">
					<ul class="h40">
						<li class="toggle_bt category-name">服务类型设置</li>
						<li class="del">删除</li>
						<li class="edi">编辑</li>
						<li class="addnew"><div class="bt_icon bt_icon_b1 addCat_bt"><div class="addCat_bt"></div><div class="text">新增分类</div></div></li>
					</ul>
                    <ul class="second-item">
                    	<li class="bg2">
                            <ul>
                                <li ><input type="checkbox"/></li>
                                <li class="ml20 secondName category-name">VIP</li>
                                <li class="ml20 secondName category-name">其它</li>
                                <li class="del">删除</li>
                                <li class="edi">编辑</li>
                            </ul>
                        </li>
                        <li class="bg2">
                            <ul>
                                <li ><input type="checkbox"/></li>
                                <li class="ml20 secondName category-name">VIP</li>
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