package com.se.entity;

public class Course {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column COURSE.ID
     *
     * @mbg.generated Wed Oct 27 22:15:26 CST 2021
     */
    private String id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column COURSE.NAME
     *
     * @mbg.generated Wed Oct 27 22:15:26 CST 2021
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column COURSE.CREDITS
     *
     * @mbg.generated Wed Oct 27 22:15:26 CST 2021
     */
    private Integer credits;

    private String teacherId;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COURSE
     *
     * @mbg.generated Wed Oct 27 22:15:26 CST 2021
     */
    public Course(String id, String name, Integer credits, String teacherId) {
        this.id = id;
        this.name = name;
        this.credits = credits;
        this.teacherId = teacherId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table COURSE
     *
     * @mbg.generated Wed Oct 27 22:15:26 CST 2021
     */
    public Course() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column COURSE.ID
     *
     * @return the value of COURSE.ID
     *
     * @mbg.generated Wed Oct 27 22:15:26 CST 2021
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column COURSE.ID
     *
     * @param id the value for COURSE.ID
     *
     * @mbg.generated Wed Oct 27 22:15:26 CST 2021
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }


    public String getTeacherId() {
        return teacherId;
    }


    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId == null ? null : teacherId.trim();
    }
    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column COURSE.NAME
     *
     * @return the value of COURSE.NAME
     *
     * @mbg.generated Wed Oct 27 22:15:26 CST 2021
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column COURSE.NAME
     *
     * @param name the value for COURSE.NAME
     *
     * @mbg.generated Wed Oct 27 22:15:26 CST 2021
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column COURSE.CREDITS
     *
     * @return the value of COURSE.CREDITS
     *
     * @mbg.generated Wed Oct 27 22:15:26 CST 2021
     */
    public Integer getCredits() {
        return credits;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column COURSE.CREDITS
     *
     * @param credits the value for COURSE.CREDITS
     *
     * @mbg.generated Wed Oct 27 22:15:26 CST 2021
     */
    public void setCredits(Integer credits) {
        this.credits = credits;
    }
}