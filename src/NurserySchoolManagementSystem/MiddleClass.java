package NurserySchoolManagementSystem;

public class MiddleClass extends NurseryClass {
    public MiddleClass(String classId) {
        super(classId, "Middle Class", 20);
    }

    @Override
    public boolean enrollStudent(Student student) throws EnrollmentException {
        if (student.getAge() < 3 || student.getAge() > 4) {
            throw new EnrollmentException("Student age must be between 3-4 for Middle Class");
        }

        if (isAtCapacity()) {
            throw new EnrollmentException("Middle Class has reached maximum capacity of 20 students");
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
        System.out.println("Tracking language development and basic counting in Middle Class");
    }

    @Override
    public void conductActivity(String activityName) {
        activities.add(activityName);
        System.out.println("Conducted " + activityName + " activity in Middle Class");
    }

    @Override
    public String generateClassReport() {
        StringBuilder report = new StringBuilder();
        report.append("MIDDLE CLASS REPORT\n");
        report.append("Class ID: ").append(classId).append("\n");
        report.append("Teacher: ").append(assignedTeacher != null ? assignedTeacher.getTeacherName() : "Not assigned").append("\n");
        report.append("Students enrolled: ").append(students.size()).append("/").append(maxCapacity).append("\n");
        report.append("Activities conducted:\n");
        for (String activity : activities) {
            report.append("- ").append(activity).append("\n");
        }
        report.append("Progress: Focusing on language development and basic counting\n");
        return report.toString();
    }
}
