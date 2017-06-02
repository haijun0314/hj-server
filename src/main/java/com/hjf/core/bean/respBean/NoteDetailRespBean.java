package com.hjf.core.bean.respBean;

import com.hjf.core.bean.BaseRespBean;
/**
 * 笔记详情
 * author lihaijun
 * createTime   2014-11-21
 */
public class NoteDetailRespBean extends   BaseRespBean {
	private Integer customerId;	//用户编号
	private Integer noteId;		//笔记编号
	private String  content;	//笔记内容
	private String  title;		//标题
	private String  photos;		//图片 ,分割
	private String  noteType;   //笔记类型 0 图片  1  文字
	private String  customerName;//用户名称
	private String  headPic;	//头像地址
	private String  babyName;	//头像地址
	private String  babyAge;    //发布时间
	private String  createdTime;    //发布时间
	private Integer commentCount;//评论数量				 
	private Integer praiseCount;//点赞数量
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
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
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getHeadPic() {
		return headPic;
	}
	public void setHeadPic(String headPic) {
		this.headPic = headPic;
	}
	public String getBabyName() {
		return babyName;
	}
	public void setBabyName(String babyName) {
		this.babyName = babyName;
	}
	public String getBabyAge() {
		return babyAge;
	}
	public void setBabyAge(String babyAge) {
		this.babyAge = babyAge;
	}
	public String getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
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