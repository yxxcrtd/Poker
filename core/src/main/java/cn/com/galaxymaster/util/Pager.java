package cn.com.galaxymaster.util;

/**
 * Pager
 */
public class Pager {

	private int totalCount;

	private int pageSize = 20;

	private int pageNo = 1;

	private String pageUrl = "?p={p}";

	public Pager() {
		pageNo = 1;
		pageSize = 10;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNo() {
		if (pageNo > getTotalPageCount()) {
			return getTotalPageCount();
		}
		return pageNo;
	}
	
	public int getTotalPageCount() {
		if (0 >= totalCount) {
			return 0;
		}
		if (0 >= pageSize) {
			return 0;
		}
		int i = totalCount / pageSize;
		
		if ( 0 != (totalCount % pageSize)) {
			i++;
		}
		return i;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public String getPageUrl() {
		return pageUrl;
	}

	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}
	
	public int getOffset() {
		return (pageNo - 1) * pageSize;
	}

}
