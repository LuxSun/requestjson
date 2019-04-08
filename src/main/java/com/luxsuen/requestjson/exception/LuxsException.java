package com.luxsuen.requestjson.exception;

public class LuxsException  extends Exception {

    private int status;
    private String msg;
    private Object data;

    public LuxsException(int status){
        this.status = status;
    }

    public LuxsException(int status, Object data){
        this.status = status;
        this.data = data;
    }

    public LuxsException(int status, String msg){
        this.status = status;
        this.msg = msg;
    }

    public LuxsException(int status, String msg, Object data){
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "LuxsException{" +
                "status=" + status +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}