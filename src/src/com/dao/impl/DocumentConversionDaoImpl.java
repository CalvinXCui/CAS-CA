package com.dao.impl;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.dao.DocumentConversionDao;


/**
* 名称 :
* 作者 :Calvin(崔红刚) 
* 邮箱 :calvin_it@163.com
* 时间 :2018年6月3日 下午3:26:54
* 描述 :
*/
public class DocumentConversionDaoImpl extends HibernateDaoSupport implements DocumentConversionDao{

	@SuppressWarnings("unchecked")
	@Override
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
	
	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> selectAccountsAndVouchers()throws Exception{
		Session session = getSession();
		try {
		String sql="SELECT d.vId,d.dDate,d.dPeriodName,d.debit_amount,d.credit_amount,d.dBrokerage,d.dSingleMan,d.six_subject_name,d.dTopicName,d.dTopicNo,s.abstract" + 
				" FROM document_conversion d,  subject_details s" + 
				" WHERE d.vId=s.vId ";
		SQLQuery query=session.createSQLQuery(sql)
				              .addScalar("vId", Hibernate.STRING)//凭证号
				              .addScalar("dDate", Hibernate.STRING)//日期
				              .addScalar("dPeriodName", Hibernate.STRING)//期间名称
				              .addScalar("dDebitAmount", Hibernate.STRING)//借方金额
				              .addScalar("dCreditAmount", Hibernate.STRING)//贷方金额
							  .addScalar("dBrokerage", Hibernate.STRING)//经手人
							  .addScalar("dSingleMan", Hibernate.STRING)//制单人
			                  .addScalar("dSubjectName", Hibernate.STRING)//六级科目名称
			                  .addScalar("dTopicName", Hibernate.STRING)//课题段名称
			                  .addScalar("dTopicNo", Hibernate.STRING)//课题段代码
			                  .addScalar("abstract",Hibernate.STRING);//摘要
		return query.list();
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.clear();
			session.close();
		}
		return null;
	}

}
