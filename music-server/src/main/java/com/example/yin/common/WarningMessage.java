package com.example.yin.common;

import com.alibaba.fastjson.JSONObject;

public class WarningMessage {
//    该类使用com.alibaba.fastjson.JSONObject来创建一个JSON对象来表示警告消息
    JSONObject jsonObject = new JSONObject();

    public WarningMessage(String message) {
        jsonObject.put("code", 200);   // 警告代码（code）
        jsonObject.put("message", message); // 警告信息（message）
        jsonObject.put("success", false); // 是否成功（success）
        jsonObject.put("type", "warning"); // 警告类型（type）
        jsonObject.put("data", null); // 警告数据（data）
    }
//可以方便地构建和操作JSON对象，将其用作警告消息的返回格式。
    public JSONObject getMessage() {
        return jsonObject;
    }
}
