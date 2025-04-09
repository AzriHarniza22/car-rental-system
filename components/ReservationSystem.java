package components;

import model.*;

public class ReservationSystem {
    private CarMgr carMgr;
    private CustomerMgr custMgr;
    private BillingSystem billing;

    public ReservationSystem(CarMgr carMgr, CustomerMgr custMgr, BillingSystem billing) {
        this.carMgr = carMgr;
        this.custMgr = custMgr;
        this.billing = billing;
    }

    public String reserveCar(String customerId, String carId, DateRange dateRange) {
        CarDetails car = carMgr.getCarInfo(carId);
        if (car == null || !car.available) throw new IllegalArgumentException("Mobil tidak tersedia");

        RentalDetails rental = new RentalDetails(carId, dateRange);
        String resRef = carMgr.makeReservation(rental, customerId);
        
        String customerInfo = custMgr.getCustomer(customerId);
        System.out.println("Reservasi untuk pelanggan: " + customerInfo);
        
        billing.generateBill(customerId, resRef, car.price);
        return resRef;
    }

    public String startCarRental(String resRef) {
        return carMgr.startRental(resRef);
    }
}