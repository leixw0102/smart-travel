<%@ page import="com.smart.model.UserInfo" %>
<%@ page import="com.smart.common.Page" %>
<%@ page import="java.util.List" %>
<%@ page import="com.smart.model.SellerInfo" %>
<%@ page import="org.joda.time.DateTime" %>
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
        Object user = session.getAttribute("userSessionId");
        if(null == user ){
            response.sendRedirect("login.jsp");

        }
        Page<SellerInfo> pag1= (Page<SellerInfo>) request.getAttribute("msgs");
        long size = pag1.getCount()/pag1.getPageSize()==0?pag1.getCount()/pag1.getPageSize()+1:pag1.getCount()/pag1.getPageSize();
    %>
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
        current_page:<%=pag1.getPageNumber()-1%>,
		num_edge_entries : 3,			//边缘值
		ellipse_text : '...',			//边缘显示
		num_display_entries : 5	,		//显示条数
		link_to : 'javascript:void(0)'
		
	});
 }();

 function page_callback(page_index, jq){
     location.href="<%=request.getContextPath()%>/1.0/seller/getAccountLists?page="+(page_index+1)
	return false;
}
})
function popCompanyInfo(){
	window.top.$.popWin({
		title:"添加商户信息",
		width:610,
		height:530,
		center:true,
		url:"<%=request.getContextPath()%>/1.0/seller/addPage"
	});
}

var editSellerUser_win;
function editUserInfo(id){

    editSellerUser_win=window.top.$.popWin({
		title:"编辑用户信息",
		width:610,
		height:530,
		center:true,
		url:"<%=request.getContextPath()%>/1.0/seller/editSeller/"+id
	});
}

function editCompanyInfo(id){
	window.top.$.popWin({
		title:"编辑商户信息",
		width:610,
		height:330,
		center:true,
		url:"<%=request.getContextPath()%>/1.0/seller/editCompanyPage/"+id
	});
}
</script>
</head>
<body class="pad20">
		<div class="body_main">
            	<div class="list-item-c1 h40 lh40 ti20 fwb bl1 br1 bt1">
					<span class="titleSpan fl">商户账号管理</span>
					<div class="bt_icon bt_icon_b2 fr pd10 r10 pr t8" onClick="popCompanyInfo()"><div class="text clfff pdl0">增加账户</div></div>
                </div>
                
				<table class="blackbor_table"  cellspacing="0" cellpadding="0">
					<tr class="trup">
						<td width="5%">序号</td>
						<td width="10%">用户名(手机号)</td>
						<td width="5%">密码</td>
						<td width="5%">商户类型</td>
						<%--<td width="20%" >商户名称</td>--%>
                        <%--<td width="10%" >联系人</td>--%>
                        <td width="5%" >服务费比例</td>
                        <td width="10%" >创建日期</td>
						<td width="10%" class="borderright">备注</td>
						<td width="20%" class="borderright">操作</td>
					</tr>
                    <%
                        int id =(pag1.getPageNumber()-1)*pag1.getPageSize();
                     List<SellerInfo> infos=pag1.getMessages();
                       if(null == infos || infos.isEmpty()){

                       }   else{
                           for(SellerInfo info : infos){
                               ++id;
                           %>
                    <tr>
                        <td ><%=id%></td>
                        <td class="list-link" style="cursor:pointer" onclick="editUserInfo('<%=info.getId()%>')"><%=info.getUserName()%></td>
                        <td><%=info.getPwd()%></td>
                        <td><%=info.getType()%></td>
                        <%--<td ><%=info.getSellerName()%></td>--%>
                        <%--<td ><%=info.getContactName()%></td>--%>
                        <td><%=info.getFree()%></td>
                        <td><%=new DateTime(info.getCreateTime()).toString("yyyy-MM-dd HH:mm:ss")%></td>
                        <td><%=info.getRemark()%></td>
                        <td><div class="bt_icon bt_icon_b3 r10 pr bd0" style="display:inline-block" onClick="editCompanyInfo(<%=info.getId()%>)"><div class="text c1 pdl0">修改商户信息</div></div></td>
                    </tr>
                    <%
                            } }
                    %>

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
