package model;

public class RentalDetails {
    public String carId;
    public DateRange dateRange;
    public boolean claimed = false;

    public RentalDetails(String carId, DateRange dateRange) {
        this.carId = carId;
        this.dateRange = dateRange;
    }
}
