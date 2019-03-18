package com.entity;

import java.sql.Timestamp;

/**
 * 项目表
 * ProjectInfo entity. @author MyEclipse Persistence Tools
 */

public class ProjectInfo implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 3805419351188497692L;
	private String id;                   //
	private String groupName;            //团队名称
	private String groupId;              //团队ID
	private String groupHead;            //团队负责人
	private String groupHeadId;          //团队负责人ID
	private String projectNo;            //项目编号
	private String projectName;          //项目名称
	private String projectHead;          //项目负责人
	private String projectHeadId;        //项目负责人ID
	private String projectStatus;        //项目状态
	private String accountType;          //付款类型
	private String payCode;              //支付码
	private String createName;           //创建人名称
	private String createBy;             //创建人登录名称
	private String createDate;        //创建日期
	private String updateName;           //更新人名称
	private String updateBy;             //更新人登录名称
	private String updateDate;        //更新日期
	private String sysOrgCode;           //所属部门
	private String sysCompanyCode;       //所属公司
	private String groupOrgCode;         //团队组织编码

	// Constructors

	/** default constructor */
	public ProjectInfo() {
	}

	/** full constructor */
	public ProjectInfo(String groupName, String groupId, String groupHead,
			String groupHeadId, String projectNo, String projectName,
			String projectHead, String projectHeadId, String projectStatus,
			String accountType, String payCode, String createName,
			String createBy, Timestamp createDate, String updateName,
			String updateBy, Timestamp updateDate, String sysOrgCode,
			String sysCompanyCode, String groupOrgCode) {
		this.groupName = groupName;
		this.groupId = groupId;
		this.groupHead = groupHead;
		this.groupHeadId = groupHeadId;
		this.projectNo = projectNo;
		this.projectName = projectName;
		this.projectHead = projectHead;
		this.projectHeadId = projectHeadId;
		this.projectStatus = projectStatus;
		this.accountType = accountType;
		this.payCode = payCode;
		this.createName = createName;
		this.createBy = createBy;
		this.updateName = updateName;
		this.updateBy = updateBy;
		this.sysOrgCode = sysOrgCode;
		this.sysCompanyCode = sysCompanyCode;
		this.groupOrgCode = groupOrgCode;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getGroupName() {
		return this.groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getGroupId() {
		return this.groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getGroupHead() {
		return this.groupHead;
	}

	public void setGroupHead(String groupHead) {
		this.groupHead = groupHead;
	}

	public String getGroupHeadId() {
		return this.groupHeadId;
	}

	public void setGroupHeadId(String groupHeadId) {
		this.groupHeadId = groupHeadId;
	}

	public String getProjectNo() {
		return this.projectNo;
	}

	public void setProjectNo(String projectNo) {
		this.projectNo = projectNo;
	}

	public String getProjectName() {
		return this.projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectHead() {
		return this.projectHead;
	}

	public void setProjectHead(String projectHead) {
		this.projectHead = projectHead;
	}

	public String getProjectHeadId() {
		return this.projectHeadId;
	}

	public void setProjectHeadId(String projectHeadId) {
		this.projectHeadId = projectHeadId;
	}

	public String getProjectStatus() {
		return this.projectStatus;
	}

	public void setProjectStatus(String projectStatus) {
		this.projectStatus = projectStatus;
	}

	public String getAccountType() {
		return this.accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getPayCode() {
		return this.payCode;
	}

	public void setPayCode(String payCode) {
		this.payCode = payCode;
	}

	public String getCreateName() {
		return this.createName;
	}

	public void setCreateName(String createName) {
		this.createName = createName;
	}

	public String getCreateBy() {
		return this.createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getUpdateName() {
		return this.updateName;
	}

	public void setUpdateName(String updateName) {
		this.updateName = updateName;
	}

	public String getUpdateBy() {
		return this.updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public String getSysOrgCode() {
		return this.sysOrgCode;
	}

	public void setSysOrgCode(String sysOrgCode) {
		this.sysOrgCode = sysOrgCode;
	}

	public String getSysCompanyCode() {
		return this.sysCompanyCode;
	}

	public void setSysCompanyCode(String sysCompanyCode) {
		this.sysCompanyCode = sysCompanyCode;
	}

	public String getGroupOrgCode() {
		return this.groupOrgCode;
	}

	public void setGroupOrgCode(String groupOrgCode) {
		this.groupOrgCode = groupOrgCode;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

}