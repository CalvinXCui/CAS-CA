package com.entity;

import java.io.Serializable;
/****
 * 现金流量报表实体类
 * @author 15176
 *
 */
public class CashReport implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6825395005000185126L;
	
	
	private String id;
//	一、日常活动产生的现金流量：
	//财政基本支出拨款收到的现金
	private String czjbzcsddxj="";
	
	//财政非资本性项目拨款收到的现金
	private String czfzbxxmbksddxj="";
	
	//事业活动收到的除财政拨款以外的现金
	private String syhdsddcczbkywdxj="";
	
	//收到的其他与日常活动有关的现金
	private String sddqtyrchdygdxj="";
	
	//日常活动的现金流入小计
	private String rchddxjlrxj="";
	
	//购买商品、接受劳务支付的现金
	private String gmspjswzfdxj="";
	
	//支付给职工以及为职工支付的现金
	private String zfgzgyjwzgzfdxj="";
	
	//支付的各项税费
	private String zfdgxsf="";
	
	//支付的其他与日常活动有关的现金
	private String zfdqtyrchdygdxj="";
	
	//日常活动的现金流出小计
	private String rchddxjlcxj="";
	
	//日常活动产生的现金流量净额
	private String rchdcsdxjllje="";
	
	
//	二、投资活动产生的现金流量：
	//收回投资收到的现金
	private String shtzsddxj="";
	
	//取得投资收益收到的现金
	private String qdtzsydxj="";
	
	//处置固定资产、无形资产、公共基础设施等收回的现金净额
	private String ggjcssshdje="";
	
	//收到的其他与投资活动有关的现金
	private String ytzhdygdxj="";
	
	//投资活动的现金流入小计
	private String tzhddxjlrxj="";
	
	//购建固定资产、无形资产、公共基础设施等支付的现金
	private String ggjcssdzfdxj="";
	
	//对外投资支付的现金
	private String dwtzzfdxj="";
	
	//上缴处置固定资产、无形资产、公共基础设施等净收入支付的现金
	private String ggjcssdsrzfje="";
	
	//支付的其他与投资活动有关的现金
	private String zfytzhdygdxj="";
	
	//投资活动的现金流出小计
	private String tzhdxjlcxj="";
	
	//投资活动产生的现金流量净额
	private String tzhdcsdxjllje="";
	
	
//	三、筹资活动产生的现金流量：
	//财政资本性项目拨款收到的现金
	private String czzbxxmbksddxj="";
	
	//取得借款收到的现金
	private String qdjksddxj="";
	
	//收到的其他与筹资活动有关的现金
	private String sddqtyczhdygxj="";
	
	//筹资活动的现金流入小计
	private String czhddxjlrxj="";
	
	//偿还借款支付的现金
	private String chjkzfdxj="";
	
	//偿还利息支付的现金
	private String chlxzfdxj="";
	
	//支付的其他与筹资活动有关的现金
	private String zfdqtyczhdygdxj="";
	
	//筹资活动的现金流出小计
	private String czhddxjlcxj="";
	//筹资活动产生的现金流量净额
	private String czhdcsdxjllje="";
	
//	四、汇率变动对现金的影响额
//	五、现金净增加额
	
	
	
	
	
	
	
	
	
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCzjbzcsddxj() {
		return czjbzcsddxj;
	}
	public void setCzjbzcsddxj(String czjbzcsddxj) {
		this.czjbzcsddxj = czjbzcsddxj;
	}
	public String getCzfzbxxmbksddxj() {
		return czfzbxxmbksddxj;
	}
	public void setCzfzbxxmbksddxj(String czfzbxxmbksddxj) {
		this.czfzbxxmbksddxj = czfzbxxmbksddxj;
	}
	public String getSyhdsddcczbkywdxj() {
		return syhdsddcczbkywdxj;
	}
	public void setSyhdsddcczbkywdxj(String syhdsddcczbkywdxj) {
		this.syhdsddcczbkywdxj = syhdsddcczbkywdxj;
	}
	public String getSddqtyrchdygdxj() {
		return sddqtyrchdygdxj;
	}
	public void setSddqtyrchdygdxj(String sddqtyrchdygdxj) {
		this.sddqtyrchdygdxj = sddqtyrchdygdxj;
	}
	public String getRchddxjlrxj() {
		return rchddxjlrxj;
	}
	public void setRchddxjlrxj(String rchddxjlrxj) {
		this.rchddxjlrxj = rchddxjlrxj;
	}
	public String getGmspjswzfdxj() {
		return gmspjswzfdxj;
	}
	public void setGmspjswzfdxj(String gmspjswzfdxj) {
		this.gmspjswzfdxj = gmspjswzfdxj;
	}
	public String getZfgzgyjwzgzfdxj() {
		return zfgzgyjwzgzfdxj;
	}
	public void setZfgzgyjwzgzfdxj(String zfgzgyjwzgzfdxj) {
		this.zfgzgyjwzgzfdxj = zfgzgyjwzgzfdxj;
	}
	public String getZfdgxsf() {
		return zfdgxsf;
	}
	public void setZfdgxsf(String zfdgxsf) {
		this.zfdgxsf = zfdgxsf;
	}
	public String getZfdqtyrchdygdxj() {
		return zfdqtyrchdygdxj;
	}
	public void setZfdqtyrchdygdxj(String zfdqtyrchdygdxj) {
		this.zfdqtyrchdygdxj = zfdqtyrchdygdxj;
	}
	public String getRchddxjlcxj() {
		return rchddxjlcxj;
	}
	public void setRchddxjlcxj(String rchddxjlcxj) {
		this.rchddxjlcxj = rchddxjlcxj;
	}
	public String getRchdcsdxjllje() {
		return rchdcsdxjllje;
	}
	public void setRchdcsdxjllje(String rchdcsdxjllje) {
		this.rchdcsdxjllje = rchdcsdxjllje;
	}
	public String getShtzsddxj() {
		return shtzsddxj;
	}
	public void setShtzsddxj(String shtzsddxj) {
		this.shtzsddxj = shtzsddxj;
	}
	public String getQdtzsydxj() {
		return qdtzsydxj;
	}
	public void setQdtzsydxj(String qdtzsydxj) {
		this.qdtzsydxj = qdtzsydxj;
	}
	public String getGgjcssshdje() {
		return ggjcssshdje;
	}
	public void setGgjcssshdje(String ggjcssshdje) {
		this.ggjcssshdje = ggjcssshdje;
	}
	public String getYtzhdygdxj() {
		return ytzhdygdxj;
	}
	public void setYtzhdygdxj(String ytzhdygdxj) {
		this.ytzhdygdxj = ytzhdygdxj;
	}
	public String getTzhddxjlrxj() {
		return tzhddxjlrxj;
	}
	public void setTzhddxjlrxj(String tzhddxjlrxj) {
		this.tzhddxjlrxj = tzhddxjlrxj;
	}
	public String getGgjcssdzfdxj() {
		return ggjcssdzfdxj;
	}
	public void setGgjcssdzfdxj(String ggjcssdzfdxj) {
		this.ggjcssdzfdxj = ggjcssdzfdxj;
	}
	public String getDwtzzfdxj() {
		return dwtzzfdxj;
	}
	public void setDwtzzfdxj(String dwtzzfdxj) {
		this.dwtzzfdxj = dwtzzfdxj;
	}
	public String getGgjcssdsrzfje() {
		return ggjcssdsrzfje;
	}
	public void setGgjcssdsrzfje(String ggjcssdsrzfje) {
		this.ggjcssdsrzfje = ggjcssdsrzfje;
	}
	public String getZfytzhdygdxj() {
		return zfytzhdygdxj;
	}
	public void setZfytzhdygdxj(String zfytzhdygdxj) {
		this.zfytzhdygdxj = zfytzhdygdxj;
	}
	public String getTzhdxjlcxj() {
		return tzhdxjlcxj;
	}
	public void setTzhdxjlcxj(String tzhdxjlcxj) {
		this.tzhdxjlcxj = tzhdxjlcxj;
	}
	public String getTzhdcsdxjllje() {
		return tzhdcsdxjllje;
	}
	public void setTzhdcsdxjllje(String tzhdcsdxjllje) {
		this.tzhdcsdxjllje = tzhdcsdxjllje;
	}
	public String getCzzbxxmbksddxj() {
		return czzbxxmbksddxj;
	}
	public void setCzzbxxmbksddxj(String czzbxxmbksddxj) {
		this.czzbxxmbksddxj = czzbxxmbksddxj;
	}
	public String getQdjksddxj() {
		return qdjksddxj;
	}
	public void setQdjksddxj(String qdjksddxj) {
		this.qdjksddxj = qdjksddxj;
	}
	public String getSddqtyczhdygxj() {
		return sddqtyczhdygxj;
	}
	public void setSddqtyczhdygxj(String sddqtyczhdygxj) {
		this.sddqtyczhdygxj = sddqtyczhdygxj;
	}
	public String getCzhddxjlrxj() {
		return czhddxjlrxj;
	}
	public void setCzhddxjlrxj(String czhddxjlrxj) {
		this.czhddxjlrxj = czhddxjlrxj;
	}
	public String getChjkzfdxj() {
		return chjkzfdxj;
	}
	public void setChjkzfdxj(String chjkzfdxj) {
		this.chjkzfdxj = chjkzfdxj;
	}
	public String getChlxzfdxj() {
		return chlxzfdxj;
	}
	public void setChlxzfdxj(String chlxzfdxj) {
		this.chlxzfdxj = chlxzfdxj;
	}
	public String getZfdqtyczhdygdxj() {
		return zfdqtyczhdygdxj;
	}
	public void setZfdqtyczhdygdxj(String zfdqtyczhdygdxj) {
		this.zfdqtyczhdygdxj = zfdqtyczhdygdxj;
	}
	public String getCzhddxjlcxj() {
		return czhddxjlcxj;
	}
	public void setCzhddxjlcxj(String czhddxjlcxj) {
		this.czhddxjlcxj = czhddxjlcxj;
	}
	public String getCzhdcsdxjllje() {
		return czhdcsdxjllje;
	}
	public void setCzhdcsdxjllje(String czhdcsdxjllje) {
		this.czhdcsdxjllje = czhdcsdxjllje;
	}
	

	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
