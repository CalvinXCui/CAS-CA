package com.dao.impl;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.dao.BaseDao;
import com.entity.OldAccountingVoucher;

public class BaseDaoImpl extends HibernateDaoSupport implements BaseDao {
	
	DataSource dataSource;

	public boolean saveOrUpdate(Object obj) {
		Session session = getSession();
		Transaction ts = session.beginTransaction();
		try {
			session.saveOrUpdate(obj);
			ts.commit();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			ts.rollback();
			return false;
		} finally {
			session.clear();
			session.close();
		}
	}

	public boolean zxSql(String sql) {
		Session session = getSession();
		try {
			Query query = session.createSQLQuery(sql);
			query.executeUpdate();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.clear();
			session.close();
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> findObjList(String hql)throws Exception{
		Session session = getSession();
		try {
			Query query = session.createQuery(hql);
			return query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.clear();
			session.close();
		}
		return null;
	}
	
	

	/*@Override
	public <T> boolean batchInsert(String hql, List<T> list) throws SQLException {
			Session session = getSession();
			session.beginTransaction();
			T oldAccountingVoucher=null;
			try{
			for(int i=0;i<list.size();i++){
				oldAccountingVoucher=list.get(i);
				session.save(oldAccountingVoucher);
				if(i%100==0){
					session.flush();
					session.clear();
				}
			}
			session.getTransaction().commit();
			return true;
			} catch (Exception e) {
				e.printStackTrace(); // 打印错误信息
				session.getTransaction().rollback(); // 出错将回滚事物
			} finally {
				session.close();
			}
			Connection connection2=dataSource.getConnection();
			try {
				PreparedStatement stmt=(PreparedStatement) connection2.prepareStatement(hql);
				connection2.setAutoCommit(false);
				for(int i=0;i<list.size();i++){
					OldAccountingVoucher oav=(OldAccountingVoucher) list.get(i);
					stmt.setString(1, oav.getVDate());
					stmt.setString(2, oav.getVNo());
					stmt.setString(3, oav.getVAbstract());
					stmt.setString(4, oav.getVDebitAmount());
					stmt.setString(5, oav.getVCreditAmount());
					stmt.setString(6, oav.getTNo());
					stmt.setString(7, oav.getTName());
					stmt.setString(8, oav.getSNo());
					stmt.setString(9, oav.getSName());
					stmt.setString(10, oav.getTOrganization());
					stmt.addBatch(); //添加到批处理命令中
					if(i%100==0){
						stmt.executeBatch();
						connection2.commit();
					}
				}
				stmt.executeBatch();
				connection2.commit();
				return true;
			} catch (HibernateException e) {
				e.printStackTrace();
			} finally {
				session.clear();
				session.close();
			}
		return false;
	}*/



	@Override
	public <T> boolean batchInsert(String hql,List<T> list) throws SQLException {
		Session session = getSession();
		session.beginTransaction();
		T t=null;
		try{
		for(int i=0;i<list.size();i++){
			t=list.get(i);
			session.save(t);
			if(i%100==0){
				session.flush();
				session.clear();
			}
		}
		session.getTransaction().commit();
		return true;
		} catch (Exception e) {
			e.printStackTrace(); // 打印错误信息
			session.getTransaction().rollback(); // 出错将回滚事物
		} finally {
			session.close();
		}
		return false;
	}


	@SuppressWarnings("unchecked")
	public <T> List<T> selectDocumentConversion()throws Exception{
		Session session = getSession();
		try {
			String sql="SELECT dSubjectName,'贷方' AS TYPE ,dTopicName,dTopicNo FROM document_conversion WHERE dDebitAmount=0 GROUP BY dSubjectName " + 
					"   UNION ALL " + 
					"   SELECT dSubjectName ,'借方' AS TYPE,dTopicName,dTopicNo FROM document_conversion WHERE dCreditAmount=0 GROUP BY dSubjectName";
			SQLQuery query=session.createSQLQuery(sql).addScalar("dSubjectName", Hibernate.STRING)
			                                          .addScalar("TYPE", Hibernate.STRING)
			                                          .addScalar("dTopicName", Hibernate.STRING)
			                                          .addScalar("dTopicNo", Hibernate.STRING);
			return query.list();
		}  catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.clear();
			session.close();
		}
		return null;
	}
	
	/**
	 * 自己编写sql 返回对象
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> getSqlList(String sql, Class<T> classname)throws Exception{
		Session session = getSession();
		try {
			SQLQuery query=session.createSQLQuery(sql);
			
			return (List<T>) query.addEntity(classname.getClass());
		}  catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.clear();
			session.close();
		}
		return null;
		}

	/**
	 * 根据sql语句执行  返回obj数组
	 */
	@SuppressWarnings({ "unchecked", "unlikely-arg-type" })
	@Override
	public <T> List<T> getSqlList(String sql, String[] title) throws Exception {
		Session session = getSession();
		try {
			SQLQuery query=session.createSQLQuery(sql);
			if(!"".equals(title) && null != title) {
				for(int i=0;i<=title.length;i++) {
					query.addScalar(title[i], Hibernate.STRING);
				}
			}
			return query.list();
		}  catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.clear();
			session.close();
		}
		return null;
	}

	@Override
	public boolean save(Object obj) {
		Session session = getSession();
		Transaction ts = session.beginTransaction();
		try {
			session.save(obj);
			ts.commit();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			ts.rollback();
			return false;
		} finally {
			session.clear();
			session.close();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Integer getCountBySql(String sql) {
		Session session = getSession();
		try {
			Query query = session.createSQLQuery(sql);
			List<BigInteger> list=query.list(); 
			return  list.get(0).intValue();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.clear();
			session.close();
		}
		return 0;
	}
}
