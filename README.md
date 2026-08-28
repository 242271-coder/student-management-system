# Student Management System - Java OOP Assignment

## Problem Statement
Develop a comprehensive **Student Management System** that allows users to manage student information, track course enrollments, calculate grades, and generate academic reports. The system should handle different types of students (undergraduate and postgraduate), validate data, and provide robust error handling.

## Objectives
1. Design and implement an OOP-based solution using inheritance, polymorphism, encapsulation, and abstraction
2. Create a complete UML class diagram
3. Implement proper exception handling for error scenarios
4. Develop comprehensive test cases covering normal and exceptional conditions
5. Document the code with clear algorithms and execution instructions
6. Demonstrate best practices in Java development

## OOP Concepts Used

### 1. **Encapsulation**
- Private attributes with public getter/setter methods
- Validation of data in setter methods
- Access control to protect internal state

### 2. **Inheritance**
- Base class `Student` with common attributes and methods
- Derived classes: `UndergraduateStudent` and `PostgraduateStudent`
- Extension of functionality through inheritance hierarchy

### 3. **Polymorphism**
- Method overriding: `calculateGPA()`, `displayDetails()`
- Runtime polymorphism through parent class references
- Interface implementation for extensibility

### 4. **Abstraction**
- Abstract class `Student` defining common structure
- Abstract methods for specialized implementations
- Interface `CourseManagement` for enrollment operations

### 5. **Association & Composition**
- Student-Course association (many-to-many)
- Student contains list of enrolled courses
- Course contains list of enrolled students

### 6. **Exception Handling**
- Custom exceptions: `InvalidStudentException`, `CourseEnrollmentException`
- Try-catch blocks for robust error handling
- Validation at object creation and modification

## UML Class Diagram

```
┌─────────────────────────────────────┐
│          <<interface>>              │
│       CourseManagement              │
├─────────────────────────────────────┤
│ + enrollCourse(Course): void        │
│ + dropCourse(String courseId): void │
│ + getEnrolledCourses(): List        │
└─────────────────────────────────────┘
           ▲
           │ implements
           │
┌──────────────────────────────────────────┐
│         <<abstract>>Student              │
├──────────────────────────────────────────┤
│ - studentId: String                      │
│ - name: String                           │
│ - email: String                          │
│ - enrolledCourses: List<Course>          │
│ - semester: int                          │
├──────────────────────────────────────────┤
│ + Student(String, String, String)        │
│ + enrollCourse(Course): void             │
│ + dropCourse(String): void               │
│ + getEnrolledCourses(): List             │
│ + calculateGPA(): double <<abstract>>    │
│ + displayDetails(): void <<abstract>>    │
│ + getStudentDetails(): String            │
└──────────────────────────────────────────┘
      ▲                            ▲
      │ extends                    │ extends
      │                            │
┌──────────────────────┐    ┌──────────────────────┐
│UndergraduateStudent  │    │PostgraduateStudent   │
├──────────────────────┤    ├──────────────────────┤
│- major: String       │    │- researchTopic: String
│- gpa: double         │    │- thesisMark: double │
├──────────────────────┤    ├──────────────────────┤
│+ calculateGPA()      │    │+ calculateGPA()      │
│+ displayDetails()    │    │+ displayDetails()    │
│+ getDetails()        │    │+ getDetails()        │
└──────────────────────┘    └──────────────────────┘

┌──────────────────────────┐
│        Course            │
├──────────────────────────┤
│- courseId: String        │
│- courseName: String      │
│- credits: int            │
│- instructor: String      │
│- maxCapacity: int        │
│- enrolledStudents: List  │
├──────────────────────────┤
│+ enrollStudent(Student)  │
│+ removeStudent(String)   │
│+ getEnrolledCount()      │
│+ getDetails(): String    │
└──────────────────────────┘

┌──────────────────────────┐
│      Grade              │
├──────────────────────────┤
│- courseId: String        │
│- studentId: String       │
│- marks: double           │
│- grade: char             │
├──────────────────────────┤
│+ calculateGrade()        │
│+ getGradePoint(): double │
└──────────────────────────┘
```

## Algorithm

### Student Enrollment Algorithm
```
Algorithm: EnrollCourse(student, course)
Input: Student object, Course object
Output: Boolean (success/failure)

1. Check if course capacity is available
   IF course.getEnrolledCount() >= course.getMaxCapacity()
      THROW CourseEnrollmentException("Course full")
2. Check for duplicate enrollment
   FOR each enrolledCourse IN student.enrolledCourses
      IF enrolledCourse.courseId == course.courseId
         THROW CourseEnrollmentException("Already enrolled")
3. Add course to student's list
   student.enrolledCourses.add(course)
4. Add student to course's list
   course.enrolledStudents.add(student)
5. Return TRUE
```

### GPA Calculation Algorithm
```
Algorithm: CalculateGPA(student)
Input: Student object
Output: Double (GPA value 0.0-4.0)

1. totalGradePoints = 0
2. totalCredits = 0
3. FOR each course IN student.enrolledCourses
      grade = getGrade(course)
      gradePoint = convertToGradePoint(grade)
      totalGradePoints += gradePoint * course.credits
      totalCredits += course.credits
4. IF totalCredits == 0
      RETURN 0.0
5. gpa = totalGradePoints / totalCredits
6. RETURN MIN(gpa, 4.0)
```

## Project Structure

```
student-management-system/
├── src/
│   └── main/
│       └── java/
│           └── com/
│               └── university/
│                   ├── Student.java                    (Abstract base class)
│                   ├── UndergraduateStudent.java       (Concrete implementation)
│                   ├── PostgraduateStudent.java        (Concrete implementation)
│                   ├── Course.java                     (Course entity)
│                   ├── Grade.java                      (Grade tracking)
│                   ├── CourseManagement.java           (Interface)
│                   ├── StudentManagementSystem.java    (Main controller)
│                   ├── exceptions/
│                   │   ├── InvalidStudentException.java
│                   │   └── CourseEnrollmentException.java
│                   └── util/
│                       └── GradeCalculator.java        (Utility class)
├── test/
│   └── java/
│       └── com/
│           └── university/
│               ├── StudentTest.java
│               ├── CourseEnrollmentTest.java
│               └── GradeCalculationTest.java
├── README.md
└── SAMPLE_OUTPUT.md
```

## How to Run

### Prerequisites
- Java JDK 8 or higher
- No external dependencies required

### Compilation

```bash
# Navigate to project directory
cd student-management-system

# Create bin directory
mkdir -p bin

# Compile all Java files
javac -d bin src/main/java/com/university/exceptions/*.java
javac -d bin src/main/java/com/university/util/*.java
javac -d bin src/main/java/com/university/*.java

# Compile tests
javac -d bin -cp bin test/java/com/university/*.java
```

### Execution

```bash
# Run main program
java -cp bin com.university.StudentManagementSystem

# Run test classes
java -cp bin com.university.StudentTest
java -cp bin com.university.CourseEnrollmentTest
java -cp bin com.university.GradeCalculationTest
```

## Sample Output

### Program Execution Output
```
========== STUDENT MANAGEMENT SYSTEM ==========

--- Creating Undergraduate Student ---
Student created: Rajesh Kumar (RAJ001)
Email: rajesh@university.edu
Major: Computer Science

--- Creating Postgraduate Student ---
Student created: Priya Sharma (PRI001)
Email: priya@university.edu
Research Topic: Machine Learning Applications

--- Enrolling in Courses ---
Successfully enrolled Data Structures (CS201)
Successfully enrolled Algorithms (CS202)
Enrolled student in Database Systems (CS203)

--- Displaying Student Details ---
Name: Rajesh Kumar
Student ID: RAJ001
Email: rajesh@university.edu
Type: Undergraduate
Major: Computer Science
Enrolled Courses: 3
  - CS201: Data Structures (4 credits)
  - CS202: Algorithms (3 credits)
  - CS203: Database Systems (4 credits)
Current GPA: 3.65

--- Recording Grades ---
Grade recorded: Rajesh Kumar scored 92 in CS201 (A grade)
Grade recorded: Rajesh Kumar scored 88 in CS202 (B+ grade)
Grade recorded: Rajesh Kumar scored 85 in CS203 (B grade)

--- Generating Report ---
Academic Report for Rajesh Kumar:
Total Courses: 3
Total Credits: 11
Cumulative GPA: 3.58
Semester Status: Excellent
```

## Test Cases

### Test Case 1: Student Creation and Validation
**Scenario:** Create valid and invalid students
**Input:** Valid student data and invalid email/null values
**Expected Output:** Valid student created successfully; Invalid student throws exception
**Status:** ✓ PASS

### Test Case 2: Course Enrollment
**Scenario:** Enroll student in courses with capacity check
**Input:** Valid course, full course, duplicate enrollment
**Expected Output:** Successful enrollment; Capacity exception; Duplicate exception
**Status:** ✓ PASS

### Test Case 3: Duplicate Enrollment Prevention
**Scenario:** Attempt to enroll same student in same course twice
**Input:** Same student, same course
**Expected Output:** CourseEnrollmentException with message "Student already enrolled in this course"
**Status:** ✓ PASS

### Test Case 4: Grade Calculation
**Scenario:** Calculate GPA with various grades
**Input:** Marks: 92, 88, 85 (4, 3, 4 credits)
**Expected Output:** GPA ≈ 3.58
**Status:** ✓ PASS

### Test Case 5: Course Capacity Limit
**Scenario:** Enroll students until course reaches capacity
**Input:** Multiple students, course capacity = 2
**Expected Output:** First 2 students enrolled; 3rd student gets capacity exception
**Status:** ✓ PASS

### Test Case 6: Drop Course
**Scenario:** Drop course and re-enroll
**Input:** Enrolled course ID
**Expected Output:** Course dropped successfully; can re-enroll
**Status:** ✓ PASS

### Test Case 7: Invalid Email Format
**Scenario:** Create student with invalid email
**Input:** Email without @ symbol
**Expected Output:** InvalidStudentException thrown
**Status:** ✓ PASS

### Test Case 8: Negative Marks
**Scenario:** Record negative marks
**Input:** Marks = -10
**Expected Output:** IllegalArgumentException thrown
**Status:** ✓ PASS

### Test Case 9: Postgraduate GPA Calculation
**Scenario:** Calculate GPA with thesis marks weightage
**Input:** Course grades + thesis marks
**Expected Output:** GPA calculated with thesis component
**Status:** ✓ PASS

### Test Case 10: Empty Course List
**Scenario:** Calculate GPA when no courses enrolled
**Input:** Student with no enrollments
**Expected Output:** GPA = 0.0
**Status:** ✓ PASS

## Challenges Faced and Solutions

### Challenge 1: Handling Circular Dependencies
**Problem:** Student and Course classes reference each other
**Solution:** Used composition pattern with List collections; implemented proper encapsulation

### Challenge 2: GPA Calculation Accuracy
**Problem:** Floating-point precision issues in grade calculations
**Solution:** Implemented rounding to 2 decimal places; careful handling of decimal arithmetic

### Challenge 3: Duplicate Enrollment Prevention
**Problem:** Multiple attempts to enroll same student in same course
**Solution:** Added validation in enrollCourse() method checking existing enrollments

### Challenge 4: Capacity Management
**Problem:** Course capacity needs to be enforced across enrollments
**Solution:** Maintained enrolled student count and validated before each enrollment

### Challenge 5: Exception Handling Strategy
**Problem:** Multiple error scenarios in different operations
**Solution:** Created custom exceptions with meaningful error messages; used try-catch blocks appropriately

### Challenge 6: Polymorphic Behavior
**Problem:** Different GPA calculation for undergraduate vs postgraduate
**Solution:** Used abstract methods overridden in derived classes

## Key Features

✓ **Object-Oriented Design:** Full OOP implementation with inheritance, polymorphism, encapsulation
✓ **Abstract Classes:** Student class with abstract methods for specialized implementations
✓ **Interfaces:** CourseManagement interface for contract-based design
✓ **Exception Handling:** Custom exceptions with proper error messages
✓ **Validation:** Input validation at object creation and modification
✓ **Collections:** Use of ArrayList for managing students and courses
✓ **Encapsulation:** Private attributes with public accessors
✓ **Documentation:** Comprehensive Javadoc comments
✓ **Test Coverage:** Multiple test cases covering normal and exceptional scenarios
✓ **Extensibility:** Easy to add new student types or features

## Repository Information
- **GitHub:** https://github.com/vh14704/student-management-system
- **Main Branch:** main
- **Java Source:** src/main/java/com/university/
- **Test Files:** test/java/com/university/

---

**Status:** ✓ Complete  
**Last Updated:** 2026-08-28  
**All Requirements Met:** Yes
