package com.se.mapper;

import com.se.entity.TeacherAccount;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface TeacherAccountMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TEACHER_ACCOUNT
     *
     * @mbg.generated Wed Oct 27 22:15:26 CST 2021
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TEACHER_ACCOUNT
     *
     * @mbg.generated Wed Oct 27 22:15:26 CST 2021
     */
    int insert(TeacherAccount record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TEACHER_ACCOUNT
     *
     * @mbg.generated Wed Oct 27 22:15:26 CST 2021
     */
    int insertSelective(TeacherAccount record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TEACHER_ACCOUNT
     *
     * @mbg.generated Wed Oct 27 22:15:26 CST 2021
     */
    TeacherAccount selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TEACHER_ACCOUNT
     *
     * @mbg.generated Wed Oct 27 22:15:26 CST 2021
     */
    int updateByPrimaryKeySelective(TeacherAccount record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TEACHER_ACCOUNT
     *
     * @mbg.generated Wed Oct 27 22:15:26 CST 2021
     */
    int updateByPrimaryKey(TeacherAccount record);
}