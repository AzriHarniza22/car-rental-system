import components.*;
import model.*;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        CarMgr carMgr = new CarMgr();
        CustomerMgr custMgr = new CustomerMgr();
        BillingSystem billing = new BillingSystem();
        
        ReservationSystem system = new ReservationSystem(carMgr, custMgr, billing);
        Scanner sc = new Scanner(System.in);

        try {
            System.out.println("Login sebagai (admin/pelanggan): ");
            String role = sc.nextLine();

            if (role.equalsIgnoreCase("admin")) {
                while (true) {
                    System.out.println("1. Tambah Mobil\n2. Edit Mobil\n3. Hapus Mobil\n4. Lihat Mobil\n5. Keluar");
                    String choice = sc.nextLine();
                    switch (choice) {
                        case "1":
                            System.out.print("ID: "); String id = sc.nextLine();
                            System.out.print("Model: "); String model = sc.nextLine();
                            System.out.print("Tipe: "); String type = sc.nextLine();
                            System.out.print("Harga: "); double price = Double.parseDouble(sc.nextLine());
                            carMgr.addCar(new CarDetails(id, model, type, true, price));
                            break;
                        case "2":
                            System.out.print("ID: "); String eid = sc.nextLine();
                            System.out.print("Model baru: "); String emodel = sc.nextLine();
                            System.out.print("Tipe baru: "); String etype = sc.nextLine();
                            System.out.print("Harga baru: "); double eprice = Double.parseDouble(sc.nextLine());
                            carMgr.editCar(eid, emodel, etype, eprice);
                            break;
                        case "3":
                            System.out.print("ID: "); String did = sc.nextLine();
                            carMgr.deleteCar(did);
                            break;
                        case "4":
                            carMgr.listCars().forEach(c -> System.out.println(c.carId + " - " + c.model + " - " + c.price));
                            break;
                        case "5":
                            return;
                    }
                }
            } else {
                System.out.print("Nama: "); String name = sc.nextLine();
                System.out.print("Email: "); String email = sc.nextLine();
                String custId = custMgr.registerCustomer(name, email);

                System.out.println("Mobil Tersedia:");
                CarDetails[] list = carMgr.getAvailableCars("");
                for (int i = 0; i < list.length; i++) {
                    System.out.println((i + 1) + ". " + list[i].model + " - Rp" + list[i].price);
                }
                System.out.print("Pilih (nomor): ");
                int pilih = Integer.parseInt(sc.nextLine()) - 1;
                CarDetails chosen = list[pilih];

                System.out.print("Mulai (YYYY-MM-DD): "); String start = sc.nextLine();
                System.out.print("Selesai (YYYY-MM-DD): "); String end = sc.nextLine();
                DateRange dr = new DateRange(start, end);
                String resRef = system.reserveCar(custId, chosen.carId, dr);
                System.out.println("Reservasi Berhasil. Kode: " + resRef);

                System.out.print("Mulai sewa sekarang? (y/n): ");
                if (sc.nextLine().equalsIgnoreCase("y")) {
                    String plate = system.startCarRental(resRef);
                    System.out.println("sewa Mobil dimulai. Plat: " + plate);
                }
            }
        } finally {
            sc.close();
        }
    }
}