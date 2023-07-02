package com.example.yin.common;

//该类使用com.alibaba.fastjson.JSONObject来创建一个JSON对象来表示成功消息。
import com.alibaba.fastjson.JSONObject;

public class SuccessMessage<T> {
    JSONObject jsonObject = new JSONObject();

    public SuccessMessage(String message) {
        jsonObject.put("code", 200);
        jsonObject.put("message", message);
        jsonObject.put("success", true);
        jsonObject.put("type", "success");
        jsonObject.put("data", null);
    }
//    data参数是一个泛型类型，表示附加的数据。
    public SuccessMessage(String message, T data) {
        jsonObject.put("code", 200);
        jsonObject.put("message", message);
        jsonObject.put("success", true);
        jsonObject.put("type", "success");
        jsonObject.put("data", data);
    }
//    getMessage()方法是SuccessMessage类的一个方法，用于获取成功消息的JSON对象。
    public JSONObject getMessage() {
        return jsonObject;
    }
}
