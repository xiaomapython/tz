package cn.tedu.ttms.product.service;

import java.util.Map;

import cn.tedu.ttms.product.entity.Project;

public interface ProjectService {
	Map<String,Object> findPageObjects(
			                             String name,
			                             Integer valid,
			                             Integer pageCurrent);
	void getValidById(String checkedIds,Integer valid);
/*3.-------------------------------------------------------------------------------*/
	void saveObject(Project entity);
/*44444444444444444444444444444444444444444444444444*/
	Project findObjectById(Integer id);
/*55555555555555555555555555555555555555555555555555555*/
	void updateObjectById(Project entity);
}
