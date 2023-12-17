import java.util.Date;
import java.util.Objects;

/**
 * Represents information about a prospective driver's driver license.
 */
public class DriverLicense {
    private String licenseNumber;
    private Name name;
    private String address;
    private Date birthDate;
    private String countryOfIssuance;
    private String stateOfIssuance;
    private Date issuedDate;
    private Date expirationDate;

    /**
     * Constructor for DriverLicense.
     * @param licenseNumber The unique license number.
     * @param name The name on the license.
     * @param address The address on the license.
     * @param birthDate The birthdate on the license.
     * @param countryOfIssuance The country of issuance.
     * @param stateOfIssuance The state of issuance.
     * @param issuedDate The date of issuance.
     * @param expirationDate The expiration date.
     */
    public DriverLicense(String licenseNumber, Name name, String address, Date birthDate, String countryOfIssuance, String stateOfIssuance, Date issuedDate, Date expirationDate) {
        this.licenseNumber = licenseNumber;
        this.name = name;
        this.address = address;
        this.birthDate = birthDate;
        this.countryOfIssuance = countryOfIssuance;
        this.stateOfIssuance = stateOfIssuance;
        this.issuedDate = issuedDate;
        this.expirationDate = expirationDate;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public Name getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public String getCountryOfIssuance() {
        return countryOfIssuance;
    }

    public String getStateOfIssuance() {
        return stateOfIssuance;
    }

    public Date getIssuedDate() {
        return issuedDate;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DriverLicense that = (DriverLicense) o;
        return Objects.equals(licenseNumber, that.licenseNumber) && Objects.equals(name, that.name) && Objects.equals(address, that.address) && Objects.equals(birthDate, that.birthDate) && Objects.equals(countryOfIssuance, that.countryOfIssuance) && Objects.equals(stateOfIssuance, that.stateOfIssuance) && Objects.equals(issuedDate, that.issuedDate) && Objects.equals(expirationDate, that.expirationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(licenseNumber, name, address, birthDate, countryOfIssuance, stateOfIssuance, issuedDate, expirationDate);
    }
}
