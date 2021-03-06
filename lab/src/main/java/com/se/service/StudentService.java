package com.se.service;

import com.se.dto.CourseDTO;
import com.se.dto.CourseINO;
import com.se.dto.StudentDTO;
import com.se.entity.Student;

import java.util.List;

public interface StudentService {
    List<StudentDTO> selectAllStudents();
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table STUDENT
     *
     * @mbg.generated Wed Oct 27 09:24:02 CST 2021
     */
    List<CourseDTO> selectAllCourses(CourseINO courseINO);
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table STUDENT
     *
     * @mbg.generated Wed Oct 27 09:24:02 CST 2021
     */
    int insert(Student record);
    int insertManyStudents(List<Student> students);
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table STUDENT
     *
     * @mbg.generated Wed Oct 27 09:24:02 CST 2021
     */
    int insertSelective(Student record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table STUDENT
     *
     * @mbg.generated Wed Oct 27 09:24:02 CST 2021
     */
    Student selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table STUDENT
     *
     * @mbg.generated Wed Oct 27 09:24:02 CST 2021
     */
    int updateByPrimaryKeySelective(Student record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table STUDENT
     *
     * @mbg.generated Wed Oct 27 09:24:02 CST 2021
     */
    int updateByPrimaryKey(Student record);
}
