package com.action;

import com.entity.Menu;
import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;
import com.service.BaseService;
import com.util.PaginationUtil;
import net.sf.json.JSONArray;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.interceptor.RequestAware;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MenuAction extends ActionSupport implements RequestAware{
	private BaseService baseService;
	String menuListJson="";
	private Map<String, Object> request;
	private String parentId;
	private String name;
	private String id;
	private String nodeID;
	private List menuList;
	private String Parentname;
	private String menuID;
	private String meData;
	private String menuCode;
	private String menuUrl;
	private Menu menudata;
	private String aId;
	private String menuLevel;
	private  Map<Object, Object> jsonmap;
	private String param;
    int page;//第几页
	
	int limit;//每页几条数据
	/**
	 * 菜单列表
	 * @return
	 * @throws Exception 
	 */
	public String showMenuList() throws Exception{
		request=new HashMap<String, Object>();
		menuList = baseService.findObjList("from Menu");
		Gson gson = new Gson();
		menuListJson = gson.toJson(menuList);
		return "showMenuList";
	}
	
	public String findMenuData(){
		
		
		return null;
	}
/**
 * 修改节点
 * @return
 */
	public String updateNode(){
		try {
			if(StringUtils.isNotBlank(name)){
				name = URLDecoder.decode(name, "UTF-8");
				Menu menu=new Menu();
				menu.setName(name);
				menu.setId(id);
				menu.setaId(nodeID);
				if(!"".equals(id) && id!=null){
					menu.setpId(id);
				}
				boolean flag = baseService.saveOrUpdate(menu);
				if(flag)
					return "showMenuList";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "showMenuList";
	}

	
	//新增节点
	public String addNode() {
			
		try {
			if(StringUtils.isNotBlank(name)){
			name = URLDecoder.decode(name, "UTF-8");
			Menu menu=new Menu();
			menu.setName(name);
			menu.setMenuCode(menuCode);
			menu.setMenuUrl(menuUrl);
			if(!"".equals(id) && id!=null && !"undefined".equals(id)){
				menu.setpId(id);
			}
			boolean flag = baseService.saveOrUpdate(menu);
			if("undefined".equals(id)){
				menu.setId(menu.getaId());
			}else{
				menu.setId(id+"/"+menu.getaId());
			}
			if(Parentname!=null){
				Parentname = URLDecoder.decode(Parentname, "UTF-8");
			}
			if("undefined".equals(Parentname)){
				menu.setMenuNameLevel(name);
			}else{
				menu.setMenuNameLevel(Parentname+"/"+name);
			}
			String menuId=menu.getId();
			String[] menuArray = menuId.split("/");
			menu.setMenuLevel(String.valueOf(menuArray.length));
			flag = baseService.saveOrUpdate(menu);

			if(flag)
				return "showMenuList";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	//删除节点
	public String delNode() {
		try {
			boolean flag = baseService.zxSql("delete from Menu where id='"+id+"'");
			if(flag)
				return "showMenuList";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 跳转
	 * @return
	 */
	public String selectMenu() {
		jsonmap=new HashMap<Object, Object>();
		if(!"".equals(menuID) && menuID!=null){
			jsonmap.put("menuID", menuID);
		}
		if(!"".equals(menuLevel) && menuLevel!=null){
			jsonmap.put("menuLevel", menuLevel);
		}
			return "showMenuData";
	}
	/**
	 * 编辑页面
	 * @return
	 * @throws Exception 
	 */
	public String editMenu() throws Exception {
		if(!"".equals(menuID) && menuID!=null){
			menuList = baseService.findObjList("from Menu where aId = '"+menuID+"'");
			if(menuList.size()>0){
				menudata=(Menu)menuList.get(0);
			}
			jsonmap=new HashMap<Object,Object>();
			jsonmap.put("menu", menudata);
		}
			return "editMenu";
	}
	/**
	 * 编辑
	 * @return
	 * @throws Exception 
	 */
	public String editMenuData() throws Exception {
		jsonmap=new HashMap();
		menuList = baseService.findObjList("from Menu where aId = '"+menuID+"'");
		if(menuList.size()>0){
			menudata=(Menu)menuList.get(0);
		}
		menudata.setaId(menuID);
		menudata.setMenuUrl(menuUrl);
		menudata.setMenuCode(menuCode);
		boolean flag = baseService.saveOrUpdate(menudata);
		if(flag){
			jsonmap.put("status", "success");
		}else{
			jsonmap.put("status", "faied");
		}
		return "jsonmap";
	}
	/**
	 * 查看页面
	 * @return
	 * @throws Exception
	 */
	public String viewMenuData() throws Exception {
		menuList = baseService.findObjList("from Menu where aId = '"+menuID+"'");
		if(menuList.size()>0){
			menudata=(Menu)menuList.get(0);
		}
		jsonmap=new HashMap<Object,Object>();
		jsonmap.put("menu", menudata);
		return "viewMenu";
	}
	//删除
	public String delMenu() {
		try {
			boolean flag = baseService.zxSql("delete from Menu where aId='"+menuID+"'");
			if(flag)
				return "showMenuList";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 分页查询
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public String menuList() throws Exception{
		String sql = "select count(id) from Menu m where m.menuLevel='2'";
		String hql="from Menu m where m.menuLevel='2'";
		if(menuID!=null && !"".equals(menuID)){
			if("1".equals(menuLevel)){
				sql= "select count(id) from Menu b where b.id like '%"+menuID+"%' and b.aId!='"+menuID+"' and b.menuLevel='2'";
				hql="from Menu  b where b.id like '%"+menuID+"%'  and b.aId!='"+menuID+"' and b.menuLevel='2'";
			}else{
				sql= "select count(id) from Menu b where b.id like '%"+menuID+"%' and b.aId!='"+menuID+"'";
				hql="from Menu  b where b.id like '%"+menuID+"%'  and b.aId!='"+menuID+"'";
			}
		}
		if(!"".equals(param) && param!=null){
			sql= "select count(id) from Menu b where b.name like '%"+param+"%' or b.menuCode like '%"+param+"%'";
			hql="from Menu  b where b.name like '%"+param+"%' or b.menuCode like '%"+param+"%'";
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
	
	@SuppressWarnings("rawtypes")
	public String addMenu() throws Exception{

		return "addMenu";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@SuppressWarnings("rawtypes")
	public String showMenuTreeList() throws Exception{
		String sql = "select count(id) from Menu b where b.id like '%"+menuID+"%'";
		//获取总条数
		int totalCount = PaginationUtil.getTotalCount(sql);
		String hql="from Menu  b where b.id like '%"+menuID+"%'";
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*-------------------------------gettings and settings方法-----------------------------*/

	public BaseService getBaseService() {
		return baseService;
	}

	public void setBaseService(BaseService baseService) {
		this.baseService = baseService;
	}

	public List getMenuList() {
		return menuList;
	}

	public void setMenuList(List menuList) {
		this.menuList = menuList;
	}

	public Map<String, Object> getRequest() {
		return request;
	}

	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}

	public String getMenuListJson() {
		return menuListJson;
	}


	public void setMenuListJson(String menuListJson) {
		this.menuListJson = menuListJson;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNodeID() {
		return nodeID;
	}

	public void setNodeID(String nodeID) {
		this.nodeID = nodeID;
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

	public String getParentname() {
		return Parentname;
	}

	public void setParentname(String parentname) {
		Parentname = parentname;
	}

	public String getMenuID() {
		return menuID;
	}

	public void setMenuID(String menuID) {
		this.menuID = menuID;
	}

	public String getMeData() {
		return meData;
	}

	public void setMeData(String meData) {
		this.meData = meData;
	}

	public String getMenuCode() {
		return menuCode;
	}

	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}

	public String getMenuUrl() {
		return menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

	public Menu getMenudata() {
		return menudata;
	}

	public void setMenudata(Menu menudata) {
		this.menudata = menudata;
	}

	public String getaId() {
		return aId;
	}

	public void setaId(String aId) {
		this.aId = aId;
	}

	public String getMenuLevel() {
		return menuLevel;
	}

	public void setMenuLevel(String menuLevel) {
		this.menuLevel = menuLevel;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}










	
}
