package MissionManagementSystem;

import java.util.*;
import java.text.SimpleDateFormat;

public class MissionManagementSystem {
    private static Scanner scanner = new Scanner(System.in);
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static List<Mission> missions = new ArrayList<>();
    private static List<Personnel> personnelList = new ArrayList<>();
    private static List<Resource> resources = new ArrayList<>();

    public static void main(String[] args) {
        initializeSampleData();

        while (true) {
            System.out.println("\n=== Mission Management System ===");
            System.out.println("1. Create New Mission");
            System.out.println("2. List All Missions");
            System.out.println("3. Manage Mission");
            System.out.println("4. List Personnel");
            System.out.println("5. List Resources");
            System.out.println("6. Exit");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    createNewMission();
                    break;
                case 2:
                    listAllMissions();
                    break;
                case 3:
                    manageMission();
                    break;
                case 4:
                    listPersonnel();
                    break;
                case 5:
                    listResources();
                    break;
                case 6:
                    System.out.println("Exiting system...");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void initializeSampleData() {
        // Sample personnel
        personnelList.add(new Personnel("P001", "Dr. Smith", "Medic"));
        personnelList.add(new Personnel("P002", "John Doe", "Soldier"));
        personnelList.add(new Personnel("P003", "Jane Smith", "Soldier"));
        personnelList.add(new Personnel("P004", "Mike Johnson", "Logistics Officer"));
        personnelList.add(new Personnel("P005", "Sarah Williams", "Recon Specialist"));

        // Sample resources
        resources.add(new Resource("R001", "Drone", 5, "RECON_EQUIPMENT"));
        resources.add(new Resource("R002", "Medical Kit", 10, "MEDICAL_SUPPLIES"));
        resources.add(new Resource("R003", "Ammunition", 1000, "WEAPONRY"));
        resources.add(new Resource("R004", "Food Supplies", 50, "FOOD_SUPPLIES"));
        resources.add(new Resource("R005", "Armored Vehicle", 2, "VEHICLE"));
    }

    private static void createNewMission() {
        System.out.println("\n=== Create New Mission ===");
        System.out.println("1. Recon Mission");
        System.out.println("2. Rescue Mission");
        System.out.println("3. Combat Mission");
        System.out.println("4. Humanitarian Mission");
        System.out.print("Select mission type: ");

        int type = scanner.nextInt();
        scanner.nextLine(); // consume newline

        System.out.print("Enter Mission ID: ");
        String missionId = scanner.nextLine();

        System.out.print("Enter Mission Name: ");
        String missionName = scanner.nextLine();

        Date startDate = null, endDate = null;
        try {
            System.out.print("Enter Start Date (yyyy-MM-dd): ");
            startDate = dateFormat.parse(scanner.nextLine());

            System.out.print("Enter End Date (yyyy-MM-dd): ");
            endDate = dateFormat.parse(scanner.nextLine());
        } catch (Exception e) {
            System.out.println("Invalid date format. Please use yyyy-MM-dd.");
            return;
        }

        Mission mission = null;
        switch (type) {
            case 1:
                mission = new ReconMission(missionId, missionName, startDate, endDate);
                break;
            case 2:
                mission = new RescueMission(missionId, missionName, startDate, endDate);
                break;
            case 3:
                mission = new CombatMission(missionId, missionName, startDate, endDate);
                break;
            case 4:
                mission = new HumanitarianMission(missionId, missionName, startDate, endDate);
                break;
            default:
                System.out.println("Invalid mission type.");
                return;
        }

        missions.add(mission);
        System.out.println("Mission created successfully!");
    }

    private static void listAllMissions() {
        System.out.println("\n=== All Missions ===");
        if (missions.isEmpty()) {
            System.out.println("No missions available.");
            return;
        }

        for (int i = 0; i < missions.size(); i++) {
            Mission m = missions.get(i);
            System.out.printf("%d. %s (%s) - %s to %s - Status: %s\n",
                    i+1, m.getMissionName(), m.getMissionId(),
                    dateFormat.format(m.getMissionStartDate()),
                    dateFormat.format(m.getMissionEndDate()),
                    m.getStatus());
        }
    }

    private static void manageMission() {
        listAllMissions();
        if (missions.isEmpty()) return;

        System.out.print("Select mission to manage (number): ");
        int missionIndex = scanner.nextInt() - 1;
        scanner.nextLine(); // consume newline

        if (missionIndex < 0 || missionIndex >= missions.size()) {
            System.out.println("Invalid mission selection.");
            return;
        }

        Mission mission = missions.get(missionIndex);

        while (true) {
            System.out.println("\n=== Managing Mission: " + mission.getMissionName() + " ===");
            System.out.println("1. Assign Personnel");
            System.out.println("2. Allocate Resources");
            System.out.println("3. Assign Task");
            System.out.println("4. Track Progress");
            System.out.println("5. Generate Report");
            System.out.println("6. Back to Main Menu");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    assignPersonnel(mission);
                    break;
                case 2:
                    allocateResources(mission);
                    break;
                case 3:
                    assignTask(mission);
                    break;
                case 4:
                    mission.trackMissionProgress();
                    System.out.println("Mission progress tracked. Current status: " + mission.getStatus());
                    break;
                case 5:
                    System.out.println(mission.generateMissionReport());
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void assignPersonnel(Mission mission) {
        listPersonnel();
        System.out.print("Select personnel to assign (number): ");
        int personnelIndex = scanner.nextInt() - 1;
        scanner.nextLine(); // consume newline

        if (personnelIndex < 0 || personnelIndex >= personnelList.size()) {
            System.out.println("Invalid personnel selection.");
            return;
        }

        try {
            mission.assignPersonnel(personnelList.get(personnelIndex));
            System.out.println("Personnel assigned successfully!");
        } catch (InvalidPersonnelAssignmentException e) {
            System.out.println("Error assigning personnel: " + e.getMessage());
        }
    }

    private static void allocateResources(Mission mission) {
        listResources();
        System.out.print("Select resource to allocate (number): ");
        int resourceIndex = scanner.nextInt() - 1;
        scanner.nextLine(); // consume newline

        if (resourceIndex < 0 || resourceIndex >= resources.size()) {
            System.out.println("Invalid resource selection.");
            return;
        }

        try {
            mission.allocateResources(resources.get(resourceIndex));
            System.out.println("Resource allocated successfully!");
        } catch (ResourceAllocationException e) {
            System.out.println("Error allocating resource: " + e.getMessage());
        }
    }

    private static void assignTask(Mission mission) {
        System.out.print("Enter Task ID: ");
        String taskId = scanner.nextLine();

        System.out.print("Enter Task Description: ");
        String description = scanner.nextLine();

        Date startDate = null, endDate = null;
        try {
            System.out.print("Enter Start Date (yyyy-MM-dd): ");
            startDate = dateFormat.parse(scanner.nextLine());

            System.out.print("Enter End Date (yyyy-MM-dd): ");
            endDate = dateFormat.parse(scanner.nextLine());
        } catch (Exception e) {
            System.out.println("Invalid date format. Please use yyyy-MM-dd.");
            return;
        }

        Task task = new Task(taskId, description, startDate, endDate);

        listPersonnel();
        System.out.print("Select personnel to assign task (number): ");
        int personnelIndex = scanner.nextInt() - 1;
        scanner.nextLine(); // consume newline

        if (personnelIndex < 0 || personnelIndex >= personnelList.size()) {
            System.out.println("Invalid personnel selection.");
            return;
        }

        try {
            mission.assignTask(task, personnelList.get(personnelIndex));
            System.out.println("Task assigned successfully!");
        } catch (InvalidTaskAssignmentException e) {
            System.out.println("Error assigning task: " + e.getMessage());
        }
    }

    private static void listPersonnel() {
        System.out.println("\n=== Available Personnel ===");
        for (int i = 0; i < personnelList.size(); i++) {
            Personnel p = personnelList.get(i);
            System.out.printf("%d. %s (%s) - %s\n",
                    i+1, p.getPersonnelName(), p.getPersonnelId(), p.getPersonnelRole());
        }
    }

    private static void listResources() {
        System.out.println("\n=== Available Resources ===");
        for (int i = 0; i < resources.size(); i++) {
            Resource r = resources.get(i);
            System.out.printf("%d. %s (%s) - Qty: %d - Type: %s\n",
                    i+1, r.getResourceName(), r.getResourceId(),
                    r.getQuantity(), r.getResourceType());
        }
    }
}