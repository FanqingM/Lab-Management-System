package com.se.mapper;

import com.se.entity.Teacher;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface TeacherMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TEACHER
     *
     * @mbg.generated Wed Oct 27 22:15:26 CST 2021
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TEACHER
     *
     * @mbg.generated Wed Oct 27 22:15:26 CST 2021
     */
    int insert(Teacher record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TEACHER
     *
     * @mbg.generated Wed Oct 27 22:15:26 CST 2021
     */
    int insertSelective(Teacher record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TEACHER
     *
     * @mbg.generated Wed Oct 27 22:15:26 CST 2021
     */
    Teacher selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TEACHER
     *
     * @mbg.generated Wed Oct 27 22:15:26 CST 2021
     */
    int updateByPrimaryKeySelective(Teacher record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TEACHER
     *
     * @mbg.generated Wed Oct 27 22:15:26 CST 2021
     */
    int updateByPrimaryKey(Teacher record);
}