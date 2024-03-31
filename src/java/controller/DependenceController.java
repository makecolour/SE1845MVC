/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Dependence;
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
import model.DAODependence;

/**
 *
 * @author quyen
 */
@WebServlet(name = "DependenceController", urlPatterns = {"/DependenceController"})
public class DependenceController extends HttpServlet {

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
        DAODependence dao = new DAODependence();

        HttpSession session = request.getSession(true);
        String service = request.getParameter("service");
        if (service == null) {
            service = "listAll";
        }
        if (service.equals("listAll")) {
            String option = request.getParameter("option");
            if (option == null) {
                Vector<Dependence> vector = dao.getAll("select * from Dependence");
                request.setAttribute("data", vector);
                request.setAttribute("titleTable", "List of Dependence");
            } else if (option.equals("searchname")) {
                String depName = request.getParameter("name");
                Vector<Dependence> vector = dao.searchName(depName);
                request.setAttribute("data", vector);
                request.setAttribute("titleTable", "Dependence name: " + depName);
            }

            RequestDispatcher dis = request.getRequestDispatcher("jsp/DependenceManage.jsp");
            dis.forward(request, response);
        } else if (service.equals("update")) {
            String submit = request.getParameter("submit");
            if (submit == null) {
                String ssn = request.getParameter("ssn");
                String name = request.getParameter("name");
                Vector<Dependence> vector = dao.getAll("select * from dependence where ssn ='" + ssn + "' AND name = '" + name + "'");
                request.setAttribute("data", vector);
                RequestDispatcher dis = request.getRequestDispatcher("jsp/UpdateDependence.jsp");
                dis.forward(request, response);
            } else {
                String ssn = request.getParameter("SSN");
                String name = request.getParameter("Name");
                String dob = request.getParameter("DOB");
                String relationship = request.getParameter("Relationship");
                Dependence dep = new Dependence(ssn, name, dob, relationship);
                dao.updateDependence(dep);
                response.sendRedirect("DependenceController?service=listAll");
            }
        } else if (service.equals("insert")) {
            String submit = request.getParameter("submit");
            if (submit == null) {
                request.getRequestDispatcher("jsp/InsertDependence.jsp").forward(request, response);
            } else {
                Dependence dep = new Dependence(request.getParameter("SSN"), request.getParameter("Name"),
                        request.getParameter("DOB"), request.getParameter("Relationship"));
                dao.addDependence(dep);
                response.sendRedirect("DependenceController");
            }
        } else if (service.equals("delete")) {
            dao.removeDependence(request.getParameter("ssn"), request.getParameter("name"));
            response.sendRedirect("DependenceController?service=listAll");
        }

//        response.setContentType("text/html;charset=UTF-8");
//        try (PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet DependenceController</title>");  
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet DependenceController at " + request.getContextPath () + "</h1>");
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
