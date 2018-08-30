package cn.tedu.ttms.common.web;
//值对象(VO:ValueObject)
public class PageObject {
	/**当前页*/
	private Integer pageCurrent;
	/**起始页*/
	private Integer startIndex;
	/**总记录数*/
	private Integer rowCount;
	/**总页数*/
	private Integer pageCount;
	/**页面大小(每页显示多少条数据)*/
	private Integer pageSize;
	
	public Integer getPageCurrent() {
		return pageCurrent;
	}
	public void setPageCurrent(Integer pageCurrent) {
		this.pageCurrent = pageCurrent;
	}
	public Integer getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(Integer startIndex) {
		this.startIndex = startIndex;
	}
	public Integer getRowCount() {
		return rowCount;
	}
	public void setRowCount(Integer rowCount) {
		this.rowCount = rowCount;
	}
	public Integer getPageCount() {
		return pageCount;
	}
	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	
}
