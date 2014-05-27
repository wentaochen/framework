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
<title>外卖</title>
<!--[if lt IE 9]>
    <script src="{ctx}/static/shop/js/css3-mediaqueries.js"></script>
    <script src="${ctx}/static/shop/js/html5.js"></script>
<![endif]-->
<link href="${ctx}/static/shop/css/main.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="${ctx}/static/shop/css/font-awesome.min.css">
<!--[if IE 7]>
  <link rel="stylesheet" href="${ctx}/static/shop/css/font-awesome-ie7.min.css">
<![endif]-->
<script src="${ctx}/static/shop/js/jquery.min.1.8.3.js" type="text/javascript"></script>
<script src="${ctx}/static/shop/js/iscroll.js"></script>
<script src="${ctx}/static/shop/js/core.js" type="text/javascript"></script>
</head>

<body>
 <header>
    <h1 class="toptitle">
     <!-- 
     <a  href="javascript:void(0);" onclick="history.go(-1)"><i class="icon-angle-left">
     </i>
         确认订单</a>
       -->
          确认订单
     
     </h1>
  <%@ include file="common/header.jsp"%>
 </header>

<!--地址-->
<div class="top_add">
   <div class="add_tb"><i class="icon-map-marker"></i></div>  
   <div class="add_text">
     <p class="addname">${sessionScope.member.name}<span class="tel">${sessionScope.member.mobile}</span></p>
     <p class="addwenzi">${sessionScope.member.address}</p>
   </div>    
</div> 
<div class="affireBox" id="wrapper">
   <div class="xzdcp"> 
        <div class="jdxxbox">
           <div class="butt_right">
              <div class="fkbut" >
                 货到付款
                 <div class="xzhong" style="display:block;"></div> 
              </div>
            </div>支付方式：
        </div> 
        <div class="jdxxbox">
         <input name="" type="text" placeholder="若有特殊要求请留言" class="ddliuyan">
        </div> 

        <ul id="thelist">
              <c:forEach items="${sessionScope.cart.products}" var="entry">
                    <li>
		              <div class="photo"><img src="${ctx}/static/shop/img/list_img.jpg"></div>
		              	<h3>${entry.value.product.name}</h3>
		              <p class="jiaqian">￥${entry.value.product.price}</p>
		              <div class="tjia2">
		          
		                 <input type="hidden" class="max2" value="30" />
		                 <!--  
		                 <div class="minus2"><i class="icon-minus"></i></div>
		                 -->
		                 <div class="shuzi2">${entry.value.count}</div>
		                  <!-- 
		                 <div class="plus2"><i class="icon-plus"></i></div>
		                  -->
		             </div>
		            </li>  
              </c:forEach>
		</ul>
   </div>

</div>
<div id="foot_box">  
  <div class="heji_box">总计：￥${sessionScope.cart.totalPrice}/${sessionScope.cart.totalNumber}个菜</div>

  <div class="foot_bott">
    <a href="${ctx}/order/confirm" class="bott_ok_but">立即下单</a>
  
  </div>
</div>
</body>
<script>
//
$(".fkbut").click(function(){
		
	    if ( $(".xzhong").css("display")=="none" ){ 
		    $(".xzhong").css({ "display": "block"});
			$(".fkbut").addClass("xzbk")
		}else{
		    $(".xzhong").css({ "display": "none"});
			$(".fkbut").removeClass("xzbk")
		};
});
$(".ddliuyan").focus(function(){
	$("#foot_box").css({ "display": "none","position":"inherit"});
	$(".affireBox").css({ "bottom": "0px"});
});
$(".ddliuyan").blur(function(){
    $("#foot_box").css({ "display": "block","position":"absolute"});
	$(".affireBox").css({ "bottom": "70px"});
  });
</script>
<script type="text/javascript">
/**
加减运算
**/  
  	$(".plus2").click(function(){
        changeNum(this,"+");
    })
  	$(".minus2").click(function(){
        changeNum(this,"-");
    })
  	function filterNum(_parent,num,min,max,handle){
  		if(isNaN(num)){
  			num=0;
  		}
  		
  		if(num<=min){//判断值
		           $(".minus2",_parent).parent().parent().remove();//删除当前对象的祖父级
                  			  
				   if ( $("#thelist li").length < 1 ){//判断对象是否为空
	   	                   
						   $("#foot_box").css({ "display": "none"});
						 
	               }else {
                           
						   $("#foot_box").css({ "display": "block"});
						 	
                   };//判断选中外卖总合的一些条件逻辑
				   
				  if ( $("#foot_box").css("display")=="none" ){//判断对象是否为空
	   	 	  
    				   $(".affireBox").css({ "bottom": "0px"});

	               }else {
					   
 					   $(".affireBox").css({ "bottom": "63px"});

                   };//
			 
  			num = min;
  		}
  		if(num>=max){
  			//$(".plus",_parent).addClass("hide");
  			num =max;
  		}
  		handle&&handle.call(this,num);
  	}
  	function changeNum(self,type){
       var self = $(self);
       var _parent = self.parents(".tjia2");
       var shuziElem = _parent.find(".shuzi2");
       var max = _parent.find(".max2").val();
       var _shuzi = parseInt(shuziElem.html());
      console.log(type);
      if(type=="+"){
        _shuzi++;
      }else{
         _shuzi--;
      }
     
      filterNum(_parent,_shuzi,0,max,function(num){
        shuziElem.html(num);
      });
  	}
  </script>
</html>
