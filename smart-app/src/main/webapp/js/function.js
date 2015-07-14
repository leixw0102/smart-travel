function px(num){
	if(num<0)num=0;
	return num.toString()+"px";
}

//----------------------创建页面元素
function creatElement(p,n,Id,c){
	var ne=document.createElement(n);
	p.appendChild(ne);
	if(Id!="")ne.id=Id;
	if(c!="")ne.className=c;
	return ne;
}

//判断ie,FF
function ieBrowser(){
	var ie;
    var firefox;
    if (document.all) ie = true; else ie = false;
	return ie;
}
//黑板报列表
$(function(){
	 $(document).delegate(this,"click",function(e){
				var evt = e.target;
				var adpop="<div class='adtitle'>aaaaaaaaa</div>"
				$("body").append(adpop);
				var adpop_text=$(evt).text();
				if($(evt).closest(".adpop").length!=0){
					$(".adtitle").html(adpop_text).css({
						"top":e.pageY+"px",
						"left":e.pageX+"px"
					}).show();
				}
				else{
					$(".adtitle").hide()
				}
	})
	$(".operate").blur(function(){
		$(".add").removeClass("addbg_click").addClass("addbg");
		$(".star").removeClass("starbg_click").addClass("starbg");
		$(".end").removeClass("endbg_click").addClass("endbg");
		$(".modify").removeClass("modifybg_click").addClass("modifybg");
	})
	$(".add").click(function(){
		$(".add").removeClass("addbg").addClass("addbg_click");
		$(".star").removeClass("starbg_click").addClass("starbg");
		$(".end").removeClass("endbg_click").addClass("endbg");
		$(".modify").removeClass("modifybg_click").addClass("modifybg");
	})
	$(".star").click(function(){
		$(".add").removeClass("addbg_click").addClass("addbg");
		$(".star").removeClass("starbg").addClass("starbg_click");
		$(".end").removeClass("endbg_click").addClass("endbg");
		$(".modify").removeClass("modifybg_click").addClass("modifybg");
	})
	$(".end").click(function(){
		$(".add").removeClass("addbg_click").addClass("addbg");
		$(".star").removeClass("starbg_click").addClass("starbg");
		$(".end").removeClass("endbg").addClass("endbg_click");
		$(".modify").removeClass("modifybg_click").addClass("modifybg");
	})
	$(".modify").click(function(){
		$(".add").removeClass("addbg_click").addClass("addbg");
		$(".star").removeClass("starbg_click").addClass("starbg");
		$(".end").removeClass("endbg_click").addClass("endbg");
		$(".modify").removeClass("modifybg").addClass("modifybg_click");
	})

	$(".notice_save div").hover(function(){
		$(this).removeClass("bga").addClass("bgb");
	},function(){
		$(this).removeClass("bgb").addClass("bga");
	})
	$(".contacts_op div").hover(function(){
		$(this).removeClass("bga").addClass("bgb");
	},function(){
		$(this).removeClass("bgb").addClass("bga");
	})
	
})
//check判断
$(function(){
	$(".sum_check").click(function(){
		if($(".sum_check").hasClass("zl_ck_normal")){		
			$(".td_select").removeClass("zl_ck_normal").addClass("zl_ck_down").parents("tr").css("background-color","#daf2f6");		
				
		}
		if($(".sum_check").hasClass("zl_ck_down")){
			$(".td_select").removeClass("zl_ck_down").addClass("zl_ck_normal").parents("tr").css("background-color","#FFFFFF");
		}
	})
	$(".td_select").click(function(){
		if($(this).hasClass("zl_ck_normal")){
			$(this).parents("tr:first").css("background-color","#daf2f6");
		}
		if($(this).hasClass("zl_ck_down")){
			$(this).parents("tr:first").css("background-color","#FFFFFF");
		}
		$(".sum_check").removeClass("zl_ck_down");
		$(".sum_check").addClass("zl_ck_normal");
		
	})
	//联系人上传照片
	$(".click_up").click(function(){
		$(this).val("")
	})
	$(".click_up").blur(function(){
		if(!$(this).val()){
			$(this).val("点击这里上传")
		}
	})
})

/**
*上下工位图添加删除座位
*
**/
function gw_operate(operatePic,fun){
	$("."+operatePic+" .p").hover(function(){
		var obj = $(this);
		if($("div[class^='p_name']",obj).text() == ""){
			var p_nobody_u_size = $("div[class*='p_nobody_u_']",obj).length;
			var p_nobody_d_size = $("div[class*='p_nobody_d_']",obj).length;
			var empty_gw_add;
			if(p_nobody_u_size != 0){
				var empty_gw_add = $("<div class='empty_gw_u'/>").appendTo($("div[class*='p_nobody_u_']",obj));
			}else if(p_nobody_d_size != 0){
				var empty_gw_add = $("<div class='empty_gw_d'/>").appendTo($("div[class*='p_nobody_d_']",obj));
			}
			$(empty_gw_add).bind("click",function(){
				//popup-window
				eval(fun)();
			})
		}else{
			var p_nobody_u_size = $("div[class*='p_nobody_u_']",obj).length;
			var p_nobody_d_size = $("div[class*='p_nobody_d_']",obj).length;
			var empty_gw_del;
			if(p_nobody_u_size != 0){
				var empty_gw_del = $("<div class='del_gw_u'/>").appendTo($("div[class*='p_nobody_u_']",obj));
			}else if(p_nobody_d_size != 0){
				var empty_gw_del = $("<div class='del_gw_d'/>").appendTo($("div[class*='p_nobody_d_']",obj));
			}
			$(empty_gw_del).bind("click",function(){
				//popup-window
			})
		}
	},
	function(){
		var obj = $(this);
		$("div[class^='p_nobody'] div",obj).remove();
	})
}

/**
*左右工位图添加删除座位
*
**/
function gw_operate_lr(operatePic,fun){
	$("."+operatePic+" .b").hover(function(){
		var obj = $(this);
		if($("div[class^='p_name']",obj).text() == ""){
			var p_nobody_u_size = $("div[class*='p_nobody_l_']",obj).length;
			var p_nobody_d_size = $("div[class*='p_nobody_r_']",obj).length;
			var empty_gw_add;
			if(p_nobody_u_size != 0){
				var empty_gw_add = $("<div class='empty_gw_l'/>").appendTo($("div[class*='p_nobody_l_']",obj));
			}else if(p_nobody_d_size != 0){
				var empty_gw_add = $("<div class='empty_gw_r'/>").appendTo($("div[class*='p_nobody_r_']",obj));
			}
			$(empty_gw_add).bind("click",function(){
				//popup-window
				eval(fun)();
			})
		}else{
			var p_nobody_u_size = $("div[class*='p_nobody_l_']",obj).length;
			var p_nobody_d_size = $("div[class*='p_nobody_r_']",obj).length;
			var empty_gw_del;
			if(p_nobody_u_size != 0){
				var empty_gw_del = $("<div class='del_gw_l'/>").appendTo($("div[class*='p_nobody_l_']",obj));
			}else if(p_nobody_d_size != 0){
				var empty_gw_del = $("<div class='del_gw_r'/>").appendTo($("div[class*='p_nobody_r_']",obj));
			}
			$(empty_gw_del).bind("click",function(){
				//popup-window
			})
		}
	},
	function(){
		var obj = $(this);
		$("div[class^='p_nobody'] div",obj).remove();
	})
}