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
        Object user = session.getAttribute("userSessionId");
        if(null == user ){
            response.sendRedirect("login.jsp");

        }
        Long id = Long.parseLong(request.getParameter("id"));
    %>
    <script>
        function save(){
            var category_name = $(window.top.frames["mainFrame"].that_edit).closest("ul").find(".category-name");
            $(category_name).text($("#categoryName").val());
            $.ajax({

                type: "Post",

                url: "<%=request.getContextPath()%>/1.0/category/updateA/"+<%=id%>,

                data: $('#updateCategoryForm').serialize(),

                success: function(result){
                    if (result.code==0){
                        alert("修改成功")
//                            InitData(1);
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
    <form id="updateCategoryForm">
        <!--filter start-->
        <div class="fieldsContainer">
            <ul class="fields search pdt0 pdb0">
                <li class="pr9">
                    <ul>
                        <li class="text w60 fb c1">分类名称：</li>
                        <li class="value">
                            <input id="categoryName" name="name" type="text" class="w300 h27 inputStyle"/>
                        </li>
                    </ul>
                </li>

            </ul>
        </div>
        <%--<input type="hidden" name="type" value="0"/> javascript:$('#companyInfo').submit()--%>

        <div class="bt_icon bt_icon_b3 bd0" style="margin-left:130px;display:inline-block" onclick="save()"><div class="text c1 pdl0">确定</div></div>
    </form>
</div>
</body>
</html>
<!-- -->