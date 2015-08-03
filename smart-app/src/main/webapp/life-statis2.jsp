<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/bridging.css"/>
<link rel="stylesheet" href="js/plugins/pagination/default-style/css/pagination.css" />
<link rel="stylesheet" href="js/plugins/pagination/default-style/css/pagination.extend.css" />	
<style type="text/css">
    body, html,#allmap {width: 100%;height: 100%;margin:0;font-family:"微软雅黑";}
    #l-map{height:500px;width:100%;margin-top:10px;}
    #r-result{width:100%;}
</style>
<script type="text/javascript" src="js/jquery-1.9.1.min.js" ></script>
<script src="js/plugins/echars/echarts.js" language="javascript" type="text/javascript"></script>
<script type="text/javascript" src="js/plugins/My97DatePicker/WdatePicker.js"></script> 
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=DuHqWhvValvTTon7GG1BOkY0"></script>
<script type="text/javascript" src="js/function.js" ></script>
<script>
require.config({
	paths: {
		echarts: 'js/plugins/echars/'
	}
});
var cyChart, option;
var gwChart, optionA;
var ylChart, optionYl;
var qtChart, optionQt;
require(
	[
		'echarts',
		'echarts/chart/bar'
	],
	function(ec){
		//餐饮统计
        cyChart = ec.init(document.getElementById("cy-statis"));
        option  = {

			color:["#4fd1f1"],
			title : {
				text: '餐饮统计'
			},
			tooltip : {
				show:false,
				trigger: 'axis'
			},
			calculable : false,
			xAxis : [
//				{
//					type : 'category',
//					data : ['2015-7-21','2015-7-22','2015-7-23','2015-7-24','2015-7-25']
//				}
			],
			yAxis : [
				{
					type : 'value'
				}
			],
			series : [
//				{
//					type:'bar',
//					data:[120, 40, 95, 60, 55]
//
//				}
			]
		};
        $.ajax({
            type: "get",//使用get方法访问后台
            dataType: "json",//返回json格式的数据
            url: "<%=request.getContextPath()%>/1.0/cate/getXy/"+$("#totalType").val(),//要访问的后台地址
            success: function(msg){//msg为返回的数据，在这里做数据绑定
                if(msg.code==0){
                    option.xAxis.push(msg.messages[0]);
                    option.series.push(msg.messages[1]);
                    cyChart.setOption(option);
                }else{
                    alert(msg.message)
                }
            }
        });

//
//		//购物统计
        gwChart = ec.init(document.getElementById("gw-statis"));
        optionA= {
			color:["#4fd1f1"],
			title : {
				text: '生活统计'
			},
			tooltip : {
				show:false,
				trigger: 'axis'
			},
			calculable : false,
			xAxis : [
//				{
//					type : 'category',
//					data : ['2015-7-21','2015-7-22','2015-7-23','2015-7-24','2015-7-25']
//				}
			],
			yAxis : [
				{
					type : 'value'
				}
			],
			series : [
//				{
//					type:'bar',
//					data:[120, 40, 95, 60, 55]
//
//				}
			]
		};

        $.ajax({
            type: "get",//使用get方法访问后台
            dataType: "json",//返回json格式的数据
            url: "<%=request.getContextPath()%>/1.0/life/getXy/"+$("#totalType").val()+"?type="+$("#life-a").val(),//要访问的后台地址
            success: function(msg){//msg为返回的数据，在这里做数据绑定
                if(msg.code==0){
                    optionA.xAxis.push(msg.messages[0]);
                    optionA.series.push(msg.messages[1]);

                    gwChart.setOption(optionA);
                }else{
                    alert(msg.message)
                }
            }
        });
//		gwChart.setOption(option);
//
		//娱乐统计
        ylChart= ec.init(document.getElementById("yl-statis"));
		optionYl = {
			color:["#4fd1f1"],
			title : {
				text: '生活统计'
			},
			tooltip : {
				show:false,
				trigger: 'axis'
			},
			calculable : false,
			xAxis : [
//				{
//					type : 'category',
//					data : ['2015-7-21','2015-7-22','2015-7-23','2015-7-24','2015-7-25']
//				}
			],
			yAxis : [
				{
					type : 'value'
				}
			],
			series : [
//				{
//					type:'bar',
//					data:[120, 40, 95, 60, 55]
//
//				}
			]
		};
        $.ajax({
            type: "get",//使用get方法访问后台
            dataType: "json",//返回json格式的数据
            url: "<%=request.getContextPath()%>/1.0/life/getXy/"+$("#totalType").val()+"?type="+$("#life-b").val(),//要访问的后台地址
            success: function(msg){//msg为返回的数据，在这里做数据绑定
                if(msg.code==0){
                    optionYl.xAxis.push(msg.messages[0]);
                    optionYl.series.push(msg.messages[1]);

                    ylChart.setOption(optionYl);
                }else{
                    alert(msg.message)
                }
            }
        });
//
//		ylChart.setOption(option);
//
//		//其他统计
		 qtChart = ec.init(document.getElementById("qt-statis"));
		 optionQt = {
			color:["#4fd1f1"],
			title : {
				text: '生活统计'
			},
			tooltip : {
				show:false,
				trigger: 'axis'
			},
			calculable : false,
			xAxis : [
//				{
//					type : 'category',
//					data : ['2015-7-21','2015-7-22','2015-7-23','2015-7-24','2015-7-25']
//				}
			],
			yAxis : [
				{
					type : 'value'
				}
			],
			series : [
//				{
//					type:'bar',
//					data:[120, 40, 95, 60, 55]
//
//				}
			]
		};
        $.ajax({
            type: "get",//使用get方法访问后台
            dataType: "json",//返回json格式的数据
            url: "<%=request.getContextPath()%>/1.0/life/getXy/"+$("#totalType").val()+"?type="+$("#life-c").val(),//要访问的后台地址
            success: function(msg){//msg为返回的数据，在这里做数据绑定
                if(msg.code==0){
                    optionQt.xAxis.push(msg.messages[0]);
                    optionQt.series.push(msg.messages[1]);

                    qtChart.setOption(optionQt);
                }else{
                    alert(msg.message)
                }
            }
        });
//
//		qtChart.setOption(option);
	}

)
function AChange(obj){
    $.ajax({
        type: "get",//使用get方法访问后台
        dataType: "json",//返回json格式的数据
        url: "<%=request.getContextPath()%>/1.0/cate/getXy/"+$("#totalType").val(),//要访问的后台地址
        success: function(msg){//msg为返回的数据，在这里做数据绑定
            if(msg.code==0){
                option.xAxis = [];
                option.series = [];
                option.xAxis.push(msg.messages[0]);
                option.series.push(msg.messages[1]);

                cyChart.setOption(option);
            }else{
                alert(msg.message)
            }
        }
    });
//    BChange(gwChart, optionA,$("#life-a").val())
//    BChange(ylChart, optionYl,$("#life-b").val());
//    BChange(qtChart, optionQt,$("#life-c").val())
}
function BChange(obj,obj2,abc){
    $.ajax({
        type: "get",//使用get方法访问后台
        dataType: "json",//返回json格式的数据
        url: "<%=request.getContextPath()%>/1.0/life/getXy/"+$("#totalType").val()+"?type="+abc,//要访问的后台地址
        success: function(msg){//msg为返回的数据，在这里做数据绑定
            if(msg.code==0){
                obj.clear();
                obj2.xAxis = [];
                obj2.series = [];
                obj2.xAxis.push(msg.messages[0]);
                obj2.series.push(msg.messages[1]);

                obj.setOption(obj2);
            }else{
                alert(msg.message)
            }
        }
    });
}
$(function(){
	contentWH();
	$(window).resize(contentWH);
	
})

function contentWH(){
	var winH = document.documentElement.clientHeight;
	$(".content").height(winH - $(".head").outerHeight(true));

	//40是iframe的边距。
	//$(".page-pos").width(document.body.clientWidth - $(".menu").outerWidth(true)-40);
	$(".page").width(document.body.clientWidth - $(".menu").outerWidth(true));
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
<div class="content" style="overflow-y:auto;overflow-x:hidden">
	<div class="page">
        <div style="margin:20px">
        	<div style="width:100%;">
            	<div style="display:inline-block">统计类型</div>
            	<select id="totalType" style="display:inline-block" onchange="AChange(this)">
                	<option value="1">订单数量</option>
                    <option value="2">消费额度</option>
                </select>
            </div>
        	<div style="width:100%;">
            	<div style="display:inline-block;width:45%;height:290px;border:1px solid #ccc;position: relative">
                	<%--<div style="float:right;top:10px;right:10px;position:absolute;z-index:99999">--%>
                    	<%--<select style="display:inline-block">--%>
                            <%--<option>生活类别</option>--%>
                        <%--</select>--%>
                    <%--</div>--%>
                	<div id="cy-statis" class="cy-statis" style="width:100%;height:260px"></div>
                </div>
                <div style="display:inline-block;width:5%;height:290px;"></div>
                <div style="display:inline-block;width:45%;height:290px;border:1px solid #ccc;position: relative">
                	<div style="float:right;top:10px;right:10px;position:absolute;z-index:99999">
                    	<select id="life-a" style="display:inline-block" onchange="BChange(gwChart,optionA,$(this).val())">
                            <option value="1">ktv</option>
                            <option value="2">洗浴</option>
                            <option value="3">土特产特卖</option>
                            <option value="4">药店诊所</option>
                            <option value="5">运动健身</option>
                            <option value="6">足疗按摩</option>
                            <option value="7">文化艺术</option>
                            <option value="8">电影游艺</option>
                        </select>
                    </div>
                	<div id="gw-statis" class="gw-statis"  style="width:100%;height:260px"></div>
                </div>
            </div>
            <div style="width:100%;">
            	<div style="display:inline-block;width:45%;height:290px;border:1px solid #ccc;position: relative">
                	<div style="float:right;top:10px;right:10px;position:absolute;z-index:99999">
                    	<select id="life-b" style="display:inline-block" onchange="BChange(ylChart,optionYl,$(this).val())">
                            <option value="1">ktv</option>
                            <option value="2">洗浴</option>
                            <option value="3">土特产特卖</option>
                            <option value="4">药店诊所</option>
                            <option value="5">运动健身</option>
                            <option value="6">足疗按摩</option>
                            <option value="7">文化艺术</option>
                            <option value="8">电影游艺</option>
                        </select>
                    </div>
                	<div id="yl-statis" class="cy-statis" style="width:100%;height:260px"></div>
                </div>
                <div style="display:inline-block;width:5%;height:290px;"></div>
                <div style="display:inline-block;width:45%;height:290px;border:1px solid #ccc;position: relative">
                	<div style="float:right;top:10px;right:10px;position:absolute;z-index:99999">
                    	<select id="life-c" style="display:inline-block" onchange="BChange(qtChart,optionQt,$(this).val())">
                            <option value="1">ktv</option>
                            <option value="2">洗浴</option>
                            <option value="3">土特产特卖</option>
                            <option value="4">药店诊所</option>
                            <option value="5">运动健身</option>
                            <option value="6">足疗按摩</option>
                            <option value="7">文化艺术</option>
                            <option value="8">电影游艺</option>
                        </select>
                    </div>
                	<div id="qt-statis" class="gw-statis"  style="width:100%;height:260px"></div>
                </div>
            </div>
        
        </div>
	</div>
</div>
	</body>
</html>