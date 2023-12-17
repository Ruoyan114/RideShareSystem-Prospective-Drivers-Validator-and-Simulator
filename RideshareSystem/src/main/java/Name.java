import java.util.Objects;

/**
 * The Name class represents a driver's name, including first name and last name
 */
public class Name {
    private String firstName;
    private String lastName;

    /**
     * Constructor to create a new name instance
     * @param firstName the first name of the driver
     * @param lastName the last name of the driver
     */
    public Name(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * Retrieves the first name
     * @return a string representing the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Retrieves the last name
     * @return a string representing the last name
     */
    public String getLastName() {
        return lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Name name = (Name) o;
        return Objects.equals(firstName, name.firstName) && Objects.equals(lastName, name.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }

    @Override
    public String toString() {
        return "Name{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}