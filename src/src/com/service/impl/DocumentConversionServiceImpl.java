package com.service.impl;

import java.util.List;

import com.dao.DocumentConversionDao;
import com.service.DocumentConversionService;

/**
* 名称 :
* 作者 :Calvin(崔红刚) 
* 邮箱 :calvin_it@163.com
* 时间 :2018年6月3日 下午3:50:47
* 描述 :
*/
public class DocumentConversionServiceImpl implements DocumentConversionService{

	private DocumentConversionDao documentConversionDao;
	
	@Override
	public <T> List<T> selectDocumentConversion() throws Exception {
		return documentConversionDao.selectDocumentConversion();
	}

	@Override
	public <T> List<T> selectAccountsAndVouchers() throws Exception {
		return documentConversionDao.selectAccountsAndVouchers();
	}
	
	
	public DocumentConversionDao getDocumentConversionDao() {
		return documentConversionDao;
	}

	public void setDocumentConversionDao(DocumentConversionDao documentConversionDao) {
		this.documentConversionDao = documentConversionDao;
	}
}
