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
<c:set var="toptitle" value="登录"></c:set>
<%@ include file="common/header.jsp"%>
<div class="stieBox" id="wrapper">
  <div id="gundongbox">
     <!--顶图-->
    <form action="${ctx}/login" method="post" id="loginForm"> 
    <div class="top_login">
       <div class="log-box">
        <i class="icon-user"></i> <input type="text" name="mobile" class="logform" placeholder="用户名">
       </div> 
       <div class="log-box">
        <i class="icon-key"></i> <input type="password" name="password"  class="logform" placeholder="密码">
       </div>
       <div class="but-box">
        <input type="submit" value="登 录" class="login-but">
        <c:if test="${sessionScope.openid!=null}"> 
				<a href="${ctx}/binding/${sessionScope.openid}">注册</a>
		</c:if>
       </div>
    </div>
    </form>
     <!--基础信息-->
     
     <!--公告信息-->
      <div class="user-list">
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
$().ready(function() {
	var validator = $("#loginForm").validate({
		submitHandler : function(form) {
			$.ajax({
                cache: true,
                type: "POST",
                url:"${ctx}/login",
                data:$('#loginForm').serialize(),
                async: true,
                error: function(request) {
                    alert("提交失败请刷新后在提交");
                },
                success: function(data) {
                     if("ok"==data){
                    	 window.location.href = "${ctx}/";
                     }else{
                    	 alert("输入信息有误，请填写正确的手机号码或密码");
                     }
                }
            });
		},
		rules: {
			   mobile: {
			    required: true,
			    minlength : 11,
				maxlength : 11
			   },
			   password: {
			    required: true,
			    minlength: 6
			  } 
	    }
		,messages: {
			mobile: {
				required: "请输入手机号码",
				minlength : "手机号至少11位",
				maxlength : "手机号最多11位"
			},
			password: {
				required: "请输入密码",
				minlength: "密码长度最少6位"
			} 
		}
	});
});
</script>
</html>
