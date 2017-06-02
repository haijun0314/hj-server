package com.hjf.dao;

import org.jxjz.base.mybatis.BaseIbatisDAO;
import org.springframework.stereotype.Repository;

import com.hjf.entity.SysLogClient;

 
@Repository
public class SysLogClientDAO extends BaseIbatisDAO{
	public Class getEntityClass() {
		return  SysLogClient.class;
	}	 
	
	 
 
	 
}