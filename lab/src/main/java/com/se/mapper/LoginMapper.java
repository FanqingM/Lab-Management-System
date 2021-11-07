package com.se.mapper;

import com.se.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface LoginMapper {

    @Select("select ID from UNACTIVATED_ACCOUNT where ID=#{id}")
    String UnactivatedLogin(String id);

    @Select("select ID from STUDENT_ACCOUNT where ID=#{id} and password=#{password}")
    String StudentLogin(String id,String password);

    @Select("select ID from ADMINISTRATOR_ACCOUNT where ID=#{id} and password=#{password}")
    String AdministratorLogin(String id,String password);

    @Select("select ID from TEACHER_ACCOUNT where ID=#{id} and password=#{password}")
    String TeacherLogin(String id,String password);

}
