package com.se.mapper;

import com.se.entity.LabInstance;
import com.se.entity.LabInstanceKey;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface LabInstanceMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table LAB_INSTANCE
     *
     * @mbg.generated Wed Oct 27 22:15:26 CST 2021
     */
    int deleteByPrimaryKey(LabInstanceKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table LAB_INSTANCE
     *
     * @mbg.generated Wed Oct 27 22:15:26 CST 2021
     */
    int insert(LabInstance record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table LAB_INSTANCE
     *
     * @mbg.generated Wed Oct 27 22:15:26 CST 2021
     */
    int insertSelective(LabInstance record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table LAB_INSTANCE
     *
     * @mbg.generated Wed Oct 27 22:15:26 CST 2021
     */
    LabInstance selectByPrimaryKey(LabInstanceKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table LAB_INSTANCE
     *
     * @mbg.generated Wed Oct 27 22:15:26 CST 2021
     */
    int updateByPrimaryKeySelective(LabInstance record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table LAB_INSTANCE
     *
     * @mbg.generated Wed Oct 27 22:15:26 CST 2021
     */
    int updateByPrimaryKey(LabInstance record);
}