package com.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class PaginationUtil {
	public static Query getQuery(String hql){
		Session session=HibernateSessionFactory.getSession();
		Query query = session.createQuery(hql);
		return query;
	}
	public static int getTotalCount(String hql){
		Session session=HibernateSessionFactory.getSession();
		Query query = session.createQuery(hql);
		int totalCount = Integer.parseInt(query.iterate().next().toString());
		return totalCount;
	}
	public static String scccc(String hql){
		Session session=HibernateSessionFactory.getSession();
		Query query = session.createQuery(hql);
		String totalCount = String.valueOf(query.iterate().next());
		return totalCount;
	}
	public static Double csss(String hql){
		Session session=HibernateSessionFactory.getSession();
		Query query = session.createQuery(hql);
		Double totalCount = Double.valueOf(query.iterate().next().toString());
		return totalCount;
	}
	public static List<?> pagerff(String hql,int totalCount,int page,int limit, Map<String, Object> pram) {
        Session session = null;
        Transaction tx = null;
        List<?> list=new ArrayList();
        Page p=new Page();
		p.setPage(page);
		p.setRowsTotal(totalCount);
		p.setRows(limit);
        try {
            session=HibernateSessionFactory.getSession();
            tx=session.beginTransaction();
            Query query= session.createQuery(hql).setCacheable(true);
            //设置参数
//          query.setProperties(pram);
           //查询具体数据
//           int count=query.list().size();
//           p.setRowsTotal(count);
           int nowPage=1;
           if(p.getPage()>0){
               nowPage=p.getPage();
           }
           //指定从那个对象开始查询，参数的索引位置是从0开始的，
           query.setFirstResult((p.getPage()-1)*p.getRows());
           //分页时，一次最多产寻的对象数
           query.setMaxResults(p.getRows());
           list=query.list(); 
           tx.commit();
           
       } catch (Exception e) {
           e.printStackTrace();
           tx.rollback();
       }finally{
           session.close();
       }
        return list;
   }
	
	
	
	
	
	

}
