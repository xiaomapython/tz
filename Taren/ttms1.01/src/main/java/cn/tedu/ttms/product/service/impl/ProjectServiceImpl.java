package cn.tedu.ttms.product.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import cn.tedu.ttms.common.web.PageObject;
import cn.tedu.ttms.common.web.ServiceException;
import cn.tedu.ttms.product.dao.ProjectDao;
import cn.tedu.ttms.product.entity.Project;
import cn.tedu.ttms.product.service.ProjectService;
/**
 * 在实际项目中要通过业务层对象处理具体业务
 * 比如：
 * 1）业务数据验证
 * 2）事务的处理
 * 3）缓存的操作
 * 4）日志操作
 * @author Administrator
 *
 */
/*@Transactional(rollbackFor=ServiceException.class,
                            propagation=Propagation.REQUIRED)*/
@Service
public class ProjectServiceImpl implements ProjectService {
	/*@Resource(默认先按名字匹配，
	 * 名字没找到按类型)
	 * @Autowired(默认按类型匹配,
	 * 假如需要按名字需要借助@Qualifier注解)
	 */
	@Autowired
     private ProjectDao d;
	/**
	 * 通过此方法进行分页查询
	 * @Param name页面输入的项目名
	 * @Param valid 页面输入的启用禁用状态
	 * @pageCurrent 表示页面传入的当前页面
	 */
	@Override
	public Map<String,Object> findPageObjects(
			  String name,
              Integer valid,
			Integer pageCurrent) {
		//对数据进行业务验证
		if(valid!=null&&valid!=1&&valid!=0){
			throw new ServiceException("valid值不正确");
		}
		//定义pageSize(每页显示多少条)
		int pageSize=8;
		/*
		 * 定义pageSize,此值会经过计算获得
		 * 1)pageCurrent(当前页,此值从页面传递)
		 * 2)pageSize(每页显示多少条)
		 */
		int startIndex=(pageCurrent-1)*pageSize;
		//获得总记录数
		int rowCount=d.getRowCount(name, valid);
		//根据总记录数以及pagSize计算总页数
		int pageCount = rowCount/pageSize;
		if(rowCount%pageSize!=0){
			pageCount++;
		}
		//将分页信息封装到PageObject对象(参考ttms1.0)
		PageObject po=new PageObject();
		po.setPageCurrent(pageCurrent);
		po.setPageCount(pageCount);
		po.setPageSize(pageSize);
		po.setStartIndex(startIndex);
		po.setRowCount(rowCount);
		//获得当前页数据
	    List<Project> list=d.findPageObjects(name, valid,startIndex,pageSize);
		//将分页信息和每页要显示的数据封装到Map，一起返回
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("list",list);
		map.put("po",po);
		//return 下一步操作是返回分页信息和当前数据
		return map;
	}
/*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
	@Override
	public void getValidById(String checkedIds, Integer valid) {
		System.out.println("非业层checkedIds="+checkedIds);
		System.out.println("非业层valid="+valid);
		//1.对业务验证
		if(checkedIds==null||checkedIds.trim().length()==0){
			throw new ServiceException("请选择修改对象");
		}
		if(valid!=0&&valid!=1){
			throw new ServiceException("valid值无效");
		}
		//2.转化业务数据
		String[] ids=checkedIds.split(",");
		System.out.println("ids="+ids[0]);
		//3.执行修改动作(返回值row表示修改的行数)
		int row=d.validById(ids, valid);
		if(row==-1)
			throw new ServiceException("UPDATE error");
		System.out.println("更新记录数="+row);
	}
/*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
	/**保存项目的信息*/
	@Override
	public void saveObject(Project entity) {
		if(entity==null){
			throw new ServiceException("保存数据不能为空");
		}
		int row=d.insertObject(entity);
		if(row!=1)
			 throw new ServiceException("插入错误");
		System.out.println("插入行数row="+row);
	}
 /*44444444444444444444444444444444444444444444444*/
	/**
	 * */
	/*@Transactional(readOnly=true)*/
	@Override
	public Project findObjectById(Integer id) {
		if(id==null||id<1){
			throw new ServiceException("数据不合法");
		}
		Project project=d.findObjectById(id);
		if(project==null){
			throw new ServiceException("该数据不存在");
		}
		return project;
	}
/*555555555555555555555555555555555555555555555555555*/
	/*@Transactional(
			rollbackFor=ServiceException.class,
			//防止脏读，默认为READ_COMMITTED
			isolation=Isolation.READ_COMMITTED
		)
		*/	
	@Override
	public void updateObjectById(Project entity) {
		if(entity==null){
			throw new ServiceException("保存对象不能为空");
		}
		Integer id=entity.getId();
		findObjectById(id);
		int row=d.updateObjectById(entity);
		if(row!=1){
			throw new ServiceException("更改失败");
		}
		System.out.println("通过id更新行数="+row);
	}    
}
