package com.jalinyiel.petrichor.core;

public class ResponseResult<T> {
    protected int code;
    protected String msg;
    protected T data;

    public ResponseResult(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> ResponseResult<T> successResult(AbsResultCode rc) {
        return (ResponseResult<T>) successResult(rc, (Object) null);
    }

    public static <T> ResponseResult<T> successResult(AbsResultCode rc, T data) {
        return new ResponseResult(rc.getCode(), rc.getMsg(), data);
    }

    public static <T> ResponseResult<T> failedResult(int code, String msg) {
        return new ResponseResult(code, msg, (Object) null);
    }

    public static <T> ResponseResult<T> failedResult(AbsResultCode rc) {
        return failedResult(rc, (String) null);
    }

    public static <T> ResponseResult<T> failedResult(AbsResultCode rc, String appendMsg) {
        return new ResponseResult(rc.getCode(), appendMsg, null);
    }

    public boolean isSuccess() {
        return this.code == CommonResultCode.SUCCESS.getCode();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}