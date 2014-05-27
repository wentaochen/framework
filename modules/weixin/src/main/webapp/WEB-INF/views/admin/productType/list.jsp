<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/commons/taglibs.jsp"%>
<!DOCTYPE html>
<head>
<%@include file="/WEB-INF/views/admin/commons/meta.jsp"%>
<title>产品分类列表</title>
<script type="text/javascript">
	
</script>
</head>
<body>
	<header> 产品分类管理 </header>
	<a href="${ctx}/admin/productype/add" class="btn btn-info">添加产品分类</a>
	<div class="bs-docs-example">
		<table class="table table-hover">
			<thead>
				<tr>
					<th>名称</th>
					<th>排序</th>
					<th>子产品名称</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${types}" var="item">
					<tr>
						<td>${item.name}</td>
						<td>${item.sort}</td>
						<td>
							<c:forEach items="${item.products}" var="item2">
									${item2.name}&nbsp;
							</c:forEach>
						</td>
						<td>
							<a href="${ctx}/admin/productype/update/${item.id}" class="btn btn-primary">编辑</a>
							<a href="${ctx}/admin/productype/delete/${item.id}" class="btn btn-primary">删除</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<%@include file="/WEB-INF/views/admin/commons/footer.jsp"%>
</body>
</html>
