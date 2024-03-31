package controller;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
import entity.Employee;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Vector;
import model.DAOEmployee;

/**
 *
 * 
 */
@WebServlet(name = "EmployeeController", urlPatterns = {"/EmployeeController"})
public class EmployeeController extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        DAOEmployee dao = new DAOEmployee();

        HttpSession session = request.getSession(true);
        String service = request.getParameter("service");
        if (service == null) {
            service = "listAll";
        }
        if (service.equals("listAll")) {
            String option = request.getParameter("option");
            if (option == null) {
                Vector<Employee> vector = dao.getAll("select * from Employee");
                request.setAttribute("data", vector);
                request.setAttribute("titleTable", "List of Employee");
            } else if (option.equals("searchfname")) {
                String fName = request.getParameter("fname");
                Vector<Employee> vector = dao.searchFname(fName);
                request.setAttribute("data", vector);
                request.setAttribute("titleTable", "Employee first name: " + fName);
            }

            RequestDispatcher dis = request.getRequestDispatcher("jsp/EmployeeManage.jsp");
            dis.forward(request, response);
        } else if (service.equals("update")) {
            String submit = request.getParameter("submit");
            if (submit == null) {
                String ssn = request.getParameter("ssn");
                Vector<Employee> vector = dao.getAll("select * from employee where SSN= '" + ssn + "'");
                request.setAttribute("data", vector);
                RequestDispatcher dis = request.getRequestDispatcher("jsp/UpdateEmployee.jsp");
                dis.forward(request, response);
            } else {
                String ssn = request.getParameter("SSN");
                String fname = request.getParameter("FName");
                String lname = request.getParameter("LName");
                String address = request.getParameter("Address");
                double salary = Double.parseDouble(request.getParameter("Salary"));
                boolean sex = request.getParameter("Sex").equals("1") ? true : false;
                String deptno = request.getParameter("DeptNo");
                String ssnsupervisor = request.getParameter("SSNSupervisor");
                Employee emp = new Employee(ssn, fname, lname, address, salary, sex, deptno, ssnsupervisor);
                dao.updateEmployee(emp);
                response.sendRedirect("EmployeeController?service=listAll");
            }
        } else if (service.equals("insert")) {
            String submit = request.getParameter("submit");
            if (submit == null) {
                request.getRequestDispatcher("jsp/InsertEmployee.jsp").forward(request, response);
            } else {
                Employee emp = new Employee(request.getParameter("SSN"), request.getParameter("FName"), request.getParameter("LName"),
                        request.getParameter("Address"), Double.parseDouble(request.getParameter("Salary")), request.getParameter("Sex").equals("1") ? true : false,
                        request.getParameter("DeptNo"), request.getParameter("SSNSupervisor"));
                dao.addEmployee(emp);
                response.sendRedirect("EmployeeController");
            }
        } else if (service.equals("delete")) {
            dao.removeEmployee(request.getParameter("ssn"));
            response.sendRedirect("EmployeeController?service=listAll");
        }

//        try (PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet EmployeeController</title>");  
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet EmployeeController at " + request.getContextPath () + "</h1>");
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
