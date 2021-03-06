package com.se.entity;

public class LabInstanceKey {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column LAB_INSTANCE.COURSE_ID
     *
     * @mbg.generated Wed Oct 27 22:15:26 CST 2021
     */
    private String courseId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column LAB_INSTANCE.SECTION_ID
     *
     * @mbg.generated Wed Oct 27 22:15:26 CST 2021
     */
    private String sectionId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column LAB_INSTANCE.LAB_ID
     *
     * @mbg.generated Wed Oct 27 22:15:26 CST 2021
     */
    private String labId;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table LAB_INSTANCE
     *
     * @mbg.generated Wed Oct 27 22:15:26 CST 2021
     */
    public LabInstanceKey(String courseId, String sectionId, String labId) {
        this.courseId = courseId;
        this.sectionId = sectionId;
        this.labId = labId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table LAB_INSTANCE
     *
     * @mbg.generated Wed Oct 27 22:15:26 CST 2021
     */
    public LabInstanceKey() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column LAB_INSTANCE.COURSE_ID
     *
     * @return the value of LAB_INSTANCE.COURSE_ID
     *
     * @mbg.generated Wed Oct 27 22:15:26 CST 2021
     */
    public String getCourseId() {
        return courseId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column LAB_INSTANCE.COURSE_ID
     *
     * @param courseId the value for LAB_INSTANCE.COURSE_ID
     *
     * @mbg.generated Wed Oct 27 22:15:26 CST 2021
     */
    public void setCourseId(String courseId) {
        this.courseId = courseId == null ? null : courseId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column LAB_INSTANCE.SECTION_ID
     *
     * @return the value of LAB_INSTANCE.SECTION_ID
     *
     * @mbg.generated Wed Oct 27 22:15:26 CST 2021
     */
    public String getSectionId() {
        return sectionId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column LAB_INSTANCE.SECTION_ID
     *
     * @param sectionId the value for LAB_INSTANCE.SECTION_ID
     *
     * @mbg.generated Wed Oct 27 22:15:26 CST 2021
     */
    public void setSectionId(String sectionId) {
        this.sectionId = sectionId == null ? null : sectionId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column LAB_INSTANCE.LAB_ID
     *
     * @return the value of LAB_INSTANCE.LAB_ID
     *
     * @mbg.generated Wed Oct 27 22:15:26 CST 2021
     */
    public String getLabId() {
        return labId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column LAB_INSTANCE.LAB_ID
     *
     * @param labId the value for LAB_INSTANCE.LAB_ID
     *
     * @mbg.generated Wed Oct 27 22:15:26 CST 2021
     */
    public void setLabId(String labId) {
        this.labId = labId == null ? null : labId.trim();
    }
}