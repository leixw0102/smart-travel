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

    function deleteMsg(id){

        $.ajax({

            type: "Post",

            url: "<%=request.getContextPath()%>/1.0/user/financeDeleteById/"+id,

//            data: $('#userLogin').serialize(),

            success: function(result){
                if (result.code==0){
                    alert("删除成功")
                    InitData(1);
                }else{
                    alert(result.message)
                }

            }

        });
    }
        var totalPage=0;
        var pageSize=20;
		function InitData(pageIndex) { 
			var tbody = ""; //声明表格中body部分 
			$.ajax({ //这里使用到Jquery的ajax方法，具体使用在这里不详细叙述 
				type: "get", 
				dataType: "json", 
				url: '<%=request.getContextPath()%>/1.0/user/cashUsers/'+pageIndex, //请求的处理数据
//				data: "pageIndex=" + (pageIndex + 1) + "&sortType=" + orderby,
				//传入的参数，第一个参数就是分页的页数，第二个参数为排序的依据 
				//下面的操作就是成功返回数据以后，进行数据的绑定 
				success: function(data) { 
					$(".blackbor_table tr:gt(0)").remove(); 
					var myData = data.messages;
                    totalPage= data.count/data.pageSize==0? data.count/data.pageSize:(data.count/data.pageSize)+1
                    pageSize=data.pageSize;
					$.each(myData, function(i, n) { 
						var trs = ""; 
						trs += "<tr><td align='center'>" + i + "</td><td align='center'>" + n.contactName + "</td><td>" + n.userName + "</td><td>" + n.pwd + "</td><td>" + n.mark + "</td>";
						trs += '<td align="center">'+
			                       	'<div class="bt_icon bt_icon_b3 r10 pr bd0" style="display:inline-block" onClick="deleteMsg('+ n.id+')"><div class="text c1 pdl0">删除</div></div>'+
			                        '<div class="bt_icon bt_icon_b3 r10 pr bd0" style="display:inline-block" onClick="pwdRewrite('+ n.id+')"><div class="text c1 pdl0">密码重置</div></div>'+
			                    '</td></tr>'
						tbody += trs; 
					}); 
					$(tbody).appendTo(".blackbor_table"); 
				} 
			}); 
			//加入分页的绑定 
			$("#pagination").pagination(totalPage, {
				callback: page_callback, 
				prev_text: '< 上一页', 
				next_text: '下一页 >', 
				items_per_page: pageSize,
				num_display_entries: 6, 
				current_page: pageIndex, 
				num_edge_entries: 2 
			}); 
		} 	
    </script>
    <script type="text/javascript">
        var pwdRewrite_win;
        var addFinance_win;
    function popCompanyInfo(){
        addFinance_win=window.top.$.popWin({
			title:"添加财务用户",
			width:610,
			height:350,
			center:true,
			url:"<%=request.getContextPath()%>/finaceAcountManagerAdd.jsp"
		});
	}

    function pwdRewrite(id){
        pwdRewrite_win = window.top.$.popWin({
			title:"密码重置",
			width:400,
			height:240,
			center:true,
			url:"<%=request.getContextPath()%>/1.0/user/getUpdatePwdPage/"+id
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
       <!--content-->
	<div class="body_main">
           	<div class="list-item-c1 h40 lh40 ti20 fwb bl1 br1 bt1">
				<span class="titleSpan fl">财务账号管理</span>
				<div class="bt_icon bt_icon_b2 fr pd10 r10 pr t8" onClick="popCompanyInfo()"><div class="text clfff pdl0">添加用户</div></div>
               </div>
               
			<table class="blackbor_table"  cellspacing="0" cellpadding="0">
				<tr class="trup">
					<td width="5%">序号</td>
                       <td width="15%" >联系人</td>
                       <td width="20%" >登录账号</td>
                       <td width="10%" >密码</td>
                       <td width="20%" class="borderright">备注</td>
                       <td width="30%" >操作</td>
				</tr>
			</table>
			<div class="detail_bottom">
				<div>
					<div id="pagination" class="paginations_style" ></div>
				</div>
			</div>
	</div>
</body>
</html>
