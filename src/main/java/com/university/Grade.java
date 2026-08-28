package com.university;

/**
 * Represents a grade record for a student in a course.
 * Manages marks and grade calculations.
 * 
 * @author Student Management System
 * @version 1.0
 */
public class Grade {
    private String courseId;
    private String studentId;
    private double marks;
    private char grade;
    
    /**
     * Constructs a Grade record with marks.
     * 
     * @param courseId the course ID
     * @param studentId the student ID
     * @param marks the marks obtained
     * @throws IllegalArgumentException if marks are invalid
     */
    public Grade(String courseId, String studentId, double marks) 
            throws IllegalArgumentException {
        if (marks < 0 || marks > 100) {
            throw new IllegalArgumentException("Marks must be between 0 and 100. Got: " + marks);
        }
        this.courseId = courseId;
        this.studentId = studentId;
        this.marks = marks;
        this.grade = calculateGrade();
    }
    
    /**
     * Gets the course ID.
     * 
     * @return the course ID
     */
    public String getCourseId() {
        return courseId;
    }
    
    /**
     * Gets the student ID.
     * 
     * @return the student ID
     */
    public String getStudentId() {
        return studentId;
    }
    
    /**
     * Gets the marks.
     * 
     * @return the marks obtained
     */
    public double getMarks() {
        return marks;
    }
    
    /**
     * Gets the letter grade.
     * 
     * @return the letter grade (A, B, C, D, F)
     */
    public char getGrade() {
        return grade;
    }
    
    /**
     * Calculates the letter grade based on marks.
     * Grade Scale:
     * A: 90-100
     * B: 80-89
     * C: 70-79
     * D: 60-69
     * F: 0-59
     * 
     * @return the calculated letter grade
     */
    private char calculateGrade() {
        if (marks >= 90) return 'A';
        if (marks >= 80) return 'B';
        if (marks >= 70) return 'C';
        if (marks >= 60) return 'D';
        return 'F';
    }
    
    /**
     * Converts grade to grade point (4.0 scale).
     * A: 4.0, B: 3.0, C: 2.0, D: 1.0, F: 0.0
     * 
     * @return the grade point
     */
    public double getGradePoint() {
        switch (grade) {
            case 'A': return 4.0;
            case 'B': return 3.0;
            case 'C': return 2.0;
            case 'D': return 1.0;
            case 'F': return 0.0;
            default: return 0.0;
        }
    }
    
    /**
     * Gets the grade details as a formatted string.
     * 
     * @return formatted grade details
     */
    public String getDetails() {
        return String.format("%s - %s: %.2f marks = %c grade (%.2f points)",
                courseId, studentId, marks, grade, getGradePoint());
    }
    
    /**
     * Gets string representation of the grade.
     * 
     * @return string representation
     */
    @Override
    public String toString() {
        return studentId + " - " + courseId + ": " + marks + " (" + grade + ")";
    }
}
