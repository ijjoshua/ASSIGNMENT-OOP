package LandManagementSystem;

import java.util.Date;

public abstract class Land {
    protected String landId;
    protected String ownerName;
    protected String location;
    protected double sizeInAcres;
    protected Date registrationDate;
    protected String landUseStatus;

    public Land(String landId, String ownerName, String location,
                double sizeInAcres, Date registrationDate) {
        this.landId = landId;
        this.ownerName = ownerName;
        this.location = location;
        this.sizeInAcres = sizeInAcres;
        this.registrationDate = registrationDate;
        this.landUseStatus = "Vacant";
    }

    // Abstract methods to be implemented by concrete land classes
    public abstract boolean validateOwnership();
    public abstract boolean checkZoningCompliance();
    public abstract double calculateTax();
    public abstract String generateLandReport();

    // Common methods for all land types
    public boolean validateBasicInfo() {
        return ownerName != null && !ownerName.isEmpty() &&
                sizeInAcres > 0 && landId != null;
    }

    // Getters and setters
    public String getLandId() {
        return landId;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public String getLocation() {
        return location;
    }

    public double getSizeInAcres() {
        return sizeInAcres;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public String getLandUseStatus() {
        return landUseStatus;
    }

    public void setLandUseStatus(String landUseStatus) {
        this.landUseStatus = landUseStatus;
    }
}
