package com.entity;

/**
 * 老会计科目表
 * OldSubject entity. @author MyEclipse Persistence Tools
 */

public class OldSubject implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 2415300353142565686L;
	
	private String id;                        //
	private String SNo;                       //科目编号
	private String SName;                     //科目名称
	private String SType;                     //科目类型
	private String SLevel;                    //科目级别
	private String SHigherLevelNo;            //上级编码
	private String SIsLastLevel;              //是否末级
	private String SIsAccpunting;             //是否为项目核算
	private String PNo;                       //项目编码
	private String SRemarks;                  //备注
	private String SMnemonic;                 //助记码
	private String SCurrency;                 //币种
	private String SUnit;                     //计量单位
	private String SAuxiliaryType;            //辅助帐类型
	private String SBalanceDirection;         //余额方向
	private String SFolioFormat;              //帐页格式

	// Constructors

	/** default constructor */
	public OldSubject() {
	}

	/** minimal constructor */
	public OldSubject(String SNo) {
		this.SNo = SNo;
	}

	/** full constructor */
	public OldSubject(String SNo, String SName, String SType, String SLevel,
			String SHigherLevelNo, String SIsLastLevel, String SIsAccpunting,
			String PNo, String SRemarks, String SMnemonic, String SCurrency,
			String SUnit, String SAuxiliaryType, String SBalanceDirection,
			String SFolioFormat) {
		this.SNo = SNo;
		this.SName = SName;
		this.SType = SType;
		this.SLevel = SLevel;
		this.SHigherLevelNo = SHigherLevelNo;
		this.SIsLastLevel = SIsLastLevel;
		this.SIsAccpunting = SIsAccpunting;
		this.PNo = PNo;
		this.SRemarks = SRemarks;
		this.SMnemonic = SMnemonic;
		this.SCurrency = SCurrency;
		this.SUnit = SUnit;
		this.SAuxiliaryType = SAuxiliaryType;
		this.SBalanceDirection = SBalanceDirection;
		this.SFolioFormat = SFolioFormat;
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

	public String getSRemarks() {
		return this.SRemarks;
	}

	public void setSRemarks(String SRemarks) {
		this.SRemarks = SRemarks;
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

}