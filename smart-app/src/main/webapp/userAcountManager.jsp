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
        long pageCount = 10L;
    %>

    <script>
        var total_page = 0;
        var current_page = 1;
        var pageSize = 6 ;
    var orderby = ""; //进行排序的依据 
    $(function(){
//    	InitData(1);
        queryThis();
	})
    // 对Date的扩展，将 Date 转化为指定格式的String
    // 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符，
    // 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)
    // 例子：
    // (new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423
    // (new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18
    Date.prototype.Format = function(fmt)
    { //author: meizz
        var o = {
            "M+" : this.getMonth()+1,                 //月份
            "d+" : this.getDate(),                    //日
            "h+" : this.getHours(),                   //小时
            "m+" : this.getMinutes(),                 //分
            "s+" : this.getSeconds(),                 //秒
            "q+" : Math.floor((this.getMonth()+3)/3), //季度
            "S"  : this.getMilliseconds()             //毫秒
        };
        if(/(y+)/.test(fmt))
            fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
        for(var k in o)
            if(new RegExp("("+ k +")").test(fmt))
                fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
        return fmt;
    }
//		function page_callback(page_index, jq){
//			 InitData(page_index);
//		}
        function queryThis(){
            var param = {
                "param.pageSize":pageSize,
                "param.currentPage":current_page
            };
            callAPI("<%=request.getContextPath()%>/1.0/order/clientUsers/"+current_page,param,queryThis_callback);
        };

        function queryThis_callback(data){
            total_page = Math.ceil(data.count/data.pageSize);
            current_page = data.pageNumber;
            pageSize = data.pageSize;
            var tbody = "";
            $(".blackbor_table tr:gt(0)").remove();
            var myData = data.messages;
            $.each(myData, function(i, n) {
                var trs = "";
                trs += "<tr><td align='center'>" + (++i) + "</td><td align='center'>" + n.userName + "</td><td>" + n.contactName + "</td><td>" + new Date(n.createDate).Format("yyyy-MM-dd hh:mm:ss") + "</td><td>" + n.mark+ "</td>";
                trs += '<td align="center">'+
                '<div class="bt_icon bt_icon_b3 r10 pr bd0" style="display:inline-block" onClick="popOrderInfo('+ n.id+')"><div class="text c1 pdl0">订单详情</div></div>'+
                '<div class="bt_icon bt_icon_b3 r10 pr bd0" style="display:inline-block" onClick="deleteData('+n.id+')"><div class="text c1 pdl0">删除</div></div>'+
                '</td></tr>'
                tbody += trs;
            });
            $(tbody).appendTo(".blackbor_table");

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
            current_page = page_index + 1;
            var param = {
                "param.currentPage":current_page,
                "param.pageSize":pageSize};
            callAPI("<%=request.getContextPath()%>/1.0/order/clientUsers/"+current_page,param,queryThis_callback);
        }

		<%--function InitData(pageIndex) { --%>
			<%--var tbody = ""; //声明表格中body部分--%>
            <%--var totalPage;--%>
			<%--$.ajax({ //这里使用到Jquery的ajax方法，具体使用在这里不详细叙述 --%>
				<%--type: "get", --%>
				<%--dataType: "json", --%>
				<%--&lt;%&ndash;url: '<%=request.getContextPath()%>/FinaceAction?type=user_search', //请求的处理数据&ndash;%&gt;--%>
                <%--url:'<%=request.getContextPath()%>/1.0/order/clientUsers/'+pageIndex,--%>
<%--//				data: "pageIndex=" + (pageIndex + 1) + "&sortType=" + orderby,--%>
				<%--//传入的参数，第一个参数就是分页的页数，第二个参数为排序的依据 --%>
				<%--//下面的操作就是成功返回数据以后，进行数据的绑定 --%>
				<%--success: function(data) { --%>
					<%--$(".blackbor_table tr:gt(0)").remove(); --%>
					<%--var myData = data.messages;--%>
                    <%--totalPage=Math.ceil(data.count/data.pageSize)--%>
<%--//                    alert(totalPage)--%>
					<%--$.each(myData, function(i, n) {--%>
						<%--var trs = ""; --%>
						<%--trs += "<tr><td align='center'>" + (++i) + "</td><td align='center'>" + n.userName + "</td><td>" + n.contactName + "</td><td>" + new Date(n.createDate).Format("yyyy-MM-dd hh:mm:ss") + "</td><td>" + n.mark+ "</td>";--%>
						<%--trs += '<td align="center">'+--%>
									<%--'<div class="bt_icon bt_icon_b3 r10 pr bd0" style="display:inline-block" onClick="popOrderInfo('+ n.id+')"><div class="text c1 pdl0">订单详情</div></div>'+--%>
			                       	<%--'<div class="bt_icon bt_icon_b3 r10 pr bd0" style="display:inline-block" onClick="deleteData('+n.id+')"><div class="text c1 pdl0">删除</div></div>'+--%>
			                    <%--'</td></tr>'--%>
						<%--tbody += trs; --%>
					<%--}); --%>
					<%--$(tbody).appendTo(".blackbor_table");--%>
                     <%--alert(totalPage)--%>
<%--//                    alert($("#pagination").text())--%>

				<%--}--%>
			<%--});--%>
            <%--//加入分页的绑定--%>
            <%--$("#pagination").pagination(totalPage, {--%>

                <%--callback: page_callback,--%>
                <%--prev_text: '< 上一页',--%>
                <%--next_text: '下一页 >',--%>
                <%--items_per_page: 1,--%>
                <%--num_display_entries: 6,--%>
                <%--current_page: pageIndex-1,--%>
                <%--num_edge_entries: 5--%>
            <%--});--%>
		<%--}--%>

		function deleteData(id){
			var r = window.confirm('你确定要取消删除吗？');
			if(r){
                $.ajax({

                    type: "Post",

                    url: "<%=request.getContextPath()%>/1.0/order/delete/"+id,

//                    data: $('#userLogin').serialize(),

                    success: function(result){
                        if (result.code==0){
                            alert("删除成功")
                            InitData(1)
                        }else{
                            alert(result.message)
                        }

                    }

                });
			}
		}
    </script>
    <script type="text/javascript">
    function popOrderInfo(id){
		window.top.$.popWin({
			title:"订单详情",
			width:800,
			height:350,
			center:true,
			url:"<%=request.getContextPath()%>/1.0/order/getUserOrderList/"+id
		});
	}
    </script>
</head>
<body>
	<!--head-->

       <!--content-->
	<div class="body_main">
           	<div class="list-item-c1 h40 lh40 ti20 fwb bl1 br1 bt1">
				<span class="titleSpan fl">财务账号管理</span>
               </div>
               
			<table class="blackbor_table"  cellspacing="0" cellpadding="0">
				<tr class="trup">
					<td width="5%">序号</td>
					<td width="15%" >用户名(手机号)</td>
					<td width="15%" >联系人</td>
					<td width="15%" >注册日期</td>
                    <td width="20%">备注</td>
                    <td width="30%" class="borderright">操作</td>
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
