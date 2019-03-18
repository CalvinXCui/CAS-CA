package com.entity;

/***
 * 本年盈余与预算结余差异说明
 * @author admin
 *
 */
public class Difference {
	
	private String id="";
	//一、本年预算结余（本年预算收支差异）	
	private String bnysjy="";
	//二、差异调节	
	private String cytj="";
	//（一）重要事项的差异	
	private String zysxdcy="";
	//加：1.当期确认为收入但没有确认为预算收入	
	private String dqqrwsr="";
	//（1）应收款项、预收账款确认的收入	2760.69
	private String yszkqrdsr="";
	//（2）接受非货币性资产捐赠确认的收入
	private String jsfhbxzcjzdqrdsr="";
	//2.当期确认为预算支出但没有确认为费用	
	private String dqqrwyszcdmyqrwfy="";
	//（1）支付应付款项、预付账款的支出	55869553.31
	private String zfyfkx="";
	//（2）为取得存货、政府储备物资等计入物资成本的支出	0.00
	private String wqdch="";
	//（3）为构建固定资产等的资本性支出	22401706.94
	private String wgjgdzcddzbxzc="";
	//（4）偿还借款本息支出	0.00
	private String chjkbxzc="";
	//减：1.当期确认为预算收入但没有确认为收入	
	private String dqqrwyssrdmyqrwsr="";
	//（1）收到应收款你想、预收账款确认的预算收入	56189853.31
	private String sdyfknx="";
	//（2）取得借款确认的预算收入	0.00
	private String qdjkqrdyssr="";
	//2.当期确认为费用但没有确认为预算支出	
	private String dqqrwfydmyqrwyszc="";
	//（1）发出存货、政府储备物资等确认的费用	0.00
	private String fcch="";
	//（2）计提的这就费用和摊销费用	50262046.27
	private String jtdzfy="";
	//（3）确认的资产处置费用（处置资产价值）	0.00
	private String czzcjz="";
	//（4）应付款项、预付账款确认的费用	0.00
	private String yfkx="";
	//（二）其他事项差异	0.00
	private String qtsxcy="";
	//三、本年盈余（本年收入与费用的差额）
	private String bnyy="";
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBnysjy() {
		return bnysjy;
	}
	public void setBnysjy(String bnysjy) {
		this.bnysjy = bnysjy;
	}
	public String getCytj() {
		return cytj;
	}
	public void setCytj(String cytj) {
		this.cytj = cytj;
	}
	public String getZysxdcy() {
		return zysxdcy;
	}
	public void setZysxdcy(String zysxdcy) {
		this.zysxdcy = zysxdcy;
	}
	public String getDqqrwsr() {
		return dqqrwsr;
	}
	public void setDqqrwsr(String dqqrwsr) {
		this.dqqrwsr = dqqrwsr;
	}
	public String getYszkqrdsr() {
		return yszkqrdsr;
	}
	public void setYszkqrdsr(String yszkqrdsr) {
		this.yszkqrdsr = yszkqrdsr;
	}
	public String getJsfhbxzcjzdqrdsr() {
		return jsfhbxzcjzdqrdsr;
	}
	public void setJsfhbxzcjzdqrdsr(String jsfhbxzcjzdqrdsr) {
		this.jsfhbxzcjzdqrdsr = jsfhbxzcjzdqrdsr;
	}
	public String getDqqrwyszcdmyqrwfy() {
		return dqqrwyszcdmyqrwfy;
	}
	public void setDqqrwyszcdmyqrwfy(String dqqrwyszcdmyqrwfy) {
		this.dqqrwyszcdmyqrwfy = dqqrwyszcdmyqrwfy;
	}
	public String getZfyfkx() {
		return zfyfkx;
	}
	public void setZfyfkx(String zfyfkx) {
		this.zfyfkx = zfyfkx;
	}
	public String getWqdch() {
		return wqdch;
	}
	public void setWqdch(String wqdch) {
		this.wqdch = wqdch;
	}
	public String getWgjgdzcddzbxzc() {
		return wgjgdzcddzbxzc;
	}
	public void setWgjgdzcddzbxzc(String wgjgdzcddzbxzc) {
		this.wgjgdzcddzbxzc = wgjgdzcddzbxzc;
	}
	public String getChjkbxzc() {
		return chjkbxzc;
	}
	public void setChjkbxzc(String chjkbxzc) {
		this.chjkbxzc = chjkbxzc;
	}
	public String getDqqrwyssrdmyqrwsr() {
		return dqqrwyssrdmyqrwsr;
	}
	public void setDqqrwyssrdmyqrwsr(String dqqrwyssrdmyqrwsr) {
		this.dqqrwyssrdmyqrwsr = dqqrwyssrdmyqrwsr;
	}
	public String getSdyfknx() {
		return sdyfknx;
	}
	public void setSdyfknx(String sdyfknx) {
		this.sdyfknx = sdyfknx;
	}
	public String getQdjkqrdyssr() {
		return qdjkqrdyssr;
	}
	public void setQdjkqrdyssr(String qdjkqrdyssr) {
		this.qdjkqrdyssr = qdjkqrdyssr;
	}
	public String getDqqrwfydmyqrwyszc() {
		return dqqrwfydmyqrwyszc;
	}
	public void setDqqrwfydmyqrwyszc(String dqqrwfydmyqrwyszc) {
		this.dqqrwfydmyqrwyszc = dqqrwfydmyqrwyszc;
	}
	public String getFcch() {
		return fcch;
	}
	public void setFcch(String fcch) {
		this.fcch = fcch;
	}
	public String getJtdzfy() {
		return jtdzfy;
	}
	public void setJtdzfy(String jtdzfy) {
		this.jtdzfy = jtdzfy;
	}
	public String getCzzcjz() {
		return czzcjz;
	}
	public void setCzzcjz(String czzcjz) {
		this.czzcjz = czzcjz;
	}
	public String getYfkx() {
		return yfkx;
	}
	public void setYfkx(String yfkx) {
		this.yfkx = yfkx;
	}
	public String getQtsxcy() {
		return qtsxcy;
	}
	public void setQtsxcy(String qtsxcy) {
		this.qtsxcy = qtsxcy;
	}
	public String getBnyy() {
		return bnyy;
	}
	public void setBnyy(String bnyy) {
		this.bnyy = bnyy;
	}
	
	
	
}
