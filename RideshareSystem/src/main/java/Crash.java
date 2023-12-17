import java.util.Date;
import java.util.Objects;

/**
 * Class representing a vehicle crash.
 */
public class Crash {
    private Date date;
    private String crashType;
    private Name offendingDriver;

    /**
     * Constructs a new crash.csv instance.
     * @param date The date of the crash.
     * @param crashType The type of crash.
     * @param offendingDriver The name of the offending driver involved in the crash.
     */
    public Crash(Date date, String crashType, Name offendingDriver) {
        this.date = date;
        this.crashType = crashType;
        this.offendingDriver = offendingDriver;
    }

    /**
     * Gets the date of the crash.
     * @return The date of the crash.
     */
    public Date getDate() {
        return date;
    }

    /**
     * Gets the type of the crash.
     * @return The type of the crash.
     */
    public String getCrashType() {
        return crashType;
    }

    /**
     * Gets the name of the offending driver.
     * @return The name of the offending driver.
     */
    public Name getOffendingDriver() {
        return offendingDriver;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Crash crash = (Crash) o;
        return Objects.equals(date, crash.date) && Objects.equals(crashType, crash.crashType) && Objects.equals(offendingDriver, crash.offendingDriver);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, crashType, offendingDriver);
    }
}
