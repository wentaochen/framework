<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/commons/taglibs.jsp"%>
<!DOCTYPE html>
<head>
<%@include file="/WEB-INF/views/admin/commons/meta.jsp"%>
<title>添加产品</title>
</head>
<body>
	  <form action="${ctx}/admin/product/add" method="post" enctype="multipart/form-data">
	  		<input type="hidden" name="id" value="${model.id}">
	  		<input type="hidden" id="imageUrl" name="imageUrl" value="${model.imageUrl}">
	  		<br>
	  		<label>
	  			  上传图片:
	  		</label>
	  		<br>
			<input type="file" name="imageFile" id="imageFile">
	  		<br>
	  		<label>
	  			产品名称:
	  		</label>
	  		<br>
	  		<input type="text" name="name" value="${model.name}">
	  		<br>
	  		<label>
	  			产品出售价格价:
	  		</label>
	  		<br>
	  		<input type="text" name="price" value="${model.price}">
	  		<br>
	  			<label>
	  				打折前的价格:
	  			</label>
	  		<br>
	  	 	<input type="text" name="discountPrice" value="${model.discountPrice}">
	  		<br>
	  			<label>
	  				已销售的数量:
	  			</label>
	  			<br>
	  			<input type="text" name="salesVolume" value="${model.salesVolume}">
	  		<br>
	  			<label>
	  				好评数量:
	  			</label>
	  			<br>
	  			<input type="text" name="goodReputation" value="${model.goodReputation}">
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
	  		<input type="submit" value="发布产品">
	  </form>
</body>
</html>
