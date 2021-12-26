package com.se.entity;

import io.swagger.v3.oas.annotations.media.Schema;

public class AdministratorAccount {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ADMINISTRATOR_ACCOUNT.ID
     *
     * @mbg.generated Wed Oct 27 22:15:26 CST 2021
     */
    @Schema(name = "id", description = "管理员账号")
    private String id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ADMINISTRATOR_ACCOUNT.EMAIL
     *
     * @mbg.generated Wed Oct 27 22:15:26 CST 2021
     */
    @Schema(name = "email", description = "管理员邮箱")
    private String email;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ADMINISTRATOR_ACCOUNT.PASSWORD
     *
     * @mbg.generated Wed Oct 27 22:15:26 CST 2021
     */
    @Schema(name = "password", description = "管理员密码")
    private String password;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ADMINISTRATOR_ACCOUNT
     *
     * @mbg.generated Wed Oct 27 22:15:26 CST 2021
     */
    public AdministratorAccount(String id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ADMINISTRATOR_ACCOUNT
     *
     * @mbg.generated Wed Oct 27 22:15:26 CST 2021
     */
    public AdministratorAccount() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ADMINISTRATOR_ACCOUNT.ID
     *
     * @return the value of ADMINISTRATOR_ACCOUNT.ID
     *
     * @mbg.generated Wed Oct 27 22:15:26 CST 2021
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ADMINISTRATOR_ACCOUNT.ID
     *
     * @param id the value for ADMINISTRATOR_ACCOUNT.ID
     *
     * @mbg.generated Wed Oct 27 22:15:26 CST 2021
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ADMINISTRATOR_ACCOUNT.EMAIL
     *
     * @return the value of ADMINISTRATOR_ACCOUNT.EMAIL
     *
     * @mbg.generated Wed Oct 27 22:15:26 CST 2021
     */
    public String getEmail() {
        return email;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ADMINISTRATOR_ACCOUNT.EMAIL
     *
     * @param email the value for ADMINISTRATOR_ACCOUNT.EMAIL
     *
     * @mbg.generated Wed Oct 27 22:15:26 CST 2021
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ADMINISTRATOR_ACCOUNT.PASSWORD
     *
     * @return the value of ADMINISTRATOR_ACCOUNT.PASSWORD
     *
     * @mbg.generated Wed Oct 27 22:15:26 CST 2021
     */
    public String getPassword() {
        return password;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ADMINISTRATOR_ACCOUNT.PASSWORD
     *
     * @param password the value for ADMINISTRATOR_ACCOUNT.PASSWORD
     *
     * @mbg.generated Wed Oct 27 22:15:26 CST 2021
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }
}