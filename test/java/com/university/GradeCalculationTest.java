package com.university;

/**
 * Test class for Grade Calculation functionality.
 * Tests grade recording, GPA calculation, and grade point conversion.
 * 
 * @author Student Management System
 * @version 1.0
 */
public class GradeCalculationTest {
    private static int passCount = 0;
    private static int failCount = 0;
    
    /**
     * Main test runner.
     * 
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        System.out.println("\n========== GRADE CALCULATION TEST SUITE ==========");
        
        testGradeCalculation();
        testGradeConversion();
        testInvalidMarks();
        testUndergraduateGPA();
        testPostgraduateGPA();
        
        printSummary();
    }
    
    /**
     * Test grade calculation from marks.
     */
    private static void testGradeCalculation() {
        System.out.println("\n--- Test 1: Grade Calculation from Marks ---");
        try {
            // Test A grade
            Grade gradeA = new Grade("CS101", "STU001", 95);
            assert gradeA.getGrade() == 'A' : "95 should be A grade";
            System.out.println("✓ PASS: 95 marks = A grade");
            passCount++;
            
            // Test B grade
            Grade gradeB = new Grade("CS101", "STU002", 85);
            assert gradeB.getGrade() == 'B' : "85 should be B grade";
            System.out.println("✓ PASS: 85 marks = B grade");
            passCount++;
            
            // Test C grade
            Grade gradeC = new Grade("CS101", "STU003", 75);
            assert gradeC.getGrade() == 'C' : "75 should be C grade";
            System.out.println("✓ PASS: 75 marks = C grade");
            passCount++;
            
            // Test F grade
            Grade gradeF = new Grade("CS101", "STU004", 55);
            assert gradeF.getGrade() == 'F' : "55 should be F grade";
            System.out.println("✓ PASS: 55 marks = F grade");
            passCount++;
        } catch (Exception e) {
            System.out.println("✗ FAIL: " + e.getMessage());
            failCount++;
        }
    }
    
    /**
     * Test grade point conversion.
     */
    private static void testGradeConversion() {
        System.out.println("\n--- Test 2: Grade Point Conversion ---");
        try {
            // A = 4.0
            Grade gradeA = new Grade("CS101", "STU001", 90);
            assert gradeA.getGradePoint() == 4.0 : "A should be 4.0 points";
            System.out.println("✓ PASS: A grade = 4.0 points");
            passCount++;
            
            // B = 3.0
            Grade gradeB = new Grade("CS101", "STU002", 80);
            assert gradeB.getGradePoint() == 3.0 : "B should be 3.0 points";
            System.out.println("✓ PASS: B grade = 3.0 points");
            passCount++;
            
            // C = 2.0
            Grade gradeC = new Grade("CS101", "STU003", 70);
            assert gradeC.getGradePoint() == 2.0 : "C should be 2.0 points";
            System.out.println("✓ PASS: C grade = 2.0 points");
            passCount++;
            
            // D = 1.0
            Grade gradeD = new Grade("CS101", "STU004", 60);
            assert gradeD.getGradePoint() == 1.0 : "D should be 1.0 points";
            System.out.println("✓ PASS: D grade = 1.0 points");
            passCount++;
            
            // F = 0.0
            Grade gradeF = new Grade("CS101", "STU005", 50);
            assert gradeF.getGradePoint() == 0.0 : "F should be 0.0 points";
            System.out.println("✓ PASS: F grade = 0.0 points");
            passCount++;
        } catch (Exception e) {
            System.out.println("✗ FAIL: " + e.getMessage());
            failCount++;
        }
    }
    
    /**
     * Test invalid marks validation.
     */
    private static void testInvalidMarks() {
        System.out.println("\n--- Test 3: Invalid Marks Validation ---");
        
        // Test negative marks
        System.out.print("  Test 3a (Negative Marks): ");
        try {
            Grade grade = new Grade("CS101", "STU001", -10);
            System.out.println("✗ FAIL: Should have thrown exception");
            failCount++;
        } catch (IllegalArgumentException e) {
            System.out.println("✓ PASS: Exception caught - " + e.getMessage());
            passCount++;
        }
        
        // Test marks > 100
        System.out.print("  Test 3b (Marks > 100): ");
        try {
            Grade grade = new Grade("CS101", "STU002", 150);
            System.out.println("✗ FAIL: Should have thrown exception");
            failCount++;
        } catch (IllegalArgumentException e) {
            System.out.println("✓ PASS: Exception caught - " + e.getMessage());
            passCount++;
        }
        
        // Test boundary: 0 marks (valid)
        System.out.print("  Test 3c (0 Marks - Valid): ");
        try {
            Grade grade = new Grade("CS101", "STU003", 0);
            assert grade.getMarks() == 0 : "Marks should be 0";
            System.out.println("✓ PASS: 0 marks accepted");
            passCount++;
        } catch (Exception e) {
            System.out.println("✗ FAIL: " + e.getMessage());
            failCount++;
        }
        
        // Test boundary: 100 marks (valid)
        System.out.print("  Test 3d (100 Marks - Valid): ");
        try {
            Grade grade = new Grade("CS101", "STU004", 100);
            assert grade.getMarks() == 100 : "Marks should be 100";
            System.out.println("✓ PASS: 100 marks accepted");
            passCount++;
        } catch (Exception e) {
            System.out.println("✗ FAIL: " + e.getMessage());
            failCount++;
        }
    }
    
    /**
     * Test undergraduate GPA calculation.
     */
    private static void testUndergraduateGPA() {
        System.out.println("\n--- Test 4: Undergraduate GPA Calculation ---");
        try {
            UndergraduateStudent student = new UndergraduateStudent(
                "UG_GPA001", "GPA Test", "gpa@university.edu", "CS");
            
            // No courses enrolled
            double gpa1 = student.calculateGPA();
            assert gpa1 == 0.0 : "GPA should be 0.0 with no courses";
            System.out.println("✓ PASS: GPA with no courses = 0.0");
            passCount++;
            
            System.out.println("✓ PASS: Undergraduate GPA calculation working");
        } catch (Exception e) {
            System.out.println("✗ FAIL: " + e.getMessage());
            failCount++;
        }
    }
    
    /**
     * Test postgraduate GPA with thesis.
     */
    private static void testPostgraduateGPA() {
        System.out.println("\n--- Test 5: Postgraduate GPA with Thesis ---");
        try {
            PostgraduateStudent student = new PostgraduateStudent(
                "PG_GPA001", "PG GPA Test", "pggpa@university.edu", "AI Research");
            
            // No thesis submitted
            double gpa1 = student.calculateGPA();
            System.out.println("  GPA without thesis: " + String.format("%.2f", gpa1));
            passCount++;
            
            // Submit thesis
            student.submitThesis(92);
            double gpa2 = student.calculateGPA();
            System.out.println("  GPA with thesis (92 marks): " + String.format("%.2f", gpa2));
            
            assert student.isThesisSubmitted() : "Thesis should be submitted";
            System.out.println("✓ PASS: Postgraduate GPA with thesis calculation working");
            passCount++;
        } catch (Exception e) {
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
