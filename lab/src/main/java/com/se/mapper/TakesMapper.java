package com.se.mapper;

import com.se.entity.Takes;
import com.se.entity.TakesKey;

public interface TakesMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TAKES
     *
     * @mbg.generated Wed Oct 27 21:50:19 CST 2021
     */
    int deleteByPrimaryKey(TakesKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TAKES
     *
     * @mbg.generated Wed Oct 27 21:50:19 CST 2021
     */
    int insert(Takes record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TAKES
     *
     * @mbg.generated Wed Oct 27 21:50:19 CST 2021
     */
    int insertSelective(Takes record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TAKES
     *
     * @mbg.generated Wed Oct 27 21:50:19 CST 2021
     */
    Takes selectByPrimaryKey(TakesKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TAKES
     *
     * @mbg.generated Wed Oct 27 21:50:19 CST 2021
     */
    int updateByPrimaryKeySelective(Takes record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TAKES
     *
     * @mbg.generated Wed Oct 27 21:50:19 CST 2021
     */
    int updateByPrimaryKey(Takes record);
}