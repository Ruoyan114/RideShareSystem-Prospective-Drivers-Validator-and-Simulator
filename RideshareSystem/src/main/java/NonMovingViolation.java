import java.util.Date;
import java.util.Objects;

/**
 * Represents a non-moving traffic violation.
 */
public class NonMovingViolation extends Violation {
    public String violationType; // type of the non-moving violation

    /**
     * Constructs a new NonMovingViolation instance.
     * @param violationType The type of non-moving violation.
     * @param date The date of the violation.
     */
    public NonMovingViolation(Date date, String violationType) {
        super(date);
        this.violationType = violationType;
    }

    /**
     * Gets the type of the non-moving violation.
     * @return The type of the non-moving violation.
     */
    public String getViolationType() {
        return violationType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        NonMovingViolation that = (NonMovingViolation) o;
        return Objects.equals(violationType, that.violationType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), violationType);
    }

}
