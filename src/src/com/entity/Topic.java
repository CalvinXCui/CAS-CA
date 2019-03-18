package com.entity;

import java.io.Serializable;


public class Topic implements Serializable {
	private static final long serialVersionUID = 1L;

	private String id;

	private String tCategoryType;

	private String tChildName;

	private String tConcludingTime;

	private String tCreateTime;

	private String tEndTime;

	private String tFundsSources;

	private String tName;

	private String tNo;

	private String tOpeningTime;

	private String tOrganization;

	private String tProjectBudget;

	private String tResponsible;

	private String tStartTime;

	private String tState;

	private String tStayFunds;

	private String tSubClass;

	private String tType;

	public Topic() {
		super();
	}

	public Topic(String tCategoryType, String tChildName, String tConcludingTime, String tCreateTime, String tEndTime,
			String tFundsSources, String tName, String tNo, String tOpeningTime, String tOrganization,
			String tProjectBudget, String tResponsible, String tStartTime, String tState, String tStayFunds,
			String tSubClass, String tType) {
		super();
		this.tCategoryType = tCategoryType;
		this.tChildName = tChildName;
		this.tConcludingTime = tConcludingTime;
		this.tCreateTime = tCreateTime;
		this.tEndTime = tEndTime;
		this.tFundsSources = tFundsSources;
		this.tName = tName;
		this.tNo = tNo;
		this.tOpeningTime = tOpeningTime;
		this.tOrganization = tOrganization;
		this.tProjectBudget = tProjectBudget;
		this.tResponsible = tResponsible;
		this.tStartTime = tStartTime;
		this.tState = tState;
		this.tStayFunds = tStayFunds;
		this.tSubClass = tSubClass;
		this.tType = tType;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String gettCategoryType() {
		return tCategoryType;
	}

	public void settCategoryType(String tCategoryType) {
		this.tCategoryType = tCategoryType;
	}

	public String gettChildName() {
		return tChildName;
	}

	public void settChildName(String tChildName) {
		this.tChildName = tChildName;
	}

	public String gettConcludingTime() {
		return tConcludingTime;
	}

	public void settConcludingTime(String tConcludingTime) {
		this.tConcludingTime = tConcludingTime;
	}

	public String gettCreateTime() {
		return tCreateTime;
	}

	public void settCreateTime(String tCreateTime) {
		this.tCreateTime = tCreateTime;
	}

	public String gettEndTime() {
		return tEndTime;
	}

	public void settEndTime(String tEndTime) {
		this.tEndTime = tEndTime;
	}

	public String gettFundsSources() {
		return tFundsSources;
	}

	public void settFundsSources(String tFundsSources) {
		this.tFundsSources = tFundsSources;
	}

	public String gettName() {
		return tName;
	}

	public void settName(String tName) {
		this.tName = tName;
	}

	public String gettNo() {
		return tNo;
	}

	public void settNo(String tNo) {
		this.tNo = tNo;
	}

	public String gettOpeningTime() {
		return tOpeningTime;
	}

	public void settOpeningTime(String tOpeningTime) {
		this.tOpeningTime = tOpeningTime;
	}

	public String gettOrganization() {
		return tOrganization;
	}

	public void settOrganization(String tOrganization) {
		this.tOrganization = tOrganization;
	}

	public String gettProjectBudget() {
		return tProjectBudget;
	}

	public void settProjectBudget(String tProjectBudget) {
		this.tProjectBudget = tProjectBudget;
	}

	public String gettResponsible() {
		return tResponsible;
	}

	public void settResponsible(String tResponsible) {
		this.tResponsible = tResponsible;
	}

	public String gettStartTime() {
		return tStartTime;
	}

	public void settStartTime(String tStartTime) {
		this.tStartTime = tStartTime;
	}

	public String gettState() {
		return tState;
	}

	public void settState(String tState) {
		this.tState = tState;
	}

	public String gettStayFunds() {
		return tStayFunds;
	}

	public void settStayFunds(String tStayFunds) {
		this.tStayFunds = tStayFunds;
	}

	public String gettSubClass() {
		return tSubClass;
	}

	public void settSubClass(String tSubClass) {
		this.tSubClass = tSubClass;
	}

	public String gettType() {
		return tType;
	}

	public void settType(String tType) {
		this.tType = tType;
	}

	@Override
	public String toString() {
		return "Topic [id=" + id + ", tCategoryType=" + tCategoryType + ", tChildName=" + tChildName
				+ ", tConcludingTime=" + tConcludingTime + ", tCreateTime=" + tCreateTime + ", tEndTime=" + tEndTime
				+ ", tFundsSources=" + tFundsSources + ", tName=" + tName + ", tNo=" + tNo + ", tOpeningTime="
				+ tOpeningTime + ", tOrganization=" + tOrganization + ", tProjectBudget=" + tProjectBudget
				+ ", tResponsible=" + tResponsible + ", tStartTime=" + tStartTime + ", tState=" + tState
				+ ", tStayFunds=" + tStayFunds + ", tSubClass=" + tSubClass + ", tType=" + tType + "]";
	}

}