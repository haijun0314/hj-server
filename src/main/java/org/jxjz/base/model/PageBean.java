package org.jxjz.base.model;
import java.util.HashMap;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

/**
 * 【功能说明】： 分页工具类
 * @author lihaijun
 * 【创建时间】2017-05-25
 */
public class PageBean {

	private  Integer startRow;
	private  Integer endRow;
	private  Integer pageSize;
	private  List datas;
	private Map<String, Object> params = new HashMap<String, Object>();

	public PageBean (HttpServletRequest request) {
		initPage(request);
	}
	/**
	 * 单条加入查询条件
	 * @param key 字段名称
	 * @param value 值
	 * @return 查询条件
	 */
	public void append(String key, Object value) {
		params.put(key, value);
		 
	}	
	 
	public  void initPage(HttpServletRequest request){
		String pageNum=request.getParameter("page");
		String pageSize=request.getParameter("pageSize");
	    if(StringUtils.isBlank(pageNum)){
	    	pageNum="0";
	    }
	    if(StringUtils.isBlank(pageSize)){
	    	pageSize="10";
	    }
	    int page=Integer.parseInt(pageNum);
		int rp=Integer.parseInt(pageSize);
	    int start=(page)*rp;
		int end=start+rp-1;
		setStartRow(start);
		setEndRow(end);
		setPageSize(rp);
		 
}

	
	public Map<String, Object> getParams() {
		return params;
	}
	public void setParams(Map<String, Object> params) {
		this.params = params;
	}
	public Integer getStartRow() {
		return startRow;
	}
	public void setStartRow(Integer startRow) {
		this.startRow = startRow;
	}
	public Integer getEndRow() {
		return endRow;
	}
	public void setEndRow(Integer endRow) {
		this.endRow = endRow;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public List getDatas() {
		return datas;
	}
	public void setDatas(List datas) {
		this.datas = datas;
	}

	

	
 
	 
}
