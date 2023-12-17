import java.util.ArrayList;
import java.util.List;

/**
 * Represents the history of a vehicle, including all recorded crashes and traffic violations.
 */
public class VehicleHistory {
    private List<Crash> crashes;
    private List<Violation> violations;

    /**
     * Constructor for VehicleHistory.
     */
    public VehicleHistory(List<Crash> crashes, List<Violation> violations) {
        this.crashes = new ArrayList<>();
        this.violations = new ArrayList<>();
    }

    /**
     * Adds a crash to the vehicle's history.
     * @param crash The crash to add.
     */
    public void addCrash(Crash crash) {
        if (crash != null) {
            crashes.add(crash);
        }
    }

    /**
     * Removes a crash from the vehicle's history.
     * @param crash The crash to remove.
     */
    public void removeCrash(Crash crash) {
        crashes.remove(crash);
    }

    /**
     * Gets the list of crashes in the vehicle's history.
     * @return A list of crashes.
     */
    public List<Crash> getCrashes() {
        return new ArrayList<>(crashes);
    }

    /**
     * Adds a traffic violation to the vehicle's history.
     * @param violation The traffic violation to add.
     */
    public void addViolation(Violation violation) {
        if (violation != null) {
            violations.add(violation);
        }
    }

    /**
     * Removes a traffic violation from the vehicle's history.
     * @param violation The traffic violation to remove.
     */
    public void removeViolation(Violation violation) {
        violations.remove(violation);
    }

    /**
     * Gets the list of traffic violations in the vehicle's history.
     * @return A list of traffic violations.
     */
    public List<Violation> getViolations() {
        return new ArrayList<>(violations); 
    }


}
