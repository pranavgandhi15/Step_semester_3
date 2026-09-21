package Step_semester_3.src.main.java.week7;

public class pg5 {

    static abstract class HomeDevice {
        private static int counter = 1000;
        private final String serialNumber;

        HomeDevice() {
            serialNumber = "HD-" + (++counter);
        }

        String getSerialNumber() {
            return serialNumber;
        }

        public abstract String activate();
    }

    interface RemoteControllable {
        String connect(String appId);
    }

    interface EnergyTrackable {
        double getConsumptionWatts();
    }

    static class WashingMachine extends HomeDevice
            implements RemoteControllable, EnergyTrackable {

        private double consumptionWatts;

        WashingMachine(double consumptionWatts) {
            this.consumptionWatts = consumptionWatts;
        }

        public String activate() {
            return "Washing machine " + getSerialNumber()
                    + " started a cycle";
        }

        public String connect(String appId) {
            return getSerialNumber() + " connected to " + appId;
        }

        public double getConsumptionWatts() {
            return consumptionWatts;
        }
    }

    static class Refrigerator extends HomeDevice
            implements EnergyTrackable {

        private double consumptionWatts;

        Refrigerator(double consumptionWatts) {
            this.consumptionWatts = consumptionWatts;
        }

        public String activate() {
            return "Refrigerator " + getSerialNumber() + " activated";
        }

        public double getConsumptionWatts() {
            return consumptionWatts;
        }
    }

    static class MobileApp implements RemoteControllable {
        private String appName;

        MobileApp(String appName) {
            this.appName = appName;
        }

        public String connect(String appId) {
            return appName + " connected to " + appId;
        }
    }

    static void connectAll(RemoteControllable[] items,
                           String appId) {
        for (RemoteControllable item : items) {
            System.out.println(item.connect(appId));
        }
    }

    static double getConsumptionIfTrackable(HomeDevice d) {
        if (d instanceof EnergyTrackable) {
            return ((EnergyTrackable) d).getConsumptionWatts();
        }
        return -1;
    }

    public static void main(String[] args) {
        WashingMachine wm = new WashingMachine(500.0);

        System.out.println(wm.activate());
        System.out.println(wm.connect("HomeConnect"));

        Refrigerator fridge = new Refrigerator(150.0);
        System.out.println(getConsumptionIfTrackable(fridge));

        MobileApp app = new MobileApp("HomeConnect App");
        System.out.println(app.connect("HomeConnect"));

        HomeDevice ref = wm;
        System.out.println(getConsumptionIfTrackable(ref));
    }
}