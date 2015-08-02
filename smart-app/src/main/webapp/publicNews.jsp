<%@ page import="com.smart.model.NewsInfo" %>
<%@ page import="com.smart.common.Page" %>
<%@ page import="java.util.List" %>
<%@ page import="org.joda.time.DateTime" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
    <link href="<%=request.getContextPath()%>/css/bridging.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/plugins/popWin/css/win.css"/>
    <script src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/js/function.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/js/plugins/popWin/js/jquery.window.min.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/js/plugins/jquery-ui-1.10.4.custom.min.js" type="text/javascript"></script>
<%--<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bridging.css"/>--%>
<%--<link rel="stylesheet" href="<%=request.getContextPath()%>/js/plugins/pagination/default-style/css/pagination.css" />--%>
<%--<link rel="stylesheet" href="<%=request.getContextPath()%>/js/plugins/pagination/default-style/css/pagination.extend.css" />--%>
<%--&lt;%&ndash;<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js" ></script>&ndash;%&gt;--%>
<%--<script type="text/javascript" src="<%=request.getContextPath()%>/js/plugins/pagination/default-style/js/jquery.pagination.js" ></script>--%>
<%--<script type="text/javascript" src="<%=request.getContextPath()%>/js/plugins/pagination/default-style/js/jquery.pagination.extend.js"></script>--%>
<%--<script type="text/javascript" src="<%=request.getContextPath()%>/js/function.js" ></script>--%>
 <%   Object user = session.getAttribute("userSessionId");
     if(null == user ){
         response.sendRedirect("login.jsp");

     }
     Page<NewsInfo> pag1= (Page<NewsInfo>) request.getAttribute("msgs");%>
<script>
$(function(){
	var w=$(".pad20")[0].clientWidth;
	$(".body_main").width=w;
})
$(function(){
var initPagination = function() {
	var total_page = $("#hiddenresult div.result").length;
	var total_page1 = $("#hiddenresult div.result1").length;
	// 创建分页
	$("#pagination").pagination(total_page,{
		callback: page_callback,
		items_per_page : 1,				
		prev_text:"上一页",
		next_text:"下一页",
		num_edge_entries : 3,			//边缘值
		ellipse_text : '...',			//边缘显示
		num_display_entries : 5	,		//显示条数
		link_to : 'javascript:void(0)'
		
	});
	$("#page").pagination(total_page1,{
		callback: page_callback,
		items_per_page : 1,				
		prev_text:"上一页",
		next_text:"下一页",
		num_edge_entries : 3,			//边缘值
		ellipse_text : '...',			//边缘显示
		num_display_entries : 5	,		//显示条数
		link_to : 'javascript:void(0)'
		
	});
 }();

 function page_callback(page_index, jq){
	var new_content = $("#hiddenresult div.result:eq("+page_index+")").clone();
	$("#Searchresult").empty().append(new_content); //装载对应分页的内容
	return false;
}
})
function popCompanyInfo(){
	window.top.$.popWin({
		title:"编辑发布新闻页",
		width:610,
		height:430,
		center:true,
		url:"<%=request.getContextPath()%>/publicNews-edit.jsp"
	});
}

</script>
</head>
<body class="pad20">
		<div class="body_main">
            	<div class="list-item-c1 h40 lh40 ti20 fwb bl1 br1 bt1">
					<span class="titleSpan fl">已发布新闻</span>
					<div class="bt_icon bt_icon_b2 fr pd10 r10 pr t8" onClick="popCompanyInfo()"><div class="text clfff pdl0">新闻发布</div></div>
                </div>
				<%--<table class="blackbor_table bt0 bb0"  cellspacing="0" cellpadding="0">--%>
					<%--<tr>--%>
						<%--<td>--%>
							<%--<div class="fieldsContainer fl">--%>
								<%--<ul class="fields search pdt0 pdb0 bg2" style="width:100% ">--%>
									<%--<li class="pr9 pb0">--%>
										<%--<ul>--%>
											<%--<li class="text w60 fb c1 pb0">发布日期：</li>--%>
											<%--<li class="value pb0">--%>
												<%--<input type="text" class="w300 h27 inputStyle"/>--%>
											<%--</li>--%>
											<%--<li class="text pb0">-</li>--%>
											<%--<li class="value pb0">--%>
												<%--<input type="text" class="w300 h27 inputStyle"/>--%>
											<%--</li>--%>
										<%--</ul>--%>
									<%--</li>--%>
								<%--</ul>--%>
							<%--</div>--%>
							<%--<div class="bt_icon bt_icon_b3 fr r10 pr bd0"><div class="text c1 pdl0">查询</div></div>--%>
						<%--</td>--%>
					<%--</tr>--%>
				<%----%>
				<%--</table>--%>
				
                
				<table class="blackbor_table"  cellspacing="0" cellpadding="0">
					<tr class="trup">
					    <td width="10%">序号</td>
						<td width="10%">标题</td>
						<td width="20%">图片</td>
						<td width="20%">简介</td>
						<td width="15%" >创建日期</td>
						<td width="45%">内容 </td>
					</tr>
					 <%
						int id =0;
					 List<NewsInfo> infos=pag1.getMessages();
					   if(null == infos || infos.isEmpty()){

					   }   else{
						   for(NewsInfo info : infos){
							   ++id;
						   %>
					  <tr>
						<td ><%=id%></td>
						<td class="list-link"><%=info.getTitle()%></td>
						<td><img src=<%=info.getPicture()%> height="100" width="100" /></td>
						<td><%=info.getAbs()%></td>
						<td ><%=info.getCreateTime()%></td>
						<td ><%=info.getContent()%></td>
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
