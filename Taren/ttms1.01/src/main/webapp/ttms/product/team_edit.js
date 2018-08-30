$(document).ready(function(){
	//在模态框对应按钮上注册click事件
	$("#modal-dialog").on("click",".ok",doSaveOrUpdate);
	//在模态框隐藏时，移除注册click事件，
	//假如不移除可能会导致表单重复提交问题
	$("#modal-dialog").off("hidden.bs.modal",function(){
		$(this).off("click",".ok");
	});//模态框隐藏时解绑事件
	doGetIdAndName();
});
/*获取项目下拉数据*/
function doGetIdAndName(){
	var url="team/doFindIdAndNames.do"
    $.getJSON(url,function(result){
    	if(result.state==1){
    		doInitProjectSelect(result.data)
    	}else{
    		alert(result.msg);
    	}
    })
	
}
/*初始化select列表*/
function doInitProjectSelect(project){
	var select=$("#projectId");
	select.empty();
	var option="<option value='[id]'>[name]</option>";
	for(var i in project){
		select.append(
				option.replace("[id]",project[i].id)
				           .replace("[name]",project[i].name));
	}
}
/*执行保存或跟新操作*/
function doSaveOrUpdate(){
	var params=getEditFormData();
	var url="team/doSaveObject.do";
	$.post(url,params,function(result){
		if(result.state==1){
			$("#modal-dialog").modal("hide");
			$("#pageId").data("pageCurrent",1);
			doGetObjects();
		}else{
			alert(result.msg);
		}
	})
}
/*获得编辑框数据*/
function getEditFormData(){
	var params={"name":$("#nameId").val(),
			              "projectId":$("#projectId").val(),
			              "valid":$("input[name='valid']:checked").val(),
			              "note":$("#noteId").val()};
	return params;
}
/*采用动态方式初始化编辑页面*/

