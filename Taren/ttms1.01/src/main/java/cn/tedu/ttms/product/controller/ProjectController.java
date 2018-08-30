package cn.tedu.ttms.product.controller;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.ttms.common.web.JsonResult;
import cn.tedu.ttms.product.entity.Project;
import cn.tedu.ttms.product.service.ProjectService;


@Controller
@RequestMapping("/project/")
public class ProjectController {
	@Autowired
	private ProjectService p;
	
	@RequestMapping("listUI")
	public String listUI(){
		//WEB-INF/pages/product/project_list.jsp
		return "product/project_list";
	}
	
	/*
	 * @ResponseBody注解可以将方法返回的对象(
	 * 例如实体对象，map对象，list对象)转换成json
	 * 字符串
	 * 问题：控制层的方法中接受页面数据时,是如何实现的?
	 * 1)HttpServletRequest
	 * 2)直接通过变量(名字必须与页面参数相同)
	 * 3)通过实体对象(参数必须与对象用对应的set方法)
	 * 4)...........
	 */
	@RequestMapping("doFindObjects")
	@ResponseBody
	public JsonResult doFindObjects( 
			String name,
			Integer valid ,
			Integer pageCurrent){
		System.out.println("pageCurrent="+pageCurrent);
		
		Map<String,Object> map=p.findPageObjects(
				                                                       name,valid,pageCurrent);
		return new JsonResult(map);
	}//封装返回结果到JsonResult对象中
/*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/	
	@RequestMapping("doValidById")
	@ResponseBody
	public JsonResult doValidById(String checkedIds,Integer valid){
		System.out.println("处理器checkedIds="+checkedIds);
		System.out.println("处理器valid="+valid);
		p.getValidById(checkedIds, valid);
		return new JsonResult("update=OK");
	}
/*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
	/***
	 * 实现数据的写入操作
	 * @param entity 用于封装页面中表单的数据(name,code...)
	 * 客户端：name=""
	 * SpringMVC：从参数中获取这些名字和值，然后底层会通过反射将这些
	 * 值通过对象set方法注入到具体对象
	 * 	 Dispatcher-->HandlerMapping-->HandlerExecutionChain
	 * -->HandlerAdapter-->Handler(Controller)
	 * -->HttpMessageConvert
	 */
	@RequestMapping("doSaveObject")
	@ResponseBody
	public JsonResult doSaveObject(Project entity){
		p.saveObject(entity);
		return new JsonResult("保存成功");
	}
/*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
	@RequestMapping("editUI")
	public String edit(){
		return "product/project_edit";
	}
/*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
	@RequestMapping("doFindObjectById")
	@ResponseBody
	public JsonResult doFindObjectById(Integer id){
		Project project=p.findObjectById(id);
		return new JsonResult(project);
	}
/*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
	@RequestMapping("doUpdateObject")
	@ResponseBody
	public JsonResult doUpdateById(Project entity){
		p.updateObjectById(entity);
		return new JsonResult("更新成功");
	}
}











