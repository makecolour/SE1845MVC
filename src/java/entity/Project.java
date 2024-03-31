/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author quyen
 */
public class Project {

    private String ProNo, Name, StartDate, EndDate, DeptNo;

    public Project() {
    }

    public Project(String ProNo, String Name, String StartDate, String EndDate, String DeptNo) {
        this.ProNo = ProNo;
        this.Name = Name;
        this.StartDate = StartDate;
        this.EndDate = EndDate;
        this.DeptNo = DeptNo;
    }

    public String getProNo() {
        return ProNo;
    }

    public String getName() {
        return Name;
    }

    public String getStartDate() {
        return StartDate;
    }

    public String getEndDate() {
        return EndDate;
    }

    public String getDeptNo() {
        return DeptNo;
    }

    public void setProNo(String ProNo) {
        this.ProNo = ProNo;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public void setStartDate(String StartDate) {
        this.StartDate = StartDate;
    }

    public void setEndDate(String EndDate) {
        this.EndDate = EndDate;
    }

    public void setDeptNo(String DeptNo) {
        this.DeptNo = DeptNo;
    }

    @Override
    public String toString() {
        return "Project{" + "ProNo=" + ProNo + ", Name=" + Name + ", StartDate=" + StartDate + ", EndDate=" + EndDate + ", DeptNo=" + DeptNo + '}';
    }

}
