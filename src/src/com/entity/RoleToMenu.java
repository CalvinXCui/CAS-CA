package com.entity;

import java.io.Serializable;

public class RoleToMenu implements Serializable{
	private static final long serialVersionUID = 6935867174087844229L;
	/**
	 * 
	 */
	private String id;
	private String menuId;
	private String roleId;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	
}
