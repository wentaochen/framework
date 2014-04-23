<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/commons/taglibs.jsp"%>
<!DOCTYPE html>
<head>
<%@include file="/WEB-INF/views/admin/commons/meta.jsp"%>
<title>订单管理</title>
<script type="text/javascript">
</script>
</head>
<body>
	<header>订单管理</header>
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
					<th>客户资料</th>
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
							 ${item.orderState.label}
						</td>
						<td>
							 ${item.createTime}
						</td>
						<td>
							 ${item.member.name}/${item.member.mobile}
						</td>
						<td>
							<a href="${ctx}/admin/order/confirm/${item.id}">确认</a>
							<a href="${ctx}/admin/order/complete/${item.id}">完成</a>
							<a href="${ctx}/admin/order/cancel/${item.id}">取消</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<%@include file="/WEB-INF/views/admin/commons/footer.jsp"%>
</body>
</html>
