package MissionManagementSystem;

import java.util.Date;
import java.util.List;

public abstract class Mission {
    protected String missionId;
    protected String missionName;
    protected Date missionStartDate;
    protected Date missionEndDate;
    protected String status;
    protected List<Personnel> assignedPersonnel;

    public Mission(String missionId, String missionName, Date missionStartDate, Date missionEndDate) {
        this.missionId = missionId;
        this.missionName = missionName;
        this.missionStartDate = missionStartDate;
        this.missionEndDate = missionEndDate;
        this.status = "PLANNED";
    }

    // Abstract methods to be implemented by concrete mission classes
    public abstract void assignTask(Task task, Personnel personnel) throws InvalidTaskAssignmentException;
    public abstract void allocateResources(Resource resource) throws ResourceAllocationException;
    public abstract void trackMissionProgress();
    public abstract String generateMissionReport();

    // Common methods for all missions
    public void assignPersonnel(Personnel personnel) throws InvalidPersonnelAssignmentException {
        if (personnel.getAssignedMission() != null && !personnel.getAssignedMission().equals(this)) {
            throw new InvalidPersonnelAssignmentException("Personnel is already assigned to another mission");
        }

        if (!assignedPersonnel.contains(personnel)) {
            assignedPersonnel.add(personnel);
            personnel.setAssignedMission(this);
        }
    }

    public boolean validateMissionDates() {
        return missionStartDate.before(missionEndDate);
    }

    // Getters and setters
    public String getMissionId() {
        return missionId;
    }

    public String getMissionName() {
        return missionName;
    }

    public Date getMissionStartDate() {
        return missionStartDate;
    }

    public Date getMissionEndDate() {
        return missionEndDate;
    }

    public String getStatus() {
        return status;
    }

    public List<Personnel> getAssignedPersonnel() {
        return assignedPersonnel;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}