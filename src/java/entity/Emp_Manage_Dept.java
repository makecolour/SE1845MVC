/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author quyen
 */
public class Emp_Manage_Dept {

    private String SSN, DeptNo, StartDate, EndDate;

    public Emp_Manage_Dept() {
    }

    public Emp_Manage_Dept(String SSN, String DeptNo, String StartDate, String EndDate) {
        this.SSN = SSN;
        this.DeptNo = DeptNo;
        this.StartDate = StartDate;
        this.EndDate = EndDate;
    }

    public String getSSN() {
        return SSN;
    }

    public String getDeptNo() {
        return DeptNo;
    }

    public String getStartDate() {
        return StartDate;
    }

    public String getEndDate() {
        return EndDate;
    }

    public void setSSN(String SSN) {
        this.SSN = SSN;
    }

    public void setDeptNo(String DeptNo) {
        this.DeptNo = DeptNo;
    }

    public void setStartDate(String StartDate) {
        this.StartDate = StartDate;
    }

    public void setEndDate(String EndDate) {
        this.EndDate = EndDate;
    }

    @Override
    public String toString() {
        return "Emp_Manage_Dept{" + "SSN=" + SSN + ", DeptNo=" + DeptNo + ", StartDate=" + StartDate + ", EndDate=" + EndDate + '}';
    }

}
