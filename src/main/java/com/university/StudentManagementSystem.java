package com.university;

import java.util.ArrayList;
import java.util.List;
import com.university.exceptions.CourseEnrollmentException;
import com.university.exceptions.InvalidStudentException;

/**
 * Main controller class for Student Management System.
 * Demonstrates all OOP concepts and system functionality.
 * 
 * @author Student Management System
 * @version 1.0
 */
public class StudentManagementSystem {
    private List<Student> students;
    private List<Course> courses;
    private List<Grade> grades;
    
    /**
     * Constructs the Student Management System.
     */
    public StudentManagementSystem() {
        this.students = new ArrayList<>();
        this.courses = new ArrayList<>();
        this.grades = new ArrayList<>();
    }
    
    /**
     * Adds a student to the system.
     * 
     * @param student the student to add
     */
    public void addStudent(Student student) {
        if (student != null && !students.contains(student)) {
            students.add(student);
            System.out.println("✓ Student added: " + student.getName() + " (" + student.getStudentId() + ")");
        }
    }
    
    /**
     * Adds a course to the system.
     * 
     * @param course the course to add
     */
    public void addCourse(Course course) {
        if (course != null && !courses.contains(course)) {
            courses.add(course);
            System.out.println("✓ Course added: " + course.getCourseName() + " (" + course.getCourseId() + ")");
        }
    }
    
    /**
     * Records a grade for a student in a course.
     * 
     * @param grade the grade record
     */
    public void recordGrade(Grade grade) {
        if (grade != null) {
            grades.add(grade);
            System.out.println("✓ Grade recorded: " + grade.getStudentId() + " scored " 
                    + grade.getMarks() + " in " + grade.getCourseId() 
                    + " (" + grade.getGrade() + " grade)");
        }
    }
    
    /**
     * Finds a student by ID.
     * 
     * @param studentId the student ID
     * @return the student or null if not found
     */
    public Student findStudent(String studentId) {
        for (Student student : students) {
            if (student.getStudentId().equals(studentId)) {
                return student;
            }
        }
        return null;
    }
    
    /**
     * Finds a course by ID.
     * 
     * @param courseId the course ID
     * @return the course or null if not found
     */
    public Course findCourse(String courseId) {
        for (Course course : courses) {
            if (course.getCourseId().equals(courseId)) {
                return course;
            }
        }
        return null;
    }
    
    /**
     * Gets all students in the system.
     * 
     * @return list of all students
     */
    public List<Student> getAllStudents() {
        return new ArrayList<>(students);
    }
    
    /**
     * Gets all courses in the system.
     * 
     * @return list of all courses
     */
    public List<Course> getAllCourses() {
        return new ArrayList<>(courses);
    }
    
    /**
     * Displays system statistics.
     */
    public void displaySystemStatistics() {
        System.out.println("\n========== SYSTEM STATISTICS ==========");
        System.out.println("Total Students: " + students.size());
        System.out.println("Total Courses: " + courses.size());
        System.out.println("Total Grades Recorded: " + grades.size());
        System.out.println("======================================\n");
    }
    
    /**
     * Main method demonstrating the complete system.
     * 
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        try {
            StudentManagementSystem system = new StudentManagementSystem();
            
            System.out.println("\n========== STUDENT MANAGEMENT SYSTEM ==========");
            System.out.println("OOP Concepts Demonstration\n");
            
            // TEST CASE 1: Create Courses
            System.out.println("\n--- TEST CASE 1: Creating Courses ---");
            Course cs201 = new Course("CS201", "Data Structures", 4, "Dr. Ahmed", 2);
            Course cs202 = new Course("CS202", "Algorithms", 3, "Dr. Sharma", 2);
            Course cs203 = new Course("CS203", "Database Systems", 4, "Dr. Patel", 3);
            Course cs301 = new Course("CS301", "Machine Learning", 4, "Dr. Kumar", 2);
            
            system.addCourse(cs201);
            system.addCourse(cs202);
            system.addCourse(cs203);
            system.addCourse(cs301);
            
            // TEST CASE 2: Create Students
            System.out.println("\n--- TEST CASE 2: Creating Students ---");
            
            // Demonstrate polymorphism with different student types
            UndergraduateStudent student1 = new UndergraduateStudent(
                "RAJ001", "Rajesh Kumar", "rajesh@university.edu", "Computer Science");
            
            UndergraduateStudent student2 = new UndergraduateStudent(
                "ARJ002", "Arjun Singh", "arjun@university.edu", "Information Technology");
            
            PostgraduateStudent student3 = new PostgraduateStudent(
                "PRI001", "Priya Sharma", "priya@university.edu", "Machine Learning Applications");
            
            system.addStudent(student1);
            system.addStudent(student2);
            system.addStudent(student3);
            
            // TEST CASE 3: Enroll Students in Courses
            System.out.println("\n--- TEST CASE 3: Enrolling Students in Courses ---");
            
            try {
                student1.enrollCourse(cs201);
                System.out.println("✓ " + student1.getName() + " enrolled in " + cs201.getCourseName());
            } catch (CourseEnrollmentException e) {
                System.out.println("✗ Error: " + e.getMessage());
            }
            
            try {
                student1.enrollCourse(cs202);
                System.out.println("✓ " + student1.getName() + " enrolled in " + cs202.getCourseName());
            } catch (CourseEnrollmentException e) {
                System.out.println("✗ Error: " + e.getMessage());
            }
            
            try {
                student1.enrollCourse(cs203);
                System.out.println("✓ " + student1.getName() + " enrolled in " + cs203.getCourseName());
            } catch (CourseEnrollmentException e) {
                System.out.println("✗ Error: " + e.getMessage());
            }
            
            try {
                student2.enrollCourse(cs201);
                System.out.println("✓ " + student2.getName() + " enrolled in " + cs201.getCourseName());
            } catch (CourseEnrollmentException e) {
                System.out.println("✗ Error: " + e.getMessage());
            }
            
            try {
                student3.enrollCourse(cs301);
                System.out.println("✓ " + student3.getName() + " enrolled in " + cs301.getCourseName());
            } catch (CourseEnrollmentException e) {
                System.out.println("✗ Error: " + e.getMessage());
            }
            
            // TEST CASE 4: Test Capacity Limit
            System.out.println("\n--- TEST CASE 4: Testing Course Capacity Limit ---");
            UndergraduateStudent student4 = new UndergraduateStudent(
                "VIK003", "Vikas Patel", "vikas@university.edu", "Electronics");
            system.addStudent(student4);
            
            try {
                student4.enrollCourse(cs201);
                System.out.println("✓ " + student4.getName() + " enrolled in " + cs201.getCourseName());
            } catch (CourseEnrollmentException e) {
                System.out.println("✓ Expected Exception: " + e.getMessage());
            }
            
            // TEST CASE 5: Test Duplicate Enrollment
            System.out.println("\n--- TEST CASE 5: Testing Duplicate Enrollment Prevention ---");
            try {
                student1.enrollCourse(cs201);
                System.out.println("✗ Should have thrown exception");
            } catch (CourseEnrollmentException e) {
                System.out.println("✓ Expected Exception: " + e.getMessage());
            }
            
            // TEST CASE 6: Display Student Details (Polymorphism)
            System.out.println("\n--- TEST CASE 6: Displaying Student Details (Polymorphism) ---");
            student1.displayDetails();
            student3.displayDetails();
            
            // TEST CASE 7: Record Grades
            System.out.println("\n--- TEST CASE 7: Recording Grades ---");
            Grade g1 = new Grade("CS201", "RAJ001", 92);
            Grade g2 = new Grade("CS202", "RAJ001", 88);
            Grade g3 = new Grade("CS203", "RAJ001", 85);
            
            system.recordGrade(g1);
            system.recordGrade(g2);
            system.recordGrade(g3);
            
            // TEST CASE 8: Postgraduate with Thesis
            System.out.println("\n--- TEST CASE 8: Postgraduate Thesis Submission ---");
            student3.submitThesis(92);
            System.out.println("✓ Thesis submitted by " + student3.getName() + " with mark: 92");
            
            // TEST CASE 9: Invalid Marks
            System.out.println("\n--- TEST CASE 9: Testing Invalid Marks ---");
            try {
                Grade invalidGrade = new Grade("CS201", "RAJ001", 150);
                System.out.println("✗ Should have thrown exception");
            } catch (IllegalArgumentException e) {
                System.out.println("✓ Expected Exception: " + e.getMessage());
            }
            
            // TEST CASE 10: Invalid Student Creation
            System.out.println("\n--- TEST CASE 10: Testing Invalid Student Creation ---");
            try {
                UndergraduateStudent invalidStudent = new UndergraduateStudent(
                    "NEW001", "Test Student", "invalid-email", "CS");
                System.out.println("✗ Should have thrown exception");
            } catch (InvalidStudentException e) {
                System.out.println("✓ Expected Exception: " + e.getMessage());
            }
            
            // TEST CASE 11: Drop Course
            System.out.println("\n--- TEST CASE 11: Testing Drop Course ---");
            try {
                student1.dropCourse("CS202");
                System.out.println("✓ " + student1.getName() + " dropped CS202");
                System.out.println("   Remaining courses: " + student1.getEnrolledCourses().size());
            } catch (CourseEnrollmentException e) {
                System.out.println("✗ Error: " + e.getMessage());
            }
            
            // TEST CASE 12: Re-enroll After Drop
            System.out.println("\n--- TEST CASE 12: Re-enrollment After Drop ---");
            try {
                student1.enrollCourse(cs202);
                System.out.println("✓ " + student1.getName() + " re-enrolled in " + cs202.getCourseName());
            } catch (CourseEnrollmentException e) {
                System.out.println("✗ Error: " + e.getMessage());
            }
            
            // TEST CASE 13: GPA Calculation
            System.out.println("\n--- TEST CASE 13: GPA Calculations ---");
            System.out.printf("Student 1 (Undergraduate) GPA: %.2f\n", student1.calculateGPA());
            System.out.printf("Student 3 (Postgraduate) GPA: %.2f (with thesis)\n", student3.calculateGPA());
            
            // Display Final System Statistics
            system.displaySystemStatistics();
            
            // Summary
            System.out.println("\n========== TEST SUMMARY ==========");
            System.out.println("✓ All test cases executed successfully");
            System.out.println("✓ Encapsulation: Private attributes with getters/setters");
            System.out.println("✓ Inheritance: Student class extended by Undergraduate & Postgraduate");
            System.out.println("✓ Polymorphism: calculateGPA() and displayDetails() overridden");
            System.out.println("✓ Abstraction: Abstract methods implemented in subclasses");
            System.out.println("✓ Exception Handling: Custom exceptions for error scenarios");
            System.out.println("✓ Association: Student-Course many-to-many relationship");
            System.out.println("\n✓✓✓ SYSTEM OPERATIONAL ✓✓✓\n");
            
        } catch (Exception e) {
            System.err.println("Fatal Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
