package com.dao;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import com.entity.OldAccountingVoucher;

public interface BaseDao {
	
	/**
	 * 添加方法
	 * @param obj
	 * @return
	 */
	public boolean save(Object obj);
	
	/**
	 * 批量添加方法
	 * @param <T>
	 * @param obj
	 * @return
	 * @throws SQLException 
	 */
	public <T> boolean batchInsert(String hql,List<T> list) throws SQLException;
	
	/**
	 * 添加或修改方法，数据库无id添加有id修改
	 * @param obj
	 * @return
	 */
	public boolean saveOrUpdate(Object obj);
	
	/**
	 * 执行sql语句，用于修改部分字段与删除
	 * @param sql
	 * @return
	 */
	public boolean zxSql(String sql);
	
	/**
	 * 查询多少总个数
	 * @param sql
	 * @return
	 */
	public Integer getCountBySql(String sql);
	
	/**
	 * 根据hql查询实体类对象列表
	 * @param <T>
	 * @param hql
	 * @return
	 */
	public <T> List<T> findObjList(String hql)throws Exception;
	
	
	public <T> List<T> getSqlList(String sql, String[] title)throws Exception;

}
