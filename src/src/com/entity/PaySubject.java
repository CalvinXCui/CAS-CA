package com.entity;

/**
 * 支出分类科目(功能分类和经济分类)
 * PaySubject entity. @author MyEclipse Persistence Tools
 */

public class PaySubject  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	 private static final long serialVersionUID = 7168824972533570462L;
	
	 private String id;                //编号
     private String num;               //科目编码
     private String name;              //科目名称
     private String type;              //类型(功能分类、经济分类)
     private String leadersNum;        //上级编码

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLeadersNum() {
		return leadersNum;
	}

	public void setLeadersNum(String leadersNum) {
		this.leadersNum = leadersNum;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public PaySubject(String id, String num, String name, String type, String leadersNum) {
		super();
		this.id = id;
		this.num = num;
		this.name = name;
		this.type = type;
		this.leadersNum = leadersNum;
	}

	public PaySubject() {
		super();
	}

	@Override
	public String toString() {
		return "PaySubject [id=" + id + ", num=" + num + ", name=" + name + ", type=" + type + ", leadersNum="
				+ leadersNum + "]";
	}
}