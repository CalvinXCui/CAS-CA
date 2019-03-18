package com.action;

import com.entity.*;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.service.BaseService;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProfitActoin extends ActionSupport  implements RequestAware{
	/**
	 * 
	 */
	private static final long serialVersionUID = 333325022519358595L;
	private Budgetary budgetary;
	private  Map<Object, Object> jsonmap;
	@SuppressWarnings("rawtypes")
	private List avslist;
	private BaseService baseService;
	private AccountingVoucher accountingVoucher; 
	private List<AccountingVoucher> acco; 
	private List<BudgetVoucher> BudgetVouc; 
	private BudgetVoucher budgetVoucher;
	private Map<String, Object> request;
	private AccountingSubject accountingSubject;
	private AccountTitle accountTitle; 
	private List<AccountTitle> lists;
	private static Difference  difference = new  Difference();
	/**
	 * 本年盈余 预算盈余列表
	 * @throws Exception 
	 * @return17777842333
	 */
	public String profit() throws Exception{
		List<AccountingVoucher> BudgetV = new ArrayList<>();
		List<AccountingVoucher> list=new ArrayList<>();
		List<AccountTitle> listss=new ArrayList<>();
		List<AccountingVoucher> listd = new ArrayList<>();
		Map<String,List<AccountingVoucher>> map=new  HashMap<>(); 
		List<AccountingVoucher> listds = new ArrayList<>();
		List<AccountingVoucher> listdd = new ArrayList<>();
		String s1 = "0.00";//应收帐款//应收票据//其他应收款//应收股利
		String s2 = "0.00";
		String s3 = "0.00";
		String s4 = "0.00";
		/**
		 * 
		 * 应收帐款
		 */
		String getpId2  ="";
		String totalCountacz ="0.00";
		String totalCountacz1 ="0.00";
		String vNo = "";
		String sub="";
		String vsna ="";
		list = baseService.findObjList("from AccountingVoucher  where VDebitAmount !=0");
		listd = baseService.findObjList("from AccountingVoucher  where VCreditAmount !=0");
		/**
		 * 
		 * 应收账款+应收票据+其他应收款+应收股利”借方合计（借方本科目，贷方收入类科
		 */
		String a = (String) ActionContext.getContext().getSession().get("nnn");//本年预算结余
		String b = (String) ActionContext.getContext().getSession().get("mmm");//本年盈余
		difference.setBnysjy(a);
		difference.setBnyy(b);
		BudgetV = baseService.findObjList("FROM AccountingVoucher  where SName like '应收账款%' and VDebitAmount !=0 or  SName like '应收票据%' and VDebitAmount !=0 or SName like '其他应收款%' and VDebitAmount !=0 or SName like '应收股利%' and VDebitAmount !=0"); 
		for(AccountingVoucher accv: BudgetV){//所有满足借方的凭证编号 放入map
			map.put(accv.getVNo(),null);
		}
		if(BudgetV.size() >0){
		for (AccountingVoucher av : listd) {
			if(!map.containsKey(av.getVNo())){//判断借方的凭证编号 和贷方的数据有没有相同的有的话 存到 listds
				continue;
			}else{
				listds.add(av);//贷方
				map.put(av.getVNo(),listds);
			}
	}
		}
		for (AccountingVoucher SNO : listds) {//根据科目名称 去判断是不是属于“收入类”
			if(SNO.getSNo().startsWith("4")){
				for (AccountingVoucher accountingVoucher : BudgetV) {
					String sNo2 = SNO.getVNo();
					if(accountingVoucher.getVNo().equals(sNo2)){//属于收入类的借方存放到 “listdd”
						listdd.add(accountingVoucher);
					}
				}
			}
		}
		for (AccountingVoucher accountingVoucher : listdd) {
			String fub ="0.0";
			String vDebitAmount = accountingVoucher.getVDebitAmount();
			 if(vDebitAmount.equals(fub)){
				 vDebitAmount = "0.0";
			 }
			 BigDecimal num1 = new BigDecimal(vDebitAmount);
			 BigDecimal num2 = new BigDecimal(fub);
			 BigDecimal result = num1.add(num2);
			 String ouft = result.toString();
			 String hhhh = ouft; 
			 Double hhhhf = Double.parseDouble(hhhh); 
			 DecimalFormat hhhh1 = new DecimalFormat("0.00"); 
			 //String s = hhhh1.format(hhhhf);
			 BigDecimal num3 = new BigDecimal(hhhh);
			 BigDecimal num4 = new BigDecimal(s1);
			 BigDecimal result1 = num3.add(num4);
			 String ouft1 = result1.toString();
			 s1 = ouft1; 
			 Double hhhhf1 = Double.parseDouble(s1); 
			 DecimalFormat hhhh111 = new DecimalFormat("0.00"); 
			 s1 = hhhh111.format(hhhhf1);
		}
	
		
		/**
		  “应收账款+应收票据+其他应收款+应收股利”贷方合计（贷方本科目，借方资金类科目）
		 */								//VCreditAmount	贷		
		String s11 = "0.00";//应收帐款//应收票据//其他应收款//应收股利
		String s22 = "0.00";//预算借方合计
		String s33 = "0.00";
		String s44 = "0.00";
		String s55 = "0.00";
		String s66 = "0.00";
		String s77 = "0.00";
		String s88 = "0.00";
		String s99 = "0.00";
		String s100 = "0.00";
		String s500 = "0.00";
		/**
		 * 
		 * 应收账款+应收票据+其他应收款+应收股利”贷方合计（贷方本科目，借方资金类科目）
		 */
		map.clear();
		listds.clear();
		listdd.clear();
		BudgetV = baseService.findObjList("FROM AccountingVoucher  where SName like '应收账款%' and VCreditAmount !=0 or  SName like '应收票据%' and VCreditAmount !=0 or SName like '其他应收款%' and VCreditAmount !=0 or SName like '应收股利%' and VCreditAmount !=0"); 
		for(AccountingVoucher accv: BudgetV){//查找满足贷方的凭证编号 存到“map”
			map.put(accv.getVNo(),null);
		}
		if(BudgetV.size() >0){
		for (AccountingVoucher av : list) {//根据贷方凭证编号 去查找借方是否有数据 有的话存放到“listds”
			if(!map.containsKey(av.getVNo())){
				continue;
			}else{
				listds.add(av);
				map.put(av.getVNo(),listds);//借方
			}
	}
		}
		for (AccountingVoucher SNO : listds) {//根据科目名称 借方判断是否满足“”资金类科目“” 存放到“listdd”
			if(SNO.getSName().startsWith("库存现金") || SNO.getSName().startsWith("银行存款") || SNO.getSName().startsWith("零余额账户用款额度") || SNO.getSName().startsWith("其他货币资金") ){
				for (AccountingVoucher accountingVoucher : BudgetV) {
					String sNo2 = SNO.getVNo();
					if(accountingVoucher.getVNo().equals(sNo2)){//属于收入类的借方存放到 “listdd”
						listdd.add(accountingVoucher);
					}
				}
			}
		}
		for (AccountingVoucher accountingVoucher : listdd) {
			String fub ="0.0";
			String vDebitAmount = accountingVoucher.getVCreditAmount();
			 if(vDebitAmount.equals(fub)){
				 vDebitAmount = "0.0";
			 }
			 BigDecimal numA = new BigDecimal(vDebitAmount);
			 BigDecimal numB = new BigDecimal(fub);
			 BigDecimal resultA = numA.add(numB);
			 String ouftA = resultA.toString();
			 BigDecimal numC = new BigDecimal(ouftA);
			 BigDecimal numD = new BigDecimal(s11);
			 BigDecimal result1 = numC.add(numD);
			 String ouft1 = result1.toString();
			 s11 = ouft1; 
			 Double hhhhfa = Double.parseDouble(s11); 
			 DecimalFormat hhhhcc = new DecimalFormat("0.00"); 
			 s11 = hhhhcc.format(hhhhfa);
		}
		
		//**（2）接受非货币性资产捐赠确认的收入
		map.clear();
		listds.clear();
		listdd.clear();
		BudgetV = baseService.findObjList("FROM AccountingVoucher  where SName like '捐赠收入%' and VCreditAmount !=0"); 
		for(AccountingVoucher accv: BudgetV){//查找满足贷方的凭证编号 存到“map”
			map.put(accv.getVNo(),null);
		}
		if(BudgetV.size() >0){
		for (AccountingVoucher av : list) {//根据贷方凭证编号 去查找借方是否有数据 有的话存放到“listds”
			if(!map.containsKey(av.getVNo())){
				continue;
			}else{
				listds.add(av);
				map.put(av.getVNo(),listds);//借方
			}
	}
		}
		for (AccountingVoucher SNO : listds) {//根据科目名称 借方判断是否满足“”资金类科目“” 存放到“listdd”
			if(SNO.getSName().startsWith("库存现金") || SNO.getSName().startsWith("银行存款") || SNO.getSName().startsWith("零余额账户用款额度") || SNO.getSName().startsWith("其他货币资金") ){
				for (AccountingVoucher accountingVoucher : BudgetV) {
					String sNo2 = SNO.getVNo();
					if(accountingVoucher.getVNo().equals(sNo2)){//属于收入类的借方存放到 “listdd”
						listdd.add(accountingVoucher);
					}
				}
			}
		}
		for (AccountingVoucher accountingVoucher : listdd) {
			String fub ="0.0";
			String vDebitAmount = accountingVoucher.getVCreditAmount();
			 if(vDebitAmount.equals(fub)){
				 vDebitAmount = "0.0";
			 }
			 BigDecimal numA = new BigDecimal(vDebitAmount);
			 BigDecimal numB = new BigDecimal(fub);
			 BigDecimal resultA = numA.add(numB);
			 String ouftA = resultA.toString();
			 BigDecimal numC = new BigDecimal(ouftA);
			 BigDecimal numD = new BigDecimal(s500);
			 BigDecimal result1 = numC.add(numD);
			 String ouft1 = result1.toString();
			 s500 = ouft1; 
			 Double hhhhfa = Double.parseDouble(s500); 
			 DecimalFormat hhhhcc = new DecimalFormat("0.00"); 
			 s500 = hhhhcc.format(hhhhfa);
		}
		request.put("s500", s500);
		difference.setJsfhbxzcjzdqrdsr(s500);
		
		/**
		 * “应收账款+应收票据+其他应收款+应收股利”借方合计（借方本科目，贷方收入类科目）减“应收账款+应收票据+其他应收款+应收股利”贷方合计（贷方本科目，借方资金类科目）
		 */
		 BigDecimal num1 = new BigDecimal(s1);
		 BigDecimal num2 = new BigDecimal(s11);
		 BigDecimal result2 = num1.subtract(num2);
		 String ouft = result2.toString();
		 String hhhh = ouft; 
		 Double hhhhf1 = Double.parseDouble(hhhh); 
		 DecimalFormat hhhh111 = new DecimalFormat("0.00"); 
		 String pl = hhhh111.format(hhhhf1);
		 String trim = pl.replace("-", "").trim();
		 request.put("subf", trim);
		 difference.setYszkqrdsr(trim);
		 /**
		  * 预付账款”借方合计
		  */
		
		 BudgetV = baseService.findObjList("FROM AccountingVoucher  where SName like '预付账款%' and VDebitAmount !=0"); 
		 for (AccountingVoucher accountingVoucher : BudgetV) {
				String fub ="0.0";
				String vDebitAmount = accountingVoucher.getVDebitAmount();
				 if(vDebitAmount.equals(fub)){
					 vDebitAmount = "0.0";
				 }
				 BigDecimal numA = new BigDecimal(vDebitAmount);
				 BigDecimal numB = new BigDecimal(fub);
				 BigDecimal resultA = numA.add(numB);
				 String ouftA = resultA.toString();
				 BigDecimal numC = new BigDecimal(ouftA);
				 BigDecimal numD = new BigDecimal(s22);
				 BigDecimal result1 = numC.add(numD);
				 String ouft1 = result1.toString();
				 s22 = ouft1; 
				 Double hhhhfa = Double.parseDouble(s22); 
				 DecimalFormat hhhhcc = new DecimalFormat("0.00"); 
				 s22 = hhhhcc.format(hhhhfa);
		 }
		 
		 /**
		  * “预付账款”借方合计减：“预付账款”贷合计（借方“业务活动费”科目+贷方本科目）
		  */
		 map.clear();
		 listds.clear();
		 listdd.clear();
		 BudgetV = baseService.findObjList("FROM AccountingVoucher  where SName like '预付账款%' and VCreditAmount !=0"); 
		 for(AccountingVoucher accv: BudgetV){//查找满足贷方的凭证编号 存到“map”
				map.put(accv.getVNo(),null);
			}
		 if(BudgetV.size() >0){
		 for (AccountingVoucher av : list) {//根据贷方凭证编号 去查找借方是否有数据 有的话存放到“listds”
				if(!map.containsKey(av.getVNo())){
					continue;
				}else{
					listds.add(av);
					map.put(av.getVNo(),listds);//借方
				}
		}
		 }
			for (AccountingVoucher SNO : listds) {//根据科目名称 借方判断是否满足“”业务活动费“” 存放到“listdd”
				if(SNO.getSName().startsWith("业务活动费")){
					for (AccountingVoucher accountingVoucher : BudgetV) {
						String sNo2 = SNO.getVNo();
						if(accountingVoucher.getVNo().equals(sNo2)){//属于收入类的借方存放到 “listdd”
							listdd.add(accountingVoucher);
						}
					}
				}
			}
			for (AccountingVoucher accountingVoucher : listdd) {
				String fub ="0.0";
				String vDebitAmount = accountingVoucher.getVCreditAmount();
				 if(vDebitAmount.equals(fub)){
					 vDebitAmount = "0.0";
				 }
				 BigDecimal numA = new BigDecimal(vDebitAmount);
				 BigDecimal numB = new BigDecimal(fub); 
				 BigDecimal resultA = numA.add(numB);
				 String ouftA = resultA.toString();
				 BigDecimal numC = new BigDecimal(ouftA);
				 BigDecimal numD = new BigDecimal(s33);
				 BigDecimal result1 = numC.add(numD);
				 String ouft1 = result1.toString();
				 s33 = ouft1; 
				 Double hhhhfa = Double.parseDouble(s33); 
				 DecimalFormat hhhhcc = new DecimalFormat("0.00"); 
				 s33 = hhhhcc.format(hhhhfa);
			}
			
			 BigDecimal numCc = new BigDecimal(s22);
			 BigDecimal numDd = new BigDecimal(s33);
			 BigDecimal result11 = numCc.subtract(numDd);
			 String ouft11 = result11.toString();
//			 ouft11.split("-");
			 String trim1 = ouft11.replace("-", "").trim();
			 s44 = trim1; 
			 
			 
			 /**
			  * 应付账款+应付票据”贷方合计-“应付账款+应付票据”借方合计（贷方为资金类科目科目）
			  */
			 map.clear();
			 listds.clear();
			 listdd.clear();
			 BudgetV = baseService.findObjList("FROM AccountingVoucher  where SName like '应付账款%' and VDebitAmount !=0 or SName like '应付票据%' and VDebitAmount !=0 ");
			 for (AccountingVoucher accv : BudgetV) {//查找满足借方的凭证编号 存到“map”
				 map.put(accv.getVNo(),null);
			 }
			 if(BudgetV.size() >0){
			 for (AccountingVoucher av : listd) {//根据贷方凭证编号 去查找借方是否有数据 有的话存放到“listds”
					if(!map.containsKey(av.getVNo())){
						continue;
					}else{
						listds.add(av);
						map.put(av.getVNo(),listds);//借方
					}
			}
			 }
			 for (AccountingVoucher SNO : listds) {//根据科目名称 去判断贷方是不是属于“资金类科目科目）”
					if(SNO.getSName().startsWith("库存现金") || SNO.getSName().startsWith("银行存款") || SNO.getSName().startsWith("零余额账户用款额度") || SNO.getSName().startsWith("其他货币资金") ){
						for (AccountingVoucher accountingVoucher : BudgetV) {
							String sNo2 = SNO.getVNo();
							if(accountingVoucher.getVNo().equals(sNo2)){//属于收入类的借方存放到 “listdd”
								listdd.add(accountingVoucher);
							}
						}
					}
				}
			 for (AccountingVoucher accountingVoucher : listdd) {
					String fub ="0.0";
					String vDebitAmount = accountingVoucher.getVDebitAmount();
					 if(vDebitAmount.equals(fub)){
						 vDebitAmount = "0.0";
					 }
					 BigDecimal numA = new BigDecimal(vDebitAmount);
					 BigDecimal numB = new BigDecimal(fub); 
					 BigDecimal resultA = numA.add(numB);
					 String ouftA = resultA.toString();
					 BigDecimal numC = new BigDecimal(ouftA);
					 BigDecimal numD = new BigDecimal(s55);
					 BigDecimal result1 = numC.add(numD);
					 String ouft1 = result1.toString();
					 s55 = ouft1; 
					 Double hhhhfa = Double.parseDouble(s55); 
					 DecimalFormat hhhhcc = new DecimalFormat("0.00"); 
					 s55 = hhhhcc.format(hhhhfa);
			 }
			 
			 /**
			  * “预付账款”借方合计减：“预付账款”贷合计（借方“业务活动费”科目+贷方本科目）
					加：“应付账款+应付票据”贷方合计-“应付账款+应付票据”借方合计（贷方为资金类科目科目）
			  */
			 BigDecimal numCcC = new BigDecimal(s44);
			 BigDecimal numDdD = new BigDecimal(s55);
			 BigDecimal result112 = numCcC.subtract(numDdD);
			 String ouft112 = result112.toString();
//			 ouft11.split("-");
			 String trim12 = ouft112.replace("-", "").trim();
			 s66 = trim12; 
			 request.put("zf", s66);
			 difference.setZfyfkx(s66);
		  
			 
			 /**
			  * “在途物品”+“库存物品”+“加工物品”+“政府储备物资”+“工程物资”---借方合计  借方本科目+贷方资金科目
			  */
			 map.clear();
			 listds.clear();
			 listdd.clear();
			 BudgetV = baseService.findObjList("FROM AccountingVoucher  where SName like '在途物品%' and VDebitAmount !=0 or  SName like '库存物品%' and VDebitAmount !=0 or SName like '加工物品%' and VDebitAmount !=0 or SName like '政府储备物品%' and VDebitAmount !=0 or SName like '工程物资%' and VDebitAmount !=0"); 
				for(AccountingVoucher accv: BudgetV){//所有满足借方的凭证编号 放入map
					map.put(accv.getVNo(),null);
				}
				if(BudgetV.size() >0){
				for (AccountingVoucher av : listd) {
					if(!map.containsKey(av.getVNo())){//判断借方的凭证编号 和贷方的数据有没有相同的有的话 存到 listds
						continue;
					}else{
						listds.add(av);//贷方
						map.put(av.getVNo(),listds);
					}
			}
				}
				for (AccountingVoucher SNO : listds) {//根据科目名称 去判断是不是属于“收入类”
					if(SNO.getSName().startsWith("库存现金") || SNO.getSName().startsWith("银行存款") || SNO.getSName().startsWith("零余额账户用款额度") || SNO.getSName().startsWith("其他货币资金") ){
						for (AccountingVoucher accountingVoucher : BudgetV) {
							String sNo2 = SNO.getVNo();
							if(accountingVoucher.getVNo().equals(sNo2)){//属于收入类的借方存放到 “listdd”
								listdd.add(accountingVoucher);
							}
						}
					}
				}
				 for (AccountingVoucher accountingVoucher : listdd) {
						String fub ="0.0";
						String vDebitAmount = accountingVoucher.getVDebitAmount();
						 if(vDebitAmount.equals(fub)){
							 vDebitAmount = "0.0";
						 }
						 BigDecimal numA = new BigDecimal(vDebitAmount);
						 BigDecimal numB = new BigDecimal(fub); 
						 BigDecimal resultA = numA.add(numB);
						 String ouftA = resultA.toString();
						 BigDecimal numC = new BigDecimal(ouftA);
						 BigDecimal numD = new BigDecimal(s77);
						 BigDecimal result1 = numC.add(numD);
						 String ouft1 = result1.toString();
						 s77 = ouft1; 
						 Double hhhhfa = Double.parseDouble(s77); 
						 DecimalFormat hhhhcc = new DecimalFormat("0.00"); 
						 s77 = hhhhcc.format(hhhhfa);
				 }
				 request.put("ztwp", s77);
				 difference.setWqdch(s77);
				 
				 /**
				  * 固定资产”+“在建工程”+“无形资产”+“研发支出”+“公共基础设施”+“文化文物资产”+“保障性住房”---借方合计 借方本科目+贷方资金科目
				  */
				 map.clear();
				 listds.clear();
				 listdd.clear();
				 BudgetV = baseService.findObjList("FROM AccountingVoucher  where SName like '固定资产%' and VDebitAmount !=0 or  SName like '在建工程%' and VDebitAmount !=0 or SName like '无形资产%' and VDebitAmount !=0 or SName like '研发支出%' and VDebitAmount !=0 or SName like '公共基础设施%' and VDebitAmount !=0 or  SName like '文化文物资产%' and VDebitAmount !=0 or  SName like '保障性住房%' and VDebitAmount !=0"); 
					for(AccountingVoucher accv: BudgetV){//所有满足借方的凭证编号 放入map
						map.put(accv.getVNo(),null);
					}
					if(BudgetV.size() >0){
					for (AccountingVoucher av : listd) {
						if(!map.containsKey(av.getVNo())){//判断借方的凭证编号 和贷方的数据有没有相同的有的话 存到 listds
							continue;
						}else{
							listds.add(av);//贷方
							map.put(av.getVNo(),listds);
						}
				}
					}
					for (AccountingVoucher SNO : listds) {//根据科目名称 去判断是不是属于“收入类”
						if(SNO.getSName().startsWith("库存现金") || SNO.getSName().startsWith("银行存款") || SNO.getSName().startsWith("零余额账户用款额度") || SNO.getSName().startsWith("其他货币资金") ){
							for (AccountingVoucher accountingVoucher : BudgetV) {
								String sNo2 = SNO.getVNo();
								if(accountingVoucher.getVNo().equals(sNo2)){//属于收入类的借方存放到 “listdd”
									listdd.add(accountingVoucher);
								}
							}
						}
					}
					String trim11 ="0.00";
					 for (AccountingVoucher accountingVoucher : listdd) {
							String fub ="0.0";
							String vDebitAmount = accountingVoucher.getVDebitAmount();
							 if(vDebitAmount.equals(fub)){
								 vDebitAmount = "0.0";
							 }
							 BigDecimal numA = new BigDecimal(vDebitAmount);
							 BigDecimal numB = new BigDecimal(fub); 
							 BigDecimal resultA = numA.add(numB);
							 String ouftA = resultA.toString();
							 BigDecimal numC = new BigDecimal(ouftA);
							 BigDecimal numD = new BigDecimal(s88);
							 BigDecimal result1 = numC.add(numD);
							 String ouft1 = result1.toString();
							 s88 = ouft1; 
							 Double hhhhfa = Double.parseDouble(s88); 
							 DecimalFormat hhhhcc = new DecimalFormat("0.00"); 
							 s88 = hhhhcc.format(hhhhfa);
							trim11 = s88.replace("-", "").trim();
					 }
					 request.put("gdzc", trim11);
					 difference.setWgjgdzcddzbxzc(trim11);
					 /**
					  * “短期借款”+“长期借款”+“应付利息”借方合计-本科目在贷方，同时借方为“在建工程”时的科目金额
					  */
					 map.clear();
					 listds.clear();
					 listdd.clear();
					 BudgetV = baseService.findObjList("FROM AccountingVoucher  where SName like '短期借款%' and VDebitAmount !=0 or  SName like '长期借款%' and VDebitAmount !=0 or SName like '应付利息%'"); 
						for(AccountingVoucher accv: BudgetV){//所有满足借方的凭证编号 放入map
							map.put(accv.getVNo(),null);
						}
						if(BudgetV.size() >0){
						for (AccountingVoucher av : listd) {
							if(!map.containsKey(av.getVNo())){//判断借方的凭证编号 和贷方的数据有没有相同的有的话 存到 listds
								continue;
							}else{
								listds.add(av);//贷方
								map.put(av.getVNo(),listds);
							}
					}
						}
						for (AccountingVoucher SNO : listds) {//根据科目名称 去判断是不是属于“收入类”
							if(SNO.getSName().startsWith("在建工程")){
								for (AccountingVoucher accountingVoucher : BudgetV) {
									String sNo2 = SNO.getVNo();
									if(accountingVoucher.getVNo().equals(sNo2)){//属于收入类的借方存放到 “listdd”
										listdd.add(accountingVoucher);
									}
								}
							}
						}
						String trim111 ="0.00";
						 for (AccountingVoucher accountingVoucher : listdd) {
								String fub ="0.0";
								String vDebitAmount = accountingVoucher.getVDebitAmount();
								 if(vDebitAmount.equals(fub)){
									 vDebitAmount = "0.0";
								 }
								 BigDecimal numA = new BigDecimal(vDebitAmount);
								 BigDecimal numB = new BigDecimal(fub); 
								 BigDecimal resultA = numA.add(numB);
								 String ouftA = resultA.toString();
								 BigDecimal numC = new BigDecimal(ouftA);
								 BigDecimal numD = new BigDecimal(s99);
								 BigDecimal result1 = numC.add(numD);
								 String ouft1 = result1.toString();
								 s99 = ouft1; 
								 Double hhhhfa = Double.parseDouble(s99); 
								 DecimalFormat hhhhcc = new DecimalFormat("0.00"); 
								 s99 = hhhhcc.format(hhhhfa);
								trim111 = s99.replace("-", "").trim();
						 }
						 request.put("dqjk", trim111);
						 difference.setChjkbxzc(trim111);
						 /**
						  * “预收账款”贷方合计减：“预收账款”借合计（借方本科目+贷方资金类科目）
						  */
						 map.clear();
						 listds.clear();
						 listdd.clear();
						 BudgetV = baseService.findObjList("FROM AccountingVoucher  where SName like '预付账款%' and VCreditAmount !=0"); 
						 for(AccountingVoucher accv: BudgetV){//查找满足贷方的凭证编号 存到“map”
								map.put(accv.getVNo(),null);
							}
						 if(BudgetV.size() >0){
						 for (AccountingVoucher av : list) {//根据贷方凭证编号 去查找借方是否有数据 有的话存放到“listds”
								if(!map.containsKey(av.getVNo())){
									continue;
								}else{
									listds.add(av);
									map.put(av.getVNo(),listds);//借方
								}
						}
						 }
							for (AccountingVoucher SNO : listds) {//根据科目名称 贷方方判断是否满足“”资金“” 存放到“listdd”
								if(SNO.getSName().startsWith("库存现金") || SNO.getSName().startsWith("银行存款") || SNO.getSName().startsWith("零余额账户用款额度") || SNO.getSName().startsWith("其他货币资金") ){
									for (AccountingVoucher accountingVoucher : BudgetV) {
										String sNo2 = SNO.getVNo();
										if(accountingVoucher.getVNo().equals(sNo2)){//属于收入类的借方存放到 “listdd”
											listdd.add(accountingVoucher);
										}
									}
								}
							}
							for (AccountingVoucher accountingVoucher : listdd) {
								String fub ="0.0";
								String vDebitAmount = accountingVoucher.getVCreditAmount();
								 if(vDebitAmount.equals(fub)){
									 vDebitAmount = "0.0";
								 }
								 BigDecimal numA = new BigDecimal(vDebitAmount);
								 BigDecimal numB = new BigDecimal(fub); 
								 BigDecimal resultA = numA.add(numB);
								 String ouftA = resultA.toString();
								 BigDecimal numC = new BigDecimal(ouftA);
								 BigDecimal numD = new BigDecimal(s100);
								 BigDecimal result1 = numC.add(numD);
								 String ouft1 = result1.toString();
								 s100 = ouft1; 
								 Double hhhhfa = Double.parseDouble(s100); 
								 DecimalFormat hhhhcc = new DecimalFormat("0.00"); 
								 s100 = hhhhcc.format(hhhhfa);
							 }
							String s101 = "0.00";
							 BudgetV = baseService.findObjList("FROM AccountingVoucher  where SName like '预付账款%' and VDebitAmount !=0");
							 for (AccountingVoucher accountingVoucher : BudgetV) {
									String fub ="0.0";
									String vDebitAmount = accountingVoucher.getVDebitAmount();
									 if(vDebitAmount.equals(fub)){
										 vDebitAmount = "0.0";
									 }
									 BigDecimal numA = new BigDecimal(vDebitAmount);
									 BigDecimal numB = new BigDecimal(fub); 
									 BigDecimal resultA = numA.add(numB);
									 String ouftA = resultA.toString();
									 BigDecimal numC = new BigDecimal(ouftA);
									 BigDecimal numD = new BigDecimal(s101);
									 BigDecimal result1 = numC.add(numD);
									 String ouft1 = result1.toString();
									 s101 = ouft1; 
									 Double hhhhfa = Double.parseDouble(s101); 
									 DecimalFormat hhhhcc = new DecimalFormat("0.00"); 
									 s101 = hhhhcc.format(hhhhfa);
								 }
							 String trim1111 ="0.00";
							 BigDecimal numA = new BigDecimal(s100);
							 BigDecimal numB = new BigDecimal(s101);
							 BigDecimal resultA = numA.subtract(numB);
							 if(s100.equals("0.00")){
								 String ouftA = resultA.toString();
								 String s102 = ouftA;  
								 trim1111 = s102.replace("-", "").trim();
							 }else {
								 String ouftA = resultA.toString();
								 String s102 = ouftA;
								 trim1111 = s102;
							}
							 request.put("kkkkk", trim1111);
							 difference.setSdyfknx(trim1111);
							 	/**
							 	 * “短期借款”+“长期借款-本金”---贷方合计
							 	 */
							 String s102 = "0.00";
							 BudgetV = baseService.findObjList("FROM AccountingVoucher  where SName like '短期借款%' and VCreditAmount !=0 or SName like '长期借款-本金%' and VCreditAmount !=0");
							 for (AccountingVoucher accountingVoucher : BudgetV) {
									String fub ="0.0";
									String vDebitAmount = accountingVoucher.getVCreditAmount();
									 if(vDebitAmount.equals(fub)){
										 vDebitAmount = "0.0";
									 }
									 BigDecimal numP = new BigDecimal(vDebitAmount);
									 BigDecimal numT = new BigDecimal(fub); 
									 BigDecimal resultdA = numP.add(numT);
									 String ouftA = resultdA.toString();
									 BigDecimal numC = new BigDecimal(ouftA);
									 BigDecimal numD = new BigDecimal(s102);
									 BigDecimal result1 = numC.add(numD);
									 String ouft1 = result1.toString();
									 s102 = ouft1; 
									 Double hhhhfa = Double.parseDouble(s102); 
									 DecimalFormat hhhhcc = new DecimalFormat("0.00"); 
									 s102 = hhhhcc.format(hhhhfa);
								 }
							 request.put("dqjkdd", s102);
							 difference.setQdjkqrdyssr(s102);
							 
							 /**
							  * “在途物品”+“库存物品”+“加工物品”+“政府储备物资”+“工程物资”---贷方合计
							  * 借方“业务活动费/单位管理费用/经营费用”+贷方本科目
							  */
							 map.clear();
								listds.clear();
								listdd.clear();
								BudgetV = baseService.findObjList("FROM AccountingVoucher  where SName like '在途物品%' and VCreditAmount !=0 or  SName like '库存物品%' and VCreditAmount !=0 or SName like '加工物品%' and VCreditAmount !=0 or SName like '政府储备物资%' and VCreditAmount !=0 or SName like '工程物资%' and VCreditAmount !=0"); 
								for(AccountingVoucher accv: BudgetV){//查找满足贷方的凭证编号 存到“map”
									map.put(accv.getVNo(),null);
								}
								if(BudgetV.size() >0){
								for (AccountingVoucher av : list) {//根据贷方凭证编号 去查找借方是否有数据 有的话存放到“listds”
									if(!map.containsKey(av.getVNo())){
										continue;
									}else{
										listds.add(av);
										map.put(av.getVNo(),listds);//借方
									}
							}
								}
								for (AccountingVoucher SNO : listds) {//根据科目名称 借方判断是否满足“”借方“业务活动费/单位管理费用/经营费用“” 存放到“listdd”
									if(SNO.getSName().startsWith("业务活动费") || SNO.getSName().startsWith("单位管理费用") || SNO.getSName().startsWith("经营费用")){
										for (AccountingVoucher accountingVoucher : BudgetV) {
											String sNo2 = SNO.getVNo();
											if(accountingVoucher.getVNo().equals(sNo2)){//属于借方“业务活动费/单位管理费用/经营费用的借方存放到 “listdd”
												listdd.add(accountingVoucher);
											}
										}
									}
								}
								String S103 = "0.00";
								for (AccountingVoucher accountingVoucher : listdd) {
									String fub ="0.0";
									String vDebitAmount = accountingVoucher.getVCreditAmount();
									 if(vDebitAmount.equals(fub)){
										 vDebitAmount = "0.0";
									 }
									 BigDecimal numAG = new BigDecimal(vDebitAmount);
									 BigDecimal numBG = new BigDecimal(fub);
									 BigDecimal resultAF = numAG.add(numBG);
									 String ouftA = resultAF.toString();
									 BigDecimal numC = new BigDecimal(ouftA);
									 BigDecimal numD = new BigDecimal(S103);
									 BigDecimal result1 = numC.add(numD);
									 String ouft1 = result1.toString();
									 S103 = ouft1; 
									 Double hhhhfa = Double.parseDouble(S103); 
									 DecimalFormat hhhhcc = new DecimalFormat("0.00"); 
									 S103 = hhhhcc.format(hhhhfa);
								}
								request.put("fcwp", S103);
								difference.setFcch(S103);
								/**
								 * 固定资产累计折旧+无形资产累计摊销+公共基础设施累计折旧（摊销）+保障性住房累计折旧”--贷方合计
								 */
								String s105 = "0.00";
								 BudgetV = baseService.findObjList("FROM AccountingVoucher  where SName like '固定资产累计折旧%' and VCreditAmount !=0 or SName like '无形资产累计摊销%' and VCreditAmount !=0 or SName like '公共基础设施累计折旧%' and VCreditAmount !=0 or SName like '公共基础设施累计摊销%' and VCreditAmount !=0 or SName like '保障性住房累计折旧%' and VCreditAmount !=0");
								 for (AccountingVoucher accountingVoucher : BudgetV) {
										String fub ="0.0";
										String vDebitAmount = accountingVoucher.getVCreditAmount();
										 if(vDebitAmount.equals(fub)){
											 vDebitAmount = "0.0";
										 }
										 BigDecimal numP = new BigDecimal(vDebitAmount);
										 BigDecimal numT = new BigDecimal(fub); 
										 BigDecimal resultdA = numP.add(numT);
										 String ouftA = resultdA.toString();
										 BigDecimal numC = new BigDecimal(ouftA);
										 BigDecimal numD = new BigDecimal(s105);
										 BigDecimal result1 = numC.add(numD);
										 String ouft1 = result1.toString();
										 s105 = ouft1; 
										 Double hhhhfa = Double.parseDouble(s105); 
										 DecimalFormat hhhhcc = new DecimalFormat("0.00"); 
										 s105 = hhhhcc.format(hhhhfa);
									 }
								 request.put("txxz", s105);
								 difference.setJtdzfy(s105);
								 /**
								  * 资产处置费用”--借方合计 借方本科目+贷方非资金
								  */
								 map.clear();
								 listds.clear();
								 listdd.clear();
								 BudgetV = baseService.findObjList("FROM AccountingVoucher  where SName like '资产处置费用%' and VDebitAmount !=0"); 
								 for(AccountingVoucher accv: BudgetV){//查找满足贷方的凭证编号 存到“map”
										map.put(accv.getVNo(),null);
									}
								 if(BudgetV.size() >0){
								 for (AccountingVoucher av : list) {//根据贷方凭证编号 去查找借方是否有数据 有的话存放到“listds”
										if(!map.containsKey(av.getVNo())){
											continue;
										}else{
											listds.add(av);
											map.put(av.getVNo(),listds);//借方
										}
								}
								 }
									for (AccountingVoucher SNO : listds) {//根据科目名称 贷方方判断是否满足“”资金“” 存放到“listdd”
										if(!SNO.getSName().startsWith("库存现金") || !SNO.getSName().startsWith("银行存款") || !SNO.getSName().startsWith("零余额账户用款额度") || !SNO.getSName().startsWith("其他货币资金") ){
											for (AccountingVoucher accountingVoucher : BudgetV) {
												String sNo2 = SNO.getVNo();
												if(accountingVoucher.getVNo().equals(sNo2)){//属于收入类的借方存放到 “listdd”
													listdd.add(accountingVoucher);
												}
											}
										}
									}
									String s106 = "0.00";
									for (AccountingVoucher accountingVoucher : listdd) {
										String fub ="0.0";
										String vDebitAmount = accountingVoucher.getVCreditAmount();
										 if(vDebitAmount.equals(fub)){
											 vDebitAmount = "0.0";
										 }
										 BigDecimal numP = new BigDecimal(vDebitAmount);
										 BigDecimal numT = new BigDecimal(fub); 
										 BigDecimal resultAf = numP.add(numT);
										 String ouftA = resultAf.toString();
										 BigDecimal numC = new BigDecimal(ouftA);
										 BigDecimal numD = new BigDecimal(s106);
										 BigDecimal result1 = numC.add(numD);
										 String ouft1 = result1.toString();
										 s106 = ouft1; 
										 Double hhhhfa = Double.parseDouble(s106); 
										 DecimalFormat hhhhcc = new DecimalFormat("0.00"); 
										 s106 = hhhhcc.format(hhhhfa);
									 }
									request.put("zccz", s106);
									difference.setCzzcjz(s106);
									/**
									 * “应付账款+应付票据+”借方合计减：“预付账款”贷合计（借方资金类科目+贷方本科目）
									 */
									map.clear();
									 listds.clear();
									 listdd.clear();
									 BudgetV = baseService.findObjList("FROM AccountingVoucher  where SName like '应付账款%' and VCreditAmount !=0 or SName like '应付票据%' and VCreditAmount !=0 ");
									 for (AccountingVoucher accv : BudgetV) {//查找满足借方的凭证编号 存到“map”
										 map.put(accv.getVNo(),null);
									 }
									 if(BudgetV.size() >0){
									 for (AccountingVoucher av : list) {//根据贷方凭证编号 去查找借方是否有数据 有的话存放到“listds”
											if(!map.containsKey(av.getVNo())){
												continue;
											}else{
												listds.add(av);
												map.put(av.getVNo(),listds);//借方
											}
									}
									 }
									 for (AccountingVoucher SNO : listds) {//根据科目名称 去判断借方是不是属于“资金类科目科目）”
											if(SNO.getSName().startsWith("库存现金") || SNO.getSName().startsWith("银行存款") || SNO.getSName().startsWith("零余额账户用款额度") || SNO.getSName().startsWith("其他货币资金") ){
												for (AccountingVoucher accountingVoucher : BudgetV) {
													String sNo2 = SNO.getVNo();
													if(accountingVoucher.getVNo().equals(sNo2)){//属于收入类的借方存放到 “listdd”
														listdd.add(accountingVoucher);
													}
												}
											}
										}
									 String s107 = "0.00";
									 for (AccountingVoucher accountingVoucher : listdd) {
											String fub ="0.0";
											String vDebitAmount = accountingVoucher.getVCreditAmount();
											 if(vDebitAmount.equals(fub)){
												 vDebitAmount = "0.0";
											 }
											 BigDecimal numAb = new BigDecimal(vDebitAmount);
											 BigDecimal numBf = new BigDecimal(fub); 
											 BigDecimal resultAff = numAb.add(numBf);
											 String ouftA = resultAff.toString();
											 BigDecimal numC = new BigDecimal(ouftA);
											 BigDecimal numD = new BigDecimal(s107);
											 BigDecimal result1 = numC.add(numD);
											 String ouft1 = result1.toString();
											 s107 = ouft1; 
											 Double hhhhfa = Double.parseDouble(s107); 
											 DecimalFormat hhhhcc = new DecimalFormat("0.00"); 
											 s107 = hhhhcc.format(hhhhfa);
									 }
									request.put("s107", s107);
									difference.setYfkx(s107);
									/**
									 * 短期借款、长期借款 借方资金+贷方本科目
									 */
									 map.clear();
									 listds.clear();
									 listdd.clear();
									 BudgetV = baseService.findObjList("FROM AccountingVoucher  where SName like '短期借款%' and VCreditAmount !=0 or SName like '长期借款%' and VCreditAmount !=0"); 
									 for(AccountingVoucher accv: BudgetV){//查找满足贷方的凭证编号 存到“map”
											map.put(accv.getVNo(),null);
										}
									 if(BudgetV.size() >0){
									 for (AccountingVoucher av : list) {//根据贷方凭证编号 去查找借方是否有数据 有的话存放到“listds”
											if(!map.containsKey(av.getVNo())){
												continue;
											}else{
												listds.add(av);
												map.put(av.getVNo(),listds);//借方
											}
									}
									 }
										for (AccountingVoucher SNO : listds) {//根据科目名称 贷方方判断是否满足“”资金“” 存放到“listdd”
											if(SNO.getSName().startsWith("库存现金") || SNO.getSName().startsWith("银行存款") || SNO.getSName().startsWith("零余额账户用款额度") || SNO.getSName().startsWith("其他货币资金") ){
												for (AccountingVoucher accountingVoucher : BudgetV) {
													String sNo2 = SNO.getVNo();
													if(accountingVoucher.getVNo().equals(sNo2)){//属于收入类的借方存放到 “listdd”
														listdd.add(accountingVoucher);
													}
												}
											}
										}
										String s200 = "0.00";
										for (AccountingVoucher accountingVoucher : listdd) {
											String fub ="0.0";
											String vDebitAmount = accountingVoucher.getVCreditAmount();
											 if(vDebitAmount.equals(fub)){
												 vDebitAmount = "0.0";
											 }
											 BigDecimal numAf = new BigDecimal(vDebitAmount);
											 BigDecimal numBf = new BigDecimal(fub); 
											 BigDecimal resultAff = numAf.add(numBf);
											 String ouftA = resultAff.toString();
											 BigDecimal numC = new BigDecimal(ouftA);
											 BigDecimal numD = new BigDecimal(s200);
											 BigDecimal result1 = numC.add(numD);
											 String ouft1 = result1.toString();
											 s200 = ouft1; 
											 Double hhhhfa = Double.parseDouble(s200); 
											 DecimalFormat hhhhcc = new DecimalFormat("0.00"); 
											 s200 = hhhhcc.format(hhhhfa);
										 }
										request.put("s200", s200);
										difference.setQtsxcy(s200);
										
										
		return "profit";
}
	public void exportCashExcel() throws IOException{
		HttpServletResponse response= ServletActionContext.getResponse();
		 String[] titles ={"项目","金额"};
		 String[] column ={"一、本年预算结余（本年预算收支差异）","二、差异调节","（一）重要事项的差异","加：1.当期确认为收入但没有确认为预算收入","（1）应收款项、预收账款确认的收入","（2）接受非货币性资产捐赠确认的收入","2.当期确认为预算支出但没有确认为费用","（1）支付应付款项、预付账款的支出","（2）为取得存货、政府储备物资等计入物资成本的支出"
				 ,"（3）为构建固定资产等的资本性支出","（4）偿还借款本息支出","减：1.当期确认为预算收入但没有确认为收入","（1）收到应收款你想、预收账款确认的预算收入","（2）取得借款确认的预算收入","2.当期确认为费用但没有确认为预算支出","（1）发出存货、政府储备物资等确认的费用","（2）计提的这就费用和摊销费用","（3）确认的资产处置费用（处置资产价值）","（4）应付款项、预付账款确认的费用","（二）其他事项差异","三、本年盈余（本年收入与费用的差额）"};
		String filename = "本年盈余与预算结余差异说明.xlsx";
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
		      style.setAlignment((short) 2);
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

		      XSSFSheet sheet = wb.createSheet("本年盈余与预算结余差异说明");
		      sheet.setDefaultColumnWidth(55);

		      CellRangeAddress callRangeAddress = new CellRangeAddress(0, 0, 0, 2);
		      sheet.addMergedRegion(callRangeAddress);
		      XSSFRow rowOne = sheet.createRow(0);
		      rowOne.setHeight((short) 800);
		      XSSFCell cel0 = rowOne.createCell(0);
		      cel0.setCellStyle(style);
		      cel0.setCellValue("本年盈余与预算结余差异说明");
		      XSSFRow row = sheet.createRow(1);
		      for (int i = 0; i < titles.length; ++i) {
		        XSSFCell cell = row.createCell(i);
		        cell.setCellStyle(styleCell);

		        cell.setCellValue(titles[i]);
		      }
		      
		     // Difference thisReportData = new Difference();
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
		            if ("一、本年预算结余（本年预算收支差异）".equals(column[j]))
		              createCell.setCellValue(difference.getBnysjy());
		            else if ("二、差异调节".equals(column[j]))
		              createCell.setCellValue(difference.getCytj());
		            else if ("（一）重要事项的差异".equals(column[j]))
		              createCell.setCellValue(difference.getZysxdcy());
		            else if ("加：1.当期确认为收入但没有确认为预算收入".equals(column[j]))
		              createCell.setCellValue(difference.getDqqrwyssrdmyqrwsr());
		            else if ("（1）应收款项、预收账款确认的收入".equals(column[j]))
			              createCell.setCellValue(difference.getYszkqrdsr());
		            else if ("（2）接受非货币性资产捐赠确认的收入".equals(column[j]))
			              createCell.setCellValue(difference.getJsfhbxzcjzdqrdsr());
		            else if ("2.当期确认为预算支出但没有确认为费用".equals(column[j]))
			              createCell.setCellValue(difference.getDqqrwyszcdmyqrwfy());
		            else if ("（1）支付应付款项、预付账款的支出".equals(column[j]))
			              createCell.setCellValue(difference.getZfyfkx());
		            else if ("（2）为取得存货、政府储备物资等计入物资成本的支出".equals(column[j]))
			              createCell.setCellValue(difference.getWqdch());
		            else if ("（3）为构建固定资产等的资本性支出".equals(column[j]))
			              createCell.setCellValue(difference.getWgjgdzcddzbxzc());
		            else if ("（4）偿还借款本息支出".equals(column[j]))
			              createCell.setCellValue(difference.getChjkbxzc());
		            else if ("减：1.当期确认为预算收入但没有确认为收入".equals(column[j]))
			              createCell.setCellValue(difference.getDqqrwyssrdmyqrwsr());
		            else if ("（1）收到应收款你想、预收账款确认的预算收入".equals(column[j]))
			              createCell.setCellValue(difference.getSdyfknx());
		            else if ("（2）取得借款确认的预算收入".equals(column[j]))
			              createCell.setCellValue(difference.getQdjkqrdyssr());
		            else if ("2.当期确认为费用但没有确认为预算支出".equals(column[j]))
			              createCell.setCellValue(difference.getDqqrwfydmyqrwyszc());
		            else if ("（1）发出存货、政府储备物资等确认的费用".equals(column[j]))
			              createCell.setCellValue(difference.getFcch());
		            else if ("（2）计提的这就费用和摊销费用".equals(column[j]))
			              createCell.setCellValue(difference.getJtdzfy());
		            else if ("（3）确认的资产处置费用（处置资产价值）".equals(column[j]))
			              createCell.setCellValue(difference.getCzzcjz());
		            else if ("（4）应付款项、预付账款确认的费用".equals(column[j]))
			              createCell.setCellValue(difference.getYfkx());
		            else if ("（二）其他事项差异".equals(column[j]))
			              createCell.setCellValue(difference.getQtsxcy());
		            else if ("三、本年盈余（本年收入与费用的差额）".equals(column[j]))
			              createCell.setCellValue(difference.getBnyy());
		          }
		          }
		      }
		   wb.write(os);
		      os.close();
		    } catch (Exception e) {
		      e.printStackTrace();
		    }
	}
		
	
	public List<AccountTitle> getLists() {
		return lists;
	}

	public void setLists(List<AccountTitle> lists) {
		this.lists = lists;
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
	public Map<Object, Object> getJsonmap() {
		return jsonmap;
	}
	public void setJsonmap(Map<Object, Object> jsonmap) {
		this.jsonmap = jsonmap;
	}
	public BaseService getBaseService() {
		return baseService;
	}
	public void setBaseService(BaseService baseService) {
		this.baseService = baseService;
	}
	public AccountingVoucher getAccountingVoucher() {
		return accountingVoucher;
	}
	public void setAccountingVoucher(AccountingVoucher accountingVoucher) {
		this.accountingVoucher = accountingVoucher;
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
	public BudgetVoucher getBudgetVoucher() {
		return budgetVoucher;
	}
	public void setBudgetVoucher(BudgetVoucher budgetVoucher) {
		this.budgetVoucher = budgetVoucher;
	}
	public Map<String, Object> getRequest() {
		return request;
	}
	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}

	public AccountingSubject getAccountingSubject() {
		return accountingSubject;
	}

	public void setAccountingSubject(AccountingSubject accountingSubject) {
		this.accountingSubject = accountingSubject;
	}

	public AccountTitle getAccountTitle() {
		return accountTitle;
	}

	public void setAccountTitle(AccountTitle accountTitle) {
		this.accountTitle = accountTitle;
	}
	
}