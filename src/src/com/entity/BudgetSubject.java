package com.entity;

/**
 * 预算会计科目
 * BudgetSubject entity. @author MyEclipse Persistence Tools
 */

public class BudgetSubject implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 2841474928898756332L;
	private String id;                          //财务会计科目id
	private String SNo;                         //科目编号
	private String SName;                       //科目名称
	private String SType;                       //科目类型（要素）
	private String SLevel;                      //科目级别
	private String SHigherLevelNo;              //上级编码
	private String SIsLastLevel;                //是否末级
	private String bisAccunting;                //是否为项目核算
	private String pno;                         //项目编码
	private String bmnemonts;                   //助记码
	private String bcurrecy;                    //币种
	private String bunit;                       //计量单位
	private String bauxiliaryType;              //辅助帐类型
	private String bbalanceDirection;           //余额方向
	private String bfolloFormat;                //账页格式
	private String bremarks;                    //备注
	
	private String isfeature;					//功能科目
	private String iseconomics;					//经济科目
	private String bCostKeeping;				//成本核算科目
	private String bControlledSubject;			//受控科目
	private String bCashFlow;					//现金流量科目
	private String bTaxcontrolAccounting;		//税控核算科目
	private String bKtxx;						//课题信息
	private String bXmxx;						//项目信息
	private String bYwhd;						//业务活动
	private String bCzyskm;						//财政预算科目
	

	public String getbCostKeeping() {
		return bCostKeeping;
	}


	public void setbCostKeeping(String bCostKeeping) {
		this.bCostKeeping = bCostKeeping;
	}


	public String getbControlledSubject() {
		return bControlledSubject;
	}


	public void setbControlledSubject(String bControlledSubject) {
		this.bControlledSubject = bControlledSubject;
	}


	public String getbCashFlow() {
		return bCashFlow;
	}


	public void setbCashFlow(String bCashFlow) {
		this.bCashFlow = bCashFlow;
	}


	public String getbTaxcontrolAccounting() {
		return bTaxcontrolAccounting;
	}


	public void setbTaxcontrolAccounting(String bTaxcontrolAccounting) {
		this.bTaxcontrolAccounting = bTaxcontrolAccounting;
	}


	public String getbKtxx() {
		return bKtxx;
	}


	public void setbKtxx(String bKtxx) {
		this.bKtxx = bKtxx;
	}


	public String getbXmxx() {
		return bXmxx;
	}


	public void setbXmxx(String bXmxx) {
		this.bXmxx = bXmxx;
	}


	/** default constructor */
	public BudgetSubject() {
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


	public String getBisAccunting() {
		return this.bisAccunting;
	}

	public void setBisAccunting(String bisAccunting) {
		this.bisAccunting = bisAccunting;
	}

	public String getPno() {
		return this.pno;
	}

	public void setPno(String pno) {
		this.pno = pno;
	}

	public String getBmnemonts() {
		return this.bmnemonts;
	}

	public void setBmnemonts(String bmnemonts) {
		this.bmnemonts = bmnemonts;
	}

	public String getBcurrecy() {
		return this.bcurrecy;
	}

	public void setBcurrecy(String bcurrecy) {
		this.bcurrecy = bcurrecy;
	}

	public String getBunit() {
		return this.bunit;
	}

	public void setBunit(String bunit) {
		this.bunit = bunit;
	}

	public String getBauxiliaryType() {
		return this.bauxiliaryType;
	}

	public void setBauxiliaryType(String bauxiliaryType) {
		this.bauxiliaryType = bauxiliaryType;
	}

	public String getBbalanceDirection() {
		return this.bbalanceDirection;
	}

	public void setBbalanceDirection(String bbalanceDirection) {
		this.bbalanceDirection = bbalanceDirection;
	}

	public String getBfolloFormat() {
		return this.bfolloFormat;
	}

	public void setBfolloFormat(String bfolloFormat) {
		this.bfolloFormat = bfolloFormat;
	}

	public String getBremarks() {
		return this.bremarks;
	}

	public void setBremarks(String bremarks) {
		this.bremarks = bremarks;
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


	public String getbYwhd() {
		return bYwhd;
	}


	public void setbYwhd(String bYwhd) {
		this.bYwhd = bYwhd;
	}


	public String getbCzyskm() {
		return bCzyskm;
	}


	public void setbCzyskm(String bCzyskm) {
		this.bCzyskm = bCzyskm;
	}

}