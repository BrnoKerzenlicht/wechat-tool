package com.june.common;

import com.alibaba.fastjson.serializer.SerializeConfig;

public enum ReturnCodeEnum {

    SUCCESS(200, "success"), //
    UNKNOWN(400, "未知状态码"), //
    ERROR_SYSTEM(501, "服务器异常"), //
    ERROR_PARAM(502, "参数错误"), //
    EMPTY_PARAM(503, "请求参数为空"), //
    FAILURE_OPERATE(504, "操作失败"), //
    ;

    private int retcode;
    private String retdesc;

    //用于序列化，禁止修改
    {
        SerializeConfig.globalInstance.configEnumAsJavaBean(this.getClass());
    }

    ReturnCodeEnum(int retcode, String retdesc) {
        this.retcode = retcode;
        this.retdesc = retdesc;
    }

    public int getRetcode() {
        return retcode;
    }

    public String getRetdesc() {
        return retdesc;
    }

}
