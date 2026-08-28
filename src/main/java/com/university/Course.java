package com.university;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a course in the university system.
 * Manages course details and enrolled students.
 * 
 * @author Student Management System
 * @version 1.0
 */
public class Course {
    private String courseId;
    private String courseName;
    private int credits;
    private String instructor;
    private int maxCapacity;
    private List<Student> enrolledStudents;
    
    /**
     * Constructs a Course with specified parameters.
     * 
     * @param courseId the unique course identifier
     * @param courseName the name of the course
     * @param credits the number of credits
     * @param instructor the name of the instructor
     * @param maxCapacity the maximum enrollment capacity
     */
    public Course(String courseId, String courseName, int credits, 
                  String instructor, int maxCapacity) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.credits = credits;
        this.instructor = instructor;
        this.maxCapacity = maxCapacity;
        this.enrolledStudents = new ArrayList<>();
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
     * Gets the course name.
     * 
     * @return the course name
     */
    public String getCourseName() {
        return courseName;
    }
    
    /**
     * Gets the number of credits.
     * 
     * @return the credits
     */
    public int getCredits() {
        return credits;
    }
    
    /**
     * Gets the instructor name.
     * 
     * @return the instructor name
     */
    public String getInstructor() {
        return instructor;
    }
    
    /**
     * Gets the maximum capacity.
     * 
     * @return the max capacity
     */
    public int getMaxCapacity() {
        return maxCapacity;
    }
    
    /**
     * Gets the current enrollment count.
     * 
     * @return the number of enrolled students
     */
    public int getEnrolledCount() {
        return enrolledStudents.size();
    }
    
    /**
     * Enrolls a student in the course.
     * 
     * @param student the student to enroll
     * @throws Exception if course is full
     */
    public void enrollStudent(Student student) throws Exception {
        if (enrolledStudents.size() >= maxCapacity) {
            throw new Exception("Course " + courseId + " is full. Maximum capacity: " + maxCapacity);
        }
        if (!enrolledStudents.contains(student)) {
            enrolledStudents.add(student);
        }
    }
    
    /**
     * Removes a student from the course.
     * 
     * @param student the student to remove
     */
    public void removeStudent(Student student) {
        enrolledStudents.remove(student);
    }
    
    /**
     * Gets the list of enrolled students.
     * 
     * @return list of enrolled students
     */
    public List<Student> getEnrolledStudents() {
        return new ArrayList<>(enrolledStudents);
    }
    
    /**
     * Gets course details as a formatted string.
     * 
     * @return formatted course details
     */
    public String getDetails() {
        return String.format("%s: %s (Credits: %d, Instructor: %s, Enrolled: %d/%d)",
                courseId, courseName, credits, instructor, 
                enrolledStudents.size(), maxCapacity);
    }
    
    /**
     * Gets string representation of the course.
     * 
     * @return string representation
     */
    @Override
    public String toString() {
        return courseId + ": " + courseName + " (" + credits + " credits)";
    }
}
