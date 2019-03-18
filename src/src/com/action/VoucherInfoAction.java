package com.action;

import com.entity.VoucherInfo;
import com.opensymphony.xwork2.ActionSupport;
import com.service.BaseService;
import com.util.ListSubPage;
import net.sf.json.JSONArray;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author calvin
 *
 */
public class VoucherInfoAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4371334985883587428L;
	
	private BaseService baseService;
	
	private  Map<Object, Object> jsonmap;
	
	private  VoucherInfo accv;
	
	private String id;
	
    int page;//第几页
	
	int limit;//每页几条数据
	
	@SuppressWarnings("rawtypes")
	List<VoucherInfo> accountList=null;
	
	public String voucherDataInfo() throws Exception{
		/*VoucherInfo voucherInfo=new VoucherInfo();
		voucherInfo.setVcwfzr("李玉杰");
		voucherInfo.setVcn("梁婧");
		voucherInfo.setVzhzh("IFSSC");
		voucherInfo.setVjzh("梁婧");
		voucherInfo.setVfh("李汝敏");
		baseService.save(voucherInfo);*/
		return "voucherDataInfo";
	}
	/**
	 * 分页查询
	 * @return
	 */
	public String findVoucherInfo(){
		try {
			accountList = baseService.findObjList("from VoucherInfo a order by a.id ");
			int totalCount=accountList.size();//总数据
			accountList=new ListSubPage().fenyeObj(accountList, limit, page);
			JSONArray json = JSONArray.fromObject(accountList);   
			jsonmap=new HashMap<Object, Object>();
			jsonmap.put("code", "0");
			jsonmap.put("msg", "");
			jsonmap.put("count",totalCount);
			jsonmap.put("data", json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "voucherInfoList";
	}
	/**
	 * 编辑
	 * @return
	 * @throws Exception 
	 */
	public String editVoucherInfo() throws Exception{
		accountList = baseService.findObjList("from VoucherInfo where id='"+id+"'");
		if(accountList.size()>0){
			accv=accountList.get(0);
		}
		return "editVoucherInfo";
	}
	public String saveOrUpdateVoucherInfo() throws Exception{
		baseService.saveOrUpdate(accv);
		
		
		return null;
	}
	
	
	/*-------------------------------------------------*/
	
	public String addVoucherInfo(){
		
		return null;
	}
	
	public BaseService getBaseService() {
		return baseService;
	}

	public void setBaseService(BaseService baseService) {
		this.baseService = baseService;
	}

	@SuppressWarnings("rawtypes")
	public List getAccountList() {
		return accountList;
	}

	@SuppressWarnings("rawtypes")
	public void setAccountList(List accountList) {
		this.accountList = accountList;
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
	public VoucherInfo getAccv() {
		return accv;
	}
	public void setAccv(VoucherInfo accv) {
		this.accv = accv;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
}
