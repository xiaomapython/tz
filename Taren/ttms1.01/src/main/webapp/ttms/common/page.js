/*
 * 设置分页信息
 *   Pagination(表示分页)
 */
//3
$(document).ready(function(){
	$("#pageId").on("click",".next,.first,.pre,.last",jumpToPage);
});
function setPagination(po){
	//1.初始化页面总页数
	//1.1首先获得pageCount类选择器代表的对象
	//1.2在对象内部写入总页数
	$(".pageCount").html("总页数("+po.pageCount+")");
	//2.初始化页面当前页
	$(".pageCurrent").html("当前页("+po.pageCurrent+")");
	//5.数据绑定(当前页，总页数)，就是将一个数据
	//绑定到对应的对象上
	$("#pageId").data("pageCurrent",po.pageCurrent);
	$("#pageId").data("pageCount",po.pageCount);
}
//4
function jumpToPage(){
	//1)需要获得当前页
	var pageCurrent = $("#pageId").data("pageCurrent");
	//2)需要获得总页数
	var pageCount = $("#pageId").data("pageCount");
	//3)获取点击的对象
	var clazz=$(this).attr("class");
	//点击next跳转到下一页 
	if(clazz=="next"&&pageCurrent<pageCount){
		pageCurrent++;
	}
	//点击pre跳转到上一页
	if(clazz=="pre"&&pageCurrent>1){
		pageCurrent--;
	}
	//点击首页first跳转到首页
	if(clazz=="first"){
		pageCurrent=1;
	}
	//点击尾页last跳转到尾页
	if(clazz=="last"){
		pageCurrent=pageCount;
	}
	//重新绑定pageCurrent的值
	$("#pageId").data("pageCurrent",pageCurrent);
	//重新执行查询
	doGetObjects();
}





