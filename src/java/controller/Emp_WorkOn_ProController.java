/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Emp_WorkOn_Pro;
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
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.DAOEmp_WorkOn_Pro;

/**
 *
 * @author quyen
 */
@WebServlet(name = "Emp_WorkOn_ProController", urlPatterns = {"/Emp_WorkOn_ProController"})
public class Emp_WorkOn_ProController extends HttpServlet {

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
        DAOEmp_WorkOn_Pro dao = new DAOEmp_WorkOn_Pro();

        HttpSession session = request.getSession(true);
        String service = request.getParameter("service");
        if (service == null) {
            service = "listAll";
        }
        if (service.equals("listAll")) {
            String option = request.getParameter("option");
            if (option == null) {
                Vector<Emp_WorkOn_Pro> vector = dao.getAll("select * from Emp_WorkOn_Pro");
                request.setAttribute("data", vector);
                request.setAttribute("titleTable", "List of Emp_WorkOn_Pro");
            } else if (option.equals("searchssn")) {
                String ssn = request.getParameter("ssn");
                Vector<Emp_WorkOn_Pro> vector = dao.searchSSN(ssn);
                request.setAttribute("data", vector);
                request.setAttribute("titleTable", "Emp_WorkOn_Pro SSN: " + ssn);
            }
            else if(option.equals("bill"))
            {
                UserLogin user = (UserLogin)session.getAttribute("UserLogin");
                Vector<Emp_WorkOn_Pro> vector = dao.getEmpFromPro(user.getUserName());
                request.setAttribute("data", vector);
                request.setAttribute("titleTable", "First Review");
            }
            
            else if(option.equals("project"))
            {
                String project = request.getParameter("id");
                LOG.log(Level.SEVERE, project);
                Vector<Emp_WorkOn_Pro> vector = dao.getEmpByProID(project);
                request.setAttribute("data", vector);
                request.setAttribute("titleTable", "Second Review");
            }
            RequestDispatcher dis = request.getRequestDispatcher("jsp/Emp_WorkOn_ProManage.jsp");
            dis.forward(request, response);
        } else if (service.equals("insert")) {
            String submit = request.getParameter("submit");
            if (submit == null) {
                request.getRequestDispatcher("jsp/InsertEmp_WorkOn_Pro.jsp").forward(request, response);
            } else {
                Emp_WorkOn_Pro ewp = new Emp_WorkOn_Pro(request.getParameter("SSN"), request.getParameter("ProNo"),
                        request.getParameter("Position"), Double.parseDouble(request.getParameter("HourPerDay")));
                dao.addEmp_WorkOn_Pro(ewp);
                response.sendRedirect("Emp_WorkOn_ProController");
            }
        } else if (service.equals("delete")) {
            dao.removeEmp_WorkOn_Pro(request.getParameter("ssn"), request.getParameter("prono"));
            response.sendRedirect("Emp_WorkOn_ProController?service=listAll");
        } else if (service.equals("update")) {
            String submit = request.getParameter("submit");
            if (submit == null) {
                String ssn = request.getParameter("ssn");
                String prono = request.getParameter("prono");
                Vector<Emp_WorkOn_Pro> vector = dao.getAll("select * from emp_workon_pro where ssn = '" + ssn + "' AND prono = '" + prono + "'");
                request.setAttribute("data", vector);
                RequestDispatcher dis = request.getRequestDispatcher("jsp/UpdateEmp_WorkOn_Pro.jsp");
                dis.forward(request, response);
            }
            else if(service.equals("listAllCoworker")){
                UserLogin user = (UserLogin) session.getAttribute("user");
                Vector<Emp_WorkOn_Pro> vector = dao.getEmpFromPro(user.getUserName());
                request.setAttribute("data", vector);
                RequestDispatcher dis = request.getRequestDispatcher("jsp/Emp_WorkOn_ProManage.jsp");
                dis.forward(request, response);
            }
            else {
                String ssn = request.getParameter("SSN");
                String prono = request.getParameter("ProNo");
                String position = request.getParameter("Position");
                double hourPerDay = Double.parseDouble(request.getParameter("HourPerDay"));
                Emp_WorkOn_Pro ewp = new Emp_WorkOn_Pro(ssn, prono, position, hourPerDay);
                dao.updateEmp_WorkOn_Pro(ewp);
                response.sendRedirect("Emp_WorkOn_ProController?service=listAll");
            }
        }

//        response.setContentType("text/html;charset=UTF-8");
//        try (PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet Emp_WorkOn_ProController</title>");  
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet Emp_WorkOn_ProController at " + request.getContextPath () + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        }
    }
    private static final Logger LOG = Logger.getLogger(Emp_WorkOn_ProController.class.getName());

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
