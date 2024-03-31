/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Bill;
import entity.Emp_WorkOn_Pro;
import entity.Employee;
import entity.EmployeeCart;
import entity.Project;
import entity.UserLogin;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.Vector;
import model.DAOEmp_WorkOn_Pro;
import model.DAOEmployee;
import model.DAOProject;
import model.DAOBill;

/**
 *
 * @author quyen
 */
@WebServlet(name = "CartController", urlPatterns = {"/CartController"})
public class CartController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        DAOEmployee dao = new DAOEmployee();
        String service = request.getParameter("service");

        if (service.equals("addtocart")) {
            String ssn = request.getParameter("ssn");

            EmployeeCart empCart = (EmployeeCart) session.getAttribute(ssn);
            if (empCart == null) {
                empCart = new EmployeeCart();
                Vector<Employee> vector = dao.getAll("select * from employee where SSN= '" + ssn + "'");
                Employee emp = vector.get(0);
                empCart.setSSN(emp.getSSN());
                empCart.setFName(emp.getFName());
                empCart.setLName(emp.getLName());
                empCart.setSalary(emp.getSalary());
                empCart.setQuantity(1);
                session.setAttribute(ssn, empCart);
            } else {
                empCart.setQuantity(empCart.getQuantity() + 1);
                session.setAttribute(ssn, empCart);
            }
            response.sendRedirect("EmployeeController");
        } else if (service.equals("showcart")) {
            DAOProject daoPro = new DAOProject();
            Vector<Project> proVec = daoPro.getAll("With TableA AS(\n"
                    + "SELECT Emp_WorkOn_Pro.ProNo FROM Employee LEFT JOIN Emp_WorkOn_Pro\n"
                    + "ON Employee.SSN = Emp_WorkOn_Pro.SSN\n"
                    + "WHERE Employee.SSN = '" + ((UserLogin) session.getAttribute("UserLogin")).getUserName() + "')\n"
                    + "\n"
                    + "SELECT * FROM Project INNER JOIN TableA ON\n"
                    + "TableA.ProNo = Project.ProNo");
            if (proVec.isEmpty()) {
                proVec = daoPro.getAll("select * from project");
            }
            request.setAttribute("data", proVec);
            RequestDispatcher dis = request.getRequestDispatcher("jsp/ShowCart.jsp");
            dis.forward(request, response);
        } else if (service.equals("remove")) {
            String key = request.getParameter("ssn");
            session.removeAttribute(key);
            response.sendRedirect("CartController?service=showcart");
        } else if (service.equals("removeall")) {
            Enumeration em = session.getAttributeNames();
            while (em.hasMoreElements()) {
                String key = em.nextElement().toString();
                if (!key.equals("UserLogin")) {
                    session.removeAttribute(key);
                }
            }
            response.sendRedirect("CartController?service=showcart");
        } else if (service.equals("update")) {
            Enumeration<String> allNames = session.getAttributeNames();

            while (allNames.hasMoreElements()) {
                String key = allNames.nextElement();
                if (!key.equals("UserLogin")) {
                    EmployeeCart empCart = (EmployeeCart) session.getAttribute(key);
                    int quantity = Integer.parseInt(request.getParameter(key + "quantity"));
                    String Position = request.getParameter(key + "position");
                    String ProNo = request.getParameter("ProNo");
                    if (quantity > 0) {
                        empCart.setQuantity(quantity);
                    } else if (quantity == 0) {
                        session.removeAttribute(key);
                    }
                    empCart.setPosition(Position);
                    empCart.setProNo(ProNo);
                    System.out.println(empCart);
                    session.setAttribute(key, empCart);
                }
            }
            response.sendRedirect("CartController?service=showcart");
        } else if (service.equals("checkout")) {
            DAOBill daoBill = new DAOBill();
            DAOEmp_WorkOn_Pro daoEwp = new DAOEmp_WorkOn_Pro();
            Enumeration<String> allNames = session.getAttributeNames();
            
            while (allNames.hasMoreElements()) {
                String key = allNames.nextElement();
                if (!key.equals("UserLogin")) {
                    EmployeeCart empCart = (EmployeeCart) session.getAttribute(key);
//                    Vector<Bill> bill = daoBill.getAll("select * from Bill where Bill_ID = "+ empCart.getSSN());
//                    if(bill.isEmpty())
//                    {
//                       daoBill.insertBill(bill);
//                    }
//                    else{
//                        
//                    }
                    Vector<Emp_WorkOn_Pro> ewpVec = daoEwp.getAll("select * from Emp_WorkOn_Pro where ssn = '" + empCart.getSSN() + "' AND prono = '" + empCart.getProNo() + "'");
                    if (!ewpVec.isEmpty()) {
                        Emp_WorkOn_Pro ewp = new Emp_WorkOn_Pro(empCart.getSSN(), empCart.getProNo(), empCart.getPosition(), empCart.getQuantity());
                        daoEwp.updateEmp_WorkOn_Pro(ewp);
                    } else {
                        Emp_WorkOn_Pro ewp = new Emp_WorkOn_Pro(empCart.getSSN(), empCart.getProNo(), empCart.getPosition(), empCart.getQuantity());
                        daoEwp.addEmp_WorkOn_Pro(ewp);
                    }
                }
            }
            response.sendRedirect("Emp_WorkOn_ProController?service=listAll");
        }
        
//        response.setContentType("text/html;charset=UTF-8");
//        try (PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet CartController</title>");
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet CartController at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
