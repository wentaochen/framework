function jumpPage(pageNo) {
	$("#pageNo").val(pageNo);
    $('form').first().submit();
}

function sort(orderBy) {
	var orderByArray=$("#orderBy").val().split(",");
	var index=$.inArray(orderBy,orderByArray); 
	//检验controller设置的排序条件和前台设置的是否一样
	if(index<0){
		alert("检验controller设置的排序条件和前台设置的是否一样");
	}
	
	var defaultOrderArray=$("#order").val().split(",");
	
	if(defaultOrderArray[index]=="desc"){
		defaultOrderArray[index]="asc";
	}else{
		defaultOrderArray[index]="desc";
	}
	
	$("#order").val(defaultOrderArray.join());
	$('form').first().submit();
}

function search() {
	$("#order").val("");
	$("#orderBy").val("");
	$("#pageNo").val("1");

	$('form').first().submit();
}

function pageSizeChange(){
	$("#order").val("");
	$("#orderBy").val("");
	$("#pageNo").val("1");

	$('form').first().submit();
}