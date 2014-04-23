<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/commons/taglibs.jsp"%>
<!DOCTYPE html>
<head>
<%@include file="/WEB-INF/views/commons/meta.jsp"%>
<title>店铺首页</title>
<script type="text/javascript">
	function placeOrder(id) {
		var url = "${ctx}/cart/add/" + id;
		$.ajax({
			url : url,
			type : 'GET',
			dataType : "html",
			cache : false,
			success : function(html) {
				if (html == "ok") {
					$("#addSuccess").fadeIn("slow");
					$("#addSuccess").fadeOut("slow");
				}
			}
		});
	}
</script>
</head>
<body>
	<%@include file="/WEB-INF/views/commons/header.jsp"%>
	<div  id="addSuccess" class="btn btn-success" style="display: none">添加成功!</div>
	<div class="bs-docs-example">
		<table class="table table-hover">
			<thead>
				<tr>
					<th>名称</th>
					<th>价格</th>
					<th>图片</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${items}" var="item">
					<tr>
						<td>${item.name}</td>
						<td>$ ${item.price}/${item.unit}</td>
						<td><img
							src="${ctx}/${static}/img/dianxin.jpg"
							class="img-rounded" style="width:100px;height:60px;">
					    </td>
						<td><input type="button" class="btn btn-primary" onclick="placeOrder('${item.id}')"
							value="添加"></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<%@include file="/WEB-INF/views/commons/footer.jsp"%>
</body>
</html>
