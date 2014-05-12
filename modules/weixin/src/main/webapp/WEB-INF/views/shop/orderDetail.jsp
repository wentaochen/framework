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
</head>

<body>
 <header>
    <h1 class="toptitle">
       	订单详细
     </h1>
  <%@ include file="common/header.jsp"%>
 </header>
<div class="ddxqBox" id="wrapper">
  <div id="gundongbox">
     <!--订单-->
     <div class="shuombox">订单状态：${order.orderState.label}</div>
     <div class="baiBox">
         <p class="dcsj">点餐时间<fmt:formatDate value="${order.createTime}"  pattern="yyyy-MM-dd HH:mm:ss"/></p>
         <ul class="ydcdlist">
	         <c:forEach items="${order.orderItem}" var="item">
	         		  <li>
			             <p class="fl-name">${item.product.name}</p>
			             <p class="ri-fen">${item.count}份x<span>￥${item.product.price}</span></p>
			          </li>
	         
	         </c:forEach>
         </ul>
         <p class="zongedu">总计：￥${order.totalPrice}/${order.totalNumber}个菜</p>
      </div>
      
      <div class="baiBox">
        <p class="xqlboo"><i class="icon-home"></i><span>我们的水果铺</span></p>
        <p class="xqlboo"><i class="icon-phone"></i><span>外卖电话：87872800</span></p>
        <div class="ddrxx">
           <p>收货人：${sessionScope.member.name}</p>
           <p>电话：${sessionScope.member.mobile}</p>
           <p>地址：${sessionScope.member.address}</p>
           <p>送货时间：2014-04-06</p>
           <p>付款当时：货到付款</p>
           <p>订单编号：2010310024689</p>
        </div>
      </div>
   </div>  
</div>

<div id="foot_box">  
  <div class="foot_bott">
  	  <!--  
      <a href="javascript:void(0);" class="bott_ok_but">取消订单</a>
      -->
  </div>
</div>
</body>
<script type="text/javascript">

//滚动区的JS
var myScroll;
function loaded() {
	myScroll = new iScroll('wrapper');
}
document.addEventListener('touchmove', function (e) { e.preventDefault(); }, false);
document.addEventListener('DOMContentLoaded', function () { setTimeout(loaded, 200); }, false);
</script>
</html>
