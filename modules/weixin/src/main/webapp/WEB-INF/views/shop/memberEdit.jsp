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
<script src="${ctx}/${static}/jquery.validate.min.js" type="text/javascript"></script>
<script src="${ctx}/${static}/messages_cn.js" type="text/javascript"></script>

</head>

<body>
<header>

    <h1 class="toptitle"><a href="javascript:void(0);" onclick="history.go(-1)"><i class="icon-angle-left"></i>个人资料</a></h1>
    <%@ include file="common/header.jsp"%>
</header>

<div class="stieBox" id="wrapper">
  <div id="gundongbox">
     <!--顶图-->
    <div class="top_login">
       <form id="memberEditForm"action="${ctx}/member/update" method="post">
       			<input name="id" type="hidden" value="${member.id}">
		       <div class="log-box">
		        <i class="icon-user"></i> <input name="name" type="text" class="logform" placeholder="姓名" value="${member.name}">
		       </div> 
		       <div class="log-box">
		        <i class="icon-key"></i> <input name="email" type="text" class="logform" placeholder="电子邮件" value="${member.email}">
		       </div>
		       <div class="log-box">
		        <i class="icon-map-marker"></i> <input name="address" type="text" class="logform" placeholder="送货地址" value="${member.address}">
		       </div>
		       <div class="but-box">
		        	性别：<span> <input name="sex" value="男" type="radio" class="logform" ${(member.sex=='男')?'checked' : ''}> 男 </span> <span><input name="sex" value="女" type="radio" class="logform" ${(member.sex=='女')?'checked' : ''}>女</span>
		       </div>	       
		       <div class="but-box">
		        <input type="submit" value="修改" class="login-but">
		        <a href="javascript:void(0);" onclick="history.go(-1)">取消</a>
		       </div>
      </form> 
    </div>
     
     
   </div>  
</div>
	<script type="text/javascript">
		$().ready(function() {
			var validator = $("#memberEditForm").validate({
				submitHandler : function(form) {
					$.ajax({
		                cache: true,
		                type: "POST",
		                url:"${ctx}/member/update",
		                data:$('#memberEditForm').serialize(),
		                async: true,
		                error: function(request) {
		                    alert("提交失败请刷新后在提交");
		                },
		                success: function(data) {
		                     if("ok"==data){
		                    	 window.location.href = "${ctx}/member/";
		                     }else{
		                    	 alert("输入信息有误，请填写正确的手机号码");
		                     }
		                }
		            });
				},
				rules: {
					   name: "required",
					   email: {
					    	email:true
					   }
			    }
				,messages: {
					name: "请输入姓名",
					email: {
						email:"请输入正确的email格式"
					   }
				}
			});
			/*
			$("#reset").click(function() {
				validator.resetForm();
			});
			*/

		});
	</script>
</body>
<script type="text/javascript">
//滚动区的JS
var myScroll;
function loaded() {
	//myScroll = new iScroll("wrapper");
}

document.addEventListener("touchmove", function (e) { e.preventDefault(); }, false);
document.addEventListener("DOMContentLoaded", function () { setTimeout(loaded, 200); }, false);

</script>
</html>




<%-- 

<!DOCTYPE html>
<head>

<title>会员注册中心</title>
</head>
<body>

	<form id="registerForm"action="${ctx}/binding" method="post" class="">
		<input type="hidden" name="openid" value="${openid}">

		<div class="control-group info">
			<label class="control-label" for="inputName">姓名</label>
			<div class="controls">
				<input type="text" name="name" value="" class="required">
			</div>
		</div>

		<div class="control-group info">
			<label class="control-label" for="inputName">手机号: </label>
			<div class="controls">
				<input type="text" name="mobile" value="">
			</div>
		</div>
		
		<div class="control-group info">
			<label class="control-label" for="inputName">密码: </label>
			<div class="controls">
				<input type="password" name="password" value="">
			</div>
		</div>
		
		
	 

		<div class="control-group">
			<div class="controls">
				<label class="checkbox"> <!--   <input type="checkbox">
					Remember me </label>
				 -->
					<button type="submit" class="btn">注册</button>
				    
					<a href="${ctx}/login"  class="btn">登录</a>  
			</div>
		</div>
	</form>

</body>
</html>
--%>