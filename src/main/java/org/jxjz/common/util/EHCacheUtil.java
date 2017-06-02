package org.jxjz.common.util;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class EHCacheUtil {

	static CacheManager cacheManager=null;
	public static CacheManager getCacheManager () {
		if ( cacheManager==null) {
			cacheManager= CacheManager.create();
		}
		return cacheManager;
	}
	/**
	 * ��ȡ����
	 * @param cacheName
	 */
	public static Cache  getCache(String cacheName) {
		Cache cache = getCacheManager().getCache(cacheName);
		return cache;
	}
	/**
	 * ��ӻ���
	 * @param cacheName
	 */
	public static void addCache(String cacheName,Element element) {
		getCache(cacheName).put(element);
	}
	/**
	 * ��ӻ���
	 * @param cacheName
	 */
	public static void addCache(String cacheName,String key,String value) {
		Element element = new Element(key, value);
		getCache(cacheName).put(element);
	}
	/**
	 * ɾ���
	 * @param cacheName
	 */
	public static void removeCache(String cacheName,String key) {
		getCache(cacheName).remove(key);
	}
	/**
	 * �õ�����Ķ���������
	 * @param cacheName
	 */
	public static int getCacheSize(String cacheName) {
		return getCache(cacheName).getSize();
	}
	
	/**
	 * �õ��������ռ���ڴ������
	 * @param cacheName
	 */
	public static long getMemoryStoreSize(String cacheName) {
		return getCache(cacheName).getMemoryStoreSize();
	}
	/**
	 * �õ�����Զ���ռ�ô��̵�����
	 * @param cacheName
	 */
	public static long getDiskStoreSize(String cacheName) {
		return getCache(cacheName).getDiskStoreSize();
	}
	
	
 
	 
}
