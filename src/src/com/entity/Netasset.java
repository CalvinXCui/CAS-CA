package com.entity;


import java.text.SimpleDateFormat;
import java.util.Date;

/***
 * 净资产变动
 * @author admin
 *
 */
public class Netasset {
	//一、上年年末余额累计盈余	专用基金	权益法调整	资金产合计
	private String snnmye="";
	private String snnmyeljyy="";
	private String snnmyezyjj="";
	private String snnmyeqyftz="";
	private String snnmyezjchj="";
	private String snnmyesn="";
	private String snnmyesnljyy="";
	private String snnmyesnzyjj="";
	private String snnmyesnqyftz="";
	//二、以前年度盈余调整（ 减少以“-”号填列）		--	--			--	--	
	private String yqndyytz="";
	private String yqndyytzljyy="";   
	private String yqndyytzzyjj="--";   
	private String yqndyytzqyftz="--";  
	private String yqndyytzzjchj="";  
	private String yqndyytzsn="";     
	private String yqndyytzsnljyy=""; 
	private String yqndyytzsnzyjj="--"; 
	private String yqndyytzsnqyftz="--";
	//三、本年年初余额		
	private String bnncye="";       
	private String bnncyeljyy="";   
	private String bnncyezyjj="";   
	private String bnncyeqyftz="";  
	private String bnncyezjchj="";  
	private String bnncyesn="";     
	private String bnncyesnljyy=""; 
	private String bnncyesnzyjj=""; 
	private String bnncyesnqyftz="";
	//四、本年变动金额（减少以“-”号填列）		--	--			--	--	
	private String bnbdje="";       
	private String bnbdjeljyy="";   
	private String bnbdjezyjj="--";   
	private String bnbdjeqyftz="--";  
	private String bnbdjezjchj="";  
	private String bnbdjesn="";     
	private String bnbdjesnljyy=""; 
	private String bnbdjesnzyjj="--"; 
	private String bnbdjesnqyftz="--";
	//（一）本年盈余		--	--			--	--	
	private String bnyy="";       
	private String bnyyljyy="";   
	private String bnyyzyjj="--";   
	private String bnyyqyftz="--";  
	private String bnyyzjchj="";  
	private String bnyysn="";     
	private String bnyysnljyy=""; 
	private String bnyysnzyjj="--"; 
	private String bnyysnqyftz="--";
	//（二）无偿调拨净资产		--	--			--	--	
	private String wctbjzc="";       
	private String wctbjzcljyy="";   
	private String wctbjzczyjj="--";   
	private String wctbjzcqyftz="--";  
	private String wctbjzczjchj="";  
	private String wctbjzcsn="";     
	private String wctbjzcsnljyy=""; 
	private String wctbjzcsnzyjj="--"; 
	private String wctbjzcsnqyftz="--";
	//（三）归集调整预算结转结余		--	--			--	--	
	private String gjtzysjzjy="";       
	private String gjtzysjzjyljyy="";   
	private String gjtzysjzjyzyjj="--";   
	private String gjtzysjzjyqyftz="--";  
	private String gjtzysjzjyzjchj="";  
	private String gjtzysjzjysn="";     
	private String gjtzysjzjysnljyy=""; 
	private String gjtzysjzjysnzyjj="--"; 
	private String gjtzysjzjysnqyftz="--";
	//（四）提取或设置专用基金			--				--	
	private String tqhszzyjj="";       
	private String tqhszzyjjljyy="";   
	private String tqhszzyjjzyjj="";   
	private String tqhszzyjjqyftz="--";  
	private String tqhszzyjjzjchj="";  
	private String tqhszzyjjsn="";     
	private String tqhszzyjjsnljyy=""; 
	private String tqhszzyjjsnzyjj=""; 
	private String tqhszzyjjsnqyftz="--";
	//其中：从预算收入中提取	--		--		--		--	
	private String cyssrztq="";       
	private String cyssrztqljyy="--";   
	private String cyssrztqzyjj="";   
	private String cyssrztqqyftz="--";  
	private String cyssrztqzjchj="";  
	private String cyssrztqsn="";     
	private String cyssrztqsnljyy="--"; 
	private String cyssrztqsnzyjj=""; 
	private String cyssrztqsnqyftz="--";
	//从预算结余中提取			--				--	
	private String cysjyztq="";       
	private String cysjyztqljyy="";   
	private String cysjyztqzyjj="";   
	private String cysjyztqqyftz="--";  
	private String cysjyztqzjchj="";  
	private String cysjyztqsn="";     
	private String cysjyztqsnljyy=""; 
	private String cysjyztqsnzyjj=""; 
	private String cysjyztqsnqyftz="--";
	//设置的专用基金	--		--				--	
	private String szdzyjj="";       
	private String szdzyjjljyy="--";   
	private String szdzyjjzyjj="";   
	private String szdzyjjqyftz="--";  
	private String szdzyjjzjchj="";  
	private String szdzyjjsn="";     
	private String szdzyjjsnljyy="--"; 
	private String szdzyjjsnzyjj=""; 
	private String szdzyjjsnqyftz="--";
	//（五）使用专用基金	--		--				--	
	private String syzyjj="";       
	private String syzyjjljyy="";   
	private String syzyjjzyjj="--";   
	private String syzyjjqyftz="--";  
	private String syzyjjzjchj="";  
	private String syzyjjsn="";     
	private String syzyjjsnljyy=""; 
	private String syzyjjsnzyjj="--"; 
	private String syzyjjsnqyftz="--";
	//（六）权益法调整	--	--			--	--		
	private String qyftz="";       
	private String qyftzljyy="--";   
	private String qyftzzyjj="--";   
	private String qyftzqyftz="";  
	private String qyftzzjchj="";  
	private String qyftzsn="";     
	private String qyftzsnljyy="--"; 
	private String qyftzsnzyjj="--"; 
	private String qyftzsnqyftz="";
	//五、本年年末余额								注：“--”标识单元格不需填列。
	private String bnnmye="";       
	private String bnnmyeljyy="";   
	private String bnnmyezyjj="";   
	private String bnnmyeqyftz="";  
	private String bnnmyezjchj="";  
	private String bnnmyesn="";     
	private String bnnmyesnljyy=""; 
	private String bnnmyesnzyjj=""; 
	private String bnnmyesnqyftz="";

	private String AAA="________";
	private String BBB="";
	private String CCC="";
	private String DDD="";

	public String getAAA() {
		return AAA;
	}

	public void setAAA(String AAA) {
		this.AAA = AAA;
	}

	public String getBBB() {
		return BBB;
	}

	public void setBBB(String BBB) {
		this.BBB = BBB;
	}

	public String getCCC() {
		return CCC;
	}

	public void setCCC(String CCC) {
		this.CCC = CCC;
	}

	public String getDDD() {
		return DDD;
	}

	public void setDDD(String DDD) {
		this.DDD = DDD;
	}

	public String getSnnmye() {
		return snnmye;
	}
	public void setSnnmye(String snnmye) {
		this.snnmye = snnmye;
	}
	public String getSnnmyeljyy() {
		return snnmyeljyy;
	}
	public void setSnnmyeljyy(String snnmyeljyy) {
		this.snnmyeljyy = snnmyeljyy;
	}
	public String getSnnmyezyjj() {
		return snnmyezyjj;
	}
	public void setSnnmyezyjj(String snnmyezyjj) {
		this.snnmyezyjj = snnmyezyjj;
	}
	public String getSnnmyeqyftz() {
		return snnmyeqyftz;
	}
	public void setSnnmyeqyftz(String snnmyeqyftz) {
		this.snnmyeqyftz = snnmyeqyftz;
	}
	public String getSnnmyezjchj() {
		return snnmyezjchj;
	}
	public void setSnnmyezjchj(String snnmyezjchj) {
		this.snnmyezjchj = snnmyezjchj;
	}
	public String getSnnmyesn() {
		return snnmyesn;
	}
	public void setSnnmyesn(String snnmyesn) {
		this.snnmyesn = snnmyesn;
	}
	public String getSnnmyesnljyy() {
		return snnmyesnljyy;
	}
	public void setSnnmyesnljyy(String snnmyesnljyy) {
		this.snnmyesnljyy = snnmyesnljyy;
	}
	public String getSnnmyesnzyjj() {
		return snnmyesnzyjj;
	}
	public void setSnnmyesnzyjj(String snnmyesnzyjj) {
		this.snnmyesnzyjj = snnmyesnzyjj;
	}
	public String getSnnmyesnqyftz() {
		return snnmyesnqyftz;
	}
	public void setSnnmyesnqyftz(String snnmyesnqyftz) {
		this.snnmyesnqyftz = snnmyesnqyftz;
	}
	public String getYqndyytz() {
		return yqndyytz;
	}
	public void setYqndyytz(String yqndyytz) {
		this.yqndyytz = yqndyytz;
	}
	public String getYqndyytzljyy() {
		return yqndyytzljyy;
	}
	public void setYqndyytzljyy(String yqndyytzljyy) {
		this.yqndyytzljyy = yqndyytzljyy;
	}
	public String getYqndyytzzyjj() {
		return yqndyytzzyjj;
	}
	public void setYqndyytzzyjj(String yqndyytzzyjj) {
		this.yqndyytzzyjj = yqndyytzzyjj;
	}
	public String getYqndyytzqyftz() {
		return yqndyytzqyftz;
	}
	public void setYqndyytzqyftz(String yqndyytzqyftz) {
		this.yqndyytzqyftz = yqndyytzqyftz;
	}
	public String getYqndyytzzjchj() {
		return yqndyytzzjchj;
	}
	public void setYqndyytzzjchj(String yqndyytzzjchj) {
		this.yqndyytzzjchj = yqndyytzzjchj;
	}
	public String getYqndyytzsn() {
		return yqndyytzsn;
	}
	public void setYqndyytzsn(String yqndyytzsn) {
		this.yqndyytzsn = yqndyytzsn;
	}
	public String getYqndyytzsnljyy() {
		return yqndyytzsnljyy;
	}
	public void setYqndyytzsnljyy(String yqndyytzsnljyy) {
		this.yqndyytzsnljyy = yqndyytzsnljyy;
	}
	public String getYqndyytzsnzyjj() {
		return yqndyytzsnzyjj;
	}
	public void setYqndyytzsnzyjj(String yqndyytzsnzyjj) {
		this.yqndyytzsnzyjj = yqndyytzsnzyjj;
	}
	public String getYqndyytzsnqyftz() {
		return yqndyytzsnqyftz;
	}
	public void setYqndyytzsnqyftz(String yqndyytzsnqyftz) {
		this.yqndyytzsnqyftz = yqndyytzsnqyftz;
	}
	public String getBnncye() {
		return bnncye;
	}
	public void setBnncye(String bnncye) {
		this.bnncye = bnncye;
	}
	public String getBnncyeljyy() {
		return bnncyeljyy;
	}
	public void setBnncyeljyy(String bnncyeljyy) {
		this.bnncyeljyy = bnncyeljyy;
	}
	public String getBnncyezyjj() {
		return bnncyezyjj;
	}
	public void setBnncyezyjj(String bnncyezyjj) {
		this.bnncyezyjj = bnncyezyjj;
	}
	public String getBnncyeqyftz() {
		return bnncyeqyftz;
	}
	public void setBnncyeqyftz(String bnncyeqyftz) {
		this.bnncyeqyftz = bnncyeqyftz;
	}
	public String getBnncyezjchj() {
		return bnncyezjchj;
	}
	public void setBnncyezjchj(String bnncyezjchj) {
		this.bnncyezjchj = bnncyezjchj;
	}
	public String getBnncyesn() {
		return bnncyesn;
	}
	public void setBnncyesn(String bnncyesn) {
		this.bnncyesn = bnncyesn;
	}
	public String getBnncyesnljyy() {
		return bnncyesnljyy;
	}
	public void setBnncyesnljyy(String bnncyesnljyy) {
		this.bnncyesnljyy = bnncyesnljyy;
	}
	public String getBnncyesnzyjj() {
		return bnncyesnzyjj;
	}
	public void setBnncyesnzyjj(String bnncyesnzyjj) {
		this.bnncyesnzyjj = bnncyesnzyjj;
	}
	public String getBnncyesnqyftz() {
		return bnncyesnqyftz;
	}
	public void setBnncyesnqyftz(String bnncyesnqyftz) {
		this.bnncyesnqyftz = bnncyesnqyftz;
	}
	public String getBnbdje() {
		return bnbdje;
	}
	public void setBnbdje(String bnbdje) {
		this.bnbdje = bnbdje;
	}
	public String getBnbdjeljyy() {
		return bnbdjeljyy;
	}
	public void setBnbdjeljyy(String bnbdjeljyy) {
		this.bnbdjeljyy = bnbdjeljyy;
	}
	public String getBnbdjezyjj() {
		return bnbdjezyjj;
	}
	public void setBnbdjezyjj(String bnbdjezyjj) {
		this.bnbdjezyjj = bnbdjezyjj;
	}
	public String getBnbdjeqyftz() {
		return bnbdjeqyftz;
	}
	public void setBnbdjeqyftz(String bnbdjeqyftz) {
		this.bnbdjeqyftz = bnbdjeqyftz;
	}
	public String getBnbdjezjchj() {
		return bnbdjezjchj;
	}
	public void setBnbdjezjchj(String bnbdjezjchj) {
		this.bnbdjezjchj = bnbdjezjchj;
	}
	public String getBnbdjesn() {
		return bnbdjesn;
	}
	public void setBnbdjesn(String bnbdjesn) {
		this.bnbdjesn = bnbdjesn;
	}
	public String getBnbdjesnljyy() {
		return bnbdjesnljyy;
	}
	public void setBnbdjesnljyy(String bnbdjesnljyy) {
		this.bnbdjesnljyy = bnbdjesnljyy;
	}
	public String getBnbdjesnzyjj() {
		return bnbdjesnzyjj;
	}
	public void setBnbdjesnzyjj(String bnbdjesnzyjj) {
		this.bnbdjesnzyjj = bnbdjesnzyjj;
	}
	public String getBnbdjesnqyftz() {
		return bnbdjesnqyftz;
	}
	public void setBnbdjesnqyftz(String bnbdjesnqyftz) {
		this.bnbdjesnqyftz = bnbdjesnqyftz;
	}
	public String getBnyy() {
		return bnyy;
	}
	public void setBnyy(String bnyy) {
		this.bnyy = bnyy;
	}
	public String getBnyyljyy() {
		return bnyyljyy;
	}
	public void setBnyyljyy(String bnyyljyy) {
		this.bnyyljyy = bnyyljyy;
	}
	public String getBnyyzyjj() {
		return bnyyzyjj;
	}
	public void setBnyyzyjj(String bnyyzyjj) {
		this.bnyyzyjj = bnyyzyjj;
	}
	public String getBnyyqyftz() {
		return bnyyqyftz;
	}
	public void setBnyyqyftz(String bnyyqyftz) {
		this.bnyyqyftz = bnyyqyftz;
	}
	public String getBnyyzjchj() {
		return bnyyzjchj;
	}
	public void setBnyyzjchj(String bnyyzjchj) {
		this.bnyyzjchj = bnyyzjchj;
	}
	public String getBnyysn() {
		return bnyysn;
	}
	public void setBnyysn(String bnyysn) {
		this.bnyysn = bnyysn;
	}
	public String getBnyysnljyy() {
		return bnyysnljyy;
	}
	public void setBnyysnljyy(String bnyysnljyy) {
		this.bnyysnljyy = bnyysnljyy;
	}
	public String getBnyysnzyjj() {
		return bnyysnzyjj;
	}
	public void setBnyysnzyjj(String bnyysnzyjj) {
		this.bnyysnzyjj = bnyysnzyjj;
	}
	public String getBnyysnqyftz() {
		return bnyysnqyftz;
	}
	public void setBnyysnqyftz(String bnyysnqyftz) {
		this.bnyysnqyftz = bnyysnqyftz;
	}
	public String getWctbjzc() {
		return wctbjzc;
	}
	public void setWctbjzc(String wctbjzc) {
		this.wctbjzc = wctbjzc;
	}
	public String getWctbjzcljyy() {
		return wctbjzcljyy;
	}
	public void setWctbjzcljyy(String wctbjzcljyy) {
		this.wctbjzcljyy = wctbjzcljyy;
	}
	public String getWctbjzczyjj() {
		return wctbjzczyjj;
	}
	public void setWctbjzczyjj(String wctbjzczyjj) {
		this.wctbjzczyjj = wctbjzczyjj;
	}
	public String getWctbjzcqyftz() {
		return wctbjzcqyftz;
	}
	public void setWctbjzcqyftz(String wctbjzcqyftz) {
		this.wctbjzcqyftz = wctbjzcqyftz;
	}
	public String getWctbjzczjchj() {
		return wctbjzczjchj;
	}
	public void setWctbjzczjchj(String wctbjzczjchj) {
		this.wctbjzczjchj = wctbjzczjchj;
	}
	public String getWctbjzcsn() {
		return wctbjzcsn;
	}
	public void setWctbjzcsn(String wctbjzcsn) {
		this.wctbjzcsn = wctbjzcsn;
	}
	public String getWctbjzcsnljyy() {
		return wctbjzcsnljyy;
	}
	public void setWctbjzcsnljyy(String wctbjzcsnljyy) {
		this.wctbjzcsnljyy = wctbjzcsnljyy;
	}
	public String getWctbjzcsnzyjj() {
		return wctbjzcsnzyjj;
	}
	public void setWctbjzcsnzyjj(String wctbjzcsnzyjj) {
		this.wctbjzcsnzyjj = wctbjzcsnzyjj;
	}
	public String getWctbjzcsnqyftz() {
		return wctbjzcsnqyftz;
	}
	public void setWctbjzcsnqyftz(String wctbjzcsnqyftz) {
		this.wctbjzcsnqyftz = wctbjzcsnqyftz;
	}
	public String getGjtzysjzjy() {
		return gjtzysjzjy;
	}
	public void setGjtzysjzjy(String gjtzysjzjy) {
		this.gjtzysjzjy = gjtzysjzjy;
	}
	public String getGjtzysjzjyljyy() {
		return gjtzysjzjyljyy;
	}
	public void setGjtzysjzjyljyy(String gjtzysjzjyljyy) {
		this.gjtzysjzjyljyy = gjtzysjzjyljyy;
	}
	public String getGjtzysjzjyzyjj() {
		return gjtzysjzjyzyjj;
	}
	public void setGjtzysjzjyzyjj(String gjtzysjzjyzyjj) {
		this.gjtzysjzjyzyjj = gjtzysjzjyzyjj;
	}
	public String getGjtzysjzjyqyftz() {
		return gjtzysjzjyqyftz;
	}
	public void setGjtzysjzjyqyftz(String gjtzysjzjyqyftz) {
		this.gjtzysjzjyqyftz = gjtzysjzjyqyftz;
	}
	public String getGjtzysjzjyzjchj() {
		return gjtzysjzjyzjchj;
	}
	public void setGjtzysjzjyzjchj(String gjtzysjzjyzjchj) {
		this.gjtzysjzjyzjchj = gjtzysjzjyzjchj;
	}
	public String getGjtzysjzjysn() {
		return gjtzysjzjysn;
	}
	public void setGjtzysjzjysn(String gjtzysjzjysn) {
		this.gjtzysjzjysn = gjtzysjzjysn;
	}
	public String getGjtzysjzjysnljyy() {
		return gjtzysjzjysnljyy;
	}
	public void setGjtzysjzjysnljyy(String gjtzysjzjysnljyy) {
		this.gjtzysjzjysnljyy = gjtzysjzjysnljyy;
	}
	public String getGjtzysjzjysnzyjj() {
		return gjtzysjzjysnzyjj;
	}
	public void setGjtzysjzjysnzyjj(String gjtzysjzjysnzyjj) {
		this.gjtzysjzjysnzyjj = gjtzysjzjysnzyjj;
	}
	public String getGjtzysjzjysnqyftz() {
		return gjtzysjzjysnqyftz;
	}
	public void setGjtzysjzjysnqyftz(String gjtzysjzjysnqyftz) {
		this.gjtzysjzjysnqyftz = gjtzysjzjysnqyftz;
	}
	public String getTqhszzyjj() {
		return tqhszzyjj;
	}
	public void setTqhszzyjj(String tqhszzyjj) {
		this.tqhszzyjj = tqhszzyjj;
	}
	public String getTqhszzyjjljyy() {
		return tqhszzyjjljyy;
	}
	public void setTqhszzyjjljyy(String tqhszzyjjljyy) {
		this.tqhszzyjjljyy = tqhszzyjjljyy;
	}
	public String getTqhszzyjjzyjj() {
		return tqhszzyjjzyjj;
	}
	public void setTqhszzyjjzyjj(String tqhszzyjjzyjj) {
		this.tqhszzyjjzyjj = tqhszzyjjzyjj;
	}
	public String getTqhszzyjjqyftz() {
		return tqhszzyjjqyftz;
	}
	public void setTqhszzyjjqyftz(String tqhszzyjjqyftz) {
		this.tqhszzyjjqyftz = tqhszzyjjqyftz;
	}
	public String getTqhszzyjjzjchj() {
		return tqhszzyjjzjchj;
	}
	public void setTqhszzyjjzjchj(String tqhszzyjjzjchj) {
		this.tqhszzyjjzjchj = tqhszzyjjzjchj;
	}
	public String getTqhszzyjjsn() {
		return tqhszzyjjsn;
	}
	public void setTqhszzyjjsn(String tqhszzyjjsn) {
		this.tqhszzyjjsn = tqhszzyjjsn;
	}
	public String getTqhszzyjjsnljyy() {
		return tqhszzyjjsnljyy;
	}
	public void setTqhszzyjjsnljyy(String tqhszzyjjsnljyy) {
		this.tqhszzyjjsnljyy = tqhszzyjjsnljyy;
	}
	public String getTqhszzyjjsnzyjj() {
		return tqhszzyjjsnzyjj;
	}
	public void setTqhszzyjjsnzyjj(String tqhszzyjjsnzyjj) {
		this.tqhszzyjjsnzyjj = tqhszzyjjsnzyjj;
	}
	public String getTqhszzyjjsnqyftz() {
		return tqhszzyjjsnqyftz;
	}
	public void setTqhszzyjjsnqyftz(String tqhszzyjjsnqyftz) {
		this.tqhszzyjjsnqyftz = tqhszzyjjsnqyftz;
	}
	public String getCyssrztq() {
		return cyssrztq;
	}
	public void setCyssrztq(String cyssrztq) {
		this.cyssrztq = cyssrztq;
	}
	public String getCyssrztqljyy() {
		return cyssrztqljyy;
	}
	public void setCyssrztqljyy(String cyssrztqljyy) {
		this.cyssrztqljyy = cyssrztqljyy;
	}
	public String getCyssrztqzyjj() {
		return cyssrztqzyjj;
	}
	public void setCyssrztqzyjj(String cyssrztqzyjj) {
		this.cyssrztqzyjj = cyssrztqzyjj;
	}
	public String getCyssrztqqyftz() {
		return cyssrztqqyftz;
	}
	public void setCyssrztqqyftz(String cyssrztqqyftz) {
		this.cyssrztqqyftz = cyssrztqqyftz;
	}
	public String getCyssrztqzjchj() {
		return cyssrztqzjchj;
	}
	public void setCyssrztqzjchj(String cyssrztqzjchj) {
		this.cyssrztqzjchj = cyssrztqzjchj;
	}
	public String getCyssrztqsn() {
		return cyssrztqsn;
	}
	public void setCyssrztqsn(String cyssrztqsn) {
		this.cyssrztqsn = cyssrztqsn;
	}
	public String getCyssrztqsnljyy() {
		return cyssrztqsnljyy;
	}
	public void setCyssrztqsnljyy(String cyssrztqsnljyy) {
		this.cyssrztqsnljyy = cyssrztqsnljyy;
	}
	public String getCyssrztqsnzyjj() {
		return cyssrztqsnzyjj;
	}
	public void setCyssrztqsnzyjj(String cyssrztqsnzyjj) {
		this.cyssrztqsnzyjj = cyssrztqsnzyjj;
	}
	public String getCyssrztqsnqyftz() {
		return cyssrztqsnqyftz;
	}
	public void setCyssrztqsnqyftz(String cyssrztqsnqyftz) {
		this.cyssrztqsnqyftz = cyssrztqsnqyftz;
	}
	public String getCysjyztq() {
		return cysjyztq;
	}
	public void setCysjyztq(String cysjyztq) {
		this.cysjyztq = cysjyztq;
	}
	public String getCysjyztqljyy() {
		return cysjyztqljyy;
	}
	public void setCysjyztqljyy(String cysjyztqljyy) {
		this.cysjyztqljyy = cysjyztqljyy;
	}
	public String getCysjyztqzyjj() {
		return cysjyztqzyjj;
	}
	public void setCysjyztqzyjj(String cysjyztqzyjj) {
		this.cysjyztqzyjj = cysjyztqzyjj;
	}
	public String getCysjyztqqyftz() {
		return cysjyztqqyftz;
	}
	public void setCysjyztqqyftz(String cysjyztqqyftz) {
		this.cysjyztqqyftz = cysjyztqqyftz;
	}
	public String getCysjyztqzjchj() {
		return cysjyztqzjchj;
	}
	public void setCysjyztqzjchj(String cysjyztqzjchj) {
		this.cysjyztqzjchj = cysjyztqzjchj;
	}
	public String getCysjyztqsn() {
		return cysjyztqsn;
	}
	public void setCysjyztqsn(String cysjyztqsn) {
		this.cysjyztqsn = cysjyztqsn;
	}
	public String getCysjyztqsnljyy() {
		return cysjyztqsnljyy;
	}
	public void setCysjyztqsnljyy(String cysjyztqsnljyy) {
		this.cysjyztqsnljyy = cysjyztqsnljyy;
	}
	public String getCysjyztqsnzyjj() {
		return cysjyztqsnzyjj;
	}
	public void setCysjyztqsnzyjj(String cysjyztqsnzyjj) {
		this.cysjyztqsnzyjj = cysjyztqsnzyjj;
	}
	public String getCysjyztqsnqyftz() {
		return cysjyztqsnqyftz;
	}
	public void setCysjyztqsnqyftz(String cysjyztqsnqyftz) {
		this.cysjyztqsnqyftz = cysjyztqsnqyftz;
	}
	public String getSzdzyjj() {
		return szdzyjj;
	}
	public void setSzdzyjj(String szdzyjj) {
		this.szdzyjj = szdzyjj;
	}
	public String getSzdzyjjljyy() {
		return szdzyjjljyy;
	}
	public void setSzdzyjjljyy(String szdzyjjljyy) {
		this.szdzyjjljyy = szdzyjjljyy;
	}
	public String getSzdzyjjzyjj() {
		return szdzyjjzyjj;
	}
	public void setSzdzyjjzyjj(String szdzyjjzyjj) {
		this.szdzyjjzyjj = szdzyjjzyjj;
	}
	public String getSzdzyjjqyftz() {
		return szdzyjjqyftz;
	}
	public void setSzdzyjjqyftz(String szdzyjjqyftz) {
		this.szdzyjjqyftz = szdzyjjqyftz;
	}
	public String getSzdzyjjzjchj() {
		return szdzyjjzjchj;
	}
	public void setSzdzyjjzjchj(String szdzyjjzjchj) {
		this.szdzyjjzjchj = szdzyjjzjchj;
	}
	public String getSzdzyjjsn() {
		return szdzyjjsn;
	}
	public void setSzdzyjjsn(String szdzyjjsn) {
		this.szdzyjjsn = szdzyjjsn;
	}
	public String getSzdzyjjsnljyy() {
		return szdzyjjsnljyy;
	}
	public void setSzdzyjjsnljyy(String szdzyjjsnljyy) {
		this.szdzyjjsnljyy = szdzyjjsnljyy;
	}
	public String getSzdzyjjsnzyjj() {
		return szdzyjjsnzyjj;
	}
	public void setSzdzyjjsnzyjj(String szdzyjjsnzyjj) {
		this.szdzyjjsnzyjj = szdzyjjsnzyjj;
	}
	public String getSzdzyjjsnqyftz() {
		return szdzyjjsnqyftz;
	}
	public void setSzdzyjjsnqyftz(String szdzyjjsnqyftz) {
		this.szdzyjjsnqyftz = szdzyjjsnqyftz;
	}
	public String getSyzyjj() {
		return syzyjj;
	}
	public void setSyzyjj(String syzyjj) {
		this.syzyjj = syzyjj;
	}
	public String getSyzyjjljyy() {
		return syzyjjljyy;
	}
	public void setSyzyjjljyy(String syzyjjljyy) {
		this.syzyjjljyy = syzyjjljyy;
	}
	public String getSyzyjjzyjj() {
		return syzyjjzyjj;
	}
	public void setSyzyjjzyjj(String syzyjjzyjj) {
		this.syzyjjzyjj = syzyjjzyjj;
	}
	public String getSyzyjjqyftz() {
		return syzyjjqyftz;
	}
	public void setSyzyjjqyftz(String syzyjjqyftz) {
		this.syzyjjqyftz = syzyjjqyftz;
	}
	public String getSyzyjjzjchj() {
		return syzyjjzjchj;
	}
	public void setSyzyjjzjchj(String syzyjjzjchj) {
		this.syzyjjzjchj = syzyjjzjchj;
	}
	public String getSyzyjjsn() {
		return syzyjjsn;
	}
	public void setSyzyjjsn(String syzyjjsn) {
		this.syzyjjsn = syzyjjsn;
	}
	public String getSyzyjjsnljyy() {
		return syzyjjsnljyy;
	}
	public void setSyzyjjsnljyy(String syzyjjsnljyy) {
		this.syzyjjsnljyy = syzyjjsnljyy;
	}
	public String getSyzyjjsnzyjj() {
		return syzyjjsnzyjj;
	}
	public void setSyzyjjsnzyjj(String syzyjjsnzyjj) {
		this.syzyjjsnzyjj = syzyjjsnzyjj;
	}
	public String getSyzyjjsnqyftz() {
		return syzyjjsnqyftz;
	}
	public void setSyzyjjsnqyftz(String syzyjjsnqyftz) {
		this.syzyjjsnqyftz = syzyjjsnqyftz;
	}
	public String getQyftz() {
		return qyftz;
	}
	public void setQyftz(String qyftz) {
		this.qyftz = qyftz;
	}
	public String getQyftzljyy() {
		return qyftzljyy;
	}
	public void setQyftzljyy(String qyftzljyy) {
		this.qyftzljyy = qyftzljyy;
	}
	public String getQyftzzyjj() {
		return qyftzzyjj;
	}
	public void setQyftzzyjj(String qyftzzyjj) {
		this.qyftzzyjj = qyftzzyjj;
	}
	public String getQyftzqyftz() {
		return qyftzqyftz;
	}
	public void setQyftzqyftz(String qyftzqyftz) {
		this.qyftzqyftz = qyftzqyftz;
	}
	public String getQyftzzjchj() {
		return qyftzzjchj;
	}
	public void setQyftzzjchj(String qyftzzjchj) {
		this.qyftzzjchj = qyftzzjchj;
	}
	public String getQyftzsn() {
		return qyftzsn;
	}
	public void setQyftzsn(String qyftzsn) {
		this.qyftzsn = qyftzsn;
	}
	public String getQyftzsnljyy() {
		return qyftzsnljyy;
	}
	public void setQyftzsnljyy(String qyftzsnljyy) {
		this.qyftzsnljyy = qyftzsnljyy;
	}
	public String getQyftzsnzyjj() {
		return qyftzsnzyjj;
	}
	public void setQyftzsnzyjj(String qyftzsnzyjj) {
		this.qyftzsnzyjj = qyftzsnzyjj;
	}
	public String getQyftzsnqyftz() {
		return qyftzsnqyftz;
	}
	public void setQyftzsnqyftz(String qyftzsnqyftz) {
		this.qyftzsnqyftz = qyftzsnqyftz;
	}
	public String getBnnmye() {
		return bnnmye;
	}
	public void setBnnmye(String bnnmye) {
		this.bnnmye = bnnmye;
	}
	public String getBnnmyeljyy() {
		return bnnmyeljyy;
	}
	public void setBnnmyeljyy(String bnnmyeljyy) {
		this.bnnmyeljyy = bnnmyeljyy;
	}
	public String getBnnmyezyjj() {
		return bnnmyezyjj;
	}
	public void setBnnmyezyjj(String bnnmyezyjj) {
		this.bnnmyezyjj = bnnmyezyjj;
	}
	public String getBnnmyeqyftz() {
		return bnnmyeqyftz;
	}
	public void setBnnmyeqyftz(String bnnmyeqyftz) {
		this.bnnmyeqyftz = bnnmyeqyftz;
	}
	public String getBnnmyezjchj() {
		return bnnmyezjchj;
	}
	public void setBnnmyezjchj(String bnnmyezjchj) {
		this.bnnmyezjchj = bnnmyezjchj;
	}
	public String getBnnmyesn() {
		return bnnmyesn;
	}
	public void setBnnmyesn(String bnnmyesn) {
		this.bnnmyesn = bnnmyesn;
	}
	public String getBnnmyesnljyy() {
		return bnnmyesnljyy;
	}
	public void setBnnmyesnljyy(String bnnmyesnljyy) {
		this.bnnmyesnljyy = bnnmyesnljyy;
	}
	public String getBnnmyesnzyjj() {
		return bnnmyesnzyjj;
	}
	public void setBnnmyesnzyjj(String bnnmyesnzyjj) {
		this.bnnmyesnzyjj = bnnmyesnzyjj;
	}
	public String getBnnmyesnqyftz() {
		return bnnmyesnqyftz;
	}
	public void setBnnmyesnqyftz(String bnnmyesnqyftz) {
		this.bnnmyesnqyftz = bnnmyesnqyftz;
	}
	
	
	
	
}
