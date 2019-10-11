package com.example.testapi.authorization.model;

import java.io.Serializable;

/**
 * Token的Model类
 * @author hfx
 * @date 2019年10月11日16:41:33
 */
public class TokenModel implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//用户id
    private String jobNum;

    //随机生成的uuid
    private String token;
    
    private String deptId;
    
    private String pdaNum;
    

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

	
	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getPdaNum() {
		return pdaNum;
	}

	public void setPdaNum(String pdaNum) {
		this.pdaNum = pdaNum;
	}

	public String getJobNum() {
		return jobNum;
	}

	public void setJobNum(String jobNum) {
		this.jobNum = jobNum;
	}
    
    
}
