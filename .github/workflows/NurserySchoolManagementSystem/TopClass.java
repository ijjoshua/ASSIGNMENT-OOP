package NurserySchoolManagementSystem;

import java.util.ArrayList;
import java.util.List;

public class TopClass extends NurseryClass {
    private List<String> assessments;

    public TopClass(String classId) {
        super(classId, "Top Class", 25);
        this.assessments = new ArrayList<>();
    }

    @Override
    public boolean enrollStudent(Student student) throws EnrollmentException {
        if (student.getAge() < 4 || student.getAge() > 5) {
            throw new EnrollmentException("Student age must be between 4-5 for Top Class");
        }

        if (isAtCapacity()) {
            throw new EnrollmentException("Top Class has reached maximum capacity of 25 students");
        }

        if (student.getRegisteredClass() != null) {
            throw new EnrollmentException("Student is already registered in another class");
        }

        students.add(student);
        student.setRegisteredClass(this);
        return true;
    }

    @Override
    public void trackProgress() {
        System.out.println("Tracking reading, writing, and arithmetic skills in Top Class");
    }

    @Override
    public void conductActivity(String activityName) {
        activities.add(activityName);
        System.out.println("Conducted " + activityName + " activity in Top Class");
    }

    public void conductAssessment(String assessmentName) {
        assessments.add(assessmentName);
        System.out.println("Conducted " + assessmentName + " assessment in Top Class");
    }

    @Override
    public String generateClassReport() {
        StringBuilder report = new StringBuilder();
        report.append("TOP CLASS REPORT\n");
        report.append("Class ID: ").append(classId).append("\n");
        report.append("Teacher: ").append(assignedTeacher != null ? assignedTeacher.getTeacherName() : "Not assigned").append("\n");
        report.append("Students enrolled: ").append(students.size()).append("/").append(maxCapacity).append("\n");
        report.append("Activities conducted:\n");
        for (String activity : activities) {
            report.append("- ").append(activity).append("\n");
        }
        report.append("Assessments conducted:\n");
        for (String assessment : assessments) {
            report.append("- ").append(assessment).append("\n");
        }
        report.append("Progress: Preparing for primary school with reading, writing, and arithmetic\n");
        return report.toString();
    }
}