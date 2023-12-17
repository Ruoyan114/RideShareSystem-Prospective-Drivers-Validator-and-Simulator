import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * DriverHistory class represents the driving history of a driver, including all recorded traffic violations.
 */
public class DriverHistory {
    private List<Violation> violations;

    /**
     * Constructor for DriverHistory
     * @param violations initialize the list of violations
     */
    public DriverHistory(List<Violation> violations) {
        this.violations = violations;
    }

    /**
     * Returns the list of traffic violations in the driver's history.
     * @return A list of traffic violations.
     */
    public List<Violation> getViolations() {
        return violations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DriverHistory history = (DriverHistory) o;
        return Objects.equals(violations, history.violations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(violations);
    }
}
