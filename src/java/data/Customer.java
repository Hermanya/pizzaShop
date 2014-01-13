package data;


/**
 *  Describe a customer object which represents the customer
 * @author Herman
 */
public class Customer {
  private String name;
  private String address;
  private String tel;
  private String email;
  private Boolean delivery;
  /**
   * Default constructor
   * @param name
   * @param address
   * @param tel
   * @param email
   * @param delivery 
   */
    public Customer(String name, String address, String tel, String email, Boolean delivery) {
        this.name = name;
        this.address = address;
        this.tel = tel;
        this.email = email;
        this.delivery = delivery;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getTel() {
        return tel;
    }

    public String getEmail() {
        return email;
    }

    public Boolean getDelivery() {
        return delivery;
    }
  
}
