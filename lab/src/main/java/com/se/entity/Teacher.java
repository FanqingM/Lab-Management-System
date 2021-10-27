package com.se.entity;

public class Teacher {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TEACHER.ID
     *
     * @mbg.generated Wed Oct 27 22:15:26 CST 2021
     */
    private String id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TEACHER.NAME
     *
     * @mbg.generated Wed Oct 27 22:15:26 CST 2021
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TEACHER.SCHOOL_NAME
     *
     * @mbg.generated Wed Oct 27 22:15:26 CST 2021
     */
    private String schoolName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TEACHER.IS_SENIOR_TEACHER
     *
     * @mbg.generated Wed Oct 27 22:15:26 CST 2021
     */
    private Boolean isSeniorTeacher;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TEACHER
     *
     * @mbg.generated Wed Oct 27 22:15:26 CST 2021
     */
    public Teacher(String id, String name, String schoolName, Boolean isSeniorTeacher) {
        this.id = id;
        this.name = name;
        this.schoolName = schoolName;
        this.isSeniorTeacher = isSeniorTeacher;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TEACHER
     *
     * @mbg.generated Wed Oct 27 22:15:26 CST 2021
     */
    public Teacher() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TEACHER.ID
     *
     * @return the value of TEACHER.ID
     *
     * @mbg.generated Wed Oct 27 22:15:26 CST 2021
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TEACHER.ID
     *
     * @param id the value for TEACHER.ID
     *
     * @mbg.generated Wed Oct 27 22:15:26 CST 2021
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TEACHER.NAME
     *
     * @return the value of TEACHER.NAME
     *
     * @mbg.generated Wed Oct 27 22:15:26 CST 2021
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TEACHER.NAME
     *
     * @param name the value for TEACHER.NAME
     *
     * @mbg.generated Wed Oct 27 22:15:26 CST 2021
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TEACHER.SCHOOL_NAME
     *
     * @return the value of TEACHER.SCHOOL_NAME
     *
     * @mbg.generated Wed Oct 27 22:15:26 CST 2021
     */
    public String getSchoolName() {
        return schoolName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TEACHER.SCHOOL_NAME
     *
     * @param schoolName the value for TEACHER.SCHOOL_NAME
     *
     * @mbg.generated Wed Oct 27 22:15:26 CST 2021
     */
    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName == null ? null : schoolName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TEACHER.IS_SENIOR_TEACHER
     *
     * @return the value of TEACHER.IS_SENIOR_TEACHER
     *
     * @mbg.generated Wed Oct 27 22:15:26 CST 2021
     */
    public Boolean getIsSeniorTeacher() {
        return isSeniorTeacher;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TEACHER.IS_SENIOR_TEACHER
     *
     * @param isSeniorTeacher the value for TEACHER.IS_SENIOR_TEACHER
     *
     * @mbg.generated Wed Oct 27 22:15:26 CST 2021
     */
    public void setIsSeniorTeacher(Boolean isSeniorTeacher) {
        this.isSeniorTeacher = isSeniorTeacher;
    }
}