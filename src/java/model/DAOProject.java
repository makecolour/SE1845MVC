/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Project;
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
public class DAOProject extends DBConnect {

    public int addProject(Project pro) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Project]\n"
                + "           ([ProNo]\n"
                + "           ,[Name]\n"
                + "           ,[StartDate]\n"
                + "           ,[EndDate]\n"
                + "           ,[DeptNo])\n"
                + "     VALUES(?,?,?,?,?)";

        try {
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setString(1, pro.getProNo());
            pre.setString(2, pro.getName());
            pre.setString(3, pro.getStartDate());
            pre.setString(4, pro.getEndDate());
            pre.setString(5, pro.getDeptNo());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOProject.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int updateProject(Project pro) {
        int n = 0;
        String sql = "UPDATE [dbo].[Project]\n"
                + "   SET [Name] = ?\n"
                + "      ,[StartDate] = ?\n"
                + "      ,[EndDate] = ?\n"
                + "      ,[DeptNo] = ?\n"
                + " WHERE [ProNo] = ?";

        try {
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setString(1, pro.getName());
            pre.setString(2, pro.getStartDate());
            pre.setString(3, pro.getEndDate());
            pre.setString(4, pro.getDeptNo());
            pre.setString(5, pro.getProNo());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOProject.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public Vector<Project> getAll(String sql) {
        Vector<Project> vector = new Vector<Project>();
        try {
            Statement state = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                String proNo = rs.getString(1);
                String name = rs.getString(2);
                String startDate = rs.getString(3);
                String endDate = rs.getString(4);
                String depNo = rs.getString(5);
                Project project = new Project(proNo, name, startDate, endDate, depNo);
                vector.add(project);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOEmployee.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public Vector<Project> searchName(String str) {
        Vector<Project> vector = new Vector<Project>();
        String sql = "select * from project where name like '%" + str + "%'";
        try {
            Statement state = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                String proNo = rs.getString(1);
                String name = rs.getString(2);
                String startDate = rs.getString(3);
                String endDate = rs.getString(4);
                String depNo = rs.getString(5);
                Project project = new Project(proNo, name, startDate, endDate, depNo);
                vector.add(project);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOEmployee.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public int removeProject(String prono) {
        int n = 0;
        String sql = "delete from Project where ProNo = '" + prono + "' "
                + "AND ('" + prono + "' not in (select distinct ProNo from Emp_WorkOn_Pro))";

        try {
            Statement state = con.createStatement();
            n = state.executeUpdate(sql);

        } catch (SQLException ex) {
            Logger.getLogger(DAOEmployee.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }

    public static void main(String[] args) {
        DAOProject dao = new DAOProject();
        //Vector<Project> vector = dao.searchFname("marketing");
        //dao.addProject(new Project("P7","Lab6","2022-01-04","2022-01-05","IS"));
        //dao.updateProject(new Project("P5","Marketing","2022-01-04","2022-01-05","IS"));
//        for (Project pro : vector) {
//            System.out.println(pro);
//        }
        dao.removeProject("P7");
    }
}
