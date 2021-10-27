package com.se.mapper;

import com.se.entity.Report;
import com.se.entity.ReportKey;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ReportMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table REPORT
     *
     * @mbg.generated Wed Oct 27 22:15:26 CST 2021
     */
    int deleteByPrimaryKey(ReportKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table REPORT
     *
     * @mbg.generated Wed Oct 27 22:15:26 CST 2021
     */
    int insert(Report record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table REPORT
     *
     * @mbg.generated Wed Oct 27 22:15:26 CST 2021
     */
    int insertSelective(Report record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table REPORT
     *
     * @mbg.generated Wed Oct 27 22:15:26 CST 2021
     */
    Report selectByPrimaryKey(ReportKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table REPORT
     *
     * @mbg.generated Wed Oct 27 22:15:26 CST 2021
     */
    int updateByPrimaryKeySelective(Report record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table REPORT
     *
     * @mbg.generated Wed Oct 27 22:15:26 CST 2021
     */
    int updateByPrimaryKey(Report record);
}