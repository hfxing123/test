package com.example.common.message;

import com.example.common.exception.BusinessException;

public class Resp {

    private static String DEFAULT_ERROR_MESSAGE="系统错误";

    public static ResponseMessage SUCCESS() {
        return new ResponseMessage();
    }

    public static ResponseMessage SUCCESSMessage(String message){return new ResponseMessage(true,message);}

    public static ResponseMessage SUCCESS(Object result) {
        return new ResponseMessage(result);
    }

    public static ResponseMessage SUCCESS(Object result, String message) {
        return new ResponseMessage(result,message);
    }

    public static ResponseMessage FAIL(String message) {
        return new ResponseMessage(false,message);
    }

    public static ResponseMessage EXCEPTION_FAIL(Exception e)
    {
        if(e instanceof BusinessException)
        {
            return new ResponseMessage(false,e.getMessage());
        }
        else
        {
            return new ResponseMessage(false,DEFAULT_ERROR_MESSAGE,e.getMessage());
        }
    }

    public static ResponseMessage FAIL(Object data) {
        return new ResponseMessage(false, data);
    }

    public static ResponseMessage SUCCESS(boolean isSuccess, String message, Object result) {
        return new ResponseMessage(isSuccess,message,result);
    }
}
