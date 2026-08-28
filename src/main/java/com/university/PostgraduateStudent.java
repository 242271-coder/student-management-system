package com.university;

import com.university.exceptions.InvalidStudentException;

/**
 * Represents a postgraduate student.
 * Extends Student class with postgraduate-specific functionality including thesis tracking.
 * 
 * @author Student Management System
 * @version 1.0
 */
public class PostgraduateStudent extends Student {
    private String researchTopic;
    private double thesisMark;
    private boolean thesisSubmitted;
    
    /**
     * Constructs a PostgraduateStudent with personal and academic information.
     * 
     * @param studentId the unique student identifier
     * @param name the student's full name
     * @param email the student's email address
     * @param researchTopic the research topic
     * @throws InvalidStudentException if data is invalid
     */
    public PostgraduateStudent(String studentId, String name, String email, String researchTopic) 
            throws InvalidStudentException {
        super(studentId, name, email);
        if (researchTopic == null || researchTopic.trim().isEmpty()) {
            throw new InvalidStudentException("Research topic cannot be empty");
        }
        this.researchTopic = researchTopic;
        this.thesisMark = 0.0;
        this.thesisSubmitted = false;
    }
    
    /**
     * Gets the research topic.
     * 
     * @return the research topic
     */
    public String getResearchTopic() {
        return researchTopic;
    }
    
    /**
     * Sets the research topic.
     * 
     * @param researchTopic the new research topic
     */
    public void setResearchTopic(String researchTopic) {
        if (researchTopic != null && !researchTopic.trim().isEmpty()) {
            this.researchTopic = researchTopic;
        }
    }
    
    /**
     * Gets the thesis mark.
     * 
     * @return the thesis mark
     */
    public double getThesisMark() {
        return thesisMark;
    }
    
    /**
     * Submits thesis with mark.
     * 
     * @param mark the thesis mark (0-100)
     * @throws IllegalArgumentException if mark is invalid
     */
    public void submitThesis(double mark) throws IllegalArgumentException {
        if (mark < 0 || mark > 100) {
            throw new IllegalArgumentException("Thesis mark must be between 0 and 100. Got: " + mark);
        }
        this.thesisMark = mark;
        this.thesisSubmitted = true;
    }
    
    /**
     * Checks if thesis is submitted.
     * 
     * @return true if thesis submitted, false otherwise
     */
    public boolean isThesisSubmitted() {
        return thesisSubmitted;
    }
    
    /**
     * Calculates GPA for postgraduate student.
     * GPA = (Sum of (grade point * course credits) + thesis mark weighted) / (Total credits + thesis weight)
     * Thesis contributes 20% weightage
     * 
     * @return the calculated GPA (0.0 to 4.0 scale)
     */
    @Override
    public double calculateGPA() {
        double courseGPA = 0.0;
        int totalCredits = 0;
        
        // Calculate course-based GPA
        if (!enrolledCourses.isEmpty()) {
            double totalWeightedGrade = 0.0;
            
            for (Course course : enrolledCourses) {
                double gradePoint = getSimulatedGradePoint(course.getCourseId());
                totalWeightedGrade += gradePoint * course.getCredits();
                totalCredits += course.getCredits();
            }
            
            if (totalCredits > 0) {
                courseGPA = totalWeightedGrade / totalCredits;
            }
        }
        
        // Include thesis mark if submitted (20% weightage)
        if (thesisSubmitted && totalCredits > 0) {
            double thesisGradePoint = convertThesisMarkToGradePoint(thesisMark);
            double weightedCourseGPA = courseGPA * 0.8;
            double weightedThesisGPA = thesisGradePoint * 0.2;
            double finalGPA = weightedCourseGPA + weightedThesisGPA;
            return Math.min(finalGPA, 4.0);
        }
        
        return Math.min(courseGPA, 4.0);
    }
    
    /**
     * Converts thesis mark to grade point.
     * 
     * @param mark the thesis mark
     * @return the grade point
     */
    private double convertThesisMarkToGradePoint(double mark) {
        if (mark >= 90) return 4.0;
        if (mark >= 80) return 3.0;
        if (mark >= 70) return 2.0;
        if (mark >= 60) return 1.0;
        return 0.0;
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
        double[] grades = {4.0, 3.9, 3.8, 3.7, 3.6, 3.5, 3.4};
        return grades[Math.abs(hash % grades.length)];
    }
    
    /**
     * Displays postgraduate student details.
     */
    @Override
    public void displayDetails() {
        System.out.println("\n===== POSTGRADUATE STUDENT =====");
        System.out.println("Name: " + name);
        System.out.println("Student ID: " + studentId);
        System.out.println("Email: " + email);
        System.out.println("Research Topic: " + researchTopic);
        System.out.println("Semester: " + semester);
        System.out.printf("Current GPA: %.2f%n", calculateGPA());
        System.out.println("Thesis Submitted: " + (thesisSubmitted ? "Yes" : "No"));
        if (thesisSubmitted) {
            System.out.printf("Thesis Mark: %.2f%n", thesisMark);
        }
        System.out.println("\nEnrolled Courses: " + enrolledCourses.size());
        for (Course course : enrolledCourses) {
            System.out.println("  - " + course.getDetails());
        }
        System.out.println("================================\n");
    }
    
    /**
     * Gets string representation of postgraduate student.
     * 
     * @return string representation
     */
    @Override
    public String toString() {
        return "Postgraduate - " + name + " (" + studentId + ") - Topic: " + researchTopic;
    }
}
