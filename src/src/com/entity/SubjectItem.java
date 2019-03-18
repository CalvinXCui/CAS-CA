package com.entity;

/**
 * 科目类别
 * SubjectItem entity. @author MyEclipse Persistence Tools
 */

public class SubjectItem implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;            //主键
	private Integer SSerial;      //序号
	private String SName;         //名称
	private String SLevel;        //级别
	private String SFlag;         //标注 1：旧制度 2：新制度
	private String SRemarks;      //备注

	// Constructors

	/** default constructor */
	public SubjectItem() {
	}

	/** minimal constructor */
	public SubjectItem(Integer SSerial, String SName, String SLevel,
			String SFlag) {
		this.SSerial = SSerial;
		this.SName = SName;
		this.SLevel = SLevel;
		this.SFlag = SFlag;
	}

	/** full constructor */
	public SubjectItem(Integer SSerial, String SName, String SLevel,
			String SFlag, String SRemarks) {
		this.SSerial = SSerial;
		this.SName = SName;
		this.SLevel = SLevel;
		this.SFlag = SFlag;
		this.SRemarks = SRemarks;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getSSerial() {
		return this.SSerial;
	}

	public void setSSerial(Integer SSerial) {
		this.SSerial = SSerial;
	}

	public String getSName() {
		return this.SName;
	}

	public void setSName(String SName) {
		this.SName = SName;
	}

	public String getSLevel() {
		return this.SLevel;
	}

	public void setSLevel(String SLevel) {
		this.SLevel = SLevel;
	}

	public String getSFlag() {
		return this.SFlag;
	}

	public void setSFlag(String SFlag) {
		this.SFlag = SFlag;
	}

	public String getSRemarks() {
		return this.SRemarks;
	}

	public void setSRemarks(String SRemarks) {
		this.SRemarks = SRemarks;
	}

}