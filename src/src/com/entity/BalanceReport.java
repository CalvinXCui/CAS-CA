package com.entity;

import java.io.Serializable;

/**
 * 对应资产负债表
 * @author 15176
 *
 */
public class BalanceReport implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2682387548630703218L;
	private String id;
	private String columnOne;
	private String columnTwo;
	private String columnThree;
	private String columnFour;
	
	private String dataType;
	private String createTime;
	
	private String beginDataOne;
	private String beginDataTwo;
	
	
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getBeginDataOne() {
		return beginDataOne;
	}
	public void setBeginDataOne(String beginDataOne) {
		this.beginDataOne = beginDataOne;
	}
	public String getBeginDataTwo() {
		return beginDataTwo;
	}
	public void setBeginDataTwo(String beginDataTwo) {
		this.beginDataTwo = beginDataTwo;
	}
	public String getColumnOne() {
		return columnOne;
	}
	public void setColumnOne(String columnOne) {
		this.columnOne = columnOne;
	}
	public String getColumnTwo() {
		return columnTwo;
	}
	public void setColumnTwo(String columnTwo) {
		this.columnTwo = columnTwo;
	}
	public String getColumnThree() {
		return columnThree;
	}
	public void setColumnThree(String columnThree) {
		this.columnThree = columnThree;
	}
	public String getColumnFour() {
		return columnFour;
	}
	public void setColumnFour(String columnFour) {
		this.columnFour = columnFour;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	

}
