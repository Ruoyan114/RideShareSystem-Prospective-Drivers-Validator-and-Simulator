import java.util.Date;
import java.util.Objects;

/**
 * Represents a moving traffic violation.
 */
public class MovingViolation extends Violation {
    private String violationType; // type of moving violation

    /**
     * Constructs a new MovingViolation instance.
     * @param violationType The type of moving violation.
     * @param date The date of the violation.
     */
    public MovingViolation(Date date, String violationType) {
        super(date);
        this.violationType = violationType;
    }

    /**
     * Gets the type of the moving violation.
     * @return The type of the moving violation.
     */
    public String getViolationType() {
        return violationType;
    }

    @Override
    public String toString() {
        return violationType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        MovingViolation that = (MovingViolation) o;
        return Objects.equals(violationType, that.violationType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), violationType);
    }
}
