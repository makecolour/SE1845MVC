/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.util.Date;

/**
 *
 * @author quyen
 */
public class Bill {
    private String billId, customerName, date, status;
    double total;

    public Bill() {
    }

    public Bill(String billId, String customerName, String date, String status, double total) {
        this.billId = billId;
        this.customerName = customerName;
        this.date = date;
        this.status = status;
        this.total = total;
    }

    public String getBillId() {
        return billId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getDate() {
        return date;
    }

    public String getStatus() {
        return status;
    }

    public double getTotal() {
        return total;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    
    
}
