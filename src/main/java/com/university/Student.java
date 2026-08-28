package com.university;

import java.util.ArrayList;
import java.util.List;
import com.university.exceptions.CourseEnrollmentException;
import com.university.exceptions.InvalidStudentException;

/**
 * Abstract base class representing a student.
 * Implements CourseManagement interface and defines common student functionality.
 * 
 * @author Student Management System
 * @version 1.0
 */
public abstract class Student implements CourseManagement {
    protected String studentId;
    protected String name;
    protected String email;
    protected int semester;
    protected List<Course> enrolledCourses;
    
    /**
     * Constructs a Student with basic information.
     * 
     * @param studentId the unique student identifier
     * @param name the student's full name
     * @param email the student's email address
     * @throws InvalidStudentException if data is invalid
     */
    public Student(String studentId, String name, String email) 
            throws InvalidStudentException {
        validateStudent(studentId, name, email);
        this.studentId = studentId;
        this.name = name;
        this.email = email;
        this.semester = 1;
        this.enrolledCourses = new ArrayList<>();
    }
    
    /**
     * Validates student data.
     * 
     * @param studentId the student ID
     * @param name the student name
     * @param email the student email
     * @throws InvalidStudentException if validation fails
     */
    private void validateStudent(String studentId, String name, String email) 
            throws InvalidStudentException {
        if (studentId == null || studentId.trim().isEmpty()) {
            throw new InvalidStudentException("Student ID cannot be empty");
        }
        if (name == null || name.trim().isEmpty()) {
            throw new InvalidStudentException("Student name cannot be empty");
        }
        if (email == null || !email.contains("@")) {
            throw new InvalidStudentException("Invalid email address: " + email);
        }
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
     * Gets the student name.
     * 
     * @return the student name
     */
    public String getName() {
        return name;
    }
    
    /**
     * Gets the student email.
     * 
     * @return the student email
     */
    public String getEmail() {
        return email;
    }
    
    /**
     * Gets the current semester.
     * 
     * @return the semester number
     */
    public int getSemester() {
        return semester;
    }
    
    /**
     * Sets the semester.
     * 
     * @param semester the semester number
     */
    public void setSemester(int semester) {
        if (semester > 0) {
            this.semester = semester;
        }
    }
    
    /**
     * Enrolls student in a course.
     * Implements CourseManagement interface.
     * 
     * @param course the course to enroll in
     * @throws CourseEnrollmentException if enrollment fails
     */
    @Override
    public void enrollCourse(Course course) throws CourseEnrollmentException {
        if (course == null) {
            throw new CourseEnrollmentException("Course cannot be null");
        }
        
        // Check if already enrolled
        for (Course enrolledCourse : enrolledCourses) {
            if (enrolledCourse.getCourseId().equals(course.getCourseId())) {
                throw new CourseEnrollmentException(
                    "Student already enrolled in course: " + course.getCourseId());
            }
        }
        
        // Check course capacity
        if (course.getEnrolledCount() >= course.getMaxCapacity()) {
            throw new CourseEnrollmentException(
                "Course " + course.getCourseId() + " is full");
        }
        
        // Enroll student
        try {
            course.enrollStudent(this);
            enrolledCourses.add(course);
        } catch (Exception e) {
            throw new CourseEnrollmentException("Enrollment failed: " + e.getMessage(), e);
        }
    }
    
    /**
     * Drops a course by course ID.
     * Implements CourseManagement interface.
     * 
     * @param courseId the ID of the course to drop
     * @throws CourseEnrollmentException if course not found
     */
    @Override
    public void dropCourse(String courseId) throws CourseEnrollmentException {
        Course courseToRemove = null;
        for (Course course : enrolledCourses) {
            if (course.getCourseId().equals(courseId)) {
                courseToRemove = course;
                break;
            }
        }
        
        if (courseToRemove == null) {
            throw new CourseEnrollmentException(
                "Student not enrolled in course: " + courseId);
        }
        
        enrolledCourses.remove(courseToRemove);
        courseToRemove.removeStudent(this);
    }
    
    /**
     * Gets list of enrolled courses.
     * Implements CourseManagement interface.
     * 
     * @return list of enrolled courses
     */
    @Override
    public List<Course> getEnrolledCourses() {
        return new ArrayList<>(enrolledCourses);
    }
    
    /**
     * Calculates the GPA for the student.
     * Abstract method to be implemented by subclasses.
     * 
     * @return the calculated GPA
     */
    public abstract double calculateGPA();
    
    /**
     * Displays student details.
     * Abstract method to be implemented by subclasses.
     */
    public abstract void displayDetails();
    
    /**
     * Gets student details as a formatted string.
     * 
     * @return formatted student details
     */
    public String getStudentDetails() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n--- Student Information ---\n");
        sb.append("Name: ").append(name).append("\n");
        sb.append("Student ID: ").append(studentId).append("\n");
        sb.append("Email: ").append(email).append("\n");
        sb.append("Semester: ").append(semester).append("\n");
        sb.append("GPA: ").append(String.format("%.2f", calculateGPA())).append("\n");
        sb.append("Enrolled Courses: ").append(enrolledCourses.size()).append("\n");
        
        if (!enrolledCourses.isEmpty()) {
            sb.append("Courses:\n");
            for (Course course : enrolledCourses) {
                sb.append("  - ").append(course.toString()).append("\n");
            }
        }
        
        return sb.toString();
    }
    
    /**
     * Gets string representation of the student.
     * 
     * @return string representation
     */
    @Override
    public String toString() {
        return name + " (" + studentId + ")";
    }
}
