var zTree;
var setting = {
		data : {   
			simpleData : {
				enable : true,
				idKey : "id",  //节点数据中保存唯一标识的属性名称
				pIdKey : "parentId",  //节点数据中保存其父节点唯一标识的属性名称
				rootPId : null  //根节点id
			}
		}
} 
$(document).ready(function(){
	$("#btn-return").click(function(){
		doBack();
	});
	$("#btn-save").click(doSaveOrUpdate);
	$(".load-product-type")
	.click(doLoadZTreeNodes);
	$(".btn-confirm").click(doSetSelectedNode);
	$(".btn-cancle").click(doCancel);
	var id=$(".content").data("id");
	console.log("id="+id);
	if(id)doFindObjectById(id);
});
/*根据id获得数据*/
function doFindObjectById(id){
	var url="type/doFindObjectById.do";
	var params={"id":id};
	$.post(url,params,function(result){
		if(result.state==1){
		  //console.log(result.data);
		  doInitEditFormData(result.data);
		}else{
		  alert(result.message);
		}
	});
}
/*通过数据初始化表单内容*/
function doInitEditFormData(type){
	$("#typeNameId").val(type.name);
	$("#typeSortId").val(type.sort);
	$("#typeNoteId").val(type.note);
	$("#parentNameId").val(type.parentName);
	$("#editTypeForm")
	.data("parentId",type.parentId);
}
//点击确定按钮时设置选择的节点对象
function doSetSelectedNode(){
	//1.获得选中的节点(Node)
	var nodes=zTree.getSelectedNodes();//数组
	console.log(nodes);
	var node=nodes[0];
	//2.将节点数据填充到表中
	$("#editTypeForm").data("parentId",node.id)
	$("#parentNameId").val(node.name);
	//3.隐藏zTree
	doCancel();
}
function doCancel(){
    //隐藏树对象
    $("#typeLayer").css("display","none");
}
/*点击分类,加载分类信息并以zTree结构显示*/
function doLoadZTreeNodes(){
	//定义url
	var url="type/doFindZTreeNodes.do";
	//异步加载分类信息
	$.getJSON(url,function(result){
		if(result.state==1){
		//初始化zTree (jquery.ztree.all.min.js)
		 zTree=$.fn.zTree.init(
					$("#typeTree"),
					setting,
					result.data);
		//显示zTree,其中css为jquery的一个函数
		$("#typeLayer").css("display","block");
		}else{
		   alert(result.state);
		}
	});
}

function doBack(){
	$("#editTypeForm").removeData("parentId");
	$(".content").removeData("id");
	//清空类选择器对应表单中的数据
	$(".dynamicClear").val('');
	var url="type/listUI.do?t="+Math.random(1000);
	$(".content").load(url);
}
//执行保存或更新
function doSaveOrUpdate(){
	//表单非空验证
	if(!$("#editTypeForm").valid())return;
	//1.获得表单数据
	var params=getEditFormData();
	//2.异步提交表单数据
	var insert="type/doSaveObject.do";
	var update="type/doUpdateObject.do";
	var id=$(".content").data("id");
	var url=id?update:insert;
	if(id)params.id=id;
	$.post(url,params,function(result){
		if(result.state==1){
		  alert(result.message);
		//3.保存OK返回列表页面
		  doBack();
		}else{
		  alert(result.message);
		}
	})
}
function getEditFormData(){
	var params={
		"name":$("#typeNameId").val(),
		"parentId":$("#editTypeForm").data("parentId"),
		"sort":$("#typeSortId").val(),
		"note":$("#typeNoteId").val()
		}
	return params;
	//这个数据在传输时:name="A"&parentId=1&...
}




