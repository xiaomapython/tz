package cn.tedu.ttms.product.service;

import java.util.List;
import java.util.Map;

import cn.tedu.ttms.product.entity.ProductType;

public interface ProductTypeService {
	List<Map<String, Object>> findGridTreeNodes();
	void deleteObject(Integer id);
	void saveObject(ProductType entity);
	List<Map<String,Object>> findZTreeNodes();
	Map<String,Object> findObjectById(Integer id);
	void updateObject(ProductType entity);
}
