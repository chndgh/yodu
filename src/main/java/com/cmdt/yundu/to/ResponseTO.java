package com.cmdt.yundu.to;

/**
 * Created by dingguohua on 2017/5/25.
 */
public class ResponseTO {
    private String status;
    private String message;
    private Object result;

    private static ResponseTO response = new ResponseTO();

    public static String success(String result){
        response.status = "1";
        response.message = "执行成功！";
        response.result = result;
        return response.toString();
    }

    public static ResponseTO successResp(Object result){
        response.status = "1";
        response.message = "执行成功！";
        response.result = result;
        return response;
    }

    public static String success(){
        response.status = "1";
        response.message = "执行成功！";
        response.result = null;
        return response.toString();
    }

    public static ResponseTO successResp(){
        response.status = "1";
        response.message = "执行成功！";
        response.result = null;
        return response;
    }

    public static String fail(String message,String result){
        response.status="0";
        response.message = message;
        response.result = result;
        return response.toString();

    }

    public static String fail(String message){
        response.status="0";
        response.message = message;
        response.result = null;
        return response.toString();
    }

    public static ResponseTO failResp(String message){
        response.status="0";
        response.message = message;
        response.result = null;
        return response;
    }


    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public Object getResult() {
        return result;
    }
    public void setResult(String result) {
        this.result = result;
    }
    @Override
    public String toString() {
        return "{\"status\":\"" + status + "\", \"message\":\"" + message + "\", \"result\":" + result + "}";
    }
}
