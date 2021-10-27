package com.se.entity;

import java.util.Date;

public class LabInstance extends LabInstanceKey {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column LAB_INSTANCE.START_TIME
     *
     * @mbg.generated Wed Oct 27 22:15:26 CST 2021
     */
    private Date startTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column LAB_INSTANCE.END_TIME
     *
     * @mbg.generated Wed Oct 27 22:15:26 CST 2021
     */
    private Date endTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column LAB_INSTANCE.SUBMIT_NUM
     *
     * @mbg.generated Wed Oct 27 22:15:26 CST 2021
     */
    private Integer submitNum;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table LAB_INSTANCE
     *
     * @mbg.generated Wed Oct 27 22:15:26 CST 2021
     */
    public LabInstance(String courseId, String sectionId, String labId, Date startTime, Date endTime, Integer submitNum) {
        super(courseId, sectionId, labId);
        this.startTime = startTime;
        this.endTime = endTime;
        this.submitNum = submitNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table LAB_INSTANCE
     *
     * @mbg.generated Wed Oct 27 22:15:26 CST 2021
     */
    public LabInstance() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column LAB_INSTANCE.START_TIME
     *
     * @return the value of LAB_INSTANCE.START_TIME
     *
     * @mbg.generated Wed Oct 27 22:15:26 CST 2021
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column LAB_INSTANCE.START_TIME
     *
     * @param startTime the value for LAB_INSTANCE.START_TIME
     *
     * @mbg.generated Wed Oct 27 22:15:26 CST 2021
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column LAB_INSTANCE.END_TIME
     *
     * @return the value of LAB_INSTANCE.END_TIME
     *
     * @mbg.generated Wed Oct 27 22:15:26 CST 2021
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column LAB_INSTANCE.END_TIME
     *
     * @param endTime the value for LAB_INSTANCE.END_TIME
     *
     * @mbg.generated Wed Oct 27 22:15:26 CST 2021
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column LAB_INSTANCE.SUBMIT_NUM
     *
     * @return the value of LAB_INSTANCE.SUBMIT_NUM
     *
     * @mbg.generated Wed Oct 27 22:15:26 CST 2021
     */
    public Integer getSubmitNum() {
        return submitNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column LAB_INSTANCE.SUBMIT_NUM
     *
     * @param submitNum the value for LAB_INSTANCE.SUBMIT_NUM
     *
     * @mbg.generated Wed Oct 27 22:15:26 CST 2021
     */
    public void setSubmitNum(Integer submitNum) {
        this.submitNum = submitNum;
    }
}