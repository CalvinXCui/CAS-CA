package com.entity;

/**
 * 财务
 * AccountingVoucher entity. @author MyEclipse Persistence Tools
 */

public class VoucherRuleName implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -6944018678822138334L;
	
	private int id;                       //主键
	private String FinBrrowName;                    //凭证类型  1:收款凭证 2:付款凭证 3:转账凭证
	private String FinLoanName;                      //凭证编号
	private String BudgeBrrowName;                    //凭证日期
	private String BudgeLoanName;                //摘要 经济业务内容
	private String BudgeBrrowNO;                    //凭证日期
	private String BudgeLoanNO;                //摘要 经济业务内容
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFinBrrowName() {
		return FinBrrowName;
	}
	public void setFinBrrowName(String finBrrowName) {
		FinBrrowName = finBrrowName;
	}
	public String getFinLoanName() {
		return FinLoanName;
	}
	public void setFinLoanName(String finLoanName) {
		FinLoanName = finLoanName;
	}
	public String getBudgeBrrowName() {
		return BudgeBrrowName;
	}
	public void setBudgeBrrowName(String budgeBrrowName) {
		BudgeBrrowName = budgeBrrowName;
	}
	public String getBudgeLoanName() {
		return BudgeLoanName;
	}
	public void setBudgeLoanName(String budgeLoanName) {
		BudgeLoanName = budgeLoanName;
	}
	
	public String getBudgeBrrowNO() {
		return BudgeBrrowNO;
	}
	public void setBudgeBrrowNO(String budgeBrrowNO) {
		BudgeBrrowNO = budgeBrrowNO;
	}
	public String getBudgeLoanNO() {
		return BudgeLoanNO;
	}
	public void setBudgeLoanNO(String budgeLoanNO) {
		BudgeLoanNO = budgeLoanNO;
	}
	@Override
	public String toString() {
		return "AccountingVoucher [id=" + id + ", FinBrrowName=" + FinBrrowName + ", FinLoanName=" + FinLoanName + ", BudgeBrrowName=" + BudgeBrrowName + ", BudgeLoanName="
				+ BudgeLoanName + ", BudgeBrrowNO=" + BudgeBrrowNO + ", BudgeLoanNO="+ BudgeLoanNO + "]";
	}
	public VoucherRuleName(int id, String vType, String vNo, String vDate, String vAbstract,String budgeBrrowNO, String budgeLoanNO) {
		super();
		this.id = id;
		FinBrrowName = vType;
		FinLoanName = vNo;
		BudgeBrrowName = vDate;
		BudgeLoanName = vAbstract;
		BudgeBrrowNO=budgeBrrowNO;
		BudgeLoanNO=budgeLoanNO;
	}
	public VoucherRuleName() {
		super();
	}
	
}