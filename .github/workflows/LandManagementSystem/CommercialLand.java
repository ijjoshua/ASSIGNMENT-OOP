package LandManagementSystem;

import java.util.Date;

public class CommercialLand extends Land {
    private String businessType;
    private boolean hasParking;

    public CommercialLand(String landId, String ownerName, String location,
                          double sizeInAcres, Date registrationDate,
                          String businessType, boolean hasParking) {
        super(landId, ownerName, location, sizeInAcres, registrationDate);
        this.businessType = businessType;
        this.hasParking = hasParking;
    }

    @Override
    public boolean validateOwnership() {
        return validateBasicInfo() && ownerName != null && !ownerName.isEmpty();
    }

    @Override
    public boolean checkZoningCompliance() {
        return location.toLowerCase().contains("commercial") ||
                location.toLowerCase().contains("business");
    }

    @Override
    public double calculateTax() {
        return sizeInAcres * 10000 * 0.025;
    }

    @Override
    public String generateLandReport() {
        return "COMMERCIAL LAND REPORT\n" +
                "Land ID: " + landId + "\n" +
                "Owner: " + ownerName + "\n" +
                "Location: " + location + "\n" +
                "Size: " + sizeInAcres + " acres\n" +
                "Business Type: " + businessType + "\n" +
                "Parking Available: " + (hasParking ? "Yes" : "No") + "\n" +
                "Registration Date: " + registrationDate + "\n" +
                "Land Use Status: " + landUseStatus + "\n" +
                "Zoning Compliance: " + (checkZoningCompliance() ? "Compliant" : "Non-Compliant") + "\n" +
                "Ownership Valid: " + (validateOwnership() ? "Yes" : "No") + "\n" +
                "Annual Tax: $" + String.format("%.2f", calculateTax()) + "\n";
    }

    // Getters for additional properties
    public String getBusinessType() {
        return businessType;
    }

    public boolean hasParking() {
        return hasParking;
    }
}