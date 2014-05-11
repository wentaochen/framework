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
<script src="${ctx}/static/shop/js/jquery.min.1.8.3.js" type="text/javascript"></script>
<script src="${ctx}/static/shop/js/iscroll.js"></script>
</head>

<body>
<header>
 <h1 class="toptitle">我的资料</h1>
<%@ include file="common/header.jsp"%>
</header>
<!-- 
 <header>

   
  <h1 class="toptitle">我的资料</h1>
  <ul class="top_ico">
    <li><a href="index.html"><i class="icon-home"></i></a></li>
    <li><a href="user.html"><i class="icon-user"></i></a></li>
    <li><i class="icon-reorder"></i></li>
  </ul>
  <ul class="menu_zd">
    <li><a href="#">展开菜单1</a></li>
    <li><a href="#">展开菜单2</a></li>
  </ul>

 </header>
   -->
 
<div class="stieBox" id="wrapper">
  <div id="gundongbox">
     <!--顶图-->
    <div class="top_user">
       <div class="yiban-kuan">
          <span class="use_photo"><img src="${ctx}/static/shop/img/user.jpg"></span>
       </div> 
       <div class="yiban-kuan">
           <span class="user-name">JetXue</span>
       </div>
    </div>
     <!--基础信息-->
     
     
     <!--公告信息-->
    
      
      <div class="user-list">
        <ul>
          <li>
            <p class="f_right"><i class="icon-angle-right"></i></p><span><i class="icon-food"></i></span>我的菜单
          </li>
          <li>
           <a href="my_order.html"> <p class="f_right"><i class="icon-angle-right"></i></p><span><i class="icon-bell-alt"></i></span>我的外卖</a>
          </li>
          <li>
            <p class="f_right"><i class="icon-angle-right"></i></p><span><i class="icon-pushpin"></i></span>我的预订
          </li>
        </ul>
      </div>
      
      <div class="user-list user-marg-top">
        <ul>
          <li>
            <p class="f_right"><i class="icon-angle-right"></i></p><span><i class="icon-map-marker"></i></span>我的地址
          </li> 
        </ul>
      </div>
      
      <div class="user-list user-marg-top">
        <ul>
          <li>
           <a href="${ctx}/contactus"> <p class="f_right"><i class="icon-angle-right"></i></p><span><i class="icon-phone"></i></span>联系我们</a>
          </li> 
        </ul>
      </div>
      
     
     
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
<!DOCTYPE html>
<head>
<title>晴天铺子</title>
</head>
<body>
	     <form action="${ctx}/member/update" method="post" class="" id="memberForm">
	     	<input type="hidden" name="id" value="${sessionScope.member.id}">
			<div class="control-group info">
				<label class="control-label" for="inputEmail">用户微信ID:</label>
				<div class="controls">
					<input type="text" name="openid" value="${sessionScope.member.openid}">
				</div>
			</div>
	
			<div class="control-group info">
				<label class="control-label" for="inputName">姓名</label>
				<div class="controls">
					<input type="text" name="name" value="${sessionScope.member.name}">
				</div>
			</div>
	
			<div class="control-group info">
				<label class="control-label" for="inputName">手机号: </label>
				<div class="controls">
					<input type="text" name="mobile" value="${sessionScope.member.mobile}">
				</div>
			</div>
	
			<div class="control-group info">
				<label class="control-label" for="inputName"> 邮箱: </label>
				<div class="controls">
					<input type="text" name="email" value="${sessionScope.member.email}">
				</div>
			</div>
			
			<div class="control-group info">
				<label class="control-label" for="inputName"> 地址: </label>
				<div class="controls">
					<textarea type="text" name="address">
						${sessionScope.member.address}
					</textarea>
				</div>
			</div>
	
			<div class="control-group">
				<div class="controls">
					<label class="checkbox">  
						<button type="submit" class="btn">保存</button>
				</div>
			</div>
		</form>
	   <%@include file="/WEB-INF/views/commons/footer.jsp"%>
		<script type="text/javascript">
			$("#memberForm").submit(
					function(event) {
						event.preventDefault();
						$.ajax({
			                cache: true,
			                type: "POST",
			                url:"${ctx}/member/update",
			                data:$('#memberForm').serialize(),// 你的formid
			                async: true,
			                error: function(request) {
			                    alert("提交失败请刷新后在提交");
			                },
			                success: function(data) {
			                     alert("保存成功");
			                }
			            });
					});
		</script>
</body>
</html>

--%>
