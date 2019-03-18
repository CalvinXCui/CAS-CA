package com.action;

import com.entity.*;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.service.BaseService;
import com.util.PaginationUtil;
import com.util.ReadExcel;
import com.util.Upload;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 名称 :BudgetAction 
 * 作者 :Calvin 
 * 邮箱 :calvin_it@163.com 
 * 时间 :2018年5月24日 上午10:45:38 
 * 描述
 * :预算凭证操作的action类
 */
public class BudgetAction extends ActionSupport  implements RequestAware{
	private Map<String, Object> request;
	/**
	 * 
	 */
	private static final long serialVersionUID = -7679733144637699028L;
	private Budgetary budgetary;
	private HttpServletRequest req;
	private  Map<Object, Object> jsonmap;
	@SuppressWarnings("rawtypes")
	private List avslist;
	private BaseService baseService;
	private AccountingVoucher accountingVoucher; 
	private List<AccountingVoucher> acco; 
	private List<BudgetVoucher> BudgetVouc; 
	private BudgetVoucher budgetVoucher;
	@SuppressWarnings("rawtypes")
	List budgetList=null;
	private String id;
	private List kmList;
	private static Static staticfc = new  Static();
	private static Incomeexpenditure  incomeexpenditure= new Incomeexpenditure();
	private static Netasset netasset = new Netasset();

	   int p = 4;
	/**
	 * 预算收入支出表
	 * @return
	 * @throws Exception
	 */
	public String selectBudgetList()throws Exception{
		List<BudgetVoucher> BudgetV = new ArrayList<>();
		 budgetList = baseService.findObjList("from Budgetary b order by b.orderId ");
		 request.put("budgetList", budgetList);


		 							//	借				//
		 String sqlfff = "select sum(VDebitAmount)-sum(VCreditAmount) as VDebitAmount from BudgetVoucher where SName like '捐赠支出%'";
		 String totalCounfff = PaginationUtil.scccc(sqlfff);
		 if(totalCounfff  != "null"){
			 String jzzc = totalCounfff; 
			 Double jzzcf = Double.parseDouble(jzzc); 
			 DecimalFormat jzzcdf = new DecimalFormat("0.00"); 
			 String s1 = jzzcdf.format(jzzcf);
			 request.put("totalCounfff", s1);
			 incomeexpenditure.setJzzc(s1);
			 BudgetV = baseService.findObjList("from BudgetVoucher where SName like '捐赠支出%'");
		     String z = BudgetV.get(0).getVDate();
		      z = z.substring(0,p); 
		      int l1 = Integer.parseInt(z);
		      int k1 = 1;
		      int c = l1-k1;
		      String sqlcz = "select sum(VDebitAmount)-sum(VCreditAmount)  as VDebitAmount from BudgetVoucher where SName like '捐赠支出%' and VDate like'"+c+"%'";
		      String totalCountacz = PaginationUtil.scccc(sqlcz);
		      if(totalCountacz  == "null"){
		    	  	totalCountacz = "0.00";
					 request.put("totalCountacz1", totalCountacz);
					 incomeexpenditure.setJzzcqn(totalCountacz);
				 }else{
					 String czysbkcz = totalCountacz; 
					 Double czysbkfcz = Double.parseDouble(czysbkcz); 
					 DecimalFormat czysbkf1cz = new DecimalFormat("0.00"); 
					 String s7cz = czysbkf1cz.format(czysbkfcz);
					 request.put("totalCountacz1", s7cz);
					 incomeexpenditure.setJzzcqn(s7cz);
				 }
			 }else{
				 totalCounfff = "0.00";
				 request.put("totalCounfff", totalCounfff);
				 incomeexpenditure.setJzzc(totalCounfff);
			 }
		 BudgetV.clear();
		 String sqlffff = "select sum(VCreditAmount)-sum(VDebitAmount) as VDebitAmount from BudgetVoucher where SName like '捐赠预算收入%'";
		 String totalCounffff = PaginationUtil.scccc(sqlffff);
		 if(totalCounffff  == "null"){
			 totalCounffff = "0.00";
			 request.put("totalCounffff", totalCounffff);
			 incomeexpenditure.setJzyssr(totalCounffff);
		 }else{
			 String jzyssr = totalCounffff; 
			 Double jzyssrf = Double.parseDouble(jzyssr); 
			 DecimalFormat jzyssrf1 = new DecimalFormat("0.00"); 
			 String s2 = jzyssrf1.format(jzyssrf); 
			 request.put("totalCounffff", s2);
			 incomeexpenditure.setJzyssr(s2);
			 BudgetV = baseService.findObjList("from BudgetVoucher where SName like '捐赠预算收入%'");
			 if(BudgetV != null){
		     String z = BudgetV.get(0).getVDate();
		      z = z.substring(0,p); 
		      int l1 = Integer.parseInt(z);
		      int k1 = 1;
		      int c = l1-k1;
		      String sqlcz = "select sum( VCreditAmount)-sum(VDebitAmount) as VDebitAmount from BudgetVoucher where SName like '捐赠预算收入%' and VDate like'"+c+"%'";
		      String totalCountacz = PaginationUtil.scccc(sqlcz);
		      if(totalCountacz  == "null"){
		    	  	totalCountacz = "0.00";
					 request.put("totalCountacz2", totalCountacz);
					 incomeexpenditure.setJzyssrqn(totalCountacz);
				 }else{
					 String czysbkcz = totalCountacz; 
					 Double czysbkfcz = Double.parseDouble(czysbkcz); 
					 DecimalFormat czysbkf1cz = new DecimalFormat("0.00"); 
					 String s7cz = czysbkf1cz.format(czysbkfcz);
					 request.put("totalCountacz2", s7cz);
					 incomeexpenditure.setJzyssrqn(s7cz);
				 }
			 }
		 }
		 
		 String sqlfffff = "select sum(VCreditAmount)-sum(VDebitAmount) as VDebitAmount from BudgetVoucher where SName like '租金预算收入%'";
		 String totalCounfffff = PaginationUtil.scccc(sqlfffff);
		 if(totalCounfffff  == "null"){
			 totalCounfffff = "0.00";
			 request.put("totalCounfffff", totalCounfffff);
			 incomeexpenditure.setZjyssr(totalCounfffff);
		 }else{
			
			 String zjyssr = totalCounfffff; 
			 Double zjyssrf = Double.parseDouble(zjyssr); 
			 DecimalFormat zjyssrf1 = new DecimalFormat("0.00"); 
			 String s3 = zjyssrf1.format(zjyssrf); 
			 request.put("totalCounfffff", s3);
			 incomeexpenditure.setZjyssr(s3);
			 BudgetV = baseService.findObjList("from BudgetVoucher where SName like '租金预算收入%'");
		     String z = BudgetV.get(0).getVDate();
		      z = z.substring(0,p); 
		      int l1 = Integer.parseInt(z);
		      int k1 = 1;
		      int c = l1-k1;
		      String sqlcz = "select sum( VCreditAmount)-sum(VDebitAmount) as VDebitAmount from BudgetVoucher where SName like '租金预算收入%' and VDate like'"+c+"%'";
		      String totalCountacz = PaginationUtil.scccc(sqlcz);
		      if(totalCountacz  == "null"){
		    	  	totalCountacz = "0.00";
					 request.put("totalCountacz3", totalCountacz);
					 incomeexpenditure.setZjyssrqn(totalCountacz);
				 }else{
					 String czysbkcz = totalCountacz; 
					 Double czysbkfcz = Double.parseDouble(czysbkcz); 
					 DecimalFormat czysbkf1cz = new DecimalFormat("0.00"); 
					 String s7cz = czysbkf1cz.format(czysbkfcz);
					 request.put("totalCountacz3", s7cz);
					 incomeexpenditure.setZjyssrqn(s7cz);
				 }
		 }
		 
		 String sqlllizc = "select sum(VDebitAmount)-sum(VCreditAmount) as VDebitAmount from BudgetVoucher where SName like '利息支出%'";
		 String totalCountalizc = PaginationUtil.scccc(sqlllizc);
		 
		
		 if(totalCountalizc  == "null"){
			 totalCountalizc = "0.00";
			 request.put("totalCountalizc", totalCountalizc);
			 incomeexpenditure.setLxzc(totalCountalizc);
		 }else{
			 
			 String lxzc = totalCountalizc; 
			 Double lxzcf = Double.parseDouble(lxzc); 
			 DecimalFormat lxzc1 = new DecimalFormat("0.00"); 
			 String s4 = lxzc1.format(lxzcf); 
			 request.put("totalCountalizc", s4);
			 incomeexpenditure.setLxzc(s4);
			 BudgetV = baseService.findObjList("from BudgetVoucher where SName like '利息支出%'");
		     String z = BudgetV.get(0).getVDate();
		      z = z.substring(0,p); 
		      int l1 = Integer.parseInt(z);
		      int k1 = 1;
		      int c = l1-k1;
		      String sqlcz = "select sum(VDebitAmount)-sum(VCreditAmount) as VDebitAmount from BudgetVoucher where SName like '利息支出%' and VDate like'"+c+"%'";
		      String totalCountacz = PaginationUtil.scccc(sqlcz);
		      if(totalCountacz  == "null"){
		    	  	totalCountacz = "0.00";
					 request.put("totalCountacz4", totalCountacz);
					 incomeexpenditure.setLxzcqn(totalCountacz);
				 }else{
					 String czysbkcz = totalCountacz; 
					 Double czysbkfcz = Double.parseDouble(czysbkcz); 
					 DecimalFormat czysbkf1cz = new DecimalFormat("0.00"); 
					 String s7cz = czysbkf1cz.format(czysbkfcz);
					 request.put("totalCountacz4", s7cz);
					 incomeexpenditure.setLxzcqn(s7cz);
				 }
		 }
		 
		 String sqllxys = "select sum( VCreditAmount)-sum(VDebitAmount) as VDebitAmount from BudgetVoucher where SName like '利息预算收入%'";
		 String totalCountalxys = PaginationUtil.scccc(sqllxys);
		 if(totalCountalxys  == "null"){
			 totalCountalxys = "0.00";
			 request.put("totalCountalxys", totalCountalxys);
			 incomeexpenditure.setLxyssr(totalCountalxys);
		 }else{
			 String lxyssr = totalCountalxys; 
			 Double lxyssrf = Double.parseDouble(lxyssr); 
			 DecimalFormat lxyssrf1 = new DecimalFormat("0.00"); 
			 String s5 = lxyssrf1.format(lxyssrf); 
			 request.put("totalCountalxys", s5);
			 incomeexpenditure.setLxyssr(s5);
			 BudgetV = baseService.findObjList("from BudgetVoucher where SName like '利息预算收入%'");
		     String z = BudgetV.get(0).getVDate();
		      z = z.substring(0,p); 
		      int l1 = Integer.parseInt(z);
		      int k1 = 1;
		      int c = l1-k1;
		      String sqlcz = "select sum( VCreditAmount)-sum(VDebitAmount) as VDebitAmount from BudgetVoucher where SName like '利息预算收入%' and VDate like'"+c+"%'";
		      String totalCountacz = PaginationUtil.scccc(sqlcz);
		      if(totalCountacz  == "null"){
		    	  	totalCountacz = "0.00";
					 request.put("totalCountacz5", totalCountacz);
					 incomeexpenditure.setLxyssrqn(totalCountacz);
				 }else{
					 String czysbkcz = totalCountacz; 
					 Double czysbkfcz = Double.parseDouble(czysbkcz); 
					 DecimalFormat czysbkf1cz = new DecimalFormat("0.00"); 
					 String s7cz = czysbkf1cz.format(czysbkfcz);
					 request.put("totalCountacz5", s7cz);
					 incomeexpenditure.setLxyssrqn(s7cz);
				 }
		 }
		//其中：政府性基金收入	
		 String sqlzf = "select sum(VCreditAmount)-sum(VDebitAmount) as VDebitAmount from BudgetVoucher where SName like '政府性基金收入%'";
		 String totalCountazf = PaginationUtil.scccc(sqlzf);
		 if(totalCountazf  == "null"){
			 totalCountazf = "0.00";
			 request.put("totalCountazf", totalCountazf);
			 incomeexpenditure.setZfxjjsr(totalCountazf);
		 }else{
			 String zfjjsr = totalCountazf; 
			 Double zfjjsrf = Double.parseDouble(zfjjsr); 
			 DecimalFormat zfjjsrf1 = new DecimalFormat("0.00"); 
			 String s6 = zfjjsrf1.format(zfjjsrf);
			 request.put("totalCountazf", s6);
			 incomeexpenditure.setZfxjjsr(s6);
			 BudgetV = baseService.findObjList("from BudgetVoucher where SName like '政府性基金收入%'");
		     String z = BudgetV.get(0).getVDate();
		      z = z.substring(0,p); 
		      int l1 = Integer.parseInt(z);
		      int k1 = 1;
		      int c = l1-k1;
		      String sqlcz = "select sum( VCreditAmount)-sum(VDebitAmount) as VDebitAmount from BudgetVoucher where SName like '政府性基金收入%' and VDate like'"+c+"%'";
		      String totalCountacz = PaginationUtil.scccc(sqlcz);
		      if(totalCountacz  == "null"){
		    	  	totalCountacz = "0.00";
					 request.put("totalCountacz6", totalCountacz);
					 incomeexpenditure.setZfxjjsrqn(totalCountacz);
				 }else{
					 String czysbkcz = totalCountacz; 
					 Double czysbkfcz = Double.parseDouble(czysbkcz); 
					 DecimalFormat czysbkf1cz = new DecimalFormat("0.00"); 
					 String s7cz = czysbkf1cz.format(czysbkfcz);
					 request.put("totalCountacz6", s7cz);
					 incomeexpenditure.setZfxjjsrqn(s7cz);
				 }
		 }
		//(一) 财政拨款预算收入
		 String sql = "select sum( VCreditAmount)-sum(VDebitAmount) as VDebitAmount from BudgetVoucher where SName like '财政拨款预算收入%'";
		 String totalCounta = PaginationUtil.scccc(sql);
		 if(totalCounta  == "null"){
			 totalCounta = "0.00";
			 request.put("totalCounta", totalCounta);
			 incomeexpenditure.setCzbkyssr(totalCounta);
		 }else{
			 String czysbk = totalCounta; 
			 Double czysbkf = Double.parseDouble(czysbk); 
			 DecimalFormat czysbkf1 = new DecimalFormat("0.00"); 
			 String s7 = czysbkf1.format(czysbkf);
			 request.put("totalCounta", s7);
			 incomeexpenditure.setCzbkyssr(s7);
			 BudgetV = baseService.findObjList("from BudgetVoucher where SName like '财政拨款预算收入%'");
			     String z = BudgetV.get(0).getVDate();
			      z = z.substring(0,p); 
			      int l1 = Integer.parseInt(z);
			      int k1 = 1;
			      int c = l1-k1;
			      String sqlcz = "select sum( VCreditAmount)-sum(VDebitAmount) as VDebitAmount from BudgetVoucher where SName like '财政拨款预算收入%' and VDate like'"+c+"%'";
			      String totalCountacz = PaginationUtil.scccc(sqlcz);
			      if(totalCountacz  == "null"){
			    	  	totalCountacz = "0.00";
						 request.put("totalCountacz111", totalCountacz);
						 incomeexpenditure.setCzbkyssrqn(totalCountacz);
					 }else{
						 String czysbkcz = totalCountacz; 
						 Double czysbkfcz = Double.parseDouble(czysbkcz); 
						 DecimalFormat czysbkf1cz = new DecimalFormat("0.00"); 
						 String s7cz = czysbkf1cz.format(czysbkfcz);
						 request.put("totalCountacz111", s7cz);
						 incomeexpenditure.setCzbkyssrqn(s7cz);
					 }
		 }
		//(二) 事业预算收入	
		 String sqla = "select sum( VCreditAmount)-sum(VDebitAmount) as VDebitAmount from BudgetVoucher where SName like '事业预算收入%'";
		 String totalCountaa = PaginationUtil.scccc(sqla);
		 if(totalCountaa  == "null"){
			 totalCountaa = "0.00";
			 request.put("totalCountaa", totalCountaa);
			 incomeexpenditure.setSyyssr(totalCountaa);
		 }else{
			 String syyssr = totalCountaa; 
			 Double syyssrf = Double.parseDouble(syyssr); 
			 DecimalFormat syyssrf1 = new DecimalFormat("0.00"); 
			 String s8 = syyssrf1.format(syyssrf);
			 request.put("totalCountaa", s8);
			 incomeexpenditure.setSyyssr(s8);
			 BudgetV = baseService.findObjList("from BudgetVoucher where SName like '事业预算收入%'");
		     String z = BudgetV.get(0).getVDate();
		      z = z.substring(0,p); 
		      int l1 = Integer.parseInt(z);
		      int k1 = 1;
		      int c = l1-k1;
		      String sqlcz = "select sum( VCreditAmount)-sum(VDebitAmount) as VDebitAmount from BudgetVoucher where SName like '事业预算收入%' and VDate like'"+c+"%'";
		      String totalCountacz = PaginationUtil.scccc(sqlcz);
		      if(totalCountacz  == "null"){
		    	  	totalCountacz = "0.00";
					 request.put("totalCountacz7", totalCountacz);
					 incomeexpenditure.setSyyssrqn(totalCountacz);
				 }else{
					 String czysbkcz = totalCountacz; 
					 Double czysbkfcz = Double.parseDouble(czysbkcz); 
					 DecimalFormat czysbkf1cz = new DecimalFormat("0.00"); 
					 String s7cz = czysbkf1cz.format(czysbkfcz);
					 request.put("totalCountacz7", s7cz);
					 incomeexpenditure.setSyyssrqn(s7cz);
				 }
		 }
		 String sqlb = "select sum( VCreditAmount)-sum(VDebitAmount) as VDebitAmount from BudgetVoucher where SName like '上级补助预算收入%'";
		 String totalCountab = PaginationUtil.scccc(sqlb);
		 
		 if(totalCountab  == "null"){
			 totalCountab = "0.00";
			 request.put("totalCountab", totalCountab);
			 incomeexpenditure.setSjfzyssr(totalCountab);
		 }else{
			 String sjbzyssr = totalCountab; 
			 Double sjbzyssrf = Double.parseDouble(sjbzyssr); 
			 DecimalFormat sjbzyssrf1 = new DecimalFormat("0.00"); 
			 String s9 = sjbzyssrf1.format(sjbzyssrf);
			 request.put("totalCountab", s9);
			 incomeexpenditure.setSjfzyssr(s9);
			 BudgetV = baseService.findObjList("from BudgetVoucher where SName like '上级补助预算收入%'");
		     String z = BudgetV.get(0).getVDate();
		      z = z.substring(0,p); 
		      int l1 = Integer.parseInt(z);
		      int k1 = 1;
		      int c = l1-k1;
		      String sqlcz = "select sum( VCreditAmount)-sum(VDebitAmount) as VDebitAmount from BudgetVoucher where SName like '上级补助预算收入%' and VDate like'"+c+"%'";
		      String totalCountacz = PaginationUtil.scccc(sqlcz);
		      if(totalCountacz  == "null"){
		    	  	totalCountacz = "0.00";
					 request.put("totalCountacz8", totalCountacz);
					 incomeexpenditure.setSjfzyssrqn(totalCountacz);
				 }else{
					 String czysbkcz = totalCountacz; 
					 Double czysbkfcz = Double.parseDouble(czysbkcz); 
					 DecimalFormat czysbkf1cz = new DecimalFormat("0.00"); 
					 String s7cz = czysbkf1cz.format(czysbkfcz);
					 request.put("totalCountacz8", s7cz);
					 incomeexpenditure.setSjfzyssrqn(s7cz);
					 
				 }
		 }
		 
		 String sqlc = "select sum( VCreditAmount)-sum(VDebitAmount) as VDebitAmount from BudgetVoucher where SName like '附属单位上缴预算收入%'";
		 String totalCountac = PaginationUtil.scccc(sqlc);
		 if(totalCountac  == "null"){
			 totalCountac = "0.00";
			 request.put("totalCountac", totalCountac);
			 incomeexpenditure.setFsdwsjyssr(totalCountac);
		 }else{
			 String fsdwsjyssr = totalCountac; 
			 Double fsdwsjyssrf = Double.parseDouble(fsdwsjyssr); 
			 DecimalFormat fsdwsjyssrf1 = new DecimalFormat("0.00"); 
			 String s10 = fsdwsjyssrf1.format(fsdwsjyssrf);
			 request.put("totalCountac", s10);
			 incomeexpenditure.setFsdwsjyssr(s10);
			 BudgetV = baseService.findObjList("from BudgetVoucher where SName like '附属单位上缴预算收入%'");
		     String z = BudgetV.get(0).getVDate();
		      z = z.substring(0,p); 
		      int l1 = Integer.parseInt(z);
		      int k1 = 1;
		      int c = l1-k1;
		      String sqlcz = "select sum( VCreditAmount)-sum(VDebitAmount) as VDebitAmount from BudgetVoucher where SName like '附属单位上缴预算收入%' and VDate like'"+c+"%'";
		      String totalCountacz = PaginationUtil.scccc(sqlcz);
		      if(totalCountacz  == "null"){
		    	  	totalCountacz = "0.00";
					 request.put("totalCountacz9", totalCountacz);
					 incomeexpenditure.setFsdwsjyssr(totalCountacz);
				 }else{
					 String czysbkcz = totalCountacz; 
					 Double czysbkfcz = Double.parseDouble(czysbkcz); 
					 DecimalFormat czysbkf1cz = new DecimalFormat("0.00"); 
					 String s7cz = czysbkf1cz.format(czysbkfcz);
					 request.put("totalCountacz9", s7cz);
					 incomeexpenditure.setFsdwsjyssr(s7cz);
				 }
		 }
		 
		 String sqld = "select sum( VCreditAmount)-sum(VDebitAmount) as VDebitAmount from BudgetVoucher where SName like '经营预算收入%'";
		 String totalCountad = PaginationUtil.scccc(sqld);
		 if(totalCountad  == "null"){
			 totalCountad = "0.00";
			 request.put("totalCountad", totalCountad);
			 incomeexpenditure.setJyyssr(totalCountad);
		 }else{
			 String jyyssr = totalCountad; 
			 Double jyyssrf = Double.parseDouble(jyyssr); 
			 DecimalFormat jyyssr1 = new DecimalFormat("0.00"); 
			 String s11 = jyyssr1.format(jyyssrf);
			 request.put("totalCountad", s11);
			 incomeexpenditure.setJyyssr(s11);
			 BudgetV = baseService.findObjList("from BudgetVoucher where SName like '经营预算收入%'");
		     String z = BudgetV.get(0).getVDate();
		      z = z.substring(0,p); 
		      int l1 = Integer.parseInt(z);
		      int k1 = 1;
		      int c = l1-k1;
		      String sqlcz = "select sum( VCreditAmount)-sum(VDebitAmount) as VDebitAmount from BudgetVoucher where SName like '经营预算收入%' and VDate like'"+c+"%'";
		      String totalCountacz = PaginationUtil.scccc(sqlcz);
		      if(totalCountacz  == "null"){
		    	  	totalCountacz = "0.00";
					 request.put("totalCountacz0", totalCountacz);
					 incomeexpenditure.setJyyssrqn(totalCountacz);
				 }else{
					 String czysbkcz = totalCountacz; 
					 Double czysbkfcz = Double.parseDouble(czysbkcz); 
					 DecimalFormat czysbkf1cz = new DecimalFormat("0.00"); 
					 String s7cz = czysbkf1cz.format(czysbkfcz);
					 request.put("totalCountacz0", s7cz);
					 incomeexpenditure.setJyyssrqn(s7cz);
				 }
		 }
		 String sqle = "select sum( VCreditAmount)-sum(VDebitAmount) as VDebitAmount from BudgetVoucher where SName like '债务预算收入%'";
		 String totalCountae = PaginationUtil.scccc(sqle);
		 if(totalCountae  == "null"){
			 totalCountae = "0.00";
			 request.put("totalCountae", totalCountae);
			 incomeexpenditure.setZwyssr(totalCountae);
		 }else{
			 String zwyssr = totalCountae; 
			 Double zwyssrf = Double.parseDouble(zwyssr); 
			 DecimalFormat zwyssrf1 = new DecimalFormat("0.00"); 
			 String s12 = zwyssrf1.format(zwyssrf);
			 request.put("totalCountae", s12);
			 incomeexpenditure.setZwyssr(s12);
			 BudgetV = baseService.findObjList("from BudgetVoucher where SName like '债务预算收入%'");
		     String z = BudgetV.get(0).getVDate();
		      z = z.substring(0,p); 
		      int l1 = Integer.parseInt(z);
		      int k1 = 1;
		      int c = l1-k1;
		      String sqlcz = "select sum( VCreditAmount)-sum(VDebitAmount) as VDebitAmount from BudgetVoucher where SName like '债务预算收入%' and VDate like'"+c+"%'";
		      String totalCountacz = PaginationUtil.scccc(sqlcz);
		      if(totalCountacz  == "null"){
		    	  	totalCountacz = "0.00";
					 request.put("totalCountacz11", totalCountacz);
					 incomeexpenditure.setZwyssrqn(totalCountacz);
				 }else{
					 String czysbkcz = totalCountacz; 
					 Double czysbkfcz = Double.parseDouble(czysbkcz); 
					 DecimalFormat czysbkf1cz = new DecimalFormat("0.00"); 
					 String s7cz = czysbkf1cz.format(czysbkfcz);
					 request.put("totalCountacz11", s7cz);
					 incomeexpenditure.setZwyssrqn(s7cz);
				 }
		 }
		 
		 String sqlf= "select sum( VCreditAmount)-sum(VDebitAmount) as VDebitAmount from BudgetVoucher where SName like '非同级财政拨款预算收入%'";
		 String totalCountaf = PaginationUtil.scccc(sqlf);
		 if(totalCountaf  == "null"){
			 totalCountaf = "0.00";
			 request.put("totalCountaf", totalCountaf);
			 incomeexpenditure.setFtjczbkyssr(totalCountaf);
		 }else{
			 String ftjczbkys = totalCountaf; 
			 Double ftjczbkysf = Double.parseDouble(ftjczbkys); 
			 DecimalFormat ftjczbkys1 = new DecimalFormat("0.00"); 
			 String s13 = ftjczbkys1.format(ftjczbkysf);
			 request.put("totalCountaf", s13);
			 incomeexpenditure.setFtjczbkyssr(s13);
			 BudgetV = baseService.findObjList("from BudgetVoucher where SName like '非同级财政拨款预算收入%'");
		     String z = BudgetV.get(0).getVDate();
		      z = z.substring(0,p); 
		      int l1 = Integer.parseInt(z);
		      int k1 = 1;
		      int c = l1-k1;
		      String sqlcz = "select sum( VCreditAmount)-sum(VDebitAmount) as VDebitAmount from BudgetVoucher where SName like '非同级财政拨款预算收入%' and VDate like'"+c+"%'";
		      String totalCountacz = PaginationUtil.scccc(sqlcz);
		      if(totalCountacz  == "null"){
		    	  	totalCountacz = "0.00";
					 request.put("totalCountacz12", totalCountacz);
					 incomeexpenditure.setFtjczbkyssrqn(totalCountacz);
				 }else{
					 String czysbkcz = totalCountacz; 
					 Double czysbkfcz = Double.parseDouble(czysbkcz); 
					 DecimalFormat czysbkf1cz = new DecimalFormat("0.00"); 
					 String s7cz = czysbkf1cz.format(czysbkfcz);
					 request.put("totalCountacz12", s7cz);
					 incomeexpenditure.setFtjczbkyssrqn(s7cz);
				 }
		 }
		 
		 String sqlg = "select sum( VCreditAmount)-sum(VDebitAmount) as VDebitAmount from BudgetVoucher where SName like '投资预算收益%'";
		 String totalCountag = PaginationUtil.scccc(sqlg);
		 if(totalCountag  == "null"){
			 totalCountag = "0.00";
			 request.put("totalCountag", totalCountag);
			 incomeexpenditure.setTzyssy(totalCountag);
		 }else{
			 String tzyssy = totalCountag; 
			 Double tzyssyf = Double.parseDouble(tzyssy); 
			 DecimalFormat tzyssy1 = new DecimalFormat("0.00"); 
			 String s14 = tzyssy1.format(tzyssyf);
			 request.put("totalCountag", s14);
			 incomeexpenditure.setTzyssy(s14);
			 BudgetV = baseService.findObjList("from BudgetVoucher where SName like '投资预算收益%'");
		     String z = BudgetV.get(0).getVDate();
		      z = z.substring(0,p); 
		      int l1 = Integer.parseInt(z);
		      int k1 = 1;
		      int c = l1-k1;
		      String sqlcz = "select sum( VCreditAmount)-sum(VDebitAmount) as VDebitAmount from BudgetVoucher where SName like '投资预算收益%' and VDate like'"+c+"%'";
		      String totalCountacz = PaginationUtil.scccc(sqlcz);
		      if(totalCountacz  == "null"){
		    	  	totalCountacz = "0.00";
					 request.put("totalCountacz13", totalCountacz);
					 incomeexpenditure.setTzyssyqn(totalCountacz);
				 }else{
					 String czysbkcz = totalCountacz; 
					 Double czysbkfcz = Double.parseDouble(czysbkcz); 
					 DecimalFormat czysbkf1cz = new DecimalFormat("0.00"); 
					 String s7cz = czysbkf1cz.format(czysbkfcz);
					 request.put("totalCountacz13", s7cz);
					 incomeexpenditure.setTzyssyqn(s7cz);
				 }
		 }
		 
		 String sqlh = "select sum( VCreditAmount)-sum(VDebitAmount) as VDebitAmount from BudgetVoucher where SName like '其他预算收入%'";
		 String totalCountah = PaginationUtil.scccc(sqlh);
		 if(totalCountah  == "null"){
			 totalCountah = "0.00";
			 request.put("totalCountah", totalCountah);
			 incomeexpenditure.setQtyssr(totalCountah);
		 }else{
			 String qtyssr = totalCountah; 
			 Double qtyssrf = Double.parseDouble(qtyssr); 
			 DecimalFormat qtyssr1 = new DecimalFormat("0.00"); 
			 String s15 = qtyssr1.format(qtyssrf);
			 request.put("totalCountah", s15);
			 incomeexpenditure.setQtyssr(s15);
			 BudgetV = baseService.findObjList("from BudgetVoucher where SName like '其他预算收入%'");
		     String z = BudgetV.get(0).getVDate();
		      z = z.substring(0,p); 
		      int l1 = Integer.parseInt(z);
		      int k1 = 1;
		      int c = l1-k1;
		      String sqlcz = "select sum( VCreditAmount)-sum(VDebitAmount) as VDebitAmount from BudgetVoucher where SName like '其他预算收入%' and VDate like'"+c+"%'";
		      String totalCountacz = PaginationUtil.scccc(sqlcz);
		      if(totalCountacz  == "null"){
		    	  	totalCountacz = "0.00";
					 request.put("totalCountacz14", totalCountacz);
					 incomeexpenditure.setQtyssrqn(totalCountacz);
				 }else{
					 String czysbkcz = totalCountacz; 
					 Double czysbkfcz = Double.parseDouble(czysbkcz); 
					 DecimalFormat czysbkf1cz = new DecimalFormat("0.00"); 
					 String s7cz = czysbkf1cz.format(czysbkfcz);
					 request.put("totalCountacz14", s7cz);
					 incomeexpenditure.setQtyssrqn(s7cz);
				 }
		 }
		 
		
		 //预算支出
		
		 BudgetV = baseService.findObjList("from BudgetVoucher  where SName like '行政支出%' or SName like '事业支出%' or SName like '经营支出%' or SName like '上缴上级支出%' or SName like '对附属单位补助支出%' or SName like '投资支出%' or SName like '债务还本支出%'  or SName like '投资预算收益%' or SName like '其他支出%'"); 
		 String bnys = " select sum(VDebitAmount)-sum(VCreditAmount) as VDebitAmount from BudgetVoucher where SName like '行政支出%' or SName like '事业支出%' or SName like '经营支出%'or SName like '上缴上级支出%' or SName like '对附属单位补助支出%' or SName like '投资支出%' or SName like '债务还本支出%'  or SName like '投资预算收益%' or SName like '其他支出%' ";
		 String totalCountbnys = PaginationUtil.scccc(bnys);
		 if(BudgetV.size() >0 ){
		 String z898 = BudgetV.get(0).getVDate();
	      z898 = z898.substring(0,p); 
	      int l1898 = Integer.parseInt(z898);
	      int k1898 = 1;
	      int c898 = l1898-k1898;
	      String sqlcz898 = "select sum(VDebitAmount)-sum(VCreditAmount) as VDebitAmount from BudgetVoucher  where SName like '行政支出%' and VDate like'"+c898+"%' or SName like '事业支出%' and VDate like'"+c898+"%' or SName like '经营支出%' and VDate like'"+c898+"%' or SName like '上缴上级支出%' and VDate like'"+c898+"%' or SName like '对附属单位补助支出%' and VDate like'"+c898+"%' or SName like '投资支出%' and VDate like'"+c898+"%' or SName like '债务还本支出%' and VDate like'"+c898+"%'  or SName like '投资预算收益%' and VDate like'"+c898+"%' or SName like '其他支出%' and VDate like'"+c898+"%'"; 
	      String totalCountacz898 = PaginationUtil.scccc(sqlcz898);
	      if(totalCountacz898  == "null"){
	    	  	totalCountacz898 = "0.00";
				 request.put("totalCountacz24", totalCountacz898);
				 incomeexpenditure.setBnyszcqn(totalCountacz898);
			 }else{
				 String czysbkcz898 = totalCountacz898; 
				 Double czysbkfcz898 = Double.parseDouble(czysbkcz898); 
				 DecimalFormat czysbkf1cz898 = new DecimalFormat("0.00"); 
				 String s7cz898 = czysbkf1cz898.format(czysbkfcz898);
				 request.put("totalCountacz24", s7cz898);
				 incomeexpenditure.setBnyszcqn(s7cz898);
				}
	      
	      	BudgetV = baseService.findObjList("from BudgetVoucher  where SName like '财政拨款预算收入%' or SName like '事业预算收入%' or SName like '上级补助预算收入%' or SName like '附属单位上缴预算收入%' or SName like '经营预算收入%' or SName like '债务预算收入%' or SName like '非同级财政拨款预算收入%'  or SName like '投资预算收益%' or SName like '其他预算收入%'"); 
			 if(BudgetV.size() >0){
	      	String z789 = BudgetV.get(0).getVDate();
		      z789 = z789.substring(0,p); 
		      int l1789 = Integer.parseInt(z789);
		      int k1789 = 1;
		      int c789 = l1789-k1789;
		      String sqlcz789 = "select sum(VCreditAmount)-sum(VDebitAmount) as VDebitAmount from BudgetVoucher  where SName like '财政拨款预算收入%' and VDate like'"+c789+"%' or SName like '事业预算收入%' and VDate like'"+c789+"%' or SName like '上级补助预算收入%' and VDate like'"+c789+"%' or SName like '附属单位上缴预算收入%' and VDate like'"+c789+"%' or SName like '经营预算收入%' and VDate like'"+c789+"%' or SName like '债务预算收入%' and VDate like'"+c789+"%' or SName like '非同级财政拨款预算收入%' and VDate like'"+c789+"%'  or SName like '投资预算收益%' and VDate like'"+c789+"%' or SName like '其他预算收入%' and VDate like'"+c789+"%'"; 
		      String totalCountacz789 = PaginationUtil.scccc(sqlcz789);
		      if(totalCountacz789  == "null"){
		    	  	totalCountacz789 = "0.00";
					 request.put("totalCountacz23", totalCountacz789);
					 incomeexpenditure.setBqyssrqn(totalCountacz789);
				 }else{
					 String czysbkcz789 = totalCountacz789; 
					 Double czysbkfcz789 = Double.parseDouble(czysbkcz789); 
					 DecimalFormat czysbkf1cz789 = new DecimalFormat("0.00"); 
					 String s7cz789 = czysbkf1cz789.format(czysbkfcz789);
					 request.put("totalCountacz23", s7cz789);
					 incomeexpenditure.setBqyssrqn(s7cz789);
				 }
		      String k99 = totalCountacz789; //收入
				 String l99 = totalCountacz898;//支出
				 if(totalCountacz789 == "" ||  totalCountacz898 == ""){
					 totalCountbnys = "0.00";
					 request.put("result", totalCountbnys);
				 }else{
					 BigDecimal num3 = new BigDecimal(k99);
					 BigDecimal num4 = new BigDecimal(l99);
					 BigDecimal result1 = num3.subtract(num4);
					 String ouft1 = result1.toString();
					 String hhhh13 = ouft1; 
					 Double hhhhf1 = Double.parseDouble(hhhh13); 
					 DecimalFormat hhhh11 = new DecimalFormat("0.00"); 
					 String s251 = hhhh11.format(hhhhf1);
					 request.put("result11", s251);
					 incomeexpenditure.setBnysszceqn(s251);
				 }
			 }
		 }
		 if(totalCountbnys  == "null"){
			 totalCountbnys = "0.00";
			 request.put("totalCountbnys", totalCountbnys);
				 incomeexpenditure.setBnyszc(totalCountbnys);
		 }else{
			 String bnyszc = totalCountbnys; 
			 Double bnyszcf = Double.parseDouble(bnyszc); 
			 DecimalFormat bnyszc1 = new DecimalFormat("0.00"); 
			 String s16 = bnyszc1.format(bnyszcf);
			 request.put("totalCountbnys", s16);
			 incomeexpenditure.setBnyszc(s16);
		 }
		 
		 
		 String sql1 = "select sum(VDebitAmount)-sum(VCreditAmount) as VDebitAmount from BudgetVoucher where SName like '行政支出%'";
		 String totalCount1 = PaginationUtil.scccc(sql1);
		 if(totalCount1  == "null"){
			 totalCount1 = "0.00";
			 request.put("totalCount1", totalCount1);
			 incomeexpenditure.setHzzc(totalCount1);
		 }else{
			 String xzzc = totalCount1; 
			 Double xzzcf = Double.parseDouble(xzzc); 
			 DecimalFormat xzzcf1 = new DecimalFormat("0.00"); 
			 String s17 = xzzcf1.format(xzzcf);
			 request.put("totalCount1", s17);
			 incomeexpenditure.setHzzc(s17);
			 BudgetV = baseService.findObjList("from BudgetVoucher where SName like '行政支出%'");
		     String z = BudgetV.get(0).getVDate();
		      z = z.substring(0,p); 
		      int l1 = Integer.parseInt(z);
		      int k1 = 1;
		      int c = l1-k1;
		      String sqlcz = "select sum(VDebitAmount)-sum(VCreditAmount) as VDebitAmount from BudgetVoucher where SName like '行政支出%' and VDate like'"+c+"%'";
		      String totalCountacz = PaginationUtil.scccc(sqlcz);
		      if(totalCountacz  == "null"){
		    	  	totalCountacz = "0.00";
					 request.put("totalCountacz15", totalCountacz);
					 incomeexpenditure.setHzzcqn(totalCountacz);
				 }else{
					 String czysbkcz = totalCountacz; 
					 Double czysbkfcz = Double.parseDouble(czysbkcz); 
					 DecimalFormat czysbkf1cz = new DecimalFormat("0.00"); 
					 String s7cz = czysbkf1cz.format(czysbkfcz);
					 request.put("totalCountacz15", s7cz);
					 incomeexpenditure.setHzzcqn(s7cz);
				 }
		 }
		 
		 String sql2 = "select sum(VDebitAmount)-sum(VCreditAmount) as VDebitAmount from BudgetVoucher where SName like '事业支出%'";
		 String totalCount2 = PaginationUtil.scccc(sql2);
		 if(totalCount2  == "null"){
			 totalCount2 = "0.00";
			 request.put("totalCount2", totalCount2);
			 incomeexpenditure.setSyzc(totalCount2);
		 }else{
			 String syglzc = totalCount2; 
			 Double syglzcf = Double.parseDouble(syglzc); 
			 DecimalFormat syglzc1 = new DecimalFormat("0.00"); 
			 String s18 = syglzc1.format(syglzcf);
			 request.put("totalCount2", s18);
			 incomeexpenditure.setSyzc(s18);
			 BudgetV = baseService.findObjList("from BudgetVoucher where SName like '事业支出%'");
		     String z = BudgetV.get(0).getVDate();
		      z = z.substring(0,p); 
		      int l1 = Integer.parseInt(z);
		      int k1 = 1;
		      int c = l1-k1;
		      String sqlcz = "select sum(VDebitAmount)-sum(VCreditAmount) as VDebitAmount from BudgetVoucher where SName like '事业支出%' and VDate like'"+c+"%'";
		      String totalCountacz = PaginationUtil.scccc(sqlcz);
		      if(totalCountacz  == "null"){
		    	  	totalCountacz = "0.00";
					 request.put("totalCountacz16", totalCountacz);
					 incomeexpenditure.setSyzcqn(totalCountacz);
				 }else{
					 String czysbkcz = totalCountacz; 
					 Double czysbkfcz = Double.parseDouble(czysbkcz); 
					 DecimalFormat czysbkf1cz = new DecimalFormat("0.00"); 
					 String s7cz = czysbkf1cz.format(czysbkfcz);
					 request.put("totalCountacz16", s7cz);
					 incomeexpenditure.setSyzcqn(s7cz);
				 }
		 }
		 
		 String sql3 = "select sum(VDebitAmount)-sum(VCreditAmount) as VDebitAmount from BudgetVoucher where SName like '经营支出%'";
		 String totalCount3 = PaginationUtil.scccc(sql3);
		 if(totalCount3  == "null"){
			 totalCount3 = "0.00";
			 request.put("totalCount3", totalCount3);
			 incomeexpenditure.setJyzc(totalCount3);
		 }else{
			 String jyzc = totalCount3; 
			 Double jyzcf = Double.parseDouble(jyzc); 
			 DecimalFormat jyzc1 = new DecimalFormat("0.00"); 
			 String s19 = jyzc1.format(jyzcf);
			 request.put("totalCount3", s19);
			 incomeexpenditure.setJyzc(s19);
			 BudgetV = baseService.findObjList("from BudgetVoucher where SName like '经营支出%'");
		     String z = BudgetV.get(0).getVDate();
		      z = z.substring(0,p); 
		      int l1 = Integer.parseInt(z);
		      int k1 = 1;
		      int c = l1-k1;
		      String sqlcz = "select sum(VDebitAmount)-sum(VCreditAmount) as VDebitAmount from BudgetVoucher where SName like '经营支出%' and VDate like'"+c+"%'";
		      String totalCountacz = PaginationUtil.scccc(sqlcz);
		      if(totalCountacz  == "null"){
		    	  	totalCountacz = "0.00";
					 request.put("totalCountacz17", totalCountacz);
					 incomeexpenditure.setJyzcqn(totalCountacz);
				 }else{
					 String czysbkcz = totalCountacz; 
					 Double czysbkfcz = Double.parseDouble(czysbkcz); 
					 DecimalFormat czysbkf1cz = new DecimalFormat("0.00"); 
					 String s7cz = czysbkf1cz.format(czysbkfcz);
					 request.put("totalCountacz17", s7cz);
					 incomeexpenditure.setJyzcqn(s7cz);
				 }
		 }
		 
		 String sql4 = "select sum(VDebitAmount)-sum(VCreditAmount) as VDebitAmount from BudgetVoucher where SName like '上缴上级支出%'";
		 String totalCount4 = PaginationUtil.scccc(sql4);
		 if(totalCount4  == "null"){
			 totalCount4 = "0.00";
			 request.put("totalCount4", totalCount4);
			 incomeexpenditure.setSjsjzc(totalCount4);
		 }else{
			 
			 String sjsjzc = totalCount4; 
			 Double sjsjzcf = Double.parseDouble(sjsjzc); 
			 DecimalFormat sjsjzc1 = new DecimalFormat("0.00"); 
			 String s20 = sjsjzc1.format(sjsjzcf);
			 request.put("totalCount4", s20);
			 incomeexpenditure.setSjsjzc(s20);
			 BudgetV = baseService.findObjList("from BudgetVoucher where SName like '上缴上级支出%'");
		     String z = BudgetV.get(0).getVDate();
		      z = z.substring(0,p); 
		      int l1 = Integer.parseInt(z);
		      int k1 = 1;
		      int c = l1-k1;
		      String sqlcz = "select sum(VDebitAmount)-sum(VCreditAmount) as VDebitAmount from BudgetVoucher where SName like '上缴上级支出%' and VDate like'"+c+"%'";
		      String totalCountacz = PaginationUtil.scccc(sqlcz);
		      if(totalCountacz  == "null"){
		    	  	totalCountacz = "0.00";
					 request.put("totalCountacz18", totalCountacz);
					 incomeexpenditure.setSjsjzcqn(totalCountacz);
				 }else{
					 String czysbkcz = totalCountacz; 
					 Double czysbkfcz = Double.parseDouble(czysbkcz); 
					 DecimalFormat czysbkf1cz = new DecimalFormat("0.00"); 
					 String s7cz = czysbkf1cz.format(czysbkfcz);
					 request.put("totalCountacz18", s7cz);
					 incomeexpenditure.setSjsjzcqn(s7cz);
				 }
		 }
		 
		 String sql5 = "select sum(VDebitAmount)-sum(VCreditAmount) as VDebitAmount from BudgetVoucher where SName like '对附属单位补助支出%'";
		 String totalCount5 = PaginationUtil.scccc(sql5);
		 if(totalCount5  == "null"){
			 totalCount5 = "0.00";
			 request.put("totalCount5", totalCount5);
			 incomeexpenditure.setDfsdwbzzc(totalCount5);
		 }else{
			 
			 String dfsdwbzzc = totalCount5; 
			 Double dfsdwbzzcf = Double.parseDouble(dfsdwbzzc); 
			 DecimalFormat dfsdwbzzc1 = new DecimalFormat("0.00"); 
			 String s21 = dfsdwbzzc1.format(dfsdwbzzcf);
			 request.put("totalCount5", s21);
			 incomeexpenditure.setDfsdwbzzc(s21);
			 BudgetV = baseService.findObjList("from BudgetVoucher where SName like '对附属单位补助支出%'");
		     String z = BudgetV.get(0).getVDate();
		      z = z.substring(0,p); 
		      int l1 = Integer.parseInt(z);
		      int k1 = 1;
		      int c = l1-k1;
		      String sqlcz = "select sum(VDebitAmount)-sum(VCreditAmount) as VDebitAmount from BudgetVoucher where SName like '对附属单位补助支出%' and VDate like'"+c+"%'";
		      String totalCountacz = PaginationUtil.scccc(sqlcz);
		      if(totalCountacz  == "null"){
		    	  	totalCountacz = "0.00";
					 request.put("totalCountacz19", totalCountacz);
					 incomeexpenditure.setDfsdwbzzcqn(totalCountacz);
				 }else{
					 String czysbkcz = totalCountacz; 
					 Double czysbkfcz = Double.parseDouble(czysbkcz); 
					 DecimalFormat czysbkf1cz = new DecimalFormat("0.00"); 
					 String s7cz = czysbkf1cz.format(czysbkfcz);
					 request.put("totalCountacz19", s7cz);
					 incomeexpenditure.setDfsdwbzzcqn(s7cz);
				 }
		 }
		 
		 String sql6 = "select sum(VDebitAmount)-sum(VCreditAmount) as VDebitAmount from BudgetVoucher where SName like '投资支出%'";
		 String totalCount6 = PaginationUtil.scccc(sql6);
		 if(totalCount6  == "null"){
			 totalCount6 = "0.00";
			 request.put("totalCount6", totalCount6);
			 incomeexpenditure.setTzzc(totalCount6);
		 }else{
			 
			 String tzzc = totalCount6; 
			 Double tzzcf = Double.parseDouble(tzzc); 
			 DecimalFormat tzzc1 = new DecimalFormat("0.00"); 
			 String s22 = tzzc1.format(tzzcf);
			 request.put("totalCount6", s22);
			 incomeexpenditure.setTzzc(s22);
			 BudgetV = baseService.findObjList("from BudgetVoucher where SName like '投资支出%'");
		     String z = BudgetV.get(0).getVDate();
		      z = z.substring(0,p); 
		      int l1 = Integer.parseInt(z);
		      int k1 = 1;
		      int c = l1-k1;
		      String sqlcz = "select sum(VDebitAmount)-sum(VCreditAmount) as VDebitAmount from BudgetVoucher where SName like '投资支出%' and VDate like'"+c+"%'";
		      String totalCountacz = PaginationUtil.scccc(sqlcz);
		      if(totalCountacz  == "null"){
		    	  	totalCountacz = "0.00";
					 request.put("totalCountacz20", totalCountacz);
					 incomeexpenditure.setTzzcqn(totalCountacz);
				 }else{
					 String czysbkcz = totalCountacz; 
					 Double czysbkfcz = Double.parseDouble(czysbkcz); 
					 DecimalFormat czysbkf1cz = new DecimalFormat("0.00"); 
					 String s7cz = czysbkf1cz.format(czysbkfcz);
					 request.put("totalCountacz20", s7cz);
					 incomeexpenditure.setTzzcqn(s7cz);
				 }
		 }
		 
		 String sql7 = "select sum(VDebitAmount)-sum(VCreditAmount) as VDebitAmount from BudgetVoucher where SName like '债务还本支出%'";
		 String totalCount7 = PaginationUtil.scccc(sql7);
		 if(totalCount7  == "null"){
			 totalCount7 = "0.00";
			 request.put("totalCount7", totalCount7);
			 incomeexpenditure.setZwhbzc(totalCount7);
		 }else{
			 String zwhbzc = totalCount7; 
			 Double zwhbzcf = Double.parseDouble(zwhbzc); 
			 DecimalFormat zwhbzc1 = new DecimalFormat("0.00"); 
			 String s23 = zwhbzc1.format(zwhbzcf);
			 request.put("totalCount7", s23);
			 incomeexpenditure.setZwhbzc(s23);
			 BudgetV = baseService.findObjList("from BudgetVoucher where SName like '债务还本支出%'");
		     String z = BudgetV.get(0).getVDate();
		      z = z.substring(0,p); 
		      int l1 = Integer.parseInt(z);
		      int k1 = 1;
		      int c = l1-k1;
		      String sqlcz = "select sum(VDebitAmount)-sum(VCreditAmount) as VDebitAmount from BudgetVoucher where SName like '债务还本支出%' and VDate like'"+c+"%'";
		      String totalCountacz = PaginationUtil.scccc(sqlcz);
		      if(totalCountacz  == "null"){
		    	  	totalCountacz = "0.00";
					 request.put("totalCountacz21", totalCountacz);
					 incomeexpenditure.setZwhbzcqn(totalCountacz);
				 }else{
					 String czysbkcz = totalCountacz; 
					 Double czysbkfcz = Double.parseDouble(czysbkcz); 
					 DecimalFormat czysbkf1cz = new DecimalFormat("0.00"); 
					 String s7cz = czysbkf1cz.format(czysbkfcz);
					 request.put("totalCountacz21", s7cz);
					 incomeexpenditure.setZwhbzcqn(s7cz);
				 }
		 }
		 
		 String sql8 = "select sum(VDebitAmount)-sum(VCreditAmount) as VDebitAmount from BudgetVoucher where SName like '其他支出%'";
		 String totalCount8 = PaginationUtil.scccc(sql8);
		 if(totalCount8  == "null"){
			 totalCount8 = "0.00";
			 request.put("totalCount8", totalCount8);
			 incomeexpenditure.setQtzc(totalCount8);
		 }else{
			 String qtzc = totalCount8; 
			 Double qtzcf = Double.parseDouble(qtzc); 
			 DecimalFormat qtzc1 = new DecimalFormat("0.00"); 
			 String s24 = qtzc1.format(qtzcf);
			 request.put("totalCount8", s24);
			 incomeexpenditure.setQtzc(s24);
			 BudgetV = baseService.findObjList("from BudgetVoucher where SName like '其他支出%'");
		     String z = BudgetV.get(0).getVDate();
		      z = z.substring(0,p); 
		      int l1 = Integer.parseInt(z);
		      int k1 = 1;
		      int c = l1-k1;
		      String sqlcz = "select sum(VDebitAmount)-sum(VCreditAmount) as VDebitAmount from BudgetVoucher where SName like '其他支出%' and VDate like'"+c+"%'";
		      String totalCountacz = PaginationUtil.scccc(sqlcz);
		      if(totalCountacz  == "null"){
		    	  	totalCountacz = "0.00";
					 request.put("totalCountacz22", totalCountacz);
					 incomeexpenditure.setQtzcqn(totalCountacz);
				 }else{
					 String czysbkcz = totalCountacz; 
					 Double czysbkfcz = Double.parseDouble(czysbkcz); 
					 DecimalFormat czysbkf1cz = new DecimalFormat("0.00"); 
					 String s7cz = czysbkf1cz.format(czysbkfcz);
					 request.put("totalCountacz22", s7cz);
					 incomeexpenditure.setQtzcqn(s7cz);
				 }
		 }
			//一、本期预算收入	
		 String bnsr = "select sum(VCreditAmount)-sum(VDebitAmount)  from BudgetVoucher where SName like '财政拨款预算收入%'or SName like '事业预算收入%' or SName like '上级补助预算收入%'or SName like '附属单位上缴预算收入%' or SName like '经营预算收入%' or SName like '债务预算收入%' or SName like '非同级财政拨款预算收入%'  or SName like '投资预算收益%' or SName like '其他预算收入%'"; 
		 String totalCounbnsr = PaginationUtil.scccc(bnsr);
		 if(totalCounbnsr  == "null"){
			 totalCounbnsr = "0.00";
			 request.put("s", totalCounbnsr);
			 incomeexpenditure.setBqyssr(totalCounbnsr);
		 }else{
			 String a = totalCounbnsr; 
			 Double d= Double.parseDouble(a); 
			 DecimalFormat df = new DecimalFormat("0.00"); 
			 String s = df.format(d); 
			 request.put("s", s);
			 incomeexpenditure.setBqyssr(s);
		 }
		 String k = totalCounbnsr; //收入
		 String l = totalCountbnys;//支出
		 	if(totalCounbnsr ==""){
		 		 k = "0.0";
		 		 request.put("result",totalCounbnsr);
		 	}
		 	 if (totalCountbnys =="") {
			 		l="0.0";
					 request.put("result", totalCountbnys);
				}
		 	 if(!k.equals("") || !l.equals("")){
			 BigDecimal num1 = new BigDecimal(k);
			 BigDecimal num2 = new BigDecimal(l);
			 BigDecimal result = num1.subtract(num2);
			 String ouft = result.toString();
			 String hhhh = ouft; 
			 Double hhhhf = Double.parseDouble(hhhh); 
			 DecimalFormat hhhh1 = new DecimalFormat("0.00"); 
			 String s25 = hhhh1.format(hhhhf);
			 req = ServletActionContext.getRequest();
			 HttpSession session = req.getSession();
			 session.setAttribute("result", s25);
			 incomeexpenditure.setBnysszce(s25);
			 ActionContext.getContext().getSession().put("nnn", s25);
		 	}
		return "budgetRevenue";
	}
	
	/**
	 * 净资产变动表
	 * @return
	 * @throws Exception 
	 */
	public String carryoverbalances() throws Exception{
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String date = formatter.format(new Date());
		request.put("date", date);
        SimpleDateFormat dfggg = new SimpleDateFormat("yyyy年MM月dd");//设置日期格式
        String date1 = dfggg.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
        netasset.setAAA(date1);
		List<AccountingVoucher> absd = new ArrayList<>();
		acco = baseService.findObjList("from AccountingVoucher where SName like '以前年度盈余%' and  VDebitAmount > 0");//先查询科目
		String s = "0.00";
		for (int i = 0; i < acco.size(); i++) {
			String adf = acco.get(i).getVNo();//筛选出凭证编号
			String a = acco.get(i).getVDebitAmount();
			acco = baseService.findObjList("from AccountingVoucher where SName like '以前年度盈余%' and  VDebitAmount > 0 and VNo='"+adf+"'");//先查询科目
			System.out.println(acco);
			if(acco.size() == 1){ //判断有数据没有
				absd = baseService.findObjList("from AccountingVoucher where SName like '累计盈余%' and VCreditAmount >0 and VNo='"+adf+"'");
				System.out.println(absd);
				for (int j = 0; j < absd.size(); j++) {
				String b = absd.get(j).getVCreditAmount();
				 if( a == "" || b  == ""){
					 a = "0.00";
					 request.put("a", a);
					 netasset.setYqndyytzljyy(a);
				 }else{
					 BigDecimal num1 = new BigDecimal(a);
					 BigDecimal num2 = new BigDecimal(b);
					 BigDecimal result = num1.add(num2);
					 String ouft = result.toString();
					 String hhhh = ouft; 
					 Double hhhhf = Double.parseDouble(hhhh); 
					 DecimalFormat hhhh1 = new DecimalFormat("0.00"); 
					 s = hhhh1.format(hhhhf);
					 request.put("a", s);
					 netasset.setYqndyytzljyy(s);
				 }
				}
			}
			
		}
		/**
		 * 本年盈余分配
		 */
		acco = baseService.findObjList("from AccountingVoucher where SName like '本年盈余分配%' and  VCreditAmount > 0");//先查询科目
		String s1 = "";
		for (int i = 0; i < acco.size(); i++) {
			String adf = acco.get(i).getVNo();//筛选出凭证编号
			String a = acco.get(i).getVDebitAmount();
			acco = baseService.findObjList("from AccountingVoucher where SName like '本年盈余分配%' and  VCreditAmount > 0 and VNo='"+adf+"'");//先查询科目
			System.out.println(acco);
			if(acco.size() == 1){ //判断有数据没有
				absd = baseService.findObjList("from AccountingVoucher where SName like '本期盈余%' and VDebitAmount >0 and VNo='"+adf+"'");
				System.out.println(absd);
				for (int j = 0; j < absd.size(); j++) {
				String b = absd.get(j).getVCreditAmount();
				 if( a == "" || b  == ""){
					 a = "";
					 request.put("a1", a);
					 netasset.setBnyyljyy(a);
				 }else{
					 BigDecimal num1 = new BigDecimal(a);
					 BigDecimal num2 = new BigDecimal(b);
					 BigDecimal result = num1.subtract(num2);
					 String ouft = result.toString();
					 String hhhh = ouft; 
					 Double hhhhf = Double.parseDouble(hhhh); 
					 DecimalFormat hhhh1 = new DecimalFormat("0.00"); 
					 s1 = hhhh1.format(hhhhf);
					 request.put("a1", s1);
					 netasset.setBnyyljyy(s1);
				 }
				}
			}
			
		}
		/**
		 * 
		 * 无偿调拨净资产
		 */
		acco = baseService.findObjList("from AccountingVoucher where SName like '无偿调拨净资产%' and  VDebitAmount > 0");//先查询科目
		String s2 = "";
		for (int i = 0; i < acco.size(); i++) {
			String adf = acco.get(i).getVNo();//筛选出凭证编号
			String a = acco.get(i).getVDebitAmount();
			acco = baseService.findObjList("from AccountingVoucher where SName like '无偿调拨净资产%' and  VDebitAmount > 0 and VNo='"+adf+"'");//先查询科目
			System.out.println(acco);
			if(acco.size() == 1){ //判断有数据没有
				absd = baseService.findObjList("from AccountingVoucher where SName like '累计盈余%' and VCreditAmount >0 and VNo='"+adf+"'");
				System.out.println(absd);
				for (int j = 0; j < absd.size(); j++) {
				String b = absd.get(j).getVCreditAmount();
				 if( a == "" || b  == ""){
					 a = "0.00";
					 request.put("a2", a);
					 netasset.setWctbjzcljyy(a);
				 }else{
					 BigDecimal num1 = new BigDecimal(a);
					 BigDecimal num2 = new BigDecimal(b);
					 BigDecimal result = num1.add(num2);
					 String ouft = result.toString();
					 String hhhh = ouft; 
					 Double hhhhf = Double.parseDouble(hhhh); 
					 DecimalFormat hhhh1 = new DecimalFormat("0.00"); 
					 s2 = hhhh1.format(hhhhf);
					 request.put("a2", s2);
					 netasset.setWctbjzcljyy(s2);
				 }
				}
			}
			
		}
		/**
		 * 
		 * 专用基金-科技成果转化基金-	
		 */
		 String sql19 = "select sum(VDebitAmount)-sum(VCreditAmount) from AccountingVoucher where SName like '专用基金-科技成果转化基金-从预算收入中提取%'";
		 String s19 = "";
		 String totalCoun19 = PaginationUtil.scccc(sql19);
		 if(totalCoun19  == "null"){					  
			 totalCoun19 = "";
			 request.put("totalCoun19", totalCoun19);
			 netasset.setCyssrztqzyjj(totalCoun19);
		 }else{
			 String qtfy1 = totalCoun19; 
			 Double qtfyf1 = Double.parseDouble(qtfy1); 
			 DecimalFormat qtfy11 = new DecimalFormat("0.00"); 
			 s19 = qtfy11.format(qtfyf1); 
			 request.put("totalCoun19", s19);
			 netasset.setCyssrztqzyjj(s19);
		 }
		 
		 /**
		  * 专用基金—科技成果转化基金—从预算结余中提取
		  * 
		  */
		 String sql20 = "select sum(VDebitAmount)-sum(VCreditAmount) from AccountingVoucher where SName like '专用基金—科技成果转化基金—从预算结余中提取'";
		 String totalCoun20 = PaginationUtil.scccc(sql20);
		 String s20 = "";
		 if(totalCoun20  == "null"){
			 totalCoun20 = "";
			 request.put("totalCoun20", totalCoun20);
			 netasset.setCysjyztqljyy(totalCoun20);
			 netasset.setCysjyztqzyjj(totalCoun20);
		 }else{
			 String qtfy2 = totalCoun20; 
			 Double qtfyf2 = Double.parseDouble(qtfy2); 
			 DecimalFormat qtfy12 = new DecimalFormat("0.00"); 
			  s20 = qtfy12.format(qtfyf2); 
			 request.put("totalCoun20", s20);
			 netasset.setCysjyztqljyy(s20);
			 netasset.setCysjyztqzyjj(s20);
		 }
		 /**
		  * 
		  * 权益法调整
		  * 
		  */
		 String sql23 = "select sum(VDebitAmount)-sum(VCreditAmount) from AccountingVoucher where SName like '权益法调整'";
		 String totalCoun23 = PaginationUtil.scccc(sql23);
		 String s23 = "";
		 if(totalCoun23  == "null"){
			 totalCoun23 = "";
			 request.put("totalCoun23", totalCoun23);
			 netasset.setQyftzqyftz(totalCoun23);
		 }else{
			 String qtfy21 = totalCoun23; 
			 Double qtfyf21 = Double.parseDouble(qtfy21); 
			 DecimalFormat qtfy121 = new DecimalFormat("0.00"); 
			 s23 = qtfy121.format(qtfyf21); 
			 request.put("totalCoun23", s23);
			 netasset.setQyftzqyftz(totalCoun23);
		 }
		 /*
		  * 专用基金-职工福利基金
		  */
		 String sql21 = "select sum(VDebitAmount)-sum(VCreditAmount) from AccountingVoucher where SName like '专用基金-职工福利基金'";
		 String totalCoun21 = PaginationUtil.scccc(sql21);
		 String s21 = "";
		 if(totalCoun21  == "null"){
			 totalCoun21 = "";
			 request.put("totalCoun21",totalCoun21);
			 netasset.setSzdzyjjzyjj(totalCoun21);
			 netasset.setSzdzyjjzyjj(totalCoun21);
		 }else{
			 String qtfy3 = totalCoun21; 
			 Double qtfyf3 = Double.parseDouble(qtfy3); 
			 DecimalFormat qtfy13 = new DecimalFormat("0.00"); 
			 s21 = qtfy13.format(qtfyf3); 
			 request.put("totalCoun21", s21);
			 netasset.setSzdzyjjzyjj(s21);
		 }
		 /**
			 * 使用专用基金行累计盈余列=凭证“借：专用基金 贷：累计盈余”
			 */
			acco = baseService.findObjList("from AccountingVoucher where SName like '专用基金%' and  VDebitAmount > 0");//先查询科目
			String ss = "";
			for (int i = 0; i < acco.size(); i++) {
				String adf = acco.get(i).getVNo();//筛选出凭证编号
				String a = acco.get(i).getVDebitAmount();
				acco = baseService.findObjList("from AccountingVoucher where SName like '专用基金%' and  VDebitAmount > 0 and VNo='"+adf+"'");//先查询科目
				System.out.println(acco);
				if(acco.size() == 1){ //判断有数据没有
					absd = baseService.findObjList("from AccountingVoucher where SName like '累计盈余%' and VCreditAmount >0 and VNo='"+adf+"'");
					System.out.println(absd);
				}
					for (int j = 0; j < absd.size(); j++) {
					String b = absd.get(j).getVCreditAmount();
					 if( a == "" || b  == ""){
						 a = "";
						 request.put("a3", a);
						 netasset.setSyzyjjljyy(a);
					 }else{
						 BigDecimal num1 = new BigDecimal(a);
						 BigDecimal num2 = new BigDecimal(b);
						 BigDecimal result = num1.add(num2);
						 String ouft = result.toString();
						 String hhhh = ouft; 
						 Double hhhhf = Double.parseDouble(hhhh); 
						 DecimalFormat hhhh1 = new DecimalFormat("0.00"); 
						 ss = hhhh1.format(hhhhf);
						 request.put("a3", ss);
						 netasset.setSyzyjjljyy(ss);
						 
					}
				}
				
			}
			 /**
			 * 使用专用基金行累计盈余列=凭证“借：专用基金 贷：累计盈余”
			 */
			acco = baseService.findObjList("from AccountingVoucher where SName like '专用基金%' and  VDebitAmount > 0");//先查询科目
			String s3 = "";
			for (int i = 0; i < acco.size(); i++) {
				String adf = acco.get(i).getVNo();//筛选出凭证编号
				String a = acco.get(i).getVDebitAmount();
				acco = baseService.findObjList("from AccountingVoucher where SName like '专用基金%' and  VDebitAmount > 0 and VNo='"+adf+"'");//先查询科目
				System.out.println(acco);
				if(acco.size() == 1){ //判断有数据没有
					absd = baseService.findObjList("from AccountingVoucher where SName like '银行存款%' and VCreditAmount >0 and VNo='"+adf+"'");
					System.out.println(absd);
					for (int j = 0; j < absd.size(); j++) {
					String b = absd.get(j).getVCreditAmount();
					 if( a == "" || b  == ""){
						 a = "";
						 request.put("a4", a);
						 netasset.setSyzyjjzyjj(a);
					 }else{
						 BigDecimal num1 = new BigDecimal(a);
						 BigDecimal num2 = new BigDecimal(b);
						 BigDecimal result = num1.add(num2);
						 String ouft = result.toString();
						 String hhhh = ouft; 
						 Double hhhhf = Double.parseDouble(hhhh); 
						 DecimalFormat hhhh1 = new DecimalFormat("0.00"); 
						 s3 = hhhh1.format(hhhhf);
						 request.put("a4", s3);
						 netasset.setSyzyjjzyjj(s3);
					 }
					}
				}
			}
			/**
			 * 累计盈余 本年年末余额
			 */
		 String m11 = "";
		 if(m11 ==""){
		 String m1 = s; // 以前年度盈余
		 String m2 =s1;//  * 本年盈余分配
		 String m3 =s2;// * 无偿调拨净资产
		 //String m4 =s19;//* 专用基金-科技成果转化基金-从预算收入中提取
		 String m5 =s20;//专用基金—科技成果转化基金—从预算结余中提取
		 //String m6 =s23;//权益法调整
		 //String m7 =s21;//专用基金-职工福利基金
	     String m8 =ss;//（8）使用专用基金行累计盈余列=凭证“借：专用基金 贷：累计盈余”
	     String m9 =s3;//（9）使用专用基金行专用基金列=凭证“借：专用基金 贷：银行存款”
	     if(m1.equals("")){
	    	 m1 ="0.0";
	     }
	     if(m2.equals("")){
	    	 m2 ="0.0";
	     }
	     if(m3.equals("")){
	    	 m3 ="0.0";
	     }
	    /* if(m4.equals("")){
	    	 m4 ="0.0";
	     }*/
	     if(m5.equals("")){
	    	 m5 ="0.0";
	     }
	     /*if(m6.equals("")){
	    	 m6 ="0.0"; 
	     }*/
	     /*if(m7.equals("")){
	    	 m7 ="0.0"; 
	     }*/
	     if(m8.equals("")){
	    	 m8 ="0.0"; 
	     }
	     if(m9.equals("")){
	    	 m9 ="0.0"; 
	     }
	     if(!m1.equals("")||!m2.equals("")||!m3.equals("")||!m5.equals("")||!m8.equals("")||!m9.equals("")){
		 BigDecimal num1 = new BigDecimal(m1);
		 BigDecimal num2 = new BigDecimal(m2);
		 BigDecimal num3 = new BigDecimal(m3);
		 BigDecimal num5 = new BigDecimal(m5);
		 BigDecimal num8 = new BigDecimal(m8);
		 BigDecimal num9 = new BigDecimal(m9);
		 BigDecimal result = num1.subtract(num2).subtract(num3).subtract(num5).subtract(num8).subtract(num9);
		 String ouft = result.toString();
		 String hhhh = ouft; 
		 Double hhhhf = Double.parseDouble(hhhh); 
		 DecimalFormat hhhh1 = new DecimalFormat("0.00"); 
		 m11 = hhhh1.format(hhhhf);
		 request.put("m10", m11);
		 netasset.setBnnmyeljyy(m11);
	     }
		 }
		 
		 /**
		  * 专用基金  本年年末余额
		  */
		 String m12 = "";
		 if(m12 ==""){
		 //String m1 = s; // 以前年度盈余
		// String m2 =s1;//  * 本年盈余分配
		 //String m3 =s2;// * 无偿调拨净资产
		 String m4 =s19;//* 专用基金-科技成果转化基金-从预算收入中提取
		 String m5 =s20;//专用基金—科技成果转化基金—从预算结余中提取
		 //String m6 =s23;//权益法调整
		 String m7 =s21;//专用基金-职工福利基金
	    // String m8 =ss;//（8）使用专用基金行累计盈余列=凭证“借：专用基金 贷：累计盈余”
	     String m9 =s3;//（9）使用专用基金行专用基金列=凭证“借：专用基金 贷：银行存款”
	    /* if(m1.equals("")){
	    	 m1 ="0.0";
	     }*/
	    /* if(m2.equals("")){
	    	 m2 ="0.0";
	     }
	     if(m3.equals("")){
	    	 m3 ="0.0";
	     }*/
	     if(m4.equals("")){
	    	 m4 ="0.0";
	     }
	     if(m5.equals("")){
	    	 m5 ="0.0";
	     }
	     if(m7.equals("")){
	    	 m7 ="0.0"; 
	     }
	     /*if(m6.equals("")){
	    	 m6 ="0.0"; 
	     }
	     
	    /* if(m8.equals("")){
	    	 m8 ="0.0"; 
	     }*/
	     if(m9.equals("")){
	    	 m9 ="0.0"; 
	     }
	     if(!m5.equals("")||!m9.equals("")){
	     BigDecimal num4 = new BigDecimal(m4);
		 BigDecimal num5 = new BigDecimal(m5);
		 BigDecimal num7 = new BigDecimal(m7);
		 BigDecimal num9 = new BigDecimal(m9);
		 BigDecimal result =num4.subtract(num5).subtract(num9).subtract(num7);
		 String ouft = result.toString();
		 String hhhh = ouft; 
		 Double hhhhf = Double.parseDouble(hhhh); 
		 DecimalFormat hhhh1 = new DecimalFormat("0.00"); 
		 m12 = hhhh1.format(hhhhf);
		 request.put("m11", m12);
		 netasset.setBnnmyezyjj(m12);
	     }
		 }
		 /**
		  * 专用基金  本年年末余额
		  */
		 String m13 = "";
		 if(m13 ==""){
			 
		 String m6 =s23;//权益法调整
	     if(m6.equals("")){
	    	 m6 ="0.0"; 
	     }
	     if(!m6.equals("")){
		 BigDecimal num6 = new BigDecimal(m6);
		 BigDecimal result =num6;
		 String ouft = result.toString();
		 String hhhh = ouft; 
		 Double hhhhf = Double.parseDouble(hhhh); 
		 DecimalFormat hhhh1 = new DecimalFormat("0.00"); 
		 m13 = hhhh1.format(hhhhf);
		 request.put("m12", m13);
		 netasset.setBnnmyeqyftz(m13);
	     }
		 }
		 String m14 = "";
		 if(m14 ==""){
		 String m1 = s; // 以前年度盈余
		 String m2 =s1;//  * 本年盈余分配
		 String m3 =s2;// * 无偿调拨净资产
		 String m4 =s19;//* 专用基金-科技成果转化基金-从预算收入中提取
		 String m5 =s20;//专用基金—科技成果转化基金—从预算结余中提取
		 String m6 =s23;//权益法调整
		 String m7 =s21;//专用基金-职工福利基金
	     String m8 =ss;//（8）使用专用基金行累计盈余列=凭证“借：专用基金 贷：累计盈余”
	     String m9 =s3;//（9）使用专用基金行专用基金列=凭证“借：专用基金 贷：银行存款”
	     if(m1.equals("")){
	    	 m1 ="0.0";
	     }
	     if(m2.equals("")){
	    	 m2 ="0.0";
	     }
	     if(m3.equals("")){
	    	 m3 ="0.0";
	     }
	     if(m4.equals("")){
	    	 m4 ="0.0";
	     }
	     if(m5.equals("")){
	    	 m5 ="0.0";
	     }
	     if(m6.equals("")){
	    	 m6 ="0.0"; 
	     }
	     if(m7.equals("")){
	    	 m7 ="0.0"; 
	     }
	     if(m8.equals("")){
	    	 m8 ="0.0"; 
	     }
	     if(m9.equals("")){
	    	 m9 ="0.0"; 
	     }
	     if(!m1.equals("")||!m2.equals("")||!m3.equals("")||!m5.equals("")||!m8.equals("")||!m9.equals("")){
		 BigDecimal num1 = new BigDecimal(m1);
		 BigDecimal num2 = new BigDecimal(m2);
		 BigDecimal num3 = new BigDecimal(m3);
		 BigDecimal num4 = new BigDecimal(m4);
		 BigDecimal num5 = new BigDecimal(m5);
		 BigDecimal num6 = new BigDecimal(m6);
		 BigDecimal num7 = new BigDecimal(m7);
		 BigDecimal num8 = new BigDecimal(m8);
		 BigDecimal num9 = new BigDecimal(m9);
		 BigDecimal result = num1.subtract(num2).subtract(num3).subtract(num5).subtract(num8).subtract(num9).subtract(num4).subtract(num6).subtract(num7);
		 String ouft = result.toString();
		 String hhhh = ouft; 
		 Double hhhhf = Double.parseDouble(hhhh); 
		 DecimalFormat hhhh1 = new DecimalFormat("0.00"); 
		 m14 = hhhh1.format(hhhhf);
		 request.put("m14", m14);
		 
	     	}
		 }
		 
		 String m15 = "";
		 if(m15 ==""){
		 String m1 = s; // 以前年度盈余
		 String m2 =s1;//  * 本年盈余分配
		 String m3 =s2;// * 无偿调拨净资产
		 //String m4 =s19;//* 专用基金-科技成果转化基金-从预算收入中提取
		 String m5 =s20;//专用基金—科技成果转化基金—从预算结余中提取
		 //String m6 =s23;//权益法调整
		 //String m7 =s21;//专用基金-职工福利基金
	     String m8 =ss;//（8）使用专用基金行累计盈余列=凭证“借：专用基金 贷：累计盈余”
	     String m9 =s3;//（9）使用专用基金行专用基金列=凭证“借：专用基金 贷：银行存款”
	     if(m1.equals("")){
	    	 m1 ="0.0";
	     }
	     if(m2.equals("")){
	    	 m2 ="0.0";
	     }
	     if(m3.equals("")){
	    	 m3 ="0.0";
	     }
	    /* if(m4.equals("")){
	    	 m4 ="0.0";
	     }*/
	     if(m5.equals("")){
	    	 m5 ="0.0";
	     }
	     /*if(m6.equals("")){
	    	 m6 ="0.0"; 
	     }*/
	     /*if(m7.equals("")){
	    	 m7 ="0.0"; 
	     }*/
	     if(m8.equals("")){
	    	 m8 ="0.0"; 
	     }
	     if(m9.equals("")){
	    	 m9 ="0.0"; 
	     }
	     if(!m1.equals("")||!m2.equals("")||!m3.equals("")||!m5.equals("")||!m8.equals("")||!m9.equals("")){
		 BigDecimal num1 = new BigDecimal(m1);
		 BigDecimal num2 = new BigDecimal(m2);
		 BigDecimal num3 = new BigDecimal(m3);
		 BigDecimal num5 = new BigDecimal(m5);
		 BigDecimal num8 = new BigDecimal(m8);
		 BigDecimal num9 = new BigDecimal(m9);
		 BigDecimal result = num1.subtract(num2).subtract(num3).subtract(num5).subtract(num8).subtract(num9);
		 String ouft = result.toString();
		 String hhhh = ouft; 
		 Double hhhhf = Double.parseDouble(hhhh); 
		 DecimalFormat hhhh1 = new DecimalFormat("0.00"); 
		 m15 = hhhh1.format(hhhhf);
		 request.put("m15", m15);
		 netasset.setBnnmyezjchj(m15);
	     }
		 }
		return "carryoverB";
	}
	
	/**
	 * 清空
	 * @return
	 * @throws Exception
	 */
	public String deleteTable()throws Exception{
		
		boolean flag = baseService.zxSql("update budgetary set this_year='',last_year=''");
		 budgetList = baseService.findObjList("from Budgetary b order by b.orderId ");
		if(flag)
		return "budgetRevenue";
		return "删除失败";
	}
	
	/**
	 * 导入数据
	 * @return
	 * @throws Exception
	 */
	private List<File> file;
	private String filename;//文件名
	private Integer msg;
	@SuppressWarnings("static-access")
	public String upLoadTable()throws Exception{
	
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
					if (cellList.size()>=3) {
						baseService.zxSql("update budgetary set this_year='',last_year=''");
						break;
					}
				}

				List<Budgetary> budList = baseService.findObjList("from Budgetary order by orderId");
				
				for (int i = 1; i < list.size(); i++) {
					List<String> cellList = list.get(i);
					if (cellList.size()>=3) {//列
						for (Budgetary bud : budList) {
							if(cellList.get(0).equals(bud.getProject())){
									//本年数
								bud.setThisYear(cellList.get(1).toString());
									//上年数
								bud.setLastYear(cellList.get(2).toString());
								baseService.saveOrUpdate(bud);
							}
						} 
						setMsg(1);
					} else {
						setMsg(0);
						break;
					}
				}
			}else {
				setMsg(0);
			}
			return msg+"";
		} catch (Exception e) {
			e.printStackTrace();
			setMsg(0);
			return msg+"";
		}
   

		
	}
	
/*--------------------------------------------------------------------------*/
	public void exportCashExcels() throws IOException{
		HttpServletResponse response= ServletActionContext.getResponse();
		 String[] titles ={"项目","本年数","去年数"};
		 String[] column ={"一、本期预算收入","(一) 财政拨款预算收入","其中：政府性基金收入","(二) 事业预算收入","(三) 上级补助预算收入","(四) 附属单位上缴预算收入","(五) 经营预算收入","(六) 债务预算收入","(七) 非同级财政拨款预算收入"
				 ,"(八) 投资预算收益","(九) 其他预算收入","其中：利息预算收入","捐赠预算收入","租金预算收入","二、本年预算支出","（一）行政支出","（二）事业支出","（三）经营支出","（四）上缴上级支出","（五）对附属单位补助支出","（六）投资支出","（七）债务还本支出","（八）其他支出","其中：利息支出","捐赠支出","三、本年预算收支差额"};
		String filename = "预算收入支出.xlsx";
	   OutputStream os = response.getOutputStream();
	   response.reset();
	   response.setHeader("Content-Disposition", 
	     "attachment;filename=" + new String(filename.getBytes("GB2312"), "ISO8859-1"));
	   response.setContentType("application/msexcel");
	   try {
			XSSFWorkbook wb = new XSSFWorkbook();
		      XSSFCellStyle style = wb.createCellStyle();
		      XSSFFont font = wb.createFont();
		      font.setFontName("楷体");
		      font.setFontHeightInPoints((short) 16);
		      font.setBoldweight((short) 700);
		      style.setFont(font);	
		      style.setWrapText(true);
		      style.setAlignment(XSSFCellStyle.ALIGN_CENTER);//居中
		      style.setVerticalAlignment((short) 1);
		      style.setBorderBottom((short) 1);
		      style.setBorderLeft((short) 1);
		      style.setBorderTop((short) 1);
		      style.setBorderRight((short) 1);

		      XSSFCellStyle styleCell = wb.createCellStyle();
		      styleCell.setAlignment((short) 2);
		      styleCell.setVerticalAlignment((short) 1);
		      styleCell.setBorderBottom((short) 1);
		      styleCell.setBorderLeft((short) 1);
		      styleCell.setBorderTop((short) 1);
		      styleCell.setBorderRight((short) 1);

		      XSSFCellStyle styleLeftCell = wb.createCellStyle();
		      styleLeftCell.setAlignment((short) 1);
		      styleLeftCell.setVerticalAlignment((short) 1);
		      styleLeftCell.setBorderBottom((short) 1);
		      styleLeftCell.setBorderLeft((short) 1);
		      styleLeftCell.setBorderTop((short) 1);
		      styleLeftCell.setBorderRight((short) 1);

		      XSSFSheet sheet = wb.createSheet("预算收入支出");
		      sheet.setDefaultColumnWidth(35);

		      CellRangeAddress callRangeAddress = new CellRangeAddress(0, 0, 0, 2);
		      sheet.addMergedRegion(callRangeAddress);
		      XSSFRow rowOne = sheet.createRow(0);	
		      rowOne.setHeight((short) 400);
		      XSSFCell cel0 = rowOne.createCell(0);
		      cel0.setCellStyle(style);
		      cel0.setCellValue("预算收入支出");
		      XSSFRow row = sheet.createRow(1);
		      for (int i = 0; i < titles.length; ++i) {
		        XSSFCell cell = row.createCell(i);
		        cell.setCellStyle(styleCell);

		        cell.setCellValue(titles[i]);
		      }
		      
		      Static thisReportData = new Static();
		      //Income reportData =new  Income();
		      for (int j = 0; j < column.length; ++j)
		      {
		        XSSFRow rowContent = null;
		        rowContent = sheet.createRow(j + 2);
		        for (int m = 0; m < titles.length; ++m) {
		          XSSFCell createCell = rowContent.createCell(m);
		          createCell.setCellStyle(styleCell);
		          if (m == 0) {
		            createCell.setCellValue(column[j]);
		            createCell.setCellStyle(styleLeftCell);
		          } else if (m == 1) {
		            if ("一、本期预算收入".equals(column[j]))
		              createCell.setCellValue(incomeexpenditure.getBqyssr());
		            else if ("(一) 财政拨款预算收入".equals(column[j]))
		              createCell.setCellValue(incomeexpenditure.getCzbkyssr());
		            else if ("其中：政府性基金收入".equals(column[j]))
		              createCell.setCellValue(incomeexpenditure.getZfxjjsr());
		            else if ("(二) 事业预算收入".equals(column[j]))
			              createCell.setCellValue(incomeexpenditure.getSyyssr());
		            else if ("(三) 上级补助预算收入".equals(column[j]))
			              createCell.setCellValue(incomeexpenditure.getSjfzyssr());
		            else if ("(四) 附属单位上缴预算收入".equals(column[j]))
			              createCell.setCellValue(incomeexpenditure.getFsdwsjyssr());
		            else if ("(五) 经营预算收入".equals(column[j]))
			              createCell.setCellValue(incomeexpenditure.getJyyssr());
		            else if ("(六) 债务预算收入".equals(column[j]))
			              createCell.setCellValue(incomeexpenditure.getZwyssr());
		            else if ("(七) 非同级财政拨款预算收入".equals(column[j]))
			              createCell.setCellValue(incomeexpenditure.getFtjczbkyssr());
		            else if ("(八) 投资预算收益".equals(column[j]))
			              createCell.setCellValue(incomeexpenditure.getTzyssy());
		            else if ("(九) 其他预算收入".equals(column[j]))
			              createCell.setCellValue(incomeexpenditure.getQtyssr());
		            else if ("其中：利息预算收入".equals(column[j]))
			              createCell.setCellValue(incomeexpenditure.getLxyssr());
		            else if ("捐赠预算收入".equals(column[j]))
			              createCell.setCellValue(incomeexpenditure.getJzyssr());
		            else if ("租金预算收入".equals(column[j]))
			              createCell.setCellValue(incomeexpenditure.getZjyssr());
		            else if ("二、本年预算支出".equals(column[j]))
			              createCell.setCellValue(incomeexpenditure.getBnyszc());
		            else if ("（一）行政支出".equals(column[j]))
			              createCell.setCellValue(incomeexpenditure.getHzzc());
		            else if ("（二）事业支出".equals(column[j]))
			              createCell.setCellValue(incomeexpenditure.getSyzc());
		            else if ("（三）经营支出".equals(column[j]))
			              createCell.setCellValue(incomeexpenditure.getJyzc());
		            else if ("（四）上缴上级支出".equals(column[j]))
			              createCell.setCellValue(incomeexpenditure.getSjsjzc());
		            else if ("（五）对附属单位补助支出".equals(column[j]))
			              createCell.setCellValue(incomeexpenditure.getDfsdwbzzc());
		            else if ("（六）投资支出".equals(column[j]))
			              createCell.setCellValue(incomeexpenditure.getTzzc());
		            else if ("（七）债务还本支出".equals(column[j]))
			              createCell.setCellValue(incomeexpenditure.getZwhbzc());
		            else if ("（八）其他支出".equals(column[j]))
			              createCell.setCellValue(incomeexpenditure.getQtzc());
		            else if ("其中：利息支出".equals(column[j]))
			              createCell.setCellValue(incomeexpenditure.getLxzc());
		            else if ("捐赠支出".equals(column[j]))
			              createCell.setCellValue(incomeexpenditure.getJzzc());
		            else if ("三、本年预算收支差额".equals(column[j]))
			              createCell.setCellValue(incomeexpenditure.getBnysszce());
		          }else if (m== 2) {
		        	  if ("一、本期预算收入".equals(column[j]))
			              createCell.setCellValue(incomeexpenditure.getBqyssrqn());
			            else if ("(一) 财政拨款预算收入".equals(column[j]))
			              createCell.setCellValue(incomeexpenditure.getCzbkyssrqn());
			            else if ("其中：政府性基金收入".equals(column[j]))
			              createCell.setCellValue(incomeexpenditure.getZfxjjsrqn());
			            else if ("(二) 事业预算收入".equals(column[j]))
				              createCell.setCellValue(incomeexpenditure.getSyyssrqn());
			            else if ("(三) 上级补助预算收入".equals(column[j]))
				              createCell.setCellValue(incomeexpenditure.getSjfzyssrqn());
			            else if ("(四) 附属单位上缴预算收入".equals(column[j]))
				              createCell.setCellValue(incomeexpenditure.getFsdwsjyssrqn());
			            else if ("(五) 经营预算收入".equals(column[j]))
				              createCell.setCellValue(incomeexpenditure.getJyyssrqn());
			            else if ("(六) 债务预算收入".equals(column[j]))
				              createCell.setCellValue(incomeexpenditure.getZwyssrqn());
			            else if ("(七) 非同级财政拨款预算收入".equals(column[j]))
				              createCell.setCellValue(incomeexpenditure.getFtjczbkyssrqn());
			            else if ("(八) 投资预算收益".equals(column[j]))
				              createCell.setCellValue(incomeexpenditure.getTzyssyqn());
			            else if ("(九) 其他预算收入".equals(column[j]))
				              createCell.setCellValue(incomeexpenditure.getQtyssrqn());
			            else if ("其中：利息预算收入".equals(column[j]))
				              createCell.setCellValue(incomeexpenditure.getLxyssrqn());
			            else if ("捐赠预算收入".equals(column[j]))
				              createCell.setCellValue(incomeexpenditure.getJzyssrqn());
			            else if ("租金预算收入".equals(column[j]))
				              createCell.setCellValue(incomeexpenditure.getZjyssrqn());
			            else if ("二、本年预算支出".equals(column[j]))
				              createCell.setCellValue(incomeexpenditure.getBnyszcqn());
			            else if ("（一）行政支出".equals(column[j]))
				              createCell.setCellValue(incomeexpenditure.getHzzcqn());
			            else if ("（二）事业支出".equals(column[j]))
				              createCell.setCellValue(incomeexpenditure.getSyzcqn());
			            else if ("（三）经营支出".equals(column[j]))
				              createCell.setCellValue(incomeexpenditure.getJyzcqn());
			            else if ("（四）上缴上级支出".equals(column[j]))
				              createCell.setCellValue(incomeexpenditure.getSjsjzcqn());
			            else if ("（五）对附属单位补助支出".equals(column[j]))
				              createCell.setCellValue(incomeexpenditure.getDfsdwbzzcqn());
			            else if ("（六）投资支出".equals(column[j]))
				              createCell.setCellValue(incomeexpenditure.getTzzcqn());
			            else if ("（七）债务还本支出".equals(column[j]))
				              createCell.setCellValue(incomeexpenditure.getZwhbzcqn());
			            else if ("（八）其他支出".equals(column[j]))
				              createCell.setCellValue(incomeexpenditure.getQtzcqn());
			            else if ("其中：利息支出".equals(column[j]))
				              createCell.setCellValue(incomeexpenditure.getLxzcqn());
			            else if ("捐赠支出".equals(column[j]))
				              createCell.setCellValue(incomeexpenditure.getJzzcqn());
			            else if ("三、本年预算收支差额".equals(column[j]))
				              createCell.setCellValue(incomeexpenditure.getBnysszceqn());
				}
		          }
		      }
		   wb.write(os);
		      os.close();
		    } catch (Exception e) {
		      e.printStackTrace();
		    }
	}
	
	
	
	
	
	
	/**
	 * 导出净资产变动表
	 * @throws IOException
	 */
public void exportCashExcel() throws IOException{
	HttpServletResponse response= ServletActionContext.getResponse();
	 String[] titles ={"项目","累计盈余","专用基金","权益法调整","资金产合计","累计盈余","专用基金","权益法调整","资金产合计" };
	 String[] titles1 ={"去年","本年"};
	 String[] params = new String[] { "编制单位:", "时间","金额单位:"};
	 String[] column ={"一、上年年末余额","二、以前年度盈余调整（ 减少以“-”号填列）","三、本年年初余额","四、本年变动金额（减少以“-”号填列）","（一）本年盈余","（二）无偿调拨净资产","（三）归集调整预算结转结余","（四）提取或设置专用基金","其中：从预算收入中提取"
			 ,"从预算结余中提取","设置的专用基金","（五）使用专用基金","（六）权益法调整","五、本年年末余额"};
	String filename = "净资产变动.xlsx";
   OutputStream os = response.getOutputStream();
   response.reset();
   response.setHeader("Content-Disposition", 
     "attachment;filename=" + new String(filename.getBytes("GB2312"), "ISO8859-1"));
   response.setContentType("application/msexcel");
   try {
	   XSSFWorkbook wb = new XSSFWorkbook();
	   XSSFCellStyle style = wb.createCellStyle();
	   XSSFFont font = wb.createFont();
	   font.setFontName("楷体");
	   font.setFontHeightInPoints((short) 16);
	   font.setBoldweight((short) 700);
	   style.setFont(font);
	   style.setWrapText(true);
	   style.setAlignment(XSSFCellStyle.ALIGN_CENTER);//居中
	   style.setVerticalAlignment((short) 0);

       XSSFCellStyle style1 = wb.createCellStyle();
       font.setBoldweight((short) 700);
       style1.setFont(font);
       style1.setWrapText(true);
       style1.setAlignment(XSSFCellStyle.ALIGN_CENTER);//居中
       style1.setVerticalAlignment((short) 0);

	   XSSFCellStyle styleCell = wb.createCellStyle();
	   styleCell.setAlignment((short) 2);
	   styleCell.setVerticalAlignment((short) 0);
	   styleCell.setBorderBottom(HSSFCellStyle.BORDER_THIN);
	   styleCell.setBorderLeft(HSSFCellStyle.BORDER_THIN);
	   styleCell.setBorderTop(HSSFCellStyle.BORDER_THIN);
	   styleCell.setBorderRight(HSSFCellStyle.BORDER_THIN);

	   XSSFCellStyle styleLeftCell = wb.createCellStyle();
	   styleLeftCell.setAlignment((short) 0);
	   styleLeftCell.setVerticalAlignment((short) 0);
	   styleLeftCell.setBorderBottom(HSSFCellStyle.BORDER_THIN);
	   styleLeftCell.setBorderLeft(HSSFCellStyle.BORDER_THIN);
	   styleLeftCell.setBorderTop(HSSFCellStyle.BORDER_THIN);
	   styleLeftCell.setBorderRight(HSSFCellStyle.BORDER_THIN);

       XSSFRow rowContentf = null;



	   XSSFSheet sheet = wb.createSheet("净资产变动表");
	      sheet.setDefaultColumnWidth(30);
	      CellRangeAddress callRangeAddress = new CellRangeAddress(0, 0, 0, 8);
	      sheet.addMergedRegion(callRangeAddress);
	      XSSFRow rowOne = sheet.createRow(0);
	      rowOne.setHeight((short) 800);
	      XSSFCell cel0 = rowOne.createCell(0);
	      cel0.setCellStyle(style);
	      cel0.setCellValue("净资产变动表");
       XSSFRow row = sheet.createRow(1);
       for (int i = 0; i < titles.length; ++i) {
           XSSFCell cell = row.createCell(i);
           cell.setCellStyle(styleCell);

           cell.setCellValue(titles[i]);
       }
	      //Income reportData =new  Income();
	      for (int j = 0; j < column.length; ++j)
	      {
	        XSSFRow rowContent = null;
	        rowContent = sheet.createRow(j + 2);
	        for (int m = 0; m < titles.length; ++m) {
	         XSSFCell createCell = rowContent.createCell(m);
	          createCell.setCellStyle(styleCell);
	          if (m == 0) {
	            createCell.setCellValue(column[j]);
	           createCell.setCellStyle(styleLeftCell);
	          } /*else if (m == 1) {
	            if ("一、上年年末余额".equals(column[j]))
	              createCell.setCellValue(netasset.getSnnmyeljyy());
	            else if ("二、以前年度盈余调整（ 减少以“-”号填列）".equals(column[j]))
	              createCell.setCellValue(netasset.getYqndyytzljyy());
	            else if ("三、本年年初余额".equals(column[j]))
	             createCell.setCellValue(netasset.getBnncye());
	            else if ("四、本年变动金额（减少以“-”号填列）".equals(column[j]))
	              createCell.setCellValue(netasset.getBnbdjeljyy());
	            else if ("（一）本年盈余".equals(column[j]))
		              createCell.setCellValue(netasset.getBnyyljyy());
	            else if ("（二）无偿调拨净资产".equals(column[j]))
		              createCell.setCellValue(netasset.getWctbjzcljyy());
	            else if ("（三）归集调整预算结转结余".equals(column[j]))
		              createCell.setCellValue("");
	            else if ("（四）提取或设置专用基金".equals(column[j]))
		              createCell.setCellValue("");
	            else if ("其中：从预算收入中提取".equals(column[j]))
		              createCell.setCellValue(netasset.getCyssrztqljyy());
	            else if ("从预算结余中提取".equals(column[j]))
		              createCell.setCellValue(netasset.getCysjyztqljyy());
	            else if ("设置的专用基金".equals(column[j]))
		              createCell.setCellValue(netasset.getSzdzyjjljyy());
	            else if ("（五）使用专用基金".equals(column[j]))
		              createCell.setCellValue(netasset.getSyzyjjljyy());
	            else if ("（六）权益法调整".equals(column[j]))
		              createCell.setCellValue(netasset.getQyftzljyy());
	            else if ("五、本年年末余额".equals(column[j]))
		              createCell.setCellValue(netasset.getBnnmyeljyy());
	        } else if (m == 2) {
	            if ("一、上年年末余额".equals(column[j]))
	              createCell.setCellValue(netasset.getSnnmyezyjj());
	            else if ("二、以前年度盈余调整（ 减少以“-”号填列）".equals(column[j]))
	              createCell.setCellValue(netasset.getYqndyytzzyjj());
	            else if ("三、本年年初余额".equals(column[j]))
	              createCell.setCellValue(netasset.getBnncyezyjj());
	            else if ("四、本年变动金额（减少以“-”号填列）".equals(column[j]))
	              createCell.setCellValue(netasset.getBnbdjezyjj());
	            else if ("（一）本年盈余".equals(column[j]))
		              createCell.setCellValue(netasset.getBnyyzyjj());
	            else if ("（二）无偿调拨净资产".equals(column[j]))
		              createCell.setCellValue(netasset.getWctbjzczyjj());
	            else if ("（三）归集调整预算结转结余".equals(column[j]))
		              createCell.setCellValue(netasset.getGjtzysjzjyzyjj());
	            else if ("（四）提取或设置专用基金".equals(column[j]))
		              createCell.setCellValue(netasset.getTqhszzyjjzyjj());
	            else if ("其中：从预算收入中提取".equals(column[j]))
		              createCell.setCellValue(netasset.getCyssrztqzyjj());
	            else if ("从预算结余中提取".equals(column[j]))
		              createCell.setCellValue(netasset.getCysjyztqzyjj());
	            else if ("设置的专用基金".equals(column[j]))
		              createCell.setCellValue(netasset.getSzdzyjjzyjj());
	            else if ("（五）使用专用基金".equals(column[j]))
		              createCell.setCellValue(netasset.getSyzyjjzyjj());
	            else if ("（六）权益法调整".equals(column[j]))
		              createCell.setCellValue(netasset.getQyftzzyjj());
	            else if ("五、本年年末余额".equals(column[j]))
		              createCell.setCellValue(netasset.getBnnmyezyjj());
	          }else if (m == 3) {
		            if ("一、上年年末余额".equals(column[j]))
			              createCell.setCellValue(netasset.getSnnmyeqyftz());
			            else if ("二、以前年度盈余调整（ 减少以“-”号填列）".equals(column[j]))
			              createCell.setCellValue(netasset.getYqndyytzqyftz());
			            else if ("三、本年年初余额".equals(column[j]))
			              createCell.setCellValue(netasset.getBnncyeqyftz());
			            else if ("四、本年变动金额（减少以“-”号填列）".equals(column[j]))
			              createCell.setCellValue(netasset.getBnbdjeqyftz());
			            else if ("（一）本年盈余".equals(column[j]))
				              createCell.setCellValue(netasset.getBnyyqyftz());
			            else if ("（二）无偿调拨净资产".equals(column[j]))
				              createCell.setCellValue(netasset.getWctbjzcqyftz());
			            else if ("（三）归集调整预算结转结余".equals(column[j]))
				              createCell.setCellValue(netasset.getGjtzysjzjyqyftz());
			            else if ("（四）提取或设置专用基金".equals(column[j]))
				              createCell.setCellValue(netasset.getTqhszzyjjqyftz());
			            else if ("其中：从预算收入中提取".equals(column[j]))
				              createCell.setCellValue(netasset.getCyssrztqqyftz());
			            else if ("从预算结余中提取".equals(column[j]))
				              createCell.setCellValue(netasset.getCysjyztqqyftz());
			            else if ("设置的专用基金".equals(column[j]))
				              createCell.setCellValue(netasset.getSzdzyjjqyftz());
			            else if ("（五）使用专用基金".equals(column[j]))
				              createCell.setCellValue(netasset.getSyzyjjqyftz());
			            else if ("（六）权益法调整".equals(column[j]))
				              createCell.setCellValue(netasset.getQyftzqyftz());
			            else if ("五、本年年末余额".equals(column[j]))
				              createCell.setCellValue(netasset.getBnnmyeqyftz());
	          }else if (m == 4) {
		            if ("一、上年年末余额".equals(column[j]))
			              createCell.setCellValue(netasset.getSnnmyezjchj());
			            else if ("二、以前年度盈余调整（ 减少以“-”号填列）".equals(column[j]))
			              createCell.setCellValue(netasset.getYqndyytzzjchj());
			            else if ("三、本年年初余额".equals(column[j]))
			              createCell.setCellValue(netasset.getBnncyezjchj());
			            else if ("四、本年变动金额（减少以“-”号填列）".equals(column[j]))
			              createCell.setCellValue(netasset.getBnbdjezjchj());
			            else if ("（一）本年盈余".equals(column[j]))
				              createCell.setCellValue(netasset.getBnyyzjchj());
			            else if ("（二）无偿调拨净资产".equals(column[j]))
				              createCell.setCellValue(netasset.getWctbjzczjchj());
			            else if ("（三）归集调整预算结转结余".equals(column[j]))
				              createCell.setCellValue(netasset.getGjtzysjzjyzjchj());
			            else if ("（四）提取或设置专用基金".equals(column[j]))
				              createCell.setCellValue(netasset.getTqhszzyjjzjchj());
			            else if ("其中：从预算收入中提取".equals(column[j]))
				              createCell.setCellValue(netasset.getCyssrztqzjchj());
			            else if ("从预算结余中提取".equals(column[j]))
				              createCell.setCellValue(netasset.getCysjyztqzjchj());
			            else if ("设置的专用基金".equals(column[j]))
				              createCell.setCellValue(netasset.getSzdzyjjzjchj());
			            else if ("（五）使用专用基金".equals(column[j]))
				              createCell.setCellValue(netasset.getSyzyjjzjchj());
			            else if ("（六）权益法调整".equals(column[j]))
				              createCell.setCellValue(netasset.getQyftzzjchj());
			            else if ("五、本年年末余额".equals(column[j]))
				              createCell.setCellValue(netasset.getBnnmyezjchj());
	          } else if (m == 5) {
		            if ("一、上年年末余额".equals(column[j]))
		              createCell.setCellValue(netasset.getSnnmyesnljyy());
		            else if ("二、以前年度盈余调整（ 减少以“-”号填列）".equals(column[j]))
		              createCell.setCellValue(netasset.getYqndyytzsnljyy());
		            else if ("三、本年年初余额".equals(column[j]))
		              createCell.setCellValue(netasset.getBnncye());
		            else if ("四、本年变动金额（减少以“-”号填列）".equals(column[j]))
		              createCell.setCellValue(netasset.getBnbdjesnljyy());
		            else if ("（一）本年盈余".equals(column[j]))
			              createCell.setCellValue(netasset.getBnyysnljyy());
		            else if ("（二）无偿调拨净资产".equals(column[j]))
			              createCell.setCellValue(netasset.getWctbjzcsnljyy());
		            else if ("（三）归集调整预算结转结余".equals(column[j]))
			              createCell.setCellValue("");
		            else if ("（四）提取或设置专用基金".equals(column[j]))
			              createCell.setCellValue("");
		            else if ("其中：从预算收入中提取".equals(column[j]))
			              createCell.setCellValue(netasset.getCyssrztqsnljyy());
		            else if ("从预算结余中提取".equals(column[j]))
			              createCell.setCellValue(netasset.getCysjyztqsnljyy());
		            else if ("设置的专用基金".equals(column[j]))
			              createCell.setCellValue(netasset.getSzdzyjjsnljyy());
		            else if ("（五）使用专用基金".equals(column[j]))
			              createCell.setCellValue(netasset.getSyzyjjsnljyy());
		            else if ("（六）权益法调整".equals(column[j]))
			              createCell.setCellValue(netasset.getQyftzsnljyy());
		            else if ("五、本年年末余额".equals(column[j]))
			              createCell.setCellValue(netasset.getBnnmyesnljyy());
		        } else if (m == 6) {
		            if ("一、上年年末余额".equals(column[j]))
		              createCell.setCellValue(netasset.getSnnmyesnzyjj());
		            else if ("二、以前年度盈余调整（ 减少以“-”号填列）".equals(column[j]))
		              createCell.setCellValue(netasset.getYqndyytzsnzyjj());
		            else if ("三、本年年初余额".equals(column[j]))
		              createCell.setCellValue(netasset.getBnncyesnzyjj());
		            else if ("四、本年变动金额（减少以“-”号填列）".equals(column[j]))
		              createCell.setCellValue(netasset.getBnbdjesnzyjj());
		            else if ("（一）本年盈余".equals(column[j]))
			              createCell.setCellValue(netasset.getBnyysnzyjj());
		            else if ("（二）无偿调拨净资产".equals(column[j]))
			              createCell.setCellValue(netasset.getWctbjzcsnzyjj());
		            else if ("（三）归集调整预算结转结余".equals(column[j]))
			              createCell.setCellValue(netasset.getGjtzysjzjysnzyjj());
		            else if ("（四）提取或设置专用基金".equals(column[j]))
			              createCell.setCellValue(netasset.getTqhszzyjjsnzyjj());
		            else if ("其中：从预算收入中提取".equals(column[j]))
			              createCell.setCellValue(netasset.getCyssrztqsnzyjj());
		            else if ("从预算结余中提取".equals(column[j]))
			              createCell.setCellValue(netasset.getCysjyztqsnzyjj());
		            else if ("设置的专用基金".equals(column[j]))
			              createCell.setCellValue(netasset.getSzdzyjjsnzyjj());
		            else if ("（五）使用专用基金".equals(column[j]))
			              createCell.setCellValue(netasset.getSyzyjjsnzyjj());
		            else if ("（六）权益法调整".equals(column[j]))
			              createCell.setCellValue(netasset.getQyftzsnzyjj());
		            else if ("五、本年年末余额".equals(column[j]))
			              createCell.setCellValue(netasset.getBnnmyesnzyjj());
		          }else if (m == 7) {
			            if ("一、上年年末余额".equals(column[j]))
				              createCell.setCellValue(netasset.getSnnmyesnqyftz());
				            else if ("二、以前年度盈余调整（ 减少以“-”号填列）".equals(column[j]))
				              createCell.setCellValue(netasset.getYqndyytzsnqyftz());
				            else if ("三、本年年初余额".equals(column[j]))
				              createCell.setCellValue(netasset.getBnncyesnqyftz());
				            else if ("四、本年变动金额（减少以“-”号填列）".equals(column[j]))
				              createCell.setCellValue(netasset.getBnbdjesnqyftz());
				            else if ("（一）本年盈余".equals(column[j]))
					              createCell.setCellValue(netasset.getBnyysnqyftz());
				            else if ("（二）无偿调拨净资产".equals(column[j]))
					              createCell.setCellValue(netasset.getWctbjzcsnqyftz());
				            else if ("（三）归集调整预算结转结余".equals(column[j]))
					              createCell.setCellValue(netasset.getGjtzysjzjysnqyftz());
				            else if ("（四）提取或设置专用基金".equals(column[j]))
					              createCell.setCellValue(netasset.getTqhszzyjjsnqyftz());
				            else if ("其中：从预算收入中提取".equals(column[j]))
					              createCell.setCellValue(netasset.getCyssrztqsnqyftz());
				            else if ("从预算结余中提取".equals(column[j]))
					              createCell.setCellValue(netasset.getCysjyztqsnqyftz());
				            else if ("设置的专用基金".equals(column[j]))
					              createCell.setCellValue(netasset.getSzdzyjjsnqyftz());
				            else if ("（五）使用专用基金".equals(column[j]))
					              createCell.setCellValue(netasset.getSyzyjjsnqyftz());
				            else if ("（六）权益法调整".equals(column[j]))
					              createCell.setCellValue(netasset.getQyftzsnqyftz());
				            else if ("五、本年年末余额".equals(column[j]))
					              createCell.setCellValue(netasset.getBnnmyesnqyftz());
		          }*/
	          }
	      }
	   wb.write(os);
	      os.close();
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
}


	@SuppressWarnings("unchecked")
	public List<Budgetary> getBudgetList() {
		return budgetList;
	}



	public void setBudgetList(List<Budgetary> budgetList) {
		this.budgetList = budgetList;
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

	public List getAvslist() {
		return avslist;
	}

	public void setAvslist(List avslist) {
		this.avslist = avslist;
	}

	public Budgetary getBudgetary() {
		return budgetary;
	}

	public void setBudgetary(Budgetary budgetary) {
		this.budgetary = budgetary;
	}

	public BudgetVoucher getBudgetVoucher() {
		return budgetVoucher;
	}

	public void setBudgetVoucher(BudgetVoucher budgetVoucher) {
		this.budgetVoucher = budgetVoucher;
	}

	public AccountingVoucher getAccountingVoucher() {
		return accountingVoucher;
	}

	public void setAccountingVoucher(AccountingVoucher accountingVoucher) {
		this.accountingVoucher = accountingVoucher;
	}

	public List getKmList() {
		return kmList;
	}

	public void setKmList(List kmList) {
		this.kmList = kmList;
	}

	public List<AccountingVoucher> getAcco() {
		return acco;
	}

	public void setAcco(List<AccountingVoucher> acco) {
		this.acco = acco;
	}

	public List<BudgetVoucher> getBudgetVouc() {
		return BudgetVouc;
	}

	public void setBudgetVouc(List<BudgetVoucher> budgetVouc) {
		BudgetVouc = budgetVouc;
	}

	public HttpServletRequest getReq() {
		return req;
	}

	public void setReq(HttpServletRequest req) {
		this.req = req;
	}

	public static Static getStaticfc() {
		return staticfc;
	}

	public static void setStaticfc(Static staticfc) {
		BudgetAction.staticfc = staticfc;
	}
	
}
