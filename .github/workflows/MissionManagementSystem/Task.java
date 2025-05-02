package MissionManagementSystem;

import java.util.Date;

public class Task {
    private String taskId;
    private String description;
    private Date startDate;
    private Date endDate;
    private boolean completed;

    public Task(String taskId, String description, Date startDate, Date endDate) {
        this.taskId = taskId;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.completed = false;
    }

    // Getters and setters
    public String getTaskId() {
        return taskId;
    }

    public String getDescription() {
        return description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}