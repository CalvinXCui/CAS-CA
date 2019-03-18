package com.action;

import com.dto.AssistAccountDTO;
import com.entity.AccountingSubject;
import com.entity.BudgetSubject;
import com.entity.PaySubject;
import com.opensymphony.xwork2.ActionSupport;
import com.service.BaseService;
import com.util.PaginationUtil;
import com.util.ReadExcel;
import com.util.Upload;
import net.sf.json.JSONArray;
import org.apache.poi.xssf.usermodel.*;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 支出分类科目(功能分类和经济分类)
 * @author admin
 *
 */
@SuppressWarnings("serial")
public class PaySubjectAction extends ActionSupport implements RequestAware {
	private  Map<Object, Object> jsonmap;
	private Map<String, Object> request;

	private Map<String, Object> deleteMap;
	private BaseService baseService;
	
	private List<PaySubject> psList;
	
	private String type; // 支出分类类型： 功能分类    经济分类
	
	private String treeStr;
	private String id;
	private String TId;
	
	private String isSelect;
	
	private AccountingSubject accountingSubject;
	
	private BudgetSubject budgetSubject;// 预算对象
	
	private AssistAccountDTO assistDto;// 辅助核算
	private String name;
	private String names;
	private PaySubject paySubject; //功能
	@SuppressWarnings("rawtypes")
	private List pjList;
	private  String[] arr;
	int page;//第几页
	int limit;//每页几条数据
	String param;
	
	public String[] getArr() {
		return arr;
	}

	public void setArr(String[] arr) {
		this.arr = arr;
	}

	/***
	 * 
	 *修改页面
	 */
	public String updatEeconomictype(){
		System.out.println("修改查找"+id);
		try {
			pjList = baseService.findObjList("from PaySubject t where t.id='"+id+"'");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.put("pjList", pjList);
		return "updatEeconomictype";
		
	}
	
	/***
	 * 
	 * 经济修改
	 * @return
	 */
	public String updatEeconomictypes(){
		try {
			baseService.zxSql("update Pay_Subject t set t.num='"+paySubject.getNum()+"',t.name='"+paySubject.getName()+"',t.type ='"+paySubject.getType()+"'where t.id = '"+paySubject.getId()+"'");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "success";
		
	}
	
	
	/***
	 * 经济删除
	 * @return
	 * @throws Exception
	 */
	public String deleteBYeconomictype() throws Exception{
		boolean flag=false;
		
		 flag=baseService.zxSql("delete from pay_Subject where id='"+id+"'");
	
		 if(flag) {
			 return "functiontype";
		 }else {
			 return "删除失败";
		 }
	}
	
	
	/**
	 * 经济添加
	 */
	public String addEconomictypes(){
		try {
			baseService.save(paySubject);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "addEconomictypes";
	}
	
	/**
	 * 经济添加页面
	 */
	public String addEconomictype(){
		
		return "addEconomictype";
		
	}
	
	
	
	/**
	 * 功能修改
	 * @return
	 */
	public String updateFunctionType(){
		
		try {
			baseService.zxSql("update Pay_Subject t set t.num='"+paySubject.getNum()+"',t.name='"+paySubject.getName()+"',t.type ='"+paySubject.getType()+"'where t.id = '"+paySubject.getId()+"'");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "success";
		
	}
	/**
	 * 修改页面
	 * @return
	 */
	public String updatefunction(){
		System.out.println("修改查找"+id);
		try {
			pjList = baseService.findObjList("from PaySubject t where t.id='"+id+"'");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.put("pjList", pjList);
		return "updatefunction";
		
	}
	
	
	
	/**
	 * 功能添加
	 * @return
	 */
	public String addFunctionType (){
		try {
			baseService.save(paySubject);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "addFunctionType";
		
	}
	/**
	 * 
	 * 删除
	 * @return
	 * @throws Exception
	 */
	public String deletefunction() throws Exception {
		boolean flag=false;
				 flag=baseService.zxSql("delete from pay_Subject where id='"+id+"'");
				 deleteMap=new HashMap<>();

		if(flag) {
			return "functiontype";
		}else {
			return "删除失败";
		}
	}
	/**
	 * 
	 *财务添加
	 * @return
	 */
	public String assistAccountAdddd(){
		System.out.println(""+arr);
		String [] name = names.split(",");
		for (int i = 0; i < name.length; i++) {
			 if("功能科目".equals(name[i])){
				 accountingSubject.setIsfeature("功能科目");
			}else if("经济科目".equals(name[i])){
				accountingSubject.setIseconomics("经济科目");
			}else if("课题信息".equals(name[i])){
				accountingSubject.setvKtxx("课题信息");
			}else if("项目信息".equals(name[i])){
				accountingSubject.setvXmxx("项目信息");
			}else if("业务活动".equals(name[i])){
				accountingSubject.setvYwhd("业务活动");
			}	
		}
		try {
			HttpSession session =ServletActionContext.getRequest().getSession();
			baseService.save(accountingSubject);
			
			session.setAttribute("assistDto", assistDto);
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "assistAccountAdddd";
	}
	/**
	 * 
	 * 预算添加
	 * @return
	 */
	public String assistAccountAddd(){
		String [] name = names.split(",");
		for (int i = 0; i < name.length; i++) {
			 if("功能科目".equals(name[i])){
				budgetSubject.setIsfeature("功能科目");
			}else if("经济科目".equals(name[i])){
				budgetSubject.setIseconomics("经济科目");
			}else if("课题信息".equals(name[i])){
				budgetSubject.setbKtxx("课题信息");
			}else if("项目信息".equals(name[i])){
				budgetSubject.setbXmxx("项目信息");
			}
		}
		try {
			HttpSession session =ServletActionContext.getRequest().getSession();
			baseService.save(budgetSubject);
			session.setAttribute("assistDto", assistDto);
			return "success";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "assistAccountAddd";
	}
	
	
	private List<BudgetSubject> bList;
	private List<AccountingSubject> aList;
	
	/**
	 * 添加：
	 * 需要判断是否选中“功能”和“经济”分类
	 * @return
	 */
	public String assistAccountAdd(){
		
		try {
			HttpSession session =ServletActionContext.getRequest().getSession();
			baseService.save(budgetSubject);
			assistDto.getSubjectInfo();//科目信息
			assistDto.getProjectInfo();// 项目信息
			assistDto.getFunction();//功能
			assistDto.getAtaskActivity();
			assistDto.getEconomic();//经济
			
			
			session.setAttribute("assistDto", assistDto);
			return "success";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "assistAccountAdd";
	}
	
	/**
	 * 跳转辅助核算页面
	 * @return
	 */
	public String assistAccount(){
		HttpSession session =ServletActionContext.getRequest().getSession();
		isSelect = (String) session.getAttribute("isSelect");
		request.put("isSelect", isSelect);
		session.removeAttribute("isSelect");
		return "assistAccount";// 辅助核算
	}
	
	public String functionSubject(){
		
		return "";
	}
	/**
	 * 跳转页面
	 * @return
	 */
	public String selectFunction() {
		jsonmap=new HashMap<Object, Object>();
		if(!"".equals(TId) && TId!=null){
			jsonmap.put("TId", TId);
		}
		if(!"".equals(name) && name!=null){
			jsonmap.put("name", name);
		}
		if(!"".equals(type) && type!=null){
			jsonmap.put("type", type);
		}
		if(type.equals("功能分类")){
			return "functiontype";
		}else if(type.equals("经济分类")){
			return "economictype";
		}
		return null;
		
	}
	/**
	 * 经济分页查询
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public String jingjpysublist() throws Exception{
		String sql = "select count(id) from PaySubject where type ='经济分类'";
		String hql="from PaySubject  where type ='经济分类'";
		if(TId!=null && !"".equals(TId)){
			if("1".equals(name)){
				
			}else{
				sql= "select count(id) from PaySubject  where num like '"+TId+"%'";
			}
		}
		//获取总条数
		int totalCount = PaginationUtil.getTotalCount(sql);
		if(TId!=null && !"".equals(TId)){
			if("1".equals(name)){
				
			}else{
				sql= hql="from PaySubject  where num like '"+TId+"%'";
			}
		}
		if(!"".equals(param)&&param!=null){
			sql="select count(id) from PaySubject where  type='经济分类' and (num like'%"+param+"%' or name like '%"+param+"%')";
			hql="from PaySubject  where  type ='经济分类' and (num like'%"+param+"%' or name like '%"+param+"%')";
		}
		//分页
		List list= PaginationUtil.pagerff(hql,totalCount, page,limit,null);
		jsonmap=new HashMap<Object, Object>();
		jsonmap.put("code", "0");
		jsonmap.put("msg", "");
		jsonmap.put("count",totalCount);
		jsonmap.put("data", list);
		return "PaySubjectAct";
	}
	/**
	 * 功能分页查询
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public String pysublist() throws Exception{
		String sql = "select count(id) from PaySubject where type ='功能分类'";
		String hql="from PaySubject  where type ='功能分类'";
		if(TId!=null && !"".equals(TId)){
			if("1".equals(name)){
				
			}else{
				sql= "select count(id) from PaySubject  where num like '"+TId+"%'";
			}
		}
		//获取总条数
		int totalCount = PaginationUtil.getTotalCount(sql);
		if(TId!=null && !"".equals(TId)){
			if("1".equals(name)){
				
			}else{
				sql= hql="from PaySubject  where num like '"+TId+"%'";
			}
		}
		if(!"".equals(param)&&param!=null){
			sql="select count(id) from PaySubject where  type='功能分类' and (num like'%"+param+"%' or name like '%"+param+"%')";
			hql="from PaySubject  where  type ='功能分类' and (num like'%"+param+"%' or name like '%"+param+"%')";
		}
		//分页
		List list= PaginationUtil.pagerff(hql,totalCount, page,limit,null);
		jsonmap=new HashMap<Object, Object>();
		jsonmap.put("code", "0");
		jsonmap.put("msg", "");
		jsonmap.put("count",totalCount);
		jsonmap.put("data", list);
		return "PaySubjectAct";
	}
	/**
	 * 根据树id查询
	 * @return
	 *//*
	public String selectFunction(){
		try {
			if (TId!=null) {
				psList = baseService.findObjList("from PaySubject where num = '"+TId+"' AND type = '"+type+"' ");
			}else{
				psList = baseService.findObjList("from PaySubject where type = '"+type+"' ");
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.put("psList", psList);
		if ("功能分类".equals(type)) {
			return "functiontype";
		}else{
			return "economictype";
		}
	}
	*/
	/**
	 * 查询树结构数据
	 * @return
	 */
	public String findTreeData(){
		try {
			List<PaySubject> trees = baseService.findObjList("from PaySubject where type = '"+type+"' ");
			JSONArray jsonArray = JSONArray.fromObject(trees);
			treeStr = jsonArray.toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (treeStr!=null && !treeStr.equals("")) {
			return "success";
		}
		return null;
	}
	/**
	 * 跳转到添加页面
	 * 
	 * @return
	 */
	public String addfunction(){
		
		return "addfunction";
		
	}
	
	/**
	 * 查询支出分类列表
	 * @return
	 */
	public String findPaySubject(){
		try {
			baseService.findObjList("from PaySubject where type = '"+type+"' ");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "findPaySubject";
	}
	
	//上传文件集合   
    private List<File> file;
	private String filename;//文件名
	private Integer msg;
	
	/**
	 * 导入支出分类科目
	 * @return
	 */
	@SuppressWarnings("static-access")
	public String SubjectExcelImport(){
		Upload ie=new Upload();
		try {
			//上传到本地
			String pathName=ie.uploadFile(System.currentTimeMillis()+"_"+filename,"kemu",file.get(0));
			
			ReadExcel re = new ReadExcel();
			List<List<String>> list = re.read(pathName, 0);
			// 遍历读取结果
			if (list != null && list.size() > 2) {
				for (int i = 1; i < list.size(); i++) {
					List<String> cellList = list.get(i);
					if (cellList.size()>=3) {
						PaySubject ps = new PaySubject();
						ps.setNum(cellList.get(0).toString());
						ps.setName(cellList.get(1).toString());
						ps.setType(cellList.get(2).toString());
						if (cellList.get(0).toString() != null && cellList.get(0).length()>3) {
							ps.setLeadersNum(cellList.get(0).toString().substring(0, cellList.get(0).length()-2));
						}
						baseService.saveOrUpdate(ps);
					} else {
						msg = 0;
						break;
					}
				}
			} else {
				msg = 0;
			}
			return "msg";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "msg";
	}
	/**
	 * 导出'经济分类' 
	 * 
	 * @return
	 * @throws Exception 
	 */
	public void downLoad() throws Exception{
		HttpServletResponse response=ServletActionContext.getResponse();
		psList= baseService.findObjList("from PaySubject where type = '经济分类'");
		String filename ="经济凭证.xls";

		OutputStream os = response.getOutputStream();
		response.reset();
		response.setHeader("Content-Disposition",
				"attachment;filename=" + new String(filename.getBytes("GB2312"), "ISO8859-1"));
		response.setContentType("application/msexcel");

		try {
			XSSFWorkbook wb = new XSSFWorkbook();
			// 创建Sheet
			XSSFSheet sheet = wb.createSheet("经济凭证列表");
			// 设置单元格默认宽度
			sheet.setDefaultColumnWidth(13);
            
			XSSFRow row = sheet.createRow(0);
			String[] titles ={"科目编码","科目名称","类型","上级科目编码"};
			for (int i = 0; i <titles.length; i++) {
				XSSFCell cell = row.createCell(i);
				// 添加第i格并设置值
				cell.setCellValue(titles[i]);
			}
			XSSFCellStyle style = wb.createCellStyle();
			XSSFFont font = wb.createFont();
			font.setFontName("楷体");
			font.setFontHeightInPoints((short) 16);//设置字体大小
			font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);//粗体显示
			style.setFont(font);
			style.setWrapText(true);//设置自动换行
			style.setAlignment(XSSFCellStyle.ALIGN_CENTER);//居中
			style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);//设置垂直居中
			// EXCEL内容
			if (psList.size() > 0) {
				for (int j = 0; j < psList.size(); j++) {
					// 创建第j+1行
					XSSFRow rowContent = sheet.createRow(j + 1);
					rowContent.setRowStyle(style);
					rowContent.createCell(0).setCellValue(psList.get(j).getNum());
					rowContent.createCell(1).setCellValue(psList.get(j).getName());
					rowContent.createCell(2).setCellValue(psList.get(j).getType());
					rowContent.createCell(3).setCellValue(psList.get(j).getLeadersNum());
				}
			}
			wb.write(os);
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	/**
	 * 功能导出
	 * @throws Exception
	 */
	public void downLoads() throws Exception{
		HttpServletResponse response=ServletActionContext.getResponse();
		psList= baseService.findObjList("from PaySubject where type = '功能分类'");
		String filename ="功能凭证.xls";

		OutputStream os = response.getOutputStream();
		response.reset();
		response.setHeader("Content-Disposition",
				"attachment;filename=" + new String(filename.getBytes("GB2312"), "ISO8859-1"));
		response.setContentType("application/msexcel");

		try {
			XSSFWorkbook wb = new XSSFWorkbook();
			// 创建Sheet
			XSSFSheet sheet = wb.createSheet("功能凭证列表");
			// 设置单元格默认宽度
			sheet.setDefaultColumnWidth(13);
            
			XSSFRow row = sheet.createRow(0);
			String[] titles ={"科目编码","科目名称","类型","上级科目编码"};
			for (int i = 0; i <titles.length; i++) {
				XSSFCell cell = row.createCell(i);
				// 添加第i格并设置值
				cell.setCellValue(titles[i]);
			}
			XSSFCellStyle style = wb.createCellStyle();
			XSSFFont font = wb.createFont();
			font.setFontName("楷体");
			font.setFontHeightInPoints((short) 16);//设置字体大小
			font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);//粗体显示
			style.setFont(font);
			style.setWrapText(true);//设置自动换行
			style.setAlignment(XSSFCellStyle.ALIGN_CENTER);//居中
			style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);//设置垂直居中
			// EXCEL内容
			if (psList.size() > 0) {
				for (int j = 0; j < psList.size(); j++) {
					// 创建第j+1行
					XSSFRow rowContent = sheet.createRow(j + 1);
					rowContent.setRowStyle(style);
					rowContent.createCell(0).setCellValue(psList.get(j).getNum());
					rowContent.createCell(1).setCellValue(psList.get(j).getName());
					rowContent.createCell(2).setCellValue(psList.get(j).getType());
					rowContent.createCell(3).setCellValue(psList.get(j).getLeadersNum());
				}
			}
			wb.write(os);
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	/*public String  addExcel() throws Exception{
		//aList = baseService.findObjList(" from AccountingSubject t ");
		bList = baseService.findObjList("from BudgetSubject t");
		ReadExcel re = new ReadExcel();
		List<List<String>> list = re.read("C:/Users/admin/Desktop/成本核算.xlsx", 1);
		for (int i = 0; i < bList.size(); i++) {
			for (int j = 0; j < list.size(); j++) {
				if(bList.get(i).getSNo().equals(list.get(j).get(0).toString())){
					bList.get(i).setBauxiliaryType("成本核算");
					baseService.saveOrUpdate(bList.get(i));
				}
			}
		}
		
		return null;
	}*/

	public List<File> getFile() {
		return file;
	}

	public void setFile(List<File> file) {
		this.file = file;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public Integer getMsg() {
		return msg;
	}

	public void setMsg(Integer msg) {
		this.msg = msg;
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

	public List<PaySubject> getPsList() {
		return psList;
	}

	public void setPsList(List<PaySubject> psList) {
		this.psList = psList;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}


	public String getTreeStr() {
		return treeStr;
	}


	public void setTreeStr(String treeStr) {
		this.treeStr = treeStr;
	}

	public String getTId() {
		return TId;
	}

	public void setTId(String tId) {
		TId = tId;
	}

	public String getIsSelect() {
		return isSelect;
	}

	public void setIsSelect(String isSelect) {
		this.isSelect = isSelect;
	}

	public BudgetSubject getBudgetSubject() {
		return budgetSubject;
	}

	public void setBudgetSubject(BudgetSubject budgetSubject) {
		this.budgetSubject = budgetSubject;
	}

	public AssistAccountDTO getAssistDto() {
		return assistDto;
	}

	public void setAssistDto(AssistAccountDTO assistDto) {
		this.assistDto = assistDto;
	}

	public List getPjList() {
		return pjList;
	}

	public void setPjList(List pjList) {
		this.pjList = pjList;
	}


	public List<BudgetSubject> getbList() {
		return bList;
	}


	public List<AccountingSubject> getaList() {
		return aList;
	}

	public void setaList(List<AccountingSubject> aList) {
		this.aList = aList;
	}

	public void setbList(List<BudgetSubject> bList) {
		this.bList = bList;
	}

	public AccountingSubject getAccountingSubject() {
		return accountingSubject;
	}
	public void setAccountingSubject(AccountingSubject accountingSubject) {
		this.accountingSubject = accountingSubject;
	}
	public String  getNames() {
		return names;
	}
	public void setNames(String  names) {
		this.names = names;
	}



	public PaySubject getPaySubject() {
		return paySubject;
	}



	public void setPaySubject(PaySubject paySubject) {
		this.paySubject = paySubject;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Map<String, Object> getDeleteMap() {
		return deleteMap;
	}

	public void setDeleteMap(Map<String, Object> deleteMap) {
		this.deleteMap = deleteMap;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}
	
	
}
