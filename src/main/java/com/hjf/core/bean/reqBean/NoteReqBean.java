package com.hjf.core.bean.reqBean;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.hjf.core.bean.BaseReqBean;

public class NoteReqBean extends BaseReqBean {
	private Logger  log = Logger.getLogger("h"); 
	private Integer noteId;//笔记编号
	private String title  ;//笔记标题
	private String content ;//笔记内容
	/**
	 * 【查询笔记评论列表】
	 */
	public boolean checkDetailParams(HttpServletRequest request) {
		String noteId    =request.getParameter("noteId");
		if (StringUtils.isBlank(noteId)) {
			log.error("【查询笔记评论列表】...参数检测  缺失参数noteId");
			return false;
		}	
		setNoteId(new Integer(noteId));
		return true;
	}
	/**
	 * 【查询笔记评论列表】
	 */
	public boolean checkCommentParams(HttpServletRequest request) {
		String noteId    =request.getParameter("noteId");
		if (StringUtils.isBlank(noteId)) {
			log.error("【查询笔记评论列表】...参数检测  缺失参数noteId");
			return false;
		}	
		setNoteId(new Integer(noteId));
		return true;
	}	
	/**
	 * 【添加笔记】
	 */
	public boolean checkAddNoteParams(HttpServletRequest request) {
		String title      =request.getParameter("title");
		String content    =request.getParameter("content");
		if (StringUtils.isBlank(title)) {
			log.error("【添加笔记】...参数检测  缺失参数title");
			return false;
		}
		
		if (StringUtils.isBlank(content)) {
			log.error("【添加笔记】...参数检测  缺失参数content");
			return false;
		}	
		setContent(content);
		setTitle(title);
		return true;
	}
	public Integer getNoteId() {
		return noteId;
	}


	public void setNoteId(Integer noteId) {
		this.noteId = noteId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

  


	 
	
	
	
}
