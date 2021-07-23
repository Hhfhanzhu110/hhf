package com.exm.demo.entity;

import lombok.Data;

import java.util.HashMap;

@Data
public class AxiosResult extends HashMap<String, Object> {
    private static final long serialVersionUID = 1L;

    /**
     * 初始化一个新创建的 Message 对象
     */
    public AxiosResult() {
    }

    /**
     * 返回错误消息
     *
     * @return 错误消息
     */
    public static AxiosResult error() {
        return error(1, "操作失败");
    }

    /**
     * 返回错误消息
     *
     * @param msg 内容
     * @return 错误消息
     */
    public static AxiosResult error(String msg) {
        return error(500, msg);
    }

    /**
     * 返回错误消息
     *
     * @param code 错误码
     * @param msg  内容
     * @return 错误消息
     */
    public static AxiosResult error(int code, String msg) {
        AxiosResult json = new AxiosResult();
        json.put("code", code);
        json.put("message", msg);
        return json;
    }

    /**
     * 返回成功消息
     *
     * @param msg 内容
     * @return 成功消息
     */
    public static AxiosResult success(String msg) {
        AxiosResult json = new AxiosResult();
        json.put("message", msg);
        json.put("code", 0);
        return json;
    }

    /**
     * 返回成功消息
     *
     * @param msg    内容
     * @param object 实体
     * @return 成功消息
     */
    public static AxiosResult success(String msg, Object object) {
        AxiosResult json = new AxiosResult();
        json.put("message", msg);
        json.put("object", object);
        json.put("code", 0);
        return json;
    }

    /**
     * 返回成功消息
     *
     * @return 成功消息
     */
    public static AxiosResult success() {
        return AxiosResult.success("操作成功");
    }

    /**
     * 返回成功消息
     *
     * @param key   键值
     * @param value 内容
     * @return 成功消息
     */
    @Override
    public AxiosResult put(String key, Object value) {
        super.put(key, value);
        return this;
    }

    /**
     * 返回消息
     *
     * @param message 内容
     * @return 消息
     */
    public static AxiosResult result(String message, Integer status) {
        AxiosResult json = new AxiosResult();
        json.put("message", message);
        json.put("code", status);
        return json;
    }
}
