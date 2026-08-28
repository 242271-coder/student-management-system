package com.university;

import com.university.exceptions.CourseEnrollmentException;
import com.university.exceptions.InvalidStudentException;

/**
 * Test class for Course Enrollment functionality.
 * Tests enrollment, capacity management, and course operations.
 * 
 * @author Student Management System
 * @version 1.0
 */
public class CourseEnrollmentTest {
    private static int passCount = 0;
    private static int failCount = 0;
    
    /**
     * Main test runner.
     * 
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        System.out.println("\n========== COURSE ENROLLMENT TEST SUITE ==========");
        
        testValidEnrollment();
        testCapacityManagement();
        testDuplicateEnrollment();
        testDropCourse();
        testInvalidCourse();
        
        printSummary();
    }
    
    /**
     * Test valid course enrollment.
     */
    private static void testValidEnrollment() {
        System.out.println("\n--- Test 1: Valid Course Enrollment ---");
        try {
            UndergraduateStudent student = new UndergraduateStudent(
                "ENR001", "Enrollment Test", "enr@university.edu", "CS");
            
            Course course = new Course("CS101", "Introduction to CS", 3, "Dr. Smith", 30);
            
            student.enrollCourse(course);
            
            assert student.getEnrolledCourses().size() == 1 : "Should have 1 enrolled course";
            assert course.getEnrolledCount() == 1 : "Course should have 1 student";
            
            System.out.println("✓ PASS: Student enrolled successfully");
            System.out.println("  Student: " + student.toString());
            System.out.println("  Course: " + course.toString());
            passCount++;
        } catch (Exception e) {
            System.out.println("✗ FAIL: " + e.getMessage());
            failCount++;
        }
    }
    
    /**
     * Test course capacity management.
     */
    private static void testCapacityManagement() {
        System.out.println("\n--- Test 2: Course Capacity Management ---");
        try {
            Course course = new Course("CS201", "Data Structures", 4, "Dr. Ahmed", 2);
            
            UndergraduateStudent student1 = new UndergraduateStudent(
                "CAP001", "Student One", "s1@university.edu", "CS");
            
            UndergraduateStudent student2 = new UndergraduateStudent(
                "CAP002", "Student Two", "s2@university.edu", "CS");
            
            UndergraduateStudent student3 = new UndergraduateStudent(
                "CAP003", "Student Three", "s3@university.edu", "CS");
            
            // Fill course to capacity
            student1.enrollCourse(course);
            System.out.println("  Student 1 enrolled");
            student2.enrollCourse(course);
            System.out.println("  Student 2 enrolled");
            
            assert course.getEnrolledCount() == 2 : "Should have 2 students";
            
            // Try to exceed capacity
            System.out.print("  Attempting to exceed capacity: ");
            try {
                student3.enrollCourse(course);
                System.out.println("✗ FAIL: Should have thrown exception");
                failCount++;
            } catch (CourseEnrollmentException e) {
                System.out.println("✓ Exception caught: " + e.getMessage());
                passCount++;
            }
            
            System.out.println("✓ PASS: Capacity management working correctly");
        } catch (Exception e) {
            System.out.println("✗ FAIL: " + e.getMessage());
            failCount++;
        }
    }
    
    /**
     * Test duplicate enrollment prevention.
     */
    private static void testDuplicateEnrollment() {
        System.out.println("\n--- Test 3: Duplicate Enrollment Prevention ---");
        try {
            UndergraduateStudent student = new UndergraduateStudent(
                "DUP001", "Duplicate Test", "dup@university.edu", "CS");
            
            Course course = new Course("CS301", "Algorithms", 3, "Dr. Kumar", 30);
            
            // First enrollment
            student.enrollCourse(course);
            System.out.println("  First enrollment: SUCCESS");
            
            // Attempt duplicate enrollment
            System.out.print("  Attempting duplicate enrollment: ");
            try {
                student.enrollCourse(course);
                System.out.println("✗ FAIL: Should have thrown exception");
                failCount++;
            } catch (CourseEnrollmentException e) {
                System.out.println("✓ Exception caught: " + e.getMessage());
                System.out.println("✓ PASS: Duplicate enrollment prevented");
                passCount++;
            }
        } catch (Exception e) {
            System.out.println("✗ FAIL: " + e.getMessage());
            failCount++;
        }
    }
    
    /**
     * Test drop course functionality.
     */
    private static void testDropCourse() {
        System.out.println("\n--- Test 4: Drop Course Functionality ---");
        try {
            UndergraduateStudent student = new UndergraduateStudent(
                "DROP001", "Drop Test", "drop@university.edu", "CS");
            
            Course course1 = new Course("CS401", "Web Development", 3, "Dr. Lee", 30);
            Course course2 = new Course("CS402", "Mobile Apps", 3, "Dr. Chen", 30);
            
            student.enrollCourse(course1);
            student.enrollCourse(course2);
            
            assert student.getEnrolledCourses().size() == 2 : "Should have 2 courses";
            System.out.println("  Enrolled in 2 courses");
            
            // Drop course
            student.dropCourse("CS401");
            
            assert student.getEnrolledCourses().size() == 1 : "Should have 1 course after drop";
            assert course1.getEnrolledCount() == 0 : "Course 1 should have 0 students";
            
            System.out.println("✓ PASS: Course dropped successfully");
            System.out.println("  Remaining courses: " + student.getEnrolledCourses().size());
            
            // Try to drop non-existent course
            System.out.print("  Attempting to drop non-existent course: ");
            try {
                student.dropCourse("CS999");
                System.out.println("✗ FAIL: Should have thrown exception");
                failCount++;
            } catch (CourseEnrollmentException e) {
                System.out.println("✓ Exception caught: " + e.getMessage());
                passCount++;
            }
        } catch (Exception e) {
            System.out.println("✗ FAIL: " + e.getMessage());
            failCount++;
        }
    }
    
    /**
     * Test invalid course operations.
     */
    private static void testInvalidCourse() {
        System.out.println("\n--- Test 5: Invalid Course Operations ---");
        try {
            UndergraduateStudent student = new UndergraduateStudent(
                "INV001", "Invalid Test", "inv@university.edu", "CS");
            
            System.out.print("  Test 5a (Null Course): ");
            try {
                student.enrollCourse(null);
                System.out.println("✗ FAIL: Should have thrown exception");
                failCount++;
            } catch (CourseEnrollmentException e) {
                System.out.println("✓ PASS: Exception caught - " + e.getMessage());
                passCount++;
            }
            
        } catch (InvalidStudentException e) {
            System.out.println("✗ FAIL: " + e.getMessage());
            failCount++;
        }
    }
    
    /**
     * Prints test summary.
     */
    private static void printSummary() {
        System.out.println("\n========== TEST SUMMARY ==========");
        System.out.println("Total Tests: " + (passCount + failCount));
        System.out.println("Passed: " + passCount);
        System.out.println("Failed: " + failCount);
        System.out.println("Status: " + (failCount == 0 ? "✓ ALL TESTS PASSED" : "✗ SOME TESTS FAILED"));
        System.out.println("================================\n");
    }
}
