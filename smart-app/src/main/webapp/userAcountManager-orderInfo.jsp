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
   	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/plugins/popWin/css/win.css"/>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js" ></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/plugins/pagination/default-style/js/jquery.pagination.js" ></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/plugins/pagination/default-style/js/jquery.pagination.extend.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/plugins/My97DatePicker/WdatePicker.js"></script>
    <script src="<%=request.getContextPath()%>/js/plugins/jquery-ui-1.10.4.custom.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="<%=request.getContextPath()%>/js/plugins/popWin/js/jquery.window.min.js" type="text/javascript" charset="utf-8"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/function.js" ></script>
    <%
        Object user = session.getAttribute("userSessionId");
        if(null == user ){
            response.sendRedirect("login.jsp");

        }
        long pageCount = 0L;
    %>

    <script>
    var orderby = ""; //进行排序的依据 
    $(function(){
    	InitData(1);
 
		 
	})
		
		function page_callback(page_index, jq){
			 InitData(page_index);
		}
		
		function InitData(pageIndex) { 
			var tbody = ""; //声明表格中body部分 
			$.ajax({ //这里使用到Jquery的ajax方法，具体使用在这里不详细叙述 
				type: "get", 
				dataType: "json", 
				url: '<%=request.getContextPath()%>/FinaceAction?type=order_search', //请求的处理数据
//				data: "pageIndex=" + (pageIndex + 1) + "&sortType=" + orderby,
				//传入的参数，第一个参数就是分页的页数，第二个参数为排序的依据 
				//下面的操作就是成功返回数据以后，进行数据的绑定 
				success: function(data) { 
					$(".blackbor_table.listTable tr:gt(0)").remove(); 
					var myData = data;
					$.each(myData, function(i, n) { 
						var trs = ""; 
						trs += "<tr><td align='center'>" + n.number + "</td><td align='center'>" + n.orderNumber + "</td><td>" + n.mappingCompany + "</td><td>" + n.orderFL + "</td><td>" + n.orderStauts + "</td><td>" + n.orderDate + "</td></tr>";
						tbody += trs; 
					}); 
					$(tbody).appendTo(".blackbor_table.listTable"); 
				} 
			}); 
			//加入分页的绑定 
			$("#Pagination").pagination(<%=pageCount%>, { 
				callback: page_callback, 
				prev_text: '< 上一页', 
				next_text: '下一页 >', 
				items_per_page: 20, 
				num_display_entries: 6, 
				current_page: pageIndex, 
				num_edge_entries: 2 
			}); 
		} 	
    </script>
    <script type="text/javascript">
    </script>
</head>
<body>
       <!--content-->
	<div class="body_main">
               
               <table class="blackbor_table bt0 bb0"  cellspacing="0" cellpadding="0">
					<tr>
						<td>
							<div class="fieldsContainer fl">
								<ul class="fields search pdt0 pdb0 bg2" style="width:100% ">
									<li class="pr9 pb0">
										<ul>
											<li class="text w60 fb c1 pb0">订单分类：</li>
											<li class="value pb0">
												<select name="type">
													<option value="2">酒店</option>
													<option value="">景点</option>
													<option>餐饮</option>
													<option>生活</option>
												</select>
											</li>
										</ul>
									</li>
									<li class="pr9 pb0">
										<ul>
											<li class="text w60 fb c1 pb0">订单状态：</li>
											<li class="value pb0">
												<select>
													<option>待支付</option>
													<option>已取消</option>
													<option>支付中</option>
													<option>已支付</option>
													<option>已退款</option>
													<option>已完结</option>
												</select>
											</li>
										</ul>
									</li>
									<li class="pr9 pb0">
										<ul>
											<li class="text w60 fb c1 pb0">订单时间：</li>
											<li class="value pb0">
												<input type="text" class="w300 h27 inputStyle" style="width:100px"/>
											</li>
											<li class="text pb0">-</li>
											<li class="value pb0">
												<input type="text" class="w300 h27 inputStyle" style="width:100px"/>
											</li>
										</ul>
									</li>
								</ul>
							</div>
							<div class="bt_icon bt_icon_b3 fr r10 pr bd0"><div class="text c1 pdl0">查询</div></div>
						</td>
					</tr>
				
				</table>
			<table class="blackbor_table listTable"  cellspacing="0" cellpadding="0">
				<tr class="trup">
					<td width="5%">序号</td>
                    <td width="15%" >订单号</td>
                    <td width="20%" >订单对应商户</td>
                    <td width="10%" >订单分类</td>
                    <td width="10%" >订单状态</td>
                    <td width="20%" class="borderright">订单时间</td>
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
