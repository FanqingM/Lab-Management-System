package com.se.service;

import com.se.dto.UnactivatedAccountDTO;
import com.se.entity.UnactivatedAccount;

import java.util.List;

public interface UnactivatedAccountService {
    int activateAccount(String Id);

    List<UnactivatedAccountDTO> selectAllUnactivatedAccounts();
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table UNACTIVATED_ACCOUNT
     *
     * @mbg.generated Wed Oct 27 09:24:02 CST 2021
     */
    int deleteByPrimaryKey(String email);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table UNACTIVATED_ACCOUNT
     *
     * @mbg.generated Wed Oct 27 09:24:02 CST 2021
     */
    int insert(UnactivatedAccount record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table UNACTIVATED_ACCOUNT
     *
     * @mbg.generated Wed Oct 27 09:24:02 CST 2021
     */
    int insertSelective(UnactivatedAccount record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table UNACTIVATED_ACCOUNT
     *
     * @mbg.generated Wed Oct 27 09:24:02 CST 2021
     */
    UnactivatedAccount selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table UNACTIVATED_ACCOUNT
     *
     * @mbg.generated Wed Oct 27 09:24:02 CST 2021
     */
    int updateByPrimaryKeySelective(UnactivatedAccount record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table UNACTIVATED_ACCOUNT
     *
     * @mbg.generated Wed Oct 27 09:24:02 CST 2021
     */
    int updateByPrimaryKey(UnactivatedAccount record);
}
