package com.example.yin.common;

import com.alibaba.fastjson.JSONObject;

public class FatalMessage {
//    FatalMessage类，用于定义致命错误消息。
    JSONObject jsonObject = new JSONObject();

    public FatalMessage(String message) {
        jsonObject.put("code", 500); // 包括错误代码（code）
        jsonObject.put("message", message);// 错误信息（message）
        jsonObject.put("success", false);// 是否成功（success）
        jsonObject.put("type", "error");// 错误类型（type）
        jsonObject.put("data", null);// 错误数据（data）

    }
//    通过使用JSONObject类，可以方便地构建和操作JSON对象，将其用作致命错误消息的返回格式。
    public JSONObject getMessage() {
        return jsonObject;
    }
}