package com.xct.media.queuing.util;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Created by Chris on 2018/5/26.
 */
public class ApiResult<T> {

    // 非"0"均为异常
    private int restCode;

    // 异常时准确值
    private String message;

    // 正常时的数据封装
    private T restData;


    public int getRestCode() {
        return restCode;
    }

    public void setRestCode(int restCode) {
        this.restCode = restCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getRestData() {
        return restData;
    }

    public void setRestData(T restData) {
        this.restData = restData;
    }

    /**
     * 成功返回结果
     * 
     * @param data 返回数据
     * @param <T> 数据类型
     * @return ApiResult对象
     */
    public static <T> ApiResult<T> success(T data) {
        ApiResult<T> result = new ApiResult<>();
        result.setRestCode(0);
        result.setMessage("success");
        result.setRestData(data);
        return result;
    }

    /**
     * 失败返回结果
     * 
     * @param message 错误信息
     * @param <T> 数据类型
     * @return ApiResult对象
     */
    public static <T> ApiResult<T> error(String message) {
        ApiResult<T> result = new ApiResult<>();
        result.setRestCode(-1);
        result.setMessage(message);
        result.setRestData(null);
        return result;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
