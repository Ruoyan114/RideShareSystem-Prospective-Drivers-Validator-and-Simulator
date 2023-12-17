import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * This class will run the simulation. The user input to the simulator will be a file
 * containing information about prospective drivers.
 * the user should be able to interact with the program by searching for potential prospective
 * drivers based on their last name.
 */
public class RideshareDriverValidator {

    public static void main(String[] args) {
        List<Driver> drivers = loadData();
        DriverPoolManager driverPoolManager = new DriverPoolManager();
        for (Driver driver : drivers) {
            RegistrationValidator registrationValidator = new RegistrationValidator();
            boolean isValid = registrationValidator.validate(driver);
            if (isValid) {
                System.out.println("Driver " + driver.getName().getFirstName() + " " + driver.getName().getLastName() + " is valid.");
            } else {
                System.out.println("Driver " + driver.getName().getFirstName() + " " + driver.getName().getLastName() + " is not valid.");
            }
            driverPoolManager.addDriver(driver);
        }

        System.out.println("provide driver info Smith");
        driverPoolManager.provideDriverInfo("Smith");
        System.out.println("provide driver info Doe");
        driverPoolManager.provideDriverInfo("Doe");
        System.out.println("provide driver info NotARealDriver");
        driverPoolManager.provideDriverInfo("NotARealDriver");

    }

    /**
     * Loads the data from the CSV files.
     * @return A list of drivers.
     */
    public static List<Driver> loadData(){
        List<Driver> drivers = loadDriver();
        for (Driver driver : drivers) {
            List<DriverLicense> driverLicenses = loadDriverLicense(driver.getId());
            driver.setDriverLicense(driverLicenses.get(0));
            List<Vehicle> vehicles = loadVehicle(driver.getId());
            driver.setVehicles(vehicles);
            for (Vehicle vehicle : vehicles) {
                List<VehicleInsurance> vehicleInsurances = loadVehicleInsurance(vehicle);
                driver.setInsurance(vehicleInsurances.get(0));
            }
            List<Crash> crashes = loadCrash(driver.getId());
            List<Violation> violations = loadViolation(driver.getId());
            DriverHistory driverHistory = new DriverHistory(violations);
            driver.setDriverHistory(driverHistory);
            VehicleHistory vehicleHistory = new VehicleHistory(crashes,violations);
            driver.setVehicleHistory(vehicleHistory);
        }
        return drivers;
    }


    /**
     * parses a date string into a Date object
     * @param date The date string to parse
     * @return The Date object
     */
    private static Date parseDate(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return sdf.parse(date);
        } catch (Exception e) {
            System.out.println("Error parsing date: " + date);
            return null;
        }
    }

    /**
     * Loads the driver data from the CSV file.
     * @return A list of drivers.
     */
    private static List<Driver> loadDriver() {
        List<Driver> drivers = new ArrayList<>();
        File driverFile = new File("driver.csv");
        List<String> driverLines = readLines(driverFile);
        for (String line : driverLines) {
            String[] parts = line.split(", ");
            String id = parts[0];
            String[] name = parts[1].split(" ");
            String birthDate = parts[2];
            drivers.add(new Driver(id, new Name(name[0],name[1]), parseDate(birthDate)));
        }
        return drivers;
    }

    /**
     * Loads the driver license data from the CSV file.
     * @param driverId The driver ID to load the license for.
     * @return A list of driver licenses.
     */
    private static List<DriverLicense> loadDriverLicense(String driverId) {
        List<DriverLicense> driverLicenses = new ArrayList<>();
        File driverLicenseFile = new File("license.csv");
        List<String> driverLicenseLines = readLines(driverLicenseFile);
        for (String line : driverLicenseLines) {
            String[] parts = line.split(", ");
            // driverId, number, name, address, birthDate, countryOfIssuance, stateOfIssuance, issuedDate, expirationDate
            String driverId1 = parts[0];
            if (!driverId1.equals(driverId)) {
                continue;
            }
            String licenseNumber = parts[1];
            String[] name = parts[2].split(" ");
            String address = parts[3];
            String birthDate = parts[4];
            String countryOfIssuance = parts[5];
            String stateOfIssuance = parts[6];
            String issuedDate = parts[7];
            String expirationDate = parts[8];
            driverLicenses.add(new DriverLicense(licenseNumber, new Name(name[0],name[1]), address, parseDate(birthDate), countryOfIssuance, stateOfIssuance, parseDate(issuedDate), parseDate(expirationDate)));
        }
        return driverLicenses;
    }
    /**
     * Loads the vehicle data from the CSV file.
     * @param driverId The driver ID to load the vehicle for.
     * @return A list of vehicles.
     */
    private static List<Vehicle> loadVehicle(String driverId) {
        List<Vehicle> vehicles = new ArrayList<>();
        File vehicleFile = new File("vehicle.csv");
        List<String> vehicleLines = readLines(vehicleFile);
        for (String line : vehicleLines) {
            String[] parts = line.split(", ");
            // driverId, make, model, color, year, officialOwner, vehicleId
            String driverId1 = parts[0];
            if (!driverId1.equals(driverId)) {
                continue;
            }
            String make = parts[1];
            String model = parts[2];
            String color = parts[3];
            int year = Integer.parseInt(parts[4]);
            String[] officialOwner = parts[5].split(" ");
            String vehicleId = parts[6];
            vehicles.add(new Vehicle(make, model, color, year, new Name(officialOwner[0],officialOwner[1]), vehicleId));
        }
        return vehicles;
    }

    /**
     * Loads the vehicle insurance data from the CSV file.
     * @param vehicle The vehicle to load the insurance for.
     * @return A list of vehicle insurances.
     */
    private static List<VehicleInsurance> loadVehicleInsurance(Vehicle vehicle) {
        List<VehicleInsurance> vehicleInsurances = new ArrayList<>();
        File vehicleInsuranceFile = new File("vehicleInsurance.csv");
        List<String> vehicleInsuranceLines = readLines(vehicleInsuranceFile);
        for (String line : vehicleInsuranceLines) {
            String[] parts = line.split(", ");
            // officialOwner, insuredDrivers, expirationDate, vehicleId
            String[] officialOwner = parts[0].split(" ");
            String[] insuredDrivers = parts[1].split(";");
            List<Name> insuredDriversList = new ArrayList<>();
            for (String driver : insuredDrivers) {
                String[] name = driver.split(" ");
                insuredDriversList.add(new Name(name[0],name[1]));
            }
            String expirationDate = parts[2];
            String vehicleId = parts[3];
            if (!vehicleId.equals(vehicle.getVehicleId())) {
                continue;
            }
            vehicleInsurances.add(new VehicleInsurance(new Name(officialOwner[0],officialOwner[1]), insuredDriversList, parseDate(expirationDate), vehicle));
        }
        return vehicleInsurances;
    }

    /**
     * Loads the crash data from the CSV file.
     * @param driverId The driver ID to load the crash for.
     * @return A list of crashes.
     */
    private static List<Crash> loadCrash(String driverId) {
        List<Crash> crashes = new ArrayList<>();
        File crashFile = new File("crash.csv");
        List<String> crashLines = readLines(crashFile);
        for (String line : crashLines) {
            String[] parts = line.split(", ");
            // date, crashType, offendingDriver, driverId
            String driverId1 = parts[3];
            if (!driverId1.equals(driverId)) {
                continue;
            }
            String date = parts[0];
            String crashType = parts[1];
            String[] offendingDriver = parts[2].split(" ");

            crashes.add(new Crash(parseDate(date), crashType, new Name(offendingDriver[0],offendingDriver[1])));
        }
        return crashes;
    }

    /**
     * Loads the violation data from the CSV file.
     * @param driverId The driver ID to load the violation for.
     * @return A list of violations.
     */
    private static List<Violation> loadViolation(String driverId) {
        List<Violation> violations = new ArrayList<>();
        File violationFile = new File("violation.csv");
        List<String> violationLines = readLines(violationFile);
        for (String line : violationLines) {
            String[] parts = line.split(", ");
            // violationType, date, driverId
            String driverId1 = parts[2];
            if (!driverId1.equals(driverId)) {
                continue;
            }
            String date = parts[1];
            String violationType = parts[0];
            Violation violation = ViolationFactory.createViolation(violationType, parseDate(date));
            violations.add(violation);
        }
        return violations;
    }
    /**
     * Reads the lines from a file.
     * @param file The file to read.
     * @return A list of lines.
     */
    private static List<String> readLines(File file) {
        List<String> lines = new ArrayList<>();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(file.getName());
        Scanner scanner = null;
        if (inputStream != null) {
            scanner = new Scanner(inputStream);
        }
        if (scanner != null) {
            scanner.nextLine(); // skip header
            while (scanner.hasNextLine()) {
                lines.add(scanner.nextLine());
            }
            scanner.close();
        }
        return lines;
    }
}

