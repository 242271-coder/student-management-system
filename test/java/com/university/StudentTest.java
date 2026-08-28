package com.university;

import com.university.exceptions.CourseEnrollmentException;
import com.university.exceptions.InvalidStudentException;

/**
 * Test class for Student functionality.
 * Tests student creation, validation, and basic operations.
 * 
 * @author Student Management System
 * @version 1.0
 */
public class StudentTest {
    private static int passCount = 0;
    private static int failCount = 0;
    
    /**
     * Main test runner.
     * 
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        System.out.println("\n========== STUDENT TEST SUITE ==========");
        
        testValidStudentCreation();
        testInvalidStudentCreation();
        testStudentTypePolymorphism();
        testStudentGPACalculation();
        testPostgraduateThesis();
        
        printSummary();
    }
    
    /**
     * Test valid student creation.
     */
    private static void testValidStudentCreation() {
        System.out.println("\n--- Test 1: Valid Student Creation ---");
        try {
            UndergraduateStudent student = new UndergraduateStudent(
                "TEST001", "Test Student", "test@university.edu", "CS");
            
            assert student.getStudentId().equals("TEST001");
            assert student.getName().equals("Test Student");
            assert student.getEmail().equals("test@university.edu");
            assert student.getMajor().equals("CS");
            
            System.out.println("✓ PASS: Student created successfully");
            System.out.println("  Student: " + student.toString());
            passCount++;
        } catch (InvalidStudentException e) {
            System.out.println("✗ FAIL: " + e.getMessage());
            failCount++;
        }
    }
    
    /**
     * Test invalid student creation.
     */
    private static void testInvalidStudentCreation() {
        System.out.println("\n--- Test 2: Invalid Student Creation ---");
        
        // Test 2a: Invalid Email
        System.out.print("  Test 2a (Invalid Email): ");
        try {
            UndergraduateStudent student = new UndergraduateStudent(
                "TEST002", "Another Student", "invalid-email", "CS");
            System.out.println("✗ FAIL: Should have thrown exception");
            failCount++;
        } catch (InvalidStudentException e) {
            System.out.println("✓ PASS: Exception caught - " + e.getMessage());
            passCount++;
        }
        
        // Test 2b: Null Name
        System.out.print("  Test 2b (Null Name): ");
        try {
            UndergraduateStudent student = new UndergraduateStudent(
                "TEST003", null, "test@university.edu", "CS");
            System.out.println("✗ FAIL: Should have thrown exception");
            failCount++;
        } catch (InvalidStudentException e) {
            System.out.println("✓ PASS: Exception caught - " + e.getMessage());
            passCount++;
        }
        
        // Test 2c: Empty Student ID
        System.out.print("  Test 2c (Empty ID): ");
        try {
            UndergraduateStudent student = new UndergraduateStudent(
                "", "Test Student", "test@university.edu", "CS");
            System.out.println("✗ FAIL: Should have thrown exception");
            failCount++;
        } catch (InvalidStudentException e) {
            System.out.println("✓ PASS: Exception caught - " + e.getMessage());
            passCount++;
        }
    }
    
    /**
     * Test student type polymorphism.
     */
    private static void testStudentTypePolymorphism() {
        System.out.println("\n--- Test 3: Student Type Polymorphism ---");
        try {
            UndergraduateStudent undergrad = new UndergraduateStudent(
                "UG001", "Undergraduate", "ug@university.edu", "Computer Science");
            
            PostgraduateStudent postgrad = new PostgraduateStudent(
                "PG001", "Postgraduate", "pg@university.edu", "AI Research");
            
            // Polymorphic behavior - different display methods
            System.out.println("  Undergraduate display:");
            undergrad.displayDetails();
            
            System.out.println("  Postgraduate display:");
            postgrad.displayDetails();
            
            System.out.println("✓ PASS: Polymorphism demonstrated");
            passCount++;
        } catch (InvalidStudentException e) {
            System.out.println("✗ FAIL: " + e.getMessage());
            failCount++;
        }
    }
    
    /**
     * Test GPA calculation.
     */
    private static void testStudentGPACalculation() {
        System.out.println("\n--- Test 4: GPA Calculation ---");
        try {
            UndergraduateStudent student = new UndergraduateStudent(
                "CALC001", "Calculation Test", "calc@university.edu", "CS");
            
            double gpa = student.calculateGPA();
            
            // Empty enrollment should give 0.0
            assert gpa == 0.0 : "GPA should be 0.0 for no courses";
            
            System.out.println("✓ PASS: GPA calculation correct (empty courses = 0.0)");
            System.out.println("  Student GPA: " + gpa);
            passCount++;
        } catch (InvalidStudentException e) {
            System.out.println("✗ FAIL: " + e.getMessage());
            failCount++;
        } catch (AssertionError e) {
            System.out.println("✗ FAIL: " + e.getMessage());
            failCount++;
        }
    }
    
    /**
     * Test postgraduate thesis submission.
     */
    private static void testPostgraduateThesis() {
        System.out.println("\n--- Test 5: Postgraduate Thesis Submission ---");
        try {
            PostgraduateStudent student = new PostgraduateStudent(
                "THESIS001", "Thesis Student", "thesis@university.edu", "ML Research");
            
            // Test valid thesis submission
            student.submitThesis(92);
            
            assert student.isThesisSubmitted() : "Thesis should be marked as submitted";
            assert student.getThesisMark() == 92 : "Thesis mark should be 92";
            
            System.out.println("✓ PASS: Thesis submitted successfully");
            System.out.println("  Thesis Mark: " + student.getThesisMark());
            System.out.println("  Thesis Submitted: " + student.isThesisSubmitted());
            passCount++;
            
            // Test invalid thesis mark
            System.out.print("  Test 5b (Invalid Thesis Mark): ");
            try {
                student.submitThesis(150);
                System.out.println("✗ FAIL: Should have thrown exception");
                failCount++;
            } catch (IllegalArgumentException e) {
                System.out.println("✓ PASS: Exception caught - " + e.getMessage());
                passCount++;
            }
        } catch (InvalidStudentException e) {
            System.out.println("✗ FAIL: " + e.getMessage());
            failCount++;
        } catch (AssertionError e) {
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
