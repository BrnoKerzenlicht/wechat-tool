package com.june.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.io.Serializable;

public class ResultData<T> implements Serializable {
    private static final long serialVersionUID = -4888233333559529908L;
    private T data;
    private Integer retcode;
    private String retdesc;

    public ResultData() {
        this.data = null;
    }

    public ResultData(Integer retcode, String retdesc) {
        this.data = null;
        this.retcode = retcode;
        this.retdesc = retdesc;
    }

    public ResultData(int retcode, String retdesc, T data) {
        this.data = null;
        this.retcode = retcode;
        this.retdesc = retdesc;
        this.data = data;
    }

    public ResultData(Object returnEnum) {
        this.data = null;
        Class clazz = returnEnum.getClass();
        if (!clazz.isEnum()) {
            this.retcode = 100;
            this.retdesc = "未知状态码";
        }
        else {
            JSONObject jsonObject = JSON.parseObject(JSONObject.toJSONString(returnEnum));
            this.retcode = jsonObject.getInteger("retcode");
            this.retdesc = jsonObject.getString("retdesc");
        }

    }

    public ResultData(Object returnEnum, T data) {
        this(returnEnum);
        this.data = data;
    }

    public Integer getRetcode() {
        return this.retcode;
    }

    public void setRetcode(Integer retcode) {
        this.retcode = retcode;
    }

    public String getRetdesc() {
        return this.retdesc;
    }

    public void setRetdesc(String retdesc) {
        this.retdesc = retdesc;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResultData{data=" + this.data + ", retcode=" + this.retcode + ", retdesc='" + this.retdesc + '\'' + '}';
    }
}
