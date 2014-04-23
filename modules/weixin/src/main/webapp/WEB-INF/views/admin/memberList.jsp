<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/commons/taglibs.jsp"%>
<!DOCTYPE html>
<head>
<%@include file="/WEB-INF/views/admin/commons/meta.jsp"%>
<title>会员管理</title>
<script type="text/javascript">
</script>
</head>
<body>
	<header> 会员管理 </header>
	<div class="bs-docs-example">
		<table class="table table-hover">
			<thead>
				<tr>
					<th>微信ID</th>
					<th>姓名</th>
					<th>手机</th>
					<th>邮箱</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${members}" var="item">
					<tr>
						<td>${item.openid}</td>
						<td>${item.name}</td>
						<td>${item.mobile}</td>
						<td>${item.email}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>
