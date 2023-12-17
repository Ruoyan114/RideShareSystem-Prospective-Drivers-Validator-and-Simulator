import java.util.Date;
import java.util.Objects;

/**
 * Base class for traffic violations.
 */
public abstract class Violation {

    private Date date;

    /**
     * Constructs a new Violation instance.
     * @param date The date of the violation.
     */
    public Violation(Date date) {
        this.date = date;
    }

    /**
     * Gets the date of the violation.
     * @return The date of the violation.
     */
    public Date getDate() {
        return date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Violation violation = (Violation) o;
        return Objects.equals(date, violation.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date);
    }
}
