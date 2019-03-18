package com.action;

import com.entity.AccountTitle;
import com.entity.AccountingSubject;
import com.entity.BudgetSubject;
import com.entity.NewSubject;
import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;
import com.service.BaseService;
import com.util.ExportExcelUtils;
import com.util.PaginationUtil;
import com.util.ReadExcel;
import com.util.Upload;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.xssf.usermodel.*;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.json.annotations.JSON;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
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
public class NewSubjectAction extends ActionSupport implements RequestAware {
	private  Map<Object, Object> jsonmap;
	private Map<String, Object> request;
	private BaseService baseService;
	@SuppressWarnings("rawtypes")
	private List pjList;
	private  NewSubject newSubject;
	private String id;
	private String SNo;
	private List kmList;
	private String param;
	private String pId;
	private String name;
	private List<BudgetSubject> budgets;
	private List<AccountingSubject> accounts;
	private AccountingSubject accountingSubject;
	private BudgetSubject budgetSubject;// 预算对象
	private String names;
	Map<String, Object> deleteMap;
    int page;//第几页
	private AccountTitle accountTitle;
	int limit;//每页几条数据
	/**
	 * 查询科目数
	 * @return List<AccountingVoucher>
	 * @throws Exception
	 */
	public String newSubjectTree() throws Exception{
		System.out.println("----------------------------------------"+name);
		/*if(name == "会记科目"){
			kmList = baseService.findObjList("from AccountTitle ");
		}*/
			//pjList = baseService.findObjList("from AccountingSubject");
			kmList = baseService.findObjList("from AccountTitle");
			Gson gson = new Gson();	
			String kmListJson = gson.toJson(kmList);
			request.put("kmListJson", kmListJson);
			//request.put("pjList", pjList);
		    return "newSubjectTree";
	}
	
	/**
	 * 跳转页面
	 * @return
	 */
	public String selectsub() {
		jsonmap=new HashMap<Object, Object>();
		if(!"".equals(SNo) && SNo!=null){
			jsonmap.put("SNo", SNo);
		}
		if(!"".equals(name) && name!=null){
			jsonmap.put("name", name);
		}
			return "selectNewSubject";
	}
	
	/**
	 * 分页查询
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public String finbylist() throws Exception{
		//分页
		String sql = "select count(id) from AccountingSubject";
		String hql="from AccountingSubject order by find_in_set(sLevel,'一级,二级,三级,四级,五级,六级,七级')";
		if(SNo!=null && !"".equals(SNo)){
			if(SNo.startsWith("1")||SNo.startsWith("2")||SNo.startsWith("3")||SNo.startsWith("4")||SNo.startsWith("5")){
				if("1".equals(name)){
					
				}else{
					sql= "select count(id) from AccountingSubject  where SName like '"+name+"%' and SHigherLevelNo >='"+SNo+"'";
				}
			}else if(SNo.startsWith("6")||SNo.startsWith("7")||SNo.startsWith("8")){
				sql= "select count(id) from BudgetSubject  where SName like '"+name+"%'";
			}
		}
		int totalCount = PaginationUtil.getTotalCount(sql);
		//获取总条数
		if(SNo!=null && !"".equals(SNo)){
			if(SNo.startsWith("1")||SNo.startsWith("2")||SNo.startsWith("3")||SNo.startsWith("4")||SNo.startsWith("5")){
				if("1".equals(name)){
					sql= hql="from AccountingSubject   where id like '%"+SNo+"%'  and b.aId!='"+SNo+"' and b.menuLevel='2'";
				}else{
					sql= hql="from AccountingSubject   where SName like '%"+name+"%'  and SHigherLevelNo >='"+SNo+"'";
				}
			}else if(SNo.startsWith("6")||SNo.startsWith("7")||SNo.startsWith("8")){
				sql= hql="from BudgetSubject  where SName like '%"+name+"%' ";
		}
		}
		if(!"".equals(param)&&param!=null){
			String sqlf="select count(id) from AccountingSubject where (SName like'%"+param+"%' or SNo like '%"+param+"%' or SLevel like'%"+param+"%')";
			sql ="select count(id) from AccountingSubject where (SName like'%"+param+"%' or SNo like '%"+param+"%' or SLevel like'%"+param+"%')";
			hql ="from AccountingSubject where (SName like'%"+param+"%' or SNo like '%"+param+"%' or SLevel like'%"+param+"%')";
			int totalCounts = PaginationUtil.getTotalCount(sqlf);
			if(totalCounts == 0){
				String	sqll ="select count(id) from BudgetSubject where (SName like'%"+param+"%' or SNo like '%"+param+"%' or SLevel like'%"+param+"%')";
				sql="select count(id) from BudgetSubject where (SName like'%"+param+"%' or SNo like '%"+param+"%' or SLevel like'%"+param+"%')";
				hql="from BudgetSubject where (SName like'%"+param+"%' or SNo like '%"+param+"%' or SLevel like'%"+param+"%')";
				int totalCountss = PaginationUtil.getTotalCount(sqll);
				if(totalCountss == 0){
					sql ="select count(id) from AccountingSubject where (SName like'%"+param+"%' or SNo like '%"+param+"%' or SLevel like'%"+param+"%')";
					hql ="from AccountingSubject where (SName like'%"+param+"%' or SNo like '%"+param+"%' or SLevel like'%"+param+"%')";
				}
			}
		}
		List list= PaginationUtil.pagerff(hql,totalCount,page,limit,null);
		jsonmap=new HashMap<Object, Object>();
		jsonmap.put("code", "0");
		jsonmap.put("msg", "");
		jsonmap.put("count",totalCount);
		jsonmap.put("data", list);
		return "accountingSubjectList";
	}
	
	public String selectSubjects(){
		
		return "selectSubjects";
		
	}
	/** 新增科目 
	 * @throws Exception */
	public String addNewAccount() throws Exception{
		List list = new ArrayList();
		List subList = new ArrayList();
		accounts = baseService.findObjList(" from AccountingSubject ");
		budgets = baseService.findObjList(" from BudgetSubject ");
		for (int i = 0; i < accounts.size(); i++) {
			if(accounts.get(i).getSNo().length() == 4)
			list.add(accounts.get(i));
			if(accounts.get(i).getSNo().length() != 4 && accounts.get(i).getSNo().length()<14)
			subList.add(accounts.get(i));
		}
		for (int i = 0; i < budgets.size(); i++) {
			if(budgets.get(i).getSNo().length() == 4)
			list.add(budgets.get(i));
			if(budgets.get(i).getSNo().length() != 4 && budgets.get(i).getSNo().length()<14)
			subList.add(budgets.get(i));
	}
		request.put("subList", subList);
		request.put("list", list);
		return "addNewAccount";
	}

	/** 修改科目 
	 * @throws Exception */
	public String updateNewAccount() throws Exception{
		
		List list = new ArrayList();
		List subList = new ArrayList();
		accounts = baseService.findObjList(" from AccountingSubject ");
		budgets = baseService.findObjList(" from BudgetSubject ");
		for (int i = 0; i < accounts.size(); i++) {
			if(accounts.get(i).getSNo().length() == 4)
			list.add(accounts.get(i));
			if(accounts.get(i).getSNo().length() != 4 && accounts.get(i).getSNo().length()<14)
			subList.add(accounts.get(i));
		}
		for (int i = 0; i < budgets.size(); i++) {
			if(budgets.get(i).getSNo().length() == 4)
			list.add(budgets.get(i));
			if(budgets.get(i).getSNo().length() != 4 && budgets.get(i).getSNo().length()<14)
			subList.add(budgets.get(i));
	}
		
		if(SNo.startsWith("1")||SNo.startsWith("2")||SNo.startsWith("3")||SNo.startsWith("4")||SNo.startsWith("5")){
				pjList = baseService.findObjList("from AccountingSubject t where t.id='"+id+"'");
		}else if(SNo.startsWith("6")||SNo.startsWith("7")||SNo.startsWith("8")){
			pjList = baseService.findObjList("from BudgetSubject t where t.id='"+id+"'");
		}
		request.put("id", id);
		request.put("subList", subList);
		request.put("list", list);
		request.put("pjList", pjList);
		return "updateNewAccount";
	}
	
	//新增节点
	public String addNode() {
			
		try {
			if(StringUtils.isNotBlank(name)){
			name = URLDecoder.decode(name, "UTF-8");
			boolean flag = baseService.zxSql(" insert into account_title(p_id,name) values("+id+",'"+name+"') ");
			if(flag)
				return "newSubjectTree";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//修改节点
	public String updateNode(){
		try {
			if(StringUtils.isNotBlank(name)){
				name = URLDecoder.decode(name, "UTF-8");
				boolean flag = baseService.zxSql(" update account_title set name='"+name+"' where id= "+id);
				if(flag)
					return "newSubjectTree";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//删除节点
	public String delNode(){
		try {
			boolean flag = baseService.zxSql(" delete from account_title where id="+id);
			if(flag)
				return "newSubjectTree";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String updateAccount(){
		
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
			baseService.zxSql("update Accounting_subject t set t.sNo='"+accountingSubject.getSNo()+"',t.sName='"+accountingSubject.getSName()+"',t.isfeature='"+accountingSubject.getIsfeature()
			+"',t.iseconomics='"+accountingSubject.getIseconomics()+"',t.v_ktxx='"+accountingSubject.getvKtxx()+"',t.v_xmxx='"+accountingSubject.getvXmxx()+"',t.v_ywhd='"+accountingSubject.getvYwhd()+"',t.abalanceDirection='"+accountingSubject.getAbalanceDirection()+"',t.SIsLastLevel='"+accountingSubject.getSIsLastLevel()+"',t.SLevel='"+accountingSubject.getSLevel()+"' where t.id = '"+accountingSubject.getId()+"'");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";//assistAccountAdddd
	}
	
	public String updateBudget(){
		
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
			}else if("业务活动".equals(name[i])){
				budgetSubject.setbYwhd("业务活动");
			}
		}
		try {
			baseService.zxSql("update budget_subject t set t.sNo='"+budgetSubject.getSNo()+"',t.sName='"+budgetSubject.getSName()+"',t.isfeature='"+budgetSubject.getIsfeature()
			+"',t.iseconomics='"+budgetSubject.getIseconomics()+"',t.bktxx='"+budgetSubject.getbKtxx()+"',t.bxmxx='"+budgetSubject.getbXmxx()+"',t.bywhd='"+budgetSubject.getbYwhd()+"',t.bcostkeeping='"+budgetSubject.getbCostKeeping()+"',t.bcontrolledsubject='"+
			budgetSubject.getbControlledSubject()+"',t.bcashflow='"+budgetSubject.getbCashFlow()+"',t.btaxcontrolaccounting='"+budgetSubject.getbTaxcontrolAccounting()+"',t.bczyskm='"+budgetSubject.getbCzyskm()+"',t.bbalanceDirection='"+budgetSubject.getBbalanceDirection()+"',t.SIsLastLevel='"+budgetSubject.getSIsLastLevel()+"',t.SLevel='"+budgetSubject.getSLevel()+"'  where t.id='"+id+"'");
			
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
		return "success";
	}
	
	public AccountingSubject getAccountingSubject() {
		return accountingSubject;
	}


	public void setAccountingSubject(AccountingSubject accountingSubject) {
		this.accountingSubject = accountingSubject;
	}


	public BudgetSubject getBudgetSubject() {
		return budgetSubject;
	}


	public void setBudgetSubject(BudgetSubject budgetSubject) {
		this.budgetSubject = budgetSubject;
	}


	public String getNames() {
		return names;
	}


	public void setNames(String names) {
		this.names = names;
	}


	/**
	 * 查询会计科目
	 * @return
	 * @throws Exception
	 */
	public String selectNewSubject() throws Exception{
		HttpSession session = ServletActionContext.getRequest().getSession();
		
		if (SNo==null) {
			SNo = "";
			pjList = baseService.findObjList("from AccountingSubject order by find_in_set(sLevel,'一级,二级,三级,四级,五级,六级,七级')");
		}else{
			if(SNo.startsWith("1")||SNo.startsWith("2")||SNo.startsWith("3")||SNo.startsWith("4")||SNo.startsWith("5")){
				session.setAttribute("isSelect", SNo);
				pjList = baseService.findObjList("from AccountingSubject t where t.SName like '"+name+"%'");
			}else if(SNo.startsWith("6")||SNo.startsWith("7")||SNo.startsWith("8")){
				// 将选中的值放入session中，为辅助核算做准备判断
				session.setAttribute("isSelect", SNo);
				pjList = baseService.findObjList("from BudgetSubject t where t.SName like '"+name+"%'");
			}
		}
		request.put("pjList", pjList);
		return "selectNewSubject";
	}
	
	/**
	 * 查询会计科目
	 */
	public String selectSubject(){
		if (SNo==null) {
			return null;
		}
		try {
			if(SNo.startsWith("1")||SNo.startsWith("2")||SNo.startsWith("3")||SNo.startsWith("4")||SNo.startsWith("5")){
				//财务
				if(!"112".equals(SNo))
				pjList = baseService.findObjList(" from AccountingSubject t  where t.SName like '"+name+"%'" );
			}else if(SNo.startsWith("6")||SNo.startsWith("7")||SNo.startsWith("8")){
				//预算
				pjList = baseService.findObjList(" from BudgetSubject t where t.SName like '"+name+"%'" );
			}
			request.put("pjList", pjList);
			request.put("sNo", SNo);
			return "selectSubject";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * 根据id查询会计科目
	 * @return List
	 * @throws Exception
	 */
	public String selectByPrimaryKey() throws Exception{
		pjList = baseService.findObjList("from NewSubject where id='"+id+"'");
		if(pjList!=null && !pjList.isEmpty()) {
			request.put("pjList", pjList.get(0));
		}
	    return "findPjList";
	}
	
	/**
	 * 根据id删除会计科目
	 * @param id
	 * @return ture or false
	 * @throws Exception
	 */
	public String deleteByPrimaryKey() throws Exception {
			boolean flag=false;
			if(StringUtils.isNotBlank(SNo)){
				if(SNo.startsWith("1")||SNo.startsWith("2")||SNo.startsWith("3")||SNo.startsWith("4")||SNo.startsWith("5")){
					 flag=baseService.zxSql("delete from Accounting_Subject where id='"+id+"'");
					 deleteMap=new HashMap<>();
						if (flag) {
							deleteMap.put("status", 200);
							return "deleteMap";
						} else {
							deleteMap.put("status", 500);
							return "deleteMap";
						}
				}else if(SNo.startsWith("6")||SNo.startsWith("7")||SNo.startsWith("8")){
					 flag=baseService.zxSql("delete from Budget_Subject where id='"+id+"'");
				}
			}
			if(flag) {
				return "newSubjectTree";
			}else {
				return "删除失败";
			}
	}
	
	/** 导出会计科目
	 * @throws Exception */
	@SuppressWarnings("unchecked")
	public String downLoad() throws Exception{
		HttpServletResponse response= ServletActionContext.getResponse();
		
		String str = "";
		String [] ids = id.split(",");//1,2,3,4
		for (int i = 0; i < ids.length; i++) {
			str +="'"+ids[i]+"',";
		}
		str = str.substring(0,str.length()-1);
		/*if (SNo==null) {
			return null;
		}*/
		String[] title ={"科目编码","科目名称","科目级次","是否末级","上级科目编码","会计要素"};
		int type=0;
		try {
			
			if(SNo.startsWith("1")||SNo.startsWith("2")||SNo.startsWith("3")||SNo.startsWith("4")||SNo.startsWith("5")){
				//财务
				accounts = baseService.findObjList(" from AccountingSubject n where n.id in ("+str+") ");
				ExportExcelUtils.excelExportSubject(response, "会计科目", "会计科目", "科目", title,accounts);
			}else if(SNo.startsWith("6")||SNo.startsWith("7")||SNo.startsWith("8")){
				//预算
				budgets = baseService.findObjList(" from BudgetSubject n where n.id in ("+str+") ");
				ExportExcelUtils.excelExportSubjects(response, "会计科目", "会计科目", "科目", title,budgets);
				type=1;
			}
			
			return null;
		} catch (Exception e) {
			e.printStackTrace();
		}
			return null;
	}
	public void downExcel() throws Exception{
		
		HttpServletResponse response=ServletActionContext.getResponse();
		accounts= baseService.findObjList("from AccountingSubject");
		String filename ="财务凭证.xls";

		OutputStream os = response.getOutputStream();
		response.reset();
		response.setHeader("Content-Disposition",
				"attachment;filename=" + new String(filename.getBytes("GB2312"), "ISO8859-1"));
		response.setContentType("application/msexcel");

		try {
			XSSFWorkbook wb = new XSSFWorkbook();
			// 创建Sheet
			XSSFSheet sheet = wb.createSheet("财务凭证列表");
			// 设置单元格默认宽度
			sheet.setDefaultColumnWidth(13);

			XSSFRow row = sheet.createRow(0);
			String[] titles = new String[]{"科目编码","科目名称","科目级次","是否末级","上级科目编码","会计要素"};
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
			if (accounts.size() > 0) {
				for (int j = 0; j < accounts.size(); j++) {
					// 创建第j+1行
					XSSFRow rowContent = sheet.createRow(j + 1);
					rowContent.setRowStyle(style);
					//期间名称
					rowContent.createCell(0).setCellValue(accounts.get(j).getSNo());
					rowContent.createCell(1).setCellValue(accounts.get(j).getSName());
					rowContent.createCell(2).setCellValue(accounts.get(j).getSLevel());
					rowContent.createCell(3).setCellValue(accounts.get(j).getSHigherLevelNo());
					rowContent.createCell(4).setCellValue(accounts.get(j).getSIsLastLevel());
					rowContent.createCell(5).setCellValue(accounts.get(j).getSType());

				}
			}
			wb.write(os);
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
    public String fut(){
		return "fut";
    	
    }
	
	/**
	 * 修改以及添加  ：当对象中有id时，为修改   否则为添加
	 * @param accv
	 * @return
	 * @throws Exception
	 */
	public boolean insertVoucher() throws Exception {
		  boolean flag=baseService.saveOrUpdate(newSubject);
		  return flag;
	}
	
	//上传文件集合   
    private List<File> file;
	private String filename;//文件名
	private Integer msg;
	@SuppressWarnings("static-access")
	public String SubjectExcelImport(){
		Upload ie=new Upload();
		try {
			//上传到本地
			String pathName=ie.uploadFile(System.currentTimeMillis()+"_"+filename,"kemu",file.get(0));
			
			ReadExcel re = new ReadExcel();
			List<List<String>> list = re.read(pathName, 0);//忽略前5行
			
			// 遍历读取结果
			if (list != null && list.size() > 2) {
				
				for (int i = 1; i < list.size(); i++) {
					List<String> cellList = list.get(i);
					if (cellList.size()>=15) {
						for (int j = 0; j < cellList.size(); j++) {
							/*if("财务".equals(cellList.get(j))){
								baseService.zxSql("delete from accounting_subject where 1 = 1 ");
								break;
							}else if("预算".equals(cellList.get(j))){
								baseService.zxSql(" delete from budget_subject where 1 = 1 ");
								break;
							}*/
						}
						break;
					}
				}
				
				
				for (int i = 1; i < list.size(); i++) {
					List<String> cellList = list.get(i);
					
					if (cellList.size()>=15) {
				//		for (int j = 0; j < cellList.size(); j++) {
						//if("预算".equals(cellList.get(i))){
								BudgetSubject sj = new BudgetSubject();
								//类型
								sj.setSType(cellList.get(0).toString());
								//级次
								sj.setSLevel(cellList.get(1).toString());
								//科目编码
								sj.setSNo(cellList.get(2).toString());
								//上级编码
								if (cellList.get(1).toString() != null && !cellList.get(1).toString().equals("1") && cellList.get(2).length()>4) {
									sj.setSHigherLevelNo(cellList.get(2).toString().substring(0, cellList.get(2).length()-2));
								}
								//科目名称
								sj.setSName(cellList.get(3).toString());
								//助记码
								sj.setBmnemonts(cellList.get(4).toString());
								//外币币种
								sj.setBcurrecy(cellList.get(5).toString());
								//计量单位
								sj.setBunit(cellList.get(6).toString());
								//辅助帐类型(辅助核算类别：财务会计：收入来源、项目、往来单位及个人、部门、供应商核算;预算会计：清算银行、预算单位、往来单位、功能分类、经济分类、预算项目)
								sj.setBauxiliaryType(cellList.get(7).toString());
								//余额方向
								sj.setBbalanceDirection(cellList.get(8).toString());
								//是否末级
								sj.setSIsLastLevel(cellList.get(9).toString());
								//帐页格式
								sj.setBfolloFormat(cellList.get(10).toString());
								//是否项目核算
								sj.setBisAccunting(cellList.get(11).toString());
								//项目编码
								sj.setPno(cellList.get(12).toString());
								//备注
								sj.setBremarks(cellList.get(13).toString());
								//凭证类别
								sj.setSType(cellList.get(14).toString());
								baseService.saveOrUpdate(sj);
								msg = 1;
							/*}else{ 
								AccountingSubject sj = new AccountingSubject();
								
								sj.setSType(cellList.get(0).toString());
								//级次
								sj.setSLevel(cellList.get(1).toString());
								//科目编码
								sj.setSNo(cellList.get(2).toString());
								//上级编码
								if (cellList.get(1).toString() != null && !cellList.get(1).toString().equals("1") && cellList.get(2).length()>4) {
									sj.setSHigherLevelNo(cellList.get(2).toString().substring(0, cellList.get(2).length()-2));
								}
								//*****************   测试数据时----start  ***********************//*
								//科目名称
								sj.setSName(cellList.get(3).toString());
								//助记码
								sj.setAmnemonts(cellList.get(4).toString());
								//外币币种
								sj.setAcurrecy(cellList.get(5).toString());
								//计量单位
								sj.setAunit(cellList.get(6).toString());
								//辅助帐类型(辅助核算类别：财务会计：收入来源、项目、往来单位及个人、部门、供应商核算;预算会计：清算银行、预算单位、往来单位、功能分类、经济分类、预算项目)
								sj.setAauxiliaryType(cellList.get(7).toString());
								//余额方向
								sj.setAbalanceDirection(cellList.get(8).toString());
								//是否末级
								sj.setSIsLastLevel(cellList.get(9).toString());
								//帐页格式
								sj.setAfolloFormat(cellList.get(10).toString());
								//是否项目核算
								sj.setAisAccunting(cellList.get(11).toString());
								//项目编码
								sj.setPno(cellList.get(12).toString());
								//备注
								sj.setAremarks(cellList.get(13).toString());
								//凭证类别
								sj.setSType(cellList.get(14).toString());
								//*****************   测试数据时----end  ***********************//*
								
								//科目名称
								sj.setSName(cellList.get(4).toString());
								//助记码
								sj.setAmnemonts(cellList.get(5).toString());
								//外币币种
								sj.setAcurrecy(cellList.get(6).toString());
								//计量单位
								sj.setAunit(cellList.get(7).toString());
								//辅助帐类型(辅助核算类别：财务会计：收入来源、项目、往来单位及个人、部门、供应商核算;预算会计：清算银行、预算单位、往来单位、功能分类、经济分类、预算项目)
								sj.setAauxiliaryType(cellList.get(8).toString());
								//余额方向
								sj.setAbalanceDirection(cellList.get(9).toString());
								//是否末级
								sj.setSIsLastLevel(cellList.get(10).toString());
								//帐页格式
								sj.setAfolloFormat(cellList.get(11).toString());
								//是否项目核算
								sj.setAisAccunting(cellList.get(12).toString());
								//项目编码
								sj.setPno(cellList.get(13).toString());
								//备注
								sj.setAremarks(cellList.get(14).toString());
								baseService.saveOrUpdate(sj);
								msg = 1;*/
	//						}
						//}
					} else {
						msg = 0;
						break;
					}
				}
			}else {
				msg = 0;
				//break;
			}
			
			return "msg";
		} catch (Exception e) {
			e.printStackTrace();
			msg=0;
			return "msg";
		}
	}

//上传文件集合   
    private List<File> files;
	private String filenames;//文件名
	@SuppressWarnings("static-access")
	public String SubjectExcelImportcu(){
		Upload ie=new Upload();
		try {
			//上传到本地
			String pathName=ie.uploadFile(System.currentTimeMillis()+"_"+filename,"kemu",file.get(0));
			
			ReadExcel re = new ReadExcel();
			List<List<String>> list = re.read(pathName, 0);//忽略前5行
			
			// 遍历读取结果
			if (list != null && list.size() > 2) {
				
				for (int i = 1; i < list.size(); i++) {
					List<String> cellList = list.get(i);
					if (cellList.size()>=15) {
						for (int j = 0; j < cellList.size(); j++) {
						/*	if("财务".equals(cellList.get(j))){
								baseService.zxSql("delete from accounting_subject where 1 = 1 ");
								break;
							}else if("预算".equals(cellList.get(j))){
								baseService.zxSql(" delete from budget_subject where 1 = 1 ");
								break;
							}*/
						}
						break;
					}
				}
				
				
				for (int i = 1; i < list.size(); i++) {
					List<String> cellList = list.get(i);
					
					if (cellList.size()>=15) {
				//		for (int j = 0; j < cellList.size(); j++) {
						//if("预算".equals(cellList.get(i))){
								/*BudgetSubject sj = new BudgetSubject();
								//类型
								sj.setSType(cellList.get(0).toString());
								//级次
								sj.setSLevel(cellList.get(1).toString());
								//科目编码
								sj.setSNo(cellList.get(2).toString());
								//上级编码
								if (cellList.get(1).toString() != null && !cellList.get(1).toString().equals("1") && cellList.get(2).length()>4) {
									sj.setSHigherLevelNo(cellList.get(2).toString().substring(0, cellList.get(2).length()-2));
								}
								//科目名称
								sj.setSName(cellList.get(3).toString());
								//助记码
								sj.setBmnemonts(cellList.get(4).toString());
								//外币币种
								sj.setBcurrecy(cellList.get(5).toString());
								//计量单位
								sj.setBunit(cellList.get(6).toString());
								//辅助帐类型(辅助核算类别：财务会计：收入来源、项目、往来单位及个人、部门、供应商核算;预算会计：清算银行、预算单位、往来单位、功能分类、经济分类、预算项目)
								sj.setBauxiliaryType(cellList.get(7).toString());
								//余额方向
								sj.setBbalanceDirection(cellList.get(8).toString());
								//是否末级
								sj.setSIsLastLevel(cellList.get(9).toString());
								//帐页格式
								sj.setBfolloFormat(cellList.get(10).toString());
								//是否项目核算
								sj.setBisAccunting(cellList.get(11).toString());
								//项目编码
								sj.setPno(cellList.get(12).toString());
								//备注
								sj.setBremarks(cellList.get(13).toString());
								//凭证类别
								sj.setSType(cellList.get(14).toString());
								baseService.saveOrUpdate(sj);
								msg = 1;*/
						//	}else{ 
						AccountingSubject sj = new AccountingSubject();
								
								sj.setSType(cellList.get(0).toString());
								//级次
								sj.setSLevel(cellList.get(1).toString());
								//科目编码
								sj.setSNo(cellList.get(2).toString());
								//上级编码
								if (cellList.get(1).toString() != null && !cellList.get(1).toString().equals("1") && cellList.get(2).length()>4) {
									sj.setSHigherLevelNo(cellList.get(2).toString().substring(0, cellList.get(2).length()-2));
								}
							//*****************   测试数据时----start  ***********************//*
								//科目名称
								sj.setSName(cellList.get(3).toString());
								//助记码
								sj.setAmnemonts(cellList.get(4).toString());
								//外币币种
								sj.setAcurrecy(cellList.get(5).toString());
								//计量单位
								sj.setAunit(cellList.get(6).toString());
								//辅助帐类型(辅助核算类别：财务会计：收入来源、项目、往来单位及个人、部门、供应商核算;预算会计：清算银行、预算单位、往来单位、功能分类、经济分类、预算项目)
								sj.setAauxiliaryType(cellList.get(7).toString());
								//余额方向
								sj.setAbalanceDirection(cellList.get(8).toString());
								//是否末级
								sj.setSIsLastLevel(cellList.get(9).toString());
								//帐页格式
								sj.setAfolloFormat(cellList.get(10).toString());
								//是否项目核算
								sj.setAisAccunting(cellList.get(11).toString());
								//项目编码
								sj.setPno(cellList.get(12).toString());
								//备注
								sj.setAremarks(cellList.get(13).toString());
								//凭证类别
								sj.setSType(cellList.get(14).toString());
								//*****************   测试数据时----end  ***********************//*
								
								//科目名称
								sj.setSName(cellList.get(4).toString());
								//助记码
								sj.setAmnemonts(cellList.get(5).toString());
								//外币币种
								sj.setAcurrecy(cellList.get(6).toString());
								//计量单位
								sj.setAunit(cellList.get(7).toString());
								//辅助帐类型(辅助核算类别：财务会计：收入来源、项目、往来单位及个人、部门、供应商核算;预算会计：清算银行、预算单位、往来单位、功能分类、经济分类、预算项目)
								sj.setAauxiliaryType(cellList.get(8).toString());
								//余额方向
								sj.setAbalanceDirection(cellList.get(9).toString());
								//是否末级
								sj.setSIsLastLevel(cellList.get(10).toString());
								//帐页格式
								sj.setAfolloFormat(cellList.get(11).toString());
								//是否项目核算
								sj.setAisAccunting(cellList.get(12).toString());
								//项目编码
								sj.setPno(cellList.get(13).toString());
								//备注
								sj.setAremarks(cellList.get(14).toString());
								baseService.saveOrUpdate(sj);
								msg = 1;
	//						}
						//}
					} else {
						msg = 0;
						break;
					}
				}
			}else {
				msg = 0;
				//break;
			}
			
			return "msg";
		} catch (Exception e) {
			e.printStackTrace();
			msg=0;
			return "msg";
		}
	}
	
	@JSON(serialize=false)
	public List<File> getFile() {
		return file;
	}

	public void setFile(List<File> file) {
		this.file = file;
	}
	@JSON(serialize=false)
	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}
	@JSON(serialize=false)
	public Integer getMsg() {
		return msg;
	}

	public void setMsg(Integer msg) {
		this.msg = msg;
	}

	
	public List<File> getFiles() {
		return files;
	}

	public void setFiles(List<File> files) {
		this.files = files;
	}

	public String getFilenames() {
		return filenames;
	}

	public void setFilenames(String filenames) {
		this.filenames = filenames;
	}


	/*-------------------------------gettings and settings方法-----------------------------*/
	@JSON(serialize=false)
	public Map<String, Object> getRequest() {
		return request;
	}

	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}
	@JSON(serialize=false)
	public BaseService getBaseService() {
		return baseService;
	}

	public void setBaseService(BaseService baseService) {
		this.baseService = baseService;
	}
	@JSON(serialize=false)
	public List<NewSubject> getPjList() {
		return pjList;
	}

	public void setPjList(List<NewSubject> pjList) {
		this.pjList = pjList;
	}
	@JSON(serialize=false)
	public NewSubject getNewSubject() {
		return newSubject;
	}

	public void setNewSubject(NewSubject newSubject) {
		this.newSubject = newSubject;
	}
	@JSON(serialize=false)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	@JSON(serialize=false)
	public String getSNo() {
		return SNo;
	}

	public void setSNo(String sNo) {
		SNo = sNo;
	}
	@JSON(serialize=false)
	public List getKmList() {
		return kmList;
	}

	public void setKmList(List kmList) {
		this.kmList = kmList;
	}

	@JSON(serialize=false)
	public String getParam() {
		return param;
	}


	public void setParam(String param) {
		this.param = param;
	}

	@JSON(serialize=false)
	public String getpId() {
		return pId;
	}


	public void setpId(String pId) {
		this.pId = pId;
	}

	@JSON(serialize=false)
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
	@JSON(serialize=false)
	public List<BudgetSubject> getBudgets() {
		return budgets;
	}

	public void setBudgets(List<BudgetSubject> budgets) {
		this.budgets = budgets;
	}
	@JSON(serialize=false)
	public List<AccountingSubject> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<AccountingSubject> accounts) {
		this.accounts = accounts;
	}
	@JSON(serialize=false)
	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}
	@JSON(serialize=false)
	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public Map<Object, Object> getJsonmap() {
		return jsonmap;
	}

	public void setJsonmap(Map<Object, Object> jsonmap) {
		this.jsonmap = jsonmap;
	}

	public Map<String, Object> getDeleteMap() {
		return deleteMap;
	}

	public void setDeleteMap(Map<String, Object> deleteMap) {
		this.deleteMap = deleteMap;
	}

	public AccountTitle getAccountTitle() {
		return accountTitle;
	}

	public void setAccountTitle(AccountTitle accountTitle) {
		this.accountTitle = accountTitle;
	}

	
}
