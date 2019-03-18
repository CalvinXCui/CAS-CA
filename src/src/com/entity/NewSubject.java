package com.entity;

/**
 * 新会计科目表
 * NewSubject entity. @author MyEclipse Persistence Tools
 */

public class NewSubject implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;                       //ID
	private String SNo;                      //科目编号
	private String SName;                    //科目名称
	private String SType;                    //科目类型
	private String SCategory;                //科目类别(1：财务 2：预算)
	private String SLevel;                   //科目级次
	private String SHigherLevelNo;           //上级编码
	private String SIsLastLevel;             //是否末级
	private String SIsAccpunting;            //是否为项目核算
	private String PNo;                      //项目编码                                                         
	private String SMnemonic;                //助记码
	private String SCurrency;                //币种
	private String SUnit;                    //计量单位
	private String SAuxiliaryType;           //辅助帐类型(辅助核算类别：财务会计：收入来源、项目、往来单位及个人、部门、供应商核算;预算会计：清算银行、预算单位、往来单位、功能分类、经济分类、预算项目)
	private String SBalanceDirection;        //余额方向
	private String SFolioFormat;             //帐页格式
	private String SRemarks;                 //备注

	// Constructors

	/** default constructor */
	public NewSubject() {
	}

	/** full constructor */
	public NewSubject(String id, String sNo, String sName, String sType, String sCategory, String sLevel, String sHigherLevelNo,
			String sIsLastLevel, String sIsAccpunting, String pNo, String sMnemonic, String sCurrency, String sUnit,
			String sAuxiliaryType, String sBalanceDirection, String sFolioFormat, String sRemarks) {
		super();
		this.id = id;
		SNo = sNo;
		SName = sName;
		SType = sType;
		SCategory = sCategory;
		SLevel = sLevel;
		SHigherLevelNo = sHigherLevelNo;
		SIsLastLevel = sIsLastLevel;
		SIsAccpunting = sIsAccpunting;
		PNo = pNo;
		SMnemonic = sMnemonic;
		SCurrency = sCurrency;
		SUnit = sUnit;
		SAuxiliaryType = sAuxiliaryType;
		SBalanceDirection = sBalanceDirection;
		SFolioFormat = sFolioFormat;
		SRemarks = sRemarks;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSNo() {
		return this.SNo;
	}

	public void setSNo(String SNo) {
		this.SNo = SNo;
	}

	public String getSName() {
		return this.SName;
	}

	public void setSName(String SName) {
		this.SName = SName;
	}

	public String getSType() {
		return this.SType;
	}

	public void setSType(String SType) {
		this.SType = SType;
	}

	public String getSCategory() {
		return this.SCategory;
	}

	public void setSCategory(String SCategory) {
		this.SCategory = SCategory;
	}

	public String getSLevel() {
		return this.SLevel;
	}

	public void setSLevel(String SLevel) {
		this.SLevel = SLevel;
	}

	public String getSHigherLevelNo() {
		return this.SHigherLevelNo;
	}

	public void setSHigherLevelNo(String SHigherLevelNo) {
		this.SHigherLevelNo = SHigherLevelNo;
	}

	public String getSIsLastLevel() {
		return this.SIsLastLevel;
	}

	public void setSIsLastLevel(String SIsLastLevel) {
		this.SIsLastLevel = SIsLastLevel;
	}

	public String getSIsAccpunting() {
		return this.SIsAccpunting;
	}

	public void setSIsAccpunting(String SIsAccpunting) {
		this.SIsAccpunting = SIsAccpunting;
	}

	public String getPNo() {
		return this.PNo;
	}

	public void setPNo(String PNo) {
		this.PNo = PNo;
	}

	public String getSMnemonic() {
		return this.SMnemonic;
	}

	public void setSMnemonic(String SMnemonic) {
		this.SMnemonic = SMnemonic;
	}

	public String getSCurrency() {
		return this.SCurrency;
	}

	public void setSCurrency(String SCurrency) {
		this.SCurrency = SCurrency;
	}

	public String getSUnit() {
		return this.SUnit;
	}

	public void setSUnit(String SUnit) {
		this.SUnit = SUnit;
	}

	public String getSAuxiliaryType() {
		return this.SAuxiliaryType;
	}

	public void setSAuxiliaryType(String SAuxiliaryType) {
		this.SAuxiliaryType = SAuxiliaryType;
	}

	public String getSBalanceDirection() {
		return this.SBalanceDirection;
	}

	public void setSBalanceDirection(String SBalanceDirection) {
		this.SBalanceDirection = SBalanceDirection;
	}

	public String getSFolioFormat() {
		return this.SFolioFormat;
	}

	public void setSFolioFormat(String SFolioFormat) {
		this.SFolioFormat = SFolioFormat;
	}

	public String getSRemarks() {
		return this.SRemarks;
	}

	public void setSRemarks(String SRemarks) {
		this.SRemarks = SRemarks;
	}

}