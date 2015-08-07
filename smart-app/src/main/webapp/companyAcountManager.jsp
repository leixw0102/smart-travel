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
                "name":$("#shopNameId").val()
            };
            callAPI("<%=request.getContextPath()%>/1.0/seller/query/"+page,param,queryThis_callback);
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
                trs += "<tr><td align='center'>" + (++i) + "</td><td class='list-link' style='cursor:pointer' align='center' onclick='editUserInfo("+ n.id+")'>" + n.userName + "</td><td>" + n.pwd + "</td><td>" +n.type + "</td><td>" +n.sellerName + "</td><td>" +new Date(getfinish(n.createTime)).Format("yyyy-MM-dd hh:mm:ss") + "</td><td>" + n.remark + "</td>" +
                        '<td align="center">'+
                        '<div class="bt_icon bt_icon_b3 r10 pr bd0" style="display:inline-block" onClick="editCompanyInfo('+n.id+','+n.t+')"><div class="text c1 pdl0">修改商户信息</div></div>'+
                        '<div class="bt_icon bt_icon_b3 r10 pr bd0" style="display:inline-block" onClick="seeCode('+n.id+','+n.t+')"><div class="text c1 pdl0">查看二维码</div></div>'+
                        '</td>'
                "</tr>";
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
                    "name":$("#shopNameId").val(),
                };
                callAPI("<%=request.getContextPath()%>/1.0/seller/query/"+current_page,param,queryThis_callback);
            }
        }
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
      var editCompany_win
      function editUserInfo(id){

          editSellerUser_win=window.top.$.popWin({
      		title:"编辑用户信息",
      		width:610,
      		height:530,
      		center:true,
      		url:"<%=request.getContextPath()%>/1.0/seller/editSeller/"+id
      	});
      }
      function seeCode(id,type){
          window.location.href="<%=request.getContextPath()%>/1.0/seller/code/"+id+"/"+type;
      }
      function editCompanyInfo(id,type){
          editCompany_win=window.top.$.popWin({
      		title:"编辑商户信息",
      		width:610,
      		height:330,
      		center:true,
      		url:"<%=request.getContextPath()%>/1.0/seller/editCompanyPage/"+id+"/"+type
              <%--url: "<%=request.getContextPath()%>/editCompanyInfo.jsp"--%>
      	});

      }
    </script>
</head>
<body>
<!--head-->

<div class="body_main">
    <div class="list-item-c1 h40 lh40 ti20 fwb bl1 br1 bt1">
        <span class="titleSpan fl">商户信息</span>
        <div class="bt_icon bt_icon_b2 fr pd10 r10 pr t8" onClick="popCompanyInfo()"><div class="text clfff pdl0">增加账户</div></div>
    </div>
    <table class="blackbor_table bt0 bb0"  cellspacing="0" cellpadding="0">
        <tr>
            <td>
                <div class="fieldsContainer fl">
                    <ul class="fields search pdt0 pdb0 bg2" style="width:100% ">
                        <li class="pr9 pb0">
                            <ul>
                                <li class="text w60 fb c1 pb0">用户名：</li>
                                <li class="value pb0">
                                    <input id="shopNameId" type="text" class="w300 h27 inputStyle"/>
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
       						<td width="5%">序号</td>
       						<td width="10%">用户名(手机号)</td>
       						<td width="5%">密码</td>
       						<td width="5%">商户类型</td>
       						<td width="20%" >商户名称</td>
                               <%--<td width="10%" >联系人</td>--%>
                               <%--<td width="5%" >服务费比例</td>--%>
                               <td width="10%" >创建日期</td>
       						<td width="10%" class="borderright">备注</td>
       						<td width="20%" class="borderright">操作</td>
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
