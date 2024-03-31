/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author quyen
 */
public class Employee {

    private String SSN, FName, LName, Address, DeptNo, SSNSupervisor;
    private double Salary;
    private boolean sex;

    public Employee(String SSN, String FName, String LName, String Address, double Salary, boolean Sex, String DeptNo, String SSNSupervisor) {
        this.SSN = SSN;
        this.FName = FName;
        this.LName = LName;
        this.Address = Address;
        this.DeptNo = DeptNo;
        this.SSNSupervisor = SSNSupervisor;
        this.Salary = Salary;
        this.sex = Sex;
    }

    public Employee() {
    }

    public String getSSN() {
        return SSN;
    }

    public String getFName() {
        return FName;
    }

    public String getLName() {
        return LName;
    }

    public String getAddress() {
        return Address;
    }

    public String getDeptNo() {
        return DeptNo;
    }

    public String getSSNSupervisor() {
        return SSNSupervisor;
    }

    public double getSalary() {
        return Salary;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSSN(String SSN) {
        this.SSN = SSN;
    }

    public void setFName(String FName) {
        this.FName = FName;
    }

    public void setLName(String LName) {
        this.LName = LName;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public void setDeptNo(String DeptNo) {
        this.DeptNo = DeptNo;
    }

    public void setSSNSupervisor(String SSNSupervisor) {
        this.SSNSupervisor = SSNSupervisor;
    }

    public void setSalary(double Salary) {
        this.Salary = Salary;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "Employee{" + "SSN=" + SSN + ", FName=" + FName + ", LName=" + LName + ", Address=" + Address + ", DeptNo=" + DeptNo + ", SSNSupervisor=" + SSNSupervisor + ", Salary=" + Salary + ", sex=" + sex + '}';
    }
    
}
