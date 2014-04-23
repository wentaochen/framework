<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/commons/taglibs.jsp"%>
<!DOCTYPE html>
<head>
<%@include file="/WEB-INF/views/admin/commons/meta.jsp"%>
<title>添加产品</title>
</head>
<body>
	  <form action="${ctx}/admin/product/add" method="post">
	  		<input type="hidden" name="id" value="${model.id}">
	  		<label>
	  			产品名称:
	  		</label>
	  		<br>
	  		<input type="text" name="name" value="${model.name}">
	  		<br>
	  		<label>
	  			产品价格:
	  		</label>
	  		<br>
	  		<input type="text" name="price" value="${model.price}">
	  		<br>
	  		 <label>
	  			产品分类:
	  		</label>
	  		<br>
	  		<select name="type.id">
	  			<c:forEach items="${types}" var="item">
	  				<option value="${item.id}" <c:if test="${model.type.id==item.id}">selected="selected"</c:if> >
	  				${item.name}
	  				</option>
	  			</c:forEach>
	  		</select>
	  		
	  		<label>
	  			单位:
	  		</label>
	  		<br>
	  		<select name="unit">
	  				<option value="份">份</option>
	  				<option value="个">个</option>
	  				<option value="套">套</option>
	  		</select>
	  		<br>
	  		<br>
	  		<input type="submit" value="发布产品">
	  </form>
</body>
</html>
