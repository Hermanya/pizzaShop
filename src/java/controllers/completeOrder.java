/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import data.Customer;
import data.Pizza;
import data.PizzaShopAccess;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Herman
 */
public class completeOrder extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PizzaShopAccess  psa = (PizzaShopAccess) this.getServletContext().getAttribute("psa");
        HttpSession sess = null;
        sess = request.getSession(false);
        if (sess == null) {
            response.sendRedirect("index.html");
        }
        Customer cstmr = (Customer) sess.getAttribute("customer");
        ArrayList<Pizza> rdr = (ArrayList<Pizza>) sess.getAttribute("order");
        boolean rowsChanged = true;
        for (Pizza pizza : rdr) {
             if (! psa.insertPizza(cstmr.getName(),cstmr.getTel(),
                cstmr.getEmail(),cstmr.getAddress(),
                pizza.getSize(),pizza.getToppings(),
                cstmr.getDelivery(), pizza.getPrice()))
                 rowsChanged = false;
        }
        GregorianCalendar c = new GregorianCalendar();
                    c.add(GregorianCalendar.MINUTE, cstmr.getDelivery()?45:20);
                    sess.setAttribute("time",c.getTime()); 
        sess.setAttribute("processed", rowsChanged);
        response.sendRedirect("checkout.jsp");
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
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
     * Handles the HTTP
     * <code>POST</code> method.
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
