package com.lick.utils;

/**
 * @Description：
 * @Author: lick
 * @Date: 2017年11月01日 14:56
 * @Copyright: 版权归 lick 所有
 */
public class ResultObject {
    public static final String SUCCESS_CODE = "0";
    public static final String METHOD_NAME_EMPTY = "method_name_empty";
    /** 方法名 */
    private String methodName="method_name_empty";
    /** 错误编码 */
    private String code = SUCCESS_CODE;
    /** 错误信息 */
    private String msg;
    /** 数据类型 */
    private int dataType = 0;
    /** 数据 */
    private Object data;

    /**
    * @Description: 构造函数
    * @Method:ResultObject
    * @params:[methodName, code, msg, dataType, data]
    * @returnType:
    * @Author:lick
    * @Date:
    * @Copyright: 版权归lick 所有
     */
    public ResultObject(String methodName, String code, String msg, int dataType, Object data){
        this.methodName = methodName;
        this.code = code;
        this.msg = msg;
        this.dataType = dataType;
        this.data = data;
    }

    public static final ResultObject create(String methodName, String code, String msg) {
        return new ResultObject(methodName,code, msg, 0, null);
    }
    public static final ResultObject create(Object data) {
        return new ResultObject(METHOD_NAME_EMPTY,SUCCESS_CODE, "", 0, data);
    }
    public static final ResultObject create(String methodName, String code, String msg, Object data) {
        return new ResultObject(methodName, code, msg, 0, data);
    }
    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getDataType() {
        return dataType;
    }

    public void setDataType(int dataType) {
        this.dataType = dataType;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
