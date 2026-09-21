package Step_semester_3.src.main.java.week4.week5;

class PatientProfile {

    private String patientId;
    private String name;
    private boolean discharged;
    private String lockerPin;

    // No-argument constructor
    public PatientProfile() {
        this(null, null);
    }

    // Name-only constructor
    public PatientProfile(String name) {
        this(null, name);
    }

    // Main constructor
    public PatientProfile(String patientId, String name) {
        this.patientId = patientId;
        this.name = name;
        this.discharged = false;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String id) {

        // Write once
        if (this.patientId == null) {
            this.patientId = id;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDischarged() {
        return discharged;
    }

    public void setDischarged(boolean discharged) {
        this.discharged = discharged;
    }

    // Write-only property
    public void setLockerPin(String pin) {

        if (pin != null &&
            pin.matches("\\d{4,6}")) {

            // One-way transformation
            this.lockerPin = Integer.toString(pin.hashCode());
        }
    }
}
