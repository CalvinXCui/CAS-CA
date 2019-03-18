package com.entity;

/**
 * 部门表
 * Department entity. @author MyEclipse Persistence Tools
 */

public class Department implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -779034659838398361L;
	
	private String id;                  //ID
	private String CName;               //单位名称
	private String CAddress;            //单位地址
	private String CPhone;              //单位电话
	private String CCreateTime;         //成立时间
	private String CPrincipal;          //单位负责人
	private String CDescription;        //单位描述
	private String CAbbreviation;       //单位简称
	private String CRemarks;            //备注

	// Constructors

	/** default constructor */
	public Department() {
	}

	/** full constructor */
	public Department(String CName, String CAddress, String CPhone,
			String CCreateTime, String CPrincipal, String CDescription,
			String CAbbreviation, String CRemarks) {
		this.CName = CName;
		this.CAddress = CAddress;
		this.CPhone = CPhone;
		this.CCreateTime = CCreateTime;
		this.CPrincipal = CPrincipal;
		this.CDescription = CDescription;
		this.CAbbreviation = CAbbreviation;
		this.CRemarks = CRemarks;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCName() {
		return this.CName;
	}

	public void setCName(String CName) {
		this.CName = CName;
	}

	public String getCAddress() {
		return this.CAddress;
	}

	public void setCAddress(String CAddress) {
		this.CAddress = CAddress;
	}

	public String getCPhone() {
		return this.CPhone;
	}

	public void setCPhone(String CPhone) {
		this.CPhone = CPhone;
	}

	public String getCCreateTime() {
		return this.CCreateTime;
	}

	public void setCCreateTime(String CCreateTime) {
		this.CCreateTime = CCreateTime;
	}

	public String getCPrincipal() {
		return this.CPrincipal;
	}

	public void setCPrincipal(String CPrincipal) {
		this.CPrincipal = CPrincipal;
	}

	public String getCDescription() {
		return this.CDescription;
	}

	public void setCDescription(String CDescription) {
		this.CDescription = CDescription;
	}

	public String getCAbbreviation() {
		return this.CAbbreviation;
	}

	public void setCAbbreviation(String CAbbreviation) {
		this.CAbbreviation = CAbbreviation;
	}

	public String getCRemarks() {
		return this.CRemarks;
	}

	public void setCRemarks(String CRemarks) {
		this.CRemarks = CRemarks;
	}

}