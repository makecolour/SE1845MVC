/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author quyen
 */
public class EmployeeCart {

    private String SSN, FName, LName, Position, ProNo;
    private int quantity;
    private double Salary;

    public EmployeeCart() {
    }

    public EmployeeCart(String SSN, String FName, String LName, int quantity, double Salary, String Position, String ProNo) {
        this.SSN = SSN;
        this.FName = FName;
        this.LName = LName;
        this.quantity = quantity;
        this.Salary = Salary;
        this.Position = Position;
        this.ProNo = ProNo;
    }

    public String getSSN() {
        return SSN;
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

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setSalary(double Salary) {
        this.Salary = Salary;
    }

    public String getFName() {
        return FName;
    }

    public String getLName() {
        return LName;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getSalary() {
        return Salary;
    }
    
    public String getPosition() {
        return Position;
    }
    
    public void setPosition(String Position) {
        this.Position = Position;
    }
    
    public String getProNo() {
        return ProNo;
    }
    
    public void setProNo(String ProNo) {
        this.ProNo = ProNo;
    }

    @Override
    public String toString() {
        return "EmployeeCart{" + "SSN=" + SSN + ", FName=" + FName + ", LName=" + LName + ", Position=" + Position + ", ProNo=" + ProNo + ", quantity=" + quantity + ", Salary=" + Salary + '}';
    }

}
