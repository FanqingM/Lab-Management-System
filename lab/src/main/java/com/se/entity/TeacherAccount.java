package com.se.entity;

public class TeacherAccount {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TEACHER_ACCOUNT.ID
     *
     * @mbg.generated Wed Oct 27 22:15:26 CST 2021
     */
    private String id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TEACHER_ACCOUNT.EMAIL
     *
     * @mbg.generated Wed Oct 27 22:15:26 CST 2021
     */
    private String email;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TEACHER_ACCOUNT.PASSWORD
     *
     * @mbg.generated Wed Oct 27 22:15:26 CST 2021
     */
    private String password;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TEACHER_ACCOUNT
     *
     * @mbg.generated Wed Oct 27 22:15:26 CST 2021
     */
    public TeacherAccount(String id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TEACHER_ACCOUNT
     *
     * @mbg.generated Wed Oct 27 22:15:26 CST 2021
     */
    public TeacherAccount() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TEACHER_ACCOUNT.ID
     *
     * @return the value of TEACHER_ACCOUNT.ID
     *
     * @mbg.generated Wed Oct 27 22:15:26 CST 2021
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TEACHER_ACCOUNT.ID
     *
     * @param id the value for TEACHER_ACCOUNT.ID
     *
     * @mbg.generated Wed Oct 27 22:15:26 CST 2021
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TEACHER_ACCOUNT.EMAIL
     *
     * @return the value of TEACHER_ACCOUNT.EMAIL
     *
     * @mbg.generated Wed Oct 27 22:15:26 CST 2021
     */
    public String getEmail() {
        return email;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TEACHER_ACCOUNT.EMAIL
     *
     * @param email the value for TEACHER_ACCOUNT.EMAIL
     *
     * @mbg.generated Wed Oct 27 22:15:26 CST 2021
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TEACHER_ACCOUNT.PASSWORD
     *
     * @return the value of TEACHER_ACCOUNT.PASSWORD
     *
     * @mbg.generated Wed Oct 27 22:15:26 CST 2021
     */
    public String getPassword() {
        return password;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TEACHER_ACCOUNT.PASSWORD
     *
     * @param password the value for TEACHER_ACCOUNT.PASSWORD
     *
     * @mbg.generated Wed Oct 27 22:15:26 CST 2021
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }
}