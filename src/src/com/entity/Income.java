package com.entity;

import java.io.Serializable;

public class Income implements Serializable{

	/**
	 * 收入费用表
	 */
	private static final long serialVersionUID = -8870834701648198477L;
	
	private String id;
	//一、本期预算收入
	private String bqyssr="";
	//(一) 财政拨款收入
	private String czbksr="";
	//其中：政府性基金收入
	private String zfjjsr="";
	//（二）事业收入
	private String  sysr="";
	//（三）上级补助收入
	private String sjfzsr="";
	//（四）附属单位上缴收入
	private String fsdwsjsr="";
	//（五）经营收入
	private String jysr="";
	//（六）非同级财政拨款收入
	private String ftjczbksr="";
	//（七）投资收益	
	private String tzsy="";
	//（八）捐赠收入
	private String jzsr="";
	//（九）利息收入
	private String lxsr="";
	//（十）租金收入
	private String zjsr="";
	//（十一）其他收入
	private String qtsr="";
	//二、本期费用
	private String bqfy="";
	//（（一）业务活动费用
	private String ywhdfy="";
	//（二）单位管理费用
	private String dwglfy="";
	//（三）经营费用
	private String jyfy="";
	//（四）资产处置费用
	private String zcczfy="";
	//（五）上缴上级费用
	private String sjsjfy="";
	//（六）对附属单位补助费用
	private String dfsdwbzfy="";
	//（七）所得税费用
	private String sdsfy="";
	//（八）其他费用
	private String qtfy="";
	//三、本期盈余
	private String bqyy="";
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBqyssr() {
		return bqyssr;
	}
	public void setBqyssr(String bqyssr) {
		this.bqyssr = bqyssr;
	}
	public String getCzbksr() {
		return czbksr;
	}
	public void setCzbksr(String czbksr) {
		this.czbksr = czbksr;
	}
	public String getZfjjsr() {
		return zfjjsr;
	}
	public void setZfjjsr(String zfjjsr) {
		this.zfjjsr = zfjjsr;
	}
	public String getSysr() {
		return sysr;
	}
	public void setSysr(String sysr) {
		this.sysr = sysr;
	}
	public String getSjfzsr() {
		return sjfzsr;
	}
	public void setSjfzsr(String sjfzsr) {
		this.sjfzsr = sjfzsr;
	}
	public String getFsdwsjsr() {
		return fsdwsjsr;
	}
	public void setFsdwsjsr(String fsdwsjsr) {
		this.fsdwsjsr = fsdwsjsr;
	}
	public String getJysr() {
		return jysr;
	}
	public void setJysr(String jysr) {
		this.jysr = jysr;
	}
	public String getFtjczbksr() {
		return ftjczbksr;
	}
	public void setFtjczbksr(String ftjczbksr) {
		this.ftjczbksr = ftjczbksr;
	}
	public String getTzsy() {
		return tzsy;
	}
	public void setTzsy(String tzsy) {
		this.tzsy = tzsy;
	}
	public String getJzsr() {
		return jzsr;
	}
	public void setJzsr(String jzsr) {
		this.jzsr = jzsr;
	}
	public String getLxsr() {
		return lxsr;
	}
	public void setLxsr(String lxsr) {
		this.lxsr = lxsr;
	}
	public String getZjsr() {
		return zjsr;
	}
	public void setZjsr(String zjsr) {
		this.zjsr = zjsr;
	}
	public String getQtsr() {
		return qtsr;
	}
	public void setQtsr(String qtsr) {
		this.qtsr = qtsr;
	}
	public String getBqfy() {
		return bqfy;
	}
	public void setBqfy(String bqfy) {
		this.bqfy = bqfy;
	}
	public String getYwhdfy() {
		return ywhdfy;
	}
	public void setYwhdfy(String ywhdfy) {
		this.ywhdfy = ywhdfy;
	}
	public String getDwglfy() {
		return dwglfy;
	}
	public void setDwglfy(String dwglfy) {
		this.dwglfy = dwglfy;
	}
	public String getJyfy() {
		return jyfy;
	}
	public void setJyfy(String jyfy) {
		this.jyfy = jyfy;
	}
	public String getZcczfy() {
		return zcczfy;
	}
	public void setZcczfy(String zcczfy) {
		this.zcczfy = zcczfy;
	}
	public String getSjsjfy() {
		return sjsjfy;
	}
	public void setSjsjfy(String sjsjfy) {
		this.sjsjfy = sjsjfy;
	}
	public String getDfsdwbzfy() {
		return dfsdwbzfy;
	}
	public void setDfsdwbzfy(String dfsdwbzfy) {
		this.dfsdwbzfy = dfsdwbzfy;
	}
	public String getSdsfy() {
		return sdsfy;
	}
	public void setSdsfy(String sdsfy) {
		this.sdsfy = sdsfy;
	}
	public String getQtfy() {
		return qtfy;
	}
	public void setQtfy(String qtfy) {
		this.qtfy = qtfy;
	}
	public String getBqyy() {
		return bqyy;
	}
	public void setBqyy(String bqyy) {
		this.bqyy = bqyy;
	}
	
	
	

}
