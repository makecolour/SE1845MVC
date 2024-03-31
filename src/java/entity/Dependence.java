/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author quyen
 */
public class Dependence {

    private String SSN, Name, DOB, Relationship;

    public Dependence() {
    }

    public Dependence(String SSN, String Name, String DOB, String Relationship) {
        this.SSN = SSN;
        this.Name = Name;
        this.Relationship = Relationship;
        this.DOB = DOB;
    }

    public String getSSN() {
        return SSN;
    }

    public String getName() {
        return Name;
    }

    public String getRelationship() {
        return Relationship;
    }

    public String getDOB() {
        return DOB;
    }

    public void setSSN(String SSN) {
        this.SSN = SSN;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public void setRelationship(String Relationship) {
        this.Relationship = Relationship;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    @Override
    public String toString() {
        return "Dependence{" + "SSN=" + SSN + ", Name=" + Name + ", DOB=" + DOB + ", Relationship=" + Relationship + '}';
    }

}
