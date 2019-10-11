package com.example.common.entry.user;

import io.swagger.annotations.ApiModel;

import java.io.Serializable;

/**
 * <p> 类说明 </p>
 * @author hfx
 * Date: 2019年10月11日16:41:53
 * @version 1.01
 * app登陆用户信息
 */
@ApiModel(description = "用户参数")
public class UserInfo implements Serializable{

    /** 属性说明 */
    private static final long serialVersionUID = 1L;

    private Long id;// 主键
	private String userAccount;// 用户账号
	private String userName;// 用户名
	private String phone;// 手机号
	private String role;// 角色
	private String token;
	private String encoder;
	private Integer position;// 职务(1：副部长  2：主任  3：副主任  4：科长  5：技术工程师  6：作业班长  7：作业员工  8：管员  9：司机  10：文员)
	private String department;// 所属部门

	public Integer getPosition() {
		return position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getEncoder() {
		return encoder;
	}
	public void setEncoder(String encoder) {
		this.encoder = encoder;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUserAccount() {
		return userAccount;
	}
	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}

}
