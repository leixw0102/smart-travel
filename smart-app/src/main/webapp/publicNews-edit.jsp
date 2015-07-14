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
						<li class="text w60 fb c1">新闻标题：</li>
						<li class="value">
							<input type="text" class="w300 h27 inputStyle"/>
						</li>
						<span class="cldff0000">*</span><span class="cldccc">50字以内</span>
					</ul>
				</li>
				<li class="pr9">
					<ul>
						<li class="text w60 fb c1">新闻图片：</li>
						<li class="value">
							<img src=""/>
						</li>
						<li class="value">
							<div class="bt_icon bt_icon_b4 fr r10 pr bd0"><div class="text c1 pdl0">上传</div></div>
						</li>
						<span class="cldff0000">*</span><span class="cldccc">支持JPG格式，且小于1mb</span>
					</ul>
				</li>
				
				<li class="pr9">
					<ul>
						<li class="text w60 fb c1">新闻摘要：</li>
						<li class="value">
							<div class="inputBt">
								<textarea id="to" class="w300 h100 inputStyle"></textarea>
							</div>
						</li>
						<span class="cldff0000">*</span><span class="cldccc">100字以内</span>
					</ul>
				</li>
				<li class="pr9">
					<ul>
						<li class="text w60 fb c1">新闻正文：</li>
						<li class="value">
							<div class="inputBt">
								<textarea id="to" class="w300 h100 inputStyle"></textarea>
							</div>
						</li>
						<span class="cldff0000">*</span><span class="cldccc">5000字以内</span>
					</ul>
				</li>
			</ul>
		</div>
		<div class="bt_icon bt_icon_b3 fr r240 pr bd0" onClick="popCompanyInfo()"><div class="text c1 pdl0">确定</div></div>
	</div>
	</body>
</html>
