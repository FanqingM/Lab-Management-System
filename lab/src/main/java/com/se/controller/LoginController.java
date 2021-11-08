package com.se.controller;


import com.se.dto.UserDTO;
import com.se.service.LoginService;
import com.se.service.LoginServiceImpl;
import com.se.util.JWTUtils;
import com.se.util.Msg;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("login")
public class LoginController {

    @Autowired
    LoginService loginService;

//    @ApiOperation(value="登录（返回TOKEN）")
    @Operation
    @PostMapping
    public Msg loginWithToken(@RequestBody UserDTO userDTO){ //传过来的json包装成实体
//        System.out.println(userDTO);
        return loginService.Login(userDTO);
    }

}
