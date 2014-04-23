<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/commons/taglibs.jsp"%>
<!DOCTYPE html>
<head>
<%@include file="/WEB-INF/views/commons/meta.jsp"%>
<script src="${ctx}/${static}/jquery.validate.min.js" type="text/javascript"></script>
<script src="${ctx}/${static}/messages_cn.js" type="text/javascript"></script>
<title>会员登录</title>
</head>
<body>
		<%@include file="/WEB-INF/views/commons/header.jsp"%>
		<form action="${ctx}/login" method="post" id="loginForm">
		<div class="control-group info">
			<label class="control-label" for="inputName">手机号: </label>
			<div class="controls">
				<input type="text" name="mobile" value="">
			</div>
		</div>
		
		<div class="control-group info">
			<label class="control-label" for="inputName"> 密码: </label>
			<div class="controls">
				<input type="password" name="password" value="">
			</div>
		</div>

		<div class="control-group">
			<div class="controls">
				<label class="checkbox"> 
					<button type="submit" class="btn">登录</button>
					<c:if test="${sessionScope.openid!=null}"> 
						<a href="${ctx}/binding/${sessionScope.openid}" class="btn">注册</a>
					</c:if>
			</div>
		</div>
		</form>
	   <%@include file="/WEB-INF/views/commons/footer.jsp"%>
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

</body>
</html>
