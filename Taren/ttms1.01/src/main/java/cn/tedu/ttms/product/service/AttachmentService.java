package cn.tedu.ttms.product.service;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import cn.tedu.ttms.product.entity.Attachment;

public interface AttachmentService {
	 void uploadObject(String title,
			 MultipartFile mFile);
	 
	 Map<String,Object> 
	 findPageObject(Integer pageCurrent);
	 Attachment findObjectByDigest(
				String digest);
}
