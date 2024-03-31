/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Bill;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author quyen
 */
public class DAOBill extends DBConnect{
    public boolean checkIfExist(){
        boolean tableExists = false;
        Vector<String> vector = new Vector<String>();
        String sql = "SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'Bill'";
        try{
            Statement state = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            tableExists = rs.next();
        }catch(Exception ex)
        {
             Logger.getLogger(DAOBill.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tableExists;
    }
    
    public void addTable() {
        int n = 0;
        String sql = "CREATE TABLE [dbo].[Bill] (Bill_ID VARCHAR(10) foreign key references [dbo].[Employee] (SSN), \n" +
"							Customer_Name VARCHAR(150), \n" +
"							[Date] DATE, \n" +
"							Total FLOAT, \n" +
"							[Status] VARCHAR(100),\n" +
"							primary key(Bill_ID));";
        try {
            PreparedStatement pre = con.prepareStatement(sql);
            //pre.setDataType(IndexOf ?,value);
            pre.execute();
        } catch (SQLException ex) {
            Logger.getLogger(DAOBill.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int insertBill(Bill bill){
        int n = 0; 
        String sql = "INSERT INTO [dbo].[Bill]\n"
                + "           ([Bill_ID]\n"
                + "           ,[Customer_Name]\n"
                + "           ,[Date]\n"
                + "           ,[Total]\n"
                + "           ,[Status])\n"
                + "     VALUES(?,?,?,?,?)";
        try{
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setString(1, bill.getBillId());
            pre.setString(2, bill.getCustomerName());
            pre.setString(3, bill.getDate());
            pre.setString(4, bill.getTotal()+"");
            pre.setString(5, bill.getStatus());
            n = pre.executeUpdate();
        }catch(SQLException ex)
        {
            Logger.getLogger(DAOBill.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
    
    public int updateBill(Bill bill){
        int n = 0;
        String sql = "UPDATE [dbo].[Bill]\n"
                + "   SET [Customer_Name] = ?\n"
                + "      ,[Date] = ?\n"
                + "      ,[Total] = ?\n"
                + "      ,[Status] = ?\n"
                + " WHERE [Bill_ID] = ?";
        try{
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setString(1, bill.getBillId());
            pre.setString(2, bill.getCustomerName());
            pre.setString(3, bill.getDate());
            pre.setString(4, bill.getTotal()+"");
            pre.setString(5, bill.getStatus());
            n = pre.executeUpdate();
        }catch(SQLException ex)
        {
            Logger.getLogger(DAOBill.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
    
    public Vector<Bill> getAll(String sql) {
        Vector<Bill> vector = new Vector<Bill>();
        try {
            Statement state = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                String billId = rs.getString(1);
                String customerName = rs.getString(2);
                String date = rs.getString(3);
                String status = rs.getString(4);
                Double total = rs.getDouble(5);
                Bill bill = new Bill(billId, customerName, date, status, total);
                vector.add(bill);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOBill.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }
    
    public Vector<Bill> searchBill(String billId) {
        Vector<Bill> vector = new Vector<Bill>();
        String sql = "select * from Bill where Bill_ID = '"+billId+"'";
        try {
            Statement state = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                String customerName = rs.getString(2);
                String date = rs.getString(3);
                String status = rs.getString(4);
                Double total = rs.getDouble(5);
                Bill bill = new Bill(billId, customerName, date, status, total);
                vector.add(bill);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOBill.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }
    
    public int removeBill(String billId) {
        int n = 0;
        String sql = "delete from Bill where Bill_ID = '" + billId + "'";
        try {
            Statement state = con.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOEmployee.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
    
    public static void main(String[] args) {
        DAOBill bill = new DAOBill();
        System.out.println(bill.checkIfExist());
        
    }
    
}

