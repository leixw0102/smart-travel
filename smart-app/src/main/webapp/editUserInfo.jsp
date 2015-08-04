<%@ page import="com.smart.model.Apply" %>
<%@ page import="com.smart.common.Page" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Insert title here</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bridging.css"/>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js" ></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/plugins/My97DatePicker/WdatePicker.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/function.js" ></script>
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
        function updateSeller(){
            if(checkSubmitMobil()){
                return false;
            } else{
                $.ajax({

                    type: "Post",

                    url: "<%=request.getContextPath()%>/1.0/seller/updateSellerUser",

                    data: $('#userSellerId').serialize(),

                    success: function(result){
                        if (result.code==0){
                            alert("修改成功")
                            window.top.frames['mainFrame'].editSellerUser_win.closeWin();
//                        window.top.frames['mainFrame'].editSellerUser_win.
                            <%--href="<%=request.getContextPath()%>/1.0/seller/getAccountLists?page=1";--%>
                        }else{
                            alert(result.message)
                        }

                    }

                });
            }
        }
    </script>

</head>
<body class="pad20">
<div class="body_main">
    <form id="userSellerId">
        <input type="hidden" name="id" value="${id}">
        <!--filter start-->
        <div class="fieldsContainer">
            <ul class="fields search pdt0 pdb0">
                <li class="pr9">
                    <ul>
                        <li class="text w60 fb c1">手机号：</li>
                        <li class="value">
                            <input name="userName" type="text" value="${userName}" class="w300 h27 inputStyle"/>
                        </li>
                    </ul>
                </li>
                <li class="pr9">
                    <ul>
                        <li class="text w60 fb c1">密码：</li>
                        <li class="value">
                            <input name="pwd" type="password" value="${pwd}" class="w300 h27 inputStyle"/>
                        </li>
                    </ul>
                </li>
                <li class="ml15 pr9">
                    <ul>
                        <li class="text w60 fb c1">确认密码：</li>
                        <li class="value">
                            <input type="password" name="pwd2" class="w300 h27 inputStyle"/>
                        </li>
                    </ul>
                </li>
                <li class="ml15 pr9">
                    <ul>
                        <li class="text w60 fb c1">商户类型：</li>
                        <li class="value">
                            <select name="type" class="w300">
                                <%
                                    String type = request.getAttribute("type").toString();
                                    if(type.equals("1")){
                                %>
                                <option selected="true" value="1">酒店</option>
                                <option value="2">景点</option>
                                <option value="3">生活</option>
                                <option value="4">美食</option>
                                <%
                                } else if(type.equals("2")){
                                %>
                                <option value="1">酒店</option>
                                <option selected="true" value="2">景点</option>
                                <option value="3">生活</option>
                                <option value="4">美食</option>
                                <%
                                } else if(type.equals("3")){
                                %>
                                <option value="1">酒店</option>
                                <option  value="2">景点</option>
                                <option selected="true" value="3">生活</option>
                                <option value="4">美食</option>
                                <%
                                } else if(type.equals("4")){
                                %>
                                <option value="1">酒店</option>
                                <option  value="2">景点</option>
                                <option  value="3">生活</option>
                                <option selected="true" value="4">美食</option>
                                <%
                                    }
                                %>

                            </select>
                        </li>
                    </ul>
                </li>
                <li class="pr9">
                    <ul>
                        <li class="text w60 fb c1">服务费比例：</li>
                        <li class="value">
                            <input type="text" value="${free}" name="free" class="w300 h27 inputStyle"/>
                        </li>
                    </ul>
                </li>
                <li class="pr9">
                    <ul>
                        <li class="text w60 fb c1">备注：</li>
                        <li class="value">
                            <div class="inputBt">
                                <textarea id="to"  name="remark" class="w300 h100 inputStyle">${remark}</textarea>
                            </div>
                        </li>
                    </ul>
                </li>
            </ul>
        </div>
    </form>
    <div class="bt_icon bt_icon_b3 fr r240 pr bd0" onClick="updateSeller()"><div class="text c1 pdl0">确定</div></div>
</div>
</body>
</html>
