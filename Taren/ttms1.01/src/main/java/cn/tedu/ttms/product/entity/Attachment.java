package cn.tedu.ttms.product.entity;

import java.io.Serializable;
import java.util.Date;
public class Attachment implements Serializable{
	private static final long serialVersionUID = -6892511084046690376L;
    private Integer id;
    private String fileName;
    private String title;
    /**文件内容类型*/
    private String contentType;
    private String filePath;
    /**文件摘要信息*/
    private String fileDisgest;
    /**附件归属类型(产品-1,分销商-2,...)*/
    private Integer athType;
    /**附件具体归属哪个对象*/
    private Integer belongId;
    private Date createdTime;
    private Date modifiedTime;
    private String createdUser;
    private String modifiedUser;
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public String getFileDisgest() {
		return fileDisgest;
	}
	public void setFileDisgest(String fileDisgest) {
		this.fileDisgest = fileDisgest;
	}
	public Integer getAthType() {
		return athType;
	}
	public void setAthType(Integer athType) {
		this.athType = athType;
	}
	public Integer getBelongId() {
		return belongId;
	}
	public void setBelongId(Integer belongId) {
		this.belongId = belongId;
	}
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	public Date getModifiedTime() {
		return modifiedTime;
	}
	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}
	public String getCreatedUser() {
		return createdUser;
	}
	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}
	public String getModifiedUser() {
		return modifiedUser;
	}
	public void setModifiedUser(String modifiedUser) {
		this.modifiedUser = modifiedUser;
	}
    
}
