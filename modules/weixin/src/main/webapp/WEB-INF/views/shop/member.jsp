<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/commons/taglibs.jsp"%>
<!DOCTYPE html>
<head>
<%@include file="/WEB-INF/views/commons/meta.jsp"%>
<title>晴天铺子</title>
</head>
<body>
	     <%@include file="/WEB-INF/views/commons/header.jsp"%>
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
