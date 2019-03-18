package com.entity;

/**
 * 项目预算表
 * Budget entity. @author MyEclipse Persistence Tools
 */

public class Budget implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -2420566578949070035L;
	
	private String id;               //主键
	private String BProjectNo;       //项目编号
	private String BProjectName;     //项目名称
	private String BProjectId;       //项目ID
	private String BDetailed;        //项目经费预算明细
	private String BRemarks;         //备注

	// Constructors

	/** default constructor */
	public Budget() {
	}

	/** full constructor */
	public Budget(String BProjectNo, String BProjectName, String BProjectId,
			String BDetailed, String BRemarks) {
		this.BProjectNo = BProjectNo;
		this.BProjectName = BProjectName;
		this.BProjectId = BProjectId;
		this.BDetailed = BDetailed;
		this.BRemarks = BRemarks;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBProjectNo() {
		return this.BProjectNo;
	}

	public void setBProjectNo(String BProjectNo) {
		this.BProjectNo = BProjectNo;
	}

	public String getBProjectName() {
		return this.BProjectName;
	}

	public void setBProjectName(String BProjectName) {
		this.BProjectName = BProjectName;
	}

	public String getBProjectId() {
		return this.BProjectId;
	}

	public void setBProjectId(String BProjectId) {
		this.BProjectId = BProjectId;
	}

	public String getBDetailed() {
		return this.BDetailed;
	}

	public void setBDetailed(String BDetailed) {
		this.BDetailed = BDetailed;
	}

	public String getBRemarks() {
		return this.BRemarks;
	}

	public void setBRemarks(String BRemarks) {
		this.BRemarks = BRemarks;
	}

}