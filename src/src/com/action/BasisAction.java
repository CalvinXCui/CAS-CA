package com.action;

import com.entity.Department;
import com.entity.Person;
import com.opensymphony.xwork2.ActionSupport;
import com.service.BaseService;
import com.util.PaginationUtil;
import org.apache.struts2.interceptor.RequestAware;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 名称 :BasisAction
 * 作者 :Calvin
 * 邮箱 :calvin_it@163.com
 * 时间 :2018年5月24日 上午10:45:38
 * 描述
 * :财务凭证操作的action类
 */
public class BasisAction extends ActionSupport implements RequestAware {

	/**
	 *
	 */
	private static final long serialVersionUID = -7485085345965661121L;
	private  Map<Object, Object> jsonmap;
	private BaseService baseService;


	private List<Person> persons;// 人员list
	Map<String, Object> deleteMap;
	private List<Department> departments;// 部门list
	private List pjList;
	private Map<String, Object> request;// 也面传值用
	private String PUpdateTime;        //修改时间
	private Person person;
	private String id;                 //ID             ;
	private String PName;                //姓名           ;
	private String PLoginName;            //用户名（登录名）    ;
	private String PPassword;             //密码          ;
	private String PPosition;             //职位/务        ;
	private String PSex;                  //性别          ;
	private String PMobile;               //电话          ;
	private String PEmail;                //邮箱          ;
	private String PCreateTime;          //创建时间         ;
	int page;//第几PIsdelete            //是否删除
	int limit;//每PEntryTime         //入职时间           几条数据
	String param;





	public String updateperson() throws Exception {
		 	/*pjList = baseService.findObjList("from Person where id = '"+id+"'");
			if(pjList.size()>0){
				person=(Person)pjList.get(0);
			}*/
		Person person = new Person();
		person.setId(id);
		person.setPName(PName);//姓名
		person.setPLoginName(PLoginName);//用户名（登录名）
		person.setPPassword(PPassword);  //密码
		person.setPPosition(PPosition); //职位/务
		person.setPCreateTime(PCreateTime);           //创建时间
		person.setPEmail(PEmail); //邮箱
		person.setPMobile(PMobile); //电话
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String PUpdateTime = formatter.format(currentTime);
		person.setPUpdateTime(PUpdateTime);
		baseService.saveOrUpdate(person);
		//baseService.zxSql("update person t set t.PName='"+person.getPName()+"'t.PLoginName='"+person.getPLoginName()+"'t.PPassword'"+person.getPPassword()+"'t.PPosition'"+person.getPPosition()+"'t.PCreateTime'"+person.getPCreateTime()+"'t.PEmail'"+person.getPEmail()+"'t.PMobile'"+person.getPMobile());
		return "personelList";
	}

	/**            PRemarks
	 * 添加页面    PPower
	 * @return
	 */
	public String addpers(){
		return "addpers";

	}
	public String savenpers(){
		Person person = new Person();
		person.setPName(PName);//姓名
		person.setPLoginName(PLoginName);//用户名（登录名）
		person.setPPassword(PPassword);  //密码
		person.setPPosition(PPosition); //职位/务
		person.setPCreateTime(PCreateTime);           //创建时间
		person.setPEmail(PEmail); //邮箱
		person.setPMobile(PMobile); //电话
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String PUpdateTime = formatter.format(currentTime);
		person.setPUpdateTime(PUpdateTime);
		try {
			baseService.saveOrUpdate(person);
			return "success";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "personelList";
	}
	/**
	 * 修改页面
	 * @return
	 */
	public String updatepers(){
		try {
			pjList = baseService.findObjList("from Person  where id='"+id+"'");
			if(pjList.size()>0){
				person=(Person)pjList.get(0);
			}
			jsonmap.put("Person", pjList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "updatepers";

	}
	public  String stringname() throws Exception{
		pjList = baseService.findObjList("from Person  where PLoginName ='"+PLoginName+"'");
		deleteMap=new HashMap<>();
		if (pjList != null && pjList.size()>0) {
			deleteMap.put("status", 500);
			return "deleteMap";
		} else {
			deleteMap.put("status", 200);

			return "deleteMap";
		}
	}

	@SuppressWarnings("rawtypes")
	public String PersonActionlist() throws Exception{
		String sql = "select count(id) from Person  Order By P_UPDATE_TIME DESC";
		String hql="from Person  Order By P_UPDATE_TIME DESC";
		if(!"".equals(param)&&param!=null){
			sql="select count(id) from Person where (PName like'%"+param+"%' or PLoginName like '%"+param+"%')";
			hql="from Person  where (PName like'%"+param+"%' or PLoginName like '%"+param+"%')";
		}
		//获取总条数
		int totalCount = PaginationUtil.getTotalCount(sql);
		List list= PaginationUtil.pagerff(hql,totalCount, page,limit,null);
		jsonmap=new HashMap<Object, Object>();
		jsonmap.put("code", "0");
		jsonmap.put("msg", "");
		jsonmap.put("count",totalCount);
		jsonmap.put("data", list);
		return "PersonAction";
	}


	/**
	 * 查询所有人员信息
	 * @return
	 * @throws Exception
	 */
	public String selectPersonel() throws Exception {
		persons = baseService.findObjList("from Person");
		request.put("persons", persons);
		return "personelList";
	}
	/**
	 * 根据人员id查询人员信息
	 * @return
	 * @throws Exception
	 */
	public String selectPersonByPrimaryKey() throws Exception {
		persons = baseService.findObjList("from Person");
		if(persons!=null && !persons.isEmpty()) {
			request.put("persons", persons.get(0));
		}
		return "personelList";
	}

	/**
	 * 查询所有部门信息
	 * @return
	 * @throws Exception
	 */
	public String selectDepartment() throws Exception {
		departments = baseService.findObjList("from Department");
		request.put("departments", departments);
		return "departmentList";
	}

	/**
	 * 根据部门id查询人员信息
	 * @return
	 * @throws Exception
	 */
	public String selectDepartmentByPrimaryKey() throws Exception {
		departments = baseService.findObjList("from Department");
		if(departments!=null && !departments.isEmpty()) {
			request.put("departments", departments.get(0));
		}
		return "departmentList";
	}

	/**
	 * 根据id删除人员
	 * @return
	 * @throws Exception
	 */
	public String deletePersonByPrimaryKey() throws Exception {
		boolean flag = baseService.zxSql("delete from Person where id='" + id + "'");
		deleteMap=new HashMap<>();
		if (flag) {
			deleteMap.put("status", 200);
			return "deleteMap";
		} else {
			deleteMap.put("status", 500);
			return "deleteMap";
		}

	}
	/**
	 * 根据id删除部门信息
	 * @return
	 * @throws Exception
	 */
	public String deleteDepartmentByPrimaryKey() throws Exception {
		boolean flag = baseService.zxSql("delete from Department where id='" + id + "'");
		if (flag) {
			return "departmentList";
		} else {
			return "删除失败";
		}

	}

	/*-----------------------------------gettings and settings方法-------------------------------*/

	public List<Person> getPersons() {
		return persons;
	}

	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}

	public List<Department> getDepartments() {
		return departments;
	}

	public void setDepartments(List<Department> departments) {
		this.departments = departments;
	}

	public Map<String, Object> getRequest() {
		return request;
	}

	public BaseService getBaseService() {
		return baseService;
	}

	public void setBaseService(BaseService baseService) {
		this.baseService = baseService;
	}

	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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


	public String getParam() {
		return param;
	}


	public void setParam(String param) {
		this.param = param;
	}
	public Map<String, Object> getDeleteMap() {
		return deleteMap;
	}
	public void setDeleteMap(Map<String, Object> deleteMap) {
		this.deleteMap = deleteMap;
	}
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	public String getPUpdateTime() {
		return PUpdateTime;
	}
	public void setPUpdateTime(String pUpdateTime) {
		PUpdateTime = pUpdateTime;
	}
	public String getPName() {
		return PName;
	}
	public void setPName(String pName) {
		PName = pName;
	}
	public String getPLoginName() {
		return PLoginName;
	}
	public void setPLoginName(String pLoginName) {
		PLoginName = pLoginName;
	}
	public String getPPassword() {
		return PPassword;
	}
	public void setPPassword(String pPassword) {
		PPassword = pPassword;
	}
	public String getPPosition() {
		return PPosition;
	}
	public void setPPosition(String pPosition) {
		PPosition = pPosition;
	}
	public String getPSex() {
		return PSex;
	}
	public void setPSex(String pSex) {
		PSex = pSex;
	}
	public String getPMobile() {
		return PMobile;
	}
	public void setPMobile(String pMobile) {
		PMobile = pMobile;
	}
	public String getPEmail() {
		return PEmail;
	}
	public void setPEmail(String pEmail) {
		PEmail = pEmail;
	}
	public String getPCreateTime() {
		return PCreateTime;
	}
	public void setPCreateTime(String pCreateTime) {
		PCreateTime = pCreateTime;
	}
	public List getPjList() {
		return pjList;
	}
	public void setPjList(List pjList) {
		this.pjList = pjList;
	}

}
