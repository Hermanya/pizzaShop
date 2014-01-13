package data;


import java.io.Serializable;
import java.util.ArrayList;

/**
 *  Describe pizza object, which represents pizza
 * @author Herman
 */
public class Pizza implements Serializable{

    private char size;
    private ArrayList<String> toppings;
    /**
     * Default constructor
     * @param size
     * @param toppings 
     */
    public Pizza(char size, ArrayList toppings) {
        this.size = size;
        this.toppings = toppings;
    }

    public char getSize() {
        return size;
    }
    /**
     * Convert ArrayList of toppings to a string
     * @return string of toppings, separated by commas 
     */
    public String getToppings() {
        String string = "";

        for (String s : toppings) {
            string += s + ",";
        }
        return toppings.isEmpty()?"no toppings":string.substring(0, string.length()-1);
    }

    /**
     * Calculate the price of a pizza
     *
     * @return price of the pizza
     */
    public double getPrice() {
        double price = 0;
        switch (size) {
            case 's':
                price += 9.99;
                break;
            case 'm':
                price += 12.99;
                break;
            case 'l':
                price += 14.99;
                break;
            case 'x':
                price += 19.99;
                break;
        }
        int numberOfToppings = toppings.size();
        price += numberOfToppings - numberOfToppings / 4;
        price=Math.ceil(price*100)/100;
        return price;
    }
    /**
     * Sum up the prices of a list of pizzas
     * @param list
     * @return 
     */
    public static double getTotal(ArrayList<Pizza> list){
        double total = 0;
        for(Pizza pizza:list){
            total+=pizza.getPrice();
        }
        return total;
    }
}
