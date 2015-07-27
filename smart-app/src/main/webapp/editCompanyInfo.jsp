<%@ page import="com.smart.model.Apply" %>
<%@ page import="com.smart.common.Page" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.Set" %>
<%@ page import="com.smart.model.CompanyInfo" %>
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
    <%
        Object user = session.getAttribute("userSessionId");
        if(null == user ){
            response.sendRedirect("login.jsp");

        }
    %>
    <script>
        $(function(){
            var w=$(".pad20")[0].clientWidth;
            $(".body_main").width=w;
        })
        function updateCompany(){
            $.ajax({

                type: "Post",

                url: "<%=request.getContextPath()%>/1.0/seller/updateCompanyInfo",

                data: $('#companyInfo1').serialize(),

                success: function(result){
                    if (result.code==0){
                        alert("修改成功")
                        window.top.frames['mainFrame'].editCompany_win.closeWin();
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
    <form id="companyInfo1" >
        <input type="hidden" name="id" value="${user.id}">
        <input type="hidden" name="type" value="${user.type}">
        <input type="hidden" name="userId" value="${user.userId}">
        <!--filter start-->
        <div class="fieldsContainer">
            <ul class="fields search pdt0 pdb0">
                <li class="pr9">
                    <ul>
                        <li class="text w90 fb c1">商户二级分类：</li>
                        <li class="value">
                            <select name="secondaryType" class="w300">
                                <%
                                    Integer secondaryType =((CompanyInfo) request.getAttribute("user")).getSecondaryType();
                                    Map<Integer,String> maps = (Map<Integer, String>) request.getAttribute("secondaryTypeInfo");
                                    Set<Integer> keys = maps.keySet();
                                    for(Integer key : keys ){
                                        if(key == secondaryType){

                                %>

                                <option selected="true" value="<%=key%>"><%=maps.get(key)%></option>

                                <%
                                        } else{
                                            %>
                                <option  value="<%=key%>"><%=maps.get(key)%></option>
                                <%
                                        }
                                    }%>
                            </select>
                        </li>
                    </ul>
                </li>
                <li class="pr9">
                    <ul>
                        <li class="text w90 fb c1">商户名称：</li>
                        <li class="value">
                            <input type="text" name="name" value="${user.name}" class="w300 h27 inputStyle"/>
                        </li>
                    </ul>
                </li>
                <li class="pr9">
                    <ul>
                        <li class="text w90 fb c1">联系人：</li>
                        <li class="value">
                            <input name="contactName" type="text" value="${user.contactName}" class="w300 h27 inputStyle"/>
                        </li>
                    </ul>
                </li>
                <li class="pr9">
                    <ul>
                        <li class="text w90 fb c1">评分值：</li>
                        <li class="value">
                            <input type="text" name="grade" value="${user.grade}" class="w300 h27 inputStyle"/>
                        </li>
                    </ul>
                </li>
            </ul>
        </div>
    </form>
    <div class="bt_icon bt_icon_b3 fr r200 pr bd0" onclick="updateCompany()"><div class="text c1 pdl0">确定</div></div>
</div>
</body>
</html>
