package interfaces;

public interface IBilling {
    boolean generateBill(String customerId, String resRef, double amount);
}
