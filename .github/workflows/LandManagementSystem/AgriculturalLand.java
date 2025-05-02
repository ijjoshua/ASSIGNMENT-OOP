package LandManagementSystem;

import java.util.Date;

public class AgriculturalLand extends Land {
    private boolean hasFarmingEquipment;
    private String cropType;

    public AgriculturalLand(String landId, String ownerName, String location,
                            double sizeInAcres, Date registrationDate,
                            boolean hasFarmingEquipment, String cropType) {
        super(landId, ownerName, location, sizeInAcres, registrationDate);
        this.hasFarmingEquipment = hasFarmingEquipment;
        this.cropType = cropType;
    }

    @Override
    public boolean validateOwnership() {
        return validateBasicInfo() && ownerName != null && !ownerName.isEmpty();
    }

    @Override
    public boolean checkZoningCompliance() {
        return sizeInAcres >= 1 && !location.toLowerCase().contains("urban");
    }

    @Override
    public double calculateTax() {
        return sizeInAcres * 5000 * 0.01;
    }

    @Override
    public String generateLandReport() {
        return "AGRICULTURAL LAND REPORT\n" +
                "Land ID: " + landId + "\n" +
                "Owner: " + ownerName + "\n" +
                "Location: " + location + "\n" +
                "Size: " + sizeInAcres + " acres\n" +
                "Crop Type: " + cropType + "\n" +
                "Registration Date: " + registrationDate + "\n" +
                "Land Use Status: " + landUseStatus + "\n" +
                "Zoning Compliance: " + (checkZoningCompliance() ? "Compliant" : "Non-Compliant") + "\n" +
                "Ownership Valid: " + (validateOwnership() ? "Yes" : "No") + "\n" +
                "Annual Tax: $" + String.format("%.2f", calculateTax()) + "\n";
    }

    // Getters for additional properties
    public boolean hasFarmingEquipment() {
        return hasFarmingEquipment;
    }

    public String getCropType() {
        return cropType;
    }
}
