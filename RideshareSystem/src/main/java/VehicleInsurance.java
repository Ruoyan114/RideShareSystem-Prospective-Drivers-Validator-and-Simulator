import java.util.Date;
import java.util.List;
import java.util.Objects;

public class VehicleInsurance {
    private Name officialOwner;
    private List<Name> insuredDrivers;
    private Date expirationDate;
    private Vehicle vehicle;

    /**
     * Constructs a new VehicleInsurance instance.
     * @param officialOwner The official owner of the vehicle.
     * @param insuredDrivers The list of drivers covered by the insurance.
     * @param expirationDate The expiration date of the insurance.
     * @param vehicle the vehicle associated with the insurance
     */
    public VehicleInsurance(Name officialOwner, List<Name> insuredDrivers, Date expirationDate, Vehicle vehicle) {
        this.officialOwner = officialOwner;
        this.insuredDrivers = insuredDrivers;
        this.expirationDate = expirationDate;
        this.vehicle = vehicle;
    }


    /**
     * Gets the official owner of the vehicle.
     * @return The official owner's name.
     */
    public Name getOfficialOwner() {
        return officialOwner;
    }

    /**
     * Gets the list of drivers covered by the specific insurance.
     * @return A list of names of the insured drivers.
     */
    public List<Name> getInsuredDrivers() {
        return insuredDrivers;
    }

    /**
     * Gets the expiration date of the insurance policy.
     * @return The expiration date.
     */
    public Date getExpirationDate() {
        return expirationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VehicleInsurance insurance = (VehicleInsurance) o;
        return Objects.equals(officialOwner, insurance.officialOwner) && Objects.equals(insuredDrivers, insurance.insuredDrivers) && Objects.equals(expirationDate, insurance.expirationDate) && Objects.equals(vehicle, insurance.vehicle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(officialOwner, insuredDrivers, expirationDate, vehicle);
    }
}
