package Step_semester_3.src.main.java.week6;

class RaceEntry {

    protected String bibNumber;
    protected double entryFee;
    protected double amountPaid;

    // Static counter
    private static int bibCounter = 0;

    // Cannot be changed after construction
    private final String entryCode;

    public RaceEntry(String bibNumber, double entryFee) {

        if (bibNumber == null ||
            bibNumber.trim().isEmpty() ||
            bibNumber.length() < 4) {

            throw new IllegalArgumentException();
        }

        this.bibNumber = bibNumber;
        this.entryFee = entryFee;

        bibCounter++;

        entryCode = "ENTRY" + bibCounter;
    }


    // Normal payment
    public void pay(double amount) {

        amountPaid += amount;
    }


    // Overloaded payment
    public void pay(double amount, String mode) {

        pay(amount);

        System.out.println(
            "Paying via " + mode
        );
    }


    // Discount validation
    public static boolean isValidDiscountCode(
            String code) {

        if (code == null ||
            code.length() != 5) {

            return false;
        }

        // First character must be M
        if (code.charAt(0) != 'M') {
            return false;
        }

        // Next three must be digits
        if (!Character.isDigit(code.charAt(1)) ||
            !Character.isDigit(code.charAt(2)) ||
            !Character.isDigit(code.charAt(3))) {

            return false;
        }

        // Last must be uppercase
        if (!Character.isUpperCase(
                code.charAt(4))) {

            return false;
        }

        return true;
    }


    public static int getBibCounter() {

        return bibCounter;
    }


    // Night settlement
    public static String settleNight(
            RaceEntry[] entries) {

        int processed = 0;
        int nullSkipped = 0;
        int relay = 0;
        int individual = 0;

        for (RaceEntry entry : entries) {

            if (entry == null) {
                nullSkipped++;
                continue;
            }

            processed++;

            if (entry instanceof RelayTeamEntry) {
                relay++;
            }
            else {
                individual++;
            }
        }

        return processed +
               " processed | " +
               nullSkipped +
               " null skipped | " +
               relay +
               " relay | " +
               individual +
               " individual";
    }
}


class RelayTeamEntry extends RaceEntry {

    private int teamSize;

    public RelayTeamEntry(String bibNumber,
                          double entryFee,
                          int teamSize) {

        super(bibNumber, entryFee);
        this.teamSize = teamSize;
    }
}


public class Main5 {

    public static void main(String[] args) {

        System.out.println(
            RaceEntry.isValidDiscountCode("M123A")
        );

        System.out.println(
            RaceEntry.isValidDiscountCode("M12A")
        );

        System.out.println(
            RaceEntry.isValidDiscountCode("X123A")
        );


        RaceEntry r =
            new RaceEntry("BIB1001", 100);

        r.pay(10, "UPI");


        RelayTeamEntry relay =
            new RelayTeamEntry(
                "BIB2001",
                300,
                4
            );


        System.out.println(
            RaceEntry.settleNight(
                new RaceEntry[]{
                    r,
                    null,
                    relay
                }
            )
        );


        System.out.println(
            RaceEntry.getBibCounter()
        );
    }
}
