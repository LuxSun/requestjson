package com.luxsuen.requestjson.exception;

public enum ExceptionCode {

    UNKNOW_ERROR(1,"未知错误"),
    SYSTEEM_ERROR(0,"系统错误");

    private final int code;
    private final String desc;

    ExceptionCode(int code, String desc){
        this.code = code;
        this.desc = desc;
    }

    public int getCode(){
        return code;
    }

    public String getDesc(){
        return desc;
    }
}
