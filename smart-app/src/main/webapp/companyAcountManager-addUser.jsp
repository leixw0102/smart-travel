<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <% Object user = session.getAttribute("userSessionId");
        if(null == user ){
            response.sendRedirect("login.jsp");

        } %>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Insert title here</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bridging.css"/>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js" ></script>
    <script>
        $(function(){
            var w=$(".pad20")[0].clientWidth;
            $(".body_main").width=w;
        })

        function checkSubmitMobil() {
            if ($("#userName").val() == ""|| typeof($("#userName").val())=="undefined") {
                alert("手机号码不能为空！");
                return false;
            }

            if (!$("#userName").val().match(/^(((13[0-9]{1})|159|153)+\d{8})$/)) {
                alert("手机号码格式不正确！");
                return false;
            }
            return true;
        }
        function addCompanyInfo(){
//        alert($("#type").val())
            if(!checkSubmitMobil()){
                return false;
            } else{
                alert(1);
                <%--$.ajax({--%>

                    <%--type: "GET",--%>

                    <%--url: "<%=request.getContextPath()%>/1.0/seller/addUser",--%>

                    <%--data: {--%>
                        <%--"userName":$("#userName").val(),--%>
                        <%--"pwd":$("#pwd").val(),--%>
                        <%--"pwd2":$("#pwd2").val(),--%>
                        <%--"type":$("#type").val(),--%>
                        <%--"remark":$("#to").val()--%>
                    <%--},--%>
                    <%--success: function(result){--%>
                        <%--if (result.code==0){--%>
                            <%--location.href="<%=request.getContextPath()%>/1.0/seller/addCompanyPage";--%>
                        <%--}else{--%>
                            <%--alert(result.message)--%>
                        <%--}--%>

                    <%--}--%>

                <%--});--%>
            }
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
                        <li class="text w60 fb c1">手机号：</li>
                        <li class="value">
                            <input id="userName" name="userName" type="text" class="w300 h27 inputStyle"/>
                        </li>
                    </ul>
                </li>
                <li class="pr9">
                    <ul>
                        <li class="text w60 fb c1">密码：</li>
                        <li class="value">
                            <input id="pwd" type="text" name="pwd" class="w300 h27 inputStyle"/>
                        </li>
                    </ul>
                </li>
                <li class="ml15 pr9">
                    <ul>
                        <li class="text w60 fb c1">确认密码：</li>
                        <li class="value">
                            <input id="pwd2" type="text" name="pwd2" class="w300 h27 inputStyle"/>
                        </li>
                    </ul>
                </li>
                <li class="ml15 pr9">
                    <ul>
                        <li class="text w60 fb c1">商户类型：</li>
                        <li class="value">
                            <select id="type" name="type" class="w300">
                                <option selected="true" value="1">酒店</option>
                                <option value="2">景点</option>
                                <option value="3">生活</option>
                                <option value="4">美食</option>
                            </select>
                        </li>
                    </ul>
                </li>
                <li class="pr9">
                    <ul>
                        <!--<li class="text w60 fb c1">服务比例：</li>  -->
                        <li class="value">
                            <input type="hidden"  name="free" class="w300 h27 inputStyle"/>
                        </li>
                    </ul>
                </li>
                <li class="pr9">
                    <ul>
                        <li class="text w60 fb c1">备注：</li>
                        <li class="value">
                            <div class="inputBt">
                                <textarea name="remark" id="to" class="w300 h100 inputStyle"></textarea>
                            </div>
                        </li>
                    </ul>
                </li>
            </ul>
        </div>
    </form>
    <div class="bt_icon bt_icon_b3 fr r240 pr bd0" onClick="addCompanyInfo()"><div class="text c1 pdl0">下一步</div></div>
</div>
</body>
</html>
