package components;

import interfaces.ICustomerMgt;
import java.util.*;

public class CustomerMgr implements ICustomerMgt {
    private Map<String, String> customers = new HashMap<>();

    public String registerCustomer(String name, String email) {
        String id = "CUST" + new Random().nextInt(10000);
        customers.put(id, name + " - " + email);
        return id;
    }

    public String getCustomer(String customerId) {
        return customers.get(customerId);
    }
}