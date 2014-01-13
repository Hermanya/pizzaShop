package controllers;

import data.Pizza;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Add another pizza to the order
 * @author Herman
 */
public class addToOrder extends HttpServlet {

    /**
     * Handles the HTTP
     * <code>POST</code> method. Delete if not used.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sess = request.getSession();
        ArrayList<Pizza> order = (ArrayList<Pizza>) sess.getAttribute("order");
        LinkedList<String> toppings = (LinkedList) request.getServletContext() 
                .getAttribute("toppingList");
        Map<String, String[]> params = request.getParameterMap();
        char size = params.get("size")[0].charAt(0);
        if (params.size() != 0) {
            ArrayList<String> newPizzaToppings = new ArrayList<>();
            for (String topping : toppings) {
                try {
                    if (params.get(topping)[0] != null) {
                        newPizzaToppings.add(topping);
                    }
                } catch (NullPointerException e) {
                }
            }
            Pizza pizza = new Pizza(size, newPizzaToppings);
            order.add(pizza);
            double total = data.Pizza.getTotal(order);
            sess.setAttribute("total", total*1.13);
            sess.setAttribute("tax", total*0.13);
        }
        response.sendRedirect("addPizza.jsp");
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Add another pizza to the order";
    }// </editor-fold>
}