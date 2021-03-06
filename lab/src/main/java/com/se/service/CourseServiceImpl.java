package com.se.service;

import com.se.dto.CoursePutINO;
import com.se.entity.Course;
import com.se.mapper.CourseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl implements CourseService{
    @Autowired
    private CourseMapper courseMapper;
    @Override
    public int updateTeacher(CoursePutINO coursePutINO) {
        Course course = new Course();
        course.setTeacherId(coursePutINO.getTeacherId());
        course.setId(coursePutINO.getCourseId());
        Course course2 = courseMapper.selectByPrimaryKey(coursePutINO.getCourseId());
        course.setName(course2.getName());
        course.setCredits(course2.getCredits());
        return courseMapper.updateByPrimaryKey(course);
    }
    @Override
    public int deleteByPrimaryKey(String id) {
        return courseMapper.deleteByPrimaryKey(id);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ADMINISTRATOR_ACCOUNT
     *
     * @mbg.generated Wed Oct 27 09:24:02 CST 2021
     */
    @Override
    public int insert(Course record) {
        return courseMapper.insert(record);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ADMINISTRATOR_ACCOUNT
     *
     * @mbg.generated Wed Oct 27 09:24:02 CST 2021
     */
    @Override
    public int insertSelective(Course record) {
        return courseMapper.insertSelective(record);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ADMINISTRATOR_ACCOUNT
     *
     * @mbg.generated Wed Oct 27 09:24:02 CST 2021
     */
    @Override
    public Course selectByPrimaryKey(String id) {
        return courseMapper.selectByPrimaryKey(id);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ADMINISTRATOR_ACCOUNT
     *
     * @mbg.generated Wed Oct 27 09:24:02 CST 2021
     */
    @Override
    public int updateByPrimaryKeySelective(Course record) {
        return courseMapper.updateByPrimaryKeySelective(record);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ADMINISTRATOR_ACCOUNT
     *
     * @mbg.generated Wed Oct 27 09:24:02 CST 2021
     */
    @Override
    public int updateByPrimaryKey(Course record) {
        return courseMapper.updateByPrimaryKey(record);
    }
}
