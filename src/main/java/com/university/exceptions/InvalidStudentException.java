package com.university.exceptions;

/**
 * Custom exception thrown when student data is invalid.
 * 
 * @author Student Management System
 * @version 1.0
 */
public class InvalidStudentException extends Exception {
    
    /**
     * Constructs an InvalidStudentException with specified error message.
     * 
     * @param message the detail message
     */
    public InvalidStudentException(String message) {
        super(message);
    }
    
    /**
     * Constructs an InvalidStudentException with message and cause.
     * 
     * @param message the detail message
     * @param cause the cause of the exception
     */
    public InvalidStudentException(String message, Throwable cause) {
        super(message, cause);
    }
}
