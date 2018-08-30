package cn.tedu.ttms.product.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.sun.xml.internal.messaging.saaj.packaging.mime.internet.MimeUtility;

import cn.tedu.ttms.common.web.JsonResult;
import cn.tedu.ttms.product.entity.Attachment;
import cn.tedu.ttms.product.service.AttachmentService;

@Controller
@RequestMapping("/attachment/")
public class AttachmentController {
	@Autowired
	private AttachmentService attachmentService;
	@RequestMapping("listUI")
	public String listUI(){
		return "attachment/attachment";
	}
	/**
	 * @param mFile 用于接收上传的文件对象
	 * */
	@RequestMapping("doUpload")
	@ResponseBody
	public JsonResult doUpload(
			String title,
			MultipartFile mFile){
		attachmentService.uploadObject(title, mFile);
		System.out.println("==doUpload===");
		return new JsonResult("upload ok");
	}
	@RequestMapping("doDownload")
	@ResponseBody
	public byte[] doDownload(String digest,
			HttpServletResponse response)
	        throws IOException{
		//1.根据摘要获取对象
		Attachment a=
		attachmentService
		.findObjectByDigest(digest);
		//2.设置响应内容类型及响应头信息
    	response.setContentType(
    	"application/octet-stream");
    	System.out.println("file.name="+a.getFileName());
		//String fileName=new String(a.getFileName().getBytes("UTF-8"),"iso-8859-1");
    	//String fileName=URLEncoder.encode(a.getFileName(), "UTF-8");
		String fileName=MimeUtility.encodeWord(a.getFileName());
    	response.setHeader(
		"Content-disposition",
		"attachment;filename="+fileName);
		//3.获取文件字节,并返回
		//3.1根据文件路径获得一个path对象
		Path path=Paths.get(a.getFilePath());
		//3.2通过Files类读取path对象对应的字节
		return Files.readAllBytes(path);
	}
	@RequestMapping("doFindPageObjects")
	@ResponseBody
	public JsonResult doFindPageObjects(
			Integer pageCurrent){
		  Map<String,Object> map=
		  attachmentService
		 .findPageObject(pageCurrent);
		 return new JsonResult(map);
	}
	
}





