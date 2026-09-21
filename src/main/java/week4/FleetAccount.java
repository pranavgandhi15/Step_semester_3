package Step_semester_3.src.main.java.week4;

class FleetAccount {

    protected String bookingId;
    protected double ticketFare;

    static {
        System.out.println("Fleet reconciliation system initialized.");
    }

    public FleetAccount(String bookingId, double ticketFare) {

        if (ticketFare < 0) {
            throw new IllegalArgumentException("Fare cannot be negative");
        }

        this.bookingId = bookingId;
        this.ticketFare = ticketFare;
    }

    public FleetAccount(String bookingId) {
        this(bookingId, 0.0);
    }

    public final double calculatePenalty(int minutesLate) {

        if (minutesLate < 0) {
            throw new IllegalArgumentException("Invalid minutes");
        }

        if (minutesLate == 0) {
            return 0;
        }

        return ticketFare * minutesLate * 0.01;
    }

    public void processAccount(
            FleetAccount account,
            double amount,
            int minutesLate) {

        double penalty = account.calculatePenalty(minutesLate);

        System.out.println(
                "Booking: " + account.bookingId +
                " | Amount: " + amount +
                " | Penalty: " + penalty
        );
    }

    public static void processBatch(
            FleetAccount[] accounts,
            double[] amounts,
            int[] minutesLateArray) {

        int processed = 0;
        int nullSkipped = 0;
        int sleeper = 0;
        int regular = 0;

        double grandTotal = 0;

        int length = Math.min(
                accounts.length,
                Math.min(amounts.length, minutesLateArray.length)
        );

        for (int i = 0; i < length; i++) {

            FleetAccount account = accounts[i];

            if (account == null) {
                nullSkipped++;
                continue;
            }

            double penalty =
                    account.calculatePenalty(minutesLateArray[i]);

            grandTotal += penalty;
            processed++;

            if (account instanceof SleeperAccount) {
                sleeper++;
            } else {
                regular++;
            }
        }

        System.out.println();
        System.out.println(processed + " processed");
        System.out.println(nullSkipped + " null skipped");
        System.out.println(sleeper + " sleeper");
        System.out.println(regular + " regular");
        System.out.println(
                "Grand total penalties = " + grandTotal
        );
    }

    public static void main(String[] args) {

        FleetAccount[] accounts = {
                new SleeperAccount("BK001", 2000),
                null,
                new FleetAccount("BK002", 1200)
        };

        double[] amounts = {
                1200,
                900,
                700
        };

        int[] minutesLate = {
                10,
                5,
                0
        };

        processBatch(accounts, amounts, minutesLate);
    }
}


class SleeperAccount extends FleetAccount {

    public SleeperAccount(String bookingId, double ticketFare) {
        super(bookingId, ticketFare);
    }

    @Override
    public final double calculatePenalty(int minutesLate) {

        if (minutesLate < 0) {
            throw new IllegalArgumentException("Invalid minutes");
        }

        if (minutesLate == 0) {
            return 0;
        }

        // Sleeper gets a different settlement rate
        return ticketFare * minutesLate * 0.005;
    }
}