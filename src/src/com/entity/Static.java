package com.entity;

/***
 * 净资产变动
 * @author admin
 *
 */
public class Static {
	private String id="";
	//一、上年年末余额	
	private String snnbye="";
	//二、以前年度盈余调整（ 减少以“-”号填列）		--	--			--	--	
	private String yqndyytz="";
	//三、本年年初余额	
	private String bnncye="";
	//四、本年变动金额（减少以“-”号填列）		--	--			--	--	
	private String bnbdje="";
	//（一）本年盈余		--	--			--	--	
	private String bnyy="";
	///（二）无偿调拨净资产		--	--			--	--	
	private String wcdbjzc="";
	///（三）归集调整预算结转结余		--	--			--	--	
	private String gjtzysjzjy="";
	//（四）提取或设置专用基金			--				--	
	private String tqhszzyjj="";
	//其中：从预算收入中提取	--		--		--		--	
	private String cyssrztq="";
	//从预算结余中提取			--				--	
	private String cysjyztq="";
	//设置的专用基金	--		--				--	
	private String szdzyjj="";
	//（五）使用专用基金		58377.30	--				--	
	private String syzyjj="";
	//（六）权益法调整	--	--			--	--		
	private String qyftz="";
	//五、本年年末余额	-58377.30	-58377.30	0.00	-58377.30				注：“--”标识单元格不需填列。
	private String bnnbye="";
	private String bnnbyezyjj="";
	private String bnnbyezjhj="";
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSnnbye() {
		return snnbye;
	}
	public void setSnnbye(String snnbye) {
		this.snnbye = snnbye;
	}
	public String getYqndyytz() {
		return yqndyytz;
	}
	public void setYqndyytz(String yqndyytz) {
		this.yqndyytz = yqndyytz;
	}
	public String getBnncye() {
		return bnncye;
	}
	public void setBnncye(String bnncye) {
		this.bnncye = bnncye;
	}
	public String getBnbdje() {
		return bnbdje;
	}
	public void setBnbdje(String bnbdje) {
		this.bnbdje = bnbdje;
	}
	public String getBnyy() {
		return bnyy;
	}
	public void setBnyy(String bnyy) {
		this.bnyy = bnyy;
	}
	public String getWcdbjzc() {
		return wcdbjzc;
	}
	public void setWcdbjzc(String wcdbjzc) {
		this.wcdbjzc = wcdbjzc;
	}
	public String getGjtzysjzjy() {
		return gjtzysjzjy;
	}
	public void setGjtzysjzjy(String gjtzysjzjy) {
		this.gjtzysjzjy = gjtzysjzjy;
	}
	public String getTqhszzyjj() {
		return tqhszzyjj;
	}
	public void setTqhszzyjj(String tqhszzyjj) {
		this.tqhszzyjj = tqhszzyjj;
	}
	public String getCyssrztq() {
		return cyssrztq;
	}
	public void setCyssrztq(String cyssrztq) {
		this.cyssrztq = cyssrztq;
	}
	public String getCysjyztq() {
		return cysjyztq;
	}
	public void setCysjyztq(String cysjyztq) {
		this.cysjyztq = cysjyztq;
	}
	public String getSzdzyjj() {
		return szdzyjj;
	}
	public void setSzdzyjj(String szdzyjj) {
		this.szdzyjj = szdzyjj;
	}
	public String getSyzyjj() {
		return syzyjj;
	}
	public void setSyzyjj(String syzyjj) {
		this.syzyjj = syzyjj;
	}
	public String getQyftz() {
		return qyftz;
	}
	public void setQyftz(String qyftz) {
		this.qyftz = qyftz;
	}
	public String getBnnbye() {
		return bnnbye;
	}
	public void setBnnbye(String bnnbye) {
		this.bnnbye = bnnbye;
	}
	public String getBnnbyezyjj() {
		return bnnbyezyjj;
	}
	public void setBnnbyezyjj(String bnnbyezyjj) {
		this.bnnbyezyjj = bnnbyezyjj;
	}
	public String getBnnbyezjhj() {
		return bnnbyezjhj;
	}
	public void setBnnbyezjhj(String bnnbyezjhj) {
		this.bnnbyezjhj = bnnbyezjhj;
	}

}
