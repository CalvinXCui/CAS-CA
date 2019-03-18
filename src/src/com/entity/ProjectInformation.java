package com.entity;

/**
 * 项目表
 * ProjectInformation entity. @author MyEclipse Persistence Tools
 */

public class ProjectInformation implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -7517252421024712924L;
	private String id;                           //主键
	private String PGroupResponsilble;           //团队负责人
	private String PGroupResponsilbleId;         //团队负责人ID
	private String PGroupId;                     //项目组id
	private String PGroupNo;                     //项目组编号
	private String PName;                        //项目名称
	private String PResponsilble;                //项目负责人
	private String PAscriptionPersonId;          //项目归属人id
	private String PState;                       //项目状态
	private String PPaymentType;                 //付款类型
	private String PPaymentNo;                   //支付码
	private String PRemarks;                     //备注

	// Constructors

	/** default constructor */
	public ProjectInformation() {
	}

	/** minimal constructor */
	public ProjectInformation(String PGroupNo) {
		this.PGroupNo = PGroupNo;
	}

	/** full constructor */
	public ProjectInformation(String PGroupResponsilble,
			String PGroupResponsilbleId, String PGroupId, String PGroupNo,
			String PName, String PResponsilble, String PAscriptionPersonId,
			String PState, String PPaymentType, String PPaymentNo,
			String PRemarks) {
		this.PGroupResponsilble = PGroupResponsilble;
		this.PGroupResponsilbleId = PGroupResponsilbleId;
		this.PGroupId = PGroupId;
		this.PGroupNo = PGroupNo;
		this.PName = PName;
		this.PResponsilble = PResponsilble;
		this.PAscriptionPersonId = PAscriptionPersonId;
		this.PState = PState;
		this.PPaymentType = PPaymentType;
		this.PPaymentNo = PPaymentNo;
		this.PRemarks = PRemarks;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPGroupResponsilble() {
		return this.PGroupResponsilble;
	}

	public void setPGroupResponsilble(String PGroupResponsilble) {
		this.PGroupResponsilble = PGroupResponsilble;
	}

	public String getPGroupResponsilbleId() {
		return this.PGroupResponsilbleId;
	}

	public void setPGroupResponsilbleId(String PGroupResponsilbleId) {
		this.PGroupResponsilbleId = PGroupResponsilbleId;
	}

	public String getPGroupId() {
		return this.PGroupId;
	}

	public void setPGroupId(String PGroupId) {
		this.PGroupId = PGroupId;
	}

	public String getPGroupNo() {
		return this.PGroupNo;
	}

	public void setPGroupNo(String PGroupNo) {
		this.PGroupNo = PGroupNo;
	}

	public String getPName() {
		return this.PName;
	}

	public void setPName(String PName) {
		this.PName = PName;
	}

	public String getPResponsilble() {
		return this.PResponsilble;
	}

	public void setPResponsilble(String PResponsilble) {
		this.PResponsilble = PResponsilble;
	}

	public String getPAscriptionPersonId() {
		return this.PAscriptionPersonId;
	}

	public void setPAscriptionPersonId(String PAscriptionPersonId) {
		this.PAscriptionPersonId = PAscriptionPersonId;
	}

	public String getPState() {
		return this.PState;
	}

	public void setPState(String PState) {
		this.PState = PState;
	}

	public String getPPaymentType() {
		return this.PPaymentType;
	}

	public void setPPaymentType(String PPaymentType) {
		this.PPaymentType = PPaymentType;
	}

	public String getPPaymentNo() {
		return this.PPaymentNo;
	}

	public void setPPaymentNo(String PPaymentNo) {
		this.PPaymentNo = PPaymentNo;
	}

	public String getPRemarks() {
		return this.PRemarks;
	}

	public void setPRemarks(String PRemarks) {
		this.PRemarks = PRemarks;
	}

}