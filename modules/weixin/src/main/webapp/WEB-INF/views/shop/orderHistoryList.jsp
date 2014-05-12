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
    <script src="${ctx}/static/shop/js/css3-mediaqueries.js"></script>
    <script src="${ctx}/static/shop/js/html5.js"></script>
<![endif]-->

<link href="${ctx}/static/shop/css/main.css" rel="stylesheet" type="text/css">
<link href="${ctx}/static/shop/css/font-awesome.min.css" rel="stylesheet" >
<!--[if IE 7]>
  <link rel="stylesheet" href="${ctx}/static/shop/css/font-awesome-ie7.min.css">
<![endif]-->
<script src="${ctx}/static/shop/js/jquery.min.1.8.3.js" type="text/javascript"></script>
<script src="${ctx}/static/shop/js/iscroll.js"></script>
</head>

<body>
 <header>
   <!-- <a href="javascript:void(0);" onclick="history.go(-1)"> -->
  <h1 class="toptitle"><a href="${ctx}/member/"><i class="icon-angle-left"></i>我的订单</a></h1>
<%@ include file="common/header.jsp"%>
 </header>
 
<div class="stieBox" id="wrapper">
  <div id="gundongbox">
     
      <ul class="order-list">
		<c:if test="${empty orderList}">
			 <div class="dd-date">目前还没订单哦!</div>
		</c:if>       
       
       <c:forEach items="${orderList}"  var="item" varStatus="s">
	   	   	<li>
	   	   	  
	           <div class="dd-date">点餐时间 <fmt:formatDate value="${item.createTime}"  pattern="yyyy-MM-dd HH:mm:ss"/></div>
	           <!-- <p class="dj-name">Jet的水果铺</p> -->
	           <a href="${ctx}/order/${item.id}">
		           <p class="dc-name">
		           <c:forEach items="${item.orderItem}"  var="item1" varStatus="s1">
		           		${item1.product.name}/${item1.count}${item1.product.unit} &nbsp;
		           </c:forEach>
		           </p>
	           </a>       
	           <div class="dj-meny"><span class="color-huise">${item.orderState.label}</span>金额：<span class="color-cs">￥${item.totalPrice}</span></div>
	        </li>
   	   </c:forEach>  
   	   
   	         
      </ul>
 
  </div>  
</div>
</body>
<script type="text/javascript">
//滚动区的JS
var myScroll;
function loaded() {
	myScroll = new iScroll("wrapper");
}

document.addEventListener("touchmove", function (e) { e.preventDefault(); }, false);

/* * * * * * * *
 *
 * Use this for high compatibility (iDevice + Android)
 *
 */
document.addEventListener("DOMContentLoaded", function () { setTimeout(loaded, 200); }, false);
/*
 * * 滚动区的JS* * */
</script>
</html>

<%-- 
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
    <script src="${ctx}/static/shop/js/css3-mediaqueries.js"></script>
    <script src="${ctx}/static/shop/js/html5.js"></script>
<![endif]-->

<link href="${ctx}/static/shop/css/main.css" rel="stylesheet" type="text/css">
<link href="${ctx}/static/shop/css/font-awesome.min.css" rel="stylesheet" >
<!--[if IE 7]>
  <link rel="stylesheet" href="${ctx}/static/shop/css/font-awesome-ie7.min.css">
<![endif]-->
<script src="${ctx}/static/shop/js/jquery.min.1.8.3.js" type="text/javascript"></script>
<script src="${ctx}/static/shop/js/iscroll.js"></script>
</head>

<body>
<%@ include file="common/header.jsp" %>
 
<div class="stieBox" id="wrapper">
  <div id="gundongbox">
     
      
      <ul class="review-list"> 
     
      <li>
         <div class="pjlist">
            <p class="f_left hui_999">Jet.xue</p>
            <p class="f_right hui_999">2014-04-03</p>
            <p class="fshu xingxing">
               <strong class="st-fs-3"></strong>
            </p>
            <p class="zhenghang">
              外送速度很快，32个赞
            </p> 
          </div>
       </li>
       
       <li>
         <div class="pjlist">
            <p class="f_left hui_999">Jet.xue</p>
            <p class="f_right hui_999">2014-04-03</p>
            <p class="fshu xingxing">
               <strong class="st-fs-3"></strong>
            </p>
            <p class="zhenghang">
              外送速度很快，32个赞
            </p> 
          </div>
       </li>
       
       <li>
         <div class="pjlist">
            <p class="f_left hui_999">Jet.xue</p>
            <p class="f_right hui_999">2014-04-03</p>
            <p class="fshu xingxing">
               <strong class="st-fs-3"></strong>
            </p>
            <p class="zhenghang">
              外送速度很快，32个赞
            </p> 
          </div>
       </li>
       
       <li>
         <div class="pjlist">
            <p class="f_left hui_999">Jet.xue</p>
            <p class="f_right hui_999">2014-04-03</p>
            <p class="fshu xingxing">
               <strong class="st-fs-3"></strong>
            </p>
            <p class="zhenghang">
              外送速度很快，32个赞
            </p> 
          </div>
       </li>
       
       <li>
         <div class="pjlist">
            <p class="f_left hui_999">Jet.xue</p>
            <p class="f_right hui_999">2014-04-03</p>
            <p class="fshu xingxing">
               <strong class="st-fs-3"></strong>
            </p>
            <p class="zhenghang">
              外送速度很快，32个赞
            </p> 
          </div>
       </li>
       
       <li>
         <div class="pjlist">
            <p class="f_left hui_999">Jet.xue</p>
            <p class="f_right hui_999">2014-04-03</p>
            <p class="fshu xingxing">
               <strong class="st-fs-3"></strong>
            </p>
            <p class="zhenghang">
              外送速度很快，32个赞
            </p> 
          </div>
       </li>
       
       <li>
         <div class="pjlist">
            <p class="f_left hui_999">Jet.xue</p>
            <p class="f_right hui_999">2014-04-03</p>
            <p class="fshu xingxing">
               <strong class="st-fs-3"></strong>
            </p>
            <p class="zhenghang">
              外送速度很快，32个赞
            </p> 
          </div>
       </li>
      
     </ul>
     
       
      
     
   </div>  
</div>
</body>
<script type="text/javascript">
//滚动区的JS
var myScroll;
function loaded() {
	myScroll = new iScroll("wrapper");
}

document.addEventListener("touchmove", function (e) { e.preventDefault(); }, false);

/* * * * * * * *
 *
 * Use this for high compatibility (iDevice + Android)
 *
 */
document.addEventListener("DOMContentLoaded", function () { setTimeout(loaded, 200); }, false);
/*
 * * 滚动区的JS* * */

</script>
</html>
--%>