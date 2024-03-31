/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Emp_Manage_Dept;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.PreparedStatement;
import java.util.Vector;
import java.sql.Statement;
import java.sql.ResultSet;

/**
 *
 * @author quyen
 */
public class DAOEmp_Manage_Dept extends DBConnect {

    public int addEmp_Manage_Dept(Emp_Manage_Dept emd) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Emp_Manage_Dept]\n"
                + "           ([SSN]\n"
                + "           ,[DeptNo]\n"
                + "           ,[StartDate]\n"
                + "           ,[EndDate])\n"
                + "     VALUES(?,?,?,?)";
        try {
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setString(1, emd.getSSN());
            pre.setString(2, emd.getDeptNo());
            pre.setString(3, emd.getStartDate());
            pre.setString(4, emd.getEndDate());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOEmp_Manage_Dept.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int updateEmp_Manage_Dept(Emp_Manage_Dept emd) {
        int n = 0;
        String sql = "UPDATE [dbo].[Emp_Manage_Dept]\n"
                + "   SET [EndDate] = ?\n"
                + " WHERE [SSN] = ? AND [DEPTNO] = ? AND [STARTDATE] = ?";
        try {
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setString(1, emd.getEndDate());
            pre.setString(2, emd.getSSN());
            pre.setString(3, emd.getDeptNo());
            pre.setString(4, emd.getStartDate());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOEmp_Manage_Dept.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }

    public Vector<Emp_Manage_Dept> getAll(String sql) {
        Vector<Emp_Manage_Dept> vector = new Vector<Emp_Manage_Dept>();
        try {
            Statement state = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                String ssn = rs.getString(1);
                String depNo = rs.getString(2);
                String startDate = rs.getString(3);
                String endDate = rs.getString(4);
                Emp_Manage_Dept emp = new Emp_Manage_Dept(ssn, depNo, startDate, endDate);
                vector.add(emp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOEmployee.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public Vector<Emp_Manage_Dept> searchSSN(String str) {
        Vector<Emp_Manage_Dept> vector = new Vector<Emp_Manage_Dept>();
        String sql = "select * from Emp_Manage_Dept where ssn like '%" + str + "%'";
        try {
            Statement state = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                String ssn = rs.getString(1);
                String depNo = rs.getString(2);
                String startDate = rs.getString(3);
                String endDate = rs.getString(4);
                Emp_Manage_Dept emp = new Emp_Manage_Dept(ssn, depNo, startDate, endDate);
                vector.add(emp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOEmployee.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public int removeEmp_Manage_Dept(String ssn, String deptno, String startDate) {
        int n = 0;

        String sql = "delete from Emp_Manage_Dept where ssn = '" + ssn + "' AND deptno = '" + deptno + "' AND startdate = '" + startDate + "'";

        try {
            Statement state = con.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAODependence.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }

    public static void main(String[] args) {
        DAOEmp_Manage_Dept dao = new DAOEmp_Manage_Dept();
//        Vector<Emp_Manage_Dept> vector = dao.searchSSN("ad001");
        //dao.addEmp_Manage_Dept(new Emp_Manage_Dept("IS201", "LAB211", "2024-01-01", "2024-01-01"));
        //dao.updateEmp_Manage_DeptBySSN(new Emp_Manage_Dept("IS201", "LAB211", "2000-01-01", "2024-01-01"));
        //dao.updateEmp_Manage_DeptByDeptNo(new Emp_Manage_Dept("IS201", "LAB211", "2000-01-01", "2000-01-01"));
        //dao.updateEmp_Manage_DeptByStartDate(new Emp_Manage_Dept("IS201", "LAB211", "2000-01-01", "2030-01-01"));
//        for (Emp_Manage_Dept emp : vector) {
//            System.out.println(emp);
//        }
//        dao.removeEmp_Manage_Dept("IS3000","AL","2024-03-06");
        dao.updateEmp_Manage_Dept(new Emp_Manage_Dept("IS3000", "AL", "2024-02-27","2050-02-27"));
    }
}
