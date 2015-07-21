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
    <link rel="stylesheet" href="<%=request.getContextPath()%>/js/plugins/pagination/default-style/css/pagination.css" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/js/plugins/pagination/default-style/css/pagination.extend.css" />
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js" ></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/plugins/pagination/default-style/js/jquery.pagination.js" ></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/plugins/pagination/default-style/js/jquery.pagination.extend.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/function.js" ></script>
    <%
        Page<Apply> applies= (Page<Apply>) request.getAttribute("appliesList");
        long size =0L;
        if(applies.getCount()!=0){
            size = applies.getCount()/applies.getPageSize()==0?applies.getCount()/applies.getPageSize()+1:applies.getCount()/applies.getPageSize();
        }  %>
    <script>
        $(function(){
            var w=$(".pad20")[0].clientWidth;
            $(".body_main").width=w;
        })
        $(function(){
            var initPagination = function() {
                var total_page = '<%=size%>';
                // 创建分页
                $("#pagination").pagination(total_page,{
                    callback: page_callback,
                    items_per_page : 1,
                    prev_text:"上一页",
                    next_text:"下一页",
                    current_page:<%=applies.getPageNumber()-1%>,
                    num_edge_entries : 3,			//边缘值
                    ellipse_text : '...',			//边缘显示
                    num_display_entries : 5	,		//显示条数
                    link_to : 'javascript:void(0)'

                });
            }();

            function page_callback(page_index, jq){

                return false;
            }
        })

        function search(){
            $.ajax({

                type: "GET",

                url: "/smart-app/FinaceAction",

                data: "type=search",

                success: function(msg){
                    alert("search sucess");
                }

            });
        }

        function confirm(id){
            alert(id)
            var methodType = 'Get';
            $.ajax({
                url: "<%=request.getContextPath()%>/1.0/finance/confirm/"+id,
                type: methodType,
//            data: $('#companyInfo').serialize(),
                contentType:"application/json;charset=utf-8",

                success: function (result) {

                    if (result.code==0){
                        alert("success");
//                    location.href=
                    }else{
                        alert(result.message)
                    }
                }
            });
        }
        function refuse(id){
            alert(id)
            var methodType = 'Get';
            $.ajax({
                url: "<%=request.getContextPath()%>/1.0/finance/refuse/"+id,
                type: methodType,
//            data: $('#companyInfo').serialize(),
                contentType:"application/json;charset=utf-8",

                success: function (result) {

                    if (result.code==0){
                        alert("success");
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
    <div class="list-item-c1 h40 lh40 ti20 fwb bl1 br1 bt1">
        <span class="titleSpan fl">财务管理</span>
    </div>
    <table class="blackbor_table bt0 bb0"  cellspacing="0" cellpadding="0">
        <tr>
            <td>
                <div class="fieldsContainer fl">
                    <ul class="fields search pdt0 pdb0 bg2" style="width:100% ">
                        <li class="pr9 pb0">
                            <ul>
                                <li class="text w60 fb c1 pb0">提现申请日期：</li>
                                <li class="value pb0">
                                    <input type="text" class="w300 h27 inputStyle"/>
                                </li>
                                <li class="text pb0">-</li>
                                <li class="value pb0">
                                    <input type="text" class="w300 h27 inputStyle"/>
                                </li>
                            </ul>
                        </li>
                        <li class="pr9 pb0">
                            <ul>
                                <li class="text w60 fb c1 pb0">商户类型：</li>
                                <li class="value pb0">
                                    <select class="w300">
                                        <option>酒店</option>
                                    </select>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </div>
                <div class="bt_icon bt_icon_b3 fr r10 pr bd0" onclick="search()"><div class="text c1 pdl0">查询</div></div>
            </td>
        </tr>

    </table>


    <table class="blackbor_table"  cellspacing="0" cellpadding="0">
        <tr class="trup">
            <td width="3%">序号</td>
            <td width="5%">商户类型</td>
            <td width="10%" >商户名称</td>
            <td width="10%" >联系人</td>
            <td width="10%" >联系电话</td>
            <td width="10%" >提现申请日期</td>
            <td width="10%" >本次提现金额</td>
            <td width="10%" >服务费用</td>
            <td width="10%" >兑现日期</td>
            <%--<td width="5%" >状态</td>--%>
            <td width="17%" class="borderright">操作</td>
        </tr>
        <%  int id =(applies.getPageNumber()-1)*applies.getPageSize();
            List<Apply> as=applies.getMessages();
            for(Apply apply : as){
                ++id;
        %>
        <tr>
            <td ><%=id%></td>
            <td><%=apply.getType()%></td>
            <td><%=apply.getName()%></td>
            <td><%=apply.getContactName()%></td>
            <td ><%=apply.getPhoneNumber()%></td>
            <td ><%=apply.getTime()%></td>
            <td><%=apply.getMoney()%></td>
            <td><%=apply.getServiceCharge()%></td>
            <td><%=apply.getFinishTime()%></td>
            <td>
                <% if(apply.getStatus()==1){%>
                <div class="bt_icon bt_icon_b3 fr r10 pr bd0" onclick="confirm('<%=apply.getId()%>')"><div class="text c1 pdl0">兑现</div></div>
                <div class="bt_icon bt_icon_b3 fr r10 pr bd0" onclick="refuse('<%=apply.getId()%>')"><div class="text c1 pdl0">拒绝</div></div>
                <%}else if(apply.getStatus()==2){%>
                <div class="text c1 pdl0">已兑现</div>
                <%}else if(apply.getStatus()==3){%>
                <div class="text c1 pdl0">已拒绝</div>
                <%}%>
            </td>
        </tr>
        <%}%>
    </table>
    <div class="detail_bottom">
        <div>
            <div id="pagination"class="paginations_style" ></div>
            <div id="Searchresult" class="Searchresult_style"></div>
            <div id="hiddenresult" class="disp">
                <!-- 列表元素 -->
                <div></div>
                <div class="result"></div>
                <div class="result"></div>
                <div class="result"></div>
                <div class="result"></div>
                <div class="result"></div>
                <div class="result"></div>
                <div></div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
