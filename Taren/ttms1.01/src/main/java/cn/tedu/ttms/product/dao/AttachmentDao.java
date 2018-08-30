package cn.tedu.ttms.product.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.tedu.ttms.common.dao.BaseDao;
import cn.tedu.ttms.product.entity.Attachment;
/**AttachmentDao(负责写入和查询附件信息)*/
public interface AttachmentDao 
       extends BaseDao<Attachment>{
      //int insertObject(Attachment attach);
	Attachment findObjectByDigest(
			String digest);
	List<Attachment> 
	findPageObjects(
	 @Param("startIndex")Integer startIndex,
	 @Param("pageSize")Integer pageSize);
	int getRowCount();
	
	
}
