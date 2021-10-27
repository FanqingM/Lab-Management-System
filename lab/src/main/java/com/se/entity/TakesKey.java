package com.se.entity;

public class TakesKey {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TAKES.STUDENT_ID
     *
     * @mbg.generated Wed Oct 27 22:15:26 CST 2021
     */
    private String studentId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TAKES.COURSE_ID
     *
     * @mbg.generated Wed Oct 27 22:15:26 CST 2021
     */
    private String courseId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TAKES.SECTION_ID
     *
     * @mbg.generated Wed Oct 27 22:15:26 CST 2021
     */
    private String sectionId;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TAKES
     *
     * @mbg.generated Wed Oct 27 22:15:26 CST 2021
     */
    public TakesKey(String studentId, String courseId, String sectionId) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.sectionId = sectionId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TAKES
     *
     * @mbg.generated Wed Oct 27 22:15:26 CST 2021
     */
    public TakesKey() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TAKES.STUDENT_ID
     *
     * @return the value of TAKES.STUDENT_ID
     *
     * @mbg.generated Wed Oct 27 22:15:26 CST 2021
     */
    public String getStudentId() {
        return studentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TAKES.STUDENT_ID
     *
     * @param studentId the value for TAKES.STUDENT_ID
     *
     * @mbg.generated Wed Oct 27 22:15:26 CST 2021
     */
    public void setStudentId(String studentId) {
        this.studentId = studentId == null ? null : studentId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TAKES.COURSE_ID
     *
     * @return the value of TAKES.COURSE_ID
     *
     * @mbg.generated Wed Oct 27 22:15:26 CST 2021
     */
    public String getCourseId() {
        return courseId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TAKES.COURSE_ID
     *
     * @param courseId the value for TAKES.COURSE_ID
     *
     * @mbg.generated Wed Oct 27 22:15:26 CST 2021
     */
    public void setCourseId(String courseId) {
        this.courseId = courseId == null ? null : courseId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TAKES.SECTION_ID
     *
     * @return the value of TAKES.SECTION_ID
     *
     * @mbg.generated Wed Oct 27 22:15:26 CST 2021
     */
    public String getSectionId() {
        return sectionId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TAKES.SECTION_ID
     *
     * @param sectionId the value for TAKES.SECTION_ID
     *
     * @mbg.generated Wed Oct 27 22:15:26 CST 2021
     */
    public void setSectionId(String sectionId) {
        this.sectionId = sectionId == null ? null : sectionId.trim();
    }
}