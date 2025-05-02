package LandManagementSystem;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class LandManagementSystem {
    private static Scanner scanner = new Scanner(System.in);
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static LandRegistry registry = new LandRegistry();

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            System.out.println("\n=== Land Management System ===");
            System.out.println("1. Register New Land");
            System.out.println("2. View All Land Reports");
            System.out.println("3. Search Lands by Owner");
            System.out.println("4. Search Lands by Location");
            System.out.println("5. Search Lands by Type");
            System.out.println("6. Exit");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    registerNewLand();
                    break;
                case 2:
                    registry.printAllReports();
                    break;
                case 3:
                    searchByOwner();
                    break;
                case 4:
                    searchByLocation();
                    break;
                case 5:
                    searchByType();
                    break;
                case 6:
                    running = false;
                    System.out.println("Exiting system...");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void registerNewLand() {
        System.out.println("\n=== Register New Land ===");
        System.out.println("1. Agricultural Land");
        System.out.println("2. Residential Land");
        System.out.println("3. Commercial Land");
        System.out.println("4. Industrial Land");
        System.out.print("Select land type: ");

        int type = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter Land ID: ");
        String landId = scanner.nextLine();

        System.out.print("Enter Owner Name: ");
        String ownerName = scanner.nextLine();

        System.out.print("Enter Location: ");
        String location = scanner.nextLine();

        System.out.print("Enter Size in Acres: ");
        double size = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        Date registrationDate = null;
        try {
            System.out.print("Enter Registration Date (yyyy-MM-dd): ");
            registrationDate = dateFormat.parse(scanner.nextLine());
        } catch (ParseException e) {
            System.out.println("Invalid date format. Registration failed.");
            return;
        }

        Land land = null;
        switch (type) {
            case 1: // Agricultural
                System.out.print("Does it have farming equipment? (true/false): ");
                boolean hasEquipment = scanner.nextBoolean();
                scanner.nextLine();
                System.out.print("Enter Crop Type: ");
                String cropType = scanner.nextLine();
                land = new AgriculturalLand(landId, ownerName, location, size,
                        registrationDate, hasEquipment, cropType);
                break;
            case 2: // Residential
                System.out.print("Enter Number of Residential Units: ");
                int units = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Does it have utilities? (true/false): ");
                boolean hasUtilities = scanner.nextBoolean();
                scanner.nextLine();
                land = new ResidentialLand(landId, ownerName, location, size,
                        registrationDate, units, hasUtilities);
                break;
            case 3: // Commercial
                System.out.print("Enter Business Type: ");
                String businessType = scanner.nextLine();
                System.out.print("Does it have parking? (true/false): ");
                boolean hasParking = scanner.nextBoolean();
                scanner.nextLine();
                land = new CommercialLand(landId, ownerName, location, size,
                        registrationDate, businessType, hasParking);
                break;
            case 4: // Industrial
                System.out.print("Does it have environmental clearance? (true/false): ");
                boolean hasClearance = scanner.nextBoolean();
                scanner.nextLine();
                System.out.print("Enter Industry Type: ");
                String industryType = scanner.nextLine();
                land = new IndustrialLand(landId, ownerName, location, size,
                        registrationDate, hasClearance, industryType);
                break;
            default:
                System.out.println("Invalid land type.");
                return;
        }

        registry.registerLand(land);
    }

    private static void searchByOwner() {
        System.out.print("Enter Owner Name to Search: ");
        String ownerName = scanner.nextLine();
        List<Land> results = registry.searchByOwner(ownerName);
        printSearchResults(results);
    }

    private static void searchByLocation() {
        System.out.print("Enter Location to Search: ");
        String location = scanner.nextLine();
        List<Land> results = registry.searchByLocation(location);
        printSearchResults(results);
    }

    private static void searchByType() {
        System.out.println("Search by Land Type:");
        System.out.println("1. Agricultural");
        System.out.println("2. Residential");
        System.out.println("3. Commercial");
        System.out.println("4. Industrial");
        System.out.print("Select type: ");

        int type = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Class<?> landClass = null;
        switch (type) {
            case 1: landClass = AgriculturalLand.class; break;
            case 2: landClass = ResidentialLand.class; break;
            case 3: landClass = CommercialLand.class; break;
            case 4: landClass = IndustrialLand.class; break;
            default:
                System.out.println("Invalid type.");
                return;
        }

        List<Land> results = registry.searchByType(landClass);
        printSearchResults(results);
    }

    private static void printSearchResults(List<Land> lands) {
        if (lands.isEmpty()) {
            System.out.println("No lands found matching the criteria.");
            return;
        }

        System.out.println("\n=== Search Results ===");
        for (Land land : lands) {
            System.out.println(land.generateLandReport());
            System.out.println("----------------------------------------");
        }
    }
}
