/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.util.LinkedList;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Web application lifecycle listener.
 *
 * @author Herman
 */
public class lstnr implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.err.println("******Context created******");
        LinkedList<String> toppingList = new LinkedList<>();
        toppingList.add("pepperoni");
        toppingList.add("sausage");
        toppingList.add("mushrooms");
        toppingList.add("anchovies");
        toppingList.add("green olieves");
        toppingList.add("black olieves");
        toppingList.add("green peppers");
        toppingList.add("hot peppers");
        toppingList.add("extra cheese");
        toppingList.add("extra sauce");
         ServletContext sc = sce.getServletContext();
        sc.setAttribute("toppingList", toppingList);
        PizzaShopAccess psa = new PizzaShopAccess(sc.getInitParameter("driver"));
        if (! psa.connect(sc.getInitParameter("dburl"),
                    sc.getInitParameter("dbuser"),
                    sc.getInitParameter("dbpassword")))
            System.err.println("Could not connect");
           
            sc.setAttribute("psa", psa);
    }

    @Override
    /**
     * Free resources
     */
    public void contextDestroyed(ServletContextEvent sce) {
        System.err.println("******Context destroied******");
            ((PizzaShopAccess)sce.getServletContext().getAttribute("psa")).disconnect();
    }
}
