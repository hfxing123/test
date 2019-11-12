package com.example.common.message;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Created by qiong on 2016/3/3.
 */
@ApiModel
public class ResponseMessage implements Serializable {
    /** 属性说明 */
    private static final long serialVersionUID = 1L;
    /**
     * 消息
     */
    @ApiModelProperty(value = "提示信息")
    private String message="";
    @ApiModelProperty(value = "是否成功")
    private boolean isSuccess = true;
    /**
     * 结果集
     */
    @ApiModelProperty(value = "结果集")
    private Object data;

    public ResponseMessage() {

    }
    
    public ResponseMessage(Object data) {
        super();
        this.data = data;
    }

    public ResponseMessage(Object data, String message) {
        super();
        this.data = data;
        this.message = message;
    }

    public ResponseMessage(boolean isSuccess, String message) {
        super();
        this.isSuccess = isSuccess;
        this.message = message;
    }

    public ResponseMessage(boolean isSuccess, Object data) {
        super();
        this.isSuccess = isSuccess;
        this.data = data;
    }

    public ResponseMessage(boolean isSuccess, String message, Object data) {
        super();
        this.isSuccess = isSuccess;
        this.message = message;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
