package com.action;

import com.alibaba.fastjson.JSON;
import com.entity.Menu;
import com.entity.Person;
import com.opensymphony.xwork2.ActionContext;
import com.service.BaseService;
import com.util.HibernateSessionFactory;
import org.apache.commons.lang.xwork.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;
import org.hibernate.Session;
import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class LoginAction {
	private BaseService baseService;
	private List<Person> persons;
	private String username;//登录名
	private String password;//密码
	private String resudata;//信息
	private List menus;
	private List<Object[]> menusLevel1;
	private List<Object[]> menusLevel2;
	private List<Object[]> menusLevel3;
	private List list;
	private Menu menu;
	private Map<String,Object> session;
	private Map<String,Object> msgMap;
	private Map<String,Object> menuMap;
	private Person person;
	HttpServletRequest request;
	  /**
     * BASE64解密
     *
     * @param key
     * @return
     * @throws Exception
     */
    public String decryptBASE64(String key) {
        byte[] bt;
        try {
            bt = (new BASE64Decoder()).decodeBuffer(key);
            return new String(bt);//如果出现乱码可以改成： String(bt, "utf-8")或 gbk
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
    @SuppressWarnings("unchecked")
	public String leanIndex(){
		ActionContext actionContext = ActionContext.getContext();
		Map session = actionContext.getSession();
		String pName="";
		try {
			if(StringUtils.isEmpty(username) || StringUtils.isEmpty(password)){
				if("ifc".equals(session.get("userName"))){
					Person p=new Person();
					p.setPLoginName(session.get("userName").toString());
					p.setPName("ifc");
					persons=new ArrayList();
					persons.add(p);
					resudata="1";
				}else{
					resudata="0";
				}
			}else{
				String pwd=this.decryptBASE64(password);
				persons = baseService.findObjList(" from Person where PLoginName='"+username+"' and PPassword='"+pwd+"' and PState='0'");
				if(persons.size()>0){
					resudata="1";
				}else{
					resudata="0";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		msgMap=new HashMap<String,Object>();
		if("1".equals(resudata)){
			try {
				//menus = baseService.findObjList(" from Menu ");
				menus=this.getQuery(" from Menu ").list();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			for (Person person : persons) {
				pName = person.getPName();
			}
			if("ifc".equals(pName)){
				session.put("userName", pName);
				session.put("password", "123");
				session.put("pName",pName);
			}else{
				session.put("userName", username);
				session.put("password", password);
				session.put("pName",pName);
			}
			msgMap.put("status", "200");
			return "findData";
		}else{
			msgMap.put("status", "500");
			return "findData";
		}
	}
	/**
	 * 退出登录
	 * @return
	 */
	public String quit(){
		HttpServletRequest request=ServletActionContext.getRequest();
        HttpSession sess = request.getSession();
        sess.removeAttribute("userName");
        sess.removeAttribute("password");
        sess.invalidate();
		return "login";
	}
	
	//开启项目，进行页面跳转
	public String login(){
		return "login";
	}

	public String index() throws Exception{
		ActionContext context=ActionContext.getContext();
        session=context.getSession();
        Object obj = session.get("userName");
        if(obj==null){
            return "login";
        }else{
        	username=obj.toString();
			try {
				persons = baseService.findObjList(" from Person p where p.PLoginName= '"+username+"'");
				//this.findAuthorityByUserInfo("科目管理","admin");
				//this.findMenusByUserInfo(username);
			} catch (Exception e) {
				e.printStackTrace();
			}
            return "index";
        }
   }
	/**
	 * 假登陆
	 * @return
	 * @throws Exception 
	 */
	public String ifc() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		String url="";
		String userAgent = request.getHeader("user-agent");
		if (userAgent.indexOf("Android") != -1) {
			url = "ifc_moblie";
		} else if (userAgent.indexOf("iPhone") != -1 || userAgent.indexOf("iPad") != -1) {
		     url = "ifc_moblie";
		} else {
		     url = "ifc";
		}
            return url;
   }
/**
 * 查询菜单权限
 * @param userName
 * @return
 * @throws Exception
 */
	@SuppressWarnings({"unchecked"})
	public String findMenusByUserInfo(String userName) throws Exception{
		menuMap=new HashMap();
		//List list = baseService.findObjList("select m.name,m.id,m.aId,m.menuLevel from Menu m where m.aId in (select r.menuId from RoleToMenu r where r.roleId in (select t.roleId from UserToRoleUsers t where t.userId in(select p.id from Person p where p.PLoginName='"+username+"') ))");
		menusLevel1 = baseService.findObjList("select m.name,m.id,m.aId,m.menuLevel from UserRoles r,Menu m,RoleToMenu t where r.id=t.roleId and t.menuId=m.aId and r.id in(select m.id from Person p,UserRoles m,UserToRoleUsers u where p.id=u.userId and u.roleId=m.id and p.PLoginName='"+username+"') and m.menuLevel='2'");
		//menusLevel2 = baseService.findObjList("select m.name,m.id,m.aId,m.menuLevel from UserRoles r,Menu m,RoleToMenu t where r.id=t.roleId and t.menuId=m.aId and r.id in(select m.id from Person p,UserRoles m,UserToRoleUsers u where p.id=u.userId and u.roleId=m.id and p.PLoginName='"+username+"') and m.menuLevel='3'");
		//menusLevel3 = baseService.findObjList("select m.name,m.id,m.aId,m.menuLevel from UserRoles r,Menu m,RoleToMenu t where r.id=t.roleId and t.menuId=m.aId and r.id in(select m.id from Person p,UserRoles m,UserToRoleUsers u where p.id=u.userId and u.roleId=m.id and p.PLoginName='"+username+"') and m.menuLevel='4'");
		List<Map> list1=new ArrayList<Map>();
		List<Map> list2=new ArrayList<Map>();
		List<Map> list3=new ArrayList<Map>();
		for(int i=0;i<menusLevel1.size();i++){
			Object[] obj1=menusLevel1.get(i);
			Map map=new HashMap();
			map.put("url", obj1[1].toString());
			list1.add(map);
			menusLevel2 = baseService.findObjList("select m.name,m.id,m.aId,m.menuLevel from Menu m where m.pId='"+obj1[1]+"'");
			for(int j=0;j<menusLevel2.size();j++){
				Object[] obj2=menusLevel2.get(j);
				Map map2=new HashMap();
				map2.put("url", obj2[1].toString());
				list2.add(map2);
				menusLevel3 = baseService.findObjList("select m.name,m.id,m.aId,m.menuLevel from Menu m where m.pId='"+obj2[1]+"'");
				for(int m=0;m<menusLevel3.size();m++){
					Object[] obj3=menusLevel3.get(m);
					Map map3=new HashMap();
					map3.put("url", obj3[1].toString());
					list3.add(map3);
				}
				menuMap.put("aa", list3);
			}
			menuMap.put("s", list2);
		}
		menuMap.put("m", list1);

		String json=JSON.toJSONString(menuMap);
		json="["+json+"]";
		menuMap.put("json", json);
		return "menuMap";
	}
	
	
	/**
	 * 判断是否有该权限
	 * @param menuName
	 * @return
	 * @throws Exception
	 */
	public boolean findAuthorityByUserInfo(String menuName,String userName) throws Exception{
		boolean flag=false;
		String hql="select p.id from Person p,UserRoles t,UserToRoleUsers s where s.userId=p.id and s.roleId=t.id and t.id in"
				+ "(select u.id from Menu m,RoleToMenu r,UserRoles u where r.menuId=m.aId and r.roleId=u.id and m.name='"+menuName+"')";
		List list = this.getQuery(hql).list();
        persons=this.getQuery(" from Person p where p.PLoginName= '"+userName+"'").list();
        //获取用户信息
  		if(persons!=null && persons.size()>0){
  			person=(Person)persons.get(0);
  		}
  		String userId=person.getId();
  		if(list.contains(userId)){
  			flag=true;
  		}
		return flag;
		
	}
	
	
	public Query getQuery(String hql){
		Session session=HibernateSessionFactory.getSession();
		Query query = session.createQuery(hql);
		return query;
	}
	
	
	
	
	
	
	
	
	
	
	
	
//-----------------------------------------------------------	
	public BaseService getBaseService() {
		return baseService;
	}

	public void setBaseService(BaseService baseService) {
		this.baseService = baseService;
	}

	public List<Person> getPersons() {
		return persons;
	}

	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}

	public String getResudata() {
		return resudata;
	}

	public void setResudata(String resudata) {
		this.resudata = resudata;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List getMenus() {
		return menus;
	}

	public void setMenus(List menus) {
		this.menus = menus;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	public Map<String, Object> getMsgMap() {
		return msgMap;
	}
	public void setMsgMap(Map<String, Object> msgMap) {
		this.msgMap = msgMap;
	}
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	public Menu getMenu() {
		return menu;
	}
	public void setMenu(Menu menu) {
		this.menu = menu;
	}
	public Map<String, Object> getMenuMap() {
		return menuMap;
	}
	public void setMenuMap(Map<String, Object> menuMap) {
		this.menuMap = menuMap;
	}
	public List getList() {
		return list;
	}
	public void setList(List list) {
		this.list = list;
	}
	public List<Object[]> getMenusLevel1() {
		return menusLevel1;
	}
	public void setMenusLevel1(List<Object[]> menusLevel1) {
		this.menusLevel1 = menusLevel1;
	}
	public List<Object[]> getMenusLevel2() {
		return menusLevel2;
	}
	public void setMenusLevel2(List<Object[]> menusLevel2) {
		this.menusLevel2 = menusLevel2;
	}
	public List<Object[]> getMenusLevel3() {
		return menusLevel3;
	}
	public void setMenusLevel3(List<Object[]> menusLevel3) {
		this.menusLevel3 = menusLevel3;
	}
	public HttpServletRequest getRequest() {
		return request;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	
}
