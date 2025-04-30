package NurserySchoolManagementSystem;

public class BabyClass extends NurseryClass {
    public BabyClass(String classId) {
        super(classId, "Baby Class", 15);
    }

    @Override
    public boolean enrollStudent(Student student) throws EnrollmentException {
        if (student.getAge() < 2 || student.getAge() > 3) {
            throw new EnrollmentException("Student age must be between 2-3 for Baby Class");
        }

        if (isAtCapacity()) {
            throw new EnrollmentException("Baby Class has reached maximum capacity of 15 students");
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
        System.out.println("Tracking motor skills and play-based learning progress for Baby Class");
    }

    @Override
    public void conductActivity(String activityName) {
        activities.add(activityName);
        System.out.println("Conducted " + activityName + " activity in Baby Class");
    }

    @Override
    public String generateClassReport() {
        StringBuilder report = new StringBuilder();
        report.append("BABY CLASS REPORT\n");
        report.append("Class ID: ").append(classId).append("\n");
        report.append("Teacher: ").append(assignedTeacher != null ? assignedTeacher.getTeacherName() : "Not assigned").append("\n");
        report.append("Students enrolled: ").append(students.size()).append("/").append(maxCapacity).append("\n");
        report.append("Activities conducted:\n");
        for (String activity : activities) {
            report.append("- ").append(activity).append("\n");
        }
        report.append("Progress: Tracking motor skills and play-based learning\n");
        return report.toString();
    }

    @Override
    public boolean assignTeacher(Teacher teacher) {
        if (teacher != null && teacher.getTeacherRole().equals("Early Childhood Educator")) {
            return super.assignTeacher(teacher);
        }
        return false;
    }
}
