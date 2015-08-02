<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <%
        Object user = session.getAttribute("userSessionId");
        if(null == user ){
            response.sendRedirect("login.jsp");

        }
    %>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bridging.css"/>
    <%--<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/uploadify.css"/>--%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js" ></script>
    <script type="text/javascript"src="<%=request.getContextPath()%>/js/ajaxfileupload-1.js" />
    <%--<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.uploadify-3.1.min.js" />--%>
 <script type="text/javascript">

     function editInfo(){
      
      window.top.frames['mainFrame'].viewInfoPop.closeWin();
      window.top.frames['mainFrame'].popCompanyInfo();
     }
 </script>
</head>
<body class="pad20">
		<div class="body_main">
		<div class="bt_icon bt_icon_b3 bd0" style="margin-right:20px;float:right;" onclick="editInfo()"><div class="text c1 pdl0">修改</div></div>
		<!--filter start-->
            <form id="newsInfo" >
		<div class="fieldsContainer">
			<ul class="fields search pdt0 pdb0">
			
				<li class="pr9" style="clear:left">
					<ul>
						<li class="text w60 fb c1">新闻标题：</li>
						<li class="value">
							测试
						</li>
					</ul>
				</li>
				<li class="pr9" style="clear:left">
					<ul>
						<li class="text w60 fb c1">新闻图片：</li>
						<li class="value">
							<img src=""/>
						</li>
					</ul>
				</li>
				
				<li class="pr9" style="clear:left">
					<ul>
						<li class="text w60 fb c1">新闻摘要：</li>
						<li class="value">
							<div class="inputBt">
								海河红色一日游起程 水陆联游红色景点历史遗址
							</div>
						</li>
					
					</ul>
				</li>
				<li class="pr9" style="clear:left">
					<ul>
						<li class="text w60 fb c1">新闻正文：</li>
						<li class="value">
							<div class="inputBt">
								天津频道 记者从铁路天津站了解到，随着暑运高峰的到来，为方便旅客办理购票、取票及退改签等业务，天津站
							</div>
						</li>
					</ul>
				</li>
			</ul>
		</div>
                </form>
	</div>
	</body>
</html>
