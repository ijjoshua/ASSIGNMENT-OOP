package MissionManagementSystem;

import java.util.ArrayList;
import java.util.List;

public class Personnel {
    private String personnelId;
    private String personnelName;
    private String personnelRole;
    private Mission assignedMission;
    private List<Task> assignedTasks;

    public Personnel(String personnelId, String personnelName, String personnelRole) {
        this.personnelId = personnelId;
        this.personnelName = personnelName;
        this.personnelRole = personnelRole;
        this.assignedTasks = new ArrayList<>();
    }

    public void assignTask(Task task) {
        assignedTasks.add(task);
    }

    public boolean hasOverlappingTasks(Task newTask, Mission mission) {
        if (assignedMission != null && !assignedMission.equals(mission)) {
            return assignedTasks.stream().anyMatch(t ->
                    !t.isCompleted() &&
                            t.getEndDate().after(newTask.getStartDate()) &&
                            t.getStartDate().before(newTask.getEndDate()));
        }
        return false;
    }

    // Getters and setters
    public String getPersonnelId() {
        return personnelId;
    }

    public String getPersonnelName() {
        return personnelName;
    }

    public String getPersonnelRole() {
        return personnelRole;
    }

    public Mission getAssignedMission() {
        return assignedMission;
    }

    public void setAssignedMission(Mission assignedMission) {
        this.assignedMission = assignedMission;
    }

    public List<Task> getAssignedTasks() {
        return assignedTasks;
    }
}
