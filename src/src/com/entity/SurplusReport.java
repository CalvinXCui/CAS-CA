package com.entity;

import java.io.Serializable;

public class SurplusReport implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4251587897326664843L;
	
	
	private String id;
	//财政拨款结转结余
	private String czbkjzjyData;
	//其他资金结转结余
	private String qtzjjzjyData;
	//年初余额--财政拨款结转结余
	private String ncyeczbkjzjyData;
	//年初余额--其他资金结转结余
	private String ncyeqtzjjzjyData;
	//本年收支差额
	private String fbnszceData;
	//归集调入
	private String fgjdrData;
	//归集上缴或调出
	private String fgjsjData;
	//本年收支差额
	private String fczbkjzData;
	//缴回资金
	private String jhzjData;
	//使用专用结余
	private String syzyjyData;
	//支付所得税
	private String zfsdsData;
	//财政拨款结转
	private String czbkjzData;
	//财政拨款结余
	private String czbkjyData;
	//非财政拨款结转
	private String fczbkData;
	//非财政拨款结余
	private String fczbkjyData;
	//专用结余
	private String zyjyData;
	//经营结余
	private String tjyjyData;
	//创建时间
	private String createTime;
	
	

	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getCzbkjzjyData() {
		return czbkjzjyData;
	}
	public void setCzbkjzjyData(String czbkjzjyData) {
		this.czbkjzjyData = czbkjzjyData;
	}
	public String getQtzjjzjyData() {
		return qtzjjzjyData;
	}
	public void setQtzjjzjyData(String qtzjjzjyData) {
		this.qtzjjzjyData = qtzjjzjyData;
	}
	public String getNcyeczbkjzjyData() {
		return ncyeczbkjzjyData;
	}
	public void setNcyeczbkjzjyData(String ncyeczbkjzjyData) {
		this.ncyeczbkjzjyData = ncyeczbkjzjyData;
	}
	public String getNcyeqtzjjzjyData() {
		return ncyeqtzjjzjyData;
	}
	public void setNcyeqtzjjzjyData(String ncyeqtzjjzjyData) {
		this.ncyeqtzjjzjyData = ncyeqtzjjzjyData;
	}
	public String getFbnszceData() {
		return fbnszceData;
	}
	public void setFbnszceData(String fbnszceData) {
		this.fbnszceData = fbnszceData;
	}
	public String getFgjdrData() {
		return fgjdrData;
	}
	public void setFgjdrData(String fgjdrData) {
		this.fgjdrData = fgjdrData;
	}
	public String getFgjsjData() {
		return fgjsjData;
	}
	public void setFgjsjData(String fgjsjData) {
		this.fgjsjData = fgjsjData;
	}
	public String getFczbkjzData() {
		return fczbkjzData;
	}
	public void setFczbkjzData(String fczbkjzData) {
		this.fczbkjzData = fczbkjzData;
	}
	public String getJhzjData() {
		return jhzjData;
	}
	public void setJhzjData(String jhzjData) {
		this.jhzjData = jhzjData;
	}
	public String getSyzyjyData() {
		return syzyjyData;
	}
	public void setSyzyjyData(String syzyjyData) {
		this.syzyjyData = syzyjyData;
	}
	public String getZfsdsData() {
		return zfsdsData;
	}
	public void setZfsdsData(String zfsdsData) {
		this.zfsdsData = zfsdsData;
	}
	public String getCzbkjzData() {
		return czbkjzData;
	}
	public void setCzbkjzData(String czbkjzData) {
		this.czbkjzData = czbkjzData;
	}
	public String getCzbkjyData() {
		return czbkjyData;
	}
	public void setCzbkjyData(String czbkjyData) {
		this.czbkjyData = czbkjyData;
	}
	public String getFczbkData() {
		return fczbkData;
	}
	public void setFczbkData(String fczbkData) {
		this.fczbkData = fczbkData;
	}
	public String getFczbkjyData() {
		return fczbkjyData;
	}
	public void setFczbkjyData(String fczbkjyData) {
		this.fczbkjyData = fczbkjyData;
	}
	public String getZyjyData() {
		return zyjyData;
	}
	public void setZyjyData(String zyjyData) {
		this.zyjyData = zyjyData;
	}
	public String getTjyjyData() {
		return tjyjyData;
	}
	public void setTjyjyData(String tjyjyData) {
		this.tjyjyData = tjyjyData;
	}
	
	
	
}
