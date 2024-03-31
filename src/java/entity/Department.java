/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author quyen
 */
public class Department {

    private String DeptNo, Name, Location;

    public Department(String DeptNo, String Name, String Location) {
        this.DeptNo = DeptNo;
        this.Name = Name;
        this.Location = Location;
    }

    public Department() {
    }

    public String getDeptNo() {
        return DeptNo;
    }

    public String getName() {
        return Name;
    }

    public String getLocation() {
        return Location;
    }

    public void setDeptNo(String DeptNo) {
        this.DeptNo = DeptNo;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public void setLocation(String Location) {
        this.Location = Location;
    }

    @Override
    public String toString() {
        return "Department{" + "DeptNo=" + DeptNo + ", Name=" + Name + ", Location=" + Location + '}';
    }

}
