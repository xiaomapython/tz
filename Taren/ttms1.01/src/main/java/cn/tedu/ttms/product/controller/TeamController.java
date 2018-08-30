package cn.tedu.ttms.product.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.ttms.common.vo.IdName;
import cn.tedu.ttms.common.web.JsonResult;
import cn.tedu.ttms.product.entity.Team;
import cn.tedu.ttms.product.service.TeamService;

@Controller
@RequestMapping("/team/")
public class TeamController {
	@Autowired
	@Qualifier("teamServiceImpl")
	private TeamService ts;
	@RequestMapping("listUI")
	public String listUI(){
		//WEB-INF/pages/product/project_list.jsp
		return "product/team_list";
	}
	@RequestMapping("editUI")
	public String enditUI(){
		return "product/team_edit";
	}
	
	@RequestMapping("doGetObjects")
	@ResponseBody
	public JsonResult doFindObjects(
			String projectName,Integer pageCurrent){
		//springMVC:String默认空串，int默认是null
		Map<String, Object> map=ts.findPageObjects(
				projectName,pageCurrent);
		return new JsonResult(map);
	}
	@RequestMapping("doSaveObject")
	@ResponseBody
	public JsonResult doSaveObject(Team entity){
		ts.saveObject(entity);
		return new JsonResult("insert ok");
	}
/*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
	@RequestMapping("doFindIdAndNames")
	@ResponseBody
	public JsonResult doFindIdAndNames(){
		List<IdName> list=ts.findIdAndNames();
		return new JsonResult(list);
	}
}
