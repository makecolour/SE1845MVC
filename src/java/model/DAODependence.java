/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Dependence;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.PreparedStatement;
import java.util.Vector;
import java.sql.ResultSet;

/**
 *
 * @author quyen
 */
public class DAODependence extends DBConnect {

    public int addDependence(Dependence dep) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Dependence] "
                + "([SSN], [Name], [DOB], [Relationship]) "
                + "VALUES(?, ?, ?, ?)";
        try {
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setString(1, dep.getSSN());
            pre.setString(2, dep.getName());
            pre.setString(3, dep.getDOB());
            pre.setString(4, dep.getRelationship());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAODependence.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int updateDependence(Dependence dep) {
        int n = 0;
        String sql = "UPDATE [dbo].[Dependence] "
                + "SET [DOB] = ?, [Relationship] = ? "
                + "WHERE [SSN] = ? AND [Name] = ?";
        try {
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setString(1, dep.getDOB());
            pre.setString(2, dep.getRelationship());
            pre.setString(3, dep.getSSN());
            pre.setString(4, dep.getName());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAODependence.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public Vector<Dependence> getAll(String sql) {
        Vector<Dependence> vector = new Vector<Dependence>();
        try {
            Statement state = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                String ssn = rs.getString(1);
                String name = rs.getString(2);
                String dob = rs.getString(3);
                String relationship = rs.getString(4);
                Dependence dep = new Dependence(ssn, name, dob, relationship);
                vector.add(dep);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOEmployee.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public Vector<Dependence> searchName(String depName) {
        Vector<Dependence> vector = new Vector<Dependence>();
        String sql = "select * from Dependence where name like '%" + depName + "%'";
        try {
            Statement state = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                String ssn = rs.getString(1);
                String name = rs.getString(2);
                String dob = rs.getString(3);
                String relationship = rs.getString(4);
                Dependence dep = new Dependence(ssn, name, dob, relationship);
                vector.add(dep);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOEmployee.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public int removeDependence(String ssn, String name) {
        int n = 0;

        String sql = "delete from dependence where ssn = '" + ssn + "' AND name = '" + name + "'";

        try {
            Statement state = con.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAODependence.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }

    public static void main(String[] args) {
        DAODependence dao = new DAODependence();
//        Vector<Dependence> vector = dao.searchName("messi");
        //int n = dao.addDependence(new Dependence("IS201", "Ronaldo","2000-01-01","Son"));
//        dao.updateDependenceByName(new Dependence("IS201", "Ronaldo","2000-01-01","Father"));
//        for (Dependence dep : vector) {
//            System.out.println(dep);
//        }
//        dao.removeDependence("IS3000", "Ronaldo Delima");
//            dao.updateDependence(new Dependence("IS3000","Ronaldo Delima","2000-03-13","God"));
    }
}
