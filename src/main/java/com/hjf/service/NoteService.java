package com.hjf.service;

import org.jxjz.base.model.PageBean;

import com.hjf.core.bean.BaseRespBean;
import com.hjf.core.bean.reqBean.NoteReqBean;
import com.hjf.core.bean.respBean.NoteDetailRespBean;

public interface NoteService {
	
	
	//【添加笔记】
	public BaseRespBean addNote(NoteReqBean q,BaseRespBean r);		 
	//【笔记详情】
	public NoteDetailRespBean detail(NoteReqBean q,NoteDetailRespBean r);	
	//【查询笔记列表】【主页】
	public BaseRespBean noteList(PageBean  		  pb,BaseRespBean r);	
	
}
