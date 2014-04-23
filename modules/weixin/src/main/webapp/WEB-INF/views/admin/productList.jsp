<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/commons/taglibs.jsp"%>
<!DOCTYPE html>
<head>
<%@include file="/WEB-INF/views/admin/commons/meta.jsp"%>
<title>我的产品</title>
<script type="text/javascript">
	
</script>
</head>
<body>
	<header> 产品管理 </header>
	<div class="bs-docs-example">
		<table class="table table-hover">
			<thead>
				<tr>
					<th>名称</th>
					<th>价格</th>
					<th>图片</th>
					<th>分类</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${products}" var="item">
					<tr>
						<td>${item.name}</td>
						<td>$ ${item.price}/${item.unit}</td>
						<td><img src="${ctx}/${static}/img/dianxin.jpg"
							class="img-rounded" style="width:100px;height:60px;"></td>
						<td>
							 ${item.type.name}
						</td>
						<td>
							<a href="${ctx}/admin/product/update/${item.id}" class="btn btn-primary">编辑</a>
							<a href="${ctx}/admin/product/delete/${item.id}" class="btn btn-primary">删除</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<a href="${ctx}/admin/product/add" class="btn btn-info">添加新的产品</a>
	</div>
	<%@include file="/WEB-INF/views/admin/commons/footer.jsp"%>
</body>
</html>
