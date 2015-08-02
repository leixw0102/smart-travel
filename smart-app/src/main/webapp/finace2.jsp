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
    	queryThis(2,1);
	})
	function queryThis(type,page){
    	var param = {
    		"param.pageSize":pageSize,
    		"param.currentPage":current_page
    	};
    	callAPI("<%=request.getContextPath()%>/1.0/finance/getList/"+type+"/"+page,param,queryThis_callback);
    };
    
    function queryThis_callback(data){
        total_page = Math.ceil(data.count/data.pageSize);
        current_page = data.pageNumber;
        pageSize = data.pageSize;
    	var tbody = ""; 
    	$(".blackbor_table.listTable tr:gt(0)").remove(); 
		var myData = data.messages;
		$.each(myData, function(i, n) {

			var trs = ""; 
			trs += "<tr><td align='center'>" + (++i) + "</td><td align='center'>" + n.type + "</td><td>" + n.name + "</td><td>" + n.contaceName + "</td><td>" + n.phoneNumber + "</td><td>" + n.time + "</td><td>" + n.money + "</td><td>" + + "</td>";
			trs += '<td align="center">'+
			'<div class="bt_icon bt_icon_b3 r10 pr bd0" style="display:inline-block" onClick=""><div class="text c1 pdl0">显示订单</div></div>'+
            '<div class="bt_icon bt_icon_b3 r10 pr bd0" style="display:inline-block" onClick=""><div class="text c1 pdl0">订单详情</div></div>'+
            '<div class="bt_icon bt_icon_b3 r10 pr bd0" style="display:inline-block" onClick=""><div class="text c1 pdl0">删除</div></div>'+
            '</td></tr>'
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
        alert($("#abcType").val())
   		current_page = page_index + 1;  		
   		var param = {
   				"param.currentPage":current_page,
   				"param.pageSize":pageSize};
   		callAPI("<%=request.getContextPath()%>/1.0/finance/getList/"+$("#abcType").val()+"/"+current_page,param,queryThis_callback);
	}
		
			
    </script>
    <script type="text/javascript">
    </script>
</head>
<body>
<div class="head">
    <div class="up">
        <div class="logo"></div>
        <div class="user-info">

        </div>
    </div>
</div>
<div class="body_main">
    <div class="list-item-c1 h40 lh40 ti20 fwb bl1 br1 bt1">
        <span class="titleSpan fl">财务管理</span>
    </div>
    <form id ="applyTable">
        <table class="blackbor_table bt0 bb0"  cellspacing="0" cellpadding="0">
            <tr>
                <td>
                    <div class="fieldsContainer fl">
                        <ul class="fields search pdt0 pdb0 bg2" style="width:100% ">
                            <li class="pr9 pb0">
                                <ul>
                                    <li class="text w60 fb c1 pb0">提现申请日期：</li>
                                    <li class="value pb0">
                                        <input name="from" type="text" class="w300 h27 inputStyle" onfocus="WdatePicker()"/>
                                    </li>
                                    <li class="text pb0">-</li>
                                    <li class="value pb0">
                                        <input name="to" type="text" class="w300 h27 inputStyle"  onfocus="WdatePicker()"/>
                                    </li>
                                </ul>
                            </li>
                            <li class="pr9 pb0">
                                <ul>
                                    <li class="text w60 fb c1 pb0">商户类型：</li>
                                    <li class="value pb0">
                                        <select id="abcType" name="type" class="w300">
                                            <option selected="true" value="2">酒店</option>
                                            <option  value="4">美食</option>
                                            <option value="5">景点</option>
                                            <option  value="6">美食</option>
                                        </select>
                                    </li>
                                </ul>
                            </li>
                        </ul>
                    </div>
                    <div id="abc" class="bt_icon bt_icon_b3 fr r10 pr bd0" onclick="queryThis()"  ><div class="text c1 pdl0">查询</div></div>
                </td>
            </tr>

        </table>

    </form>
    <table class="blackbor_table listTable"  cellspacing="0" cellpadding="0">
        <tr class="trup">
            <td width="3%">序号</td>
            <td width="5%">商户类型</td>
            <td width="10%" >商户名称</td>
            <td width="10%" >联系人</td>
            <td width="10%" >联系电话</td>
            <td width="10%" >提现申请日期</td>
            <td width="10%" >本次提现金额</td>
            <%--<td width="10%" >服务费用</td>--%>
            <td width="10%" >兑现日期</td>
            <%--<td width="5%" >状态</td>--%>
            <td width="17%" class="borderright">操作</td>
        </tr>

    </table>

    <div class="detail_bottom">
        <div>
            <div id="pagination"class="paginations_style" ></div> 
        </div>
    </div>
</div>
</body>
</html>
