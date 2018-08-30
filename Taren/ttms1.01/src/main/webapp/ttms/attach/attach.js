$(document).ready(function(){
  $("#uploadFormId")
  .on("click",".btn-upload",doUpload)
  .on("click",".btn-down",doDownload);
  doGetObjects();
});
/*实现文件下载*/
function doDownload(){
	var digest=
	$(this).parent().parent().data("digest");
	console.log("digest="+digest);
	var url="attachment/doDownload.do?digest="+digest;
	document.location.href=url;
}

function doGetObjects(){
  var url="attachment/doFindPageObjects.do";
  var pageCurrent=
  $("#pageId").data("pageCurrent");
  if(!pageCurrent)pageCurrent=1;
  var params={"pageCurrent":pageCurrent};
  $.post(url,params,function(result){
		if(result.state==1){
		 setTableBodyRows(result.data.list);
		 setPagination(result.data.pageObject);
		}else{
		 alert(result.msg);
		}
  });
}
function setTableBodyRows(list){
	var tBody=$("#tbodyId");
	tBody.empty();
	for(var i in list){
	 var tr=$("<tr></tr>");
	 tr.data("digest",list[i].fileDisgest)
	 tr.append("<td>"+list[i].title+"</td>");
	 tr.append("<td>"+list[i].fileName+"</td>");
	 tr.append("<td>"+list[i].contentType+"</td>");
	 tr.append("<td><button type='button' class='btn btn-default btn-down'>下载</button></td>");
	 tBody.append(tr);
	}
}
function doUpload(){
	//异步提交表单(借助ajaxSubmit函数)
	//ajaxSubmit在jquery.form.js文件中
	$("#uploadFormId")
	.ajaxSubmit({
		url:"attachment/doUpload.do",
		type:"post",
		success:function(result){
			if(result.state==1){
			alert(result.msg);
			//....
			}else{
			alert(result.msg);
			}
		},
	});
}



