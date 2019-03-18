package com.entity;

/**
 * 财务
 * AccountingVoucher entity. @author MyEclipse Persistence Tools
 */

public class VoucherRuleBudget implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -6944018678822138334L;
	
	private String id;                       
	private String BudgeFinId;                   
	private String BudgeFinName;       
	private int type;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBudgeFinId() {
		return BudgeFinId;
	}
	public void setBudgeFinId(String budgeFinId) {
		BudgeFinId = budgeFinId;
	}
	public String getBudgeFinName() {
		return BudgeFinName;
	}
	public void setBudgeFinName(String budgeFinName) {
		BudgeFinName = budgeFinName;
	}
	
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "AccountingVoucher [id=" + id + ", BudgeFinId=" + BudgeFinId + ", BudgeFinName=" + BudgeFinName + ", type=" + type + "]";
	}
	public VoucherRuleBudget(String id, String vType, String vNo) {
		super();
		this.id = id;
		BudgeFinId = vType;
		BudgeFinName = vNo;
	}
	public VoucherRuleBudget() {
		super();
	}
	
}