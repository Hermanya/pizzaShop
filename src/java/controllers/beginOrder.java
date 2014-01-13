package controllers;

import data.Customer;
import data.Pizza;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *  Store user information in the session and proceed to adding pizza
 * @author Herman
 */
public class beginOrder extends HttpServlet {

    /**
     * Handles the HTTP <code>POST</code> method. Delete if not used.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Map<String, String[]> map = request.getParameterMap();
        if (valid(map)) {
            HttpSession sess = request.getSession();
            Customer customer = new Customer(map.get("name")[0],
                    map.get("address")[0],
                    map.get("tel")[0],
                    map.get("email")[0],
                    Boolean.parseBoolean(
                            map.get("delivery")[0]
                    )
            );
            sess.setAttribute("customer", customer);
            sess.setAttribute("order", new ArrayList<Pizza>());
            response.sendRedirect("addPizza.jsp");
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Store user information in the session and proceed to adding pizza";
    }// </editor-fold>

    private boolean valid(Map<String, String[]> map) {
        return map.get("email")[0] != null
                && map.get("name")[0] != null
                && map.get("tel")[0] != null
                && map.get("address")[0] != null;
    }

}
