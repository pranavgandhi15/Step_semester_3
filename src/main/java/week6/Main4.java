package Step_semester_3.src.main.java.week6;

class RaceEntry {

    protected String bibNumber;
    protected double entryFee;
    protected double amountPaid;

    public RaceEntry(String bibNumber, double entryFee) {

        if (bibNumber == null ||
            bibNumber.trim().isEmpty() ||
            bibNumber.length() < 4) {

            throw new IllegalArgumentException();
        }

        this.bibNumber = bibNumber;
        this.entryFee = entryFee;
    }

    public void pay(double amount) {
        amountPaid += amount;
    }

    public double getBalanceDue() {
        return entryFee - amountPaid;
    }

    public String announce() {

        return "Race Entry | Bib: " + bibNumber +
               " | Balance: " + getBalanceDue();
    }
}


class RunnerEntry extends RaceEntry {

    private String category;

    public RunnerEntry(String bibNumber,
                       double entryFee,
                       String category) {

        super(bibNumber, entryFee);
        this.category = category;
    }

    @Override
    public String announce() {

        return "Runner Entry | Bib: " + bibNumber +
               " | Category: " + category +
               " | Balance: " + getBalanceDue();
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

    public int getTeamSize() {
        return teamSize;
    }

    @Override
    public String announce() {

        return "Relay Team | Bib: " + bibNumber +
               " | Team Size: " + teamSize +
               " | Balance: " + getBalanceDue();
    }
}


public class Main4 {

    public static String announceAll(RaceEntry[] entries) {

        StringBuilder result =
            new StringBuilder();

        for (RaceEntry entry : entries) {

            // Polymorphism
            result.append(entry.announce());

            // Safe downcasting
            if (entry instanceof RelayTeamEntry) {

                RelayTeamEntry relay =
                    (RelayTeamEntry) entry;

                result.append(
                    " [Team size via downcast: "
                );

                result.append(
                    relay.getTeamSize()
                );

                result.append("]");
            }

            result.append(" | ");
        }

        return result.toString();
    }


    public static void main(String[] args) {

        RunnerEntry runner =
            new RunnerEntry(
                "BIB2001",
                80,
                "Open 10K"
            );

        RelayTeamEntry relay =
            new RelayTeamEntry(
                "BIB4001",
                300,
                4
            );

        RaceEntry[] fleet = {
            runner,
            relay
        };

        System.out.println(
            announceAll(fleet)
        );
    }
}