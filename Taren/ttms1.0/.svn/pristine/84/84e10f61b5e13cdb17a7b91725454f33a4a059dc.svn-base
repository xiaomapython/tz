package cn.tedu.ttms.attachement.controller;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.tedu.ttms.attachement.entity.Attachement;
import cn.tedu.ttms.attachement.service.AttachementService;
import cn.tedu.ttms.common.web.JsonResult;

@Controller
@RequestMapping("/attachement/")
public class AttachementController {
	@Resource
	private AttachementService attachementService;
    @RequestMapping("attachementUI")
	public String attachmentUI(){
		return "attachement/attachement";
	}
    /**
     * @param title 为附件标题
     * @param mFile 用于接收上传的附件的对象
     * */
    @RequestMapping("doUpload")
    @ResponseBody
    public JsonResult doUpload(
    		String title,
    		MultipartFile mFile){
    	//原有内容是练习上传,业务要写到service
    	attachementService
    	.uploadObject(title,mFile);
    	return new JsonResult();
    }
    @RequestMapping("doDownload")
    @ResponseBody
    public byte[] doDownload(Integer id,
    	HttpServletResponse response)throws IOException{
    	//1.根据id执行查找操作
    	Attachement a=
    	attachementService.findObjectById(id);
    	//2.设置下载内容类型以及响应头(固定格式)
    	response.setContentType(
    	"appliction/octet-stream");
		response.setHeader(
		"Content-disposition",
		"attachment;filename="+a.getFileName());
		//3.获得指定文件的路径对象(java.nio.Path)
        Path path=Paths.get(a.getFilePath());
        //4.读取path路径对应的文件,并返回字节数组
    	return Files.readAllBytes(path);
    }
    
    /**获得所有的附件信息*/
    @RequestMapping("doFindObjects")
    @ResponseBody
    public JsonResult doFindObjects(){
    	List<Attachement> list=
    	attachementService.findObjects();
    	return new JsonResult(list);
    } 
}
