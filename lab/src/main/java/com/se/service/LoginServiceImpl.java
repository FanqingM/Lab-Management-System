package com.se.service;

import com.se.dto.UserDTO;
import com.se.mapper.LoginMapper;
import com.se.util.JWTUtils;
import com.se.util.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    LoginMapper loginMapper;

    @Override
    public Msg Login(UserDTO userDTO) {
        System.out.println(userDTO);
        Msg msg = new Msg();
        if (loginMapper.UnactivatedLogin(userDTO.getId())!=null){
            msg.setStatus(500);
            msg.setMessage("此账号还未激活，请等待管理员审核！");
            return msg;
        }else{
            String loginState;
            switch (userDTO.getAuthority()){
                case 0:
                    loginState = loginMapper.StudentLogin(userDTO.getId(), userDTO.getPassword());
                    break;
                case 1:
                    loginState = loginMapper.AdministratorLogin(userDTO.getId(), userDTO.getPassword());
                    break;
                case 2:
                    loginState = loginMapper.TeacherLogin(userDTO.getId(), userDTO.getPassword());
                    break;
                default:
                    loginState = null;
                    break;
            }
            if (loginState==null){
                msg.setStatus(400);
                msg.setMessage("登陆失败，用户名或密码有误！");
            }else{
                msg.setStatus(200);
                msg.setMessage("登陆成功，欢迎使用实验管理系统！");
                msg.add("token", JWTUtils.getToken(userDTO.getId(),userDTO.getAuthority()));
            }
        }
        return msg;
    }
}
