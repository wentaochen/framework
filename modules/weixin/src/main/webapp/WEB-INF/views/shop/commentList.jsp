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
    <h1 class="toptitle"><a href="javascript:void(0);" onclick="history.go(-1)"> <i class="icon-angle-left"></i>用户留言</h1>
    <%@ include file="common/header.jsp"%>
</header>
 
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
