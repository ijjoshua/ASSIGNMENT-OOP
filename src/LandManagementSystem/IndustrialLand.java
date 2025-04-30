package LandManagementSystem;

import java.util.Date;

public class IndustrialLand extends Land {
    private boolean hasEnvironmentalClearance;
    private String industryType;

    public IndustrialLand(String landId, String ownerName, String location,
                          double sizeInAcres, Date registrationDate,
                          boolean hasEnvironmentalClearance, String industryType) {
        super(landId, ownerName, location, sizeInAcres, registrationDate);
        this.hasEnvironmentalClearance = hasEnvironmentalClearance;
        this.industryType = industryType;
    }

    @Override
    public boolean validateOwnership() {
        return validateBasicInfo() && ownerName != null && !ownerName.isEmpty();
    }

    @Override
    public boolean checkZoningCompliance() {
        return hasEnvironmentalClearance &&
                (location.toLowerCase().contains("industrial") ||
                        location.toLowerCase().contains("manufacturing"));
    }

    @Override
    public double calculateTax() {
        return sizeInAcres * 12000 * 0.03;
    }

    @Override
    public String generateLandReport() {
        return "INDUSTRIAL LAND REPORT\n" +
                "Land ID: " + landId + "\n" +
                "Owner: " + ownerName + "\n" +
                "Location: " + location + "\n" +
                "Size: " + sizeInAcres + " acres\n" +
                "Industry Type: " + industryType + "\n" +
                "Environmental Clearance: " + (hasEnvironmentalClearance ? "Yes" : "No") + "\n" +
                "Registration Date: " + registrationDate + "\n" +
                "Land Use Status: " + landUseStatus + "\n" +
                "Zoning Compliance: " + (checkZoningCompliance() ? "Compliant" : "Non-Compliant") + "\n" +
                "Ownership Valid: " + (validateOwnership() ? "Yes" : "No") + "\n" +
                "Annual Tax: $" + String.format("%.2f", calculateTax()) + "\n";
    }

    // Getters for additional properties
    public boolean hasEnvironmentalClearance() {
        return hasEnvironmentalClearance;
    }

    public String getIndustryType() {
        return industryType;
    }
}