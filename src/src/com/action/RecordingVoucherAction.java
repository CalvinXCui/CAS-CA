package com.action;

import com.opensymphony.xwork2.ActionSupport;
import com.service.BaseService;
import com.util.PaginationUtil;
import net.sf.json.JSONArray;
import org.apache.struts2.interceptor.RequestAware;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecordingVoucherAction extends ActionSupport implements RequestAware{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8670416180043788260L;

	private  Map<Object, Object> jsonmap;
	private BaseService baseService;
	
    int page;//第几页
	
	int limit;//每页几条数据
	/**
	 * 凭证列表
	 * @return List<AccountingVoucher>
	 * @throws Exception
	 */
	public String selectAccountVoucher() throws Exception {

		return "accountShow";
	}
	/**
	 * 记账凭证分页查询
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public String showAccountVoucherList() throws Exception{
		String sql = "select count(id) from RecordingVoucher";
		//获取总条数
		int totalCount = PaginationUtil.getTotalCount(sql);
		String hql="from RecordingVoucher b ORDER BY b.createTime DESC";
		//分页
		List list= PaginationUtil.pagerff(hql,totalCount, page,limit,null);
		JSONArray json = JSONArray.fromObject(list);
		jsonmap=new HashMap<Object, Object>();
		jsonmap.put("code", "0");
		jsonmap.put("msg", "");
		jsonmap.put("count",totalCount);
		jsonmap.put("data", json);
		return "jsonmap";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public RecordingVoucherAction() {
		super();
	}
	public Map<Object, Object> getJsonmap() {
		return jsonmap;
	}
	public void setJsonmap(Map<Object, Object> jsonmap) {
		this.jsonmap = jsonmap;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	@Override
	public void setRequest(Map<String, Object> arg0) {
		
	}
	public BaseService getBaseService() {
		return baseService;
	}
	public void setBaseService(BaseService baseService) {
		this.baseService = baseService;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
