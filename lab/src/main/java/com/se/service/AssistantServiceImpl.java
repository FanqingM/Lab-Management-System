package com.se.service;

import com.se.entity.AssistantKey;
import com.se.mapper.AssistantMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AssistantServiceImpl implements AssistantService{
    @Autowired
    private AssistantMapper assistantMapper;
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ASSISTANT
     *
     * @mbg.generated Wed Oct 27 09:24:02 CST 2021
     */
    @Override
    public int deleteByPrimaryKey(AssistantKey key) {
        return assistantMapper.deleteByPrimaryKey(key);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ASSISTANT
     *
     * @mbg.generated Wed Oct 27 09:24:02 CST 2021
     */
    @Override
    public int insert(AssistantKey record) {
        return assistantMapper.insert(record);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ASSISTANT
     *
     * @mbg.generated Wed Oct 27 09:24:02 CST 2021
     */
    @Override
    public int insertSelective(AssistantKey record) {
        return assistantMapper.insertSelective(record);
    }

}
