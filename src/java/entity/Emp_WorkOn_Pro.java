/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author quyen
 */
public class Emp_WorkOn_Pro {

    private String SSN, ProNo, Position;
    private double HourPerDay;

    public Emp_WorkOn_Pro() {
    }

    public Emp_WorkOn_Pro(String SSN, String ProNo, String Position, double HourPerDay) {
        this.SSN = SSN;
        this.ProNo = ProNo;
        this.Position = Position;
        this.HourPerDay = HourPerDay;
    }

    public String getSSN() {
        return SSN;
    }

    public String getProNo() {
        return ProNo;
    }

    public String getPosition() {
        return Position;
    }

    public double getHourPerDay() {
        return HourPerDay;
    }

    public void setSSN(String SSN) {
        this.SSN = SSN;
    }

    public void setProNo(String ProNo) {
        this.ProNo = ProNo;
    }

    public void setPosition(String Position) {
        this.Position = Position;
    }

    public void setHourPerDay(double HourPerDay) {
        this.HourPerDay = HourPerDay;
    }

    @Override
    public String toString() {
        return "Emp_WorkOn_Pro{" + "SSN=" + SSN + ", ProNo=" + ProNo + ", Position=" + Position + ", HourPerDay=" + HourPerDay + '}';
    }
    
}
