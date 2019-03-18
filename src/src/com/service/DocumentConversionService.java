package com.service;

import java.util.List;

/**
* 名称 :
* 作者 :Calvin(崔红刚) 
* 邮箱 :calvin_it@163.com
* 时间 :2018年6月3日 下午3:50:01
* 描述 :
*/
public interface DocumentConversionService {

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public <T> List<T> selectDocumentConversion()throws Exception;
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public <T> List<T> selectAccountsAndVouchers()throws Exception;

}
