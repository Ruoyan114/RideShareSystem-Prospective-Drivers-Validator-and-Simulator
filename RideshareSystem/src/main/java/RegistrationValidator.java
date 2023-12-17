import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Class to validate the registration of a prospective driver.
 * This class checks for: prospective driver's age, prospective driver's license
 * information, vehicle information, vehicle insurance information, driver's history,
 * and vehicle history.
 */
public class RegistrationValidator {
    /**
     * Validates a prospective driver based on various criteria.
     * Checks for the driver's age, license information, vehicle information,
     * vehicle insurance information, driver's history, and vehicle history.
     * @param driver The driver to validate.
     * @return true if the driver meets all criteria, false otherwise.
     */
    public boolean validate(Driver driver) {
        if (driver == null) {
            return false;
        }

        if (!isLegalAge(driver.getBirthDate())) {
            return false;
        }

        DriverLicense license = driver.getDriverLicense();
        if (!isLicenseValid(license, driver)) {
            return false;
        }

        List<Vehicle> vehicles = driver.getVehicles();
        for (Vehicle vehicle : vehicles) {
            if (!isVehicleValid(vehicle)) {
                return false;
            }
        }

        VehicleInsurance insurance = driver.getInsurance();
        if (!isInsuranceValid(driver,insurance)) {
            return false;
        }

        DriverHistory history = driver.getDriverHistory();
        if (!isDriverHistoryAcceptable(history)) {
            return false;
        }

        VehicleHistory vehicleHistory = driver.getVehicleHistory();
        if (!isVehicleHistoryAcceptable(vehicleHistory)) {
            return false;
        }

        return true;
    }

    /**
     * Check for the driver's age (should not be accepted if under 21)
     * @param birthDate the driver's birthdate
     * @return true if the driver is over 21 years old,  false otherwise
     */
    private boolean isLegalAge(Date birthDate) {
        LocalDate birthLocalDate = birthDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate currentDate = LocalDate.now();
        int age = Period.between(birthLocalDate, currentDate).getYears();

        return age >= 21;
    }

    /**
     * Validates driver's license information.
     * @param license the driver's license information to validate
     * @param driver the driver's information
     * @return true if there is no name differences, birthdate differences, and country of issuance in US/Canada
     */
    private boolean isLicenseValid(DriverLicense license, Driver driver) {
        if (license == null) {
            return false;
        }

        // check for name difference
        if (!license.getName().equals(driver.getName())) {
            return false;
        }
        // check for birthdate difference
        if (!license.getBirthDate().equals(driver.getBirthDate())) {
            return false;
        }
        // check for country of issuance (US/Canada are valid)
        if (!license.getCountryOfIssuance().equalsIgnoreCase("US")
            && (!license.getCountryOfIssuance().equalsIgnoreCase("Canada"))) {
            return false;
        }
        // check for date of issuance (issue date should not be less than six month)
        LocalDate issuedDate = convertToLocalDate(license.getIssuedDate());
        if (issuedDate.isAfter(LocalDate.now().minusMonths(6))) {
            return false;
        }
        // check for expiration date
        LocalDate expirationDate = convertToLocalDate(license.getExpirationDate());
        if (expirationDate.isBefore(LocalDate.now())) {
            return false;
        }

        return true;
    }

    /**
     * Validates whether the vehicle associated with the driver is older than 15 years
     * @param vehicle The driver's vehicle to validate
     * @return true if the vehicle is less than 15 years, false otherwise.
     */
    private boolean isVehicleValid(Vehicle vehicle) {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        int vehicleYear = vehicle.getYear();

        return currentYear - vehicleYear <= 15;
    }

    /**
     * Validates whether the vehicle insurance information meets the criteria.
     * @param driver The prospective driver to validate.
     * @param insurance The insurance information for the driver's vehicle.
     * @return true if the insurance information meets all criteria, false otherwise.
     */
    private boolean isInsuranceValid(Driver driver, VehicleInsurance insurance) {
        if (insurance == null) {
            return false;
        }

        // Check if the driver is the official owner or a listed insured driver
        boolean isOwnerOrInsured = insurance.getOfficialOwner().equals(driver.getName()) ||
                                    insurance.getInsuredDrivers().contains(driver.getName());

        if (!isOwnerOrInsured) {
            return false;
        }

        // check if the insurance has expired
        Date insuranceExpirationDate = insurance.getExpirationDate();
        LocalDate expirationLocalDate = convertToLocalDate(insuranceExpirationDate);
        if (expirationLocalDate.isBefore(LocalDate.now())) {
            return false;
        }

        return true;
    }

    /**
     * check for driver's history (unacceptable moving violations)
     * @param driverHistory the driver's history to validate
     * @return true if the driver's history is acceptable, false otherwise
     */
    private boolean isDriverHistoryAcceptable(DriverHistory driverHistory) {
        List<Violation> violations = driverHistory.getViolations();
        for (Violation violation : violations) {
            if (violation instanceof MovingViolation) {
                String violationType = ((MovingViolation) violation).getViolationType();
                if (isUnacceptableViolation(violationType)) {
                    return false;
                }
            }
        }
        return true;
    }

    // checks if a given violation is an unacceptable moving violation
    private boolean isUnacceptableViolation(String violationType) {
        return violationType.equalsIgnoreCase("Reckless Driving") ||
                violationType.equalsIgnoreCase("Speeding") ||
                violationType.equalsIgnoreCase("Driving Under Influence") ||
                violationType.equalsIgnoreCase("Driving Without a Valid License/Insurance");
    }

    /**
     * Validates the vehicle's history to check if there are any crashes or moving
     * violations in the last six months.
     * @param vehicleHistory The vehicle history to validate.
     * @return true if the vehicle history does not contain crashes or moving violations in the last six months, false otherwise.
     */

    private boolean isVehicleHistoryAcceptable(VehicleHistory vehicleHistory) {
        LocalDate sixMonthAgo = LocalDate.now().minusMonths(6);
        // check for crashes in the last six months
        for (Crash crash : vehicleHistory.getCrashes()) {
            LocalDate crashDate = convertToLocalDate(crash.getDate());
            if (crashDate.isAfter(sixMonthAgo)) {
                return false;
            }
        }

        // check for moving violations in the last six months
        for (Violation violation : vehicleHistory.getViolations()) {
            if (violation instanceof MovingViolation) {
                LocalDate violationDate = convertToLocalDate(violation.getDate());
                if (violationDate.isAfter(sixMonthAgo)) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Converts a Date object to a LocalDate object.
     * @param date The date to convert.
     * @return The LocalDate equivalent of the provided date.
     */
    private LocalDate convertToLocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

}
