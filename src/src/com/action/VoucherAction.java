package com.action;

import com.client.BootStrap;
import com.client.MyClient;
import com.client.StringResult;
import com.dto.AssistAccountDTO;
import com.entity.*;
import com.mysql.jdbc.Connection;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.service.BaseService;
import com.thread.InitThreadPool;
import com.thread.VocherChangeThread;
import com.thread.VocherThread;
import com.util.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.xssf.usermodel.*;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.hibernate.Query;
import org.hibernate.Session;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 名称 : VoucherAction 
 * 作者 :Calvin(崔红刚) 
 * 邮箱 :calvin_it@163.com 
 * 时间 :2018年5月28日 上午10:53:12 
 * 描述 :
 */
public class VoucherAction extends ActionSupport implements RequestAware {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4978536462085483513L;

	private Map<String, Object> request;
	
	private Map<String, Object> msgMap;
	
	private  Map<Object, Object> jsonmap;

	private BaseService baseService;

	private List<AccountingVoucher> accountingVouchers;
			
	private List<VoucherRuleBudget> voucherRuleBudgets;
	
	private List<VoucherRuleName> voucherRuleNames;

	private List<VoucherRules> vrList;
	
	private VoucherRules vr;

	private List<OldAccountingVoucher> oldAccountingVouchers;

	private List<BudgetVoucher> budgetVouchers;
	
	private Connection connection;
	/*
	 * 财务凭证
	 */
	private AccountingVoucher accv;
	
	private List<AccountingVoucher> accvs;
	/*
	 * 旧的财务凭证
	 */
	private OldAccountingVoucher oldaccv;

	// 上传文件集合
	private List<File> file;

	private String filename;// 文件名

	private String msg;

	private String message;
	/*----------------前台接受信息------------------*/
	private String id;
	
	private String ids;

	/*****************************/
	private String accNo;

	private String budNo;
	
	private String accDate;
	
	private String budDate;
	private String SName;
	private AccountingSubject accountingSubject;
	
	private List<PaySubject> economics;
	private List<PaySubject> functions;
	private List<AccountingSubject> kmList;
	
	private List<BudgetSubject> ysList;
	private List<AccountingSubject> kjList;
	private AssistAccountDTO assistDto;
	private List<VoucherInfo> voucherInfoList;

	private VoucherInfo voucherInfo;
	private String jsonStr;
	
	String vnos;
	
	String[] vnostcp;
	
	int page;//第几页
	
	int limit;//每页几条数据
	
	Map<String, Object> deleteMap;
	
	Map<String, Object> uploadMap;
	
	String param;
	
	/**
	 * 
	 * 
	 * @return List<AccountingVoucher>
	 * @throws Exception
	 */
	public String selectVoucher() throws Exception {

		return "selectVoucher";
	}
	
	/**
	 * 凭证管理列表后台分页展示（视图层）
	 * 
	 * @return List<AccountingVoucher>
	 * @throws Exception
	 */
	public String voucherManagementList(){
		
		return "voucherManagementList";
	}
	
	/**
	 * 添加凭证页面
	 * @return
	 * @throws Exception 
	 */
	public String addVoucherPage(){
		try {
			HttpSession session =ServletActionContext.getRequest().getSession();
			AssistAccountDTO assistDto = (AssistAccountDTO) session.getAttribute("assistDto");
			if (assistDto!=null) {
				// 经济分类
				if (assistDto.getEconomic().equals("true")) {
					economics = baseService.findObjList("from PaySubject where type = '经济分类' ");
				}
				// 功能分类
				if (assistDto.getFunction().equals("true")) {
					functions = baseService.findObjList("from PaySubject where type = '功能分类' ");
				}

			}
			session.removeAttribute("assistDto");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "addVoucherPage";
	}
	/**
	 * 模糊查询
	 * @return
	 * @throws Exception
	 */
	public String findData() throws Exception{
		kmList=new ArrayList<AccountingSubject>();
		String kmName="";
		if(!"".equals(SName)){
			String[] split = SName.split("'");
			if(split.length>0){
				for(int i=0;i<split.length;i++){
					kmName+=split[i];
				}
			}
			kjList = baseService.findObjList("from AccountingSubject where SName like '%"+kmName+"%'");
			ysList = baseService.findObjList("from BudgetSubject where SName like '%"+kmName+"%'");
			if(kjList!=null && kjList.size()>0){
				kmList.addAll(kjList);
			}
			if(ysList!=null && ysList.size()>0){
				for(int i=0;i<ysList.size();i++){
					AccountingSubject account=new AccountingSubject();
					BudgetSubject accountingSubject2 = ysList.get(i);
					String sName=accountingSubject2.getSName();
					account.setSName(sName);
					kmList.add(account);
				}
			}
		}
		request.put("kmList", kmList);
		return "findData";
			
	}
	/**
	 * 根据科目名称查询数据
	 * @return
	 * @throws Exception
	 */
	public String findDataBySName() throws Exception{
		kmList=new ArrayList<AccountingSubject>();

		kjList = baseService.findObjList("from AccountingSubject where SName = '"+SName+"'");
		if(kjList.size()>0){
			kmList.addAll(kjList);
		}else{
			ysList = baseService.findObjList("from BudgetSubject where SName = '"+SName+"'");
			if(ysList!=null && ysList.size()>0){
				for(int i=0;i<ysList.size();i++){
					AccountingSubject account=new AccountingSubject();
					BudgetSubject accountingSubject2 = ysList.get(i);
					String sName=accountingSubject2.getSName();
					String SNo=accountingSubject2.getSNo();
					account.setSName(sName);
					account.setSNo(SNo);
					kmList.add(account);
				}
			}
		}
		//request.put("kmList", kmList.get(0));
		return "findData";
			
	}
	/**
	 * 辅助添加页面
	 * @return
	 * @throws Exception 
	 */
	public String addAssistVoucherPage() throws Exception{
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String curnDate=sdf.format(date);
		request.put("curnDate", curnDate);
		voucherInfoList = baseService.findObjList("from VoucherInfo a order by a.id ");
		if(voucherInfoList!=null && voucherInfoList.size()>0){
			voucherInfo=voucherInfoList.get(0);
		}
		return "addAssistVoucher";
	}
	/**
	 * 编辑页面
	 * @return
	 * @throws Exception 
	 */
	public String editAssistVoucherPage() throws Exception{
		accountingVouchers = baseService.findObjList("from AccountingVoucher where id = '"+id+"'");
		if(accountingVouchers.size()>0){
			accv=accountingVouchers.get(0);
		}
		voucherInfoList=baseService.findObjList("from VoucherInfo");
		if(voucherInfoList!=null&&voucherInfoList.size()>0){
			voucherInfo=voucherInfoList.get(0);
		}
		return "editAssistVoucher";
	}
	
	public String assistNext(){
		
		HttpSession session =ServletActionContext.getRequest().getSession();
		
		assistDto.getSubjectInfo();//科目信息
		assistDto.getProjectInfo();// 项目信息
		assistDto.getFunction();//功能
		assistDto.getAtaskActivity();
		assistDto.getEconomic();//经济
		
		session.setAttribute("assistDto", assistDto);
		return "assistNext";
	}
	
	@SuppressWarnings("unused")
	public String addVouchers() throws Exception{
		msgMap=new HashMap<String,Object>();
		com.alibaba.fastjson.JSONArray array = JsonUtil.toArray(jsonStr);
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currDate=sdf.format(date);
		//生成编号
		String num="";
		for (int i = 0; i < array.size(); i++) {
			com.alibaba.fastjson.JSONObject obj = (com.alibaba.fastjson.JSONObject) array.get(i);
		    String kmname = obj.getString("kmname");
			if(kmname!="" && !"".equals(kmname)){
				AccountingVoucher account=new AccountingVoucher();
				String desc = obj.getString("desc");
			    account.setVAbstract(desc);//摘要
				if("".equals(num)){
					num=voucherNum(account);
				}
				String SNo = obj.getString("SNo");
				account.setSNo(SNo);
				
			    account.setSName(kmname);//科目名称
				
				String Vgnkm = obj.getString("Vgnkm");
			    account.setVgnkm(Vgnkm);//功能科目

				String Vjjkm = obj.getString("Vjjkm");
			    account.setVjjkm(Vjjkm);//经济科目

				String Vxmxx = obj.getString("Vxmxx");
			    account.setVxmxx(Vxmxx);//项目信息

				String Vktxx = obj.getString("Vktxx");
			    account.setVktxx(Vktxx);//课题信息

				String Vywhd = obj.getString("Vywhd");
			    account.setVywhd(Vywhd);//业务活动
				//添加的信息设置为正常显示状态
				account.setVStatus(0);
			    String VCreditAmount = obj.getString("lender");
			    if("".equals(VCreditAmount)){
			    	VCreditAmount="0";
			    }
			    account.setVCreditAmount(VCreditAmount);//贷方金额

			    
			    String username = obj.getString("username");
			    account.setVHandleName(username);//经手人

			    String debit = obj.getString("debit");
			    if("".equals(debit)){
			    	debit="0";
			    }
			    account.setVDebitAmount(debit);//借方金额
			    
				account.setVNo(num);
				
				account.setCreateTime(currDate);
				account.setType("1");//手动录入
				//添加的信息设置为正常显示状态
				account.setVStatus(0);
				boolean flag = baseService.saveOrUpdate(account);
				if(!flag){
					msgMap.put("status", "fail");
					return "saveStatus";
				}
			}
		}
		msgMap.put("status", "success");
		return "saveStatus";
	}
	/**
	 * 编辑
	 * @return
	 */
	public String editVoucherData(){
		try {
			baseService.saveOrUpdate(accv);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "savesuccess";
	}
	/**
	 * 查看页面
	 * @return
	 * @throws Exception 
	 */
	public String viewVoucherData() throws Exception{
		accountingVouchers = baseService.findObjList("from AccountingVoucher where id = '"+id+"'");
		if(accountingVouchers!=null&&accountingVouchers.size()>0){
			accv=accountingVouchers.get(0);
		}
		accountingVouchers = baseService.findObjList("from AccountingVoucher where VNo = '"+accv.getVNo()+"'");
		request.put("budvList", accountingVouchers);
		request.put("vNo", accv.getVNo());
		voucherInfoList=baseService.findObjList("from VoucherInfo");
		if(voucherInfoList!=null&&voucherInfoList.size()>0){
			voucherInfo=voucherInfoList.get(0);
		}
		return "voucherDetail";
	}
	
	/**
	 * 查看凭证详情
	 * 
	 * @return List<AccountingVoucher>
	 * @throws Exception
	 */
	public String voucherDetails() throws Exception {
		
		if (StringUtils.isNotEmpty(accNo)) {
			accountingVouchers = baseService.findObjList("from AccountingVoucher where VNo='" + accNo + "' and VStatus='0' ");
			budgetVouchers = baseService.findObjList("from BudgetVoucher where VNo='" + accNo + "' and  VStatus='0' ");
			request.put("vNo", accNo);
			request.put("vDate", accDate);
		} else {
			accountingVouchers = baseService.findObjList("from AccountingVoucher where VNo='" + budNo + "' and  VStatus='0' ");
			budgetVouchers = baseService.findObjList("from BudgetVoucher where VNo='" + budNo + "'and  VStatus='0' ");
			request.put("vNo", budNo);
			request.put("vDate", budDate);
		}
		request.put("accvList", accountingVouchers);
		request.put("budvList", budgetVouchers);
		if(accNo!=null&&"".equals(accNo)){
			if(accNo.contains("zdh")){
				return "voucherDetails1";
			}
		}
		if(budNo!=null&&"".equals(budNo)){
			if(budNo.contains("zdh")){
				return "voucherDetails1";
			}
		}
		return "voucherDetails";
	}

	/**
	 * 查询财务凭证
	 * 
	 * @return List<AccountingVoucher>
	 * @throws Exception
	 */
	public String selectAccountingVoucher() throws Exception {
		accountingVouchers = baseService.findObjList("from AccountingVoucher where VStatus='0' ORDER BY SNo ASC");
		request.put("pjList", accountingVouchers);
		return "selectAccountingVoucher";
	}

	/**
	 * 原始凭证
	 * @return List<AccountingVoucher>
	 * @throws Exception
	 */
	public String selectAccountVoucher() throws Exception {
		return "accountShow";
	}
	/**
	 * 原始凭证
	 * @return List<AccountingVoucher>
	 * @throws Exception
	 */
	public String noChangeAccountVoucher() throws Exception {		
		return "noChangeAccountShow";
	}
	/**
	 * 转换失败的财务凭证分页查询
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public String NoChangeAccountVoucherList() throws Exception{
		String sql = "select count(id) from AccountingVoucher where VStatus='0' and transtype='2' ";
		//获取总条数
		int totalCount = PaginationUtil.getTotalCount(sql);
		String hql="from AccountingVoucher b where  b.transtype='2'  and  b.VStatus='0'  ORDER BY (b.VNo+0) ASC";
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
	 * 记账凭证分页查询
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public String showAccountVoucherList() throws Exception{
		String sql = "select count(id) from AccountingVoucher b where b.VStatus='0'";
		String hql="from AccountingVoucher b  where b.VStatus='0' ORDER BY b.createTime DESC";

		if(!"".equals(param)&&param!=null){
			sql="select count(id) from AccountingVoucher b where b.VStatus='0' and (b.VNo like'%"+param+"%' or b.VAbstract like '%"+param+"%' or b.VHandleName like '%"+param+"%' or b.SName like '%"+param+"%' or b.VCreditAmount like '%"+param+"%' or b.VDebitAmount like '%"+param+"%')";
			hql="from AccountingVoucher b where b.VStatus='0' and (b.VNo like '%"+param+"%' or b.VAbstract like '%"+param+"%' or b.VHandleName like '%"+param+"%' or b.SName like '%"+param+"%' or b.VCreditAmount like '%"+param+"%' or b.VDebitAmount like '%"+param+"%') ORDER BY b.createTime DESC";
		}
	
		//获取总条数
		int totalCount = PaginationUtil.getTotalCount(sql);
		//String hql="from AccountingVoucher b ORDER BY (b.VNo+0) ASC";
		//分页
		List list= PaginationUtil.pagerff(hql,totalCount, page,limit,null);
		JSONArray json = JSONArray.fromObject(list);
		jsonmap=new HashMap<Object, Object>();
		jsonmap.put("code", "0");
		jsonmap.put("msg", "");
		jsonmap.put("count",totalCount);
		jsonmap.put("data", json);
		return "accountList";
	}
	
	
	/**
	 * 
	 * @param object
	 * @param param
	 * @return
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws Exception
	 */
	public String getSqlStr(Object object,String param) throws NoSuchMethodException, SecurityException, Exception{
		List<Object> list = GetObjectClass.getClass(object);
		String sqlStr="";
		for(Object obj:list){
			sqlStr+=" or "+obj +" like '%"+param+"%'";
		}
		System.out.println(sqlStr);
		return sqlStr;
	}
	
	/**
	 * 财务凭证主页
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public String showAccountList() throws Exception{
		String sql ="";
		String hql ="";
		if(param==null||"".equals(param)){
			sql = "select count(id) from AccountingVoucher b where b.VStatus='0' ";
			hql="from AccountingVoucher b where b.VStatus='0' ORDER BY b.createTime DESC";
		}else{		
			sql="select count(id) from AccountingVoucher b where b.VStatus='0' and (b.VNo like'%"+param+"%' or b.VAbstract like '%"+param+"%' or b.VHandleName like '%"+param+"%' or b.SName like '%"+param+"%' or b.VCreditAmount like '%"+param+"%' or b.VDebitAmount like '%"+param+"%')";
			hql="from AccountingVoucher b where b.VStatus='0' and (b.VNo like '%"+param+"%' or b.VAbstract like '%"+param+"%' or b.VHandleName like '%"+param+"%' or b.SName like '%"+param+"%' or b.VCreditAmount like '%"+param+"%' or b.VDebitAmount like '%"+param+"%') ORDER BY b.createTime DESC";
		}		
		//获取总条数
		int totalCount = PaginationUtil.getTotalCount(sql);
		//String hql="from AccountingVoucher b ORDER BY (b.VNo+0) ASC";
		//分页
		List list= PaginationUtil.pagerff(hql,totalCount, page,limit,null);
		
		JSONArray json = JSONArray.fromObject(list);
		jsonmap=new HashMap<Object, Object>();
		jsonmap.put("code", "0");
		jsonmap.put("msg", "");
		jsonmap.put("count",totalCount);
		jsonmap.put("data", json);
		return "accountList";
	}
	
	/**
	 * @author Can 2018/8/31
	 * @return 新财务到预算的转换规则
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public String rulesSelect_socket() throws Exception{ 
	int    status =200;	
	String none_num ="";
	String succ_num ="";
	String fail_num ="";
	System.out.println("前端传入的请求参数:"+ids);
	boolean flag=false;
	    //1.为批量或单条操作准备数据
	if(ids!=null&&ids.equals("all")){
			vnostcp=null;
		}
	else if(ids!=null&&!ids.equals("all")){
			//判断部分中的数据1.-批量，还是2.-单条
				if(!ids.contains(",")){  			  //如果是单条
	    		vnostcp=new String[]{ids};		  //正常转换，并准备好数据	    		               
	    		}else{   
	    		vnostcp= ids.split(",");   		  //多条凭证信息  	    				    			
	    		}	
	}else{
			 ids="repeat";
		}
	
	if(ids.equals("repeat")){
			message="请不要重复转换数据";
			status=400;
		}
	
	else{
	    //初步准备好要转换的数据
	   String sendData = MyClient.queryNewVoucherGB(baseService,vnostcp);	   
	   //得到标准数据结构
	   if(sendData!=null){
		   sendData=sendData.substring(0, sendData.length()-1);  //去掉最后边得“#”  	
	   }else{
	    	message="数据准备失败";
	    	status=400;
	    	flag=true;
	   }
	 //生成本地文件，进行文件操作  
	   
	//进行NIO-tcpSocket通信
	if(!flag){
		System.out.println("发送的数据:"+sendData);
		//去掉“1#”
		//sendData = sendData.replaceFirst("1#", "").trim();
		//1.写入文件.txt中
		//获取webapps
				HttpServletRequest res=ServletActionContext.getRequest();
				String realPath = res.getSession().getServletContext().getRealPath("/");
				String parentpath = new File(realPath).getParent();//获取项目的上一级目录
				String trimStr = UUID.randomUUID().toString().replaceAll("-", "").trim();
				FileUtils.writeFile(parentpath+"/"+trimStr+".txt",sendData,false);
				System.out.println("文件写入成功");
		//2.发送文件绝对路径给服务器  格式如下:2#F://研三工作//bill_project//code//Documents_transform//DT_DLL//x64//Release//input.txt#F://研三工作//bill_project//code//Documents_transform//DT_DLL//x64//Release//output.txt
		String path="2#"+parentpath+"//"+trimStr+".txt#"+parentpath+"//output.txt";
		
		//sendData="1#"+sendData;		
	  try{  			
	    String resStr = new BootStrap().BootStrapClient("127.0.0.1", 8829, path+"\0");
	    System.out.println("接收服务端数据");
	    String recv="";
		//处理返回结果
	    if(resStr.contains("data_wrong")){ //数据格式异常
	    	message="数据格式异常";
	    	status=500;
	    }
	    else if(resStr.contains("flag_wrong")){ //转换标识错误 
	    	message="数据标识错误";
	    	status=500;
	    }else{//数据转换正常，去读文件 
	    	recv = FileUtils.readFile(parentpath+"//output.txt","GBK").toString(); 		
	    }
	    //发送给服务端的原始数据集
	    List<List<AccountingVoucher>> resultList = (List<List<AccountingVoucher>>) ActionContext.getContext().getSession().get("resultList");
	    if(!StringUtils.isEmpty(recv)){//none_num:0 succ_num:0 fail_num:2    #transform_fail#transform_fail
	    	String[] bs = recv.split("#");	    	
	    	List<String> numList = new StringResult().getResultNum(bs[0]);  //获取返回结果
	    	 none_num = numList.get(0);
	    	 succ_num = numList.get(1);
	    	 fail_num = numList.get(2);
	    	 status=200;
	    }
	    //处理返回结果的字符集
	    String[] result_per = recv.split("#");
	    for (int i = 1; i < result_per.length; i++) {
	    	if(result_per[i].equals("transform_fail")){
				List<AccountingVoucher> list = resultList.get(i-1);
				String vno = list.get(0).getVNo();
				//设置转换失败标志
				baseService.zxSql("update accounting_voucher set TRANSTYPE='2' where V_NO="+vno+" ");				
			}
	    	else if(result_per[i].equals("transform_none")){
	    		List<AccountingVoucher> list = resultList.get(i-1);
				String vno = list.get(0).getVNo();
				//假删除
				baseService.zxSql("update accounting_voucher set TRANSTYPE='4' where V_NO="+vno+" ");
	    	}
	    	else{  //转换成功的凭证信息
	    		String[] halfBudge = result_per[i].split(";");
				if(halfBudge.length!=0&&halfBudge!=null){
					boolean result=false;
					for (int j = 0; j < halfBudge.length; j++){
						BudgetVoucher bv = new BudgetVoucher();	
						String msgs = halfBudge[j];
						String[] element = msgs.split(",");	
						int length = element.length;
					if(element!=null){
						bv.setVDate(element[0]);
						bv.setVNo(element[1]);
						accNo=element[1];
						bv.setVAbstract(element[2]);
						if(element[3].equals("贷")){
							bv.setVDebitAmount("0");
							bv.setVCreditAmount(element[4]);
						}else{
							bv.setVDebitAmount(element[4]);
							bv.setVCreditAmount("0");
						}
						bv.setTNo(element[5]);
						bv.setTName(element[6]);							
						if(length==9){
							bv.setSNo(element[7]);
							bv.setSName(element[8]);
						}
					}						
						result = baseService.saveOrUpdate(bv);
						if(!result){
							break;
						}
					}
					if(result){  //同一个事物，如果成功都操作
					Session session = HibernateSessionFactory.getSessionFactory().getCurrentSession();
					session.beginTransaction();
					Query query = session.createQuery("update AccountingVoucher t set t.transtype = '1' where t.VNo = '"+accNo+"'");
					query.executeUpdate();
					session.getTransaction().commit();
					}
				}
	    	}
	    }
	  }catch(Exception e){
			e.printStackTrace();
	  }finally {
			System.gc();
	  }	  
	}	
   }
		msgMap=new HashMap<>();
		msgMap.put("status",status);
	    if(status==200){	    	
	    	msgMap.put("none_num", none_num);
	    	msgMap.put("succ_num", succ_num);
	    	msgMap.put("fail_num", fail_num);
	    }else{
	    	msgMap.put("message",message);	
	    }
		return "vouvhertranssuccess";
}
	
	
	/**
	 * 查询预算凭证
	 * 模糊查询
	 * @re  turn List<AccountingVoucher>
	 * @throws Exception
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String showBudgetVoucher() throws Exception {		
		
		//budgetVouchers = baseService.findObjList("from BudgetVoucher where VStatus=0 ORDER BY SNo ASC");
		String hql="from BudgetVoucher b where b.VStatus='0' ORDER BY (b.VNo+0) ASC ";
		String sql = "select count(id) from BudgetVoucher b where b.VStatus='0'";
		if(!"".equals(param)&&param!=null){
			sql="select count(id) from BudgetVoucher b where b.VStatus='0' and (b.VNo like'%"+param+"%' or b.VAbstract like '%"+param+"%' or b.VHandleName like '%"+param+"%' or b.SName like '%"+param+"%' or b.VCreditAmount like '%"+param+"%' or b.VDebitAmount like '%"+param+"%')";
			hql="from BudgetVoucher b where b.VStatus='0' and (b.VNo like '%"+param+"%' or b.VAbstract like '%"+param+"%' or b.VHandleName like '%"+param+"%' or b.SName like '%"+param+"%' or b.VCreditAmount like '%"+param+"%' or b.VDebitAmount like '%"+param+"%') ORDER BY (b.VNo+0) ASC";
		}
		int totalCount = PaginationUtil.getTotalCount(sql);//总数据
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
	 * 查询财务凭证
	 * 
	 * @return List<AccountingVoucher>
	 * @throws Exception
	 */
	public String selectBudgetVoucher() throws Exception {
		budgetVouchers = baseService.findObjList("from BudgetVoucher where V_STATUS=0 ORDER BY SNo ASC");
		request.put("pjList", budgetVouchers);
		return "selectBudgetVoucher";
	}
	

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String test12() throws Exception {
		oldAccountingVouchers = baseService.findObjList("from OldAccountingVoucher ORDER BY SNo ASC");
		int totalCount=oldAccountingVouchers.size();//总数据
		if(limit!=0 &&page!=0) {
			oldAccountingVouchers=new ListSubPage().fenyeObj(oldAccountingVouchers, limit, page);
		}
		
		JSONArray json = JSONArray.fromObject(oldAccountingVouchers);   
		jsonmap=new HashMap<Object, Object>();
		jsonmap.put("code", "0");
		jsonmap.put("msg", "");
		jsonmap.put("count",totalCount);
		jsonmap.put("data", json);
		return "jsonmap";
	}
	
	/**
	 * 查询财务凭证
	 * 
	 * @return List<AccountingVoucher>
	 * @throws Exception
	 */
	public String selectOldAccountingVoucher() throws Exception {
		oldAccountingVouchers = baseService.findObjList("from OldAccountingVoucher ORDER BY SNo ASC");
		request.put("pjList", oldAccountingVouchers);
		return "selectOldAccountingVoucher";
	}
	
	public String test() throws Exception {
		oldAccountingVouchers = baseService.findObjList("from OldAccountingVoucher ORDER BY SNo ASC");
		return "test";
	}
	/**
	 * 根据V_NO（凭证编号）删除新财务会计的凭证数据
	 * 
	 * @return ture or false
	 * @throws Exception
	 */
	public String deleteOldAccVByPrimaryKey() throws Exception {
		String[] vnos=ids.split(",");
		String vno="";
		for(String no:vnos){
			vno+="'"+no+"'"+",";
		}
		vno=vno.substring(0,vno.length()-1);
		boolean flag = baseService.zxSql("update accounting_voucher set V_Status=1 where V_NO in "+"("+vno+")"+" ");
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
	 * 根据V_NO（凭证编号）删除财务会计的凭证数据
	 * 
	 * @return ture or false
	 * @throws Exception
	 */
	public String deleteNewBudgeVByPrimaryKey() throws Exception {
		deleteMap=new HashMap<>();
		String[] vnos=ids.split(",");
		String vno="";
		boolean zxSql=false;
		for(String no:vnos){
			vno+="'"+no+"'"+",";
		}
		vno=vno.substring(0,vno.length()-1);
		//真删除
		//boolean flag = baseService.zxSql("delete from budget_voucher where V_NO in "+"("+vno+")"+" ");
		//假删除
		//boolean flag = baseService.zxSql("update budget_voucher set V_Status='1' where V_NO="+vno+" ");
		//批量更新
		boolean flag = baseService.zxSql("update budget_voucher SET V_Status ='1' WHERE V_NO in "+"("+vno+")"+" ");
		if(flag){
			zxSql = baseService.zxSql("update accounting_voucher SET TRANSTYPE='0' WHERE V_NO in "+"("+vno+")"+" AND V_Status='0' ");
		}
		else{
			deleteMap.put("status", 403); //sql执行失败返回403
			return "deleteMap";
		}
		if (zxSql) {
			deleteMap.put("status", 200);
			return "deleteMap";
		} else {
			deleteMap.put("status", 500);
			return "deleteMap";
		}
	}
	/**
	 * 根据id删除财务会计的凭证数据
	 * 
	 * @return ture or false
	 * @throws Exception
	 */
	public String deleteAccVById() throws Exception {
		boolean flagaccv = baseService.zxSql("delete from accounting_voucher where id='"+id+"'");
		if (flagaccv) {
			return "accountShow";
		} else {
			return "删除失败";
		}
	}
	/**
	 * 修改数据
	 * @return
	 * @throws Exception 
	 */
	public String editData() throws Exception{
		boolean flag = baseService.saveOrUpdate(accv);
		if(flag){
			return "accountShow";
		}else{
			return "修改失败";
		}
	}
	/**
	 * 根据id删除财务会计的凭证数据
	 * 
	 * @return ture or false
	 * @throws Exception
	 */
	public String deleteAccVByPrimaryKey() throws Exception {
		msgMap=new HashMap<>();
		boolean zxSql = baseService.zxSql("update accounting_voucher set V_STATUS='1' WHERE V_STATUS='0' ");
		//boolean flagbud = baseService.zxSql("delete from budget_voucher");
		//boolean flagoldaccv = baseService.zxSql("delete from old_accounting_voucher");
		//if (flagaccv && flagbud && flagoldaccv) {
		if(zxSql){
			msgMap.put("message", 200);
		}else {
			msgMap.put("message", "error");
		}
			return "vouchernull";
	}

	/**
	 * 根据id删除预算的凭证数据
	 * 
	 * @return ture or false
	 * @throws Exception
	 */
	public String deleteBudVy() throws Exception {
		msgMap=new HashMap<>();
		boolean zxSql=false;
		boolean flag = baseService.zxSql("update  budget_voucher set V_STATUS='1' where V_STATUS='0' ");  //更改标志为 ‘0’ 改为 ‘1’
		if(flag){
			zxSql = baseService.zxSql("update accounting_voucher set TRANSTYPE='0' WHERE V_STATUS='0'");  //0为没有转换过的 ，‘1’为转换过的
		}
		else{
			msgMap.put("message", "fail");
		}
		if(zxSql){			
			msgMap.put("message", 200);
		}else{
			msgMap.put("message","error");
		}
		return "deletebudget";
	}
	
	/**
	 * 根据id删除预算的凭证数据
	 * 
	 * @return ture or false
	 * @throws Exception
	 */
	public String deleteBudVyPrimaryKey() throws Exception {
		boolean flag = baseService.zxSql("delete from budget_voucher where id='"+id+"'");
		if (flag) {
			return "voucherManagementList";
		} else {
			return "删除失败";
		}
	}
	/**
	 * 生成凭证编号
	 * @return
	 * @throws Exception 
	 */
	public String voucherNum(AccountingVoucher voucher) throws Exception{
		//凭证编号生成规则  各所首字母缩写+年+月+日+六位随机数
		String num="zdh";
		//根据备注判断收、付、转
		String vAbstract = voucher.getVAbstract();
		if(vAbstract.indexOf("经费转拨")!=-1 || vAbstract.indexOf("收入")!=-1 || vAbstract.indexOf("退款") !=-1 || vAbstract.indexOf("收") !=-1){
			num+="S";
		}else if(vAbstract.indexOf("报销")!=-1 || vAbstract.indexOf("付款")!=-1 || vAbstract.indexOf("冲销") !=-1 || vAbstract.indexOf("支付") !=-1 || vAbstract.indexOf("劳务费") !=-1){
			num+="F";
		}else{
			num+="Z";
		}
		//生成时间
		Date currDate=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMM");
		num+=sdf.format(currDate);
		//生成六位随机数
		String returnCard = returnCard();
		num+=returnCard;
		return num;
	}
	public String returnCard() throws Exception{
	       String cardNnumer=getCard();//调用生成随机数的方法：这里以6位为例
	       accountingVouchers = baseService.findObjList("from AccountingVoucher where VNo like '%"+cardNnumer+"%'");//生成的随机数进入数据库中查询一下，看时候有相同的。
	       if(accountingVouchers.size()!=0){//如果有相同的数据
	          return returnCard();//再次调用方法，生成一个随机数
	       }else{//否则
	           return cardNnumer;//这个数据我就要
	       }
	   }
   //生成随机数
   public static String getCard(){
       Random rand=new Random();//生成随机数
        String cardNnumer="";
        for(int a=0;a<6;a++){
        cardNnumer+=rand.nextInt(10);//生成6位数字
        }
       return cardNnumer;
	  }

	
	
	/**
	 * 新财务凭证转化预算凭证              ‘全部凭证一键转换’||部分一键转换
	 */
	public String rulesSelect() throws Exception {
		//查询规则表，按规则进行后续转化（必做的步骤）
		voucherRuleNames = baseService.findObjList("from VoucherRuleName");
		Map<String, VoucherRuleName> vMap=new HashMap<>();
		for(VoucherRuleName vRuleName:voucherRuleNames){
			String tName=vRuleName.getFinBrrowName()+vRuleName.getFinLoanName();
			vMap.put(tName, vRuleName);
		}
		Long startTime=System.currentTimeMillis();
		boolean flag = false;
		int sign=0;
		//1.先判断是要全部转换还是部分转换
		if(vnos==null){  //没有传递单个参数，要执行全部转换
		//2.根据要求查询数据
			accountingVouchers = baseService.findObjList("from AccountingVoucher");//得到所有的数据列表集合
			sign=1;
			flag = baseService.zxSql("delete from Budget_Voucher");	     //清空之前已经转化的数据
		}
		else{ //查询部分转换
			accountingVouchers = baseService.findObjList("from AccountingVoucher where VNo = '"+vnos+"' ");
			budgetVouchers = baseService.findObjList("from BudgetVoucher where VNo = '"+vnos+"' ");
			if(budgetVouchers==null||budgetVouchers.size()<=0){   //预算凭证表中为空
				flag=true;
			}
		  }
		 //3.构建数据转换集合
			Map<String, List<AccountingVoucher>> map=groupList(accountingVouchers);
			List<String> keys = new ArrayList<String>(map.keySet());  
		if(flag){
		  int total=0;    //初始化单个线程处理数据量
		  if(sign==1){    //转换大量数据使用多线程
			if(keys.size()>100000){
				total=1001;
			}else if(keys.size()>5000&&keys.size()<100000){
				total=501;
			}else if(keys.size()>1000&&keys.size()<5000){
				total=301;
			}else if(keys.size()<1000){
				total=100;
			}	
		}
		else{ 
			if(keys.size()>1000&&keys.size()<5000){
				total=301;
			}else if(keys.size()<1000){
				total=1;
			}
		}
		  int j=0;
			List<VocherChangeThread> lvList=new ArrayList<>();
			for(;j<keys.size()-total;j+=total-1){
				    VoucherThread aThread=new VoucherThread();
					VocherChangeThread thread=null;
				    thread=new VocherChangeThread(aThread,keys.subList(j, j+total),map,vMap,baseService);
					lvList.add(thread);
					aThread=null;
					thread=null;
				}
			    VoucherThread aThread=new VoucherThread();
				VocherChangeThread thread=new VocherChangeThread(aThread,keys.subList(j, keys.size()),map,vMap,baseService);
				lvList.add(thread);	
				ExecutorService e =Executors.newFixedThreadPool(lvList.size());
			for(VocherChangeThread v:lvList){
				e.submit(v);
			}
			e.shutdown();
			while(true){
				if(e.isTerminated()){
					e=null;
					aThread=null;
					thread=null;
					lvList=null;					
					message="转换成功";
					System.gc();
					break;
				}
			}				
			long endTime=System.currentTimeMillis();
			System.out.println((endTime-startTime)/1000+"秒");
		}
		return "voucherManagementList";
	}
	
	public void saveBudget(AccountingVoucher accountingVoucher,BaseService baseService ,int status) throws Exception {
		BudgetVoucher budgetVoucher=new BudgetVoucher();
		// 六级科目代码
		budgetVoucher.setSNo(accountingVoucher.getSNo());
		// 六级科目名称
		budgetVoucher.setSName(accountingVoucher.getSName());
		/*-----以下内容保持不变------*/
		budgetVoucher.setVDate(accountingVoucher.getVDate());
		// 凭证编号
		budgetVoucher.setVNo(accountingVoucher.getVNo());
		// 摘要
		budgetVoucher.setVAbstract(accountingVoucher.getVAbstract());
		// 借方金额
		budgetVoucher.setVDebitAmount(accountingVoucher.getVDebitAmount());
		// 贷方金额
		budgetVoucher.setVCreditAmount(accountingVoucher.getVCreditAmount());
		// 课题段代码
		budgetVoucher.setTNo(accountingVoucher.getTNo());
		// 课题段名称
		budgetVoucher.setTName(accountingVoucher.getTName());
		// 课题所属部门名称
		budgetVoucher.setTOrganization(accountingVoucher.getTOrganization());
		budgetVoucher.setVStatus(status);
		baseService.saveOrUpdate(budgetVoucher);
	}
	
	public void saveAccount(AccountingVoucher accountingVoucher,BaseService baseService,int status) throws Exception {
		String hql="update accounting_voucher set V_STATUS="+status+" WHERE ID='"+accountingVoucher.getId()+"'";
		baseService.zxSql(hql);
	}
	
	/**
	 * 单条财务凭证转化预算凭证
	 */
	public String voucherChangeTO() throws Exception {
		List<AccountingVoucher> list=new ArrayList<AccountingVoucher>();
		String nos = "("+vnos+")";
		accountingVouchers = baseService.findObjList("from AccountingVoucher where VNo in "+nos+" ");
		list.add(accv);
		for (AccountingVoucher accountingVoucher : list) {
			String accvSNo = accountingVoucher.getSNo();// 科目编码
			String accvSName=accountingVoucher.getSName();//科目名称
			if (StringUtils.isNotEmpty(accvSNo)) {
				vrList = baseService.findObjList("from VoucherRules where vfinancialNo='" + accvSNo + "' and vfinancialName='"+accvSName+"'");
				if (!vrList.isEmpty() && vrList != null) {
					for (int v = 0; v < vrList.size(); v++) {
						BudgetVoucher budgetVoucher = new BudgetVoucher();
						String SNo = vrList.get(v).getVbudgetaryNo();
						String SName = vrList.get(v).getVbudgetaryName();
							if (StringUtils.isNotEmpty(SNo) && StringUtils.isNotEmpty(SName)) {
								// 六级科目代码
								budgetVoucher.setSNo(SNo);
								// 六级科目名称
								budgetVoucher.setSName(SName);
								/*-----以下内容保持不变------*/
								// 日期
								budgetVoucher.setVDate(accountingVoucher.getVDate());
								// 凭证编号
								budgetVoucher.setVNo(accountingVoucher.getVNo());
								// 摘要
								budgetVoucher.setVAbstract(accountingVoucher.getVAbstract());
								// 借方金额
								budgetVoucher.setVDebitAmount(accountingVoucher.getVDebitAmount());
								// 贷方金额
								budgetVoucher.setVCreditAmount(accountingVoucher.getVCreditAmount());
								// 课题段代码
								budgetVoucher.setTNo(accountingVoucher.getTNo());
								// 课题段名称
								budgetVoucher.setTName(accountingVoucher.getTName());
								// 课题所属部门名称
								budgetVoucher.setTOrganization(accountingVoucher.getTOrganization());
								try {
									baseService.saveOrUpdate(budgetVoucher);
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								message = "转化成功";
							} else {
								message = "新财务凭证科目编号为"+accvSNo+"的凭证不存在对应的预算凭证";
							}
					}
				} else {
					message = "科目编码为" + accvSNo + "的财务凭证转换失败";
					continue;
				}
			}
		}
		return "message";
	}

	/**
	 * 旧财务凭证的导入并且转换新财务数据
	 * @return
	 */
	@SuppressWarnings("static-access")
	public String VoucherExcelImport() throws Exception {
		Upload ie = new Upload();
		try {
			// 上传到本地
			String pathName = ie.uploadFile(System.currentTimeMillis() + "_" + filename, "oldAccountingVoucher",file.get(0));
			ReadExcel re = new ReadExcel();
			List<List<String>> list = re.read(pathName, 0);// 忽略前5行
			uploadMap=new HashMap<>();
			// 遍历读取结果
			if (list != null && list.size() > 2) {// 行数
			
				List<VocherThread> lvList=new ArrayList<>();
				int total=0;
				if(list.size()>100000){
					total=3000;//1501
				}else if(list.size()>20000&&list.size()<100000){
					total=1001;
				}else{
					total=300;
				}
				int j=0;
				System.out.println("total总数："+total);
				List<OldAccountingVoucher> oldAccVouchers=null;
				List<AccountingVoucher> accVouchers=null;
				for(;j<list.size()-total;j+=total-1){
					 oldAccVouchers=new ArrayList<>();
					 accVouchers=new ArrayList<>();
						VoucherThread aThread=new VoucherThread();
						VocherThread thread=null;
					    thread=new VocherThread(aThread,list.subList(j, j+total),baseService,oldAccVouchers,accVouchers);
						lvList.add(thread);
					}
				oldAccVouchers=new ArrayList<>();
				 accVouchers=new ArrayList<>();
					VoucherThread aThread=new VoucherThread();
					VocherThread thread=new VocherThread(aThread,list.subList(j, list.size()),baseService,oldAccVouchers,accVouchers);
					lvList.add(thread);	
					ExecutorService e =Executors.newFixedThreadPool(lvList.size());
				for(VocherThread v:lvList){
					e.submit(v);
				}
				 e.shutdown();  
			        while (true) {  
			            if (e.isTerminated()) {  
			            	message="200"; 
			                break;  
			            }  
			            Thread.sleep(20);  
			        } 
			} else {
				message="100";
			}
			return "message";
		} catch (Exception e) {
			e.printStackTrace();
			message="500";
			return "message";
		}
	}
	
	/**
	 * 旧财务凭证的导入并且转换新财务数据
	 * @can
	 * @return
	 */
	@SuppressWarnings("static-access")
	public String VoucherExcelImport2() throws Exception {
		Upload ie = new Upload();
		try{
			// 上传到本地
			String pathName = ie.uploadFile(System.currentTimeMillis() + "_" + filename, "oldAccountingVoucher",file.get(0));
			ReadExcel re = new ReadExcel();			
			List<List<String>> list = re.read(pathName, 0);//忽略标题行
			InitThreadPool.exec(list,baseService,oldAccountingVouchers,accountingVouchers);
			message="200";
			return "message";
		}catch (Exception e) {
			e.printStackTrace();
			message="500";
			return "message";
		}
	}
	
    @SuppressWarnings({ "static-access","unused" })
	public void exportJson()throws Exception {  
    	Upload ie = new Upload();
			// 上传到本地
	    String pathName = ie.uploadFile(System.currentTimeMillis() + "_" + filename, "oldAccountingVoucher",file.get(0));
			
        String JsonContext = exportJsonUtil.exportJson(pathName);
        //JSONObject json = new JSONObject(JsonContext);  
        /*JSONArray datas=json.getJSONArray("记账凭证");// 找到features的json数组  
//      JSONArray datas = json.getJSONObject("data").getJSONArray("rows");
        for(int  i = 0; i < datas.length(); i++){  
            JSONObject jsonObject = datas.getJSONObject(i);  
            String VoucherDate=jsonObject.get("VoucherDate").toString();
            String VoucherId=jsonObject.get("VoucherID").toString();
            String EventAbstract=jsonObject.get("EventAbstract").toString();
            String CreditAccountName=jsonObject.getString("CreditEntry").toString();
//          String VoucherId=jsonObject.get("VoucherID").toString();
            System.out.println("[" + i + "]VoucherDate=" + VoucherDate);  
            System.out.println("[" + i + "]VoucherId=" + VoucherId);  
        }  
        oldAccountingVouchers = (List<OldAccountingVoucher>) datas;
        for(int i = 0, j = oldAccountingVouchers.size(); i < j ; i++){  
            System.out.println(oldAccountingVouchers.get(i));  
        }  
        for(OldAccountingVoucher temp: oldAccountingVouchers){  
        	baseService.saveOrUpdate(temp);
        }  */
    }  
    
    
    /**
     * 导入json文件
     * @throws Exception
     */
    @SuppressWarnings({ "static-access","unused" })
	public void importJsonFile()throws Exception {  
    //	List str = LoadCache.loadCache();
    	Upload ie = new Upload();
			// 上传到本地
	    String pathName = ie.uploadFile(System.currentTimeMillis() + "_" + filename, "oldAccountingVoucher",file.get(0));
		StringBuilder strJson=FileUtils.readFile(pathName,"GBK");
		if (strJson != null) {
			JSONArray datas=JsonConvert.process(strJson);
            List<OldAccountingVoucher> list = new ArrayList<>();
	        for (int i = 0; i < datas.size(); i++) {
	            JSONObject obj = datas.getJSONObject(i);
	            String VoucherID = obj.getString("VoucherID");
	            String VoucherDate = obj.getString("VoucherDate");
	            String EventAbstract =   obj.getString("EventAbstract");
	            
	            if(obj.containsKey("CreditEntry")){//借方
	            	JSONArray creditDatas = obj.getJSONArray("CreditEntry");
	            	for(int j=0;j<creditDatas.size();j++){
	            		OldAccountingVoucher oldAccountingVoucher = new OldAccountingVoucher();
	            		//取值
	            		String AccountName = creditDatas.getJSONObject(j).getString("AccountName");
	    	            String AccountID = creditDatas.getJSONObject(j).getString("AccountID");
	    	            String Amount =   creditDatas.getJSONObject(j).getString("Amount");
	    	            String ProjectID =   creditDatas.getJSONObject(j).getString("ProjectID");
	    	            String DepartmentID =   creditDatas.getJSONObject(j).getString("DepartmentID");//目前数据库中没有部门id
	    	           
	    	         /*****************************************/
	    	            //设置值
	    	            oldAccountingVoucher.setVCreditAmount(Amount);//借方金额
	    	            oldAccountingVoucher.setVNo(VoucherID);
	    	            oldAccountingVoucher.setVDate(VoucherDate);
	    	            oldAccountingVoucher.setVAbstract(EventAbstract);
	    	            oldAccountingVoucher.setSName(AccountName);
	    	            oldAccountingVoucher.setSNo(AccountID);
	    	            oldAccountingVoucher.setPId(ProjectID);
//	    	            list.add(oldAccountingVoucher);
	    	        	baseService.saveOrUpdate(oldAccountingVoucher);
	            	}
	            }
	            if(obj.containsKey("DebitEntry")){//贷方
	            	JSONArray debitEntry = obj.getJSONArray("DebitEntry");
	            	for(int j=0;j<debitEntry.size();j++){
	            		OldAccountingVoucher oldAccountingVoucher = new OldAccountingVoucher();
	            		String AccountName = debitEntry.getJSONObject(j).getString("AccountName");
	    	            String AccountID = debitEntry.getJSONObject(j).getString("AccountID");
	    	            String Amount =   debitEntry.getJSONObject(j).getString("Amount");
	    	            String ProjectID =   debitEntry.getJSONObject(j).getString("ProjectID");
	    	            String DepartmentID =   debitEntry.getJSONObject(j).getString("DepartmentID");//目前数据库中没有部门id
	    	            /*****************************************/
	    	            oldAccountingVoucher.setVDebitAmount(Amount);
	    	            oldAccountingVoucher.setVNo(VoucherID);
	    	            oldAccountingVoucher.setVDate(VoucherDate);
	    	            oldAccountingVoucher.setVAbstract(EventAbstract);
	    	            oldAccountingVoucher.setSName(AccountName);
	    	            oldAccountingVoucher.setSNo(AccountID);
	    	            oldAccountingVoucher.setPId(ProjectID);
//	    	            list.add(oldAccountingVoucher);
	    	        	baseService.saveOrUpdate(oldAccountingVoucher);
	            	}
	            }
	        }  
        } else {
            System.out.println("Read the content is empty!");
            
        }
        
    }  
    
	/**
	 * 
	 * @throws Exception
	 */
    public void downExcel() throws Exception {
		HttpServletResponse response=ServletActionContext.getResponse();
		accountingVouchers= baseService.findObjList("from AccountingVoucher");
		String filename ="财务凭证.xlsx";

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
			String[] titles = new String[] {"期间名称", "凭证编号","摘要","借方金额","贷方金额","课题段代吗","课题段名称","六级科目代码","六级科目名称","课题所属部门名称"};
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
			if (accountingVouchers.size() > 0) {
				for (int j = 0; j < accountingVouchers.size(); j++) {
					// 创建第j+1行
					XSSFRow rowContent = sheet.createRow(j + 1);
					rowContent.setRowStyle(style);
					//期间名称
					rowContent.createCell(0).setCellValue(accountingVouchers.get(j).getVDate());
					//凭证编号
					rowContent.createCell(1).setCellValue(accountingVouchers.get(j).getVNo());
					//摘要
					rowContent.createCell(2).setCellValue(accountingVouchers.get(j).getVAbstract());
					//借方金额
					rowContent.createCell(3).setCellValue(accountingVouchers.get(j).getVDebitAmount());
					//贷方金额
					rowContent.createCell(4).setCellValue(accountingVouchers.get(j).getVCreditAmount());
					//课题段代码
					rowContent.createCell(5).setCellValue(accountingVouchers.get(j).getTNo());
					//课题段名称
					rowContent.createCell(6).setCellValue(accountingVouchers.get(j).getTName());
					//六级科目代码
					rowContent.createCell(7).setCellValue(accountingVouchers.get(j).getSNo());
					//六级科目名称
					rowContent.createCell(8).setCellValue(accountingVouchers.get(j).getSName());
					//课题所属部门名称
					rowContent.createCell(9).setCellValue(accountingVouchers.get(j).getTFundsSources());
				}
			}
			wb.write(os);
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
    
    /**
	 * 
	 * @throws Exception
	 */
    public void noChangeDownExcel() throws Exception {
		HttpServletResponse response=ServletActionContext.getResponse();
		accountingVouchers= baseService.findObjList("from AccountingVoucher where VStatus=0");
		String filename ="转换失败的财务凭证.xlsx";

		OutputStream os = response.getOutputStream();
		response.reset();
		response.setHeader("Content-Disposition",
				"attachment;filename=" + new String(filename.getBytes("GB2312"), "ISO8859-1"));
		response.setContentType("application/msexcel");

		try {
			XSSFWorkbook wb = new XSSFWorkbook();
			// 创建Sheet
			XSSFSheet sheet = wb.createSheet("转换失败的财务凭证列表");
			// 设置单元格默认宽度
			sheet.setDefaultColumnWidth(13);

			XSSFRow row = sheet.createRow(0);
			String[] titles = new String[] {"期间名称", "凭证编号","摘要","借方金额","贷方金额","课题段代吗","课题段名称","六级科目代码","六级科目名称","课题所属部门名称"};
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
			if (accountingVouchers.size() > 0) {
				for (int j = 0; j < accountingVouchers.size(); j++) {
					// 创建第j+1行
					XSSFRow rowContent = sheet.createRow(j + 1);
					rowContent.setRowStyle(style);
					//期间名称
					rowContent.createCell(0).setCellValue(accountingVouchers.get(j).getVDate());
					//凭证编号
					rowContent.createCell(1).setCellValue(accountingVouchers.get(j).getVNo());
					//摘要
					rowContent.createCell(2).setCellValue(accountingVouchers.get(j).getVAbstract());
					//借方金额
					rowContent.createCell(3).setCellValue(accountingVouchers.get(j).getVDebitAmount());
					//贷方金额
					rowContent.createCell(4).setCellValue(accountingVouchers.get(j).getVCreditAmount());
					//课题段代码
					rowContent.createCell(5).setCellValue(accountingVouchers.get(j).getTNo());
					//课题段名称
					rowContent.createCell(6).setCellValue(accountingVouchers.get(j).getTName());
					//六级科目代码
					rowContent.createCell(7).setCellValue(accountingVouchers.get(j).getSNo());
					//六级科目名称
					rowContent.createCell(8).setCellValue(accountingVouchers.get(j).getSName());
					//课题所属部门名称
					rowContent.createCell(9).setCellValue(accountingVouchers.get(j).getTFundsSources());
				}
			}
			wb.write(os);
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
    
    public Map<String, List<AccountingVoucher>> groupList(List<AccountingVoucher> list) {
    	Map<String, List<AccountingVoucher>> skuIdMap = new HashMap<>();
        for (AccountingVoucher aVoucher : list) {
            List<AccountingVoucher> tempList = skuIdMap.get(aVoucher.getVNo());
            /*如果取不到数据,那么直接new一个空的ArrayList**/
            if (tempList == null) {
                tempList = new ArrayList<>();
                tempList.add(aVoucher);
                skuIdMap.put(aVoucher.getVNo(), tempList);
            }
            else {
                /*某个sku之前已经存放过了,则直接追加数据到原来的List里**/
                tempList.add(aVoucher);
            }
        }

		return skuIdMap;
	}
    
    public Map<String, List<AccountingVoucher>> groupList1(List<AccountingVoucher> list) {
    	Map<String, List<AccountingVoucher>> skuIdMap = new HashMap<>();
    	List<AccountingVoucher> bList=new ArrayList<>();
    	List<AccountingVoucher> cList=new ArrayList<>();
    	int i=0;
        for (AccountingVoucher aVoucher : list) {
        	i++;
        	Float creditMoney=Float.valueOf(aVoucher.getVCreditAmount());
        	Float debiitMoney=Float.valueOf(aVoucher.getVDebitAmount());
        	//System.out.println(i+"+"+creditMoney+"+"+aVoucher.getVNo());
            if (creditMoney>0||debiitMoney<0) {
            	cList.add(aVoucher);
                skuIdMap.put("loan", cList);
            }else if(creditMoney<0||debiitMoney>0){
            	bList.add(aVoucher);
            	skuIdMap.put("brrow", bList);
            }
        }

		return skuIdMap;
	}

	private List<AccountingVoucher> removeDuplicateUser(List<AccountingVoucher> accs) {
		List<AccountingVoucher> totalList=new ArrayList<>();
		Map<String, List<AccountingVoucher>> skuIdMap=groupList1(accs);
		Set<AccountingVoucher> set = new TreeSet<AccountingVoucher>(new Comparator<AccountingVoucher>() {
			@Override
			public int compare(AccountingVoucher o1, AccountingVoucher o2) {
				// 字符串,则按照asicc码升序排列
				if(o1.getSName().contains("-")&&o2.getSName().contains("-")){
					return o1.getSName().substring(0, o1.getSName().indexOf("-")).compareTo(o2.getSName().substring(0, o2.getSName().indexOf("-")));
				}else if(!o1.getSName().contains("-")&&o2.getSName().contains("-")){
					return o1.getSName().compareTo(o2.getSName().substring(0, o2.getSName().indexOf("-")));
				}else if(o1.getSName().contains("-")&&!o2.getSName().contains("-")){
					return o1.getSName().substring(0, o1.getSName().indexOf("-")).compareTo(o2.getSName());
				}else{
					return o1.getSName().compareTo(o2.getSName());
				}
			}
		});
		if(skuIdMap.containsKey("brrow")){
		set.addAll(skuIdMap.get("brrow"));
		totalList.addAll(new ArrayList<AccountingVoucher>(set));
		}
		Set<AccountingVoucher> set1 = new TreeSet<AccountingVoucher>(new Comparator<AccountingVoucher>() {
			@Override
			public int compare(AccountingVoucher o1, AccountingVoucher o2) {
				// 字符串,则按照asicc码升序排列
				if(o1.getSName().contains("-")&&o2.getSName().contains("-")){
					return o1.getSName().substring(0, o1.getSName().indexOf("-")).compareTo(o2.getSName().substring(0, o2.getSName().indexOf("-")));
				}else if(!o1.getSName().contains("-")&&o2.getSName().contains("-")){
					return o1.getSName().compareTo(o2.getSName().substring(0, o2.getSName().indexOf("-")));
				}else if(o1.getSName().contains("-")&&!o2.getSName().contains("-")){
					return o1.getSName().substring(0, o1.getSName().indexOf("-")).compareTo(o2.getSName());
				}else{
					return o1.getSName().compareTo(o2.getSName());
				}
			}
		});
		if(skuIdMap.containsKey("loan")){
		set1.addAll(skuIdMap.get("loan"));
		totalList.addAll(new ArrayList<AccountingVoucher>(set1));
		}
		skuIdMap=null;
		return totalList;
	}
    
/*-------------------------gettings  and settings 方法-----------------------------------*/

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

	public AccountingVoucher getAccv() {
		return accv;
	}

	public void setAccv(AccountingVoucher accv) {
		this.accv = accv;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<AccountingVoucher> getAccountingVouchers() {
		return accountingVouchers;
	}

	public void setAccountingVouchers(List<AccountingVoucher> accountingVouchers) {
		this.accountingVouchers = accountingVouchers;
	}

	public List<OldAccountingVoucher> getOldAccountingVouchers() {
		return oldAccountingVouchers;
	}

	public void setOldAccountingVouchers(List<OldAccountingVoucher> oldAccountingVouchers) {
		this.oldAccountingVouchers = oldAccountingVouchers;
	}

	public List<BudgetVoucher> getBudgetVouchers() {
		return budgetVouchers;
	}

	public void setBudgetVouchers(List<BudgetVoucher> budgetVouchers) {
		this.budgetVouchers = budgetVouchers;
	}

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

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public OldAccountingVoucher getOldaccv() {
		return oldaccv;
	}

	public void setOldaccv(OldAccountingVoucher oldaccv) {
		this.oldaccv = oldaccv;
	}

	public List<VoucherRules> getVrList() {
		return vrList;
	}

	public void setVrList(List<VoucherRules> vrList) {
		this.vrList = vrList;
	}

	public VoucherRules getVr() {
		return vr;
	}

	public void setVr(VoucherRules vr) {
		this.vr = vr;
	}

	/******************** 获取页面变量 ***************************/

	public String getId() {
		return id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAccNo() {
		return accNo;
	}

	public void setAccNo(String accNo) {
		this.accNo = accNo;
	}

	public String getBudNo() {
		return budNo;
	}

	public void setBudNo(String budNo) {
		this.budNo = budNo;
	}

	public String getAccDate() {
		return accDate;
	}

	public void setAccDate(String accDate) {
		this.accDate = accDate;
	}

	public String getBudDate() {
		return budDate;
	}

	public void setBudDate(String budDate) {
		this.budDate = budDate;
	}

	public List<PaySubject> getEconomics() {
		return economics;
	}

	public void setEconomics(List<PaySubject> economics) {
		this.economics = economics;
	}

	public List<PaySubject> getFunctions() {
		return functions;
	}

	public void setFunctions(List<PaySubject> functions) {
		this.functions = functions;
	}

	public AssistAccountDTO getAssistDto() {
		return assistDto;
	}

	public void setAssistDto(AssistAccountDTO assistDto) {
		this.assistDto = assistDto;
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

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public String getSName() {
		return SName;
	}

	public void setSName(String sName) {
		SName = sName;
	}

	public List<AccountingSubject> getKmList() {
		return kmList;
	}

	public void setKmList(List<AccountingSubject> kmList) {
		this.kmList = kmList;
	}

	public AccountingSubject getAccountingSubject() {
		return accountingSubject;
	}

	public void setAccountingSubject(AccountingSubject accountingSubject) {
		this.accountingSubject = accountingSubject;
	}

	public List<VoucherInfo> getVoucherInfoList() {
		return voucherInfoList;
	}

	public void setVoucherInfoList(List<VoucherInfo> voucherInfoList) {
		this.voucherInfoList = voucherInfoList;
	}

	public VoucherInfo getVoucherInfo() {
		return voucherInfo;
	}

	public void setVoucherInfo(VoucherInfo voucherInfo) {
		this.voucherInfo = voucherInfo;
	}

	public List<VoucherRuleBudget> getVoucherRuleBudgets() {
		return voucherRuleBudgets;
	}

	public void setVoucherRuleBudgets(List<VoucherRuleBudget> voucherRuleBudgets) {
		this.voucherRuleBudgets = voucherRuleBudgets;
	}

	public List<VoucherRuleName> getVoucherRuleNames() {
		return voucherRuleNames;
	}

	public void setVoucherRuleNames(List<VoucherRuleName> voucherRuleNames) {
		this.voucherRuleNames = voucherRuleNames;
	}

	public String getVnos() {
		return vnos;
	}

	public void setVnos(String vnos) {
		this.vnos = vnos;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public Map<String, Object> getDeleteMap() {
		return deleteMap;
	}

	public void setDeleteMap(Map<String, Object> deleteMap) {
		this.deleteMap = deleteMap;
	}

	public Map<String, Object> getMsgMap() {
		return msgMap;
	}

	public void setMsgMap(Map<String, Object> msgMap) {
		this.msgMap = msgMap;
	}

	public List<AccountingVoucher> getAccvs() {
		return accvs;
	}

	public void setAccvs(List<AccountingVoucher> accvs) {
		this.accvs = accvs;
	}

	public String getJsonStr() {
		return jsonStr;
	}

	public void setJsonStr(String jsonStr) {
		this.jsonStr = jsonStr;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public Map<String, Object> getUploadMap() {
		return uploadMap;
	}

	public void setUploadMap(Map<String, Object> uploadMap) {
		this.uploadMap = uploadMap;
	}

	public List<BudgetSubject> getYsList() {
		return ysList;
	}

	public void setYsList(List<BudgetSubject> ysList) {
		this.ysList = ysList;
	}

	public List<AccountingSubject> getKjList() {
		return kjList;
	}

	public void setKjList(List<AccountingSubject> kjList) {
		this.kjList = kjList;
	}
	
	public String[] getVnostcp() {
		return vnostcp;
	}

	public void setVnostcp(String[] vnostcp) {
		this.vnostcp = vnostcp;
	}

}
