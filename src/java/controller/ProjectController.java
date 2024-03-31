/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Project;
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
import model.DAOProject;

/**
 *
 * @author quyen
 */
@WebServlet(name = "ProjectController", urlPatterns = {"/ProjectController"})
public class ProjectController extends HttpServlet {

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
        DAOProject dao = new DAOProject();

        HttpSession session = request.getSession(true);
        String service = request.getParameter("service");
        if (service == null) {
            service = "listAll";
        }
        if (service.equals("listAll")) {
            String option = request.getParameter("option");
            if (option == null) {
                Vector<Project> vector = dao.getAll("select * from Project");
                request.setAttribute("data", vector);
                request.setAttribute("titleTable", "List of Project");
            } else if (option.equals("searchname")) {
                String proName = request.getParameter("name");
                Vector<Project> vector = dao.searchName(proName);
                request.setAttribute("data", vector);
                request.setAttribute("titleTable", "Project name: " + proName);
            }

            RequestDispatcher dis = request.getRequestDispatcher("jsp/ProjectManage.jsp");
            dis.forward(request, response);
        } else if (service.equals("update")) {
            String submit = request.getParameter("submit");
            if (submit == null) {
                String prono = request.getParameter("prono");
                Vector<Project> vector = dao.getAll("select * from Project where ProNo = '" + prono + "'");
                request.setAttribute("data", vector);
                request.getRequestDispatcher("jsp/UpdateProject.jsp").forward(request, response);
            } else {
                String prono = request.getParameter("ProNo");
                String name = request.getParameter("Name");
                String startDate = request.getParameter("StartDate");
                String endDate = request.getParameter("EndDate");
                String depno = request.getParameter("DeptNo");
                Project project = new Project(prono, name, startDate, endDate, depno);
                dao.updateProject(project);
                response.sendRedirect("ProjectController?service=listAll");
            }
        } else if (service.equals("insert")) {
            String submit = request.getParameter("submit");
            if (submit == null) {
                request.getRequestDispatcher("jsp/InsertProject.jsp").forward(request, response);
            } else {
                Project pro = new Project(request.getParameter("ProNo"), request.getParameter("Name"), request.getParameter("StartDate"),
                        request.getParameter("EndDate"), request.getParameter("DeptNo"));
                dao.addProject(pro);
                response.sendRedirect("ProjectController");
            }
        } else if (service.equals("delete")) {
            dao.removeProject(request.getParameter("prono"));
            response.sendRedirect("ProjectController?service=listAll");
        }

//        response.setContentType("text/html;charset=UTF-8");
//        try (PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet ProjectController</title>");  
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet ProjectController at " + request.getContextPath () + "</h1>");
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
