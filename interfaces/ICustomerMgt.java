package interfaces;

public interface ICustomerMgt {
    String registerCustomer(String name, String email);
    String getCustomer(String customerId);
}