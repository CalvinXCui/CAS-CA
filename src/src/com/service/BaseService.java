package com.service;

import java.util.List;

import com.entity.OldAccountingVoucher;

public interface BaseService {
	
	/**
	 * 添加方法
	 * @param obj
	 * @return
	 */
	public boolean save(Object obj) throws Exception;
	
	/**
	 * 添加或修改方法，数据库无id添加有id修改
	 * @param obj
	 * @return
	 */
	public boolean saveOrUpdate(Object obj) throws Exception;
	
	/**
	 * 执行sql语句，用于修改部分字段与删除
	 * @param sql
	 * @return
	 */
	public boolean zxSql(String sql) throws Exception;
	
	/**
	 * 根据hql查询实体类对象列表
	 * @param <T>
	 * @param <T>
	 * @param hql
	 * @return
	 */
	public  <T> List<T>  findObjList(String hql) throws Exception;
	
	/**
	 * 根据
	 * @param sql
	 * @param title
	 * @return
	 * @throws Exception
	 */
	public <T> List<T> getSqlList(String sql, String[] title)throws Exception;
	
	/**
	 * 查询多少总个数
	 * @param sql
	 * @return
	 */
	public Integer getCountBySql(String sql)throws Exception;

	<T> boolean batchInsert(String hql,List<T> list) throws Exception;
	
}
