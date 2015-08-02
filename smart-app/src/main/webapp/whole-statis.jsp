<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
    <title>Insert title here</title>
    <link rel="stylesheet" type="text/css" href="css/bridging.css"/>
    <style type="text/css">
        body, html,#allmap {width: 100%;height: 100%;margin:0;font-family:"微软雅黑";overflow-x:hidden}
        #l-map{height:500px;width:100%;}
        #r-result{width:100%;}
    </style>
    <script type="text/javascript" src="js/jquery-1.9.1.min.js" ></script>
    <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=DuHqWhvValvTTon7GG1BOkY0"></script>
    <script type="text/javascript" src="js/function.js" ></script>
</head>

<body>
<!--head-->
<div class="head" style="border:2px solid #000">
    <div class="up">
        <div class="logo"></div>
        <div class="user-info">
            <div class="refresh"></div>
        </div>
    </div>
</div>
<!--content-->
<div class="content" style="width:100%;">
    <div class="date" style="height:177px;background:#1c1c1c;color:#fff">
        <div class="date-l" style="width:69%;height:100%;float:left;">
            <div style="float:left;margin-left:20px;margin-top:10px;"><span id="current_day"></span><span>成交额</span></div>
            <%--<div style="float:right;position:relativve;margin-right:20px;margin-top:10px;">09:55</div>--%>
            <div style="font-size:112px;margin-top:90px;margin-left:60px;color:#fbb03b"><span>￥</span><span id="current_value"></span></div>

        </div>
        <div style="background:#000;width:2px;float: left; height: 100%"></div>
        <div class="date-r" style="width:30%;height:100%;float:left">
            <div style="margin-top:10px;margin-left:10px">上一月成交额</div>
            <div  style="font-size:30px;margin-top:10px;margin-left:10px;color:#fbb03b"><span>￥</span><span id="last_value"></span></div>
            <div style="margin-top:20px;margin-left:10px">成交总笔数</div>
            <div  style="font-size:30px;margin-top:10px;margin-left:10px;color:#fbb03b"><span>￥</span><span id="total_value"></span></div>

        </div>

    </div>

    <div class="map-wrapper" style="position:relative">
        <div style="position:absolute;z-index:999999;float:right;right:20px;top:20px;width:150px;height:100px;background:#1c1c1c;filter:alpha(opacity=50);opacity:0.5;color:#fff">
            <div style="margin-top:10px;margin-left:10px">
                <div class="user-statis-icon" style="display:inline-block"></div>
                <div style="display:inline-block" onclick="mapX(2)">景点分布</div>
            </div>
            <%--<div style="margin-top:10px;margin-left:10px">--%>
            <%--<div class="scence-statis-icon" style="display:inline-block"></div>--%>
            <%--<div style="display:inline-block">景点预警</div>--%>
            <%--</div>--%>
            <div style="margin-top:10px;margin-left:10px">
                <div class="life-statis-icon" style="display:inline-block"></div>
                <div style="display:inline-block" onclick="mapX(1)">酒店分布</div>
            </div>
        </div>
        <div id="l-map"></div>

    </div>
</div>
</body>
</html>
<script type="text/javascript">
    $(function(){
//    	InitData(1);
        test();
        mapX(1);
    })
    function test(){
        $.ajax({
            type: "get",//使用get方法访问后台
            dataType: "json",//返回json格式的数据
            url: "<%=request.getContextPath()%>/1.0/getTotal",//要访问的后台地址
            success: function(msg){//msg为返回的数据，在这里做数据绑定
                if(msg.code==0){
                    var obj=msg.info;
                    $("#current_day").text(obj.current_day);
                    $("#current_value").text(obj.current_value);
                    $("#last_value").text(obj.last_value);
                    $("#total_value").text(obj.total_value);
                }else{
                    alert(msg.message)
                }
            }
        });

    }
    // 百度地图API功能
    var map = new BMap.Map("l-map");

    /*var marker = new BMap.Marker(point);// 创建标注
     map.addOverlay(marker);             // 将标注添加到地图中
     marker.disableDragging();           // 不可拖拽
     var label = new BMap.Label("50人",{offset:new BMap.Size(20,-10)});
     marker.setLabel(label);
     var marker1 = new BMap.Marker(point1);// 创建标注
     map.addOverlay(marker1);
     var label1 = new BMap.Label("57人",{offset:new BMap.Size(20,-10)});
     marker1.setLabel(label1);*/


    function mapX(type){

        $.ajax({
            type: "get",//使用get方法访问后台
            dataType: "json",//返回json格式的数据
            url: "<%=request.getContextPath()%>/1.0/getMapData/"+type,//要访问的后台地址
            success: function(msg){//msg为返回的数据，在这里做数据绑定
                $.each(msg.messages, function(i, n) {
                    var point = new BMap.Point(n.x,n.y);
                    map.centerAndZoom(point, 10);
                    var marker = new BMap.Marker(point);// 创建标注
                    map.addOverlay(marker);             // 将标注添加到地图中
                    marker.disableDragging();           // 不可拖拽
                    var label = new BMap.Label(n.info,{offset:new BMap.Size(20,-10)});
                    marker.setLabel(label);
                })
            }
        });
    }
</script>
