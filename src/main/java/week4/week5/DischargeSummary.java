package Step_semester_3.src.main.java.week4.week5;

class DischargeSummary {

    private final String patientId;
    private final String[] medicationCodes;

    public DischargeSummary(String patientId,
                            String[] medicationCodes) {

        if (medicationCodes == null) {
            throw new IllegalArgumentException(
                "Medication codes cannot be null");
        }

        for (String code : medicationCodes) {

            if (code == null ||
                !code.matches("MED-[A-Z]")) {

                throw new IllegalArgumentException(
                    "Invalid medication code");
            }
        }

        this.patientId = patientId;

        // Defensive copy
        this.medicationCodes = medicationCodes.clone();
    }

    public String getPatientId() {
        return patientId;
    }

    public String[] getMedicationCodes() {

        // Defensive copy
        return medicationCodes.clone();
    }

    public DischargeSummary withCorrectedMedication(
            int index, String newCode) {

        if (index < 0 ||
            index >= medicationCodes.length) {

            throw new IndexOutOfBoundsException();
        }

        if (newCode == null ||
            !newCode.matches("MED-[A-Z]")) {

            throw new IllegalArgumentException(
                "Invalid medication code");
        }

        String[] corrected = medicationCodes.clone();

        corrected[index] = newCode;

        return new DischargeSummary(
            patientId,
            corrected
        );
    }

    public static String processNightlyBatch(
            DischargeSummary[] summaries) {

        int processed = 0;
        int nullSkipped = 0;
        int criticalCare = 0;
        int routine = 0;

        if (summaries != null) {

            for (DischargeSummary summary : summaries) {

                if (summary == null) {
                    nullSkipped++;
                    continue;
                }

                processed++;

                if (summary instanceof
                    CriticalCareDischargeSummary) {

                    criticalCare++;
                } else {
                    routine++;
                }
            }
        }

        return processed + " processed | "
             + nullSkipped + " null skipped | "
             + criticalCare + " critical-care | "
             + routine + " routine";
    }

    // Static block
    static {
        System.out.println(
            "DischargeSummary system initialized."
        );
    }
}
