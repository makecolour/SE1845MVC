/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Emp_WorkOn_Pro;
import entity.Employee;
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
public class DAOEmp_WorkOn_Pro extends DBConnect {

    public int addEmp_WorkOn_Pro(Emp_WorkOn_Pro ewp) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Emp_WorkOn_Pro]\n"
                + "           ([SSN]\n"
                + "           ,[ProNo]\n"
                + "           ,[Position]\n"
                + "           ,[HourPerDay])\n"
                + "     VALUES(?,?,?,?)";
        try {
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setString(1, ewp.getSSN());
            pre.setString(2, ewp.getProNo());
            pre.setString(3, ewp.getPosition());
            pre.setDouble(4, ewp.getHourPerDay());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOEmp_WorkOn_Pro.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }

    public int updateEmp_WorkOn_Pro(Emp_WorkOn_Pro ewp) {
        int n = 0;
        String sql = "UPDATE [dbo].[Emp_WorkOn_Pro]\n"
                + "   SET [Position] = ?\n"
                + "      ,[HourPerDay] = ?\n"
                + " WHERE [SSN] = ? AND [PRONO] = ?";
        try {
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setString(1, ewp.getPosition());
            pre.setDouble(4, ewp.getHourPerDay());
            pre.setString(3, ewp.getSSN());
            pre.setString(2, ewp.getProNo());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOEmp_WorkOn_Pro.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }

    public Vector<Emp_WorkOn_Pro> getAll(String sql) {
        Vector<Emp_WorkOn_Pro> vector = new Vector<Emp_WorkOn_Pro>();
        try {
            Statement state = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                String ssn = rs.getString(1);
                String proNo = rs.getString(2);
                String position = rs.getString(3);
                double hourPerDay = rs.getDouble(4);
                Emp_WorkOn_Pro ewp = new Emp_WorkOn_Pro(ssn, proNo, position, hourPerDay);
                vector.add(ewp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOEmployee.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public Vector<Emp_WorkOn_Pro> searchSSN(String str) {
        Vector<Emp_WorkOn_Pro> vector = new Vector<Emp_WorkOn_Pro>();
        String sql = "select * from Emp_WorkOn_Pro where ssn like '%" + str + "%'";
        try {
            Statement state = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                String ssn = rs.getString(1);
                String proNo = rs.getString(2);
                String position = rs.getString(3);
                double hourPerDay = rs.getDouble(4);
                Emp_WorkOn_Pro ewp = new Emp_WorkOn_Pro(ssn, proNo, position, hourPerDay);
                vector.add(ewp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOEmployee.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public int removeEmp_WorkOn_Pro(String ssn, String prono) {
        int n = 0;

        String sql = "delete from Emp_WorkOn_Pro where ssn = '" + ssn + "' AND prono = '" + prono + "'";

        try {
            Statement state = con.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAODependence.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }

    public Vector<Emp_WorkOn_Pro> getEmpFromPro(String id) {
        Vector<Emp_WorkOn_Pro> vector = new Vector<Emp_WorkOn_Pro>();
        String sql = "select * from Emp_WorkOn_Pro ep left join Employee e on ep.SSN = e.SSN where ep.ProNo in (select emp.[ProNo] from Emp_WorkOn_Pro emp where ssn = '"+id+"')";
        try {
            Statement state = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                String ssn = rs.getString(1);
                String proNo = rs.getString(2);
                String position = rs.getString(3);
                double hourPerDay = rs.getDouble(4);
                Emp_WorkOn_Pro ewp = new Emp_WorkOn_Pro(ssn, proNo, position, hourPerDay);
                vector.add(ewp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOEmp_WorkOn_Pro.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }
    
    public Vector<Emp_WorkOn_Pro> getEmpByProID(String id) {
        Vector<Emp_WorkOn_Pro> vector = new Vector<Emp_WorkOn_Pro>();
        String sql = "select * from Emp_WorkOn_Pro ep where ep.ProNo = '"+id+"'";
        LOG.log(Level.SEVERE, sql);
        try {
            Statement state = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                String ssn = rs.getString(1);
                String proNo = rs.getString(2);
                String position = rs.getString(3);
                double hourPerDay = rs.getDouble(4);
                Emp_WorkOn_Pro ewp = new Emp_WorkOn_Pro(ssn, proNo, position, hourPerDay);
                vector.add(ewp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOEmp_WorkOn_Pro.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }
    private static final Logger LOG = Logger.getLogger(DAOEmp_WorkOn_Pro.class.getName());
    
    public static void main(String[] args) {
        DAOEmp_WorkOn_Pro dao = new DAOEmp_WorkOn_Pro();
//        Vector<Emp_WorkOn_Pro> vector = dao.searchSSN("is001");
        //dao.addEmp_WorkOn_Pro(new Emp_WorkOn_Pro("IS201","P5","MAR",10));
        //dao.updateEmp_WorkOn_ProBySSN(new Emp_WorkOn_Pro("IS201","P5","MARV",100));
        //dao.updateEmp_WorkOn_ProByProNo(new Emp_WorkOn_Pro("AD001","P5","MARV",100));
//        for (Emp_WorkOn_Pro ewp : vector) {
//            System.out.println(ewp);
//        }
//        dao.removeEmp_WorkOn_Pro("AD001", "p5");
            dao.updateEmp_WorkOn_Pro(new Emp_WorkOn_Pro("AD001","P1","LOW-TABLE",25));
    }
}
