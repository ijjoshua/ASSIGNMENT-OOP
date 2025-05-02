package LandManagementSystem;

import java.util.Date;

public class ResidentialLand extends Land {
    private int residentialUnits;
    private boolean hasUtilities;

    public ResidentialLand(String landId, String ownerName, String location,
                           double sizeInAcres, Date registrationDate,
                           int residentialUnits, boolean hasUtilities) {
        super(landId, ownerName, location, sizeInAcres, registrationDate);
        this.residentialUnits = residentialUnits;
        this.hasUtilities = hasUtilities;
    }

    @Override
    public boolean validateOwnership() {
        return validateBasicInfo() && ownerName != null && !ownerName.isEmpty();
    }

    @Override
    public boolean checkZoningCompliance() {
        return residentialUnits <= sizeInAcres * 2 &&
                location.toLowerCase().contains("residential");
    }

    @Override
    public double calculateTax() {
        return sizeInAcres * 8000 * 0.015;
    }

    @Override
    public String generateLandReport() {
        return "RESIDENTIAL LAND REPORT\n" +
                "Land ID: " + landId + "\n" +
                "Owner: " + ownerName + "\n" +
                "Location: " + location + "\n" +
                "Size: " + sizeInAcres + " acres\n" +
                "Residential Units: " + residentialUnits + "\n" +
                "Utilities Available: " + (hasUtilities ? "Yes" : "No") + "\n" +
                "Registration Date: " + registrationDate + "\n" +
                "Land Use Status: " + landUseStatus + "\n" +
                "Zoning Compliance: " + (checkZoningCompliance() ? "Compliant" : "Non-Compliant") + "\n" +
                "Ownership Valid: " + (validateOwnership() ? "Yes" : "No") + "\n" +
                "Annual Tax: $" + String.format("%.2f", calculateTax()) + "\n";
    }

    // Getters for additional properties
    public int getResidentialUnits() {
        return residentialUnits;
    }

    public boolean hasUtilities() {
        return hasUtilities;
    }
}