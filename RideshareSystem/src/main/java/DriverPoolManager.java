import java.util.*;
import java.util.stream.Collectors;

/**
 * Manages a pool of drivers and their associated vehicles in a ride-sharing system.
 */
public class DriverPoolManager {
    private Map<String, Driver> driverPool; // maps driver's license numbers to 'Driver' objects
    private Map<String, Vehicle> vehiclePool; // maps vehicle IDs to 'Vehicle' objects
    private Map<String, String> vehicleUsage; // maps vehicle IDs to the license numbers of the drivers currently using them

    public DriverPoolManager() {
        driverPool = new HashMap<>();
        vehiclePool = new HashMap<>();
        vehicleUsage = new HashMap<>();
    }

    /**
     * Adds a driver to the pool, along with their associated vehicles.
     * Ensures that each driver is uniquely identified by their driver's license number.
     * @param driver The driver to add to the pool.
     */
    public void addDriver(Driver driver) {
        if (driver == null) {
            System.err.println("Error: Attempted to add a non existing driver.");
            return;
        }

        String driverId = driver.getDriverLicense().getLicenseNumber();
        if (driverPool.containsKey(driverId)) {
            System.err.println("Error: Driver with license number " + driverId + " already exists.");
            return;
        }

        driverPool.put(driverId, driver);

        for (Vehicle vehicle : driver.getVehicles()) {
            String vehicleId = vehicle.getVehicleId();
            if (!vehiclePool.containsKey(vehicleId)) {
                vehiclePool.put(vehicleId, vehicle);
            }
            // Check if the vehicle is currently in use by another driver
            if (!vehicleUsage.containsKey(vehicleId)) {
                vehicleUsage.put(vehicleId, driverId); // Assign the vehicle to the new driver
            } else {
                System.out.println("Info: Vehicle " + vehicleId + " is currently used by another driver.");
            }
        }
    }

    /**
     * Removes a driver from the pool, along with releasing their associated vehicles.
     * @param driverId The ID of the driver to remove.
     */
    public void removeDriver(String driverId) {
        if (!driverPool.containsKey(driverId)) {
            System.err.println("Error: No driver found with license number " + driverId + ".");
            return;
        }

        Driver driver = driverPool.get(driverId);
        for (Vehicle vehicle : driver.getVehicles()) {
            vehicleUsage.remove(vehicle.getVehicleId()); // Release the vehicle
        }
        driverPool.remove(driverId);
    }

    /**
     * Provides information about drivers with a specific last name.
     * Displays detailed information of each matching driver.
     * @param lastName The last name to search for in the driver pool.
     */
    public void provideDriverInfo(String lastName) {
        List<Driver> matchingDrivers = driverPool.values().stream()
                .filter(driver -> driver.getName().getLastName().equalsIgnoreCase(lastName))
                .sorted(Comparator.comparing(driver -> driver.getName().getFirstName()))
                .collect(Collectors.toList());

        if (matchingDrivers.isEmpty()) {
            System.out.println("No registered driver found with last name: " + lastName);
            return;
        }

        for (Driver driver : matchingDrivers) {
            displayDriverInformation(driver);
        }
    }

    /**
     * Displays detailed information about a driver.
     * @param driver The driver whose information is to be displayed.
     */
    private void displayDriverInformation(Driver driver) {
        System.out.println(driver.getName().getLastName() + "," + driver.getName().getFirstName());
        for (Vehicle vehicle : driver.getVehicles()) {
            System.out.println("    Vehicle: " + vehicle.getDescription());
        }
        if (!driver.getDriverHistory().getViolations().isEmpty()) {
            System.out.println("    Driving violations:");
            for (Violation violation : driver.getDriverHistory().getViolations()) {
                System.out.println("        " + violation);
            }
        } else {
            System.out.println("No driving violations");
        }
        System.out.println();
    }
}
