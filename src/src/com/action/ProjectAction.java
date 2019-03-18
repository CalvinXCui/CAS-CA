package com.action;

import com.entity.Budget;
import com.entity.ProjectInfo;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.service.BaseService;
import org.apache.struts2.interceptor.RequestAware;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 项目及项目预算设置
 * @author admin
 *
 */
public class ProjectAction extends ActionSupport implements RequestAware {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6619257575043599910L;

	private Map<String, Object> request;
	
	private BaseService baseService;
	
	private List<ProjectInfo> pjList;
	private ProjectInfo pj;
	
	private List<Budget> budgetList;
	private Budget budget;
	
	private String id;
	
	private boolean flag;
	
	/**
	 * 查询所有项目
	 * @return
	 */
	public String findPjList(){
		try {
			ActionContext actionContext = ActionContext.getContext();
			Map session = actionContext.getSession();
			String pName = (String) session.get("pName");
			pjList = baseService.findObjList("from ProjectInfo");
			 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String date = formatter.format(new Date());
			request.put("date", date);
			request.put("pjList", pjList);
			request.put("pName", pName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "findPjList";
	}
	
	/**
	 * 添加或修改项目信息
	 * @return
	 */
	public String saveOrUpdatePj(){
		try {
			flag = baseService.saveOrUpdate(pj);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "saveOrUpdatePj";
	}
	
	/**
	 * 删除项目信息
	 * @return
	 */
	public String deletePj(){
		try {
			flag = baseService.zxSql("delete from project_info where id = '"+id+"' ");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "deletePj";
	}
	
	/**
	 * 根据id查询项目信息
	 * @return
	 */
	public String findPjById(){
		try {
			pjList = baseService.findObjList("from ProjectInfo where id = '"+id+"' ");
			if (pjList != null && pjList.size()>0) {
				pj = pjList.get(0);
			}
			request.put("pj", pj);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "findPjById";
	}
	
	/**
	 * 查询项目预算信息
	 * @return
	 */
	public String findBudgetList(){
		try {
			budgetList = baseService.findObjList("from Budget");
			request.put("budgetList", budgetList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "findBudgetList";
	}
	
	/**
	 * 添加或修改项目预算信息
	 * @return
	 */
	public String saveOrUpdateBudget(){
		try {
			flag = baseService.saveOrUpdate(budget);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "saveOrUpdateBudget";
	}
	
	/**
	 * 删除项目预算信息
	 * @return
	 */
	public String deleteBudget(){
		try {
			flag = baseService.zxSql("delete from budget where id = '"+id+"' ");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "deleteBudget";
	}
	
	/**
	 * 根据id查询项目预算信息
	 * @return
	 */
	public String findBudgetById(){
		try {
			budgetList = baseService.findObjList("from budget where id = '"+id+"' ");
			if (budgetList != null && budgetList.size()>0) {
				budget = budgetList.get(0);
			}
			request.put("budget", budget);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "findBudgetById";
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

	public List<Budget> getBudgetList() {
		return budgetList;
	}

	public void setBudgetList(List<Budget> budgetList) {
		this.budgetList = budgetList;
	}

	public Budget getBudget() {
		return budget;
	}

	public void setBudget(Budget budget) {
		this.budget = budget;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public void setPjList(List<ProjectInfo> pjList) {
		this.pjList = pjList;
	}

	public void setPj(ProjectInfo pj) {
		this.pj = pj;
	}

	public List<ProjectInfo> getPjList() {
		return pjList;
	}

	public ProjectInfo getPj() {
		return pj;
	}

}
