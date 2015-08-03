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
    <%--<%   Object user = session.getAttribute("userSessionId");--%>
    <%--if(null == user ){--%>
    <%--response.sendRedirect("login.jsp");--%>

    <%--}--%>
    <%--Page<NewsInfo> pag1= (Page<NewsInfo>) request.getAttribute("msgs");%>--%>
    <script>
        var total_page = 0;
        var current_page = 1;
        var pageSize = 6 ;
        $(function(){
            //初始化请求数据列表
            queryThis(1);
        })

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
        function queryThis(page){
            var param = {
                "param.pageSize":pageSize,
                "param.currentPage":page ,
                "from":$("#from-bcd").val(),
                "to":$("#to-bcd").val()
            };
            callAPI("<%=request.getContextPath()%>/1.0/news/lists/"+page,param,queryThis_callback);
        };

        function getfinish(exp){
            if (!exp && typeof(exp)=="undefined" && exp!=0)
            {
                return "";
            }else{
                return new Date(exp).Format("yyyy-MM-dd hh:mm:ss")
            }
        }
        function queryThis_callback(data){
            total_page = Math.ceil(data.count/data.pageSize);
            current_page = data.pageNumber;
            pageSize = data.pageSize;
            var tbody = "";
            $(".blackbor_table.listTable tr:gt(0)").remove();
            var myData = data.messages;
            $.each(myData, function(i, n) {
            	var trs = ""; 
    			trs += "<tr><td align='center'>" + (++i) + "</td><td align='center'>" + n.title + "</td><td><img width='100' height='100' src='"+ n.picture+"'></td><td>" + n.abs + "</td><td>" + n.createTime + "</td><td>" + n.content + "</td></tr>";
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
            //翻页回调
            function page_callback(page_index, jq){
                current_page = page_index + 1;
                var param = {
                    "param.currentPage":current_page,
                    "param.pageSize":pageSize,
                    "from":$("#from-bcd").val(),
                    "to":$("#to-bcd").val()
                };
                callAPI("<%=request.getContextPath()%>/1.0/news/lists/"+current_page,param,queryThis_callback);
            }
        }
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
<body>
<!--head-->
<div class="head">
	<div class="up">
		<div class="logo"></div>
		<div class="user-info">

		</div>
	</div>
</div>
<div class="body_main">
    <div class="list-item-c1 h40 lh40 ti20 fwb bl1 br1 bt1">
        <span class="titleSpan fl">已发布新闻</span>
        <div class="bt_icon bt_icon_b2 fr pd10 r10 pr t8" onClick="popCompanyInfo()"><div class="text clfff pdl0">新闻发布</div></div>
    </div>
    <table class="blackbor_table bt0 bb0"  cellspacing="0" cellpadding="0">
        <tr>
            <td>
                <div class="fieldsContainer fl">
                    <ul class="fields search pdt0 pdb0 bg2" style="width:100% ">
                        <li class="pr9 pb0">
                            <ul>
                                <li class="text w60 fb c1 pb0">发布日期：</li>
                                <li class="value pb0">
                                    <input id="from-bcd" type="text" class="w300 h27 inputStyle"/>
                                </li>
                                <li class="text pb0">-</li>
                                <li class="value pb0">
                                    <input id="to-bcd" type="text" class="w300 h27 inputStyle"/>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </div>
                <div class="bt_icon bt_icon_b3 fr r10 pr bd0" onclick="queryThis(1)"><div class="text c1 pdl0">查询</div></div>
            </td>
        </tr>

    </table>


    <table class="blackbor_table listTable"  cellspacing="0" cellpadding="0">
        <tr class="trup">
            <td width="10%">序号</td>
            <td width="10%">标题</td>
            <td width="10%">图片</td>
            <td width="20%">简介</td>
            <td width="15%" >创建日期</td>
            <td width="45%">内容 </td>

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
