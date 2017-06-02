package com.hjf.dao;

import org.jxjz.base.mybatis.BaseIbatisDAO;
import org.springframework.stereotype.Repository;

import com.hjf.entity.Note;



/**
 * 账户系统
 * author lihaijun
 * createTime   2014-12-01
 */
@Repository
public class NoteDAO extends BaseIbatisDAO{
	
	 
	
	@Override
	public Class getEntityClass() {
		return Note.class;
	}
	   
	 
}