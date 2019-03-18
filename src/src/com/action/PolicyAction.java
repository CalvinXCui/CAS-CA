package com.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.RequestAware;

import java.util.Map;

/**
* 名称 :
* 作者 :Calvin(崔红刚) 
* 邮箱 :calvin_it@163.com
* 时间 :2018年6月11日 下午2:03:30
* 描述 :
*/
public class PolicyAction extends ActionSupport implements RequestAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1664011799296131881L;
	
	private Map<String, Object> request;

	public String selectPolicy() throws Exception{
	    return "selectPolicy";
}
	
/*------------------gettings and settings------------------------*/
	
	public Map<String, Object> getRequest() {
		return request;
	}
	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}


	
	
}
