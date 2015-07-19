<%@ page import="java.util.Map" %>
<%@ page import="java.util.Set" %>
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
    Map<Integer,String> maps = (Map<Integer, String>) request.getAttribute("secondaryTypes");
        String userId = request.getSession().getAttribute("addUserId").toString();
        String userType=request.getSession().getAttribute("userType").toString();
    %>
<script>
	$(function(){
		var w=$(".pad20")[0].clientWidth;
		$(".body_main").width=w;
	})
	function addCompanyInfo1(){
		$.ajax({

		   type: "GET",
	
		   url: "<%=request.getContextPath()%>/1.0/seller/addCompany",

            data: $('#companyInfo').serialize(),
	
		   success: function(result){
               if (result.code==0){
                   alert("success")
               }else{
                   alert(result.message)
               }
		   }
	
		});
	}
</script>
</head>
<body class="pad20">
		<div class="body_main">
		<form id="companyInfo" >
            <input type="hidden" name="userId" value="<%=userId%>">
            <input type="hidden" name="type" value="<%=userType%>">
		<!--filter start-->
		<div class="fieldsContainer">
			<ul class="fields search pdt0 pdb0">
				<li class="pr9">
					<ul>
						<li class="text w90 fb c1">商户二级分类：</li>
						<li class="value">
							<select name="secondaryType" class="w300">
                                <%
                                    Set<Integer> it=maps.keySet();
                                    for(Integer i:it){
                                        %>
                                <option value="<%=i%>"><%=maps.get(i)%></option>
                                <%
                                    }
                                %>

							</select>
						</li>
					</ul>
				</li>
				<li class="pr9">
					<ul>
						<li class="text w90 fb c1">商户名称：</li>
						<li class="value">
								<input type="text" name="name" class="w300 h27 inputStyle"/>
						</li>
					</ul>
				</li>
				<li class="pr9">
					<ul>
						<li class="text w90 fb c1">联系人：</li>
						<li class="value">
								<input type="text" name="contactName" class="w300 h27 inputStyle"/>
						</li>
					</ul>
				</li>
				<li class="pr9">
					<ul>
						<li class="text w90 fb c1">评分值：</li>
						<li class="value">
								<input type="text" name="grade" class="w300 h27 inputStyle"/>
						</li>
					</ul>
				</li>
			</ul>
		</div>
		</form>
		<%--<div class="bt_icon bt_icon_b3 fr r200 pr bd0" onClick="addCompanyInfo()"><div class="text c1 pdl0">上一步</div></div>--%>
		<div class="bt_icon bt_icon_b3 fr r200 pr bd0" onClick="addCompanyInfo1()"><div class="text c1 pdl0">确定</div></div>
	</div>
	</body>
</html>
