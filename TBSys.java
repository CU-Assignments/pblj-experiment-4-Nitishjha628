import java.util.*;
class Seat {
    private boolean[] seats; 
    private int totalSeats;

    public Seat(int totalSeats) {
        this.totalSeats = totalSeats;
        this.seats = new boolean[totalSeats + 1]; 
    }
    public synchronized boolean bookSeat(int seatNumber, String userType) {
        if (seatNumber < 1 || seatNumber > totalSeats) {
            System.out.println("Invalid seat number: " + seatNumber);
            return false;
        }

        if (seats[seatNumber]) {
            System.out.println("Seat " + seatNumber + " is already booked.");
            return false;
        }

        seats[seatNumber] = true;
        System.out.println(userType + " booked seat: " + seatNumber);
        return true;
    }
}

class BookingThread extends Thread {
    private Seat seat;
    private int seatNumber;
    private String userType;

    public BookingThread(Seat seat, int seatNumber, String userType) {
        this.seat = seat;
        this.seatNumber = seatNumber;
        this.userType = userType;
    }

    public void run() {
        seat.bookSeat(seatNumber, userType);
    }
}

public class TBSys {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the total number of seats: ");
        int totalSeats = scanner.nextInt();
        Seat seat = new Seat(totalSeats);

        System.out.print("Enter the number of bookings: ");
        int numBookings = scanner.nextInt();
        scanner.nextLine(); 
        BookingThread[] bookingThreads = new BookingThread[numBookings];

        for (int i = 0; i < numBookings; i++) {
            System.out.println("\nBooking " + (i + 1) + ":");
            System.out.print("Enter seat number: ");
            int seatNumber = scanner.nextInt();
            scanner.nextLine(); 

            System.out.print("Enter user type (VIP/Regular): ");
            String userType = scanner.nextLine();

            bookingThreads[i] = new BookingThread(seat, seatNumber, userType);

            if (userType.equalsIgnoreCase("VIP")) {
                bookingThreads[i].setPriority(Thread.MAX_PRIORITY);
            } else {
                bookingThreads[i].setPriority(Thread.MIN_PRIORITY);
            }
        }
        for (BookingThread thread : bookingThreads) {
            thread.start();
        }
        for (BookingThread thread : bookingThreads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                System.out.println("Thread interrupted: " + e.getMessage());
            }
        }

        System.out.println("\nBooking process completed.");
        scanner.close();
    }
}