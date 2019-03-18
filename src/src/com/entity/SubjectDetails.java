package com.entity;



/**
 * 科目明细
 * SubjectDetails entity. @author MyEclipse Persistence Tools
 */

public class SubjectDetails  implements java.io.Serializable {


    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;
     private String vId	;
     private String oneSubjectName;
     private String sixSubjectName;
     private String debitAmount;
     private String creditAmount;
     private String abstract_;
     private String singleMan;
     private String brokerageMan;
     private String topicNo;
     private String topicName;
     private String periodName;



    // Constructors

    /** default constructor */
    public SubjectDetails() {
    }

    
    /** full constructor */
    public SubjectDetails(String vId, String oneSubjectName, String sixSubjectName, String debitAmount, String creditAmount, String abstract_, String singleMan, String brokerageMan, String topicNo, String topicName, String periodName) {
        this.vId = vId;
        this.oneSubjectName = oneSubjectName;
        this.sixSubjectName = sixSubjectName;
        this.debitAmount = debitAmount;
        this.creditAmount = creditAmount;
        this.abstract_ = abstract_;
        this.singleMan = singleMan;
        this.brokerageMan = brokerageMan;
        this.topicNo = topicNo;
        this.topicName = topicName;
        this.periodName = periodName;
    }

   
    // Property accessors

    public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
    }

   

    public String getvId() {
		return vId;
	}


	public void setvId(String vId) {
		this.vId = vId;
	}


	public String getOneSubjectName() {
        return this.oneSubjectName;
    }
    
    public void setOneSubjectName(String oneSubjectName) {
        this.oneSubjectName = oneSubjectName;
    }

    public String getSixSubjectName() {
        return this.sixSubjectName;
    }
    
    public void setSixSubjectName(String sixSubjectName) {
        this.sixSubjectName = sixSubjectName;
    }

    public String getDebitAmount() {
        return this.debitAmount;
    }
    
    public void setDebitAmount(String debitAmount) {
        this.debitAmount = debitAmount;
    }

    public String getCreditAmount() {
        return this.creditAmount;
    }
    
    public void setCreditAmount(String creditAmount) {
        this.creditAmount = creditAmount;
    }

    public String getAbstract_() {
        return this.abstract_;
    }
    
    public void setAbstract_(String abstract_) {
        this.abstract_ = abstract_;
    }

    public String getSingleMan() {
        return this.singleMan;
    }
    
    public void setSingleMan(String singleMan) {
        this.singleMan = singleMan;
    }

    public String getBrokerageMan() {
        return this.brokerageMan;
    }
    
    public void setBrokerageMan(String brokerageMan) {
        this.brokerageMan = brokerageMan;
    }

    public String getTopicNo() {
        return this.topicNo;
    }
    
    public void setTopicNo(String topicNo) {
        this.topicNo = topicNo;
    }

    public String getTopicName() {
        return this.topicName;
    }
    
    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public String getPeriodName() {
        return this.periodName;
    }
    
    public void setPeriodName(String periodName) {
        this.periodName = periodName;
    }
   








}