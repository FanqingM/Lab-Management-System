package com.se.service;

import com.se.dto.CoursePutINO;
import com.se.entity.Course;

public interface CourseService {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COURSE
     *
     * @mbg.generated Wed Oct 27 09:24:02 CST 2021
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COURSE
     *
     * @mbg.generated Wed Oct 27 09:24:02 CST 2021
     */
    int insert(Course record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COURSE
     *
     * @mbg.generated Wed Oct 27 09:24:02 CST 2021
     */
    int insertSelective(Course record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COURSE
     *
     * @mbg.generated Wed Oct 27 09:24:02 CST 2021
     */
    Course selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COURSE
     *
     * @mbg.generated Wed Oct 27 09:24:02 CST 2021
     */
    int updateByPrimaryKeySelective(Course record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COURSE
     *
     * @mbg.generated Wed Oct 27 09:24:02 CST 2021
     */
    int updateByPrimaryKey(Course record);

    int updateTeacher(CoursePutINO coursePutINO);
}
