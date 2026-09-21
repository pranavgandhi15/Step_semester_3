package Step_semester_3.src.main.java.week7;

public class pg7 {

    interface Alertable {
        String sendAlert(String message);
    }

    static class SecuritySensor {
        protected String zoneName;

        SecuritySensor(String zoneName) {
            this.zoneName = zoneName;
        }

        String getZoneName() {
            return zoneName;
        }
    }

    static class MotionSensor extends SecuritySensor
            implements Alertable {

        MotionSensor(String zoneName) {
            super(zoneName);
        }

        public String sendAlert(String message) {
            return "[" + zoneName + "] " + message;
        }
    }

    static class DualZoneMotionSensor extends MotionSensor {
        private String secondZoneName;

        DualZoneMotionSensor(String zoneName,
                             String secondZoneName) {
            super(zoneName);
            this.secondZoneName = secondZoneName;
        }

        @Override
        public String sendAlert(String message) {
            return super.sendAlert(message)
                    + " [also covering " + secondZoneName + "]";
        }
    }

    static class SmokeDetector implements Alertable {
        private String deviceId;

        SmokeDetector(String deviceId) {
            this.deviceId = deviceId;
        }

        public String sendAlert(String message) {
            return "[" + deviceId + "] " + message;
        }
    }

    static void broadcastAll(Alertable[] devices,
                             String message) {
        for (Alertable device : devices) {
            System.out.println(device.sendAlert(message));
        }
    }

    static String getZoneIfMotionSensor(Alertable a) {
        if (a instanceof MotionSensor) {
            return ((MotionSensor) a).getZoneName();
        }
        return "Not a motion sensor";
    }

    public static void main(String[] args) {
        MotionSensor m =
                new MotionSensor("Living Room");

        System.out.println(
                m.sendAlert("Motion detected"));

        DualZoneMotionSensor d =
                new DualZoneMotionSensor(
                        "Hallway", "Stairwell");

        System.out.println(
                d.sendAlert("Motion detected"));

        SmokeDetector s =
                new SmokeDetector("SD-01");

        System.out.println(
                s.sendAlert("Smoke detected"));

        System.out.println(getZoneIfMotionSensor(m));
        System.out.println(getZoneIfMotionSensor(s));

        broadcastAll(
                new Alertable[]{m, d, s},
                "Emergency");
    }
}
