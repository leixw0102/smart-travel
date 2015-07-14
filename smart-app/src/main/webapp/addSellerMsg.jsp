<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html xmlns="http://www.w3.org/1999/xhtml" lang="en">
	<head>	
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<link rel="stylesheet" type="text/css" href="css/bridging.css"/>
		<script type="text/javascript" src="js/jquery-1.9.1.min.js" ></script>
		<script type="text/javascript" src="js/function.js" ></script>
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
						<li class="text w60 fb c1">手机号：</li>
						<li class="value">
							<input type="text" class="w300 h27 inputStyle"/>
						</li>
					</ul>
				</li>
				<li class="pr9">
					<ul>
						<li class="text w60 fb c1">密码：</li>
						<li class="value">
							<input type="text" class="w300 h27 inputStyle"/>
						</li>
					</ul>
				</li>
				<li class="ml15 pr9">
					<ul>
						<li class="text w60 fb c1">确认密码：</li>
						<li class="value">
							<input type="text" class="w300 h27 inputStyle"/>
						</li>
					</ul>
				</li>
				<li class="ml15 pr9">
					<ul>
						<li class="text w60 fb c1">商户类型：</li>
						<li class="value">
							<select class="w150">
								<option>酒店</option>
							</select>
						</li>
						<li class="value">
							<select class="w150">
								<option>精品酒店</option>
							</select>
						</li>
					</ul>
				</li>
				<li class="pr9">
					<ul>
						<li class="text w60 fb c1">商户名称：</li>
						<li class="value">
								<input type="text" class="w300 h27 inputStyle"/>
						</li>
					</ul>
				</li>
				<li class="pr9">
					<ul>
						<li class="text w60 fb c1">联系人：</li>
						<li class="value">
								<input type="text" class="w300 h27 inputStyle"/>
						</li>
					</ul>
				</li>
				<li class="pr9">
					<ul>
						<li class="text w60 fb c1">服务类型：</li>
						<li class="value">
							<select class="w300">
							
							</select>
						</li>
					</ul>
				</li>
				<li class="pr9">
					<ul>
						<li class="text w60 fb c1">评分值：</li>
						<li class="value">
								<input type="text" class="w300 h27 inputStyle"/>
						</li>
					</ul>
				</li>
				<li class="pr9">
					<ul>
						<li class="text w60 fb c1">备注：</li>
						<li class="value">
							<div class="inputBt">
								<textarea id="to" class="w300 h100 inputStyle"></textarea>
							</div>
						</li>
					</ul>
				</li>
			</ul>
		</div>
		<div class="bt_icon bt_icon_b3 fr r240 pr bd0" onClick="popCompanyInfo()"><div class="text c1 pdl0">确定</div></div>
	</div>
	</body>
</html>
