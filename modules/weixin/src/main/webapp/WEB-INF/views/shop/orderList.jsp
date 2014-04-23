<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/commons/taglibs.jsp"%>
<!DOCTYPE html>
<head>
<%@include file="/WEB-INF/views/commons/meta.jsp"%>
<title>我的订单</title>
<script type="text/javascript">
</script>
</head>
<body>
	<%@include file="/WEB-INF/views/commons/header.jsp"%>
	<div class="bs-docs-example">
	    <!-- cart is not null -->
		<table class="table table-hover">
			<thead>
				<tr>
					<th>订单编号</th>
					<th>订单明细详情</th>
					<th>总价</th>
					<th>下单时间</th>
					<th>状态</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${orders}" var="item">
					<tr>
						<td>
							${item.id}
						</td>
						<td>
							<c:forEach items="${item.orderItem}" var="orderItem">
								<p>
									${orderItem.product.name}(数量:${orderItem.count})
								</p>
							</c:forEach>
						</td>
						<td>
							 ${item.totalPrice}
						</td>
						<td>
							 ${item.createTime}
						</td>
						<td>
							 ${item.orderState.label}
						</td>
						<td>
							<input type="button" class="btn btn-primary" onclick="cancelCartItem('')"
							value="查物流">
							<input type="button" class="btn btn-primary" onclick="cancelCartItem('')"
							value="确认收货">
							<input type="button" class="btn btn-primary" onclick="cancelCartItem('')"
							value="取消">
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<%@include file="/WEB-INF/views/commons/footer.jsp"%>
</body>
</html>
