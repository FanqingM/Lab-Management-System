package com.se.util;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Msg {
    // 状态码: 200-成功 400-失败 500-异常
    private int status;
    // 提示信息
    private String message;

    // 用户返回给浏览器的数据
    private Map<String, Object> data = new HashMap<String, Object>();

    public Msg(int status, String message, Map<String, Object> data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public Msg() {
    }

    public void add(String title, Object content){
        data.put(title,content);
    }
}
