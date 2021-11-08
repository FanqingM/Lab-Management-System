package com.se.intercepter;

import com.se.util.JWTUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

public class JWTIntercepter implements HandlerInterceptor {

    @Resource
    JWTUtils jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) {
        // 从 http 请求头中取出 token
//        System.out.println(httpServletRequest.g);
        String newtoken = httpServletRequest.getHeader("Authorization");
        String token;

        if (newtoken != null && newtoken.indexOf("Bearer") == 0) {
            token = newtoken.substring(7);
        } else {
            token = newtoken;
        }

        // 如果不是映射到方法直接通过
        if (!(object instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) object;
        Method method = handlerMethod.getMethod();
        //检查有没有需要用户权限的注解
        if (method.isAnnotationPresent(JwtToken.class)) {
            JwtToken jwtToken = method.getAnnotation(JwtToken.class);
            if (token == null) {
                throw new RuntimeException("无token，请重新登录");
            }else{
                jwtUtil.checkSign(token);
                if (jwtToken.authority().equals(JWTUtils.getAuthority(token))){
                    return true;
                }else {
                    throw new RuntimeException("您没有权限执行该操作");
                }
            }
//            jwtUtil.checkSign(token);
//            switch (jwtToken.authority().toString().equals(JWTUtils.getAuthority(token)))
//            if (jwtToken.required() == ) {
//                // 执行认证
//                if (token == null) {
//                    throw new RuntimeException("无token，请重新登录");
//                }
//                // 验证 token
//                jwtUtil.checkSign(token);
//            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
