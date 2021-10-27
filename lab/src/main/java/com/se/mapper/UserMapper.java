package com.se.mapper;

import com.se.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


@Mapper
@Repository
public interface UserMapper {
    User selectPasswordByName(@Param("userName") String userName, @Param("password") String password);
}
