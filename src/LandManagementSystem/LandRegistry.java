package LandManagementSystem;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LandRegistry {
    private List<Land> registeredLands;

    public LandRegistry() {
        this.registeredLands = new ArrayList<>();
    }

    public void registerLand(Land land) {
        if (land.validateOwnership()) {
            registeredLands.add(land);
            System.out.println("Land registered successfully: " + land.getLandId());
        } else {
            System.out.println("Registration failed: Invalid ownership details");
        }
    }

    public List<Land> searchByOwner(String ownerName) {
        List<Land> results = new ArrayList<>();
        for (Land land : registeredLands) {
            if (land.getOwnerName().equalsIgnoreCase(ownerName)) {
                results.add(land);
            }
        }
        return results;
    }

    public List<Land> searchByLocation(String location) {
        List<Land> results = new ArrayList<>();
        for (Land land : registeredLands) {
            if (land.getLocation().toLowerCase().contains(location.toLowerCase())) {
                results.add(land);
            }
        }
        return results;
    }

    public List<Land> searchByType(Class<?> landType) {
        List<Land> results = new ArrayList<>();
        for (Land land : registeredLands) {
            if (landType.isInstance(land)) {
                results.add(land);
            }
        }
        return results;
    }

    public void printAllReports() {
        for (Land land : registeredLands) {
            System.out.println(land.generateLandReport());
            System.out.println("----------------------------------------");
        }
    }
}
