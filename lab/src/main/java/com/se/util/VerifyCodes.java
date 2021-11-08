package com.se.util;

import java.util.HashMap;
import java.util.Map;


public class VerifyCodes {
    private VerifyCodes(){}

    private static Map<String,String> verifyCodeMap = new HashMap<>();

    public static void add(String id,String verifyCode){
        verifyCodeMap.put(id,verifyCode);
    }

    public static void remove(String id){
        verifyCodeMap.remove(id);
    }

    public static Boolean verify(String email,String code){

        if (verifyCodeMap.get(email)!=null && verifyCodeMap.get(email).equals(code)){
            verifyCodeMap.remove(email);
            return true;
        }
        return false;
    }
}
