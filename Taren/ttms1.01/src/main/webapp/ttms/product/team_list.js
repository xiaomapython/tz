$(document).ready(function(){
	doGetObjects();
	$("#queryFormId").on("click",".btn-search",doQueryObjects)
	                                  .on("click",".btn-add",doLoadPage)
});
function doLoadPage(){
	var title;
	if($(this).hasClass("btn-add")){
		title="添加团信息";
	}
	var url="team/editUI.do";
	$("#modal-dialog .modal-body").load(url,function(){
		$(".modal-title").html(title);
		$("#modal-dialog").modal("show");
	});
	
}
/*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
/*查询按钮的事件处理函数*/
function doQueryObjects(){
	//1.初始化当前页pageCurrent的值
	$("#pageId").data("pageCurrent",1);
	//2.重用doGetObject函数执行查询操作
	doGetObjects();
	
}
/*获取文本框中输入的文本*/
function getQueryFormData(){
	return {"projectName":$("#searchTId").val()}
}
/**/
function doGetObjects(){
	var url="team/doGetObjects.do";
	var params=getQueryFormData();
	var pageCurrent=$("#pageId").data("pageCurrent");
	if(!pageCurrent)pageCurrent=1;
	params.pageCurrent=pageCurrent;
/*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
//没有使用分页查询，将查询到的呢绒直接展示在表单中
//	$.getJSON(url,function(result){
//		setTableBodyRows(result.data.list);
//	});
/*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
	$.post(url,params,function(result){
		if(result.state==1){
			 console.log(result.data.po);
			 //填充表单数据
		     setTableBodyRows(result.data.list);
		     //设置分页查询
		     setPagination(result.data.po);
		}else{
			alert(result.msg);
		}
});
}
function setTableBodyRows(result){
	console.log(result);
	var tbody=$("#tbodyId");
	tbody.empty();
	for(var i in result){
		var tr=$("<tr></tr>");
		createTds(tr,result[i]);
		tbody.append(tr);
	}
}
function createTds(tr,r){
	var template=
		'<td><input type="checkbox" name="checkedItem"  value="[id]"></td>'
		+'<td>[name]</td>'
		+'<td>[pName]</td>'
		+'<td>[state]</td>'
		+'<td><input type="button" class="btn btn-info modify" value="修改"/></td>';
	tr.append(template.replace("[id]",r.id)
	               .replace("[name]",r.name)
	               .replace("[pName]",r.projectName)
	               .replace("[state]",r.valid?"启用":"禁用"));
	               
}