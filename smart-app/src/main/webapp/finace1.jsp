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
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/plugins/My97DatePicker/WdatePicker.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/function.js" ></script>
    <%--<%    Object user = session.getAttribute("userSessionId");--%>
        <%--if(null == user ){--%>
            <%--response.sendRedirect("login.jsp");--%>
<%----%>
        <%--}--%>
<%--//        Page<Apply> applies= (Page<Apply>) request.getAttribute("appliesList");--%>
<%--//        long size =0L;--%>
<%--//        if(applies.getCount()!=0){--%>
<%--//            size = applies.getCount()/applies.getPageSize()==0?applies.getCount()/applies.getPageSize()+1:applies.getCount()/applies.getPageSize();--%>
         <%--%>--%>
    <script>
        var total_page = 0;
        var current_page = 1;
        var pageSize = 6 ;
        var orderby = ""; //进行排序的依据
        $(function(){
            var w=$(".pad20")[0].clientWidth;
            $(".body_main").width=w;
        })
        $(function(){
            queryThis(2,1);
        })


        function confirm(id){
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

        function orderMsg(id){
            window.top.$.popWin({
                title:"密码重置",
                width:1000,
                height:500,
                center:true,
                url:"<%=request.getContextPath()%>/1.0/user/getOrderMsgPage/"+id
            });

        }
        <%--var totalPage=0;--%>
        <%--var pageSize=20;--%>
        <%--function InitData(pageIndex) {--%>
            <%--var tbody = ""; //声明表格中body部分--%>
            <%--$.ajax({ //这里使用到Jquery的ajax方法，具体使用在这里不详细叙述--%>
                <%--type: "get",--%>
                <%--dataType: "json",--%>
                <%--url: '<%=request.getContextPath()%>/1.0/finance/getList/2/'+pageIndex, //请求的处理数据--%>
<%--//				data: "pageIndex=" + (pageIndex + 1) + "&sortType=" + orderby,--%>
                <%--//传入的参数，第一个参数就是分页的页数，第二个参数为排序的依据--%>
                <%--//下面的操作就是成功返回数据以后，进行数据的绑定--%>
                <%--success: function(data) {--%>
                    <%--$(".blackbor_table tr:gt(0)").remove();--%>
                    <%--var myData = data.messages;--%>
                    <%--totalPage=Math.ceil(data.count/data.pageSize);--%>
                    <%--pageSize=data.pageSize;--%>
                    <%--$.each(myData, function(i, n) {--%>
                        <%--var trs = "";--%>
                        <%--trs += "<tr><td align='center'>" + i + "</td><td align='center'>" + n.type + "</td><td>" + n.name + "</td><td>" + n.contactName + "</td><td>" + n.phoneNumber + "</td><td>" + n.time + "</td><td>" + n.money + "</td><td>" + n.finshTime + "</td>";--%>
                        <%--trs += '<td align="center">'+--%>
                                <%--'<div class="bt_icon bt_icon_b3 r10 pr bd0" style="display:inline-block" onClick="deleteMsg('+ n.id+')"><div class="text c1 pdl0">删除</div></div>'+--%>
                                <%--'<div class="bt_icon bt_icon_b3 r10 pr bd0" style="display:inline-block" onClick="pwdRewrite('+ n.id+')"><div class="text c1 pdl0">密码重置</div></div>'+--%>

                                <%--'</td></tr>'--%>
                        <%--tbody += trs;--%>
                    <%--});--%>
                    <%--$(tbody).appendTo(".blackbor_table");--%>
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
                <%--num_edge_entries: 2--%>
            <%--});--%>
        <%--}--%>
        function queryThis(type,page){
            var param = {
                "param.pageSize":pageSize,
                "param.currentPage":current_page
            };                                               //finance/getList/2/'+pageIndex
            callAPI("<%=request.getContextPath()%>/1.0/finance/getList/"+type+"/"+page,param,queryThis_callback);
        };
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
        function queryThis_callback(data){
            total_page = Math.ceil(data.count/data.pageSize);
            current_page = data.pageNumber;
            pageSize = data.pageSize;
            var tbody = "";
            $(".blackbor_table tr:gt(0)").remove();
            var myData = data.messages;
            $.each(myData, function(i, n) {
                var trs = "";
                trs += "<tr><td align='center'>" + (++i) + "</td><td align='center'>" + n.type + "</td><td>" + n.name + "</td><td>" + n.contactName + "</td><td>" + n.phoneNumber + "</td><td>" + new Date(n.time).Format("yyyy-MM-dd hh:mm:ss") + "</td><td>" + n.money+ "</td><td>" + n.finishTime+ "</td>";
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
        function page_callback(page_index,type, jq){
            current_page = page_index + 1;
            var param = {
                "param.currentPage":current_page,
                "param.pageSize":pageSize};
            callAPI("<%=request.getContextPath()%>/1.0/finance/getList/"+type+"/"+current_page,param,queryThis_callback);
        }
    </script>

</head>
<div class="head">
    <div class="up">
        <div class="logo"></div>
        <div class="user-info">

        </div>
    </div>
</div>
<body>
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
                    <div id="abc" class="bt_icon bt_icon_b3 fr r10 pr bd0" onclick="search()"  ><div class="text c1 pdl0">查询</div></div>
                </td>
            </tr>

        </table>

    </form>
    <table class="blackbor_table"  cellspacing="0" cellpadding="0">
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
