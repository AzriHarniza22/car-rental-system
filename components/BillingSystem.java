package components;

import interfaces.IBilling;

public class BillingSystem implements IBilling {
    public boolean generateBill(String customerId, String resRef, double amount) {
        System.out.println("Tagihan untuk " + customerId + ": Rp" + amount + " untuk reservasi " + resRef);
        return true;
    }
}