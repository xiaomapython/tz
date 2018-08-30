var columns=[{field : 'selectItem',
             radio : true
             },
             {title : '分类id',
             field : 'id',
             visible : false,
             align : 'center',
             valign : 'middle',
             width : '80px'
             },
             {title : '分类名称',
             field : 'name',
             align : 'center',
             valign : 'middle',
             sortable : true,
             width : '180px'
             },
             {title : '上级分类',
             field : 'parentName',
             align : 'center',
             valign : 'middle',
             sortable : true,
             width : '180px'
             },
             {title : '排序号',
             field : 'sort',
             align : 'center',
             valign : 'middle',
             sortable : true,
             width : '100px'}];
$(function(){
	doGetObjects();
	//注册点击事件
	$("#formHead").on("click",".btn-delete",doDeleteObject)
	       .on("click",".btn-add,.btn-update",doLoadEditPage)
});
/*加载编辑页面*/
function doLoadEditPage(){
	var url="type/editUI.do?t="+Math.random(1000);
	var title;
	if($(this).hasClass("btn-add")){
		title="添加分类";
	}
	if($(this).hasClass("btn-update")){
		var id=getSelectedId();
		if(id==-1){
		alert("请先选择");return;
		}
		$(".content").data("id",id);
		title="修改分类";
	}
	$(".content").load(url,function(){
		$(".panel-heading").html(title);
	});
}
function doDeleteObject(){
	//1.获得选中的id
	var id=getSelectedId();
	if(id==-1){
		alert("请选择...");
		return;
	}
	console.log("id="+id);
	var params={"id":id};
	var url="type/doDeleteObject.do";
	//2.发起异步请求
	$.post(url,params,function(result){
		if(result.state==1){
			alert(result.msg);
			doGetObjects();
		}else{
			alert(result.msg);
		}
	});
}

function  getSelectedId(){
	//1.获得表格对象
	var table=$("#typeTable");
	//2.获得表格对象中选中的id
	//返回值为一个数组
	var selections=table.bootstrapTreeTable("getSelections");
	if(selections.length==0){
		return -1;//表示没有选中
	}
	return selections[0].id;//单选
}
function doGetObjects(){
	var tableId="typeTable";
	var url="type/doFindGridTreeNodes.do";
	var table=new TreeTable(tableId,url,columns);
	table.setIdField("id");
	table.setCodeField("id");
	table.setParentCodeField("parentId");
	table.setExpandAll(false);
	table.setExpandColumn(2);
	table.init();
}