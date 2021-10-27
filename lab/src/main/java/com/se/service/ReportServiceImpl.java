package com.se.service;

import com.se.entity.AdministratorAccount;
import com.se.entity.Report;
import com.se.entity.ReportKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportServiceImpl implements ReportService{
    @Autowired
    private ReportService reportService;
    public int deleteByPrimaryKey(ReportKey key) {
        return reportService.deleteByPrimaryKey(key);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ADMINISTRATOR_ACCOUNT
     *
     * @mbg.generated Wed Oct 27 09:24:02 CST 2021
     */
    public int insert(Report record) {
        return reportService.insert(record);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ADMINISTRATOR_ACCOUNT
     *
     * @mbg.generated Wed Oct 27 09:24:02 CST 2021
     */
    public int insertSelective(Report record) {
        return reportService.insertSelective(record);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ADMINISTRATOR_ACCOUNT
     *
     * @mbg.generated Wed Oct 27 09:24:02 CST 2021
     */
    public Report selectByPrimaryKey(ReportKey key) {
        return reportService.selectByPrimaryKey(key);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ADMINISTRATOR_ACCOUNT
     *
     * @mbg.generated Wed Oct 27 09:24:02 CST 2021
     */
    public int updateByPrimaryKeySelective(Report record) {
        return reportService.updateByPrimaryKeySelective(record);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ADMINISTRATOR_ACCOUNT
     *
     * @mbg.generated Wed Oct 27 09:24:02 CST 2021
     */
    public int updateByPrimaryKey(Report record) {
        return reportService.updateByPrimaryKey(record);
    }
}
