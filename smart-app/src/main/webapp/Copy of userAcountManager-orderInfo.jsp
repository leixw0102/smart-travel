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
    %>

    <script>
    var total_page = 0;
    var current_page = 1;
    var pageSize = 6 ;
    $(function(){
    	//初始化请求数据列表
    	queryThis();
	})
	function queryThis(){
    	var param = {
    		"param.pageSize":pageSize,
    		"param.currentPage":current_page
    	};
    	callAPI("<%=request.getContextPath()%>/FinaceAction?type=order_search",param,queryThis_callback);
    };
    
    function queryThis_callback(data){
    	total_page = data.total_page;
    	current_page = data.current_page;
    	pageSize = data.pageSize;
    	var tbody = ""; 
    	$(".blackbor_table.listTable tr:gt(0)").remove(); 
		var myData = data.list;
		$.each(myData, function(i, n) { 
			var trs = ""; 
			trs += "<tr><td align='center'>" + n.number + "</td><td align='center'>" + n.orderNumber + "</td><td>" + n.mappingCompany + "</td><td>" + n.orderFL + "</td><td>" + n.orderStauts + "</td><td>" + n.orderDate + "</td></tr>";
			tbody += trs; 
		}); 
		$(tbody).appendTo(".blackbor_table.listTable"); 
		
		$("#pagination").pagination(total_page,{
			callback: page_callback,
			items_per_page : 1,				
			prev_text:"上一页",
			next_text:"下一页",
			num_edge_entries : 3,			//边缘值
			ellipse_text : '...',			//边缘显示
			num_display_entries : 5	,		//显示条数
			current_page : current_page - 1,
			link_to : 'javascript:void(0)'
		});
    	
    }
    //翻页回调
	function page_callback(page_index, jq){
   		current_page = index + 1;  		
   		var param = {
   				"param.currentPage":current_page,
   				"param.pageSize":pageSize};
   		callAPI("<%=request.getContextPath()%>/FinaceAction?type=order_search",param,queryThis_callback);
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
												<select>
													<option>酒店</option>
													<option>景点</option>
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
					<div id="pagination" class="paginations_style" ></div>
					</div>
				</div>
			</div>
	</div>
</body>
</html>
