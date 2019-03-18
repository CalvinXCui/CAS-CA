package com.action;


import com.entity.AccountingVoucher;
import com.entity.VoucherRuleBudget;
import com.opensymphony.xwork2.ActionSupport;

import java.util.List;
import java.util.Map;



/**
 * 
 * @author can
 *
 */
public class MyClientAction extends ActionSupport{
	public static final String IP_ADDR = "localhost";//服务器地址  这里要改成服务器的ip
	public static final int PORT = 8829;//服务器端口号  
	
	private  Map<String, Object> jsonmap;

	private  List<AccountingVoucher> accountingVouchers;
	
	private  List<VoucherRuleBudget> voucherRuleBudgets;	
	
	private  String  jsonStr;
	
	private  String  VNo;
	

	
			
/*--------------------提供 get set---------------------------- */
	public List<AccountingVoucher> getAccountingVouchers() {
		return accountingVouchers;
	}

	public void setAccountingVouchers(List<AccountingVoucher> accountingVouchers) {
		this.accountingVouchers = accountingVouchers;
	}
	public Map<String, Object> getJsonmap() {
		return jsonmap;
	}
	public void setJsonmap(Map<String, Object> jsonmap) {
		this.jsonmap = jsonmap;
	}
	public List<VoucherRuleBudget> getVoucherRuleBudgets() {
		return voucherRuleBudgets;
	}
	public void setVoucherRuleBudgets(List<VoucherRuleBudget> voucherRuleBudgets) {
		this.voucherRuleBudgets = voucherRuleBudgets;
	}

	public String getJsonStr() {
		return jsonStr;
	}
	
	public void setJsonStr(String jsonStr) {
		this.jsonStr = jsonStr;;
	}

	public String getVNo() {
		return VNo;
	}

	public void setVNo(String vNo) {
		VNo = vNo;
	}
	
	
	 
}
