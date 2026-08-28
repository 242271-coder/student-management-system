package com.university;

import java.util.List;

/**
 * Interface that defines course management operations.
 * Implements the contract for enrollment and course management.
 * 
 * @author Student Management System
 * @version 1.0
 */
public interface CourseManagement {
    
    /**
     * Enrolls a student in a course.
     * 
     * @param course the course to enroll in
     * @throws Exception if enrollment fails
     */
    void enrollCourse(Course course) throws Exception;
    
    /**
     * Drops a course by course ID.
     * 
     * @param courseId the ID of the course to drop
     * @throws Exception if course not found
     */
    void dropCourse(String courseId) throws Exception;
    
    /**
     * Gets list of all enrolled courses.
     * 
     * @return list of enrolled courses
     */
    List<Course> getEnrolledCourses();
}
