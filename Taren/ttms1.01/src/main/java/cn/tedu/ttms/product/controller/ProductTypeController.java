package cn.tedu.ttms.product.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import cn.tedu.ttms.common.web.JsonResult;
import cn.tedu.ttms.product.entity.ProductType;
import cn.tedu.ttms.product.service.ProductTypeService;


@Controller
@RequestMapping("/type/")
public class ProductTypeController {
	@Autowired
	private ProductTypeService pts;
	
	@RequestMapping("listUI")
	public String listUI(){
		return "product/type_list";
	}
	@RequestMapping("editUI")
	public String editUI(){
		return "product/type_edit";
	}
	@RequestMapping("doFindGridTreeNodes")
	@ResponseBody
	public JsonResult doFindGridTreeNodes(){
		List<Map<String, Object>> list=
			pts.findGridTreeNodes();
		return new JsonResult(list);
	}
	@RequestMapping("doDeleteObject")
	@ResponseBody
	public JsonResult doDeleteObject(Integer id){
		pts.deleteObject(id);
		return new JsonResult("删除成功");
	}
	@RequestMapping("doSaveObject")
	@ResponseBody
	public JsonResult doSaveObject(ProductType entity){
		pts.saveObject(entity);
		return new JsonResult("插入成功");
	}
	
	 @RequestMapping("doFindZTreeNodes")
	 @ResponseBody
	  public JsonResult doFindZTreeNodes(){
		  List<Map<String,Object>>list=pts.findZTreeNodes();
		  return new JsonResult(list);
	  }
	 
	 @RequestMapping("doFindObjectById")
	  @ResponseBody
	  public JsonResult doFindObjectById(Integer id){
		  Map<String,Object> map=pts.findObjectById(id);
		  return new JsonResult(map);
	  }
	  @RequestMapping("doUpdateObject")
	  @ResponseBody
	  public JsonResult doUpdateObject(ProductType entity){
		  pts.updateObject(entity);
		  return new JsonResult("update ok");
	  }
}
