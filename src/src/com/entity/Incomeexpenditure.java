package com.entity;

public class Incomeexpenditure {
	private String id="";
	//一、本期预算收入		
	private String bqyssr="";
	//去年
	private String bqyssrqn="";
	//(一) 财政拨款预算收入
	private String czbkyssr="";
	////去年
	private String czbkyssrqn="";
	//其中：政府性基金收入		
	private String zfxjjsr="";
	//去年
	private String zfxjjsrqn= "";
	//(二) 事业预算收入		
	private String syyssr="";
	//去年
	private String syyssrqn="";
	//(三) 上级补助预算收入	
	private String sjfzyssr="";
	//去年
	private String sjfzyssrqn="";
	//(四) 附属单位上缴预算收入
	private String fsdwsjyssr="";
	//去年
	private  String fsdwsjyssrqn="";
	//(五) 经营预算收入		
	private String jyyssr="";
	//去年
	private String jyyssrqn= "";
	//(六) 债务预算收入		
	private String zwyssr="";
	//去年
	private  String zwyssrqn="";
	//(七) 非同级财政拨款预算收入
	private String ftjczbkyssr="";
	//去年
	private String ftjczbkyssrqn="";
	//(八) 投资预算收益		
	private String tzyssy="";
	//去年
	private String tzyssyqn="";
	//(九) 其他预算收入		
	private String qtyssr="";
	//去年
	private String qtyssrqn="";
	//其中：利息预算收入		
	private String lxyssr="";
	//去年
	private String lxyssrqn="";
	//捐赠预算收入		
	private String jzyssr="";
	//去年
	private String jzyssrqn ="";

	/**
	 * 租金预算收入
	 */
	private String zjyssr="";
	//去年
	private String zjyssrqn="";
	//二、本年预算支出		
	private String bnyszc="";
	//去年
	private String bnyszcqn="";
	///（一）行政支出		
	private String hzzc="";
	//去年
	private String hzzcqn="";
	//（二）事业支出		
	private String syzc="";
	//去年
	private String syzcqn="";
	//（三）经营支出		
	private String jyzc="";
	//去年
	private String jyzcqn="";
	//（四）上缴上级支出		
	private String sjsjzc="";
	//去年
	private String sjsjzcqn="";
	//（五）对附属单位补助支出	
	private String dfsdwbzzc="";
	//去年
	private String dfsdwbzzcqn="";
	//（六）投资支出		
	private String tzzc="";
	//去年
	private String tzzcqn="";
	//（七）债务还本支出		
	private String zwhbzc="";
	//去年
	private String zwhbzcqn="";
	//（八）其他支出		
	private String qtzc="";
	//去年
	private String qtzcqn="";

	//其中：利息支出		
	private String lxzc="";
	//去年
	private String lxzcqn="";
	//捐赠支出		
	private String jzzc="";
	//去年
	private String jzzcqn="";
	//三、本年预算收支差额
	private String bnysszce="";
	//去年
	private String bnysszceqn="";
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
	public String getCzbkyssr() {
		return czbkyssr;
	}
	public void setCzbkyssr(String czbkyssr) {
		this.czbkyssr = czbkyssr;
	}
	public String getZfxjjsr() {
		return zfxjjsr;
	}
	public void setZfxjjsr(String zfxjjsr) {
		this.zfxjjsr = zfxjjsr;
	}
	public String getSyyssr() {
		return syyssr;
	}
	public void setSyyssr(String syyssr) {
		this.syyssr = syyssr;
	}
	public String getSjfzyssr() {
		return sjfzyssr;
	}
	public void setSjfzyssr(String sjfzyssr) {
		this.sjfzyssr = sjfzyssr;
	}
	public String getFsdwsjyssr() {
		return fsdwsjyssr;
	}
	public void setFsdwsjyssr(String fsdwsjyssr) {
		this.fsdwsjyssr = fsdwsjyssr;
	}
	public String getJyyssr() {
		return jyyssr;
	}
	public void setJyyssr(String jyyssr) {
		this.jyyssr = jyyssr;
	}
	public String getZwyssr() {
		return zwyssr;
	}
	public void setZwyssr(String zwyssr) {
		this.zwyssr = zwyssr;
	}
	public String getFtjczbkyssr() {
		return ftjczbkyssr;
	}
	public void setFtjczbkyssr(String ftjczbkyssr) {
		this.ftjczbkyssr = ftjczbkyssr;
	}
	public String getTzyssy() {
		return tzyssy;
	}
	public void setTzyssy(String tzyssy) {
		this.tzyssy = tzyssy;
	}
	public String getQtyssr() {
		return qtyssr;
	}
	public void setQtyssr(String qtyssr) {
		this.qtyssr = qtyssr;
	}
	public String getLxyssr() {
		return lxyssr;
	}
	public void setLxyssr(String lxyssr) {
		this.lxyssr = lxyssr;
	}
	public String getJzyssr() {
		return jzyssr;
	}
	public void setJzyssr(String jzyssr) {
		this.jzyssr = jzyssr;
	}
	public String getZjyssr() {
		return zjyssr;
	}
	public void setZjyssr(String zjyssr) {
		this.zjyssr = zjyssr;
	}
	public String getBnyszc() {
		return bnyszc;
	}
	public void setBnyszc(String bnyszc) {
		this.bnyszc = bnyszc;
	}
	public String getHzzc() {
		return hzzc;
	}
	public void setHzzc(String hzzc) {
		this.hzzc = hzzc;
	}
	public String getSyzc() {
		return syzc;
	}
	public void setSyzc(String syzc) {
		this.syzc = syzc;
	}
	public String getJyzc() {
		return jyzc;
	}
	public void setJyzc(String jyzc) {
		this.jyzc = jyzc;
	}
	public String getSjsjzc() {
		return sjsjzc;
	}
	public void setSjsjzc(String sjsjzc) {
		this.sjsjzc = sjsjzc;
	}
	public String getDfsdwbzzc() {
		return dfsdwbzzc;
	}
	public void setDfsdwbzzc(String dfsdwbzzc) {
		this.dfsdwbzzc = dfsdwbzzc;
	}
	public String getTzzc() {
		return tzzc;
	}
	public void setTzzc(String tzzc) {
		this.tzzc = tzzc;
	}
	public String getZwhbzc() {
		return zwhbzc;
	}
	public void setZwhbzc(String zwhbzc) {
		this.zwhbzc = zwhbzc;
	}
	public String getQtzc() {
		return qtzc;
	}
	public void setQtzc(String qtzc) {
		this.qtzc = qtzc;
	}
	public String getLxzc() {
		return lxzc;
	}
	public void setLxzc(String lxzc) {
		this.lxzc = lxzc;
	}
	public String getJzzc() {
		return jzzc;
	}
	public void setJzzc(String jzzc) {
		this.jzzc = jzzc;
	}
	public String getBnysszce() {
		return bnysszce;
	}
	public void setBnysszce(String bnysszce) {
		this.bnysszce = bnysszce;
	}
	public String getBqyssrqn() {
		return bqyssrqn;
	}
	public void setBqyssrqn(String bqyssrqn) {
		this.bqyssrqn = bqyssrqn;
	}
	public String getCzbkyssrqn() {
		return czbkyssrqn;
	}
	public void setCzbkyssrqn(String czbkyssrqn) {
		this.czbkyssrqn = czbkyssrqn;
	}
	public String getZfxjjsrqn() {
		return zfxjjsrqn;
	}
	public void setZfxjjsrqn(String zfxjjsrqn) {
		this.zfxjjsrqn = zfxjjsrqn;
	}
	public String getSyyssrqn() {
		return syyssrqn;
	}
	public void setSyyssrqn(String syyssrqn) {
		this.syyssrqn = syyssrqn;
	}
	public String getSjfzyssrqn() {
		return sjfzyssrqn;
	}
	public void setSjfzyssrqn(String sjfzyssrqn) {
		this.sjfzyssrqn = sjfzyssrqn;
	}
	public String getFsdwsjyssrqn() {
		return fsdwsjyssrqn;
	}
	public void setFsdwsjyssrqn(String fsdwsjyssrqn) {
		this.fsdwsjyssrqn = fsdwsjyssrqn;
	}
	public String getJyyssrqn() {
		return jyyssrqn;
	}
	public void setJyyssrqn(String jyyssrqn) {
		this.jyyssrqn = jyyssrqn;
	}
	public String getZwyssrqn() {
		return zwyssrqn;
	}
	public void setZwyssrqn(String zwyssrqn) {
		this.zwyssrqn = zwyssrqn;
	}
	public String getFtjczbkyssrqn() {
		return ftjczbkyssrqn;
	}
	public void setFtjczbkyssrqn(String ftjczbkyssrqn) {
		this.ftjczbkyssrqn = ftjczbkyssrqn;
	}
	public String getTzyssyqn() {
		return tzyssyqn;
	}
	public void setTzyssyqn(String tzyssyqn) {
		this.tzyssyqn = tzyssyqn;
	}
	public String getQtyssrqn() {
		return qtyssrqn;
	}
	public void setQtyssrqn(String qtyssrqn) {
		this.qtyssrqn = qtyssrqn;
	}
	public String getLxyssrqn() {
		return lxyssrqn;
	}
	public void setLxyssrqn(String lxyssrqn) {
		this.lxyssrqn = lxyssrqn;
	}
	public String getJzyssrqn() {
		return jzyssrqn;
	}
	public void setJzyssrqn(String jzyssrqn) {
		this.jzyssrqn = jzyssrqn;
	}
	public String getZjyssrqn() {
		return zjyssrqn;
	}
	public void setZjyssrqn(String zjyssrqn) {
		this.zjyssrqn = zjyssrqn;
	}
	public String getBnyszcqn() {
		return bnyszcqn;
	}
	public void setBnyszcqn(String bnyszcqn) {
		this.bnyszcqn = bnyszcqn;
	}
	public String getHzzcqn() {
		return hzzcqn;
	}
	public void setHzzcqn(String hzzcqn) {
		this.hzzcqn = hzzcqn;
	}
	public String getSyzcqn() {
		return syzcqn;
	}
	public void setSyzcqn(String syzcqn) {
		this.syzcqn = syzcqn;
	}
	public String getJyzcqn() {
		return jyzcqn;
	}
	public void setJyzcqn(String jyzcqn) {
		this.jyzcqn = jyzcqn;
	}
	public String getSjsjzcqn() {
		return sjsjzcqn;
	}
	public void setSjsjzcqn(String sjsjzcqn) {
		this.sjsjzcqn = sjsjzcqn;
	}
	public String getDfsdwbzzcqn() {
		return dfsdwbzzcqn;
	}
	public void setDfsdwbzzcqn(String dfsdwbzzcqn) {
		this.dfsdwbzzcqn = dfsdwbzzcqn;
	}
	public String getTzzcqn() {
		return tzzcqn;
	}
	public void setTzzcqn(String tzzcqn) {
		this.tzzcqn = tzzcqn;
	}
	public String getZwhbzcqn() {
		return zwhbzcqn;
	}
	public void setZwhbzcqn(String zwhbzcqn) {
		this.zwhbzcqn = zwhbzcqn;
	}
	public String getQtzcqn() {
		return qtzcqn;
	}
	public void setQtzcqn(String qtzcqn) {
		this.qtzcqn = qtzcqn;
	}
	public String getLxzcqn() {
		return lxzcqn;
	}
	public void setLxzcqn(String lxzcqn) {
		this.lxzcqn = lxzcqn;
	}
	public String getJzzcqn() {
		return jzzcqn;
	}
	public void setJzzcqn(String jzzcqn) {
		this.jzzcqn = jzzcqn;
	}
	public String getBnysszceqn() {
		return bnysszceqn;
	}
	public void setBnysszceqn(String bnysszceqn) {
		this.bnysszceqn = bnysszceqn;
	}

}
