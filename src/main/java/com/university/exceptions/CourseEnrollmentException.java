package com.university.exceptions;

/**
 * Custom exception thrown when course enrollment operations fail.
 * 
 * @author Student Management System
 * @version 1.0
 */
public class CourseEnrollmentException extends Exception {
    
    /**
     * Constructs a CourseEnrollmentException with specified error message.
     * 
     * @param message the detail message
     */
    public CourseEnrollmentException(String message) {
        super(message);
    }
    
    /**
     * Constructs a CourseEnrollmentException with message and cause.
     * 
     * @param message the detail message
     * @param cause the cause of the exception
     */
    public CourseEnrollmentException(String message, Throwable cause) {
        super(message, cause);
    }
}
