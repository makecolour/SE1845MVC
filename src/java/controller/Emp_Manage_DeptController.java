/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Emp_Manage_Dept;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Vector;
import model.DAOEmp_Manage_Dept;

/**
 *
 * @author quyen
 */
@WebServlet(name = "Emp_Manage_DeptController", urlPatterns = {"/Emp_Manage_DeptController"})
public class Emp_Manage_DeptController extends HttpServlet {

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
        DAOEmp_Manage_Dept dao = new DAOEmp_Manage_Dept();

        HttpSession session = request.getSession(true);
        String service = request.getParameter("service");
        if (service == null) {
            service = "listAll";
        }
        if (service.equals("listAll")) {
            String option = request.getParameter("option");
            if (option == null) {
                Vector<Emp_Manage_Dept> vector = dao.getAll("select * from Emp_Manage_Dept");
                request.setAttribute("data", vector);
                request.setAttribute("titleTable", "List of Emp_Manage_Dept");
            } else if (option.equals("searchssn")) {
                String ssn = request.getParameter("ssn");
                Vector<Emp_Manage_Dept> vector = dao.searchSSN(ssn);
                request.setAttribute("data", vector);
                request.setAttribute("titleTable", "Emp_Manage_Dept SSN:");
            }

            RequestDispatcher dis = request.getRequestDispatcher("jsp/Emp_Manage_DeptManage.jsp");
            dis.forward(request, response);
        } else if (service.equals("insert")) {
            String submit = request.getParameter("submit");
            if (submit == null) {
                request.getRequestDispatcher("jsp/InsertEmp_Manage_Dept.jsp").forward(request, response);
            } else {
                Emp_Manage_Dept emp = new Emp_Manage_Dept(request.getParameter("SSN"), request.getParameter("DeptNo"),
                        request.getParameter("StartDate"), request.getParameter("EndDate"));
                dao.addEmp_Manage_Dept(emp);
                response.sendRedirect("Emp_Manage_DeptController");
            }
        } else if (service.equals("delete")) {
            dao.removeEmp_Manage_Dept(request.getParameter("ssn"), request.getParameter("deptno"), request.getParameter("startdate"));
            response.sendRedirect("Emp_Manage_DeptController?service=listAll");
        } else if (service.equals("update")) {
            String submit = request.getParameter("submit");
            if (submit == null) {
                String ssn = request.getParameter("ssn");
                String deptno = request.getParameter("deptno");
                String startDate = request.getParameter("startdate");
                Vector<Emp_Manage_Dept> vector = dao.getAll("select * from emp_manage_dept where ssn = '" + ssn + "' AND deptno = '" + deptno + "' AND startdate = '" + startDate + "'");
                request.setAttribute("data", vector);
                RequestDispatcher dis = request.getRequestDispatcher("jsp/UpdateEmp_Manage_Dept.jsp");
                dis.forward(request, response);
            } else {
                String ssn = request.getParameter("SSN");
                String deptno = request.getParameter("DeptNo");
                String startDate = request.getParameter("StartDate");
                String endDate = request.getParameter("EndDate");
                Emp_Manage_Dept emp = new Emp_Manage_Dept(ssn, deptno, startDate, endDate);
                dao.updateEmp_Manage_Dept(emp);
                response.sendRedirect("Emp_Manage_DeptController?service=listAll");
            }
        }

//        response.setContentType("text/html;charset=UTF-8");
//        try (PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet Emp_Manage_DeptController</title>");  
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet Emp_Manage_DeptController at " + request.getContextPath () + "</h1>");
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
