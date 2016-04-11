package com.efeiyi.ec.community.user.controller;

import com.efeiyi.ec.community.user.model.MyUser;
import com.efeiyi.ec.community.util.AppConfig;
import com.efeiyi.ec.community.util.JsonAcceptUtil;
import com.ming800.core.base.service.BaseManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;

/**
 * Created by Administrator on 2016/4/7.
 */
@Controller
@RequestMapping("/user")
public class SignController {

    @Autowired
    private BaseManager baseManager;

    @RequestMapping(value = {"/register.do"})
    @ResponseBody
    public String signUp(HttpServletRequest request){
        MyUser user;
        try {
            user = (MyUser)JsonAcceptUtil.receiveJson(request,MyUser.class);
            LinkedHashMap<String,String> queryMap = new LinkedHashMap<>();
            queryMap.put("username",user.getUsername());
            queryMap.put("password",user.getPassword());
            user = (MyUser)baseManager.getUniqueObjectByConditions(AppConfig.SQL_MYUSER_GET,null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    @RequestMapping(value = {"/login.do"})
    public String signIn(){

        return "";
    }

    @RequestMapping(value = {"/logout.do"})
    public String signOut(){

        return "";
    }

}
