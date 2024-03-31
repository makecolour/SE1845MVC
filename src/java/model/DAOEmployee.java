/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Employee;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.PreparedStatement;
import java.util.Scanner;
import java.util.Vector;

/**
 *
 * @author quyen
 */
public class DAOEmployee extends DBConnect {

    public int insertEmployee(Employee emp) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Employee]\n"
                + "           ([SSN]\n"
                + "           ,[FName]\n"
                + "           ,[LName]\n"
                + "           ,[Address]\n"
                + "           ,[Salary]\n"
                + "           ,[Sex]\n"
                + "           ,[DeptNo]\n"
                + "           ,[SSNSupervisor])\n"
                + "     VALUES\n"
                + "           ('" + emp.getSSN() + "','" + emp.getFName() + "','" + emp.getLName() + "','" + emp.getAddress() + "'," + emp.getSalary() + "," + (emp.isSex() == true ? 1 : 0) + ",'" + emp.getDeptNo() + "','" + emp.getSSNSupervisor() + "')";
        Statement state = null;
        try {
            state = con.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(DAOEmployee.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOEmployee.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int addEmployee(Employee emp) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Employee]\n"
                + "           ([SSN]\n"
                + "           ,[FName]\n"
                + "           ,[LName]\n"
                + "           ,[Address]\n"
                + "           ,[Salary]\n"
                + "           ,[Sex]\n"
                + "           ,[DeptNo]\n"
                + "           ,[SSNSupervisor])\n"
                + "     VALUES(?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pre = con.prepareStatement(sql);
            //pre.setDataType(IndexOf ?,value);
            pre.setString(1, emp.getSSN());
            pre.setString(2, emp.getFName());
            pre.setString(3, emp.getLName());
            pre.setString(4, emp.getAddress());
            pre.setDouble(5, emp.getSalary());
            pre.setBoolean(6, emp.isSex());
            pre.setString(7, emp.getDeptNo());
            pre.setString(8, emp.getSSNSupervisor());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOEmployee.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }

    public int updateEmployee(Employee emp) {
        int n = 0;
        String sql = "UPDATE [dbo].[Employee]\n"
                + "   SET [FName] = ?\n"
                + "      ,[LName] = ?\n"
                + "      ,[Address] = ?\n"
                + "      ,[Salary] = ?\n"
                + "      ,[Sex] = ?\n"
                + "      ,[DeptNo] = ?\n"
                + "      ,[SSNSupervisor] = ?\n"
                + " WHERE [SSN] = ?";
        try {
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setString(1, emp.getFName());
            pre.setString(2, emp.getLName());
            pre.setString(3, emp.getAddress());
            pre.setDouble(4, emp.getSalary());
            pre.setBoolean(5, emp.isSex());
            pre.setString(6, emp.getDeptNo());
            pre.setString(7, emp.getSSNSupervisor());
            pre.setString(8, emp.getSSN());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOEmployee.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    //chill code
    public String selectEmployee(String name) {
        String result = "";
        String sql = "SELECT * FROM Employee WHERE [FName] = ?";
        try {
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setString(1, name);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                result += rs.getString(1) + "; " + rs.getString(2) + "; " + rs.getString(3) + "; " + rs.getString(4)
                        + " " + rs.getDouble(5) + "; " + rs.getBoolean(6) + " " + rs.getString(7) + "; " + rs.getString(8) + "\n";

            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOEmployee.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

    //chill code
    public String sqlCode(String code) {
        String result = "";
        int columnCount;

        try {
            PreparedStatement pre = con.prepareStatement(code);
            ResultSet rs = pre.executeQuery();
            columnCount = rs.getMetaData().getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                if (rs.getMetaData().getColumnName(i).equalsIgnoreCase("address")) {
                    result += String.format("|%-35s|", rs.getMetaData().getColumnName(i));
                } else {
                    result += String.format("|%-15s|", rs.getMetaData().getColumnName(i));
                }
            }
            while (rs.next()) {
                result += "\n" + String.format("|%-15s||%-15s||%-15s||%-35s||%-15s||%-15s||%-15s||%-15s|",
                        rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getBoolean(6), rs.getString(7), rs.getString(8));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOEmployee.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

    public Vector<Employee> getAll(String sql) {
        Vector<Employee> vector = new Vector<Employee>();
        try {
            Statement state = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                String ssn = rs.getString(1);
                String fname = rs.getString(2);
                String lname = rs.getString(3);
                String address = rs.getString(4);
                double salary = rs.getDouble(5);
                int gt = rs.getInt(6);
                boolean sex = (gt == 1 ? true : false);
                String depno = rs.getString(7);
                String ssnSup = rs.getString(8);
                Employee emp = new Employee(ssn, fname, lname, address, salary, sex, depno, ssnSup);
                vector.add(emp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOEmployee.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public Vector<Employee> searchFname(String name) {
        Vector<Employee> vector = new Vector<Employee>();
        String sql = "select * from employee where fname like '%" + name + "%'";
        try {
            Statement state = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                String ssn = rs.getString(1);
                String fname = rs.getString(2);
                String lname = rs.getString(3);
                String address = rs.getString(4);
                double salary = rs.getDouble(5);
                int gt = rs.getInt(6);
                boolean sex = (gt == 1 ? true : false);
                String depno = rs.getString(7);
                String ssnSup = rs.getString(8);
                Employee emp = new Employee(ssn, fname, lname, address, salary, sex, depno, ssnSup);
                vector.add(emp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOEmployee.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public int removeEmployee(String ssn) {
        int n = 0;
        String sql = "delete from Employee where SSN = '" + ssn + "' AND"
                + "('" + ssn + "' not in (select SSNSupervisor from Employee where SSnSupervisor is not null))"
                + "AND ('" + ssn + "' not in (select distinct SSN from Dependence))"
                + "AND ('" + ssn + "' not in (select distinct SSN from Emp_Manage_Dept))"
                + "AND ('" + ssn + "' not in (select distinct SSN from Emp_WorkOn_Pro))";
        try {
            Statement state = con.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOEmployee.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }

    public boolean login(String user, String pass) {
        String sql = "SELECT * FROM Employee WHERE [SSN] = ?";
        try {
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setString(1, user);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                if (user.equals(rs.getString(1)) && pass.equals(rs.getString(2))) {
                    return true;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOEmployee.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean checkAdmin(String ssn) {
        String sql = "SELECT SSN FROM Employee WHERE SSN IN (SELECT SSNSupervisor FROM Employee)";
        try {
            PreparedStatement pre = con.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                if (ssn.equals(rs.getString(1))) {
                    return true;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOEmployee.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static void main(String[] args) {
        //Scanner scan = new Scanner(System.in);
        DAOEmployee dao = new DAOEmployee();
        //System.out.println(dao.login("AD001", "alice"));
        //dao.updateEmployee(new Employee("IS202", "Messi", "Tuan", "Ha noi", 1000, true, "IS", "IS001"));
        //System.out.print("Enter employee name: ");
        //System.out.println(dao.selectEmployee(scan.nextLine()));
        //System.out.println(dao.sqlCode(scan.nextLine()));
//        Vector<Employee> vector = dao.searchFname("messi");
//        for (Employee emp : vector) {
//            System.out.println(emp);
//        }
//        int n = dao.removeEmployee("IS3000");
//        if (n > 0) {
//            System.out.println("deleted!");
//        }
    }
}
