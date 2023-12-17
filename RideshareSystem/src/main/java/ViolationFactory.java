import java.util.Date;

/**
 * Factory class for creating Violation objects.
 */
public class ViolationFactory {
    private static final ViolationFactory INSTANCE = new ViolationFactory();

    // Private constructor to prevent direct instantiation.
    private ViolationFactory() {}

    /**
     * Gets the singleton instance of the ViolationFactory.
     * @return The singleton instance of the ViolationFactory.
     */
    public static ViolationFactory getInstance() {
        return INSTANCE;
    }

    /**
     * Creates a Violation object
     * @param violationType The type of the violation.
     * @param date The date of the violation.
     * @return A new Violation object.
     */
    public static Violation createViolation(String violationType, Date date) {
        if (violationType == null || date == null) {
            throw new IllegalArgumentException("Violation type and date must be provided.");
        }

        return switch (violationType) {
            case "Distracted driving", "Reckless Driving", "Speeding", "Driving under influence", "Failure to respect traffic signs", "Driving without a valid license and/or insurance" ->
                    new MovingViolation(date, violationType);
            case "Parking Violation", "Paperwork Issues", "Problems with the vehicle" ->
                    new NonMovingViolation(date, violationType);
            default -> throw new IllegalArgumentException("Unknown violation type: " + violationType);
        };
    }

}

