package com.entity;

import java.io.Serializable;

/**
* 名称 :DocumentConversion
* 作者 :Calvin(崔红刚) 
* 邮箱 :calvin_it@163.com
* 时间 :2018年6月1日 下午4:16:23
* 描述 : 凭证转化
*/
public class DocumentConversion implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8079984208109540755L;
	
	/**
	 * ID
	 */
	private String id;
	
	/**
	 * 凭证号
	 */
	private String vId;
	
	/**
	 * 日期
	 */
	private String dDate;
	
	/**
	 * 期间名称
	 */
	private String dPeriodName;
	/**
	 * 借方金额
	 */
	private String debitAmount;
	/**
	 * 贷方金额
	 */
	private String creditAmount;
	/**
	 * 经手人
	 */
	private String dBrokerage;
	/**
	 * 支票号
	 */
	private String dCheckNo; 
	/**
	 * 入库单号
	 */
	private String dWarehouse;
	/**
	 * 制单人
	 */
	private String dSingleMan;
	/**
	 * 六级科目名称
	 */
	private String sixSubjectName;
	/**
	 * 课题段名称
	 */
	private String dTopicName; 
	/**
	 * 课题段代码
	 */
	private String dTopicNo;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getvId() {
		return vId;
	}
	public void setvId(String vId) {
		this.vId = vId;
	}
	public String getdDate() {
		return dDate;
	}
	public void setdDate(String dDate) {
		this.dDate = dDate;
	}
	public String getdPeriodName() {
		return dPeriodName;
	}
	public void setdPeriodName(String dPeriodName) {
		this.dPeriodName = dPeriodName;
	}

	public String getdBrokerage() {
		return dBrokerage;
	}
	public void setdBrokerage(String dBrokerage) {
		this.dBrokerage = dBrokerage;
	}
	public String getdCheckNo() {
		return dCheckNo;
	}
	public void setdCheckNo(String dCheckNo) {
		this.dCheckNo = dCheckNo;
	}
	public String getdWarehouse() {
		return dWarehouse;
	}
	public void setdWarehouse(String dWarehouse) {
		this.dWarehouse = dWarehouse;
	}
	public String getdSingleMan() {
		return dSingleMan;
	}
	public void setdSingleMan(String dSingleMan) {
		this.dSingleMan = dSingleMan;
	}
	
	public String getdTopicName() {
		return dTopicName;
	}
	public void setdTopicName(String dTopicName) {
		this.dTopicName = dTopicName;
	}
	public String getdTopicNo() {
		return dTopicNo;
	}
	public void setdTopicNo(String dTopicNo) {
		this.dTopicNo = dTopicNo;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public DocumentConversion() {
		super();
	}
	public String getDebitAmount() {
		return debitAmount;
	}
	public void setDebitAmount(String debitAmount) {
		this.debitAmount = debitAmount;
	}
	public String getCreditAmount() {
		return creditAmount;
	}
	public void setCreditAmount(String creditAmount) {
		this.creditAmount = creditAmount;
	}
	public String getSixSubjectName() {
		return sixSubjectName;
	}
	public void setSixSubjectName(String sixSubjectName) {
		this.sixSubjectName = sixSubjectName;
	}
	@Override
	public String toString() {
		return "DocumentConversion [id=" + id + ", vId=" + vId + ", dDate=" + dDate + ", dPeriodName=" + dPeriodName
				+ ", debitAmount=" + debitAmount + ", creditAmount=" + creditAmount + ", dBrokerage=" + dBrokerage
				+ ", dCheckNo=" + dCheckNo + ", dWarehouse=" + dWarehouse + ", dSingleMan=" + dSingleMan
				+ ", sixSubjectName=" + sixSubjectName + ", dTopicName=" + dTopicName + ", dTopicNo=" + dTopicNo + "]";
	}
	public DocumentConversion(String id, String vId, String dDate, String dPeriodName, String debitAmount,
			String creditAmount, String dBrokerage, String dCheckNo, String dWarehouse, String dSingleMan,
			String sixSubjectName, String dTopicName, String dTopicNo) {
		super();
		this.id = id;
		this.vId = vId;
		this.dDate = dDate;
		this.dPeriodName = dPeriodName;
		this.debitAmount = debitAmount;
		this.creditAmount = creditAmount;
		this.dBrokerage = dBrokerage;
		this.dCheckNo = dCheckNo;
		this.dWarehouse = dWarehouse;
		this.dSingleMan = dSingleMan;
		this.sixSubjectName = sixSubjectName;
		this.dTopicName = dTopicName;
		this.dTopicNo = dTopicNo;
	}

}
