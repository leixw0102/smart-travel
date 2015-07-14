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
    <%
    Map<Integer,Map<Integer,String>> types = (Map<Integer, Map<Integer, String>>) request.getAttribute("types");
    System.out.println(types.size());
    %>
<script>
    $(document).ready(function () {
        $("#btnConfirm").click(function () {
            $('#documentForm').submitForm({
                url: "/Document/SubmitDocumentCreate",
                dataType: "text",
                callback: function (data) {
                    endFileUpload();
                    data = eval("(" + data + ")");
                    alert(data.Content);
                    if (data.Result > 0) {
                        location.href = data.Redirect;
                    }
                },
                before: function () {
                    startFileUpload();
                    var errMsg = "";
                }
            }).submit();
        });

    }
	$(function(){
		var w=$(".pad20")[0].clientWidth;
		$(".body_main").width=w;
	})
    <%--function sub(){--%>
        <%--$('#companyInfo').form('submit', {--%>
            <%--url:'<%=request.getContextPath()%>/1.0/seller/add',--%>
            <%--dataType : 'json',--%>
            <%--onSubmit: function(){--%>
                <%--var result = $stewardEditForm.form("validate");//表单验证--%>
                <%--if(!result) {--%>
                    <%--return false;--%>
                <%--}--%>
            <%--},--%>
            <%--success:function(data){--%>
                <%--alert("success");--%>
            <%--}--%>
        <%--})--%>
    <%--}--%>
</script>
</head>
<body class="pad20">
		<div class="body_main">
		<form id="companyInfo" action="<%=request.getContextPath()%>/1.0/seller/add">
		<!--filter start-->
		<div class="fieldsContainer">
			<ul class="fields search pdt0 pdb0">
				<li class="pr9">
					<ul>
						<li class="text w60 fb c1">手机号：</li>
						<li class="value">
							<input name="userName" type="text" class="w300 h27 inputStyle"/>
						</li>
					</ul>
				</li>
				<li class="pr9">
					<ul>
						<li class="text w60 fb c1">密码：</li>
						<li class="value">
							<input type="text" name="pwd" class="w300 h27 inputStyle"/>
						</li>
					</ul>
				</li>
				<li class="ml15 pr9">
					<ul>
						<li class="text w60 fb c1">确认密码：</li>
						<li class="value">
							<input type="text" name="pwd2" class="w300 h27 inputStyle"/>
						</li>
					</ul>
				</li>
				<li class="ml15 pr9">
					<ul>
						<li class="text w60 fb c1">商户类型：</li>
						<li class="value">
							<select name="type" class="w150">
                                <%
        Map<Integer,String> ms=Maps.newConcurrentMap();
                                    ms.put(1,"酒店");
                                    ms.put(2,"景点");
                                    ms.put(3,"生活");
                                    ms.put(4,"美食");
                                    Set<Integer> sets=types.keySet();
                                    try{
                                        for(Integer i:sets){

                                            %>
                                <option value="<%=i%>"><%=ms.get(i)%></option>
                                <%
                                        }
                                    }catch (Exception e){

                                    }
                                %>

							</select>
						</li>
						<%--<li class="value">--%>
							<%--<select class="w150">--%>
								<%--<option>精品酒店</option>--%>
							<%--</select>--%>
						<%--</li>--%>
					</ul>
				</li>
				<%--<li class="pr9">--%>
					<%--<ul>--%>
						<%--<li class="text w60 fb c1">商户名称：</li>--%>
						<%--<li class="value">--%>
								<%--<input type="text" name="" class="w300 h27 inputStyle"/>--%>
						<%--</li>--%>
					<%--</ul>--%>
				<%--</li>--%>
				<li class="pr9">
					<ul>
						<li class="text w60 fb c1">联系人：</li>
						<li class="value">
								<input type="text" name="contactName" class="w300 h27 inputStyle"/>
						</li>
					</ul>
				</li>
				<li class="pr9">
					<ul>
						<li class="text w60 fb c1">服务费比例：</li>
						<li class="value">
                            <input type="text" name="free" class="w300 h27 inputStyle"/>
						</li>
					</ul>
				</li>
				<li class="pr9">
					<ul>
						<li class="text w60 fb c1">评分值：</li>
						<li class="value">
								<input type="text" name="grade" class="w300 h27 inputStyle"/>
						</li>
					</ul>
				</li>
				<li class="pr9">
					<ul>
						<li class="text w60 fb c1">备注：</li>
						<li class="value">
							<div class="inputBt">
								<textarea id="to" name="remark" class="w300 h100 inputStyle"></textarea>
							</div>
						</li>
					</ul>
				</li>
			</ul>
		</div>
		<%--<input type="hidden" name="type" value="0"/>--%>
		</form>
		<div class="bt_icon bt_icon_b3 fr r240 pr bd0" onclick="javascript:$('#companyInfo').submit();window.top.close()"><div class="text c1 pdl0">确定</div></div>
	</div>
	</body>
</html>
     <!-- -->