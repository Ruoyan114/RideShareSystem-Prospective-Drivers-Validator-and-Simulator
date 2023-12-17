import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * The Driver class represents a driver in the ride-sharing system
 */
public class Driver {
    private String id;
    private Name name;
    private Date birthDate;
    private DriverLicense driverLicense;
    private List<Vehicle> vehicles;
    private VehicleInsurance insurance;
    private DriverHistory driverHistory;
    private VehicleHistory vehicleHistory;


    /**
     * Sets the driver's full name.
     * @param name The Name object representing the driver's full name.
     */
    public void setName(Name name) {
        this.name = name;
    }


    /**
     * Sets the driver's license information.
     * @param driverLicense The DriverLicense object containing the driver's license details.
     */
    public void setDriverLicense(DriverLicense driverLicense) {
        this.driverLicense = driverLicense;
    }

    /**
     * Sets the list of vehicles associated with the driver
     * @param vehicles the list of vehicles
     */
    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    /**
     * Sets the vehicle's insurance information
     * @param insurance The VehicleInsurance object of the driver's vehicle
     */
    public void setInsurance(VehicleInsurance insurance) {
        this.insurance = insurance;
    }

    /**
     * Sets the driver's history of violations.
     * @param driverHistory The DriverHistory object containing the record of the driver's traffic violations.
     */
    public void setDriverHistory(DriverHistory driverHistory) {
        this.driverHistory = driverHistory;
    }

    /**
     * Sets the vehicle's history of violations.
     * @param vehicleHistory The VehicleHistory object containing the record of the driver's all crashes and traffic violations.
     */
    public void setVehicleHistory(VehicleHistory vehicleHistory) {
        this.vehicleHistory = vehicleHistory;
    }

    /**
     * Constructor for the Driver class.
     * @param name The driver's full name.
     * @param birthDate The driver's birthdate.
     * @param driverLicense The driver's license information.
     * @param vehicles the list of vehicles associated with the driver
     * @param insurance The vehicle's insurance.
     * @param driverHistory The driver's history of violations.
     */
    public Driver(Name name, Date birthDate, DriverLicense driverLicense, List<Vehicle> vehicles, VehicleInsurance insurance, DriverHistory driverHistory, VehicleHistory vehicleHistory) {
        this.name = name;
        this.birthDate = birthDate;
        this.driverLicense = driverLicense;
        this.vehicles = vehicles;
        this.insurance = insurance;
        this.driverHistory = driverHistory;
        this.vehicleHistory = vehicleHistory;
    }

    /**
     *  Constructor for the Driver class.
     * @param id the unique id of the driver
     * @param name The driver's full name.
     * @param birthDate The driver's birthdate.
     */
    public Driver(String id,Name name, Date birthDate) {
        this.name = name;
        this.birthDate = birthDate;
        this.id = id;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets the driver's full name.
     * @return The Name object representing the driver's full name.
     */
    public Name getName() {
        return name;
    }

    /**
     * Gets the driver's birthdate.
     * @return A Date object representing the driver's birthdate.
     */
    public Date getBirthDate() {
        return birthDate;
    }

    /**
     * Gets the driver's license information.
     * @return A DriverLicense object containing the driver's license details.
     */
    public DriverLicense getDriverLicense() {
        return driverLicense;
    }

    /**
     * Gets a list of vehicles associated with the driver
     * @return a list of vehicles
     */
    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    /**
     * Gets the vehicle's insurance information
     * @return A VehicleInsurance object of the driver's vehicle
     */
    public VehicleInsurance getInsurance() {
        return insurance;
    }

    /**
     * Gets the driver's history of violations.
     * @return A DriverHistory object containing the record of the driver's traffic violations.
     */
    public DriverHistory getDriverHistory() {
        return driverHistory;
    }

    /**
     * Gets the vehicle's history of violations.
     * @return A VehicleHistory object containing the record of the driver's all crashes and traffic violations.
     */
    public VehicleHistory getVehicleHistory() {
        return vehicleHistory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Driver driver = (Driver) o;
        return Objects.equals(name, driver.name) && Objects.equals(birthDate, driver.birthDate) && Objects.equals(driverLicense, driver.driverLicense) && Objects.equals(vehicles, driver.vehicles) && Objects.equals(insurance, driver.insurance) && Objects.equals(driverHistory, driver.driverHistory) && Objects.equals(vehicleHistory, driver.vehicleHistory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, birthDate, driverLicense, vehicles, insurance, driverHistory, vehicleHistory);
    }

}
