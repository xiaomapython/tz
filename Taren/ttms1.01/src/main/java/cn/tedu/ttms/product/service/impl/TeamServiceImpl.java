package cn.tedu.ttms.product.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tedu.ttms.common.vo.IdName;
import cn.tedu.ttms.common.web.PageObject;
import cn.tedu.ttms.common.web.ServiceException;
import cn.tedu.ttms.product.dao.ProjectDao;
import cn.tedu.ttms.product.dao.TeamDao;
import cn.tedu.ttms.product.entity.Team;
import cn.tedu.ttms.product.service.TeamService;
import javafx.css.PseudoClass;
import sun.misc.ServiceConfigurationError;
@Service
public class TeamServiceImpl implements TeamService{
    @Autowired
    private TeamDao d;
    @Autowired
    private ProjectDao pd;
	@Override
	public Map<String, Object> findPageObjects(
			  String projectName,
			Integer pageCurrent ) {
		//1.验证参数的合法性
		if(pageCurrent==null||pageCurrent<1){
			throw new ServiceConfigurationError(
					"数据不合法:pageCurrent="+pageCurrent);
		}
		//2.计算startIndex的值
		int pageSize=2;
		int startIndex=(pageCurrent-1)*pageSize;
		//3.获取记录总数，然后计算总页数
		int rowCount=d.getRowCount(projectName);
		int pageCount=rowCount/pageSize;
		if(rowCount%pageSize!=0){
			pageCount++;
		}
		//4.通过PageObject封装分页信息
		PageObject po=new PageObject();
		po.setPageCount(pageCount);
		po.setPageCurrent(pageCurrent);
		po.setPageSize(pageSize);
		po.setRowCount(rowCount);
		po.setStartIndex(startIndex);
		//5.分页当前页数据
		List<Map<String,Object>>	list=
				d.findPageObjects(projectName,startIndex,pageSize);
		//6.封装分页数据和当前数据
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("list", list);
		map.put("po", po);
		return map;
	}
	@Override
	public void saveObject(Team entity) {
		if(entity==null){
			throw new ServiceException("保存数据不能为空");
		}
		int row=d.insertObject(entity);
		if(row!=1){
			throw new ServiceException("insert error");
		}
	}
	/**获取有效项目的id和name*/
	@Override
	public List<IdName> findIdAndNames() {
		return pd.findIdAndNames();
	}
	
}
