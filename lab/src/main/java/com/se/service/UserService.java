package com.se.service;

import com.se.entity.User;

/**
 * @version : V1.0
 * @ClassName: UserService
 * @Description: TODO
 * @Auther: wangqiang
 * @Date: 2020/2/25 21:32
 */
public interface UserService {
    User selectPasswordByName(String userName,String password);
}
