import java.util.Objects;

public class Vehicle {
    private String make;
    private String model;
    private String color;
    private int year;
    private Name officialOwner;
    private String vehicleId;


    /**
     * Constructs a new Vehicle instance.
     * @param make The brand of the vehicle.
     * @param model The model of the vehicle.
     * @param color The color of the vehicle.
     * @param year The year of manufacture of the vehicle.
     * @param officialOwner The official owner of the vehicle.
     * @param vehicleId the unique id of the vehicle
     */
    public Vehicle(String make, String model, String color, int year, Name officialOwner, String vehicleId) {
        this.make = make;
        this.model = model;
        this.color = color;
        this.year = year;
        this.officialOwner = officialOwner;
        this.vehicleId = vehicleId;
    }

    /**
     * Gets the make of the vehicle.
     * @return The make of the vehicle.
     */
    public String getMake() {
        return make;
    }

    /**
     * Gets the model of the vehicle.
     * @return The model of the vehicle.
     */
    public String getModel() {
        return model;
    }

    /**
     * Gets the color of the vehicle
     * @return the color of the vehicle
     */
    public String getColor() {
        return color;
    }

    /**
     * Gets the year of manufacture of the vehicle.
     * @return The year of manufacture.
     */
    public int getYear() {
        return year;
    }

    /**
     * Gets the official owner of the vehicle.
     * @return The official owner's name.
     */
    public Name getOfficialOwner() {
        return officialOwner;
    }


    /**
     * Gets the unique id of the vehicle
     * @return a string represents the unique id of the vehicle
     */
    public String getVehicleId() {
        return vehicleId;
    }

    public String getDescription() {
        return year + " " + color + " " + make + " " + model + ", " + vehicleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return year == vehicle.year && Objects.equals(make, vehicle.make) && Objects.equals(model, vehicle.model) && Objects.equals(color, vehicle.color) && Objects.equals(officialOwner, vehicle.officialOwner) && Objects.equals(vehicleId, vehicle.vehicleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(make, model, color, year, officialOwner, vehicleId);
    }
}
