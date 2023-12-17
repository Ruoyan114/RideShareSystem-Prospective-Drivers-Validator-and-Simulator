import java.util.Date;

/**
 * Factory class for creating crash.csv objects.
 */
public class CrashFactory {
    private static final CrashFactory INSTANCE = new CrashFactory();

    // Private constructor to prevent direct instantiation.
    private CrashFactory() {
    }

    /**
     * Gets the singleton instance of the CrashFactory.
     * @return The singleton instance of the CrashFactory.
     */
    public static CrashFactory getInstance() {
        return INSTANCE;
    }

    /**
     * Creates a crash.csv object
     * @param crashType The type of the crash.
     * @param date The date of the crash.
     * @param offendingDriver The name of the offending driver involved in the crash.
     * @return A new crash.csv object.
     */
    public Crash createCrash(String crashType, Date date, Name offendingDriver) throws Exception {
        if (crashType == null || date == null || offendingDriver == null) {
            throw new IllegalArgumentException("crash.csv type, date, and offending driver must be provided.");
        }

        return switch (crashType) {
            case "Fender-bender", "crash.csv without bodily injuries", "crash.csv involving bodily injuries" ->
                    new Crash(date, crashType, offendingDriver);
            default -> throw new IllegalArgumentException("Unknown crash type: " + crashType);
        };
    }

}
