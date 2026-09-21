package Step_semester_3.src.main.java.week4;

public import java.util.HashSet;

class w41 {

    private String passengerName;
    private String destination;
    private boolean checkedIn;

    public TicketValidator(String passengerName, String destination) {

        if (passengerName == null || passengerName.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid passenger name");
        }

        if (destination == null || destination.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid destination");
        }

        // Name should contain only letters and spaces
        if (!passengerName.matches("[a-zA-Z ]+")) {
            throw new IllegalArgumentException("Invalid passenger name");
        }

        this.passengerName = passengerName;
        this.destination = destination;
        this.checkedIn = false;
    }

    public void markCheckedIn() {

        if (checkedIn) {
            throw new IllegalStateException("Already checked in");
        }

        checkedIn = true;
    }

    public static void processBatch(String[][] rawBookings) {

        HashSet<String> accepted = new HashSet<>();

        int valid = 0;
        int rejected = 0;
        int duplicate = 0;

        for (String[] booking : rawBookings) {

            try {
                if (booking == null || booking.length < 2) {
                    rejected++;
                    continue;
                }

                String name = booking[0];
                String destination = booking[1];

                String key = name + "|" + destination;

                if (accepted.contains(key)) {
                    duplicate++;
                    continue;
                }

                new TicketValidator(name, destination);

                accepted.add(key);
                valid++;

            } catch (IllegalArgumentException e) {
                rejected++;
            }
        }

        System.out.println("Valid: " + valid +
                " | Rejected: " + rejected +
                " | Duplicates skipped: " + duplicate);
    }

    public static void main(String[] args) {

        String[][] bookings = {
                {"Divya", "Chennai"},
                {"", "Bangalore"},
                {"Ravi123", "Pune"},
                {"Divya", "Chennai"},
                {"   ", "  "}
        };

        processBatch(bookings);
    }
} {
    
}
