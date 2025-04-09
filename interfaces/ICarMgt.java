package interfaces;

import model.CarDetails;
import model.RentalDetails;

public interface ICarMgt {
    CarDetails[] getAvailableCars(String match);
    CarDetails getCarInfo(String carId);
    String makeReservation(RentalDetails res, String custId);
    RentalDetails getReservation(String resRef);
    String startRental(String resRef);
}
