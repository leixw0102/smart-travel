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
    <link rel="stylesheet" href="<%=request.getContextPath()%>/js/plugins/pagination/default-style/css/pagination.css" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/js/plugins/pagination/default-style/css/pagination.extend.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/plugins/popWin/css/win.css"/>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js" ></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/plugins/pagination/default-style/js/jquery.pagination.js" ></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/plugins/pagination/default-style/js/jquery.pagination.extend.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/plugins/My97DatePicker/WdatePicker.js"></script>
    <script src="<%=request.getContextPath()%>/js/plugins/jquery-ui-1.10.4.custom.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="<%=request.getContextPath()%>/js/plugins/popWin/js/jquery.window.min.js" type="text/javascript" charset="utf-8"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/function.js" ></script>
    <%
        Long id = Long.parseLong(request.getParameter("id"));
    %>
 <script type="text/javascript">
     $(function(){
         //初始化请求数据列表
         $.ajax({

             type: "Post",

             url: "<%=request.getContextPath()%>/1.0/news/view/"+<%=id%>,

//             data: $('#userLogin').serialize(),

             success: function(result){
                 if (result.code==0){
                     var msg=result.info;
                     $("#titleId").text(msg.title)
                     $("#pictureId").attr("src",msg.picture)
                     $("#absId").text(msg.abs)
                     $("#contentId").text(msg.content)
                     <%--location.href="<%=request.getContextPath()%>"+result.info;--%>
                 }else{
                     alert(result.message)
                 }

             }

         });
     })
//     function editInfo(){
//
//      window.top.frames['mainFrame'].viewInfoPop.closeWin();
//      window.top.frames['mainFrame'].popCompanyInfo();
//     }
 </script>
</head>
<body class="pad20">
		<div class="body_main">
		<%--<div class="bt_icon bt_icon_b3 bd0" style="margin-right:20px;float:right;"><div class="text c1 pdl0">修改</div></div>--%>
		<!--filter start-->
            <form id="newsInfo" >
		<div class="fieldsContainer">
			<ul class="fields search pdt0 pdb0">
			
				<li class="pr9" style="clear:left">
					<ul>
						<li class="text w60 fb c1">新闻标题：</li>
						<li id="titleId" class="value">
						</li>
					</ul>
				</li>
				<li class="pr9" style="clear:left">
					<ul>
						<li class="text w60 fb c1">新闻图片：</li>
						<li class="value">
							<img id="pictureId" src=""/>
						</li>
					</ul>
				</li>
				
				<li class="pr9" style="clear:left">
					<ul>
						<li class="text w60 fb c1">新闻摘要：</li>
						<li class="value">
							<div id="absId" class="inputBt">
							</div>
						</li>
					
					</ul>
				</li>
				<li class="pr9" style="clear:left">
					<ul>
						<li class="text w60 fb c1">新闻正文：</li>
						<li class="value">
							<div id="contentId" class="inputBt">
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
