package com.entity;

import java.io.Serializable;

/**
 * 用户角色表
 * UserRoles entity. @author MyEclipse Persistence Tools
 */

public class UserRoles implements Serializable {


    /**
	 * 
	 */
	private static final long serialVersionUID = 2314935871189425629L;
	
	private String id;
	
    private String userId;//用户id
    
    private String authorityId;//权限id
    
    private String roleName;//角色名称
    
    private String flag="1";//状态  1--有效，0--无效
    
    private String createTime;//状态  1--有效，0--无效

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAuthorityId() {
		return authorityId;
	}

	public void setAuthorityId(String authorityId) {
		this.authorityId = authorityId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
    



}