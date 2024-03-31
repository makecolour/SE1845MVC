/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Department;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.PreparedStatement;
import java.util.Vector;
import java.sql.ResultSet;

/**
 *
 * @author quyen
 */
public class DAODepartment extends DBConnect {

    public int addDepartment(Department depart) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Department]\n"
                + "           ([DeptNo]\n"
                + "           ,[Name]\n"
                + "           ,[Location])\n"
                + "           VALUES(?,?,?)";
        try {
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setString(1, depart.getDeptNo());
            pre.setString(2, depart.getName());
            pre.setString(3, depart.getLocation());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAODepartment.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }

    public int updateDAODepartment(Department dep) {
        int n = 0;
        String sql = "UPDATE [dbo].[Department]\n"
                + "   SET [Name] = ?\n"
                + "      ,[Location] = ?\n"
                + " WHERE [DeptNo] = ?";

        try {
            PreparedStatement pre = con.prepareStatement(sql);

            pre.setString(1, dep.getName());
            pre.setString(2, dep.getLocation());
            pre.setString(3, dep.getDeptNo());

            n = pre.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DAODepartment.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }

    public Vector<Department> getAll(String sql) {
        Vector<Department> vector = new Vector<Department>();
        try {
            Statement state = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                String depno = rs.getString(1);
                String name = rs.getString(2);
                String location = rs.getString(3);
                Department depart = new Department(depno, name, location);
                vector.add(depart);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOEmployee.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public Vector<Department> searchName(String depName) {
        Vector<Department> vector = new Vector<Department>();
        String sql = "select * from department where name like '%" + depName + "%'";
        try {
            Statement state = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                String depno = rs.getString(1);
                String name = rs.getString(2);
                String location = rs.getString(3);
                Department depart = new Department(depno, name, location);
                vector.add(depart);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOEmployee.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public int removeDepartment(String deptno) {
        int n = 0;
        String sql = "delete from Department where DeptNo = '" + deptno + "' AND"
                + " ('" + deptno + "' not in (select distinct DeptNo from Employee))"
                + "AND ('" + deptno + "' not in (select distinct DeptNo from Emp_Manage_Dept))"
                + "AND ('" + deptno + "' not in (select distinct DeptNo from Project))";

        try {
            Statement state = con.createStatement();
            n = state.executeUpdate(sql);

        } catch (SQLException ex) {
            Logger.getLogger(DAOEmployee.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }

    public static void main(String[] args) {
        DAODepartment dao = new DAODepartment();
        //dao.addDepartment(new Department("LAB211","Laboratory","Room 202 Alpha"));
        Vector<Department> vector = dao.searchName("Laboratory");
        for (Department depart : vector) {
            System.out.println(depart);
        }
    }
}
