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
require(
	[
		'echarts',
		'echarts/chart/bar',
		'echarts/chart/line'
	],
	function(ec){
		//前一周消费
		var cyChart = ec.init(document.getElementById("cy-statis"));
		var option = {
			color:["#4fd1f1"],
			title : {
				text: '前一周消费'
			},
			tooltip : {
				show:false,
				trigger: 'axis'
			},
			calculable : false,
			xAxis : [
				{
					type : 'category',
					data : ['2015-7-21','2015-7-22','2015-7-23','2015-7-24','2015-7-25']
				}
			],
			yAxis : [
				{
					type : 'value'
				}
			],
			series : [
				{
					type:'bar',
					data:[120, 40, 95, 60, 55]
					
				}
			]
		};
                    
		cyChart.setOption(option);
		
		//后一周预定
		var gwChart = ec.init(document.getElementById("gw-statis"));
		var option = {
				color:["#4fd1f1"],
				title : {
					text: '后一周预定'
				},
				tooltip : {
					show:false,
					trigger: 'axis'
				},
				calculable : false,
				xAxis : [
					{
						type : 'category',
						boundaryGap : false,
						data : ['2015-7-21','2015-7-22','2015-7-23','2015-7-24','2015-7-25']
					}
				],
				yAxis : [
					{
						type : 'value'
					}
				],
				series : [
					{
						type:'line',
						data:[500, 1500, 1700, 1900, 2100]
						
					}
				]
			};
                    
                    
		gwChart.setOption(option);
	}

)

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
        	<div style="width:100%;border:1px solid #ccc;">
            	<div style="display:inline-block;width:45%;height:290px;position: relative">
                	<div id="cy-statis" class="cy-statis" style="width:100%;height:260px"></div>
                </div>
                <div style="display:inline-block;width:5%;height:290px;"></div>
                <div style="display:inline-block;width:45%;height:290px;position: relative">
                	<div style="float:right;top:10px;right:10px;position:absolute;z-index:99999">
                    	<div style="display:inline-block">统计类型</div>
                        <select style="display:inline-block">
                            <option>订单数量</option>
                            <option>消费额度</option>
                        </select>
                    </div>
                	<div id="gw-statis" class="gw-statis"  style="width:100%;height:260px"></div>
                </div>
            </div>
            
            <div style="width:100%;border:1px solid #ccc;margin-top:20px;">
            	<div style="font-size:16px;font-weight:bold;margin-top:10px;margin-left:10px">景点状态</div>
            	<div id="l-map"></div>
            
           	</div>
        </div>
        
	</div>
</div>
	</body>
</html>
<script type="text/javascript">
	// 百度地图API功能
	var map = new BMap.Map("l-map");
	var point = new BMap.Point(100.944716,37.973845);
  	var point1 = new BMap.Point(100.259491,38.183462);
	map.centerAndZoom(point, 10)
	var marker = new BMap.Marker(point);// 创建标注
	map.addOverlay(marker);             // 将标注添加到地图中
	marker.disableDragging();           // 不可拖拽
	var label = new BMap.Label("50人",{offset:new BMap.Size(20,-10)});
	marker.setLabel(label);
  	var marker1 = new BMap.Marker(point1);// 创建标注
  	map.addOverlay(marker1);
	var label1 = new BMap.Label("57人",{offset:new BMap.Size(20,-10)});
	marker1.setLabel(label1);
</script>
