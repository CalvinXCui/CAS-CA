package com.entity;

public class RecordingVoucher implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7373452240527238979L;
	
	private String id;//主键
	private String createTime;//创建时间
	private String voucherNo;//凭证编号
	private String jfAccount;//借方金额
	private String dfAccount;//贷方金额
	private String flag="1";//数据状态，1为有效
	
	
	
	
	
	

	@Override
	public String toString() {
		return "RecordingVoucher [id=" + id + ", createTime=" + createTime + ", voucherNo=" + voucherNo + ", jfAccount="
				+ jfAccount + ", dfAccount=" + dfAccount + ", flag=" + flag + "]";
	}
	public RecordingVoucher(String id, String createTime, String voucherNo, String jfAccount, String dfAccount,
			String flag) {
		super();
		this.id = id;
		this.createTime = createTime;
		this.voucherNo = voucherNo;
		this.jfAccount = jfAccount;
		this.dfAccount = dfAccount;
		this.flag = flag;
	}
	public RecordingVoucher() {
		super();
		// TODO Auto-generated constructor stub
	}
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
	public String getVoucherNo() {
		return voucherNo;
	}
	public void setVoucherNo(String voucherNo) {
		this.voucherNo = voucherNo;
	}
	public String getJfAccount() {
		return jfAccount;
	}
	public void setJfAccount(String jfAccount) {
		this.jfAccount = jfAccount;
	}
	public String getDfAccount() {
		return dfAccount;
	}
	public void setDfAccount(String dfAccount) {
		this.dfAccount = dfAccount;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
