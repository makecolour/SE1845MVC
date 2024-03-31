/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Bill;
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
import model.DAOBill;

/**
 *
 * @author quyen
 */
@WebServlet(name = "BillController", urlPatterns = {"/BillController"})
public class BillController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        DAOBill dao = new DAOBill();
        boolean tableExists = dao.checkIfExist();
        String submit;
        Vector<Bill> vector = new Vector<Bill>();
        RequestDispatcher dis;
        HttpSession session = request.getSession(true);
        String service = request.getParameter("service");
        if (!tableExists) {
            dao.addTable();
        }
        if (service == null) {
            service = "listAll";
        }
        switch (service) {
            case "listAll":
                vector = dao.getAll("select * from Bill");
                request.setAttribute("data", vector);
                request.setAttribute("titleTable", "List of Bill");
                dis = request.getRequestDispatcher("jsp/BillManage.jsp");
                dis.forward(request, response);
                break;

            case "update":
                submit = request.getParameter("submit");
                if (submit == null) {
                    String billId = request.getParameter("Bill_ID");
                    vector = dao.getAll("select * from Bill where Bill_ID = '" + billId + "'");
                    request.setAttribute("data", vector);
                    dis = request.getRequestDispatcher("jsp/UpdateBill.jsp");
                    dis.forward(request, response);
                } else {
                    response.sendRedirect("BillController?service=listAll");
                }
                break;

//            case "insert":
//                submit = request.getParameter("submit");
//                if (submit == null) {
//                    request.getRequestDispatcher("jsp/InsertBill.jsp").forward(request, response);
//                } else {
//                    Bill bill = new Bill(request.getParameter("billId"), request.getParameter("customerName"), request.getParameter("date"), request.getParameter("total"), request.getParameter("status"));
//                    
//                    response.sendRedirect("BillController");
//                }
//                break;
            default:
                response.sendRedirect("BillController?service=listAll");
        }
    }
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


