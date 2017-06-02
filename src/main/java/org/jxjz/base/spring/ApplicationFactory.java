package org.jxjz.base.spring;

import javax.sql.DataSource;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.MessageSourceAccessor;
public class ApplicationFactory implements ApplicationContextAware {
	private static ApplicationContext context;//声明一个静态变量保存   
	private static MessageSourceAccessor message = null;

	@SuppressWarnings("static-access")
	public void setApplicationContext(ApplicationContext contex)
			throws BeansException {
		this.context = contex;
	}

	public static ApplicationContext getContext() {
		return context;
	}
	/**
	 * 取得资源文件
	 * @return
	 */
	public static MessageSourceAccessor getMessageSourceAccessor() {
		if (message == null) {
			message = new MessageSourceAccessor(getContext());
		}
		return message;
	}
	/**
	 * 取得数据库连接
	 */
	public static DataSource getDataSource() {
		return (DataSource) getContext().getBean("jdbcdataSource");
	}
	/**
	 * 取得spring bean
	 * @param serviceId
	 * @return
	 */
	public static Object getBean(String serviceId) {
		return getContext().getBean(serviceId);
	}
}
