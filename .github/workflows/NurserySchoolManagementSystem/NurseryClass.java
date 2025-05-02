package NurserySchoolManagementSystem;

import java.util.ArrayList;
import java.util.List;

public abstract class NurseryClass {
    protected String classId;
    protected String className;
    protected int maxCapacity;
    protected Teacher assignedTeacher;
    protected List<Student> students;
    protected List<String> activities;

    public NurseryClass(String classId, String className, int maxCapacity) {
        this.classId = classId;
        this.className = className;
        this.maxCapacity = maxCapacity;
        this.students = new ArrayList<>();
        this.activities = new ArrayList<>();
    }

    // Abstract methods to be implemented by concrete classes
    public abstract boolean enrollStudent(Student student) throws EnrollmentException;
    public abstract void trackProgress();
    public abstract void conductActivity(String activityName);
    public abstract String generateClassReport();

    // Common methods for all nursery classes
    public boolean assignTeacher(Teacher teacher) {
        if (teacher == null) {
            return false;
        }
        this.assignedTeacher = teacher;
        teacher.setAssignedClass(this);
        return true;
    }

    public boolean isAtCapacity() {
        return students.size() >= maxCapacity;
    }

    // Getters
    public String getClassId() {
        return classId;
    }

    public String getClassName() {
        return className;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public Teacher getAssignedTeacher() {
        return assignedTeacher;
    }

    public List<Student> getStudents() {
        return students;
    }

    public List<String> getActivities() {
        return activities;
    }
}