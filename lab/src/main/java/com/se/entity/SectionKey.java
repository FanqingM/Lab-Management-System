package com.se.entity;

public class SectionKey {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SECTION.COURSE_ID
     *
     * @mbg.generated Wed Oct 27 21:50:19 CST 2021
     */
    private String courseId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SECTION.SECTION_ID
     *
     * @mbg.generated Wed Oct 27 21:50:19 CST 2021
     */
    private String sectionId;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SECTION
     *
     * @mbg.generated Wed Oct 27 21:50:19 CST 2021
     */
    public SectionKey(String courseId, String sectionId) {
        this.courseId = courseId;
        this.sectionId = sectionId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SECTION
     *
     * @mbg.generated Wed Oct 27 21:50:19 CST 2021
     */
    public SectionKey() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SECTION.COURSE_ID
     *
     * @return the value of SECTION.COURSE_ID
     *
     * @mbg.generated Wed Oct 27 21:50:19 CST 2021
     */
    public String getCourseId() {
        return courseId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SECTION.COURSE_ID
     *
     * @param courseId the value for SECTION.COURSE_ID
     *
     * @mbg.generated Wed Oct 27 21:50:19 CST 2021
     */
    public void setCourseId(String courseId) {
        this.courseId = courseId == null ? null : courseId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SECTION.SECTION_ID
     *
     * @return the value of SECTION.SECTION_ID
     *
     * @mbg.generated Wed Oct 27 21:50:19 CST 2021
     */
    public String getSectionId() {
        return sectionId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SECTION.SECTION_ID
     *
     * @param sectionId the value for SECTION.SECTION_ID
     *
     * @mbg.generated Wed Oct 27 21:50:19 CST 2021
     */
    public void setSectionId(String sectionId) {
        this.sectionId = sectionId == null ? null : sectionId.trim();
    }
}