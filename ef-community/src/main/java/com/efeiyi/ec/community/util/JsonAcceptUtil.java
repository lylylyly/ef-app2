package com.efeiyi.ec.community.util;


import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2016/1/13.
 */
public class JsonAcceptUtil {

    public static Object receiveJson(HttpServletRequest request, Class clazz) throws Exception {
//        request.setCharacterEncoding("utf-8");
        byte[] bytes = new byte[request.getContentLength()];
        request.getInputStream().read(bytes);
//        String param = new String(bytes, "UTF-8");
        String json = new String(bytes);
        Gson gson = new Gson();
        return gson.fromJson(json, clazz);
    }

//    public String name;
//    public JsonAcceptUtil jsonAcceptUtil;
//
//    public static void main(String[] ar) {
//        Gson gson = new Gson();
//        JsonAcceptUtil j1 = new JsonAcceptUtil();
//        JsonAcceptUtil j2 = new JsonAcceptUtil();
//        j1.name = "haha";
//        j2.name = "hehe";
//        j1.jsonAcceptUtil = j2;
//        j2.jsonAcceptUtil = j1;
//        System.out.println(JSONObject.toJSONString(j1));
//        System.out.println(JSONObject.toJSONString(j2));
//    }
}
