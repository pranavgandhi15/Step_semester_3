package Step_semester_3.src.main.java.week7;

public class pg10 {

    static abstract class LibraryItem {
        private static int counter = 1000;
        private final String itemId;

        LibraryItem() {
            itemId = "LI-" + (++counter);
        }

        String getItemId() {
            return itemId;
        }

        public abstract int getLoanPeriodDays();
    }

    interface Renewable {
        String renew();
    }

    interface Reservable {
        String reserve();
    }

    static class Textbook extends LibraryItem
            implements Renewable, Reservable {

        private String title;

        Textbook(String title) {
            this.title = title;
        }

        public int getLoanPeriodDays() {
            return 14;
        }

        public String renew() {
            return title + " renewed";
        }

        public String reserve() {
            return title + " reserved";
        }
    }

    static class Magazine extends LibraryItem
            implements Renewable {

        private String title;

        Magazine(String title) {
            this.title = title;
        }

        public int getLoanPeriodDays() {
            return 7;
        }

        public String renew() {
            return title + " renewed";
        }
    }

    static class DigitalPass implements Renewable {
        private String resourceName;

        DigitalPass(String resourceName) {
            this.resourceName = resourceName;
        }

        public String renew() {
            return resourceName + " renewed";
        }
    }

    static void processCheckouts(LibraryItem[] items) {
        for (LibraryItem item : items) {
            System.out.println(
                    item.getLoanPeriodDays());
        }
    }

    static String reserveIfSupported(Object o) {
        if (o instanceof Reservable) {
            return ((Reservable) o).reserve();
        }
        return "Reservation not supported";
    }

    public static void main(String[] args) {
        Textbook t =
                new Textbook("Java Fundamentals");

        System.out.println(t.getLoanPeriodDays());
        System.out.println(t.renew());
        System.out.println(t.reserve());

        Magazine m =
                new Magazine("Tech Monthly");

        System.out.println(
                reserveIfSupported(m));

        DigitalPass d =
                new DigitalPass("E-Journal Access");

        System.out.println(
                reserveIfSupported(d));

        LibraryItem ref = t;

        System.out.println(
                reserveIfSupported(ref));

        processCheckouts(
                new LibraryItem[]{t, m});
    }
}
