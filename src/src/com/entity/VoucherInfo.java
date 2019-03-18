package com.entity;

/**
 * 财务
 * AccountingVoucher entity. @author MyEclipse Persistence Tools
 */

public class VoucherInfo implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1761990035214105333L;
	
	private String id;                        //主键
	private String vcwfzr;                    //财务负责人
	private String vcn;                       //出纳
	private String vzhzh;                     //制证
	private String vjzh;               		  //记账
	private String vfh;             		  //复核
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	
	public String getVcwfzr() {
		return vcwfzr;
	}
	public void setVcwfzr(String vcwfzr) {
		this.vcwfzr = vcwfzr;
	}
	public String getVcn() {
		return vcn;
	}
	public void setVcn(String vcn) {
		this.vcn = vcn;
	}
	public String getVzhzh() {
		return vzhzh;
	}
	public void setVzhzh(String vzhzh) {
		this.vzhzh = vzhzh;
	}
	public String getVjzh() {
		return vjzh;
	}
	public void setVjzh(String vjzh) {
		this.vjzh = vjzh;
	}
	public String getVfh() {
		return vfh;
	}
	public void setVfh(String vfh) {
		this.vfh = vfh;
	}
	public VoucherInfo() {
		super();
	}
	
}