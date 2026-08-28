package com.university;

import com.university.exceptions.InvalidStudentException;

/**
 * Represents an undergraduate student.
 * Extends Student class with undergraduate-specific functionality.
 * 
 * @author Student Management System
 * @version 1.0
 */
public class UndergraduateStudent extends Student {
    private String major;
    
    /**
     * Constructs an UndergraduateStudent with personal and academic information.
     * 
     * @param studentId the unique student identifier
     * @param name the student's full name
     * @param email the student's email address
     * @param major the student's major
     * @throws InvalidStudentException if data is invalid
     */
    public UndergraduateStudent(String studentId, String name, String email, String major) 
            throws InvalidStudentException {
        super(studentId, name, email);
        if (major == null || major.trim().isEmpty()) {
            throw new InvalidStudentException("Major cannot be empty");
        }
        this.major = major;
    }
    
    /**
     * Gets the major.
     * 
     * @return the student's major
     */
    public String getMajor() {
        return major;
    }
    
    /**
     * Sets the major.
     * 
     * @param major the new major
     */
    public void setMajor(String major) {
        if (major != null && !major.trim().isEmpty()) {
            this.major = major;
        }
    }
    
    /**
     * Calculates GPA for undergraduate student.
     * GPA = Sum of (grade point * course credits) / Total credits
     * 
     * @return the calculated GPA (0.0 to 4.0 scale)
     */
    @Override
    public double calculateGPA() {
        if (enrolledCourses.isEmpty()) {
            return 0.0;
        }
        
        // For demonstration, calculate based on typical grading
        double totalWeightedGrade = 0.0;
        int totalCredits = 0;
        
        for (Course course : enrolledCourses) {
            // Simulating grades: generate consistent grades based on course ID
            double gradePoint = getSimulatedGradePoint(course.getCourseId());
            totalWeightedGrade += gradePoint * course.getCredits();
            totalCredits += course.getCredits();
        }
        
        if (totalCredits == 0) {
            return 0.0;
        }
        
        double gpa = totalWeightedGrade / totalCredits;
        // Cap at 4.0
        return Math.min(gpa, 4.0);
    }
    
    /**
     * Gets simulated grade point based on course ID for demonstration.
     * 
     * @param courseId the course ID
     * @return simulated grade point
     */
    private double getSimulatedGradePoint(String courseId) {
        // Deterministic simulation based on course ID hash
        int hash = courseId.hashCode();
        double[] grades = {4.0, 3.8, 3.6, 3.4, 3.2, 3.0, 2.8};
        return grades[Math.abs(hash % grades.length)];
    }
    
    /**
     * Displays undergraduate student details.
     */
    @Override
    public void displayDetails() {
        System.out.println("\n===== UNDERGRADUATE STUDENT =====" );
        System.out.println("Name: " + name);
        System.out.println("Student ID: " + studentId);
        System.out.println("Email: " + email);
        System.out.println("Major: " + major);
        System.out.println("Semester: " + semester);
        System.out.printf("Current GPA: %.2f%n", calculateGPA());
        System.out.println("\nEnrolled Courses: " + enrolledCourses.size());
        for (Course course : enrolledCourses) {
            System.out.println("  - " + course.getDetails());
        }
        System.out.println("==================================\n");
    }
    
    /**
     * Gets string representation of undergraduate student.
     * 
     * @return string representation
     */
    @Override
    public String toString() {
        return "Undergraduate - " + name + " (" + studentId + ") - Major: " + major;
    }
}
