/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Herman
 */
public class PizzaShopAccess {

    private Connection connection;
    private PreparedStatement insertPS;

    public PizzaShopAccess(String driver) {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException ex) {
            System.err.println("Class not found! " + ex.getMessage());
        }
    }

    public boolean connect(String url, String user, String password) {
        try {
            connection = DriverManager.getConnection(url, user, password);
            insertPS = connection.prepareStatement("INSERT INTO"
                    + " `pizzashop`.`orderinfo` (`Name`, `Phone`, `Email`,"
                    + " `Street`, `Size`, `Toppings`, `Delivery`, `Price`,"
                    + " `Date`, `Time`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
            return true;
        } catch (SQLException ex) {
            logSQLException(ex);
        }
        return false;
    }

    public boolean disconnect() {
        try {
            insertPS.close();
            connection.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(PizzaShopAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean insertPizza(String name, String tel, String email, String address,
            char size, String toppings, boolean delivery, double price) {
        try {
            synchronized (insertPS) {
                insertPS.setString(1, name);
                insertPS.setString(2, tel);
                insertPS.setString(3, email);
                insertPS.setString(4, address);
                insertPS.setString(5, size + "");
                insertPS.setString(6, toppings);
                insertPS.setInt(7, delivery ? 1 : 0);
                insertPS.setDouble(8, price);
                insertPS.setDate(9, new java.sql.Date(System.currentTimeMillis()));
                insertPS.setTime(10, new java.sql.Time(System.currentTimeMillis()));
                int rowsChanged = insertPS.executeUpdate();
                System.out.println("*******"+rowsChanged);
                return rowsChanged == 1 ? true : false;
            }
        } catch (SQLException e) {
            logSQLException(e);
        }
        return false;
    }

    public static void logSQLException(SQLException ex) {
        System.out.println("Error! " + ex.getMessage()
                + " SQL state: " + ex.getSQLState()
                + " Error code: " + ex.getErrorCode());
    }
}
