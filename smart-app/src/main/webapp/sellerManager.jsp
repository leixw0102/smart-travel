<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html xmlns="http://www.w3.org/1999/xhtml" lang="en">
	<head>	
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<link rel="stylesheet" type="text/css" href="css/bridging.css"/>
		<link rel="stylesheet" href="js/plugins/pagination/default-style/css/pagination.css" />
		<link rel="stylesheet" href="js/plugins/pagination/default-style/css/pagination.extend.css" />	
		<script type="text/javascript" src="js/jquery-1.9.1.min.js" ></script>
		<script type="text/javascript" src="js/plugins/pagination/default-style/js/jquery.pagination.js" ></script>
		<script type="text/javascript" src="js/plugins/pagination/default-style/js/jquery.pagination.extend.js"></script>
		<script type="text/javascript" src="js/function.js" ></script>
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
					title:"添加商户信息",
					width:610,
					height:530,
					center:true,
					url:"addSellerMsg.jsp"
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
						<td width="10%">商户类型</td>
						<td width="20%" >商户名称</td>
                        <td width="10%" >联系人</td>
                        <td width="10%" >账户VIP类型</td>
                        <td width="10%" >创建日期</td>
						<td width="20%" class="borderright">备注</td>
					</tr>
					<tr>
						<td >1</td>
						<td class="list-link">1332893423</td>
						<td>***********</td>
						<td>sadasdasd</td>
						<td >asdasdas1</td>
						<td >2121232332</td>
						<td>sadad</td>
						<td>2015-7-11</td>
                        <td>assad</td>
					</tr>
					<tr>
						<td >2</td>
						<td  class="list-link">1332893423</td>
						<td>***********</td>
						<td>sadasdasd</td>
						<td >asdasdas1</td>
						<td >2121232332</td>
						<td>sadad</td>
						<td>2015-7-11</td>
                        <td>assad</td>
					</tr>
					<tr>
						<td >3</td>
						<td class="list-link">1332893423</td>
						<td>***********</td>
						<td>sadasdasd</td>
						<td >asdasdas1</td>
						<td >2121232332</td>
						<td>sadad</td>
						<td>2015-7-11</td>
                        <td>assad</td>
					</tr>
					<tr>
						<td >4</td>
						<td class="list-link">1332893423</td>
						<td>***********</td>
						<td>sadasdasd</td>
						<td >asdasdas1</td>
						<td >2121232332</td>
						<td>sadad</td>
						<td>2015-7-11</td>
                        <td>assad</td>
					</tr>
					<tr>
						<td >5</td>
						<td class="list-link">1332893423</td>
						<td>***********</td>
						<td>sadasdasd</td>
						<td >asdasdas1</td>
						<td >2121232332</td>
						<td>sadad</td>
						<td>2015-7-11</td>
                        <td>assad</td>
					</tr>
					<tr>
						<td >6</td>
						<td class="list-link">1332893423</td>
						<td>***********</td>
						<td>sadasdasd</td>
						<td >asdasdas1</td>
						<td >2121232332</td>
						<td>sadad</td>
						<td>2015-7-11</td>
                        <td>assad</td>
					</tr>
					
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
