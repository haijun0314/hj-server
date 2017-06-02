package org.jxjz.base.mybatis;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.jxjz.base.jdbc.JdbcTemplateEx;
import org.jxjz.base.model.BaseModel;
import org.jxjz.base.model.PageBean;
import org.jxjz.common.util.beans.Conversion;
import org.jxjz.framework.util.LogUtil;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;
/**
 * 【功能说明】：mybatis  通用处理基类
 * @author lihaijun
 * 【创建时间】2017-05-25
 */
@Repository  
public class BaseIbatisDAO {
	@Resource public SqlSessionTemplate sqlSessionTemplate; 
	@Resource public JdbcTemplateEx jdbcTemplateEx;
	
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {    
        this.sqlSessionTemplate = sqlSessionTemplate;    
    }   	
	
	
	public JdbcTemplateEx getJdbcTemplateEx() {
		return jdbcTemplateEx;
	}

	public void setJdbcTemplateEx(JdbcTemplateEx jdbcTemplateEx) {
		this.jdbcTemplateEx = jdbcTemplateEx;
	}
	private Logger log = LogUtil.getLogger();  
	
	
	public static final String POSTFIX_SAVE = ".insert";  
	public static final String POSTFIX_SAVE_WITHID = ".insertWithId";  
    public static final String POSTFIX_BATCH_UPDATE = ".batchUpdate";
    public static final String POSTFIX_DELETE_BYID = ".deleteById";  
    public static final String POSTFIX_BATCH_DELETE = ".batchDelete";
    public static final String POSTFIX_FINDBYID = ".findById";  
    public static final String POSTFIX_QUERYLIST = ".queryList"; 
    public static final String POSTFIX_QUERYEXPLIST = ".queryExpList"; 
    public static final String POSTFIX_FINDALL = ".findAll";  
    public static final String POSTFIX_UPDATEBYID = ".updateById"; 
    
    public static final String POSTFIX_QUERYCOUNT = ".queryCount"; 
    public static final String POSTFIX_QUERYPAGELIST = ".queryPagelist"; 
	/***************************删除操作****************************************************************************/	
    
    /**
	 *  删除 根据ID编号【整型】
	 */
	public int   delete(Object o ,String sqlId)  {
		 
		try {
			return sqlSessionTemplate.delete(this.getNamespace()+"."+sqlId, o);
		} catch (Exception e) {
			log.error("执行delete方法发生异常信息.....sqlId="+sqlId+e.getMessage());
			e.printStackTrace();
			 return -1;
		}
	}   
    
    /**
	 *  删除 根据ID编号【整型】
	 */
	public void  deleteByID(Integer int_id)  {
		String sqlId = this.getNamespace() + POSTFIX_DELETE_BYID;
		deleteByID(int_id, sqlId);
	}	
    
    /**
	 *  删除 根据ID编号【整型】
	 */
	public int   deleteByID(Integer int_id,String sqlId) {
		 return delete(int_id, sqlId);
	}	 
	/**
	 *  删除 根据ID编号【字符串】
	 */
	public int  deleteByID(String str_id,String sqlId)  {
		 return delete(str_id, sqlId);
	}
	
	/**
	 * 删除数据【批量删除】【整型】
	 */
	public void delete(Integer[] ids,String sql)   {
		if(ids==null||ids.length<1)return; 
		for (int i=0;i<ids.length;i++ ) {
			deleteByID(ids[i],sql);
		}
	}	
	/**
	 * 删除数据【批量删除】【字符串】
	 */
	public void delete(String[] ids,String sql)   {
		if(ids==null||ids.length<1)return; 
		for (int i=0;i<ids.length;i++ ) {
			deleteByID(ids[i],sql);
		}
	}	
	
	/***************************添加操作****************************************************************************/	
	
	/**
	 * 保存到数据库中
	 * @param obj对象
	 * @param sqlId sql语句的id
	 * @return 保存对象的id
	 */
	public int  saveNoSession(BaseModel obj ){
		try {
			obj.setCreatedTime(new Date());
			String  sqlId=this.getNamespace()+POSTFIX_SAVE ; 
			return save(obj, sqlId);
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	/**
	 * 保存到数据库中
	 * @param obj对象
	 * @param sqlId sql语句的id
	 * @return 保存对象的id
	 */
	public int  save(BaseModel obj ){
		try {
			obj.setCreatedTime(new Date());
			String  sqlId=this.getNamespace()+POSTFIX_SAVE ; 
			return save(obj, sqlId);
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}	
	
	/**
	 *  插入返回主键值
	 */
	public Integer  saveWithReturnId(BaseModel bean)  {
		 
		bean.setCreatedTime(new Date());
		String  sqlId=this.getNamespace()+POSTFIX_SAVE_WITHID ; 
		Integer id=(Integer)sqlSessionTemplate.insert(sqlId, bean);
		return id;
	}
	
	 
	
	/**
	 *  添加
	 */
	public int  save(Object bean,String sql)  {
		try {
			sqlSessionTemplate.insert(this.getNamespace()+"."+sql, bean);
		} catch (Exception e) {
			log.error("执行save方法发生异常信息....sqlId="+sql+e.getMessage());
			 return -1;
		}
		return 0;
	}		
	
	
	
	/**
	 * 添加数据【批量添加】
	 */
	public void save(Object[] beans,String sql)   {
		if(beans==null||beans.length<1)return; 
		for (int i=0;i<beans.length;i++ ) {
			save( beans[i], sql);
		}
	}	
/***************************更新操作****************************************************************************/	
	 
	/**
	 * 更新
	 */
	public int update(BaseModel bean,String sqlId)   {
		try {
			return sqlSessionTemplate.update(this.getNamespace()+"."+sqlId, bean);	
		} catch (Exception e) {
			log.error("执行update方法发生异常信息....sqlId="+sqlId+e.getMessage());
			e.getStackTrace();
			 return -1;
		}
	}
	/**
	 * 更新
	 */
	public int updateById(BaseModel bean)   {
		try {
			 
			bean.setUpdatedTime(new Date());
			String sqlId = this.getNamespace()+POSTFIX_UPDATEBYID ; 
			return update(bean, sqlId);	
		} catch (Exception e) {
			log.error("更新数据发生异常"+e.getMessage());
			e.fillInStackTrace();
			 return -1;
		}
	}
	/**
	 * 批量修改
	 * @param idList id集合
	 * @return 修改条数
	 * @
	 */
	public int batchUpdate(List<Integer> idList, Object obj){
		return this.batchUpdate(idList, null, obj);
	}	

	/**
	 * 批量修改
	 * @param idList id集合 
	 * @param sqlId sql语句的id
	 * @return 修改条数
	 */
	public int batchUpdate(List<Integer> idList, String sqlId, Object obj){
		if(sqlId == null){
			sqlId = this.getNamespace() + POSTFIX_BATCH_UPDATE;
		}
		Map<String, Object> map = null;
		try {
			map = Conversion.conversionToMapExcludeEmpty(obj);
		} catch (Exception e) {
			LogUtil.getLogger().error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
		map.put("idList", idList);
		return this.sqlSessionTemplate.update(sqlId, map);
	}	
	
	/***************************查询操作****************************************************************************/	
	
	/**
	 * 根据ID查询
	 */
	public Map getById(Serializable id,String sql)  {
		return (Map) sqlSessionTemplate.selectOne(this.getNamespace()+"."+sql, id);
	}
	/**
	 * 根据ID查询
	 */
	public Object getObjById(Serializable id,String sql) {
		return sqlSessionTemplate.selectOne(this.getNamespace()+"."+sql, id);
	}	
	/**
	 * 根据id查找对象， 
	 * @param id
	 * @return 对象
	 */
	public Object getObjById(Serializable id){
		if(id==null){
			return null;
		}
		String sqlId = this.getNamespace()+POSTFIX_FINDBYID ; 
		return getObjById(id, sqlId);
	}	
	/**
	 * 通用查询
	 * @param query 查询条件
	 * @return 对象
	 * @
	 */
	public List   queryList(Query query){
		String sqlId = this.getNamespace()+POSTFIX_QUERYLIST ; 
		return this.sqlSessionTemplate.selectList(sqlId, query);
	}	
	/**
	 * 通用查询
	 * @param query 查询条件
	 */
	public List   queryList(String sqlId,Query query){
		try {
			return this.sqlSessionTemplate.selectList(this.getNamespace()+"."+sqlId, query);
		} catch (Exception e) {
			log.error("执行queryList方法发生异常信息....sqlId="+sqlId+e.getMessage());
			 e.getMessage();
		}
		return null;
		
	}	
	/**
	 * 通用查询
	 * @param query 查询条件
	 */
	public List   queryList(String sqlId,Object o){
		return this.sqlSessionTemplate.selectList(this.getNamespace()+"."+sqlId, o);
	}	
	
	/**
	 * 通用查询
	 * @param query 查询条件
	 */
	public List   queryList(Object o){
		String sqlId = this.getNamespace()+POSTFIX_QUERYLIST ; 
		return this.sqlSessionTemplate.selectList(sqlId, o);
	}
	
	
	
	/**
	 * 分页查询 pm存放分页参数和查询条件
	 */
	public PageBean queryPageList(PageBean pm) {
		String  listSqlId = this.getNamespace()+POSTFIX_QUERYPAGELIST;
		List datas=this.sqlSessionTemplate.selectList(listSqlId, pm);
		pm.setDatas(datas);
		return pm;	
	}	
	
	/**
	 * 分页查询 pm存放分页参数和查询条件
	 */
	public PageBean queryPageList(PageBean pm,String  sqlId) {
		List datas=this.sqlSessionTemplate.selectList(this.getNamespace()+"."+sqlId, pm);
		pm.setDatas(datas);
		return pm;	
	}		
	
	
 

	
	 
	/**
	 * 查询所有数据【返回list】
	 */
	public List findAll()   {
		String sqlId=this.getNamespace()+POSTFIX_FINDALL ; 
		return sqlSessionTemplate.selectList(sqlId);
	}
 
	
	public Class getEntityClass() {
		return null;
	}	
	
	
	private String getNamespace(){
		Class<?> clazz = getEntityClass() ;
		if(clazz == null){
			return null;
		}
		return clazz.getSimpleName();
	}
}
