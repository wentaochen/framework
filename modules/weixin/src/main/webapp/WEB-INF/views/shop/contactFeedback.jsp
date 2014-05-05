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
</head>

<body>
  <h1 class="toptitle"><a href="contact_us.html"><i class="icon-angle-left"></i>意见反馈</a></h1>
  <ul class="top_ico">
    <li><a href="${ctx}/"><i class="icon-home"></i></a></li>
    <li><a href="${ctx}/member"><i class="icon-user"></i></a></li>
    <li><i class="icon-reorder"></i></li>
  </ul>
  <ul class="menu_zd">
    <li><a href="#">展开菜单1</a></li>
    <li><a href="#">展开菜单2</a></li>
  </ul>
</header>
<script type="text/javascript">
$(document).ready(function(){
	  $(".menu_zd li").eq(0).addClass("no-border");
});

//折叠菜单
$(".icon-reorder").click(function(){

	    if ( $(".menu_zd").css("display")=="block" ){ 
		   $(".menu_zd").css({ "display": "none"});
		   
		}else{ 
		   $(".menu_zd").css({ "display": "block"});
		};
});
</script>
<div class="stieBox" id="wrapper">
  <div id="gundongbox">
      
      <div class="back-box">
      <textarea name="" cols="500" rows="" class="feedback-text" onkeyup="words_deal();"></textarea>
      <div class="xdzf"><span class="zfsz">500</span>字</div>
      </div>
   </div>  
</div>
</body>

<script type="text/javascript">
//
function words_deal()
{
   var curLength=$(".feedback-text").val().length;
   if(curLength>500)
   {
        var num=$(".feedback-text").val().substr(0,500);
        $(".feedback-text").val(num);
        alert("超过字数限制，多出的字将被截断！" );
   }
   else
   {
        $(".zfsz").text(500-$(".feedback-text").val().length);
   }
}
</script> 
</html>
 
