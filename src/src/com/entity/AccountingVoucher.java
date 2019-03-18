package com.entity;

/**
 * 财务
 * AccountingVoucher entity. @author MyEclipse Persistence Tools
 */

public class AccountingVoucher implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -6944018678822138334L;
	
	private String id;                          //主键
	private String VType="";                    //凭证类型  1:收款凭证 2:付款凭证 3:转账凭证
	private String VNo="";                      //凭证编号
	private String VDate="";                    //凭证日期
	private String VAbstract="";                //摘要                       经济业务内容
	private String VDebitAmount="";             //借方金额
	private String VCreditAmount="";            //贷方金额
	private String VHandleId="";                //经手人ID
	private String VHandleName="";              //经手人姓名
	private String VReviewId="";                //审核人ID
	private String VReviewName="";              //审核人姓名
	private String VReviewDate="";              //审核时间
	private String VState="";                   //状态  0：未审核 1：已审核 2：已作废
	private String VIsAccounting="";            //是否为项目核算
	/*--------------------*/
	private String VAccountingPeriod="";        //会计期间
	private String VAccountInformation="";      //账户信息
	private String VFinancialManager="";        //财务负责人
	/*------------------*/
	private String VCashier="";                 //出纳
	private String VCertificate="";             //制证
	private String VFounder="";                 //创建人名称
	private String VFounderId="";               //创建人ID
	private String VBookkeeping="";             //记账
	/*---------------------*/
	private String VUpdateTime="";              //更新日期
	private String VUpdatePerson="";            //更新人
	private String VFillingCompanyName="";      //填制单位名称
	private String VOriginalNum="";             //所付原始凭证张数
	/*-------------------------*/
	private String VUpdateNameId;               //更新人ID
	private String PId="";                      //项目id
/*------------后来增加字段------删除之前的表的科目id---------*/
	private String SNo="";                      //科目编号
	private String SName="";                    //科目名称
	private String TNo="";                      //课题编号
	private String TName="";                    //课题名称
	private String TOrganization="";            //所属部门/组织
	private String TFundsSources="";            //资金来源
/*--------------最后一次修改的字段----------*/
	private String VCapitalProperty="";          //资金性质
	private String VEconomic="";                 //经济分类
	private String VFunctional="";               //功能分类
	private String VBudgetItems="";              //预算科目
	private String VReimbursement="";            //报销人
	
	private String VRemarks="";                  //备注
	
	private String VAuxAccount="";                   //备注
	
	
	/*------------------------------*/
	private String Vfhr=""; 		  //复核人
	private String Vgnkm="";          //功能科目
	private String Vjjkm="";          //经济科目
	private String Vktxx="";          //课题信息
	private String Vxmxx="";          //项目信息
	private String Vywhd=""; 		  //业务活动
	private int VStatus; 		      //凭证状态
	private String createTime=""; 	  //创建时间
	private String type="0"; 	      //区分手动录入还是批量导入     1：手动录入
	private String transtype="0";

	public String getVfhr() {
		return Vfhr;
	}
	public void setVfhr(String vfhr) {
		Vfhr = vfhr;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getVType() {
		return VType;
	}
	public void setVType(String vType) {
		VType = vType;
	}
	public String getVNo() {
		return VNo;
	}
	public void setVNo(String vNo) {
		VNo = vNo;
	}
	public String getVDate() {
		return VDate;
	}
	public void setVDate(String vDate) {
		VDate = vDate;
	}
	public String getVAbstract() {
		return VAbstract;
	}
	public void setVAbstract(String vAbstract) {
		VAbstract = vAbstract;
	}
	public String getVDebitAmount() {
		return VDebitAmount;
	}
	public void setVDebitAmount(String vDebitAmount) {
		VDebitAmount = vDebitAmount;
	}
	public String getVCreditAmount() {
		return VCreditAmount;
	}
	public void setVCreditAmount(String vCreditAmount) {
		VCreditAmount = vCreditAmount;
	}
	public String getVHandleId() {
		return VHandleId;
	}
	public void setVHandleId(String vHandleId) {
		VHandleId = vHandleId;
	}
	public String getVHandleName() {
		return VHandleName;
	}
	public void setVHandleName(String vHandleName) {
		VHandleName = vHandleName;
	}
	public String getVReviewId() {
		return VReviewId;
	}
	public void setVReviewId(String vReviewId) {
		VReviewId = vReviewId;
	}
	public String getVReviewName() {
		return VReviewName;
	}
	public void setVReviewName(String vReviewName) {
		VReviewName = vReviewName;
	}
	public String getVReviewDate() {
		return VReviewDate;
	}
	public void setVReviewDate(String vReviewDate) {
		VReviewDate = vReviewDate;
	}
	public String getVState() {
		return VState;
	}
	public void setVState(String vState) {
		VState = vState;
	}
	public String getVIsAccounting() {
		return VIsAccounting;
	}
	public void setVIsAccounting(String vIsAccounting) {
		VIsAccounting = vIsAccounting;
	}
	public String getVAccountingPeriod() {
		return VAccountingPeriod;
	}
	public void setVAccountingPeriod(String vAccountingPeriod) {
		VAccountingPeriod = vAccountingPeriod;
	}
	public String getVAccountInformation() {
		return VAccountInformation;
	}
	public void setVAccountInformation(String vAccountInformation) {
		VAccountInformation = vAccountInformation;
	}
	public String getVFinancialManager() {
		return VFinancialManager;
	}
	public void setVFinancialManager(String vFinancialManager) {
		VFinancialManager = vFinancialManager;
	}
	public String getVCashier() {
		return VCashier;
	}
	public void setVCashier(String vCashier) {
		VCashier = vCashier;
	}
	public String getVCertificate() {
		return VCertificate;
	}
	public void setVCertificate(String vCertificate) {
		VCertificate = vCertificate;
	}
	public String getVFounder() {
		return VFounder;
	}
	public void setVFounder(String vFounder) {
		VFounder = vFounder;
	}
	public String getVFounderId() {
		return VFounderId;
	}
	public void setVFounderId(String vFounderId) {
		VFounderId = vFounderId;
	}
	public String getVBookkeeping() {
		return VBookkeeping;
	}
	public void setVBookkeeping(String vBookkeeping) {
		VBookkeeping = vBookkeeping;
	}
	public String getVUpdateTime() {
		return VUpdateTime;
	}
	public void setVUpdateTime(String vUpdateTime) {
		VUpdateTime = vUpdateTime;
	}
	public String getVUpdatePerson() {
		return VUpdatePerson;
	}
	public void setVUpdatePerson(String vUpdatePerson) {
		VUpdatePerson = vUpdatePerson;
	}
	public String getVFillingCompanyName() {
		return VFillingCompanyName;
	}
	public void setVFillingCompanyName(String vFillingCompanyName) {
		VFillingCompanyName = vFillingCompanyName;
	}
	public String getVOriginalNum() {
		return VOriginalNum;
	}
	public void setVOriginalNum(String vOriginalNum) {
		VOriginalNum = vOriginalNum;
	}
	public String getVUpdateNameId() {
		return VUpdateNameId;
	}
	public void setVUpdateNameId(String vUpdateNameId) {
		VUpdateNameId = vUpdateNameId;
	}
	public String getPId() {
		return PId;
	}
	public void setPId(String pId) {
		PId = pId;
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
	public String getTNo() {
		return TNo;
	}
	public void setTNo(String tNo) {
		TNo = tNo;
	}
	public String getTName() {
		return TName;
	}
	public void setTName(String tName) {
		TName = tName;
	}
	public String getTOrganization() {
		return TOrganization;
	}
	public void setTOrganization(String tOrganization) {
		TOrganization = tOrganization;
	}
	public String getTFundsSources() {
		return TFundsSources;
	}
	public void setTFundsSources(String tFundsSources) {
		TFundsSources = tFundsSources;
	}
	public String getVCapitalProperty() {
		return VCapitalProperty;
	}
	public void setVCapitalProperty(String vCapitalProperty) {
		VCapitalProperty = vCapitalProperty;
	}
	public String getVEconomic() {
		return VEconomic;
	}
	public void setVEconomic(String vEconomic) {
		VEconomic = vEconomic;
	}
	public String getVFunctional() {
		return VFunctional;
	}
	public void setVFunctional(String vFunctional) {
		VFunctional = vFunctional;
	}
	public String getVBudgetItems() {
		return VBudgetItems;
	}
	public void setVBudgetItems(String vBudgetItems) {
		VBudgetItems = vBudgetItems;
	}
	public String getVReimbursement() {
		return VReimbursement;
	}
	public void setVReimbursement(String vReimbursement) {
		VReimbursement = vReimbursement;
	}
	public String getVRemarks() {
		return VRemarks;
	}
	public void setVRemarks(String vRemarks) {
		VRemarks = vRemarks;
	}
	public String getVgnkm() {
		return Vgnkm;
	}
	public void setVgnkm(String vgnkm) {
		Vgnkm = vgnkm;
	}
	public String getVjjkm() {
		return Vjjkm;
	}
	public void setVjjkm(String vjjkm) {
		Vjjkm = vjjkm;
	}
	public String getVktxx() {
		return Vktxx;
	}
	public void setVktxx(String vktxx) {
		Vktxx = vktxx;
	}
	public String getVxmxx() {
		return Vxmxx;
	}
	public void setVxmxx(String vxmxx) {
		Vxmxx = vxmxx;
	}
	public String getVywhd() {
		return Vywhd;
	}
	public void setVywhd(String vywhd) {
		Vywhd = vywhd;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public String getVAuxAccount() {
		return VAuxAccount;
	}
	public void setVAuxAccount(String vAuxAccount) {
		VAuxAccount = vAuxAccount;
	}
	public int getVStatus() {
		return VStatus;
	}
	public void setVStatus(int vStatus) {
		VStatus = vStatus;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public String getTranstype() {
		return transtype;
	}
	public void setTranstype(String transtype) {
		this.transtype = transtype;
	}
	@Override
	public String toString() {
		return "AccountingVoucher [id=" + id + ", VType=" + VType + ", VNo=" + VNo + ", VDate=" + VDate + ", VAbstract="
				+ VAbstract + ", VDebitAmount=" + VDebitAmount + ", VCreditAmount=" + VCreditAmount + ", VHandleId="
				+ VHandleId + ", VHandleName=" + VHandleName + ", VReviewId=" + VReviewId + ", VReviewName="
				+ VReviewName + ", VReviewDate=" + VReviewDate + ", VState=" + VState + ", VIsAccounting="
				+ VIsAccounting + ", VAccountingPeriod=" + VAccountingPeriod + ", VAccountInformation="
				+ VAccountInformation + ", VFinancialManager=" + VFinancialManager + ", VCashier=" + VCashier
				+ ", VCertificate=" + VCertificate + ", VFounder=" + VFounder + ", VFounderId=" + VFounderId
				+ ", VBookkeeping=" + VBookkeeping + ", VUpdateTime=" + VUpdateTime + ", VUpdatePerson=" + VUpdatePerson
				+ ", VFillingCompanyName=" + VFillingCompanyName + ", VOriginalNum=" + VOriginalNum + ", VUpdateNameId="
				+ VUpdateNameId + ", PId=" + PId + ", SNo=" + SNo + ", SName=" + SName + ", TNo=" + TNo + ", TName="
				+ TName + ", TOrganization=" + TOrganization + ", TFundsSources=" + TFundsSources
				+ ", VCapitalProperty=" + VCapitalProperty + ", VEconomic=" + VEconomic + ", VFunctional=" + VFunctional
				+ ", VBudgetItems=" + VBudgetItems + ", VReimbursement=" + VReimbursement + ", VRemarks=" + VRemarks
				+ ", Vgnkm=" + Vgnkm + ", Vjjkm=" + Vjjkm + ", Vktxx=" + Vktxx + ", Vxmxx=" + Vxmxx + ", Vywhd=" + Vywhd
				+ ",Vywhd=" + VAuxAccount+ ",VStatus=" + VStatus
				+ "]";
	}
	public AccountingVoucher(String id, String vType, String vNo, String vDate, String vAbstract, String vDebitAmount,
			String vCreditAmount, String vHandleId, String vHandleName, String vReviewId, String vReviewName,
			String vReviewDate, String vState, String vIsAccounting, String vAccountingPeriod,
			String vAccountInformation, String vFinancialManager, String vCashier, String vCertificate, String vFounder,
			String vFounderId, String vBookkeeping, String vUpdateTime, String vUpdatePerson,
			String vFillingCompanyName, String vOriginalNum, String vUpdateNameId, String pId, String sNo, String sName,
			String tNo, String tName, String tOrganization, String tFundsSources, String vCapitalProperty,
			String vEconomic, String vFunctional, String vBudgetItems, String vReimbursement, String vRemarks,
			String vgnkm, String vjjkm, String vktxx, String vxmxx, String vywhd, String VAuxAccount, String VStatus) {
		super();
		this.id = id;
		VType = vType;
		VNo = vNo;
		VDate = vDate;
		VAbstract = vAbstract;
		VDebitAmount = vDebitAmount;
		VCreditAmount = vCreditAmount;
		VHandleId = vHandleId;
		VHandleName = vHandleName;
		VReviewId = vReviewId;
		VReviewName = vReviewName;
		VReviewDate = vReviewDate;
		VState = vState;
		VIsAccounting = vIsAccounting;
		VAccountingPeriod = vAccountingPeriod;
		VAccountInformation = vAccountInformation;
		VFinancialManager = vFinancialManager;
		VCashier = vCashier;
		VCertificate = vCertificate;
		VFounder = vFounder;
		VFounderId = vFounderId;
		VBookkeeping = vBookkeeping;
		VUpdateTime = vUpdateTime;
		VUpdatePerson = vUpdatePerson;
		VFillingCompanyName = vFillingCompanyName;
		VOriginalNum = vOriginalNum;
		VUpdateNameId = vUpdateNameId;
		PId = pId;
		SNo = sNo;
		SName = sName;
		TNo = tNo;
		TName = tName;
		TOrganization = tOrganization;
		TFundsSources = tFundsSources;
		VCapitalProperty = vCapitalProperty;
		VEconomic = vEconomic;
		VFunctional = vFunctional;
		VBudgetItems = vBudgetItems;
		VReimbursement = vReimbursement;
		VRemarks = vRemarks;
		Vgnkm = vgnkm;
		Vjjkm = vjjkm;
		Vktxx = vktxx;
		Vxmxx = vxmxx;
		Vywhd = vywhd;
		VAuxAccount=VAuxAccount;
		VStatus=VStatus;
	}
	public AccountingVoucher() {
		super();
	}
	
	public String getDataFormat() {
		//处理摘要信息，确保其不含，；#
		String transToFormatStr = AccountingVoucher.transToFormatStr(VAbstract);
		String transToFormatStrTName = AccountingVoucher.transToFormatStr(TName);
		return VDate+","+VNo+","+transToFormatStr+","+VDebitAmount+","+VCreditAmount+","+TNo+","+transToFormatStrTName+","+SNo+","+SName;
		
	}
	
	public static String transToFormatStr(String str){
        while(true){
            if(str.contains(",")||str.contains(";")||str.contains("#")){
                if(str.contains(",")){
                	str=str.replaceAll(",", " ");
                }
                if(str.contains(";")){
                	str=str.replaceAll(";", " ");
                }
                if(str.contains("#")){
                    str = str.replaceAll("#", " ");
                }
                System.out.println("打印："+str);
            }else{
                break;
            }
        }
        return str;
    } 
}