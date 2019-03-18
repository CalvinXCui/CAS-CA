package com.entity;

/**
 * 凭证转换规则
 * VoucherRules entity. @author MyEclipse Persistence Tools
 */

public class VoucherRules implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -6578915116321472226L;
	private String id;                         //主键
	private String voldSubjectNo;              //旧科目编号
	private String voldSubjectName;            //旧科目名称
	private String vfinancialNo;               //新财务科目编号
	private String vfinancialName;             //新财务科目名称
	private String vbudgetaryNo;               //新预算科目编号
	private String vbudgetaryName;             //新预算科目名称
	private String velementsType;              //科目要素类型
	private String vremarks;                   //备注

	// Constructors

	/** default constructor */
	public VoucherRules() {
	}

	/** full constructor */
	public VoucherRules(String voldSubjectNo, String voldSubjectName,
			String vfinancialNo, String vfinancialName, String vbudgetaryNo,
			String vbudgetaryName, String velementsType, String vremarks) {
		this.voldSubjectNo = voldSubjectNo;
		this.voldSubjectName = voldSubjectName;
		this.vfinancialNo = vfinancialNo;
		this.vfinancialName = vfinancialName;
		this.vbudgetaryNo = vbudgetaryNo;
		this.vbudgetaryName = vbudgetaryName;
		this.velementsType = velementsType;
		this.vremarks = vremarks;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getVoldSubjectNo() {
		return this.voldSubjectNo;
	}

	public void setVoldSubjectNo(String voldSubjectNo) {
		this.voldSubjectNo = voldSubjectNo;
	}

	public String getVoldSubjectName() {
		return this.voldSubjectName;
	}

	public void setVoldSubjectName(String voldSubjectName) {
		this.voldSubjectName = voldSubjectName;
	}

	public String getVfinancialNo() {
		return this.vfinancialNo;
	}

	public void setVfinancialNo(String vfinancialNo) {
		this.vfinancialNo = vfinancialNo;
	}

	public String getVfinancialName() {
		return this.vfinancialName;
	}

	public void setVfinancialName(String vfinancialName) {
		this.vfinancialName = vfinancialName;
	}

	public String getVbudgetaryNo() {
		return this.vbudgetaryNo;
	}

	public void setVbudgetaryNo(String vbudgetaryNo) {
		this.vbudgetaryNo = vbudgetaryNo;
	}

	public String getVbudgetaryName() {
		return this.vbudgetaryName;
	}

	public void setVbudgetaryName(String vbudgetaryName) {
		this.vbudgetaryName = vbudgetaryName;
	}

	public String getVelementsType() {
		return this.velementsType;
	}

	public void setVelementsType(String velementsType) {
		this.velementsType = velementsType;
	}

	public String getVremarks() {
		return this.vremarks;
	}

	public void setVremarks(String vremarks) {
		this.vremarks = vremarks;
	}

	@Override
	public String toString() {
		return "VoucherRules [id=" + id + ", voldSubjectNo=" + voldSubjectNo + ", voldSubjectName=" + voldSubjectName
				+ ", vfinancialNo=" + vfinancialNo + ", vfinancialName=" + vfinancialName + ", vbudgetaryNo="
				+ vbudgetaryNo + ", vbudgetaryName=" + vbudgetaryName + ", velementsType=" + velementsType
				+ ", vremarks=" + vremarks + "]";
	}

}