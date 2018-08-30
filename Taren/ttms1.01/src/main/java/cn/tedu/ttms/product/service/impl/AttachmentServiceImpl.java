package cn.tedu.ttms.product.service.impl;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import cn.tedu.ttms.common.web.PageObject;
import cn.tedu.ttms.common.web.ServiceException;
import cn.tedu.ttms.product.dao.AttachmentDao;
import cn.tedu.ttms.product.entity.Attachment;
import cn.tedu.ttms.product.service.AttachmentService;
@Service
public class AttachmentServiceImpl implements AttachmentService {
	@Autowired
	private AttachmentDao attachmentDao;
	
	@Override
	public Attachment findObjectByDigest(String digest) {
		if(StringUtils.isEmpty(digest))
		throw new ServiceException("参数不能为空");
		Attachment a=attachmentDao.findObjectByDigest(digest);
		if(a==null)
		throw new ServiceException("对象已经不存在");
		return a;
	}
	@Override
	public void uploadObject(String title,
			MultipartFile mFile) {
	  //1.判定参数的有效性
	  if(StringUtils.isEmpty(title))
	  throw new ServiceException("标题不能为空");
	  if(mFile==null||mFile.isEmpty())
	  throw new ServiceException("文件对象不能为空");
	  //2.计算文件的摘要信息
	  String digest=null;
	  try{
	  //2.1获取文件内容
	  byte[] content=mFile.getBytes();
	  //2.3对文件内容进行MD5加密,获取摘要信息
	  //byte[] buf=DigestUtils.md5Digest(content);
	  //System.out.println(Arrays.toString(buf));
	  digest=
	  DigestUtils.md5DigestAsHex(content);
	  System.out.println(digest);
	  }catch(IOException e){
	  e.printStackTrace();
	  throw new ServiceException("读取文件内容失败");
	  }
	  //3.根据摘要信息查询数据库,判定有没有此文件
	  Attachment attachment=
	  attachmentDao.findObjectByDigest(digest);
	  System.out.println("attachment="+attachment);
	  if(attachment!=null)
	  throw new ServiceException("文件已上传");
	  //4.假如服务端没有此文件,执行上传动作
	  //d:/upload/2017/09/14/文件名.后缀名
	  //构建一个文件上传目录
	  SimpleDateFormat sdf=
	  new SimpleDateFormat("yyyy/MM/dd");
	  String destDir="d:/upload/"+sdf.format(new Date());
	  //构建一个随机字符串作为文件名
	  String newName=UUID.randomUUID().toString();
	  //获得文件名后缀,构建新文件名
	  String fileName=mFile.getOriginalFilename();
	  System.out.println("fileName="+fileName);
	  String ext=
	  fileName.substring(fileName.lastIndexOf("."));
	  String destFileName=newName+ext;
	  //创建目标文件对象
	  File dest=new File(destDir,destFileName);
	  File parent=dest.getParentFile();
	  if(!parent.exists()){
		  parent.mkdirs();
	  }
	  //上传文件(transfertTo方法用于实现上传)
	  try{
	  mFile.transferTo(dest);
	  }catch(IOException e){
	  e.printStackTrace();
	  throw new ServiceException("文件上传失败");  
	  }
	  //5.将文件信息写到数据库中(例如名字,路径,摘要)
	  Attachment entity=new Attachment();
	  entity.setTitle(title);
	  entity.setFileName(mFile.getOriginalFilename());
	  entity.setFilePath(dest.getAbsolutePath());
	  System.out.println("mFile.getContentType()="+mFile.getContentType());
	  entity.setContentType(mFile.getContentType());
	  entity.setFileDisgest(digest);
	  //........
	  //entity.setModifiedUser(modifiedUser);
	  int rows=attachmentDao.insertObject(entity);
	  if(rows!=1)throw new ServiceException("insert error");
	}
	@Override
	public Map<String, Object> findPageObject(Integer pageCurrent) {
		//1.验证参数的有效性
		if(pageCurrent==null||pageCurrent<1)
		throw new ServiceException("当前页的值无效");
		//2.计算startIndex
		int pageSize=3;
		int startIndex=(pageCurrent-1)*pageSize;
		//3.获得总记录数,计算总页数
		int rowCount=attachmentDao.getRowCount();
		int pageCount=rowCount/pageSize;
		if(rowCount%pageSize!=0){
			pageCount++;
		}
		//4.封装分页信息(PageObject)
		PageObject pageObject=new PageObject();
		pageObject.setPageCount(pageCount);
		pageObject.setPageCurrent(pageCurrent);
		pageObject.setPageSize(pageSize);
		pageObject.setRowCount(rowCount);
		pageObject.setStartIndex(startIndex);
		//5.查询当前页数据(List<Attachment>)
		List<Attachment> list=
		attachmentDao.
		findPageObjects(startIndex,pageSize);
		//6.封装分页及当前页数据
		Map<String,Object> map=
	    new HashMap<String,Object>();
		map.put("list", list);
		map.put("pageObject", pageObject);
		return map;
	}
	
	
	
}






