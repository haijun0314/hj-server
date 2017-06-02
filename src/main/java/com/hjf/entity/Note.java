package com.hjf.entity;

import java.io.Serializable;

import org.jxjz.base.model.BaseModel;
/**
 * 笔记实体类
 * author lihaijun
 * createTime   2014-11-21
 */
public class Note extends BaseModel implements Serializable  {
	private Integer accountId;	//用户编号
	private Integer noteId;		//笔记编号
	private String  content;	//笔记内容
	private String  title;		//标题
	private String  photos;		//图片 ,分割
	private String  noteType;  //笔记类型 0 图片  1  文字
	private Integer babyId;		//宝宝编号
	private Integer commentCount;//评论数量				 
	private Integer praiseCount;//点赞数量
	
	
	 
	public Integer getAccountId() {
		return accountId;
	}
	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}
	public Integer getNoteId() {
		return noteId;
	}
	public void setNoteId(Integer noteId) {
		this.noteId = noteId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPhotos() {
		return photos;
	}
	public void setPhotos(String photos) {
		this.photos = photos;
	}
	public String getNoteType() {
		return noteType;
	}
	public void setNoteType(String noteType) {
		this.noteType = noteType;
	}
	public Integer getBabyId() {
		return babyId;
	}
	public void setBabyId(Integer babyId) {
		this.babyId = babyId;
	}
	 
	public Integer getCommentCount() {
		return commentCount;
	}
	public void setCommentCount(Integer commentCount) {
		this.commentCount = commentCount;
	}
	public Integer getPraiseCount() {
		return praiseCount;
	}
	public void setPraiseCount(Integer praiseCount) {
		this.praiseCount = praiseCount;
	}
	
	
	
	
}