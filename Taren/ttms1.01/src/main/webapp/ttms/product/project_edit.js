/*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
$(document).ready(function(){
	//在模态框的save上注册点击事件
	$("#modal-dialog").on("click",".ok",doSaveOrUpdate);
	$("#modal-dialog").on("hidden.bs.modal",function(){
		//当模态框隐藏时移除.ok上注册的click事件
		//this指的是$("#modal-dialog .ok")
		$(this).off("click",".ok");
		$(this).removeData("id");
	});
	var id=$("#modal-dialog").data("id");
	console.log("id="+id);
	//假如ID有值则可以根据此值执行下一步操作
	if(id)doFindObjectById(id);
	
});
/*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
/*根据id执行查询操作*/
function doFindObjectById(id){
	var url="project/doFindObjectById.do";
	var param={"id":id};
	$.post(url,param,function(result){
		console.log(result)
		//初始化页面数据
		if(result.state==1){
			doInitEditFormData(result);
		}else{
			alert(result.msg);
		}
	});
}
function doInitEditFormData(result){
	$("#nameId").val(result.data.name);
	$("#codeId").val(result.data.code);
	$("#beginDateId").val(result.data.beginDate);
	$("#endDateId").val(result.data.endDate);
	$("#editFormId input[name='valid']").each(function(){
		if($(this).val()==result.data.valid){
			$(this).prop("checked",true);
		}
	});
	console.log(result.data.valid)
	$("#noteId").html(result.data.note);
}
/*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
/*添加或修改*/
function doSaveOrUpdate(){
	//3.验证表单数据是否为空
	if(!$("#editFormId").valid())return;
	//1.获得表单数据
	var params=getEditFormData();
	//当修改记录时需要添加id值，所以要在参数中
	//要动态添加一个key:value
	var id=$("#modal-dialog").data("id");
	if(id)params.id=id;
	console.log(params);
	console.log(JSON.stringify(params));
	//2.异步提交表单数据

	var insert="project/doSaveObject.do";
	var update="project/doUpdateObject.do";
	var url=id?update:insert;
	console.log(url);
	$.post(url,params,function(result){
		console.log(result.msg);
		console.log(id+"就是id");
		if(result.state==1){
			$("#modal-dialog").modal("hide");
			doGetObjects();
		}else{
			alert(result.msg);
		}
	});
}
/*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
/*获取表单数据*/
function getEditFormData(){
	var params={
			"name":$("#nameId").val(),
			"code":$("#codeId").val(),
			"beginDate":$("#beginDateId").val(),
			"endDate":$("#endDateId").val(),
			"valid":$("input[name='valid']:checked").val(),
			"note":$("#noteId").val()
	       };
  return params;
}


