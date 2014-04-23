<%@ page pageEncoding="UTF-8"%>
<script type="text/javascript">
$(document).ready(function(){
	      $(".pageSizeClass").first().initSelected("${page.pageSize}");
	      $(".pageSizeClass").last().initSelected("${page.pageSize}");
});  
</script>
<div>
	第${page.pageNo}页, 共${page.totalPages}页
	<a href="javascript:jumpPage(1)">首页</a>
	<c:if test="${page.hasPre}">
		<a href="javascript:jumpPage(${page.prePage})">上一页</a>
	</c:if>
	<c:if test="${page.hasNext}">
		<a href="javascript:jumpPage(${page.nextPage})">下一页</a>
	</c:if>
	<a href="javascript:jumpPage(${page.totalPages})">末页</a>
	<select id="pageSize" name="pageSize" class="pageSizeClass" onchange="pageSizeChange();">
		<option value="10">
			10
		</option>
		<option value="20">
			20
		</option>
		<option value="50">
			50
		</option>
		<option value="100">
			100
		</option>
	</select>
    <%--
	这里做的目的是为了保证下面的input,hidden元素只出现一次
	是为了保证page.js中的操作元素,不出现重复以免引起很多问题 
	--%>
	<c:if test="${isShow==null}">
	<c:set value="hasShow" var="isShow" scope="page"/> 
		<input type="hidden" name="pageNo" id="pageNo" value="${page.pageNo}" />
		<input type="hidden" name="orderBy" id="orderBy"
			value="${page.orderBy}" />
		<input type="hidden" name="order" id="order" value="${page.order}" />
	</c:if>
</div>