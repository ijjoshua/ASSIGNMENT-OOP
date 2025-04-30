package NurserySchoolManagementSystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NurserySchoolManagementSystem {
    private static Scanner scanner = new Scanner(System.in);
    private static List<NurseryClass> classes = new ArrayList<>();
    private static List<Teacher> teachers = new ArrayList<>();
    private static List<Student> students = new ArrayList<>();

    public static void main(String[] args) {
        initializeSampleData();

        while (true) {
            System.out.println("\n=== Nursery School Management System ===");
            System.out.println("1. Create Class");
            System.out.println("2. Register Teacher");
            System.out.println("3. Enroll Student");
            System.out.println("4. Assign Teacher to Class");
            System.out.println("5. Conduct Class Activity");
            System.out.println("6. Conduct Top Class Assessment");
            System.out.println("7. Generate Class Report");
            System.out.println("8. Track Class Progress");
            System.out.println("9. Exit");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    createClass();
                    break;
                case 2:
                    registerTeacher();
                    break;
                case 3:
                    enrollStudent();
                    break;
                case 4:
                    assignTeacherToClass();
                    break;
                case 5:
                    conductActivity();
                    break;
                case 6:
                    conductAssessment();
                    break;
                case 7:
                    generateReport();
                    break;
                case 8:
                    trackProgress();
                    break;
                case 9:
                    System.out.println("Exiting system...");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void initializeSampleData() {
        // Sample teachers
        teachers.add(new Teacher("T001", "Ms. Johnson", "Early Childhood Educator"));
        teachers.add(new Teacher("T002", "Mr. Smith", "Assistant"));
        teachers.add(new Teacher("T003", "Mrs. Williams", "Lead Teacher"));

        // Sample classes
        classes.add(new BabyClass("BC001"));
        classes.add(new MiddleClass("MC001"));
        classes.add(new TopClass("TC001"));

        // Sample students
        students.add(new Student("S001", "Alice Brown", 2, "Mary Brown"));
        students.add(new Student("S002", "Bob Green", 3, "John Green"));
        students.add(new Student("S003", "Charlie White", 4, "Sarah White"));
    }

    private static void createClass() {
        System.out.println("\n=== Create New Class ===");
        System.out.println("1. Baby Class");
        System.out.println("2. Middle Class");
        System.out.println("3. Top Class");
        System.out.print("Select class type: ");

        int type = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter Class ID: ");
        String classId = scanner.nextLine();

        NurseryClass newClass = null;
        switch (type) {
            case 1:
                newClass = new BabyClass(classId);
                break;
            case 2:
                newClass = new MiddleClass(classId);
                break;
            case 3:
                newClass = new TopClass(classId);
                break;
            default:
                System.out.println("Invalid class type.");
                return;
        }

        classes.add(newClass);
        System.out.println("Class created successfully!");
    }

    private static void registerTeacher() {
        System.out.println("\n=== Register New Teacher ===");
        System.out.print("Enter Teacher ID: ");
        String teacherId = scanner.nextLine();

        System.out.print("Enter Teacher Name: ");
        String teacherName = scanner.nextLine();

        System.out.print("Enter Teacher Role: ");
        String teacherRole = scanner.nextLine();

        Teacher teacher = new Teacher(teacherId, teacherName, teacherRole);
        teachers.add(teacher);
        System.out.println("Teacher registered successfully!");
    }

    private static void enrollStudent() {
        System.out.println("\n=== Enroll New Student ===");
        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine();

        System.out.print("Enter Student Name: ");
        String studentName = scanner.nextLine();

        System.out.print("Enter Student Age: ");
        int age = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter Guardian Name: ");
        String guardianName = scanner.nextLine();

        Student student = new Student(studentId, studentName, age, guardianName);

        System.out.println("Available Classes:");
        for (int i = 0; i < classes.size(); i++) {
            System.out.println((i+1) + ". " + classes.get(i).getClassName() + " (" + classes.get(i).getClassId() + ")");
        }
        System.out.print("Select class to enroll in: ");
        int classIndex = scanner.nextInt() - 1;
        scanner.nextLine(); // Consume newline

        if (classIndex < 0 || classIndex >= classes.size()) {
            System.out.println("Invalid class selection.");
            return;
        }

        try {
            boolean success = classes.get(classIndex).enrollStudent(student);
            if (success) {
                students.add(student);
                System.out.println("Student enrolled successfully!");
            }
        } catch (EnrollmentException e) {
            System.out.println("Enrollment failed: " + e.getMessage());
        }
    }

    private static void assignTeacherToClass() {
        System.out.println("\n=== Assign Teacher to Class ===");

        System.out.println("Available Teachers:");
        for (int i = 0; i < teachers.size(); i++) {
            System.out.println((i+1) + ". " + teachers.get(i).getTeacherName() +
                    " (" + teachers.get(i).getTeacherRole() + ")");
        }
        System.out.print("Select teacher: ");
        int teacherIndex = scanner.nextInt() - 1;
        scanner.nextLine(); // Consume newline

        if (teacherIndex < 0 || teacherIndex >= teachers.size()) {
            System.out.println("Invalid teacher selection.");
            return;
        }

        System.out.println("Available Classes:");
        for (int i = 0; i < classes.size(); i++) {
            System.out.println((i+1) + ". " + classes.get(i).getClassName() + " (" + classes.get(i).getClassId() + ")");
        }
        System.out.print("Select class: ");
        int classIndex = scanner.nextInt() - 1;
        scanner.nextLine(); // Consume newline

        if (classIndex < 0 || classIndex >= classes.size()) {
            System.out.println("Invalid class selection.");
            return;
        }

        boolean success = classes.get(classIndex).assignTeacher(teachers.get(teacherIndex));
        if (success) {
            System.out.println("Teacher assigned successfully!");
        } else {
            System.out.println("Assignment failed. Teacher may not have the required role for this class.");
        }
    }

    private static void conductActivity() {
        System.out.println("\n=== Conduct Class Activity ===");
        System.out.println("Available Classes:");
        for (int i = 0; i < classes.size(); i++) {
            System.out.println((i+1) + ". " + classes.get(i).getClassName() + " (" + classes.get(i).getClassId() + ")");
        }
        System.out.print("Select class: ");
        int classIndex = scanner.nextInt() - 1;
        scanner.nextLine(); // Consume newline

        if (classIndex < 0 || classIndex >= classes.size()) {
            System.out.println("Invalid class selection.");
            return;
        }

        System.out.print("Enter activity name: ");
        String activityName = scanner.nextLine();

        classes.get(classIndex).conductActivity(activityName);
    }

    private static void conductAssessment() {
        System.out.println("\n=== Conduct Top Class Assessment ===");
        // Find Top Classes
        List<TopClass> topClasses = new ArrayList<>();
        for (NurseryClass nc : classes) {
            if (nc instanceof TopClass) {
                topClasses.add((TopClass) nc);
            }
        }

        if (topClasses.isEmpty()) {
            System.out.println("No Top Classes available.");
            return;
        }

        System.out.println("Available Top Classes:");
        for (int i = 0; i < topClasses.size(); i++) {
            System.out.println((i+1) + ". " + topClasses.get(i).getClassId());
        }
        System.out.print("Select class: ");
        int classIndex = scanner.nextInt() - 1;
        scanner.nextLine(); // Consume newline

        if (classIndex < 0 || classIndex >= topClasses.size()) {
            System.out.println("Invalid class selection.");
            return;
        }

        System.out.print("Enter assessment name: ");
        String assessmentName = scanner.nextLine();

        topClasses.get(classIndex).conductAssessment(assessmentName);
    }

    private static void generateReport() {
        System.out.println("\n=== Generate Class Report ===");
        System.out.println("Available Classes:");
        for (int i = 0; i < classes.size(); i++) {
            System.out.println((i+1) + ". " + classes.get(i).getClassName() + " (" + classes.get(i).getClassId() + ")");
        }
        System.out.print("Select class: ");
        int classIndex = scanner.nextInt() - 1;
        scanner.nextLine(); // Consume newline

        if (classIndex < 0 || classIndex >= classes.size()) {
            System.out.println("Invalid class selection.");
            return;
        }

        System.out.println(classes.get(classIndex).generateClassReport());
    }

    private static void trackProgress() {
        System.out.println("\n=== Track Class Progress ===");
        System.out.println("Available Classes:");
        for (int i = 0; i < classes.size(); i++) {
            System.out.println((i+1) + ". " + classes.get(i).getClassName() + " (" + classes.get(i).getClassId() + ")");
        }
        System.out.print("Select class: ");
        int classIndex = scanner.nextInt() - 1;
        scanner.nextLine(); // Consume newline

        if (classIndex < 0 || classIndex >= classes.size()) {
            System.out.println("Invalid class selection.");
            return;
        }

        classes.get(classIndex).trackProgress();
    }
}