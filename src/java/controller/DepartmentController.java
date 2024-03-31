/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Department;
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
import model.DAODepartment;

/**
 *
 * @author quyen
 */
@WebServlet(name = "DepartmentController", urlPatterns = {"/DepartmentController"})
public class DepartmentController extends HttpServlet {

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
        DAODepartment dao = new DAODepartment();

        HttpSession session = request.getSession(true);
        String service = request.getParameter("service");
        if (service == null) {
            service = "listAll";
        }
        if (service.equals("listAll")) {
            String option = request.getParameter("option");
            if (option == null) {
                Vector<Department> vector = dao.getAll("select * from Department");
                request.setAttribute("data", vector);
                request.setAttribute("titleTable", "List of Department");
            } else if (option.equals("searchname")) {
                String departName = request.getParameter("name");
                Vector<Department> vector = dao.searchName(departName);
                request.setAttribute("data", vector);
                request.setAttribute("titleTable", "Department name:");
            }

            RequestDispatcher dis = request.getRequestDispatcher("jsp/DepartmentManage.jsp");
            dis.forward(request, response);
        } else if (service.equals("update")) {
            String submit = request.getParameter("submit");
            if (submit == null) {
                String deptno = request.getParameter("deptno");
                Vector<Department> vector = dao.getAll("select * from department where DEPTNO = '" + deptno + "'");
                request.setAttribute("data", vector);
                request.getRequestDispatcher("jsp/UpdateDepartment.jsp").forward(request, response);
            } else {
                String deptno = request.getParameter("DeptNo");
                String name = request.getParameter("Name");
                String location = request.getParameter("Location");
                Department depart = new Department(deptno, name, location);
                dao.updateDAODepartment(depart);
                response.sendRedirect("DepartmentController?service=listAll");
            }
        } else if (service.equals("insert")) {
            String submit = request.getParameter("submit");
            if (submit == null) {
                request.getRequestDispatcher("jsp/InsertDepartment.jsp").forward(request, response);
            } else {
                Department depart = new Department(request.getParameter("DeptNo"), request.getParameter("Name"),
                        request.getParameter("Location"));
                dao.addDepartment(depart);
                response.sendRedirect("DepartmentController");
            }

        } else if (service.equals("delete")) {
            dao.removeDepartment(request.getParameter("deptno"));
            response.sendRedirect("DepartmentController?service=listAll");
        }

//        response.setContentType("text/html;charset=UTF-8");
//        try (PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet DepartmentController</title>");  
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet DepartmentController at " + request.getContextPath () + "</h1>");
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
