<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 15-8-2
  Time: 下午1:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
        Long id=Long.parseLong(request.getAttribute("userForOrderId").toString());
    %>
    <script type="text/javascript">

        $(function(){
//    	InitData(1);
            InitData(1)
        })
        function page_callback(page_index, jq){
            InitData(page_index);
        }
        var totalPage=0;
        var pageSize=20;
        function InitData(pageIndex) {
            var tbody = ""; //声明表格中body部分
            $.ajax({ //这里使用到Jquery的ajax方法，具体使用在这里不详细叙述
                type: "get",
                dataType: "json",
                url: '<%=request.getContextPath()%>/1.0/order/getUserOrder/<%=id%>/'+pageIndex, //请求的处理数据
//				data: "pageIndex=" + (pageIndex + 1) + "&sortType=" + orderby,
                //传入的参数，第一个参数就是分页的页数，第二个参数为排序的依据
                //下面的操作就是成功返回数据以后，进行数据的绑定
                success: function(data) {
                    $(".blackbor_table tr:gt(0)").remove();
                    var myData = data.messages;
                    totalPage=Math.ceil(data.count/data.pageSize);
                    pageSize=data.pageSize;

//                    $.each(myData, function(i, n) {
//                        var trs = "";
//                        trs += "<tr><td align='center'>" + i + "</td><td align='center'>" + n.contactName + "</td><td>" + n.userName + "</td><td>" + n.pwd + "</td><td>" + n.mark + "</td>";
//                        trs += '<td align="center">'+
//                                '<div class="bt_icon bt_icon_b3 r10 pr bd0" style="display:inline-block" onClick="deleteMsg('+ n.id+')"><div class="text c1 pdl0">删除</div></div>'+
//                                '<div class="bt_icon bt_icon_b3 r10 pr bd0" style="display:inline-block" onClick="pwdRewrite('+ n.id+')"><div class="text c1 pdl0">密码重置</div></div>'+
//                                '</td></tr>'
//                        tbody += trs;
//                    });
//                    $(tbody).appendTo(".blackbor_table");
                }
            });
            //加入分页的绑定
            $("#pagination").pagination(totalPage, {
                callback: page_callback,
                prev_text: '< 上一页',
                next_text: '下一页 >',
                items_per_page: 1,
                num_display_entries: 6,
                current_page: pageIndex-1,
                num_edge_entries: 2
            });
        }
    </script>
</head>
<body>

</body>
</html>