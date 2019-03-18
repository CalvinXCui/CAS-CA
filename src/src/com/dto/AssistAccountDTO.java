package com.dto;
/**
 * 辅助核算dto
 * @author wangjianshuai
 */
public class AssistAccountDTO {
	
	private String economic; // 经济科目
	
	private String function; // 功能科目
	
	private String subjectInfo; // 课题信息

	private String projectInfo; // 项目信息
	
	private String ataskActivity; //业务活动

	public String getEconomic() {
		return economic;
	}

	public void setEconomic(String economic) {
		this.economic = economic;
	}

	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}

	public String getSubjectInfo() {
		return subjectInfo;
	}

	public void setSubjectInfo(String subjectInfo) {
		this.subjectInfo = subjectInfo;
	}

	public String getProjectInfo() {
		return projectInfo;
	}

	public void setProjectInfo(String projectInfo) {
		this.projectInfo = projectInfo;
	}

	public String getAtaskActivity() {
		return ataskActivity;
	}

	public void setAtaskActivity(String ataskActivity) {
		this.ataskActivity = ataskActivity;
	}

}
