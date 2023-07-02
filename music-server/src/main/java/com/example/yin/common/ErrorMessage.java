package com.example.yin.common;

import com.alibaba.fastjson.JSONObject;

public class ErrorMessage {
//    该类使用com.alibaba.fastjson.JSONObject来创建一个JSON对象来表示错误消息。
    JSONObject jsonObject = new JSONObject();

    // 构造函数，接受一个错误信息作为参数
    public ErrorMessage(String message) {
        // 向 jsonObject 添加键值对，表示错误信息的相关属性
        jsonObject.put("code", 200); // 错误代码，这里示例为 200
        jsonObject.put("message", message); // 错误信息，由构造函数的参数指定
        jsonObject.put("success", false); // 是否成功，这里示例为 false
        jsonObject.put("type", "error"); // 错误类型，这里示例为 error
        jsonObject.put("data", null); // 错误数据，这里示例为 null
    }

    // 获取错误信息的方法
    public JSONObject getMessage() {
        return jsonObject;
    }
}