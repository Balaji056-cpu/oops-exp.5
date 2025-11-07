import java.util.Scanner;
class Hotel {
    protected String hotelName;
    protected String location;
    protected int roomNo;
    public Hotel(String hotelName, String location, int roomNo) {
        this.hotelName = hotelName;
        this.location = location;
        this.roomNo = roomNo;
    }
    public void checkIn() {
        System.out.println("Checking into " + hotelName + " at " + location);
    }
    public void checkOut() {
        System.out.println("Checking out from " + hotelName + "...");
    }
    public void displayBill() {
        System.out.println("Display bill: Generic Hotel. (To be overridden by subclasses)");
    }
}
class DeluxeRoom extends Hotel {
    private String custName;
    private int noOfDays;
    private double roomRate;
    private double GST;
    public DeluxeRoom(String hotelName, String location, int roomNo, String custName, int noOfDays, double roomRate) {
        super(hotelName, location, roomNo);
        this.custName = custName;
        this.noOfDays = noOfDays;
        this.roomRate = roomRate;
        this.GST = 0.05; // 5% tax
    }
    @Override
    public void checkIn() {
        System.out.println("Deluxe Room Check-In for " + custName);
        super.checkIn();
    }

    @Override
    public void checkOut() {
        System.out.println("Deluxe Room Check-Out for " + custName);
        super.checkOut();
    }
    @Override
    public void displayBill() {
        double total = roomRate * noOfDays;
        double tax = total * GST;
        double bill = total + tax;
        System.out.println("\n------ Deluxe Room Bill ------");
        System.out.println("Customer Name : " + custName);
        System.out.println("Room No       : " + roomNo);
        System.out.println("No. of Days   : " + noOfDays);
        System.out.println("Room Rate     : " + roomRate);
        System.out.println("Tax (5%)      : " + tax);
        System.out.println("Total Bill    : " + bill);
        System.out.println("------------------------------\n");
    }
}
class SuiteRoom extends Hotel {
    private String custName;
    private int noOfDays;
    private double roomRate;
    private double GST;
    private double serviceCharge;
    public SuiteRoom(String hotelName, String location, int roomNo, String custName, int noOfDays, double roomRate) {
        super(hotelName, location, roomNo);
        this.custName = custName;
        this.noOfDays = noOfDays;
        this.roomRate = roomRate;
        this.GST = 0.10; // 10% tax
        this.serviceCharge = 500.0; // fixed service charge
    }

    @Override
    public void checkIn() {
        System.out.println("Suite Room Check-In for " + custName);
        super.checkIn();
    }
    @Override
    public void checkOut() {
        System.out.println("Suite Room Check-Out for " + custName);
        super.checkOut();
    }
    @Override
    public void displayBill() {
        double total = roomRate * noOfDays;
        double tax = total * GST;
        double bill = total + tax + serviceCharge;
        System.out.println("\n------ Suite Room Bill ------");
        System.out.println("Customer Name : " + custName);
        System.out.println("Room No       : " + roomNo);
        System.out.println("No. of Days   : " + noOfDays);
        System.out.println("Room Rate     : " + roomRate);
        System.out.println("Tax (10%)     : " + tax);
        System.out.println("Service Charge: " + serviceCharge);
        System.out.println("Total Bill    : " + bill);
        System.out.println("-----------------------------\n");
    }
}
public class HotelManagement {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("===== HOTEL MANAGEMENT SYSTEM =====");
            System.out.println("1. Deluxe Room");
            System.out.println("2. Suite Room");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            if (choice == 1 || choice == 2) {
                sc.nextLine(); // consume newline
                System.out.print("Enter Hotel Name: ");
                String hotelName = sc.nextLine();
                System.out.print("Enter Location: ");
                String location = sc.nextLine();
                System.out.print("Enter Room No: ");
                int roomNo = sc.nextInt();
                sc.nextLine();
                System.out.print("Enter Customer Name: ");
                String custName = sc.nextLine();
                System.out.print("Enter No. of Days: ");
                int noOfDays = sc.nextInt();
                System.out.print("Enter Room Rate: ");
                double roomRate = sc.nextDouble();

                Hotel h; // Polymorphism

                if (choice == 1) {
                    h = new DeluxeRoom(hotelName, location, roomNo, custName, noOfDays, roomRate);
                } else {
                    h = new SuiteRoom(hotelName, location, roomNo, custName, noOfDays, roomRate);
                }

                // Demonstrating polymorphism
                h.checkIn();
                h.displayBill();
                h.checkOut();
            } else if (choice == 3) {
                System.out.println("Exiting the system. Thank you!");
            } else {
                System.out.println("Invalid choice. Please try again.");
            }

        } while (choice != 3);

        sc.close();
    }
}


