package com.hjf.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hjf.dao.SysLogClientDAO;
import com.hjf.entity.SysLogClient;
import com.hjf.service.SysLogClientService;

/**
 * 系统管理--日志管理
 * @author lihaijun
 */
@Service("clientLogService")
@Transactional
public class SysLogClientServiceImpl  extends BaseService implements SysLogClientService {
	@Resource SysLogClientDAO clientLogDAO;
	private List logList=new ArrayList();
	/**
	 * 增加功能
	 */
	public void addClientLog(SysLogClient clientLog){
		clientLogDAO.save(clientLog);
	}
	
	/**
	 * 增加客户端日志到缓存
	 */
	public void addCacheLog(SysLogClient clientLog){
		this.logList.add(clientLog);
	}	
	/**
	 * 将缓存日志输入持久化到数据库
	 */
	public void persistenceCacheLog(){
		if (this.logList==null||this.logList.size()<1)return;
		for (int i = 0; i < logList.size(); i++) {
			SysLogClient clientLog=(SysLogClient)logList.get(i);
			log.info("添加客户端日志"+clientLog.getContent());
			addClientLog(clientLog);
		}
		logList.clear();
	}

	 
}
