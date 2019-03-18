package com.action;

import com.entity.UserRoles;
import com.opensymphony.xwork2.ActionSupport;
import com.service.BaseService;
import com.util.PaginationUtil;
import net.sf.json.JSONArray;
import org.apache.struts2.interceptor.RequestAware;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserRolesAction extends ActionSupport implements RequestAware{

	private BaseService baseService;
	private Map<String, Object> request;
	private  Map<Object, Object> jsonmap;
	private String id;
	private List list;
	private UserRoles userRole;
    int page;//第几页
	int limit;//每页几条数据
	private String param;
	
	
	/**
	 * 列表页
	 * @return
	 */
	public String showUserRolesList(){
		
		return "showUserRolesList";
		
	}
	/**
	 * 分页查询
	 * @return
	 */
	public String showDataList(){
		String sql = "select count(id) from UserRoles b where b.flag='1'";
		String hql="from UserRoles b where b.flag='1' order by b.createTime desc";
		if(!"".equals(param) && param!=null){
			sql = "select count(id) from UserRoles b where b.flag='1' and( b.roleName like '%"+param+"%')";
			hql="from UserRoles b where b.flag='1' and (b.roleName like '%"+param+"%') order by b.createTime desc";
		}
		//获取总条数
		int totalCount = PaginationUtil.getTotalCount(sql);
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
	
	/**
	 * 删除
	 * @return
	 */
	public String delUserRole() {
		try {
			list = baseService.findObjList("from UserRoles where id='"+id+"'");
			if(list!=null && list.size()>0){
				userRole=(UserRoles)list.get(0);
			}
			userRole.setFlag("0");
			boolean flag = baseService.saveOrUpdate(userRole);
			if(flag)
				return "showUserRolesList";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 增加页面
	 * @return
	 */
	public String addUserRoles() {

		return "addUserRoles";
	}
	/**
	 * 保存
	 * @return
	 */
	public String addUserRoleData(){
		try {
			Date date=new Date();
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			userRole.setCreateTime(sdf.format(date));
			boolean flag = baseService.saveOrUpdate(userRole);
			jsonmap=new HashMap();
			if(!flag){
				jsonmap.put("status", "failed");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		jsonmap.put("status", "success");
		return "jsonmap";
	}
	/**
	 * 编辑页面
	 * @return
	 */
	public String editUserRoleData(){
		try {
			list = baseService.findObjList("from UserRoles where id='"+id+"'");
			if(list!=null && list.size()>0){
				userRole=(UserRoles)list.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "editUserRoles";
	}	
	/**
	 * 查看页面
	 * @return
	 */
	public String viewUserRole(){
		try {
			list = baseService.findObjList("from UserRoles where id='"+id+"'");
			if(list!=null && list.size()>0){
				userRole=(UserRoles)list.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "viewUserRole";
	}	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public BaseService getBaseService() {
		return baseService;
	}
	public void setBaseService(BaseService baseService) {
		this.baseService = baseService;
	}
	public Map<String, Object> getRequest() {
		return request;
	}
	public void setRequest(Map<String, Object> request) {
		this.request = request;
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public List getList() {
		return list;
	}
	public void setList(List list) {
		this.list = list;
	}
	public UserRoles getUserRole() {
		return userRole;
	}
	public void setUserRole(UserRoles userRole) {
		this.userRole = userRole;
	}
	public String getParam() {
		return param;
	}
	public void setParam(String param) {
		this.param = param;
	}
	
}
