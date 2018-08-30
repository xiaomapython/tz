$(document).ready(function(){
//1.页面加载完后，通过doGetObjects方法
	//异步请求数据
	doGetObjects();
	//2.为表单注册点击事件
	$("#queryFormId").on("click",".btn-search",doQueryObjects)
	                     .on("click",".btn-valid,.btn-invalid",doValidById)
	                    .on("click",".btn-add,.modify",doLoadEditPage)
	                    
});//$(function(){})

/*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
/*加载（异步加载）编辑页面*/
function doLoadEditPage(){
	//判定是添加还是修改
	var title;
	//var clazz=$(this).attr("class")
	if($(this).hasClass("modify")){
		title="修改项目";
		//<tr><td><button></td></tr>
		//在模态框绑定id值
		//在修改页面可以获得此值，根据有没有值执行
		//不同操作
		$("#modal-dialog").
		      data("id",$(this).parent().parent().data("id"));
	}
    if($(this).hasClass("btn-add")){
    	title="添加项目";
	}
	var url="project/editUI.do";
	//在id为modal-dialog的对象中找到类选择器为.modal-body
	//的对象，然后在此位置异步加载url
	$("#modal-dialog .modal-body").load(url,function(){
		//设置标题
		$(".modal-title").html(title);
		//显示模块
		$("#modal-dialog").modal("show");//显示
	});
}
/*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
/*实现项目对象的禁用和启用操作*/
function doValidById(){
	//1.获得点击对象的class属性
	//要根据此属性的值判定是执行禁用还是启用
	var clazz=$(this).attr("class");
	//2.执行禁用启用操作
	//2.1设置状态值(后续要将此值传到服务端)
	var state;
	if($(this).hasClass("btn-valid")){
		state=1;
	}
    if($(this).hasClass("btn-invalid")){
		state=0;
	}
    console.log(state);
    //2.2获得选中的checkbox的值
    var checkedIds=getCheckedIds();
    if(checkedIds==""){
    	alert("请至少选择一个：");
    	return;
    }
    console.log(checkedIds);
    //2.3封装参数数据
    var params={"valid":state,"checkedIds":checkedIds};
    var url="project/doValidById.do";
    //发起异步请求并处理结果
    $.post(url,params,function(result){
    	if(result.state==1){
    		//显示修改结果
    		alert(result.msg);
    		doGetObjects();
    	}else{
    		alert(result.msg);
    	}
    });
}
/*通过此函数获得选中的checkedboxid*/
function getCheckedIds(){
	var checkedIds="";//1,2,3,4....
	//用于迭代名字为cn的input对象
	$("#tbodyId input[name='cn']").each(function(){
		//验证当前对象中有没有checked属性
		if($(this).prop("checked")){
			if(checkedIds==""){
				checkedIds+=$(this).val();
			}else{
				checkedIds+=","+$(this).val();
			}
		}
	});
	return checkedIds;
}
/*定义查询数据的方法*/
function doQueryObjects(){
	//1.初始化分页的pageCurrent的值
	$("#pageId").data("pageCurrent",1);
	//2.获得并提交参数,执行查询操作
	doGetObjects();
}
/*定义函数获得查询表单中的参数值*/
function getQueryData(){
	var params={"name":$("#searchNameId").val(),
			"valid":$("#searchValidId").val()};
	console.log(JSON.stringify(params));
	return params;
}

function doGetObjects(){
	var url = "project/doFindObjects.do";
	var params=getQueryData();
	//获得当前页的数据
	var pageCurrent=$("#pageId").data("pageCurrent");
	if(!pageCurrent)pageCurrent=1;
	//动态为params对应的json添加key-value
	params.pageCurrent=pageCurrent;
	//var params={"pageCurrent":pageCurrent};
	//1.底层发起ajax异步get请求
//	$.getJSON(url,params,function(result){
	//2.底层发起ajax异步post请求
	$.post(url,params,function(result){
	//客户端会将服务端返回的json串转换
	//为json对象赋值给result参数
		if(result.state==1){
		setTableBodyRows(result.data.list);
		console.log(result.data.list);
		//设置分页信息(参考page.jsp)
		setPagination(result.data.po);
//		console.log(result[1].id);
		}else{
			alert(result.msg);
		}
	});
	//i表示第几个元素
//	for(var i in result){
//		//key值为id的所有value
//		console.log(result[i].id);
//	}
	
}
/*
 * 将服务端返回的数据添加在页面table的tbody中
 */
function setTableBodyRows(result){
	console.log(result);
	//1.获得tbody对象
	var tbody=$("#tbodyId");
	//2.清空tBody中的数据(类似刷新，假如有数据先清空)
	tbody.empty();
	var firstTd=
		'<td><input type="checkbox" name="cn" value="[id]"/></td>';
	//3.迭代result这个json对象
	for( var i in result){
		//3.1构建tr对象
		var tr=$("<tr></tr>");
		//在tr对象上绑定一个数据
		tr.data("id",result[i].id);
		//3.2 tr中追加td对象
		var newTd=firstTd.replace("[id]",result[i].id);
		tr.append(newTd);
		tr.append('<td>'+result[i].code+'</td>');
		tr.append('<td>'+result[i].name+'</td>');
		tr.append('<td>'+result[i].beginDate+'</td>');
		tr.append('<td>'+result[i].endDate+'</td>');
		tr.append('<td>'+(result[i].valid?'启用':'禁用')+'</td>');
		tr.append('<td><input type="button" class="btn btn-info modify" value="修改"/></td>');
		//3.3将tr追加到tbody中
		tbody.append(tr)
	}
}


