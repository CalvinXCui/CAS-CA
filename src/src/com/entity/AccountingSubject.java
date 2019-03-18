package com.entity;

/**
 * 财务会计科目
 * AccountingSubject entity. @author MyEclipse Persistence Tools
 */

public class AccountingSubject implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -8213668159606096950L;
	private String id;                          //财务会计科目id
	private String SNo;                         //科目编号
	private String SName;                       //科目名称
	private String SType;                       //科目类型（要素）
	private String SLevel;                      //科目级别
	private String SHigherLevelNo;              //上级编码
	private String SIsLastLevel;                //是否末级
	private String aisAccunting;                //是否为项目核算
	private String pno;                         //项目编码
	private String amnemonts;                   //助记码
	private String acurrecy;                    //币种
	private String aunit;                       //计量单位
	private String aauxiliaryType;              //辅助帐类型
	private String abalanceDirection;           //余额方向
	private String afolloFormat;                //账页格式
	private String aremarks;                    //备注
	private String vYwhd;						//业务活动
	private String isfeature;					//功能科目
	private String iseconomics;					//经济科目
	private String vCostKeeping;				//成本核算科目
	private String vControlledSubject;			//受控科目
	private String vCashFlow;					//现金流量科目
	private String vTaxcontrolAccounting;		//税控核算科目
	private String vKtxx;						//课题信息
	private String vXmxx;						//项目信息
	private String vCzyskm;						//财政预算科目

	
	public String getvKtxx() {
		return vKtxx;
	}

	public void setvKtxx(String vKtxx) {
		this.vKtxx = vKtxx;
	}

	public String getvXmxx() {
		return vXmxx;
	}

	public void setvXmxx(String vXmxx) {
		this.vXmxx = vXmxx;
	}

	public String getvCostKeeping() {
		return vCostKeeping;
	}

	public void setvCostKeeping(String vCostKeeping) {
		this.vCostKeeping = vCostKeeping;
	}

	public String getvControlledSubject() {
		return vControlledSubject;
	}



	public void setvControlledSubject(String vControlledSubject) {
		this.vControlledSubject = vControlledSubject;
	}



	public String getvCashFlow() {
		return vCashFlow;
	}



	public void setvCashFlow(String vCashFlow) {
		this.vCashFlow = vCashFlow;
	}



	public String getvTaxcontrolAccounting() {
		return vTaxcontrolAccounting;
	}



	public void setvTaxcontrolAccounting(String vTaxcontrolAccounting) {
		this.vTaxcontrolAccounting = vTaxcontrolAccounting;
	}



	/** default constructor */
	public AccountingSubject() {
	}

	

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}




	public String getSNo() {
		return SNo;
	}



	public void setSNo(String sNo) {
		SNo = sNo;
	}



	public String getSName() {
		return SName;
	}



	public void setSName(String sName) {
		SName = sName;
	}

	public String getSType() {
		return SType;
	}



	public void setSType(String sType) {
		SType = sType;
	}



	public String getSLevel() {
		return SLevel;
	}



	public void setSLevel(String sLevel) {
		SLevel = sLevel;
	}



	public String getSHigherLevelNo() {
		return SHigherLevelNo;
	}



	public void setSHigherLevelNo(String sHigherLevelNo) {
		SHigherLevelNo = sHigherLevelNo;
	}



	public String getSIsLastLevel() {
		return SIsLastLevel;
	}



	public void setSIsLastLevel(String sIsLastLevel) {
		SIsLastLevel = sIsLastLevel;
	}



	public String getAisAccunting() {
		return this.aisAccunting;
	}

	public void setAisAccunting(String aisAccunting) {
		this.aisAccunting = aisAccunting;
	}

	public String getPno() {
		return this.pno;
	}

	public void setPno(String pno) {
		this.pno = pno;
	}

	public String getAmnemonts() {
		return this.amnemonts;
	}

	public void setAmnemonts(String amnemonts) {
		this.amnemonts = amnemonts;
	}

	public String getAcurrecy() {
		return this.acurrecy;
	}

	public void setAcurrecy(String acurrecy) {
		this.acurrecy = acurrecy;
	}

	public String getAunit() {
		return this.aunit;
	}

	public void setAunit(String aunit) {
		this.aunit = aunit;
	}

	public String getAauxiliaryType() {
		return this.aauxiliaryType;
	}

	public void setAauxiliaryType(String aauxiliaryType) {
		this.aauxiliaryType = aauxiliaryType;
	}

	public String getAbalanceDirection() {
		return this.abalanceDirection;
	}

	public void setAbalanceDirection(String abalanceDirection) {
		this.abalanceDirection = abalanceDirection;
	}

	public String getAfolloFormat() {
		return this.afolloFormat;
	}

	public void setAfolloFormat(String afolloFormat) {
		this.afolloFormat = afolloFormat;
	}

	public String getAremarks() {
		return this.aremarks;
	}

	public void setAremarks(String aremarks) {
		this.aremarks = aremarks;
	}



	public String getIsfeature() {
		return isfeature;
	}



	public void setIsfeature(String isfeature) {
		this.isfeature = isfeature;
	}



	public String getIseconomics() {
		return iseconomics;
	}



	public void setIseconomics(String iseconomics) {
		this.iseconomics = iseconomics;
	}



	public String getvYwhd() {
		return vYwhd;
	}



	public void setvYwhd(String vYwhd) {
		this.vYwhd = vYwhd;
	}



	public String getvCzyskm() {
		return vCzyskm;
	}



	public void setvCzyskm(String vCzyskm) {
		this.vCzyskm = vCzyskm;
	}

}