<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bridging.css"/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/js/plugins/pagination/default-style/css/pagination.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/js/plugins/pagination/default-style/css/pagination.extend.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/plugins/pagination/default-style/js/jquery.pagination.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/plugins/pagination/default-style/js/jquery.pagination.extend.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/function.js" ></script>

</head>
<body class="pad20">
<div class="body_main">
    <ul class="level">
        <li class="list-item-c1 h40">
            <ul>
                <li class="title">功能流程说明</li>

            </ul>
        </li>
        <li class="list-item-c1 h40">
            <ul >
                <li class="toggle_bt category-name">账户分类：系统用户，新闻发布用户，财务管理账号</li>

            </ul>

        </li>

        <li class="list-item-c1 h40">
                    <ul >
                        <li class="toggle_bt category-name">系统用户功能：1.编辑添加分类管理 2.商户账号管理，包括商户创建修改，产品创建修改 3.用户账号管理：显示用户信息，查询用户订单，删除等功能。4.财务账号管理：创建，修改密码等. 5.新闻账号管理：创建修改密码等</li>
                    </ul>

                </li>
  <li class="list-item-c1 h40">
                    <ul >
                        <li class="toggle_bt category-name">新闻发布功能：1.查询相关新闻。2发布新闻。3预览新闻。4.编辑新闻</li>
                    </ul>

                </li>
                  <li class="list-item-c1 h40">
                                                                         <ul >
                                                                             <li class="toggle_bt category-name">财务账号管理功能：1.商户申请操作审核。2.查询商户订单</li>
                                                                         </ul>

                                                                     </li>
                                                                     <li class="list-item-c1 h40">
                                                                                                         <ul >
                                                                                                             <li class="toggle_bt category-name">统计功能：1.整体统计。2.酒店统计3.餐饮统计。4.生活统计</li>
                                                                                                         </ul>

                                                                                                     </li>

</body>
</html>