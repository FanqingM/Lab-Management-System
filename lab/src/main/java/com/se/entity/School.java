package com.se.entity;

public class School {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SCHOOL.NAME
     *
     * @mbg.generated Wed Oct 27 22:15:26 CST 2021
     */
    private String name;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SCHOOL
     *
     * @mbg.generated Wed Oct 27 22:15:26 CST 2021
     */
    public School(String name) {
        this.name = name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SCHOOL
     *
     * @mbg.generated Wed Oct 27 22:15:26 CST 2021
     */
    public School() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SCHOOL.NAME
     *
     * @return the value of SCHOOL.NAME
     *
     * @mbg.generated Wed Oct 27 22:15:26 CST 2021
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SCHOOL.NAME
     *
     * @param name the value for SCHOOL.NAME
     *
     * @mbg.generated Wed Oct 27 22:15:26 CST 2021
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}