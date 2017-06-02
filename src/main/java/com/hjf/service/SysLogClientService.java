package com.hjf.service;

import com.hjf.entity.SysLogClient;

public interface SysLogClientService {
	 
	//增加系统日志到缓存
	public void addCacheLog(SysLogClient clientLog);
	//将缓存日志输入持久化到数据库
	public void persistenceCacheLog();
}
