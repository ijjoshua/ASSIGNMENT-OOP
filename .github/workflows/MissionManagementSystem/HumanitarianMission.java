package MissionManagementSystem;

import java.util.Date;
import java.util.List;

public class HumanitarianMission extends Mission {
    private List<Resource> allocatedResources;
    private List<Task> tasks;

    public HumanitarianMission(String missionId, String missionName, Date missionStartDate, Date missionEndDate) {
        super(missionId, missionName, missionStartDate, missionEndDate);
    }

    @Override
    public void assignTask(Task task, Personnel personnel) throws InvalidTaskAssignmentException {
        if (!assignedPersonnel.contains(personnel)) {
            throw new InvalidTaskAssignmentException("Personnel not assigned to this mission");
        }

        if (personnel.hasOverlappingTasks(task, this)) {
            throw new InvalidTaskAssignmentException("Personnel has overlapping tasks");
        }

        tasks.add(task);
        personnel.assignTask(task);
    }

    @Override
    public void allocateResources(Resource resource) throws ResourceAllocationException {
        if (!resource.getResourceType().equals("FOOD_SUPPLIES") &&
                !resource.getResourceType().equals("MEDICAL_SUPPLIES") &&
                !resource.getResourceType().equals("TRANSPORTATION")) {
            throw new ResourceAllocationException("Invalid resource type for humanitarian mission");
        }

        if (resource.getQuantity() <= 0) {
            throw new ResourceAllocationException("No available quantity for this resource");
        }

        allocatedResources.add(resource);
    }

    @Override
    public void trackMissionProgress() {
        // Simplified progress tracking
        if (tasks.stream().allMatch(Task::isCompleted)) {
            setStatus("COMPLETED");
        } else if (tasks.stream().anyMatch(Task::isCompleted)) {
            setStatus("IN_PROGRESS");
        }
    }

    @Override
    public String generateMissionReport() {
        StringBuilder report = new StringBuilder();
        report.append("Humanitarian Mission Report\n");
        report.append("Mission ID: ").append(missionId).append("\n");
        report.append("Mission Name: ").append(missionName).append("\n");
        report.append("Status: ").append(status).append("\n");
        report.append("Start Date: ").append(missionStartDate).append("\n");
        report.append("End Date: ").append(missionEndDate).append("\n");

        report.append("\nAssigned Personnel:\n");
        for (Personnel p : assignedPersonnel) {
            report.append("- ").append(p.getPersonnelName()).append(" (").append(p.getPersonnelRole()).append(")\n");
        }

        report.append("\nAllocated Resources:\n");
        for (Resource r : allocatedResources) {
            report.append("- ").append(r.getResourceName()).append(" (").append(r.getQuantity()).append(")\n");
        }

        report.append("\nTasks:\n");
        for (Task t : tasks) {
            report.append("- ").append(t.getDescription()).append(": ").append(t.isCompleted() ? "COMPLETED" : "PENDING").append("\n");
        }

        return report.toString();
    }

    public boolean validatePersonnel() {
        return assignedPersonnel.stream().anyMatch(p ->
                p.getPersonnelRole().equals("Medic") ||
                        p.getPersonnelRole().equals("Logistics Officer"));
    }

    public boolean validateResources() {
        return allocatedResources.stream().anyMatch(r ->
                r.getResourceName().equals("Food Supplies") ||
                        r.getResourceName().equals("Medical Kit"));
    }
}