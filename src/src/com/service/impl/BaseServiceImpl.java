package com.service.impl;

import java.util.List;

import javax.sql.DataSource;

import com.dao.BaseDao;
import com.entity.OldAccountingVoucher;
import com.service.BaseService;

public class BaseServiceImpl implements BaseService {
	
	private BaseDao baseDao;
	
	@Override
	public boolean save(Object obj) throws Exception {
		// TODO Auto-generated method stub
		return baseDao.save(obj);
	}
	
	@Override
	public boolean saveOrUpdate(Object obj) throws Exception {
		return baseDao.saveOrUpdate(obj);
	}
	
	@Override
	public <T> boolean batchInsert(String hql,List<T> list) throws Exception {
		return baseDao.batchInsert(hql,list);
	}
 
	@Override
	public boolean zxSql(String sql) throws Exception {
		return baseDao.zxSql(sql);
	}

	@Override
	public <T> List<T> findObjList(String hql) throws Exception {
		return baseDao.findObjList(hql);
	}

	@Override
	public <T> List<T> getSqlList(String sql, String[] title) throws Exception {
		return baseDao.getSqlList(sql, title);
	}
	
	@Override
	public Integer getCountBySql(String sql) throws Exception {
		return baseDao.getCountBySql(sql);
	}


/*-----------------------------gettings and settings方法 ------------------=---------*/
	public BaseDao getBaseDao() {
		return baseDao;
	}
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	
}
