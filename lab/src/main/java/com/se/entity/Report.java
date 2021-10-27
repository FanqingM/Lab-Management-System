package com.se.entity;

public class Report extends ReportKey {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column REPORT.URL
     *
     * @mbg.generated Wed Oct 27 21:50:19 CST 2021
     */
    private String url;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column REPORT.GRADES
     *
     * @mbg.generated Wed Oct 27 21:50:19 CST 2021
     */
    private Integer grades;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table REPORT
     *
     * @mbg.generated Wed Oct 27 21:50:19 CST 2021
     */
    public Report(String studentId, String courseId, String sectionId, String labId, String url, Integer grades) {
        super(studentId, courseId, sectionId, labId);
        this.url = url;
        this.grades = grades;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table REPORT
     *
     * @mbg.generated Wed Oct 27 21:50:19 CST 2021
     */
    public Report() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column REPORT.URL
     *
     * @return the value of REPORT.URL
     *
     * @mbg.generated Wed Oct 27 21:50:19 CST 2021
     */
    public String getUrl() {
        return url;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column REPORT.URL
     *
     * @param url the value for REPORT.URL
     *
     * @mbg.generated Wed Oct 27 21:50:19 CST 2021
     */
    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column REPORT.GRADES
     *
     * @return the value of REPORT.GRADES
     *
     * @mbg.generated Wed Oct 27 21:50:19 CST 2021
     */
    public Integer getGrades() {
        return grades;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column REPORT.GRADES
     *
     * @param grades the value for REPORT.GRADES
     *
     * @mbg.generated Wed Oct 27 21:50:19 CST 2021
     */
    public void setGrades(Integer grades) {
        this.grades = grades;
    }
}