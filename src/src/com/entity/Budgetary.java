package com.entity;

import java.io.Serializable;

public class Budgetary implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4992495700142670416L;
	private String id;
	private String project;
	private String thisYear; //本年数
	private String lastYear; //上年数	
	private String time;
	private Integer orderId;
	private String direction;
	private String level;
	private String formula;
	

	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getFormula() {
		return formula;
	}
	public void setFormula(String formula) {
		this.formula = formula;
	}
	public String getProject() {
		return project;
	}
	public void setProject(String project) {
		this.project = project;
	}
	public String getThisYear() {
		return thisYear;
	}
	public void setThisYear(String thisYear) {
		this.thisYear = thisYear;
	}
	public String getLastYear() {
		return lastYear;
	}
	public void setLastYear(String lastYear) {
		this.lastYear = lastYear;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "Budgetary [id=" + id + ", project=" + project + ", thisYear=" + thisYear + ", lastYear=" + lastYear
				+ ", time=" + time + "]";
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	
}
