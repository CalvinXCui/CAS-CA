package com.entity;

import java.sql.Timestamp;

/**
 * 人员表
 * Person entity. @author MyEclipse Persistence Tools
 */

public class Person implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -1508774915142661219L;
	
	private String id;                    //ID
	private String PName;                 //姓名
	private String PLoginName;            //用户名（登录名）
	private String PPassword;             //密码
	private String PPosition;             //职位/务
	private String PSex;                  //性别
	private String PMobile;               //电话
	private String CId;                   //单位ID
	private String DId;                   //部门ID
	private String PState="0";            //状态 0：在职  1：离职
	private String PEmail;                //邮箱
	private String PCreateTime;           //创建时间
	private String PIsdelete;             //是否删除
	private String PEntryTime;         //入职时间
	private String PUpdateTime;        //修改时间
	private String PRemarks;              //备注
	private String PPower;				  //访问权限
	// Constructors

	/** default constructor */
	public Person() {
	}

	/** full constructor */
	public Person(String PName, String PLoginName, String PPassword,
			String PPosition, String PSex, String PMobile, String CId,
			String DId, String PState, String PEmail, String PCreateTime,
			String PIsdelete, Timestamp PEntryTime, Timestamp PUpdateTime,
			String PRemarks) {
		this.PName = PName;
		this.PLoginName = PLoginName;
		this.PPassword = PPassword;
		this.PPosition = PPosition;
		this.PSex = PSex;
		this.PMobile = PMobile;
		this.CId = CId;
		this.DId = DId;
		this.PState = PState;
		this.PEmail = PEmail;
		this.PCreateTime = PCreateTime;
		this.PIsdelete = PIsdelete;
		this.PRemarks = PRemarks;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPName() {
		return this.PName;
	}

	public void setPName(String PName) {
		this.PName = PName;
	}

	public String getPLoginName() {
		return this.PLoginName;
	}

	public void setPLoginName(String PLoginName) {
		this.PLoginName = PLoginName;
	}

	public String getPPassword() {
		return this.PPassword;
	}

	public void setPPassword(String PPassword) {
		this.PPassword = PPassword;
	}

	public String getPPosition() {
		return this.PPosition;
	}

	public void setPPosition(String PPosition) {
		this.PPosition = PPosition;
	}

	public String getPSex() {
		return this.PSex;
	}

	public void setPSex(String PSex) {
		this.PSex = PSex;
	}

	public String getPMobile() {
		return this.PMobile;
	}

	public void setPMobile(String PMobile) {
		this.PMobile = PMobile;
	}

	public String getCId() {
		return this.CId;
	}

	public void setCId(String CId) {
		this.CId = CId;
	}

	public String getDId() {
		return this.DId;
	}

	public void setDId(String DId) {
		this.DId = DId;
	}

	public String getPState() {
		return this.PState;
	}

	public void setPState(String PState) {
		this.PState = PState;
	}

	public String getPEmail() {
		return this.PEmail;
	}

	public void setPEmail(String PEmail) {
		this.PEmail = PEmail;
	}

	public String getPCreateTime() {
		return this.PCreateTime;
	}

	public void setPCreateTime(String PCreateTime) {
		this.PCreateTime = PCreateTime;
	}

	public String getPIsdelete() {
		return this.PIsdelete;
	}

	public void setPIsdelete(String PIsdelete) {
		this.PIsdelete = PIsdelete;
	}

	public String getPRemarks() {
		return this.PRemarks;
	}

	public void setPRemarks(String PRemarks) {
		this.PRemarks = PRemarks;
	}

	public String getPPower() {
		return PPower;
	}

	public void setPPower(String pPower) {
		PPower = pPower;
	}

	public String getPEntryTime() {
		return PEntryTime;
	}

	public void setPEntryTime(String pEntryTime) {
		PEntryTime = pEntryTime;
	}

	public String getPUpdateTime() {
		return PUpdateTime;
	}

	public void setPUpdateTime(String pUpdateTime) {
		PUpdateTime = pUpdateTime;
	}

}