package com.action;

import com.entity.OldSubject;
import com.opensymphony.xwork2.ActionSupport;
import com.service.BaseService;
import org.apache.struts2.interceptor.RequestAware;

import java.util.List;
import java.util.Map;

/**
* 名称 :AccountingVoucherAction
* 作者 :Calvin 
* 邮箱 :calvin_it@163.com
* 时间 :2018年5月24日 上午10:45:38
* 描述 :科目操作的action类
*/
@SuppressWarnings("serial")
public class OldSubjectAction extends ActionSupport implements RequestAware {
	
	private Map<String, Object> request;
	
	private BaseService baseService;
	
	private List<OldSubject> pjList;
	
	private  OldSubject oldSubject;
	
	private String id;
	
	/**
	 * 查询财务凭证
	 * @return List<AccountingVoucher>
	 * @throws Exception
	 */
	public String OldSubjectTree() throws Exception{
			pjList = baseService.findObjList("from OldSubject");
			request.put("pjList", pjList);
		    return "oldSubjectTree";
	}
	
	public String selectOldSubject() throws Exception{
			pjList = baseService.findObjList("from OldSubject");
			request.put("pjList", pjList);
		    return "selectOldSubject";
	}
	
	/**
	 * 根据id查询财务凭证
	 * @return List<AccountingVoucher>
	 * @throws Exception
	 */
	public String selectByPrimaryKey() throws Exception{
		pjList = baseService.findObjList("from OldSubject where id='"+id+"'");
		if(pjList!=null && !pjList.isEmpty()) {
			request.put("pjList", pjList.get(0));
		}
	    return "selectByPrimaryKey";
	}
	
	/**
	 * 根据id删除财务会计的凭证数据
	 * @return ture or false
	 * @throws Exception
	 */
	public String deleteByPrimaryKey() throws Exception {
		    boolean flag=baseService.zxSql("delete from OldSubject where id='"+id+"'");
		    if(flag) {
		    	return "selectByPrimaryKey";
		    }else {
		    	return "删除失败";
		    }
			
	}
	
	/**
	 * 修改以及添加  ：当对象中有id时，为修改   否则为添加
	 * @return
	 * @throws Exception
	 */
	public boolean insertVoucher() throws Exception {
		  boolean flag=baseService.saveOrUpdate(oldSubject);
		  return flag;
	}
	
	
	public Map<String, Object> getRequest() {
		return request;
	}

	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}

	public BaseService getBaseService() {
		return baseService;
	}

	public void setBaseService(BaseService baseService) {
		this.baseService = baseService;
	}
	
	public List<OldSubject> getPjList() {
		return pjList;
	}

	public void setPjList(List<OldSubject> pjList) {
		this.pjList = pjList;
	}

	public String getId() {
		return id;
	}

	public OldSubject getOldSubject() {
		return oldSubject;
	}

	public void setOldSubject(OldSubject oldSubject) {
		this.oldSubject = oldSubject;
	}

	public void setId(String id) {
		this.id = id;
	}

}
