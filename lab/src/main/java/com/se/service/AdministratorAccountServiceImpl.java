package com.se.service;

import com.se.entity.AdministratorAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdministratorAccountServiceImpl implements AdministratorAccountService{
    @Autowired
    private AdministratorAccountService administratorAccountService;
    public int deleteByPrimaryKey(String id) {
        return administratorAccountService.deleteByPrimaryKey(id);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ADMINISTRATOR_ACCOUNT
     *
     * @mbg.generated Wed Oct 27 09:24:02 CST 2021
     */
    public int insert(AdministratorAccount record) {
        return administratorAccountService.insert(record);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ADMINISTRATOR_ACCOUNT
     *
     * @mbg.generated Wed Oct 27 09:24:02 CST 2021
     */
    public int insertSelective(AdministratorAccount record) {
        return administratorAccountService.insertSelective(record);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ADMINISTRATOR_ACCOUNT
     *
     * @mbg.generated Wed Oct 27 09:24:02 CST 2021
     */
    public AdministratorAccount selectByPrimaryKey(String id) {
        return administratorAccountService.selectByPrimaryKey(id);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ADMINISTRATOR_ACCOUNT
     *
     * @mbg.generated Wed Oct 27 09:24:02 CST 2021
     */
    public int updateByPrimaryKeySelective(AdministratorAccount record) {
        return administratorAccountService.updateByPrimaryKeySelective(record);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ADMINISTRATOR_ACCOUNT
     *
     * @mbg.generated Wed Oct 27 09:24:02 CST 2021
     */
    public int updateByPrimaryKey(AdministratorAccount record) {
        return administratorAccountService.updateByPrimaryKey(record);
    }

}
