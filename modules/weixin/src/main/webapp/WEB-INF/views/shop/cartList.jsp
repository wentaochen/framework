<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/commons/taglibs.jsp"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0"/>
<meta name="Description" content="网站描述">
<meta name="Keywords" content="关键字">
<meta name="copyright" content="版权声明" />
<c:set var="title" value="购物车" />
<title>${title}</title>
<!--[if lt IE 9]>
	<script src="${ctx}/static/shop/js/css3-mediaqueries.js"></script>
	<script src="${ctx}/static/shop/js/html5.js"></script>
<![endif]-->

<link href="${ctx}/static/shop/css/main.css" rel="stylesheet" type="text/css">
<link href="${ctx}/static/shop/css/font-awesome.min.css" rel="stylesheet" >
<!--[if IE 7]>
  <link rel="stylesheet" href="${ctx}/static/shop/css/font-awesome-ie7.min.css">
<![endif]-->

<script src="${ctx}/static/shop/js/jquery.min.1.7.1.js" type="text/javascript"></script>
<script src="${ctx}/static/shop/js/alpha_bg.js" type="text/javascript"></script>
<script src="${ctx}/static/shop/js/jquery.scrollTo.js" type="text/javascript"></script>
<script src="${ctx}/static/shop/js/jquery.nav.min.js" type="text/javascript"></script>
<script src="${ctx}/static/shop/js/core.js" type="text/javascript"></script>


<script type="text/javascript">
var map = new HashMap();  
var totalPrice=0;
var totalCount=0;  

$(document).ready(function(){
	$('#nav').onePageNav({
		filter: ':not(.external)',
		scrollThreshold: 0.25
	});
});
</script>
 
<script type="text/javascript" src="${ctx}/static/shop/js/jquery.easing.1.3.js"></script>
<script type="text/javascript" src="${ctx}/static/shop/js/sexylightbox.v2.3.jquery.min.js"></script>


  <script type="text/javascript">
    $(document).ready(function(){
      SexyLightbox.initialize({color:'white', dir: '../static/shop/sexyimages'});
    });
  </script>
</head>

<body>
<header>
 <h1 class="toptitle">水果铺</h1>
<%@ include file="common/header.jsp"%>
</header>
 
<div id="search_bar" data-reveal-id="myModal">
    <div class="searchBox">
        <i class="icon-search"></i>
        <input type="search" class="keywords" placeholder="请输入关键字">
    </div>
    <input name="完成" type="button" class="ok_butt" value="确定" >
</div>
<!--公告开始-->
 <div id="notice">
    <i class="icon-volume-down float_left"></i> 
    <span>最新优惠打折啦！买10送一！</span> 
    <div id="ico_close"><i class="icon-remove"></i></div>
 </div>
 <!--公告end-->
<div class="posit_box">
 
 
 <!--左侧tab栏目分类代码开始-->
 <div class="left_tab" id="lanxx">
   <div id="search" data-reveal-id="search_bar"><i class="icon-search"></i></div> 
   <ul class="nav-list" id="nav" >
   	   <c:forEach items="${types}"  var="item" varStatus="s">
   	   			 <li <c:if test="${s.first}"> class="current" </c:if> >
   	   			 <a href="#section-${item.id}">${item.name}
   	   			 <!--<span class="navbar-unread">1</span> -->
   	   			 </a>
   	   			 </li>
   	   </c:forEach>
   </ul>
 </div>
 <!--左侧tab栏目分类代码结束-->
 </div>
 
 
 
 <!--右侧列表代码开始-->
 <div class="right_list" id="wrapper">
 	 <!-- 
 	 <c:set value="1" var="count"/> 
 	 <c:set value="${count+1}" var="count"/>
 	   --> 
     <c:forEach items="${types}"  var="item" varStatus="s">
   	   			 <div class="section" id="section-${item.id}">
				 <h2 class="fltitle">${item.name}</h2>
		         <ul id="thelist">
		         
		         		 <c:forEach items="${item.products}"  var="product" begin="0" varStatus="varStatus">
				   	   			   <li class="goodsitem" data-goodsid="${product.id}">
				   	   			  
						             <a href="${ctx}/static/shop/img/list_img.jpg" rel="sexylightbox[group1]" title="${product.name}">
						              <div class="photo"><img src="${ctx}/static/shop/img/list_img.jpg"></div>
						              <h3>${product.name}</h3>
						              <p>已售20份<span>3人赞过</span></p>
						              <p class="jiaqian">￥${product.price}<span>${product.price*2}</span></p>
						              </a>
						              <div class="tjia">
						                <input type="hidden" class="max" value="30" />
						                
						                <div class="minus hide" onclick="changeNum(${product.id},${product.price},this,'-')"><i class="icon-minus"></i></div>
						                <div class="shuzi hide">0</div>
						                <div id="plus_${product.id}" class="plus" onclick="changeNum(${product.id},${product.price},this,'+')"><i class="icon-plus"></i></div>
						              </div>
						           </li>  
				   	     </c:forEach>
				   	     
		         </ul>
				</div>
   	</c:forEach>
  </div>
 
 <!--统计报价-->
<div id="foot_box" style="display:none;">
 <ul style="display: hidden;" class="bott_list">
  
   </ul>
  
  <div id="totalContent" class="heji_box"></div>

  <div class="foot_bott">
    <a href="javascript:void(0);" onclick="confirmOrder();" class="bott_ok_but">选好了</a>
    <div class="bott_zhankai"><i class="icon-angle-up"></i><i class="icon-angle-down" style="display:none;"></i></div>
  </div>
</div>
<!-- 底部菜单模版文件-->
<ul id="footer-list-template" style="display:none;">
 <li data-goodsid="_id_" class="xzli goodsitem">
      <span>_title_</span>
       <div class="tjia2">
           <p class="jiage">￥_price_</p>
           <input class="max2" value="30" type="hidden">
           <div class="minus2" onclick="changeNum(_id_,_price_,this,'-')"><i class="icon-minus"></i></div>
           <div class="shuzi2"></div>
           <div class="plus2" onclick="changeNum(_id_,_price_,this,'+')"><i class="icon-plus"></i></div>
      </div>
    </li>
</ul>

<script type="text/javascript">
//页脚展开关闭代码
if ( $("#foot_box").css("display")=="none" ){ 
		   $(".right_list").css({ "bottom": "0px"});
		}else{
		   $(".right_list").css({ "bottom": "50px"});
};
$("#ico_close").click(function(){
		$("#notice").css({ "display": "none"});
		$(".posit_box").css({ "top": "53px"});
	    if ( $("#foot_box").css("display")=="none" ){ 
		   $(".right_list").css({ "top": "53px","bottom": "0px"});
		}else{
		   $(".right_list").css({ "top": "53px","bottom": "50px"});
		};
});

$("#search").click(function(){
			$("#foot_box").css({ "display": "none"});
			$(".right_list").css({ "bottom": "0px"});
});
$(".bott_zhankai").click(function(){
	         
		 if($(".bott_list").css("display")=="none"){
				 
				     $(".bott_list").css({ "display": "block"});
					 $(".icon-angle-up").css({ "display": "block"});
				     $(".icon-angle-down").css({ "display": "none"});
					 $(".heji_box").addClass("jiage2")
				}else{//否则就执行下面的语句
					 $(".bott_list").css({ "display": "none"});
					 $(".icon-angle-up").css({ "display": "none"});
					 $(".icon-angle-down").css({ "display": "block"});
                     $(".heji_box").removeClass("jiage2")
					 
				}
		
});
</script>

<script type="text/javascript">
/**
加减运算
**/  
	function addOrderList(id,price,tjia){
		var _parent=$(tjia).parents('.goodsitem');
		var _goodsid = id;
		var _targetGoods=$('.bott_list li[data-goodsid='+_goodsid+']');
		if(_targetGoods.length>0){
			return;
		}else{
			//商品标题
			var title = _parent.find('h3').html();
			$template = $('#footer-list-template').clone().html();
			//添加商品标题
			var $con = $template.replace(/_title_/g,title).replace(/_id_/g,id).replace(/_price_/g,price);
			$('.bott_list').append($con);
		}
	}
	
	function removeOrderList(tjia){
		var _goodsid = $(tjia).parents('.goodsitem').attr('data-goodsid');
		var _targetGoods=$('.bott_list li[data-goodsid='+_goodsid+']');
		if(_targetGoods.length>0){
			_targetGoods.remove();
		}
	}
  	function filterNum(id,price,_parent,num,min,max,handle){
  		if(isNaN(num)){
  			num=0;
  		}
  		$(".minus,.shuzi,.plus",_parent).removeClass("hide");
		if(_parent.is('.tjia')){
			addOrderList(id,price,_parent);
		}
		
		

	  	 $("#foot_box").css({ "display": "block"});
	  	 $(".right_list").css({ "bottom": "50px"});	
  		if(num<=min){//判断值
			
  			$(".minus,.shuzi",_parent).addClass("hide");
			removeOrderList(_parent);
			
		    _parent.parents('.xzli').remove();//删除当前对象的祖父级
			
                  			  
				   if ( $(".bott_list li").length < 1 ){//判断对象是否为空
	   	                   $(".bott_list").css({ "display": "none"});
						   $("#foot_box").css({ "display": "none"});
						   $(".right_list").css({ "bottom": "0px"});						   
	               }
				   
				  if ( $("#foot_box").css("display")=="none" ){//判断对象是否为空
	   	                 
						  if ( $("#notice").css("display")=="none" ){//判断对象是否为空
    							 $(".right_list").css({ "top": "53px","bottom": "0px"});
	                      }else {
 							     $(".right_list").css({ "top": "94px","bottom": "0px"});
	                      }; 
						
	               }else {
					     if ( $("#notice").css("display")=="none" ){//判断对象是否为空
    							 $(".right_list").css({ "top": "53px","bottom": "50px"});
	                      }else {
 							      $(".right_list").css({ "top": "94px","bottom": "50px"});
	                      }; 
			  
                   };//
	        
			 
  			num = min;
  		}
  		if(num>=max){
  			//$(".plus",_parent).addClass("hide");
  			num =max;
  		}
  		handle&&handle.call(this,num);
  	}
  	function changeNum(id,price,self,type){
  	   var self = $(self);
       var _parent = self.parents(".tjia,.tjia2");
       var shuziElem = _parent.find(".shuzi,.shuzi2");
       var max = _parent.find(".max,.max2").val();
       var _shuzi = parseInt(shuziElem.html());

      if(type=="+"){
        _shuzi++;
        //把产品添加至购物车
        if(isCommit==true){
        	addCartItem(id);
        }
      }else{
         _shuzi--;
         //把产品从购物车中删除
         deleteCartItem(id);
      }
      
  
      //计算总架构和总菜数
      changeData(id,_shuzi,price);
     
      filterNum(id,price,_parent,_shuzi,0,max,function(num){
        var _goodsid = _parent.parents('.goodsitem').attr('data-goodsid');
		var _targetElem = $('li[data-goodsid='+_goodsid+']');
		_targetElem.find(".shuzi,.shuzi2").html(num);
		if(num<=0){
		$('.tjia',_targetElem).find('.minus,.shuzi').addClass('hide');
		}
      });
  	}
  	
  	
  	//业务逻辑计算处理
  	function changeData(id,number,price){
  		  var item=map.get(id);
  		  //如果为空
  		  if(item==null){
  			  item=new Item(id,price,number);
  			  map.put(id,item);
  		  }else{
  		  	  //如果不为空
  		 	  item.number=number;
  		  }
  		  
  		   updateTotal(map);
  	}
  	
  	function updateTotal(map){
  		var valueArray=map.values();
  		var tPrice=0;
  		var tNumber=0;
  		for(var i=0;i<=valueArray.length-1;i++){
  			var item=valueArray[i];
  			tPrice=tPrice+item.price*item.number;
  			tNumber=tNumber+item.number;
  		}
  		
  		$("#totalContent").html("总计：￥"+tPrice+"/"+tNumber+"个菜");
  	}
  	
  	
  	function confirmOrder(){
  		<%-- 判断是否登录 --%>
  		<c:if test="${empty sessionScope.member}">
  			   var isLogin=false;
  		</c:if>
  			  
  		<c:if test="${not empty sessionScope.member}">
  			   var isLogin=true;
  		</c:if>
  		
  		if(isLogin==false){
  			alert("尊敬的客户,请在登录以后继续完成剩余步骤!");
  			window.location.href="${ctx}/login";
  			return;
  		}
  		var valueArray=map.values();
  		var idAndNumbers="";
  		for(var i=0;i<=valueArray.length-1;i++){
  			var item=valueArray[i];
  			idAndNumbers=idAndNumbers+item.id+":"+item.number+";";
  		}
  		
		$.ajax({
            cache: true,
            type: "POST",
            url:"${ctx}/cart/add",
            data:"ids="+idAndNumbers,
            async: false,
            error: function(request) {
                alert("提交失败请刷新后在提交");
            },
            success: function(data) {
                 if("ok"==data){
                	 window.location.href = "${ctx}/order/toconfirm";
                 }
            }
        });
  	}
  	
  	//添加购物车
  	function addCartItem(id) {
		var url = "${ctx}/cart/add/" + id;
		$.ajax({
			url : url,
			type : 'GET',
			dataType : "html",
			cache : false,
			success : function(html) {
				if (html == "ok") {
					//$("#deleteSuccess").fadeIn("slow");
				}
			}
		});
	}
  	
	
  	//产品从购物车中删除
  	function deleteCartItem(id) {
		var url = "${ctx}/cart/delete/" + id;
		$.ajax({
			url : url,
			type : 'GET',
			dataType : "html",
			cache : false,
			success : function(html) {
				if (html == "ok") {
					//$("#deleteSuccess").fadeIn("slow");
				}
			}
		});
		
	}
  	
  	//触发事件:首次进入页面确保不要再重复提交;
  	var isCommit=false;
  	<c:forEach items="${sessionScope.cart.products}" var="entry">
	  	for (var i = 0; i < ${entry.value.count}; i++) {
	  		$('#plus_${entry.key}').trigger("click");
		}
	</c:forEach>				
	var isCommit=true;
	
  	
  </script>
</html>
