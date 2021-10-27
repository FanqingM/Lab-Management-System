package com.se.dto;

import java.sql.Date;


public class EmployeeDTO {
    private int id;
    private String employeeName;
    private String email;
    private int gender;
    private String departmentName;
    private Date date;

    public EmployeeDTO() {
    }

    public int getId() {
        return this.id;
    }

    public String getEmployeeName() {
        return this.employeeName;
    }

    public String getEmail() {
        return this.email;
    }

    public int getGender() {
        return this.gender;
    }

    public String getDepartmentName() {
        return this.departmentName;
    }

    public Date getDate() {
        return this.date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof EmployeeDTO)) return false;
        final EmployeeDTO other = (EmployeeDTO) o;
        if (!other.canEqual((Object) this)) return false;
        if (this.getId() != other.getId()) return false;
        final Object this$employeeName = this.getEmployeeName();
        final Object other$employeeName = other.getEmployeeName();
        if (this$employeeName == null ? other$employeeName != null : !this$employeeName.equals(other$employeeName))
            return false;
        final Object this$email = this.getEmail();
        final Object other$email = other.getEmail();
        if (this$email == null ? other$email != null : !this$email.equals(other$email)) return false;
        if (this.getGender() != other.getGender()) return false;
        final Object this$departmentName = this.getDepartmentName();
        final Object other$departmentName = other.getDepartmentName();
        if (this$departmentName == null ? other$departmentName != null : !this$departmentName.equals(other$departmentName))
            return false;
        final Object this$date = this.getDate();
        final Object other$date = other.getDate();
        if (this$date == null ? other$date != null : !this$date.equals(other$date)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof EmployeeDTO;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * PRIME + this.getId();
        final Object $employeeName = this.getEmployeeName();
        result = result * PRIME + ($employeeName == null ? 43 : $employeeName.hashCode());
        final Object $email = this.getEmail();
        result = result * PRIME + ($email == null ? 43 : $email.hashCode());
        result = result * PRIME + this.getGender();
        final Object $departmentName = this.getDepartmentName();
        result = result * PRIME + ($departmentName == null ? 43 : $departmentName.hashCode());
        final Object $date = this.getDate();
        result = result * PRIME + ($date == null ? 43 : $date.hashCode());
        return result;
    }

    public String toString() {
        return "EmployeeDTO(id=" + this.getId() + ", employeeName=" + this.getEmployeeName() + ", email=" + this.getEmail() + ", gender=" + this.getGender() + ", departmentName=" + this.getDepartmentName() + ", date=" + this.getDate() + ")";
    }
}
