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
        Long id=Long.parseLong(request.getParameter("id"));
    %>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bridging.css"/>
    <%--<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/uploadify.css"/>--%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js" ></script>
    <script type="text/javascript"src="<%=request.getContextPath()%>/js/ajaxfileupload-1.js" />
    <%--<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bridging.css"/>--%>
    <%--<link rel="stylesheet" href="<%=request.getContextPath()%>/js/plugins/pagination/default-style/css/pagination.css" />--%>
    <%--<link rel="stylesheet" href="<%=request.getContextPath()%>/js/plugins/pagination/default-style/css/pagination.extend.css" />--%>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/plugins/popWin/css/win.css"/>
    <%--<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js" ></script>--%>
    <%--<script type="text/javascript" src="<%=request.getContextPath()%>/js/plugins/pagination/default-style/js/jquery.pagination.js" ></script>--%>
    <%--<script type="text/javascript" src="<%=request.getContextPath()%>/js/plugins/pagination/default-style/js/jquery.pagination.extend.js"></script>--%>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/plugins/My97DatePicker/WdatePicker.js"></script>
    <script src="<%=request.getContextPath()%>/js/plugins/jquery-ui-1.10.4.custom.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="<%=request.getContextPath()%>/js/plugins/popWin/js/jquery.window.min.js" type="text/javascript" charset="utf-8"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/function.js" ></script>
    <%--<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.uploadify-3.1.min.js" />--%>

    <script type="text/javascript">

            $.ajax({

                type: "get",

                url: "<%=request.getContextPath()%>/1.0/news/view/"+<%=id%>,

                data: $('#userLogin').serialize(),

                success: function(result){
                    if (result.code==0){
                        var msg = result.info;
//                        alert(msg.title);
                        $('#title1').attr("value",msg.title);
                        $('#abs').text(msg.abs);
                        $('#content').text(msg.content)
                        $("#file1").text(msg.picture)
                    }else{
                        alert(result.message)
                    }

                }

        })

        function update(){
//            alert($('#newsInfo').serialize())
            <%--alert($('#title1').val())--%>
            <%--alert($('#abs').val())--%>
            <%--alert('<%=id%>')--%>
            <%--alert($('#content').val())--%>
            $.ajaxFileUpload
            (
                    {
                        url: '<%=request.getContextPath()%>/1.0/news/update', //用于文件上传的服务器端请求地址
                        secureuri: false, //是否需要安全协议，一般设置为false
                        fileElementId: 'file1', //文件上传域的ID
                        data: {
                                title:$('#title1').val(),
                              abs:$('#abs').val(),
                              content:$('#content').val(),
                              id:"<%=id%>"
                              },
                        dataType: 'json', //返回值类型 一般设置为json
                        success: function (data, status)  //服务器成功响应处理函数
                        {
                            if (data.code==0){
                                window.alert("success");
                            }else{
                                alert(data.message)
                            }
                        },
                        error: function (data, status, e)//服务器响应失败处理函数
                        {
                            alert(e);
                        }
                    }
            )
            return false;

        }
    </script>
</head>
<body class="pad20">
		<div class="body_main">
		<!--filter start-->
            <form id="newsInfo" >
		<div class="fieldsContainer">
			<ul class="fields search pdt0 pdb0">
				<li class="pr9">
					<ul>
						<li class="text w60 fb c1">新闻标题：</li>
						<li class="value">
							<input id="title1" name="title" type="text" class="w300 h27 inputStyle"/>
						</li>
						<span class="cldff0000">*</span><span class="cldccc">50字以内</span>
					</ul>
				</li>
				<li class="pr9">
					<ul>
						<li class="text w60 fb c1">新闻图片：</li>
						<li class="value">
							<input id="file1" type="file" name="file1"/>
                            <div id="fileQueue"></div>
						</li>
						<%--<li class="value">--%>
							<%--<div class="bt_icon bt_icon_b4 fr r10 pr bd0"><div class="text c1 pdl0">上传</div></div>--%>
						<%--</li>--%>
						<span class="cldff0000">*</span><span class="cldccc">支持JPG格式，且小于1mb</span>
					</ul>
				</li>
				
				<li class="pr9">
					<ul>
						<li class="text w60 fb c1">新闻摘要：</li>
						<li class="value">
							<div class="inputBt">
								<textarea id="abs" name="abs" class="w300 h100 inputStyle"></textarea>
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
								<textarea id="content" name="content" class="w300 h100 inputStyle"></textarea>
							</div>
						</li>
						<span class="cldff0000">*</span><span class="cldccc">5000字以内</span>
					</ul>
				</li>
			</ul>
		</div>
		<div id="abcd" class="bt_icon bt_icon_b3 fr r240 pr bd0" onclick="javascript:update()" ><div class="text c1 pdl0">确定</div></div>
                </form>
	</div>
	</body>
</html>
